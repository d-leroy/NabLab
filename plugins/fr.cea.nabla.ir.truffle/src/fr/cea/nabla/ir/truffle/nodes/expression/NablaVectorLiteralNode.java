package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public class NablaVectorLiteralNode extends NablaExpressionNode {

	@CompilationFinal
	private NablaValue value;

	private final NablaExpressionNode[] values;
	private final NablaExpressionNode[] dimensions;
	
	private final Assumption constantSizes = Truffle.getRuntime().createAssumption();

	public NablaVectorLiteralNode(NablaExpressionNode[] values, NablaExpressionNode[] dimensions) {
		this.values = values;
		this.dimensions = dimensions;
	}
	
	@Override
	@ExplodeLoop
	public NablaValue executeGeneric(VirtualFrame frame) {
		
		CompilerAsserts.compilationConstant(dimensions.length);
		
		int[] computedDimensions = new int[dimensions.length];
		
		for (int i = 0; i < dimensions.length; i++) {
			
			try {
				computedDimensions[i] = NablaTypesGen.expectNV0Int(dimensions[i].executeGeneric(frame)).getData();
			} catch (UnexpectedResultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CompilerDirectives.transferToInterpreterAndInvalidate();
		final int[] computedValues = new int[values.length];
		CompilerAsserts.compilationConstant(values.length);
		for (int i = 0; i < values.length; i++) {
			try {
				computedValues[i] = NablaTypesGen.expectNV0Int(values[i].executeGeneric(frame)).getData();
			} catch (UnexpectedResultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new NV1Int(computedValues);
	}
	
	@Override
//	@Specialization(guards = )
	public NV1Int executeNV1Int(VirtualFrame frame) throws UnexpectedResultException {
		// TODO Auto-generated method stub
		return super.executeNV1Int(frame);
	}
	
	public void setDimensions(int[] dimensions) {
		
	}

	@Override
	public boolean isInstrumentable() {
		return false;
	}
}
