package fr.cea.nabla.ir.truffle.nodes.expression.unary;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

@NodeChild("valueNode")
@NodeInfo(shortName = "-")
public abstract class NablaMinusNode extends NablaExpressionNode {
	
	
	@Specialization
	protected NV0Int mul(NV0Int right) {
		return new NV0Int(Math.multiplyExact(-1, right.getData()));
	}
	
	@Specialization
	protected NV0Real mul(NV0Real right) {
		return new NV0Real(-1 * right.getData());
	}
	
	
	@Specialization
	protected NV1Int mul(NV1Int right) {
		final int leftData = -1;
		final int[] rightData = right.getData();
		
		CompilerAsserts.compilationConstant(rightData.length);
		
		final int[] result = new int[rightData.length];
		
		for (int i=0; i < rightData.length; i++) {
			result[i] = Math.multiplyExact(leftData, rightData[i]);
		}
		
		return new NV1Int(result);
	}
	
	
	@Specialization
	protected NV1Real mul(NV1Real right) {
		final int leftData = -1;
		final double[] rightData = right.getData();
		
		CompilerAsserts.compilationConstant(rightData.length);
		
		final double[] result = new double[rightData.length];
		
		for (int i=0; i < rightData.length; i++) {
			result[i] = leftData * rightData[i];
		}
		
		return new NV1Real(result);
	}
	
	
	@Specialization
	protected NV2Int mul(NV2Int right) {
		final int leftData = -1;
		final int[][] rightData = right.getData();
		
		CompilerAsserts.compilationConstant(rightData.length);
		CompilerAsserts.compilationConstant(rightData[0].length);
		
		final int[][] result = new int[rightData.length][rightData[0].length];
		
		for (int i=0; i < rightData.length; i++) {
			for (int j=0; j < rightData[0].length; j++) {
				result[i][j] = Math.multiplyExact(leftData, rightData[i][j]);
			}
		}
		
		return new NV2Int(result);
	}
	
	
	@Specialization
	protected NV2Real mul(NV2Real right) {
		final int leftData = -1;
		final double[][] rightData = right.getData();
		
		CompilerAsserts.compilationConstant(rightData.length);
		CompilerAsserts.compilationConstant(rightData[0].length);
		
		final double[][] result = new double[rightData.length][rightData[0].length];
		
		for (int i=0; i < rightData.length; i++) {
			for (int j=0; j < rightData[0].length; j++) {
				result[i][j] = leftData * rightData[i][j];
			}
		}
		
		return new NV2Real(result);
	}
}
