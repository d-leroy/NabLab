package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV1Bool;

public abstract class NablaBool1Node extends NablaExpressionNode {

	@Children
	private final NablaExpressionNode[] values;
	
	public NablaBool1Node(NablaExpressionNode[] values) {
		this.values = values;
	}

	@Override
	@ExplodeLoop
	@Specialization
	public NV1Bool executeNV1Bool(VirtualFrame frame) {
		final boolean[] computedValues = new boolean[values.length];
		CompilerAsserts.compilationConstant(values.length);
		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV0Bool(values[i].executeGeneric(frame)).isData();
		}
		return new NV1Bool(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
