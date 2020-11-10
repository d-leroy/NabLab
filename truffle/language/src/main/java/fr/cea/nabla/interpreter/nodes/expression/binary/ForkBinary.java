package fr.cea.nabla.interpreter.nodes.expression.binary;

import java.util.concurrent.RecursiveAction;

import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.values.NV1RealLibrary;

public class ForkBinary extends RecursiveAction {

	private static final long serialVersionUID = -1341756839654730830L;
	private static final int SPLIT_THRESHOLD = 100;
	
	private final int begin;
	private final int length;

	private final Object left;
	private final Object right;
	private final NV1RealLibrary arraysLeft;
	private final NV1RealLibrary arraysRight;
	
	private final double[] result;
	private final NablaAddNode nablaAddNode;

	public ForkBinary(NablaAddNode nablaAddNode, Object left, Object right, NV1RealLibrary arraysLeft, NV1RealLibrary arraysRight, int begin,
			int length, double[] result) {
		this.nablaAddNode = nablaAddNode;
		this.begin = begin;
		this.length = length;
		this.left = left;
		this.right = right;
		this.arraysLeft = arraysLeft;
		this.arraysRight = arraysRight;
		this.result = result;
	}

	@Override
	protected void compute() {
		if (length < SPLIT_THRESHOLD) {
			computeDirectly();
			return;
		}

		int split = length / 2;

		invokeAll(new ForkBinary(nablaAddNode, left, right, arraysLeft, arraysRight, begin, split, result),
				new ForkBinary(nablaAddNode, left, right, arraysLeft, arraysRight, begin + split, length - split, result));
	}

	@ExplodeLoop
	private void computeDirectly() {
		for (int i = begin; i < begin + length; i++) {
			result[i] = nablaAddNode.doBinaryDouble(arraysLeft.read(left, i), arraysRight.read(right, i));
		}
	}
}