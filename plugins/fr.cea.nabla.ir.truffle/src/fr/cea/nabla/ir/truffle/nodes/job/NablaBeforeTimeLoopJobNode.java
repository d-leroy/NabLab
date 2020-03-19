package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaBeforeTimeLoopJobNode extends NablaJobNode {

	@Children
	private final NablaInstructionNode[] instructions;

	public NablaBeforeTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			NablaInstructionNode[] instructions) {
		super(language, frameDescriptor, name);
		this.instructions = instructions;
	}

	@Override
	@ExplodeLoop
	public Object execute(VirtualFrame frame) {
		for (NablaInstructionNode instruction : instructions) {
			instruction.executeGeneric(frame);
		}
		return null;
	}
}
