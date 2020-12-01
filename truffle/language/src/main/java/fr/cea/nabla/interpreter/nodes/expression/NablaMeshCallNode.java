package fr.cea.nabla.interpreter.nodes.expression;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleContext;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.NablaContext;

public abstract class NablaMeshCallNode extends NablaExpressionNode {

	@Children
	private final NablaExpressionNode[] args;
	protected final String providerName;
	private final String memberName;

	public NablaMeshCallNode(String providerName, String memberName, NablaExpressionNode[] args) {
		this.providerName = providerName;
		this.memberName = memberName;
		this.args = args;
	}

//	@Specialization
//	public Object doInnerContext(VirtualFrame frame) {
//		Env env = null;
//		TruffleContext innerContext = env.newContextBuilder().build();
//		Object p = innerContext.enter(this);
//		try {
//			Context.getCurrent().getBindings("llvm");
//		} finally {
//			innerContext.leave(this, p);
//		}
//		return null;
//	}

	@ExplodeLoop
	@Specialization(assumptions = "contextActive")
	public Object doCached(VirtualFrame frame, @Cached("getMember()") Value member,
			@CachedContext(NablaLanguage.class) NablaContext context,
			@Cached("context.getNativeLibrary(providerName)") Value meshWrapper,
			@Cached("context.getContextActive()") Assumption contextActive) {
		final Object[] argumentValues = new Object[args.length + 1];
		argumentValues[0] = meshWrapper;
		for (int i = 0; i < args.length; i++) {
			argumentValues[i + 1] = args[i].executeGeneric(frame);
		}
		return member.execute(argumentValues);
	}

//	@ExplodeLoop
//	@Specialization
//	public Object doCached(VirtualFrame frame,
//			@CachedContext(NablaLanguage.class) ContextReference<NablaContext> context) {
//		final Object[] argumentValues = new Object[args.length + 1];
//		argumentValues[0] = context.get().getMeshWrapper();
//		for (int i = 0; i < args.length; i++) {
//			argumentValues[i + 1] = args[i].executeGeneric(frame);
//		}
//		return getMember().execute(argumentValues);
//	}

	protected Value getMember() {
		final Value llvmBindings = Context.getCurrent().getBindings("llvm");
		return llvmBindings.getMember(memberName);
	}
}
