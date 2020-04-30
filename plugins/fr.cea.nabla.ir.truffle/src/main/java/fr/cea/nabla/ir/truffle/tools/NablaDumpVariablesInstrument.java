package fr.cea.nabla.ir.truffle.tools;

import java.util.Map;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionStability;
import org.graalvm.options.OptionValues;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Option;
import com.oracle.truffle.api.instrumentation.Instrumenter;
import com.oracle.truffle.api.instrumentation.SourceSectionFilter;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;

import fr.cea.nabla.ir.truffle.tools.NablaTags.DumpTag;
import fr.cea.nabla.javalib.mesh.PvdFileWriter2D;
import fr.cea.nabla.javalib.mesh.Quad;

@Registration(id = NablaDumpVariablesInstrument.ID, name = "Nabla Variables Dump", version = "0.1.3", services = NablaDumpVariablesInstrument.class)
public class NablaDumpVariablesInstrument extends TruffleInstrument {

	@Option(name = "", help = "Enable Nabla Variables Dump (default: false).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	static final OptionKey<Boolean> ENABLED = new OptionKey<>(false);

	public static final String ID = "nabla-dump-variables";

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
	private double period;

	@Override
	protected void onCreate(Env env) {
		final OptionValues options = env.getOptions();
		if (ENABLED.getValue(options)) {
			enable(env);
			env.registerService(this);
		}
	}

	private void enable(final Env env) {
		SourceSectionFilter filter = SourceSectionFilter.newBuilder().tagIs(DumpTag.class)
//				.sourceIs(s -> {
//						if (s.hasCharacters()) {
//							if (s.getCharacters().length() > offset) {
//								String c = "" + s.getCharacters().charAt(offset);
//								System.out.println("Char at: " + c);
//								return iterationVariable.equals(c);
//							}
//						}
//						return false;
//					})
				.build();
		Instrumenter instrumenter = env.getInstrumenter();
		instrumenter.attachExecutionEventFactory(filter, new DumpVariablesNodeFactory(this));
		instrumenter.attachExecutionEventFactory(filter, new DumpVariablesNodeFactory(this));
	}

	@TruffleBoundary
	public void dump(int iteration, double time, double[][] nodes, Quad[] cells, Map<String, double[]> cellVariables,
			Map<String, double[]> nodeVariables) {
		writer.writeFile(iteration, time, nodes, cells, cellVariables, nodeVariables);
	}

	@Override
	protected OptionDescriptors getOptionDescriptors() {
		return new NablaDumpVariablesInstrumentOptionDescriptors();
	}

	public void configure(int offset, String moduleName, String iterationVariable, String timeVariable, String periodVariable,
			String[] cellVariables, String[] nodeVariables, String nodeCoordinatesVariable, double period) {
		this.offset = offset;
		
		this.writer = new PvdFileWriter2D(moduleName);

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
		return period;
	}

	public String getNodeCoordinatesVariable() {
		return nodeCoordinatesVariable;
	}

}
