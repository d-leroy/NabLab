package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.ir.BoolConstant;
import fr.cea.nabla.ir.truffle.values.NV0Bool;

public abstract class NablaBoolConstantNode extends NablaExpressionNode {

	private final NV0Bool value;
	
	public NablaBoolConstantNode(BoolConstant boolConstant) {
		this.value = new NV0Bool(boolConstant.isValue());
	}
	
	@Override
	@Specialization
	public NV0Bool executeNV0Bool(VirtualFrame frame) {
		return this.value;
	}
	
	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
