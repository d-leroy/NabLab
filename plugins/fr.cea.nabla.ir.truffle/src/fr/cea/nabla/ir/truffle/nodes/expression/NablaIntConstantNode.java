package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.values.NV0Int;

public abstract class NablaIntConstantNode extends NablaExpressionNode {

	private final NV0Int value;
	
	public NablaIntConstantNode(int value) {
		this.value = new NV0Int(value);
	}
	
	@Override
	@Specialization
	public NV0Int executeNV0Int(VirtualFrame frame) {
		return this.value;
	}
	
	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
