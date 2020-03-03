package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV2Real;

public abstract class NablaReal2LiteralNode extends NablaExpressionNode {

	private final NablaExpressionNode[] values;
	private final NablaExpressionNode[] dimensions;

	public NablaReal2LiteralNode(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
		this.values = values;
		this.dimensions = dimensions;
	}

	@ExplodeLoop
	@Specialization
	public NV2Real executeNV2Real(VirtualFrame frame) {

		CompilerAsserts.compilationConstant(dimensions.length);

		int[] computedDimensions = new int[2];

		for (int i = 0; i < 2; i++) {
			computedDimensions[i] = NablaTypesGen.asNV0Int(dimensions[i].executeGeneric(frame)).getData();
		}
		
		final double[][] computedValues = new double[computedDimensions[0]][computedDimensions[1]];
		
		CompilerAsserts.compilationConstant(values.length);

		for (int i = 0; i < values.length; i++) {
			computedValues[i] = NablaTypesGen.asNV1Real(values[i].executeGeneric(frame)).getData();
		}

		return new NV2Real(computedValues);
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
