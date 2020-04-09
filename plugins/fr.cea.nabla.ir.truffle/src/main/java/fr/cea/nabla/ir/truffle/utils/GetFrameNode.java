package fr.cea.nabla.ir.truffle.utils;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.nodes.NablaRootNode;
import fr.cea.nabla.ir.truffle.runtime.NablaInitializationPerformedException;

public abstract class GetFrameNode extends Node {

	@CompilationFinal
	protected boolean initializationRequired = true;
	@CompilationFinal
	protected int depth = 0;
	@CompilationFinal
	private MaterializedFrame globalFrame;
	// FIXME: rely on FrameDescriptor instead of FrameSlot
	private final FrameSlot slot;

	public GetFrameNode(FrameSlot slot) {
		this.slot = slot;
	}

	public abstract Frame execute(VirtualFrame frame);

	@Specialization(guards = "initializationRequired", //
			rewriteOn = NablaInitializationPerformedException.class)
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

	@Specialization(guards = { "!initializationRequired", "depth == 0" })
	protected Frame doLocal(VirtualFrame frame) {
		return frame;
	}

	@Specialization(guards = { "!initializationRequired", "depth > 0" }, //
			assumptions = "resultStable")
	protected Frame doCached(VirtualFrame frame, @Cached("getFrame(frame)") Frame result,
			@Cached("getResultFrameStable(frame)") Assumption resultStable) {
		return result;
	}

	@ExplodeLoop
	protected Frame getFrame(VirtualFrame frame) {
		Frame result = (Frame) frame.getArguments()[0];
		for (int i = 1; i < depth; i++) {
			result = (Frame) result.getArguments()[0];
		}
		return result;
	}

	@ExplodeLoop
	protected Assumption getResultFrameStable(VirtualFrame frame) {
		Frame result = frame;
		for (int i = 1; i < depth; i++) {
			result = (Frame) result.getArguments()[0];
		}
		final NablaRootNode rootNode = (NablaRootNode) result.getArguments()[1];
		return rootNode.getFrameStableAssumption();
	}
}