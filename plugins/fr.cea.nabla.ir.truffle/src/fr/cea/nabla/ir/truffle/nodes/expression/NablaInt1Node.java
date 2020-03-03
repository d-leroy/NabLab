package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV1Int;

public abstract class NablaInt1Node extends NablaExpressionNode {

	@Children
	private final NablaExpressionNode[] values;
	
	public NablaInt1Node(NablaExpressionNode[] values) {
		this.values = values;
	}

	@ExplodeLoop
	@Specialization
	public NV1Int executeNV1Int(VirtualFrame frame) {
		final int[] computedValues = new int[values.length];
		CompilerAsserts.compilationConstant(values.length);
		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV0Int(values[i].executeGeneric(frame)).getData();
		}
		return new NV1Int(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
