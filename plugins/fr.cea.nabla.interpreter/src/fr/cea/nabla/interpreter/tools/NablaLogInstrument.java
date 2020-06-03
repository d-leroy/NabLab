package fr.cea.nabla.interpreter.tools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionStability;
import org.graalvm.options.OptionType;
import org.graalvm.options.OptionValues;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Option;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.instrumentation.Instrumenter;
import com.oracle.truffle.api.instrumentation.SourceSectionFilter;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.StandardTags.WriteVariableTag;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;

import fr.cea.nabla.interpreter.tools.NablaTags.JobTag;

@Registration(id = NablaLogInstrument.ID, name = "Nabla Logger", version = "0.1.3", services = NablaLogInstrument.class)
public class NablaLogInstrument extends TruffleInstrument {

	static final OptionType<List<String>> STRING_LIST_TYPE = new OptionType<>("String List",
			o -> Arrays.stream(o.split(",")).map(s -> s.trim()).collect(Collectors.toList()));

	@Option(name = "", help = "Enable Nabla Logger (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> ENABLED = new OptionKey<>(false);
	@Option(name = "format", help = "Enable Real Numbers Formatting (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> FORMAT = new OptionKey<>(false);
	@Option(name = "variables", help = "Observe Listed Nabla Variables (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> VARIABLES = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);
	@Option(name = "jobs", help = "Observe Listed Nabla Jobs (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> JOBS = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);
	@Option(name = "variables-all", help = "Observe All Nabla Variables (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> VARIABLES_ALL = new OptionKey<>(false);
	@Option(name = "jobs-all", help = "Observe All Nabla Jobs (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> JOBS_ALL = new OptionKey<>(false);
	@Option(name = "debug-source", help = "Print Source Sections (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<Boolean> DEBUG_SOURCE = new OptionKey<>(false);

	public static final String ID = "nabla-logger";
	public static final String FORMAT_OPTION = "nabla-logger.format";
	public static final String VARIABLES_OPTION = "nabla-logger.variables";
	public static final String JOBS_OPTION = "nabla-logger.jobs";
	public static final String VARIABLES_ALL_OPTION = "nabla-logger.variables-all";
	public static final String JOBS_ALL_OPTION = "nabla-logger.jobs-all";
	public static final String DEBUG_SOURCE_OPTION = "nabla-logger.debug-source";

	@CompilationFinal
	private int offset;

	private boolean variablesAll = false;
	private boolean jobsAll = false;
	private boolean debugSource = false;
	private final List<String> observedVariables = new ArrayList<>();
	private final List<String> observedJobs = new ArrayList<>();
	private final Map<FrameSlot, Object> observedVariablesValues = new HashMap<>();

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
		final List<String> variables = VARIABLES.getValue(options);
		variablesAll = VARIABLES_ALL.getValue(options);
		final List<String> jobs = JOBS.getValue(options);
		jobsAll = JOBS_ALL.getValue(options);
		if (!variables.isEmpty() || variablesAll) {
			observedVariables.addAll(variables);
			final SourceSectionFilter filterVariables = SourceSectionFilter.newBuilder().tagIs(WriteVariableTag.class)
					.build();
			instrumenter.attachExecutionEventFactory(filterVariables, new WriteVariablesEventNodeFactory(this, FORMAT.getValue(options)));
		}
		if (!jobs.isEmpty() || jobsAll) {
			observedJobs.addAll(jobs);
			final SourceSectionFilter filterJobs = SourceSectionFilter.newBuilder().tagIs(JobTag.class).build();
			instrumenter.attachExecutionEventFactory(filterJobs, new JobEventNodeFactory(this));
		}
		debugSource = DEBUG_SOURCE.getValue(options);
		if (debugSource) {
			final SourceSectionFilter filterNothing = SourceSectionFilter.newBuilder().tagIsNot(StandardTags.TryBlockTag.class).build();
			instrumenter.attachExecutionEventFactory(filterNothing, new AnyEventNodeFactory());
		}
	}

	@TruffleBoundary
	private void clearValues() {
		observedVariablesValues.clear();
	}

	@Override
	protected OptionDescriptors getOptionDescriptors() {
		return new NablaLogInstrumentOptionDescriptors();
	}

	public boolean isVariableObserved(FrameSlot variableSlot) {
		assert (variableSlot != null);
		return variablesAll || observedVariables.contains(variableSlot.getIdentifier());
	}

	public boolean isJobObserved(String jobName) {
		assert (jobName != null);
		return jobsAll || observedJobs.contains(jobName);
	}

	@TruffleBoundary
	public void inc(FrameSlot variableSlot, Object value) {
		observedVariablesValues.put(variableSlot, value);
	}

	@TruffleBoundary
	public void jobExecuted(String jobName) {
		out.println("=========== " + jobName + " end ===========");
		observedVariablesValues.forEach((s, v) -> {
			out.println(s.getIdentifier() + ": " + v);
		});
	}

	@TruffleBoundary
	public void jobAboutToExecute(String jobName) {
		out.println("=========== " + jobName + " start ===========");
		observedVariablesValues.forEach((s, v) -> {
			out.println(s.getIdentifier() + ": " + v);
		});
	}

}
