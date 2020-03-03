package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV1Int;

public abstract class NablaInt1ConstantNode extends NablaExpressionNode {

	@CompilationFinal
	private NV1Int value;

	private final NablaExpressionNode[] values;
	
	public NablaInt1ConstantNode(NablaExpressionNode[] values) {
		this.values = values;
	}

	@ExplodeLoop
	@Specialization
	public NV1Int executeNV1Int(VirtualFrame frame) {
		if (value == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			final int[] computedValues = new int[values.length];
			
			CompilerAsserts.compilationConstant(values.length);
			
			for (int i = 0; i < values.length; i++) {
				computedValues[i] = NablaTypesGen.asNV0Int(values[i].executeGeneric(frame)).getData();
			}
			value = new NV1Int(computedValues);
		}
		
		return value;
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
