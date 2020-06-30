package fr.cea.nabla.interpreter.tools;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionStability;
import org.graalvm.options.OptionType;
import org.graalvm.options.OptionValues;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Option;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleRuntime;
import com.oracle.truffle.api.TruffleRuntimeAccess;
import com.oracle.truffle.api.instrumentation.Instrumenter;
import com.oracle.truffle.api.instrumentation.SourceSectionFilter;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.StandardTags.RootTag;
import com.oracle.truffle.api.instrumentation.StandardTags.WriteVariableTag;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.interpreter.NablaLanguage;

@Registration(id = NablaLogInstrument.ID, name = "Nabla Logger", version = "0.1.3", services = NablaLogInstrument.class)
public class NablaLogInstrument extends TruffleInstrument {

	static final OptionType<List<String>> STRING_LIST_TYPE = new OptionType<>("String List",
			o -> Arrays.stream(o.split(",")).map(s -> s.trim()).collect(Collectors.toList()));

	@Option(name = "", help = "Enable Logger (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> ENABLED = new OptionKey<>(false);

	@Option(name = "format", help = "Enable Real Numbers Formatting (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> FORMAT = new OptionKey<>(false);

	@Option(name = "range", help = "Specify Shown Range of Arrays (default: 2).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Integer> RANGE = new OptionKey<>(2);

	@Option(name = "jobs", help = "Observe Listed Jobs (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> JOBS = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);

	@Option(name = "variables", help = "Observe Listed Variables (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> VARIABLES = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);

	@Option(name = "toPrint", help = "Configure String to be Printed When Logging (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> TO_PRINT = new OptionKey<>("");

	@Option(name = "variables-all", help = "Observe All Variables (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> VARIABLES_ALL = new OptionKey<>(false);

	@Option(name = "jobs-all", help = "Observe All Jobs (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> JOBS_ALL = new OptionKey<>(false);

	@Option(name = "debug-source", help = "Print Source Sections (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> DEBUG_SOURCE = new OptionKey<>(false);
	
	@Option(name = "expression", help = "Nabla Expression to evaluate (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> NABLA_EXPRESSION = new OptionKey<>("");

	public static final String ID = "nabla-logger";
	public static final String FORMAT_OPTION = "nabla-logger.format";
	public static final String RANGE_OPTION = "nabla-logger.range";
	public static final String JOBS_OPTION = "nabla-logger.jobs";
	public static final String JOBS_ALL_OPTION = "nabla-logger.jobs-all";
	public static final String VARIABLES_OPTION = "nabla-logger.variables";
	public static final String VARIABLES_ALL_OPTION = "nabla-logger.variables-all";
	public static final String TO_PRINT_OPTION = "nabla-logger.toPrint";
	public static final String DEBUG_SOURCE_OPTION = "nabla-logger.debug-source";
	public static final String NABLA_EXPRESSION_OPTION = "nabla-logger.expression";

	@CompilationFinal
	private int offset;

	private boolean variablesAll = false;
	private boolean jobsAll = false;
	private boolean debugSource = false;
	private String toPrint = "";
	private String expression = "";
	private final List<String> observedVariables = new ArrayList<>();
	private final List<String> observedJobs = new ArrayList<>();
	private final Map<String, Supplier<String>> observedVariablesValuePrinters = new HashMap<>();
	private final Map<String, Integer> updateCounter = new HashMap<>();
	
	@CompilationFinal
	private PrintStream out;
	
	@Override
	protected void onCreate(Env env) {
		final OptionValues options = env.getOptions();
		if (ENABLED.getValue(options)) {
			enable(env);
			env.registerService(this);
			out = new PrintStream(env.out());
		}
	}
	
	private void enable(final Env env) {
		final Instrumenter instrumenter = env.getInstrumenter();
		final OptionValues options = env.getOptions();

		final List<String> jobs = JOBS.getValue(options);
		jobsAll = JOBS_ALL.getValue(options);
		if (!jobs.isEmpty() || jobsAll) {
			observedJobs.addAll(jobs);
			final SourceSectionFilter filterJobs = SourceSectionFilter.newBuilder() //
					.tagIs(RootTag.class) //
					.rootNameIs(s -> jobs.contains(s)) //
					.build();
//			instrumenter.attachExecutionEventFactory(filterJobs, new JobEventNodeFactory(this, ""));
		}

		final List<String> variables = VARIABLES.getValue(options);
		variablesAll = VARIABLES_ALL.getValue(options);
		if (!variables.isEmpty() || variablesAll) {
			observedVariables.addAll(variables);
			variables.forEach(s -> updateCounter.put(s, 0));
			final SourceSectionFilter filterVariables = SourceSectionFilter.newBuilder() //
					.tagIs(WriteVariableTag.class) //
					.build();
			instrumenter.attachExecutionEventFactory(filterVariables,
					new WriteVariablesEventNodeFactory(this, FORMAT.getValue(options), RANGE.getValue(options)));
		}

		toPrint = TO_PRINT.getValue(options);

		debugSource = DEBUG_SOURCE.getValue(options);
		if (debugSource) {
			final SourceSectionFilter filterNothing = SourceSectionFilter.newBuilder()
					.tagIsNot(StandardTags.TryBlockTag.class).build();
			instrumenter.attachExecutionEventFactory(filterNothing, new AnyEventNodeFactory());
		}
		
		expression = NABLA_EXPRESSION.getValue(options);

	}
	
	

	@TruffleBoundary
	private void clearValues() {
		observedVariablesValuePrinters.clear();
	}

	@Override
	protected OptionDescriptors getOptionDescriptors() {
		return new NablaLogInstrumentOptionDescriptors();
	}

	public boolean isVariableObserved(String name) {
		return variablesAll || observedVariables.contains(name);
	}

	@TruffleBoundary
	public void inc(String variableName, Supplier<String> valuePrinter) {
		observedVariablesValuePrinters.put(variableName, valuePrinter);
		updateCounter.compute(variableName, (s, i) -> i + 1);
	}

	@TruffleBoundary
	public void jobExecuted(String jobName) {
		out.println("=========== " + jobName + " end ===========");
		if (toPrint.isEmpty()) {
			observedVariablesValuePrinters.forEach((s, v) -> {
				out.println(v.get());
			});
		} else {
			final Object[] values = observedVariables.stream().map(n -> observedVariablesValuePrinters.get(n).get())
					.collect(Collectors.toList()).toArray();
			out.println(MessageFormat.format(toPrint, values));
		}
	}

	@TruffleBoundary
	public void jobAboutToExecute(String jobName) {
		out.println("=========== " + jobName + " start ===========");
		if (toPrint.isEmpty()) {
			observedVariablesValuePrinters.forEach((s, v) -> {
				out.println(v.get());
			});
		} else {
			final Object[] values = observedVariables.stream().map(n -> observedVariablesValuePrinters.get(n).get())
					.collect(Collectors.toList()).toArray();
			out.println(MessageFormat.format(toPrint, values));
		}
	}

}
