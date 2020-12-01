package fr.cea.nabla.interpreter.nodes.expression.binary;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1IntJava;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV1RealLibrary;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;

public abstract class NablaBinaryArithmeticNode extends NablaBinaryExpressionNode {

	private final ArithmeticOperator op;

	public NablaBinaryArithmeticNode(ArithmeticOperator operator) {
		this.op = operator;
	}

	private int doBinaryInt(int a, int b) {
		switch (op) {
		case ADD:
			return Math.addExact(a, b);
		case SUB:
			return a - b;
		case DIV:
			return a / b;
		case MUL:
			return a * b;
		default:
			CompilerDirectives.shouldNotReachHere();
			return 0;
		}
	}

	double doBinaryDouble(double a, double b) {
		switch (op) {
		case ADD:
			return a + b;
		case SUB:
			return a - b;
		case DIV:
			return a / b;
		case MUL:
			return a * b;
		default:
			CompilerDirectives.shouldNotReachHere();
			return 0;
		}
	}

	@Specialization
	protected NV0Int add(NV0Int left, NV0Int right) {
		return new NV0Int(doBinaryInt(left.getData(), right.getData()));
	}

	@Specialization
	protected NV0Real add(NV0Int left, NV0Real right) {
		return new NV0Real(doBinaryDouble(left.getData(), right.getData()));
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(right)", limit = "3")
	protected NV1Int add(NV0Int left, Object right, @CachedLibrary("right") NV1IntLibrary arrays) {
		final int leftData = left.getData();
		final int length = arrays.length(right);

		final int[] result = new int[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryInt(leftData, arrays.read(right, i));
		}

		return new NV1IntJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(right)", limit = "3")
	protected NV1Real add(NV0Int left, Object right, @CachedLibrary("right") NV1RealLibrary arrays) {
		final int leftData = left.getData();
		final int length = arrays.length(right);

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(leftData, arrays.read(right, i));
		}

		return new NV1RealJava(result);
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
				result[i][j] = doBinaryInt(leftData, rightData[i][j]);
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
				result[i][j] = doBinaryDouble(leftData, rightData[i][j]);
			}
		}

