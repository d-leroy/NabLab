package fr.cea.nabla.ir.truffle.tools;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class WriteVariableEventNode extends ExecutionEventNode {
	
	private final NablaDumpVariablesInstrument nablaDumpVariablesInstrument;
	
	private final String variableName;
	
	@CompilationFinal
	private Frame rootFrame;
	@CompilationFinal
	private FrameSlot variableSlot;
	
	public WriteVariableEventNode(NablaDumpVariablesInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
		
		this.variableName = "";
//		this.iterationVariable = nablaDumpVariablesInstrument.getIterationVariable();
//		this.timeVariable = nablaDumpVariablesInstrument.getTimeVariable();
//		this.periodVariable = nablaDumpVariablesInstrument.getPeriodVariable();
//		this.cellVariables = nablaDumpVariablesInstrument.getCellVariables();
//		this.nodeVariables = nablaDumpVariablesInstrument.getNodeVariables();
//		this.nodeCoordinatesVariable = nablaDumpVariablesInstrument.getNodeCoordinatesVariable();
//		
//		this.period = nablaDumpVariablesInstrument.getPeriod();
	}
	
	@Override
	@ExplodeLoop
	protected void onReturnValue(VirtualFrame frame, Object result) {
		if (rootFrame == null) {
			rootFrame = frame;
			
			while (rootFrame.getArguments().length != 0) {
				rootFrame = (Frame) rootFrame.getArguments()[0];
			}
		}
	}
}
