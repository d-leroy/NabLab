package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV1Int;

public abstract class NablaInt1ConstantNode extends NablaExpressionNode {

	@CompilationFinal
	protected NV1Int cachedValue;

	@Child
	private NablaExpressionNode value;
	@Child
	private NablaExpressionNode size;
	
	public NablaInt1ConstantNode(NablaExpressionNode value, NablaExpressionNode size) {
		this.value = value;
		this.size = size;
	}

	@ExplodeLoop
	@Specialization
	public NV1Int executeNV1Int(VirtualFrame frame) {
		if (cachedValue == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			final int val = NablaTypesGen.asNV0Int(value.executeGeneric(frame)).getData();
			final int size = NablaTypesGen.asNV0Int(this.size.executeGeneric(frame)).getData();
			final int[] computedValues = new int[size];
			for (int i = 0; i < size; i++) {
				computedValues[i] = val;
			}
			cachedValue = new NV1Int(computedValues);
		}
		return cachedValue;
	}
}
