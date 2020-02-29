package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class NablaJobNode extends NablaNode {

	public abstract Object executeVoid(VirtualFrame frame);

}
