package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV4Real;

public abstract class NablaReal4ConstantNode extends NablaExpressionNode {

	@CompilationFinal
	private NV4Real cachedValue;

	@Child
	private NablaExpressionNode value;
	@Children
	private final NablaExpressionNode[] dimensions;

	public NablaReal4ConstantNode(NablaExpressionNode value, NablaExpressionNode[] dimensions) {
		this.value = value;
		this.dimensions = dimensions;
	}

	
	@Specialization
	public NV4Real executeNV4Real(VirtualFrame frame) {
		if (cachedValue == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			assert(dimensions.length == 4);
			final int[] computedDimensions = new int[] {NablaTypesGen.asNV0Int(dimensions[0].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[1].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[2].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(dimensions[3].executeGeneric(frame)).getData()
			};
			final double[][][][] computedValues = new double[computedDimensions[0]][computedDimensions[1]][computedDimensions[2]][computedDimensions[3]];
			final double val = NablaTypesGen.asNV0Real(value.executeGeneric(frame)).getData();
			for (int i = 0; i < computedDimensions[0]; i++) {
				for (int j = 0; j < computedDimensions[1]; j++) {
					for (int k = 0; k < computedDimensions[2]; k++) {
						for (int l = 0; l < computedDimensions[3]; l++) {
							computedValues[i][j][k][l] = val;
						}
					}
				}
			}
			cachedValue = new NV4Real(computedValues);
		}

		return cachedValue;
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
