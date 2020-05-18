package fr.cea.nabla.interpreter.nodes.expression.binary;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;

@NodeInfo(shortName = "+")
public abstract class NablaAddNode extends NablaBinaryExpressionNode {

	@Specialization
	protected NV0Int add(NV0Int left, NV0Int right) {
		return new NV0Int(Math.addExact(left.getData(), right.getData()));
	}

	@Specialization
	protected NV0Real add(NV0Int left, NV0Real right) {
		return new NV0Real(left.getData() + right.getData());
	}

	@ExplodeLoop
	@Specialization
	protected NV1Int add(NV0Int left, NV1Int right) {
		final int leftData = left.getData();
		final int[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);

		final int[] result = new int[rightData.length];

		for (int i = 0; i < rightData.length; i++) {
			result[i] = Math.addExact(leftData, rightData[i]);
		}

		return new NV1Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV0Int left, NV1Real right) {
		final int leftData = left.getData();
		final double[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);

		final double[] result = new double[rightData.length];

		for (int i = 0; i < rightData.length; i++) {
			result[i] = leftData + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Int add(NV0Int left, NV2Int right) {
		final int leftData = left.getData();
		final int[][] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);
		CompilerAsserts.partialEvaluationConstant(rightData[0].length);

		final int[][] result = new int[rightData.length][rightData[0].length];

		for (int i = 0; i < rightData.length; i++) {
			for (int j = 0; j < rightData[0].length; j++) {
				result[i][j] = Math.addExact(leftData, rightData[i][j]);
			}
		}

		return new NV2Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV0Int left, NV2Real right) {
		final int leftData = left.getData();
		final double[][] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);
		CompilerAsserts.partialEvaluationConstant(rightData[0].length);

		final double[][] result = new double[rightData.length][rightData[0].length];

		for (int i = 0; i < rightData.length; i++) {
			for (int j = 0; j < rightData[0].length; j++) {
				result[i][j] = leftData + rightData[i][j];
			}
		}

		return new NV2Real(result);
	}

	@Specialization
	protected NV0Real add(NV0Real left, NV0Int right) {
		return new NV0Real(left.getData() + right.getData());
	}

	@Specialization
	protected NV0Real add(NV0Real left, NV0Real right) {
		return new NV0Real(left.getData() + right.getData());
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV0Real left, NV1Int right) {
		final double leftData = left.getData();
		final int[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);

		final double[] result = new double[rightData.length];

		for (int i = 0; i < rightData.length; i++) {
			result[i] = leftData + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV0Real left, NV1Real right) {
		final double leftData = left.getData();
		final double[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);

		final double[] result = new double[rightData.length];

		for (int i = 0; i < rightData.length; i++) {
			result[i] = leftData + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV0Real left, NV2Int right) {
		final double leftData = left.getData();
		final int[][] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);
		CompilerAsserts.partialEvaluationConstant(rightData[0].length);

		final double[][] result = new double[rightData.length][rightData[0].length];

		for (int i = 0; i < rightData.length; i++) {
			for (int j = 0; j < rightData[0].length; j++) {
				result[i][j] = leftData + rightData[i][j];
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV0Real left, NV2Real right) {
		final double leftData = left.getData();
		final double[][] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(rightData.length);
		CompilerAsserts.partialEvaluationConstant(rightData[0].length);

		final double[][] result = new double[rightData.length][rightData[0].length];

		for (int i = 0; i < rightData.length; i++) {
			for (int j = 0; j < rightData[0].length; j++) {
				result[i][j] = leftData + rightData[i][j];
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Int add(NV1Int left, NV0Int right) {
		final int[] leftData = left.getData();
		final int rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final int[] result = new int[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = Math.addExact(leftData[i], rightData);
		}

		return new NV1Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1Int left, NV0Real right) {
		final int[] leftData = left.getData();
		final double rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData;
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Int add(NV1Int left, NV1Int right) {
		final int[] leftData = left.getData();
		final int[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final int[] result = new int[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = Math.addExact(leftData[i], rightData[i]);
		}

		return new NV1Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1Int left, NV1Real right) {
		final int[] leftData = left.getData();
		final double[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1Real left, NV0Int right) {
		final double[] leftData = left.getData();
		final int rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData;
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1Real left, NV0Real right) {
		final double[] leftData = left.getData();
		final double rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData;
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1Real left, NV1Int right) {
		final double[] leftData = left.getData();
		final int[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization(guards = "left.getData().length == cachedCount")
	protected NV1Real add(NV1Real left, NV1Real right, @Cached("left.getData().length") int cachedCount) {
		final double[] leftData = left.getData();
		final double[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(cachedCount);

		final double[] result = new double[cachedCount];

		for (int i = 0; i < cachedCount; i++) {
			result[i] = leftData[i] + rightData[i];
		}

		return new NV1Real(result);
	}

	@Specialization
	protected NV1Real add(NV1Real left, NV1Real right) {
		final double[] leftData = left.getData();
		final double[] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = leftData[i] + rightData[i];
		}

		return new NV1Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Int add(NV2Int left, NV0Int right) {
		final int[][] leftData = left.getData();
		final int rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);

		final int[][] result = new int[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = Math.addExact(leftData[i][j], rightData);
			}
		}

		return new NV2Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV2Int left, NV0Real right) {
		final int[][] leftData = left.getData();
		final double rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);

		final double[][] result = new double[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] + rightData;
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Int add(NV2Int left, NV2Int right) {
		final int[][] leftData = left.getData();
		final int[][] rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);

		final int[][] result = new int[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = Math.addExact(leftData[i][j], rightData[i][j]);
			}
		}

		return new NV2Int(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV2Real left, NV0Int right) {
		final double[][] leftData = left.getData();
		final int rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);

		final double[][] result = new double[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] + rightData;
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV2Real left, NV0Real right) {
		final double[][] leftData = left.getData();
		final double rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);

		final double[][] result = new double[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] + rightData;
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization(guards = { "left.getData().length == cachedCountI", "getInnerArrayLength(left.getData()) == cachedCountJ" })
	protected NV2Real add(NV2Real left, NV2Real right, @Cached("left.getData().length") int cachedCountI, @Cached("getInnerArrayLength(left.getData())") int cachedCountJ) {
		final double[][] leftData = left.getData();
		final double[][] rightData = right.getData();

		final double[][] result = new double[cachedCountI][cachedCountJ];

		for (int i = 0; i < cachedCountI; i++) {
			for (int j = 0; j < cachedCountJ; j++) {
				result[i][j] = leftData[i][j] + rightData[i][j];
			}
		}

		return new NV2Real(result);
	}

	@Specialization
	protected NV2Real add(NV2Real left, NV2Real right) {
		final double[][] leftData = left.getData();
		final double[][] rightData = right.getData();

		final double[][] result = new double[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] + rightData[i][j];
			}
		}

		return new NV2Real(result);
	}
	
	protected <T> int getInnerArrayLength(boolean[][] array) {
		return array[0].length;
	}
	
	protected <T> int getInnerArrayLength(int[][] array) {
		return array[0].length;
	}
	
	protected <T> int getInnerArrayLength(double[][] array) {
		return array[0].length;
	}
}
