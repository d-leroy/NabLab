package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.values.NV0Int;

public class NablaGetMeshMaxNbElementsNode extends NablaExpressionNode {

	private final String connectivityName;
	
	public NablaGetMeshMaxNbElementsNode(String connectivityName) {
		this.connectivityName = connectivityName;
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return new NV0Int(NablaContext.getMeshWrapper().getMaxNbElems(connectivityName));
	}
}
