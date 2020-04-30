package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaInstructionJobNode extends NablaNode {

	@Child
	private NablaInstructionNode instruction;

	public NablaInstructionJobNode(NablaInstructionNode instruction) {
		this.instruction = instruction;
	}
	
	@Override
	public final Object executeGeneric(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
}
