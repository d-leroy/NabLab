package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.NV0Int;

public class NablaGetMeshNbElementsNode extends NablaExpressionNode {

	private final String connectivityName;
	
	public NablaGetMeshNbElementsNode(String connectivityName) {
		this.connectivityName = connectivityName;
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return new NV0Int(NablaContext.getMeshWrapper().getNbElems(connectivityName));
	}
}
