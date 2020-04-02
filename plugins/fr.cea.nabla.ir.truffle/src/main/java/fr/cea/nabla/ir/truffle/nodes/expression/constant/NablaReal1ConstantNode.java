package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV1Real;

public abstract class NablaReal1ConstantNode extends NablaExpressionNode {

	@Child
	private NablaExpressionNode value;
	@Child
	private NablaExpressionNode size;
	
	//TODO use assumptions
	public NablaReal1ConstantNode(NablaExpressionNode value, NablaExpressionNode size) {
		this.value = value;
		this.size = size;
	}

	
	@Specialization
	public NV1Real executeNV1Real(VirtualFrame frame) {
		final int size = NablaTypesGen.asNV0Int(this.size.executeGeneric(frame)).getData();
		final double val = NablaTypesGen.asNV0Real(value.executeGeneric(frame)).getData();
		final double[] computedValues = new double[size];
		for (int i = 0; i < size; i++) {
			computedValues[i] = val;
		}
		return new NV1Real(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
