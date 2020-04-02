package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaAfterTimeLoopJobNode extends NablaJobNode {

	@Child
	private NablaInstructionNode instruction;

	public NablaAfterTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			NablaInstructionNode instruction) {
		super(language, frameDescriptor, name);
		this.instruction = instruction;
	}

	@Override

	@ExplodeLoop
	public Object execute(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
}
