package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;

@NodeChild(value = "value", type = NablaExpressionNode.class)
public abstract class NablaBaseTypeConstantNode extends NablaExpressionNode {

	@Specialization
	public NV0Bool get(VirtualFrame frame, NV0Bool value) {
		return value;
	}

	@Specialization
	public NV0Int get(VirtualFrame frame, NV0Int value) {
		return value;
	}

	@Specialization
	public NV0Real get(VirtualFrame frame, NV0Real value) {
		return value;
	}

}
