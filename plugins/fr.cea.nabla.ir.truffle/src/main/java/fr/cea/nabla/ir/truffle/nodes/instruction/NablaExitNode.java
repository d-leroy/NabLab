package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

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
