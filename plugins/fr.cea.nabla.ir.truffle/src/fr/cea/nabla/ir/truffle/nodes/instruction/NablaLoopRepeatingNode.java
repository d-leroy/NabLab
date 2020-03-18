package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RepeatingNode;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;

public class NablaLoopRepeatingNode extends NablaInstructionNode implements RepeatingNode {

	@Children NablaInstructionNode[] instructions;
	@Child NablaExpressionNode count;

	public NablaLoopRepeatingNode(NablaInstructionNode[] instructions) {
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

	@Override
	public boolean executeRepeating(VirtualFrame frame) {
		// TODO Auto-generated method stub
		return false;
	}
}
