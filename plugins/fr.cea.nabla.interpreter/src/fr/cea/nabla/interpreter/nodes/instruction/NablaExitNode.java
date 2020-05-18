package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;

@NodeChild(value = "value", type = NablaExpressionNode.class)
public class NablaExitNode extends NablaInstructionNode {

	private final String message;
	
	public NablaExitNode(String message) {
		this.message = message;
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		throw new RuntimeException(message);
	}
	
}
