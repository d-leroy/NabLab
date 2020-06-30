package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExecutableNode;

public class RootNodeNameNode extends ExecutableNode {

	protected RootNodeNameNode() {
		super(null);
	}

	@Override
	public Object execute(VirtualFrame frame) {
		return getRootNode().getName();
	}

}
