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

import fr.cea.nabla.ir.ir.SimpleVariable;
import fr.cea.nabla.interpreter.tools.NablaTags.DumpTag;
import fr.cea.nabla.interpreter.tools.NablaTags.JobTag;
import fr.cea.nabla.javalib.mesh.PvdFileWriter2D;
import fr.cea.nabla.javalib.mesh.Quad;

@Registration(id = NablaDumpVariablesInstrument.ID, name = "Nabla Variables Dump", version = "0.1.3", services = NablaDumpVariablesInstrument.class)
public class NablaDumpVariablesInstrument extends TruffleInstrument {

	static final OptionType<List<String>> STRING_LIST_TYPE = new OptionType<>("String List",
			o -> Arrays.stream(o.split(",")).map(s -> s.trim()).collect(Collectors.toList()));

	@Option(name = "", help = "Enable Nabla Variables Dump (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<Boolean> ENABLED = new OptionKey<>(false);
	@Option(name = "variables", help = "Observe Listed Nabla Variables (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<List<String>> VARIABLES = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);
	@Option(name = "jobs", help = "Observe Listed Nabla Jobs (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<List<String>> JOBS = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);
	@Option(name = "variables-all", help = "Observe All Nabla Variables (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<Boolean> VARIABLES_ALL = new OptionKey<>(false);
	@Option(name = "jobs-all", help = "Observe All Nabla Jobs (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<Boolean> JOBS_ALL = new OptionKey<>(false);
//	@Option(name = "variables", help = "Observe Listed Nabla Variables (default: \\\"\\\").", category = OptionCategory.USER, stability = OptionStability.STABLE)
//	static final OptionKey<OptionMap<String>> properties = OptionKey.mapOf(String.class);

	public static final String ID = "nabla-dump-variables";
	public static final String VARIABLES_OPTION = "nabla-dump-variables.variables";
	public static final String JOBS_OPTION = "nabla-dump-variables.jobs";
	public static final String VARIABLES_ALL_OPTION = "nabla-dump-variables.variables-all";
	public static final String JOBS_ALL_OPTION = "nabla-dump-variables.jobs-all";

	@CompilationFinal
	private int offset;

	@CompilationFinal
	private PvdFileWriter2D writer;
	@CompilationFinal
	private String iterationVariable;
	@CompilationFinal
	private String timeVariable;
	@CompilationFinal
	private String periodVariable;
	@CompilationFinal(dimensions = 1)
	private String[] cellVariables;
	@CompilationFinal(dimensions = 1)
	private String[] nodeVariables;
	private String nodeCoordinatesVariable;

	@CompilationFinal
	private SimpleVariable period;

	private boolean variablesAll = false;
	private boolean jobsAll = false;
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
		final SourceSectionFilter filterDump = SourceSectionFilter.newBuilder().tagIs(DumpTag.class).build();
		final Instrumenter instrumenter = env.getInstrumenter();
		instrumenter.attachExecutionEventFactory(filterDump, new DumpVariablesNodeFactory(this));

		final OptionValues options = env.getOptions();
		final List<String> variables = VARIABLES.getValue(options);
		variablesAll = VARIABLES_ALL.getValue(options);
		final List<String> jobs = JOBS.getValue(options);
		jobsAll = JOBS_ALL.getValue(options);
		if (!variables.isEmpty() || variablesAll) {
			observedVariables.addAll(variables);
			final SourceSectionFilter filterVariables = SourceSectionFilter.newBuilder().tagIs(WriteVariableTag.class)
					.build();
			instrumenter.attachExecutionEventFactory(filterVariables, new WriteVariablesEventNodeFactory(this));
		}
		if (!jobs.isEmpty() || jobsAll) {
			observedJobs.addAll(jobs);
			final SourceSectionFilter filterJobs = SourceSectionFilter.newBuilder().tagIs(JobTag.class).build();
			instrumenter.attachExecutionEventFactory(filterJobs, new JobEventNodeFactory(this));
		}
		final SourceSectionFilter filterNothing = SourceSectionFilter.newBuilder().tagIsNot(StandardTags.TryBlockTag.class).build();
		instrumenter.attachExecutionEventFactory(filterNothing, new AnyEventNodeFactory());
	}

	@TruffleBoundary
	public void dump(int iteration, double time, double[][] nodes, Quad[] cells, Map<String, double[]> cellVariables,
			Map<String, double[]> nodeVariables) {

		// Store value of variable observed and time variable, only dump when time
		// variable present, clear on dump

//TODO		writer.writeFile(iteration, time, nodes, cells, cellVariables, nodeVariables);
	}

	@TruffleBoundary
	private void clearValues() {
		observedVariablesValues.clear();
	}

	@Override
	protected OptionDescriptors getOptionDescriptors() {
		return new NablaDumpVariablesInstrumentOptionDescriptors();
	}

	public boolean isVariableObserved(FrameSlot variableSlot) {
		assert (variableSlot != null);
		return variablesAll || observedVariables.contains(variableSlot.getIdentifier());
	}

	public boolean isJobObserved(String jobName) {
		assert (jobName != null);
		return jobsAll || observedJobs.contains(jobName);
	}

	public void configure(String moduleName, String iterationVariable, String timeVariable,
			String periodVariable, String[] cellVariables, String[] nodeVariables, String nodeCoordinatesVariable,
			SimpleVariable period) {

//TODO		this.writer = new PvdFileWriter2D(moduleName);

		this.iterationVariable = iterationVariable;
		this.timeVariable = timeVariable;
		this.periodVariable = periodVariable;
		this.cellVariables = cellVariables;
		this.nodeVariables = nodeVariables;
		this.nodeCoordinatesVariable = nodeCoordinatesVariable;

		this.period = period;
	}

	public String getIterationVariable() {
		return iterationVariable;
	}

	public String getTimeVariable() {
		return timeVariable;
	}

	public String getPeriodVariable() {
		return periodVariable;
	}

	public String[] getCellVariables() {
		return cellVariables;
	}

	public String[] getNodeVariables() {
		return nodeVariables;
	}

	public double getPeriod() {
		return 1.0; //TODO period;
	}

	public String getNodeCoordinatesVariable() {
		return nodeCoordinatesVariable;
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
