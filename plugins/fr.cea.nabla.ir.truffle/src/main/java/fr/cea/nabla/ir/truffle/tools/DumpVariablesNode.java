package fr.cea.nabla.ir.truffle.tools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.FunctionCallHelper;
import fr.cea.nabla.ir.truffle.values.NablaValue;
import fr.cea.nabla.javalib.mesh.Quad;

public class DumpVariablesNode extends ExecutionEventNode {
	
	private final NablaDumpVariablesInstrument nablaDumpVariablesInstrument;
	
	private final String iterationVariable;
	private final String timeVariable;
	private final String periodVariable;
	@CompilationFinal(dimensions = 1)
	private final String[] cellVariables;
	@CompilationFinal(dimensions = 1)
	private final String[] nodeVariables;
	private final String nodeCoordinatesVariable;
	
	@CompilationFinal
	private Frame rootFrame;
	@CompilationFinal
	private FrameSlot iterationSlot;
	@CompilationFinal
	private FrameSlot timeSlot;
	@CompilationFinal
	private FrameSlot periodSlot;
	@CompilationFinal(dimensions = 1)
	private FrameSlot[] cellSlots;
	@CompilationFinal(dimensions = 1)
	private FrameSlot[] nodeSlots; 
	private FrameSlot nodeCoordinatesSlot;
	
	private final double period;
	private double lastDump = Double.MIN_VALUE;
	
	public DumpVariablesNode(NablaDumpVariablesInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
		
		this.iterationVariable = nablaDumpVariablesInstrument.getIterationVariable();
		this.timeVariable = nablaDumpVariablesInstrument.getTimeVariable();
		this.periodVariable = nablaDumpVariablesInstrument.getPeriodVariable();
		this.cellVariables = nablaDumpVariablesInstrument.getCellVariables();
		this.nodeVariables = nablaDumpVariablesInstrument.getNodeVariables();
		this.nodeCoordinatesVariable = nablaDumpVariablesInstrument.getNodeCoordinatesVariable();
		
		this.period = nablaDumpVariablesInstrument.getPeriod();
	}
	
	@Override
	@ExplodeLoop
	protected void onReturnValue(VirtualFrame frame, Object result) {
		if (rootFrame == null) {
			rootFrame = frame;
			
			while (rootFrame.getArguments().length != 0) {
				rootFrame = (Frame) rootFrame.getArguments()[0];
			}
			final FrameDescriptor rootDescriptor = rootFrame.getFrameDescriptor();
			iterationSlot = rootDescriptor.findFrameSlot(iterationVariable);
			timeSlot = rootDescriptor.findFrameSlot(timeVariable);
			periodSlot = rootDescriptor.findFrameSlot(periodVariable);
			cellSlots = Arrays.stream(cellVariables).map(v -> rootDescriptor.findFrameSlot(v))
					.collect(Collectors.toList()).toArray(new FrameSlot[0]);
			nodeSlots = Arrays.stream(nodeVariables).map(v -> rootDescriptor.findFrameSlot(v))
					.collect(Collectors.toList()).toArray(new FrameSlot[0]);
			nodeCoordinatesSlot = rootDescriptor.findFrameSlot(nodeCoordinatesVariable);
		}
		
		try {
			double periodValue = NablaTypesGen.asNV0Int(rootFrame.getObject(periodSlot)).getData();
			
			if (lastDump + period <= periodValue) {
				lastDump = periodValue;
				final int iteration = NablaTypesGen.asNV0Int(rootFrame.getObject(iterationSlot)).getData();
				final double time = NablaTypesGen.asNV0Real(rootFrame.getObject(timeSlot)).getData();
				final double[][] nodes = NablaTypesGen.asNV2Real(rootFrame.getObject(nodeCoordinatesSlot)).getData();;
				final Quad[] cells = NablaContext.getMeshWrapper().getQuads();
				final Map<String, double[]> cellVariables = new HashMap<>();
				final Map<String, double[]> nodeVariables = new HashMap<>();

				for (int i = 0; i < this.cellVariables.length; i++) {
					cellVariables.put(this.cellVariables[i], getJavaValue((NablaValue) rootFrame.getObject(cellSlots[i])));
				}
				for (int i = 0; i < this.nodeVariables.length; i++) {
					cellVariables.put(this.nodeVariables[i], getJavaValue((NablaValue) rootFrame.getObject(nodeSlots[i])));
				}
				
				nablaDumpVariablesInstrument.dump(iteration, time, nodes, cells, cellVariables, nodeVariables);
			}
		} catch (FrameSlotTypeException e) {
			e.printStackTrace();
		}
	}

	@TruffleBoundary
	private double[] getJavaValue(NablaValue value) {
		return (double[]) FunctionCallHelper.getJavaValue(value);
	}
}
