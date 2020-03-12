package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Real;

public abstract class NablaRealConstantNode extends NablaExpressionNode {

	private final NV0Real value;
	
	public NablaRealConstantNode(double value) {
		this.value = new NV0Real(value);
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
