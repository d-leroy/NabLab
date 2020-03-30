package fr.cea.nabla.ir.truffle.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV1Bool;

public abstract class NablaBool1ConstantNode extends NablaExpressionNode {

	@CompilationFinal
	protected NV1Bool cachedValue;

	@Child
	private NablaExpressionNode value;
	@Child
	private NablaExpressionNode size;
	
	public NablaBool1ConstantNode(NablaExpressionNode value, NablaExpressionNode size) {
		this.value = value;
		this.size = size;
	}

	@ExplodeLoop
	@Specialization
	public NV1Bool executeNV1Bool(VirtualFrame frame) {
		if (cachedValue == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			final boolean val = NablaTypesGen.asNV0Bool(value.executeGeneric(frame)).isData();
			final int size = NablaTypesGen.asNV0Int(this.size.executeGeneric(frame)).getData();
			final boolean[] computedValues = new boolean[size];
			for (int i = 0; i < size; i++) {
				computedValues[i] = val;
			}
			cachedValue = new NV1Bool(computedValues);
		}
		return cachedValue;
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