		return new NV2Real(result);
	}

	@Specialization
	protected NV0Real add(NV0Real left, NV0Int right) {
		return new NV0Real(doBinaryDouble(left.getData(), right.getData()));
	}

	@Specialization
	protected NV0Real add(NV0Real left, NV0Real right) {
		return new NV0Real(doBinaryDouble(left.getData(), right.getData()));
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(right)", limit = "3")
	protected NV1Real add(NV0Real left, Object right, @CachedLibrary("right") NV1IntLibrary arrays) {
		final double leftData = left.getData();
		final int length = arrays.length(right);

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(leftData, arrays.read(right, i));
		}

		return new NV1RealJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(right)", limit = "3")
	protected NV1Real add(NV0Real left, Object right, @CachedLibrary("right") NV1RealLibrary arrays) {
		final double leftData = left.getData();
		final int length = arrays.length(right);

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(leftData, arrays.read(right, i));
		}

		return new NV1RealJava(result);
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
				result[i][j] = doBinaryDouble(leftData, rightData[i][j]);
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
				result[i][j] = doBinaryDouble(leftData, rightData[i][j]);
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(left)", limit = "3")
	protected NV1Int add(Object left, NV0Int right, @CachedLibrary("left") NV1IntLibrary arrays) {
		final int length = arrays.length(left);
		final int rightData = right.getData();

		final int[] result = new int[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryInt(arrays.read(left, i), rightData);
		}

		return new NV1IntJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(left)", limit = "3")
	protected NV1Real add(Object left, NV0Real right, @CachedLibrary("left") NV1IntLibrary arrays) {
		final int length = arrays.length(left);
		final double rightData = right.getData();

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(arrays.read(left, i), rightData);
		}

		return new NV1RealJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = { "arraysLeft.isArray(left)", "arraysRight.isArray(right)" }, limit = "3")
	protected NV1Int add(Object left, Object right, //
			@CachedLibrary("left") NV1IntLibrary arraysLeft, //
			@CachedLibrary("right") NV1IntLibrary arraysRight) {
		final int length = arraysLeft.length(left);

		final int[] result = new int[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryInt(arraysLeft.read(left, i), arraysRight.read(right, i));
		}

		return new NV1IntJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = { "arraysLeft.isArray(left)", "arraysRight.isArray(right)" }, limit = "3")
	protected NV1Real add(Object left, Object right, //
			@CachedLibrary("left") NV1IntLibrary arraysLeft, //
			@CachedLibrary("right") NV1RealLibrary arraysRight) {
		final int length = arraysLeft.length(left);

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(arraysLeft.read(left, i), arraysRight.read(right, i));
		}

		return new NV1RealJava(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1RealJava left, NV0Int right) {
		final double[] leftData = left.getData();
		final int rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = doBinaryDouble(leftData[i], rightData);
		}

		return new NV1RealJava(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV1Real add(NV1RealJava left, NV0Real right) {
		final double[] leftData = left.getData();
		final double rightData = right.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = doBinaryDouble(leftData[i], rightData);
		}

		return new NV1RealJava(result);
	}

	@ExplodeLoop
	@Specialization(guards = "arrays.isArray(right)", limit = "3")
	protected NV1Real add(NV1RealJava left, Object right, //
			@CachedLibrary("right") NV1IntLibrary arrays) {
		final double[] leftData = left.getData();

		CompilerAsserts.partialEvaluationConstant(leftData.length);

		final double[] result = new double[leftData.length];

		for (int i = 0; i < leftData.length; i++) {
			result[i] = doBinaryDouble(leftData[i], arrays.read(right, i));
		}

		return new NV1RealJava(result);
	}

	//	@ExplodeLoop
	@Specialization(guards = { "arraysLeft.isArray(left)", "arraysRight.isArray(right)",
			"length == arraysLeft.length(left)", "length == arraysRight.length(right)" }, limit = "3")
	protected NV1Real doNV1NV1Cached(Object left, Object right, //
			@CachedLibrary("left") NV1RealLibrary arraysLeft, //
			@CachedLibrary("right") NV1RealLibrary arraysRight, //
			@Cached("arraysLeft.length(left)") int length) {

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(arraysLeft.read(left, i), arraysRight.read(right, i));
		}

		return new NV1RealJava(result);
	}

//	@ExplodeLoop
	@Specialization(guards = { "arraysLeft.isArray(left)", "arraysRight.isArray(right)",
			"arraysLeft.length(left) == arraysRight.length(right)" }, limit = "3", replaces = "doNV1NV1Cached")
	protected NV1Real add(Object left, Object right, //
			@CachedLibrary("left") NV1RealLibrary arraysLeft, //
			@CachedLibrary("right") NV1RealLibrary arraysRight) {
		final int length = arraysLeft.length(left);

		final double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = doBinaryDouble(arraysLeft.read(left, i), arraysRight.read(right, i));
		}

		return new NV1RealJava(result);
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
				result[i][j] = doBinaryInt(leftData[i][j], rightData);
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
				result[i][j] = doBinaryDouble(leftData[i][j], rightData);
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
				result[i][j] = doBinaryInt(leftData[i][j], rightData[i][j]);
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
				result[i][j] = doBinaryDouble(leftData[i][j], rightData);
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
				result[i][j] = doBinaryDouble(leftData[i][j], rightData);
			}
		}

		return new NV2Real(result);
	}

	@ExplodeLoop
	@Specialization
	protected NV2Real add(NV2Real left, NV2Real right) {
		final double[][] leftData = left.getData();
		final double[][] rightData = right.getData();

		final double[][] result = new double[leftData.length][leftData[0].length];

		for (int i = 0; i < leftData.length; i++) {
			for (int j = 0; j < leftData[0].length; j++) {
				result[i][j] = doBinaryDouble(leftData[i][j], rightData[i][j]);
			}
		}

		return new NV2Real(result);
	}
}
