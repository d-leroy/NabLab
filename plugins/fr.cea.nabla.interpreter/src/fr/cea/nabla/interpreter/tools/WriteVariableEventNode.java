package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;

@NodeChild(value = "print", type = PrettyPrintValueNode.class)
public abstract class WriteVariableEventNode extends ExecutionEventNode {

	private final NablaDumpVariablesInstrument nablaDumpVariablesInstrument;

	private final FrameSlot variableSlot;

	public WriteVariableEventNode(NablaDumpVariablesInstrument nablaDumpVariablesInstrument, FrameSlot variableSlot) {
		assert (variableSlot != null);
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
		this.variableSlot = variableSlot;
	}
	
	@Override
	protected void onReturnValue(VirtualFrame frame, Object result) {
		execute(frame);
	}

	protected abstract void execute(VirtualFrame frame);
	
	@Specialization
	protected void print(VirtualFrame frame, String string) {
		nablaDumpVariablesInstrument.inc(variableSlot, string);
	}
	
}
