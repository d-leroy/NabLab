package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;
import com.oracle.truffle.api.interop.InteropException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalMethodCallNodeFactory.InvokeMethodNodeGen;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.BoxValueNode;
import fr.cea.nabla.ir.truffle.values.BoxValueNodeGen;
import fr.cea.nabla.ir.truffle.values.CreateNablaValueNode;
import fr.cea.nabla.ir.truffle.values.CreateNablaValueNodeGen;
import fr.cea.nabla.ir.truffle.values.NablaValue;
import fr.cea.nabla.ir.truffle.values.UnboxValueNode;
import fr.cea.nabla.ir.truffle.values.UnboxValueNodeGen;

public class NablaExternalMethodCallNode extends NablaExpressionNode {

	@Children
	private UnboxValueNode[] unboxArgNodes;
	@Child
	private InvokeMethod invokeNode;
	@Child
	private BoxValueNode unboxNode;
	@Child
	private CreateNablaValueNode createNablaValueNode;
	private final String methodName;
	private final Object receiverObject;

	public NablaExternalMethodCallNode(String receiverClass, String methodName, Class<?> returnType,
			NablaExpressionNode[] argumentNodes) {
		this.unboxArgNodes = new UnboxValueNode[argumentNodes.length];
		for (int i = 0; i < unboxArgNodes.length; i++) {
			this.unboxArgNodes[i] = UnboxValueNodeGen.create(argumentNodes[i]);
		}
		this.methodName = methodName;
		this.receiverObject = NablaContext.getCurrent().getEnv().lookupHostSymbol(receiverClass);
		this.invokeNode = InvokeMethodNodeGen.create();
		this.unboxNode = BoxValueNodeGen.create(returnType);
		this.createNablaValueNode = CreateNablaValueNodeGen.create();
	}

	@Override
	@ExplodeLoop
	public Object executeGeneric(VirtualFrame frame) {
		final Object[] argumentValues = new Object[unboxArgNodes.length];
		for (int i = 0; i < unboxArgNodes.length; i++) {
			argumentValues[i] = unboxArgNodes[i].execute(frame);
		}

		final Object invokeResult = invokeNode.execute(receiverObject, methodName, argumentValues);
		final NablaValue nablaResult = createNablaValueNode.execute(unboxNode.execute(invokeResult));
		return nablaResult;
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		if (tag == StandardTags.CallTag.class) {
			return true;
		}
		return super.hasTag(tag);
	}

	abstract static class InvokeMethod extends Node {
		abstract Object execute(Object obj, String methodName, Object[] arguments);

		@Specialization(guards = "objLibrary.hasMembers(obj)", limit = "4")
		Object doDefault(Object obj, String methodName, Object[] arguments,
				@CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				return objLibrary.invokeMember(obj, methodName, arguments);
			} catch (InteropException e) {
				// TODO translate errors to your language errors
			}
			return null;
		}
	}
}
