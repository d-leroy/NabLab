package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;

public abstract class NablaInstructionNode extends Node implements NablaNode {

	public abstract Object executeGeneric(VirtualFrame frame);
	
	

}
