package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV4Int;

public abstract class NablaInt4ConstantNode extends NablaExpressionNode {

	@CompilationFinal
	private NV4Int cachedValue;

	@Child
	private NablaExpressionNode value;
	@Children
	private final NablaExpressionNode[] dimensions;

	public NablaInt4ConstantNode(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
		this.value = value;
		this.dimensions = dimensions;
	}

	@ExplodeLoop
	@Specialization
	public NV4Int executeNV4Int(VirtualFrame frame) {
		if (cachedValue == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			assert(dimensions.length == 3);
			final int[] computedDimensions = new int[] {NablaTypesGen.asNV0Int(dimensions[0].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[1].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[2].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[3].executeGeneric(frame)).getData()
			};
			final int[][][][] computedValues = new int[computedDimensions[0]][computedDimensions[1]][computedDimensions[2]][computedDimensions[3]];
			final int val = NablaTypesGen.asNV0Int(value.executeGeneric(frame)).getData();
			for (int i = 0; i < computedDimensions[0]; i++) {
				for (int j = 0; j < computedDimensions[1]; j++) {
					for (int k = 0; k < computedDimensions[2]; k++) {
						for (int l = 0; l < computedDimensions[3]; l++) {
							computedValues[i][j][k][l] = val;
						}
					}
				}
			}
			cachedValue = new NV4Int(computedValues);
		}

		return cachedValue;
	}
}
