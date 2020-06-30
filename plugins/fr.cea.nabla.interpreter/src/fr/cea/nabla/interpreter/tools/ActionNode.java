package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class ActionNode extends Node {
	
	public abstract void execute(VirtualFrame frame);
	
}
