package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionBlockNode;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child
	private NablaInstructionBlockNode instructionBlock;
	
	public NablaInstructionJobNode(String name, NablaInstructionBlockNode instructionBlock) {
		super(name);
		this.instructionBlock = instructionBlock;
	}

	protected NablaInstructionJobNode() {
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return instructionBlock.executeGeneric(frame);
	}
}
