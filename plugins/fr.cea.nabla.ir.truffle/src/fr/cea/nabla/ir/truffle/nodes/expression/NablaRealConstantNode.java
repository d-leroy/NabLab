package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.ir.RealConstant;
import fr.cea.nabla.ir.truffle.values.NV0Real;

public abstract class NablaRealConstantNode extends NablaExpressionNode {

	private final NV0Real value;
	
	public NablaRealConstantNode(RealConstant realConstant) {
		this.value = new NV0Real(realConstant.getValue());
	}

	@Override
	@Specialization
	public NV0Real executeNV0Real(VirtualFrame frame) {
		return this.value;
	}
	
	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
