package fr.cea.nabla.ir.truffle.nodes.instruction;

import java.util.Map;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.InstrumentableNode;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.runtime.NablaInitializationPerformedException;
import fr.cea.nabla.ir.truffle.utils.GetFrameNode;
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

@GenerateWrapper
@NodeChild(value = "value", type = NablaExpressionNode.class)
@NodeChild(value = "frameToRead", type = GetFrameNode.class)
public abstract class NablaWriteArrayNode extends NablaInstructionNode implements InstrumentableNode {

	private final FrameSlot slot;
	@Children
	private final NablaExpressionNode[] indices;
	@CompilationFinal
	private Class<?> arrayClass;
	@CompilationFinal
	private boolean initializationRequired = true;
	
	public NablaWriteArrayNode(FrameSlot slot, NablaExpressionNode[] indices) {
		this.slot = slot;
		this.indices = indices;
	}

	protected NablaWriteArrayNode() {
		this.slot = null;
		this.indices = null;
	}
		
	@Specialization(rewriteOn = NablaInitializationPerformedException.class)
	public Object write(VirtualFrame frame, Object value, Frame toRead) throws NablaInitializationPerformedException {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		NablaValue array = (NablaValue) FrameUtil.getObjectSafe(toRead, slot);
		this.arrayClass = array.getClass();
		this.initializationRequired = false;
		throw new NablaInitializationPerformedException();
	}
	
	@Specialization(guards = "isNV1Int()")
	protected NV1Int writeNV1Int(VirtualFrame frame, NV0Int value, Frame toRead) {
		NV1Int array = NablaTypesGen.asNV1Int(FrameUtil.getObjectSafe(toRead, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		array.getData()[idx] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV2Int()")
	protected NV2Int writeNV2Int(VirtualFrame frame, NV0Int value, Frame toRead) {
		NV2Int array = NablaTypesGen.asNV2Int(FrameUtil.getObjectSafe(toRead, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		array.getData()[idx1][idx2] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV2Int()")
	protected NV2Int writeNV2Int(VirtualFrame frame, NV1Int value, Frame toRead) {
		NV2Int array = NablaTypesGen.asNV2Int(FrameUtil.getObjectSafe(toRead, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		array.getData()[idx] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV1Real()")
	protected NV1Real writeNV1Real(VirtualFrame frame, NV0Real value, Frame toRead) {
		NV1Real array = NablaTypesGen.asNV1Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		array.getData()[idx] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV2Real()")
	protected NV2Real writeNV2Real(VirtualFrame frame, NV0Real value, Frame toRead) {
		NV2Real array = NablaTypesGen.asNV2Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		array.getData()[idx1][idx2] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV2Real()")
	protected NV2Real writeNV2Real(VirtualFrame frame, NV1Real value, Frame toRead) {
		NV2Real array = NablaTypesGen.asNV2Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		array.getData()[idx] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV3Real()")
	protected NV3Real writeNV3Real(VirtualFrame frame, NV1Real value, Frame toRead) {
		NV3Real array = NablaTypesGen.asNV3Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		array.getData()[idx1][idx2] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV3Real()")
	protected NV3Real writeNV3Real(VirtualFrame frame, NV2Real value, Frame toRead) {
		NV3Real array = NablaTypesGen.asNV3Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		array.getData()[idx] = value.getData();
		return array;
	}
	
	@Specialization(guards = "isNV4Real()")
	protected NV4Real writeNV4Real(VirtualFrame frame, NV2Real value, Frame toRead) {
		NV4Real array = NablaTypesGen.asNV4Real(FrameUtil.getObjectSafe(toRead, slot));
		final int idx1 = NablaTypesGen.asNV0Int(indices[0].executeGeneric(frame)).getData();
		final int idx2 = NablaTypesGen.asNV0Int(indices[1].executeGeneric(frame)).getData();
		array.getData()[idx1][idx2] = value.getData();
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
	
	@Override
	public Map<String, Object> getDebugProperties() {
		Map<String, Object> debugProperties = super.getDebugProperties();
		debugProperties.put("variableSlot", slot);
		return debugProperties;
	}
	
	@Override
	public boolean isInstrumentable() {
		return true;
	}
	
	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		return tag.equals(StandardTags.WriteVariableTag.class);
	}

	@Override
	public WrapperNode createWrapper(ProbeNode probeNode) {
		return new NablaWriteArrayNodeWrapper(this, probeNode);
	}
}
