package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import fr.cea.nabla.ir.ir.RealConstant;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public class NablaRealConstantNode extends NablaExpressionNode {

	private final NV0Real value;
	
	public NablaRealConstantNode(RealConstant realConstant) {
		this.value = new NV0Real(realConstant.getValue());
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
	public NV0Real executeNV0Real(VirtualFrame frame) throws UnexpectedResultException {
		return this.value;
	}

}
