package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;

public class NablaInstructionBlockNode extends NablaInstructionNode {

	@Children NablaInstructionNode[] instructions;

	public NablaInstructionBlockNode(NablaInstructionNode[] instructions) {
		this.instructions = instructions;
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		throw new UnsupportedOperationException();
	}
}
