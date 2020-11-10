package fr.cea.nabla.interpreter.utils;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;

public abstract class GetFrameNode extends Node {

	@CompilationFinal
	protected boolean isLocal = true;
	private final String slotName;

	public GetFrameNode(String name) {
		this.slotName = name;
	}

	public abstract Frame execute(VirtualFrame frame);

//	@Specialization
//	protected Frame doDefault(VirtualFrame frame) {
//		if (CompilerDirectives.inCompiledCode()) {
//			return frame;
//		} else {
//			return Truffle.getRuntime().iterateFrames(f -> {
//				final FrameDescriptor descriptor = f.getFrame(FrameAccess.READ_ONLY).getFrameDescriptor();
//				if (descriptor.findFrameSlot(slotName) == null) {
//					return null;
//				} else {
//					return f.getFrame(FrameAccess.READ_WRITE);
//				}
//			});
//		}
//	}
	
	@Specialization(rewriteOn = NablaInitializationPerformedException.class)
	protected Frame initialize(VirtualFrame frame) throws NablaInitializationPerformedException {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		final FrameDescriptor descriptor = frame.getFrameDescriptor();
		if (descriptor.findFrameSlot(slotName) == null) {
			isLocal = false;
		}
		throw new NablaInitializationPerformedException();
	}

	@Specialization(guards = { "isLocal" })
	protected Frame doLocal(VirtualFrame frame) {
		return frame;
	}

	@Specialization(guards = { "!isLocal" })
	protected Frame doCached(VirtualFrame frame, @Cached("getFrame(frame)") Frame result) {
		return result;
	}

	protected Frame getFrame(VirtualFrame frame) {
		return NablaLanguage.getCurrentContext().getGlobalFrame();
	}
}