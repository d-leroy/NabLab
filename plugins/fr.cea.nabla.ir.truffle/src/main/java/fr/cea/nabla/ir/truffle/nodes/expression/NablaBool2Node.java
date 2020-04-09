package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV2Bool;

public abstract class NablaBool2Node extends NablaExpressionNode {
	
	@Children
	private final NablaExpressionNode[] values;
	@Children
	private final NablaExpressionNode[] dimensions;

	public NablaBool2Node(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
		this.values = values;
		this.dimensions = dimensions;
	}

	@Override
	@ExplodeLoop
	@Specialization
	public NV2Bool executeNV2Bool(VirtualFrame frame) {
		int[] computedDimensions = new int[2];
		for (int i = 0; i < 2; i++) {
			computedDimensions[i] = NablaTypesGen.asNV0Int(dimensions[i].executeGeneric(frame)).getData();
		}
		final boolean[][] computedValues = new boolean[computedDimensions[0]][computedDimensions[1]];
		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV1Bool(values[i].executeGeneric(frame)).getData();
		}
		return new NV2Bool(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
