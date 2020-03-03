package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child private NablaInstructionNode instruction;

	@Override
	public Object executeVoid(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
	
}
