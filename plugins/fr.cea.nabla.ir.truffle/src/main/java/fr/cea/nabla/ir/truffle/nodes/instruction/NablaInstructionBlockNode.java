package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class NablaInstructionBlockNode extends NablaInstructionNode {

	@Children NablaInstructionNode[] instructions;

	public NablaInstructionBlockNode(NablaInstructionNode[] instructions) {
		this.instructions = instructions;
	}
	
	@Override
	@ExplodeLoop
	public Object executeGeneric(VirtualFrame frame) {
		for (int i = 0; i < instructions.length - 1; i++) {
			instructions[i].executeGeneric(frame);
		}
		return instructions[instructions.length - 1].executeGeneric(frame);
	}
}
