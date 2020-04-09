package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Bool;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Bool;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Bool;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Bool;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@NodeChild(value = "array", type = NablaExpressionNode.class)
public abstract class NablaReadArrayDimensionNode extends NablaExpressionNode {

	private final int index;
	
	public NablaReadArrayDimensionNode(int index) {
		this.index = index;
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV1Bool array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV2Bool array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV3Bool array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV4Bool array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV1Int array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV2Int array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV3Int array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV4Int array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV1Real array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV2Real array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV3Real array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NV4Real array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
	@Specialization
	protected NV0Int readDimension(VirtualFrame frame, NablaValue array) {
		return new NV0Int(array.getDimension(index+1));
	}
	
}
