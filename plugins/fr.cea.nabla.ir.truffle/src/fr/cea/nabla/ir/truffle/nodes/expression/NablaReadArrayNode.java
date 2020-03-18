package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Bool;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Bool;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

@NodeChild("arrayNode")
public abstract class NablaReadArrayNode extends NablaExpressionNode {
	
	@Children
	protected NablaExpressionNode[] indices;
	
	protected abstract NablaExpressionNode getArrayNode();
	
	public NablaReadArrayNode(NablaExpressionNode[] indices) {
		this.indices = indices;
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV0Int readNV1Int1Index(VirtualFrame frame, NV1Int array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV0Int(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV1Int readNV2Int1Index(VirtualFrame frame, NV2Int array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV1Int(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 2")
	protected NV0Int readNV2Int2Indices(VirtualFrame frame, NV2Int array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		return new NV0Int(array.getData()[idx1][idx2]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV0Real readNV1Real1Index(VirtualFrame frame, NV1Real array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV0Real(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV1Real readNV2Real1Index(VirtualFrame frame, NV2Real array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV1Real(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 2")
	protected NV0Real readNV2Real2Indices(VirtualFrame frame, NV2Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		return new NV0Real(array.getData()[idx1][idx2]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV0Bool readNV1Bool1Index(VirtualFrame frame, NV1Bool array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV0Bool(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV1Bool readNV2Bool1Index(VirtualFrame frame, NV2Bool array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV1Bool(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 2")
	protected NV0Bool readNV2Bool2Indices(VirtualFrame frame, NV2Bool array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		return new NV0Bool(array.getData()[idx1][idx2]);
	}

	
	
}
