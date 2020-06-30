package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class ConditionNode extends Node {
	
	public abstract boolean execute(VirtualFrame frame);
	
}
