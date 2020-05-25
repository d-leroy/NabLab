package fr.cea.nabla.interpreter.nodes.expression.unary;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;

@NodeChild("valueNode")
@NodeInfo(shortName = "sqrt")
public abstract class NablaSqrtNode extends NablaExpressionNode {

	@Specialization
	protected NV0Int abs(NV0Int value) {
		return new NV0Int((int) Math.sqrt(value.getData()));
	}

	@Specialization
	protected NV0Real abs(NV0Real value) {
		return new NV0Real(Math.sqrt(value.getData()));
	}

	@Specialization
	protected NV1Int abs(NV1Int value) {
		final int[] valueData = value.getData();

		CompilerAsserts.compilationConstant(valueData.length);

		final int[] result = new int[valueData.length];

		for (int i = 0; i < valueData.length; i++) {
			result[i] = (int) Math.sqrt(valueData[i]);
		}

		return new NV1Int(result);
	}

	@Specialization
	protected NV1Real abs(NV1Real value) {
		final double[] valueData = value.getData();

		CompilerAsserts.compilationConstant(valueData.length);

		final double[] result = new double[valueData.length];

		for (int i = 0; i < valueData.length; i++) {
			result[i] = Math.sqrt(valueData[i]);
		}

		return new NV1Real(result);
	}

	@Specialization
	protected NV2Int abs(NV2Int value) {
		final int[][] valueData = value.getData();

		CompilerAsserts.compilationConstant(valueData.length);
		CompilerAsserts.compilationConstant(valueData[0].length);

		final int[][] result = new int[valueData.length][valueData[0].length];

		for (int i = 0; i < valueData.length; i++) {
			for (int j = 0; j < valueData[0].length; j++) {
				result[i][j] = (int) Math.sqrt(valueData[i][j]);
			}
		}

		return new NV2Int(result);
	}

	@Specialization
	protected NV2Real abs(NV2Real value) {
		final double[][] valueData = value.getData();

		CompilerAsserts.compilationConstant(valueData.length);
		CompilerAsserts.compilationConstant(valueData[0].length);

		final double[][] result = new double[valueData.length][valueData[0].length];

		for (int i = 0; i < valueData.length; i++) {
			for (int j = 0; j < valueData[0].length; j++) {
				result[i][j] = Math.sqrt(valueData[i][j]);
			}
		}

		return new NV2Real(result);
	}
}
