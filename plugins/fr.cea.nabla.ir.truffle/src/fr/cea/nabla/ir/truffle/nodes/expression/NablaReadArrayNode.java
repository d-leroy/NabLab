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
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Real;

@NodeChild("arrayNode")
public abstract class NablaReadArrayNode extends NablaExpressionNode {
	
	@Children
	protected NablaExpressionNode[] indices;
	
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
	protected NV2Real readNV3Real1Index(VirtualFrame frame, NV3Real array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV2Real(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 2")
	protected NV1Real readNV3Real2Indices(VirtualFrame frame, NV3Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		return new NV1Real(array.getData()[idx1][idx2]);
	}
	
	@Specialization(guards = "indices.length == 3")
	protected NV0Real readNV3Real3Indices(VirtualFrame frame, NV3Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final int idx3 = NablaTypesGen.asNV0Int(indices[2].executeGeneric(frame)).getData();
		return new NV0Real(array.getData()[idx1][idx2][idx3]);
	}
	
	@Specialization(guards = "indices.length == 1")
	protected NV3Real readNV4Real1Index(VirtualFrame frame, NV4Real array) {
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		return new NV3Real(array.getData()[idx]);
	}
	
	@Specialization(guards = "indices.length == 2")
	protected NV2Real readNV4Real2Indices(VirtualFrame frame, NV4Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		return new NV2Real(array.getData()[idx1][idx2]);
	}
	
	@Specialization(guards = "indices.length == 3")
	protected NV1Real readNV4Real3Indices(VirtualFrame frame, NV4Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final int idx3 = NablaTypesGen.asNV0Int(indices[2].executeGeneric(frame)).getData();
		return new NV1Real(array.getData()[idx1][idx2][idx3]);
	}
	
	@Specialization(guards = "indices.length == 4")
	protected NV0Real readNV4Real4Indices(VirtualFrame frame, NV4Real array) {
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final int idx3 = NablaTypesGen.asNV0Int(indices[2].executeGeneric(frame)).getData();
		final int idx4 = NablaTypesGen.asNV0Int(indices[3].executeGeneric(frame)).getData();
		return new NV0Real(array.getData()[idx1][idx2][idx3][idx4]);
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
