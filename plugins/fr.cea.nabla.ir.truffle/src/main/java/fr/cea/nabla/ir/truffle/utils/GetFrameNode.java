package fr.cea.nabla.ir.truffle.utils;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.runtime.NablaInitializationPerformedException;

public abstract class GetFrameNode extends Node {

	@CompilationFinal
	boolean initializationRequired = true;
	@CompilationFinal
	private int depth = 0;
	@CompilationFinal
	private MaterializedFrame globalFrame;
	private final FrameSlot slot;

	public GetFrameNode(FrameSlot slot) {
		this.slot = slot;
	}

	public abstract Frame execute(VirtualFrame frame);

	@Specialization(guards = "initializationRequired", rewriteOn = NablaInitializationPerformedException.class)
	protected Frame initialize(VirtualFrame frame) throws NablaInitializationPerformedException {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		Frame closestFrame = Truffle.getRuntime().iterateFrames(f -> {
			final Frame result = f.getFrame(FrameAccess.READ_ONLY);
			final FrameDescriptor descriptor = result.getFrameDescriptor();
			if (descriptor.getSlots().contains(slot)) {
				return result;
			}
			depth++;
			return null;
		});
		closestFrame.getFrameDescriptor().setFrameSlotKind(slot, FrameSlotKind.Object);
		initializationRequired = false;
		throw new NablaInitializationPerformedException();
	}

	@Specialization(guards = "isFast()")
	protected Frame doLocal(VirtualFrame frame) {
		return frame;
	}

	@Specialization(guards = "isSlow()")
	protected Frame doGlobal(VirtualFrame frame) {
		if (globalFrame == null) {
			globalFrame = (MaterializedFrame) frame.getArguments()[0];
		}
		return globalFrame;
	}

	protected boolean isFast() {
		return !initializationRequired && depth == 0;
	}

	protected boolean isSlow() {
		return !initializationRequired && depth > 0;
	}

}