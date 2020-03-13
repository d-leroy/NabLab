package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;

public abstract class NablaJobNode extends NablaNode {

	public abstract Object executeVoid(VirtualFrame frame);

}
