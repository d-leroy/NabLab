package fr.cea.nabla.interpreter.tools;

import java.util.function.Supplier;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;

@NodeChild(value = "print", type = PrettyPrintValueNode.class)
public abstract class WriteVariableEventNode extends ExecutionEventNode {

	private final NablaLogInstrument nablaLogInstrument;

	private final String name;

	public WriteVariableEventNode(NablaLogInstrument nablaLogInstrument, String name) {
		this.nablaLogInstrument = nablaLogInstrument;
		this.name = name;
	}
	
	@Override
	protected void onReturnValue(VirtualFrame frame, Object result) {
		execute(frame);
	}

	protected abstract void execute(VirtualFrame frame);
	
	@Specialization
	protected void print(VirtualFrame frame, Supplier<String> stringPrinter) {
		nablaLogInstrument.inc(name, stringPrinter);
	}
	
}
