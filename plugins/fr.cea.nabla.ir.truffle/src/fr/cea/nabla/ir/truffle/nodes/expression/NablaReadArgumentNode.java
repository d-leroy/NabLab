package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.runtime.NablaNull;

public abstract class NablaReadArgumentNode extends NablaExpressionNode {

	private final int index;

	public NablaReadArgumentNode(int index) {
		this.index = index;
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		Object[] args = frame.getArguments();
		if (index < args.length) {
			return args[index];
		} else {
			return NablaNull.SINGLETON;
		}
	}

}
