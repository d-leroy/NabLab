package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;

//@NodeChild("arrayNode")
public class NablaReadArrayNode extends NablaExpressionNode {
	
	public NablaReadArrayNode(NablaExpressionNode array) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		throw new UnsupportedOperationException();
	}
	
//	@Children
//	protected final NablaExpressionNode[] indices;
	
//	public NablaReadArrayNode(NablaExpressionNode[] indices) {
//		this.indices = indices;
//	}
	
//	@Override
//	public Object executeGeneric(VirtualFrame frame) {
//		return null;
//	}
	
//	@Specialization(guards = "indices.length == 1")
//	public NV0Bool executeNV0Bool(NV1Bool array, VirtualFrame frame) {
//		final int computedDimension = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
//		return new NV0Bool(array.getData()[computedDimension]);
//	}
//
//	@Specialization(guards = "indices.length == 1")
//	protected NV1Bool read(NV2Bool array, VirtualFrame frame) {
//		final int computedDimension = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
//		return new NV1Bool(array.getData()[computedDimension]);
//	}
//	
//	@Specialization(guards = "indices.length == 1")
//	protected NV2Bool read(NV3Bool array, VirtualFrame frame) {
//		final int computedDimension = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
//		return new NV2Bool(array.getData()[computedDimension]);
//	}
//	
//	@Specialization(guards = "indices.length == 1")
//	protected NV3Bool read(NV4Bool array, VirtualFrame frame) {
//		final int computedDimension = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
//		return new NV3Bool(array.getData()[computedDimension]);
//	}
	
	
}
