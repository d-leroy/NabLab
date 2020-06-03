package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;

@NodeChild(value = "print", type = PrettyPrintValueNode.class)
public abstract class WriteVariableEventNode extends ExecutionEventNode {

	private final NablaLogInstrument nablaLogInstrument;

	private final FrameSlot variableSlot;

	private final String source;
	
	public WriteVariableEventNode(NablaLogInstrument nablaLogInstrument, FrameSlot variableSlot, String source) {
		assert (variableSlot != null);
		this.nablaLogInstrument = nablaLogInstrument;
		this.variableSlot = variableSlot;
		this.source = source;
	}
	
	@Override
	protected void onReturnValue(VirtualFrame frame, Object result) {
		execute(frame);
	}

	protected abstract void execute(VirtualFrame frame);
	
	@Specialization
	protected void print(VirtualFrame frame, String string) {
		nablaLogInstrument.inc(variableSlot, source + ": " + string);
	}
	
}
