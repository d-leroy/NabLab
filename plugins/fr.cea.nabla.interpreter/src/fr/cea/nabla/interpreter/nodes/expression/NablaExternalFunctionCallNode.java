package fr.cea.nabla.interpreter.nodes.expression;

import java.util.function.Function;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.interpreter.values.FunctionCallHelper;
import fr.cea.nabla.interpreter.values.NablaValue;

public class NablaExternalFunctionCallNode extends NablaExpressionNode {

	@Child
	private NablaExpressionNode argumentNode;
	private final Function<Object, Object> externalFunction;

	public NablaExternalFunctionCallNode(Function<Object, Object> externalFunction, NablaExpressionNode argumentNode) {
		this.argumentNode = argumentNode;
		this.externalFunction = externalFunction;
	}
	
	@Override
	public NablaValue executeGeneric(VirtualFrame frame) {
		Object argumentValue = FunctionCallHelper.getJavaValue((NablaValue) argumentNode.executeGeneric(frame));
		return FunctionCallHelper.createNablaValue(externalFunction.apply(argumentValue));
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		if (tag == StandardTags.CallTag.class) {
			return true;
		}
		return super.hasTag(tag);
	}
}
