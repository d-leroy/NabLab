package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV2Int;

public abstract class NablaInt2LiteralNode extends NablaExpressionNode {

	private final NablaExpressionNode[] values;
	private final NablaExpressionNode[] dimensions;

	public NablaInt2LiteralNode(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
		this.values = values;
		this.dimensions = dimensions;
	}

	@ExplodeLoop
	@Specialization
	public NV2Int executeNV2Int(VirtualFrame frame) {

		CompilerAsserts.compilationConstant(dimensions.length);

		int[] computedDimensions = new int[2];

		for (int i = 0; i < 2; i++) {
			computedDimensions[i] = NablaTypesGen.asNV0Int(dimensions[i].executeGeneric(frame)).getData();
		}
		
		final int[][] computedValues = new int[computedDimensions[0]][computedDimensions[1]];
		
		CompilerAsserts.compilationConstant(values.length);

		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV1Int(values[i].executeGeneric(frame)).getData();
		}

		return new NV2Int(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
