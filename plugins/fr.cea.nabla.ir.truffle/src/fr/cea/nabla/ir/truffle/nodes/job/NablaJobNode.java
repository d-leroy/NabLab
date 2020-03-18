package fr.cea.nabla.ir.truffle.nodes.job;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;

import fr.cea.nabla.ir.truffle.nodes.NablaRootNode;

public abstract class NablaJobNode extends NablaRootNode {

	public NablaJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name) {
		super(language, frameDescriptor, name);
	}
}
