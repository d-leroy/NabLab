package fr.cea.nabla.interpreter.nodes.expression;

import java.util.function.BiFunction;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.interpreter.values.FunctionCallHelper;
import fr.cea.nabla.interpreter.values.NablaValue;

public class NablaExternalBiFunctionCallNode extends NablaExpressionNode {

	@Children
	private NablaExpressionNode[] argumentNodes;
	private final BiFunction<Object, Object, Object> externalFunction;
	
	public NablaExternalBiFunctionCallNode(BiFunction<Object, Object, Object> externalFunction, NablaExpressionNode[] argumentNodes) {
		this.argumentNodes = argumentNodes;
		this.externalFunction = externalFunction;
	}

	@Override
	
	public NablaValue executeGeneric(VirtualFrame frame) {
		CompilerAsserts.compilationConstant(argumentNodes.length);

		Object[] argumentValues = new Object[argumentNodes.length];
		for (int i = 0; i < argumentNodes.length; i++) {
			argumentValues[i] = FunctionCallHelper.getJavaValue((NablaValue) argumentNodes[i].executeGeneric(frame));
		}
		
		return FunctionCallHelper.createNablaValue(externalFunction.apply(argumentValues[0], 
				argumentValues[1]));
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		if (tag == StandardTags.CallTag.class) {
			return true;
		}
		return super.hasTag(tag);
	}
}
