package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.BlockNode;
import com.oracle.truffle.api.nodes.ExecutableNode;
import com.oracle.truffle.api.nodes.Node;

public abstract class LayoutNode extends Node implements BlockNode.ElementExecutor<ExecutableNode> {

	@Child
	private BlockNode<ExecutableNode> valueNodes;

	public LayoutNode(ExecutableNode[] valueNodes) {
		this.valueNodes = BlockNode.create(valueNodes, this);
	}

	public abstract Object[] execute(VirtualFrame frame);

	@Override
	public void executeVoid(VirtualFrame frame, ExecutableNode node, int index, int argument) {
		
	}

	@Override
	public Object executeGeneric(VirtualFrame frame, ExecutableNode node, int index, int argument) {
		return null;
	}

}
