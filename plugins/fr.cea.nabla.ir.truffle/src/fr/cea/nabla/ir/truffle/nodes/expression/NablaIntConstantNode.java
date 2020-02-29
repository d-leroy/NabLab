package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public class NablaIntConstantNode extends NablaExpressionNode {

	private final NV0Int value;
	
	public NablaIntConstantNode(int value) {
		this.value = new NV0Int(value);
	}
	
	@Override
	public boolean isInstrumentable() {
		return false;
	}

	@Override
	public NablaValue executeGeneric(VirtualFrame frame) {
		return this.value;
	}
	
	@Override
	public NV0Int executeNV0Int(VirtualFrame frame) throws UnexpectedResultException {
		return this.value;
	}

}
