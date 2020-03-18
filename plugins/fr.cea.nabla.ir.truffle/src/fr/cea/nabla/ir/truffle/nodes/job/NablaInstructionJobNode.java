package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child
	private NablaInstructionNode instruction;

	public NablaInstructionJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			NablaInstructionNode instruction) {
		super(language, frameDescriptor, name);
		this.instruction = instruction;
	}
	
	@Override
	public Object execute(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
}
