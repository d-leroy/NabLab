package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;

public abstract class NablaBoolConstantNode extends NablaExpressionNode {

	private final NV0Bool value;
	
	public NablaBoolConstantNode(boolean value) {
		this.value = new NV0Bool(value);
	}
	
	@Override
	@Specialization
	public NV0Bool executeNV0Bool(VirtualFrame frame) {
		return this.value;
	}
}
