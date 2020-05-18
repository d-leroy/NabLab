package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;

import fr.cea.nabla.interpreter.nodes.NablaNode;

@GenerateWrapper
public abstract class NablaJobNode extends NablaNode {

    @Override
	public WrapperNode createWrapper(ProbeNode probeNode) {
		return new NablaJobNodeWrapper(this, probeNode);
	}
	
}
