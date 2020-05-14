package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;

@GenerateWrapper
public abstract class NablaInstructionNode extends NablaNode {

	@Override
	public WrapperNode createWrapper(ProbeNode probe) {
		return new NablaInstructionNodeWrapper(this, probe);
	}
	
}
