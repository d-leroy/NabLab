package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;

public class TrueNode extends ConditionNode {

	@Override
	public boolean execute(VirtualFrame frame) {
		return true;
	}
	
}
