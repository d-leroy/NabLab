package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child
	private NablaInstructionNode instruction;
	
	public NablaInstructionJobNode(NablaInstructionNode instruction) {
		this.instruction = instruction;
	}
	
	public Object executeVoid(VirtualFrame frame) {
		// FIXME instantiate new frame?
		return instruction.executeGeneric(frame);
	}

}
