package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@NodeChild(value = "value", type = NablaExpressionNode.class)
public abstract class NablaWriteArrayNode extends NablaInstructionNode {

	private final FrameSlot slot;
	private final NablaExpressionNode[] indices;

	@CompilationFinal
	boolean initializationRequired = true;
	@CompilationFinal
	int depth = 0;
	@CompilationFinal
	Class<?> arrayClass;

	public NablaWriteArrayNode(FrameSlot slot, NablaExpressionNode[] indices) {
		this.slot = slot;
		this.indices = indices;
	}
	
	static class InitializationPerformedException extends Exception {
		private static final long serialVersionUID = 8778912783151932310L;
	}
	
	@Specialization(rewriteOn = InitializationPerformedException.class)
	public Object write(VirtualFrame frame, Object value) throws InitializationPerformedException {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		FrameDescriptor descriptor = Truffle.getRuntime().iterateFrames(f -> {
			final FrameDescriptor result = f.getFrame(FrameAccess.READ_ONLY).getFrameDescriptor();
			if (result.getSlots().contains(slot)) {
				return result;
			}
			depth++;
			return null;
		});
		descriptor.setFrameSlotKind(slot, FrameSlotKind.Object);
		
		final Frame frameToWrite = getFrameToWrite();
		
		NablaValue array = (NablaValue) FrameUtil.getObjectSafe(frameToWrite, slot);
		arrayClass = array.getClass();
		initializationRequired = false;
		
		throw new InitializationPerformedException();
	}
	
	private Frame getFrameToWrite() {
		final int[] i = new int[] { 0 };
		return Truffle.getRuntime().iterateFrames(f -> {
			if (i[0] < depth) {
				i[0]++;
				return null;
			}
			return f.getFrame(FrameAccess.READ_WRITE);
		});
	}
	
	@Specialization(guards = "isNV1Int()")
	protected NV1Int writeNV1Int(VirtualFrame frame, NV0Int value) {
		final Frame frameToWrite = getFrameToWrite(); 
		NV1Int array = NablaTypesGen.asNV1Int(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int[] result = array.getData().clone();
		result[idx] = value.getData();
		array = new NV1Int(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV2Int()")
	protected NV2Int writeNV2Int(VirtualFrame frame, NV0Int value) {
		final Frame frameToWrite = getFrameToWrite();
		NV2Int array = NablaTypesGen.asNV2Int(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final int[][] result = array.getData().clone();
		result[idx1][idx2] = value.getData();
		array = new NV2Int(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV2Int()")
	protected NV2Int writeNV2Int(VirtualFrame frame, NV1Int value) {
		final Frame frameToWrite = getFrameToWrite();
		NV2Int array = NablaTypesGen.asNV2Int(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int[][] result = array.getData().clone();
		result[idx] = value.getData();
		array = new NV2Int(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV1Real()")
	protected NV1Real writeNV1Real(VirtualFrame frame, NV0Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV1Real array = NablaTypesGen.asNV1Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final double[] result = array.getData().clone();
		result[idx] = value.getData();
		array = new NV1Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV2Real()")
	protected NV2Real writeNV2Real(VirtualFrame frame, NV0Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV2Real array = NablaTypesGen.asNV2Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final double[][] result = array.getData().clone();
		result[idx1][idx2] = value.getData();
		array = new NV2Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV2Real()")
	protected NV2Real writeNV2Real(VirtualFrame frame, NV1Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV2Real array = NablaTypesGen.asNV2Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final double[][] result = array.getData().clone();
		result[idx] = value.getData();
		array = new NV2Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV3Real()")
	protected NV3Real writeNV3Real(VirtualFrame frame, NV1Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV3Real array = NablaTypesGen.asNV3Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final double[][][] result = array.getData().clone();
		result[idx1][idx2] = value.getData();
		array = new NV3Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV3Real()")
	protected NV3Real writeNV3Real(VirtualFrame frame, NV2Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV3Real array = NablaTypesGen.asNV3Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final double[][][] result = array.getData().clone();
		result[idx] = value.getData();
		array = new NV3Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}
	
	@Specialization(guards = "isNV4Real()")
	protected NV4Real writeNV4Real(VirtualFrame frame, NV2Real value) {
		final Frame frameToWrite = getFrameToWrite();
		NV4Real array = NablaTypesGen.asNV4Real(FrameUtil.getObjectSafe(frameToWrite, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		final double[][][][] result = array.getData().clone();
		result[idx1][idx2] = value.getData();
		array = new NV4Real(result);
		frameToWrite.setObject(slot, array);
		return array;
	}

	public FrameSlot getSlot() {
		return slot;
	}
	
	protected boolean isNV1Int() {
		return !initializationRequired && arrayClass == NV1Int.class;
	}
	
	protected boolean isNV2Int() {
		return !initializationRequired && arrayClass == NV2Int.class;
	}
	
	protected boolean isNV3Int() {
		return !initializationRequired && arrayClass == NV3Int.class;
	}
	
	protected boolean isNV4Int() {
		return !initializationRequired && arrayClass == NV4Int.class;
	}
	
	protected boolean isNV1Real() {
		return !initializationRequired && arrayClass == NV1Real.class;
	}
	
	protected boolean isNV2Real() {
		return !initializationRequired && arrayClass == NV2Real.class;
	}
	
	protected boolean isNV3Real() {
		return !initializationRequired && arrayClass == NV3Real.class;
	}
	
	protected boolean isNV4Real() {
		return !initializationRequired && arrayClass == NV4Real.class;
	}
}
