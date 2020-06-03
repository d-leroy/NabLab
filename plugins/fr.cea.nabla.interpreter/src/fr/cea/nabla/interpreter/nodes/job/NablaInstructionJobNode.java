package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionNode;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child
	private NablaInstructionNode instruction;
	
	public NablaInstructionJobNode(NablaInstructionNode instruction, String name) {
		super(name);
		this.instruction = instruction;
	}

	protected NablaInstructionJobNode() {
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
	
}
