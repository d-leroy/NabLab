package fr.cea.nabla.interpreter.nodes.expression;

import java.util.function.Supplier;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.interpreter.values.FunctionCallHelper;
import fr.cea.nabla.interpreter.values.NablaValue;

public class NablaExternalSupplierCallNode extends NablaExpressionNode {

	private final Supplier<Object> externalSupplier;
		
	public NablaExternalSupplierCallNode(Supplier<Object> externalSupplier) {
		this.externalSupplier = externalSupplier;
	}

	
	@Override
	public NablaValue executeGeneric(VirtualFrame frame) {
		return FunctionCallHelper.createNablaValue(externalSupplier.get());
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		if (tag == StandardTags.CallTag.class) {
			return true;
		}
		return super.hasTag(tag);
	}
}
