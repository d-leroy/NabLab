package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.oracle.truffle.api.dsl.NodeChild;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

@NodeChild("leftNode")
@NodeChild("rightNode")
public abstract class NablaBinaryExpressionNode extends NablaExpressionNode {
	
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
