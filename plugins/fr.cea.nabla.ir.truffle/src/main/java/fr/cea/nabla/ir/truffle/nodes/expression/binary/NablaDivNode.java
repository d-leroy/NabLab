package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

@NodeInfo(shortName = "/")
public abstract class NablaDivNode extends NablaBinaryExpressionNode {
	
	
	@Specialization
	protected NV0Int div(NV0Int left, NV0Int right) {
		return new NV0Int(left.getData() / right.getData());
	}
	
	@Specialization
	protected NV0Real div(NV0Int left, NV0Real right) {
		return new NV0Real(left.getData() / right.getData());
	}
	
	
	@Specialization
	protected NV0Real div(NV0Real left, NV0Int right) {
		return new NV0Real(left.getData() / right.getData());
	}
	
	@Specialization
	protected NV0Real div(NV0Real left, NV0Real right) {
		return new NV0Real(left.getData() / right.getData());
	}
	
	
	@ExplodeLoop
	@Specialization
	protected NV1Int div(NV1Int left, NV0Int right) {
		final int[] leftData = left.getData();
		final int rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final int[] result = new int[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData;
		}
		
		return new NV1Int(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Int left, NV0Real right) {
		final int[] leftData = left.getData();
		final double rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData;
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Int div(NV1Int left, NV1Int right) {
		final int[] leftData = left.getData();
		final int[] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final int[] result = new int[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData[i];
		}
		
		return new NV1Int(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Int left, NV1Real right) {
		final int[] leftData = left.getData();
		final double[] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData[i];
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Real left, NV0Int right) {
		final double[] leftData = left.getData();
		final int rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData;
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Real left, NV0Real right) {
		final double[] leftData = left.getData();
		final double rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData;
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Real left, NV1Int right) {
		final double[] leftData = left.getData();
		final int[] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData[i];
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV1Real div(NV1Real left, NV1Real right) {
		final double[] leftData = left.getData();
		final double[] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		
		final double[] result = new double[leftData.length];
		
		for (int i=0; i < leftData.length; i++) {
			result[i] = leftData[i] / rightData[i];
		}
		
		return new NV1Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Int div(NV2Int left, NV0Int right) {
		final int[][] leftData = left.getData();
		final int rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final int[][] result = new int[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData;
			}
		}
		
		return new NV2Int(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Real div(NV2Int left, NV0Real right) {
		final int[][] leftData = left.getData();
		final double rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final double[][] result = new double[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData;
			}
		}
		
		return new NV2Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Int div(NV2Int left, NV2Int right) {
		final int[][] leftData = left.getData();
		final int[][] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final int[][] result = new int[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData[i][j];
			}
		}
		
		return new NV2Int(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Real div(NV2Real left, NV0Int right) {
		final double[][] leftData = left.getData();
		final int rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final double[][] result = new double[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData;
			}
		}
		
		return new NV2Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Real div(NV2Real left, NV0Real right) {
		final double[][] leftData = left.getData();
		final double rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final double[][] result = new double[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData;
			}
		}
		
		return new NV2Real(result);
	}
	
	@ExplodeLoop
	@Specialization
	protected NV2Real div(NV2Real left, NV2Real right) {
		final double[][] leftData = left.getData();
		final double[][] rightData = right.getData();
		
		CompilerAsserts.partialEvaluationConstant(leftData.length);
		CompilerAsserts.partialEvaluationConstant(leftData[0].length);
		
		final double[][] result = new double[leftData.length][leftData[0].length];
		
		for (int i=0; i < leftData.length; i++) {
			for (int j=0; j < leftData[0].length; j++) {
				result[i][j] = leftData[i][j] / rightData[i][j];
			}
		}
		
		return new NV2Real(result);
	}
}
