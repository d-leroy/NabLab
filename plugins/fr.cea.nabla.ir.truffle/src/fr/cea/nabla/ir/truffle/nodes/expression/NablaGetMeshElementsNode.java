package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.NV1Int;

public class NablaGetMeshElementsNode extends NablaExpressionNode {

	@Children
	private final NablaExpressionNode[] argIds;
	private final String connectivityName;
	
	public NablaGetMeshElementsNode(NablaExpressionNode[] argIds, String connectivityName) {
		this.argIds = argIds;
		this.connectivityName = connectivityName;
	}
	
	@Override
	@ExplodeLoop
	public Object executeGeneric(VirtualFrame frame) {
		final int[] argIdsValues = new int[argIds.length];
		for (int i = 0; i < argIds.length; i++) {
			argIdsValues[i] = NablaTypesGen.asNV0Int(argIds[i].executeGeneric(frame)).getData();
		}
		return new NV1Int(NablaContext.getMeshWrapper().getElements(connectivityName, argIdsValues));
	}
}
