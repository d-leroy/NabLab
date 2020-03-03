package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV1Real;

public abstract class NablaReal1Node extends NablaExpressionNode {

	@Children
	private final NablaExpressionNode[] values;
	
	public NablaReal1Node(NablaExpressionNode[] values) {
		this.values = values;
	}

	@ExplodeLoop
	@Specialization
	public NV1Real executeNV1Real(VirtualFrame frame) {
		final double[] computedValues = new double[values.length];
		CompilerAsserts.compilationConstant(values.length);
		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV0Real(values[i].executeGeneric(frame)).getData();
		}
		return new NV1Real(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
