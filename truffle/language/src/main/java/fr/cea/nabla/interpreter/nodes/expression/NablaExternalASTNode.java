package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public abstract class NablaExternalASTNode extends NablaExpressionNode {

	protected final Object member;
	private final Object receiver;
	@Children
	private final NablaExpressionNode[] args;
	
	public NablaExternalASTNode(Object member, Object receiver, NablaExpressionNode[] args) {
		this.member = member;
		this.receiver = receiver;
		this.args = args;
	}

	@ExplodeLoop
	@Specialization
	public Object execute(VirtualFrame frame, @CachedLibrary("member") InteropLibrary function) {
		final Object[] argumentValues = new Object[args.length + 1];
		argumentValues[0] = receiver;
		for (int i = 0; i < args.length; i++) {
			argumentValues[i + 1] = args[i].executeGeneric(frame);
		}
		try {
			return function.execute(member, argumentValues);
		} catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
			e.printStackTrace();
		}
		return CompilerDirectives.shouldNotReachHere();
	}
}
