package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;

public abstract class NablaInstructionNode extends NablaNode {

	public abstract Object executeGeneric(VirtualFrame frame);
	
	

}
