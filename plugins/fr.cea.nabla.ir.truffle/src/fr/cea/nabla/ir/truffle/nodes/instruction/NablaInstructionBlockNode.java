package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;

public class NablaInstructionBlockNode extends NablaInstructionNode {

	@Children NablaInstructionNode[] instructions;

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		// TODO Auto-generated method stub
		return null;
	}
}
