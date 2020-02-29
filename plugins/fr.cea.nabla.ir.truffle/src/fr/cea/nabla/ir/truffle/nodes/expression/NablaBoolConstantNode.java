package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import fr.cea.nabla.ir.ir.BoolConstant;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public class NablaBoolConstantNode extends NablaExpressionNode {

	private final NV0Bool value;
	
	public NablaBoolConstantNode(BoolConstant boolConstant) {
		this.value = new NV0Bool(boolConstant.isValue());
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
	public NV0Bool executeNV0Bool(VirtualFrame frame) throws UnexpectedResultException {
		return this.value;
	}

}
