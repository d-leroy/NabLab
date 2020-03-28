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
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@NodeChild(value = "value", type = NablaExpressionNode.class)
public abstract class NablaWriteVariableNode extends NablaInstructionNode {

	private final FrameSlot slot;

	@CompilationFinal
	boolean initializationRequired = true;

	@CompilationFinal
	int depth = 0;
	
	public NablaWriteVariableNode(FrameSlot slot) {
		this.slot = slot;
	}

	@ExplodeLoop
	@Specialization
	protected Object write(VirtualFrame frame, NablaValue value) {
		if (initializationRequired) {
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
			initializationRequired = false;
		}
		int[] i = new int[] { 0 };
		final Frame frameToWrite = Truffle.getRuntime().iterateFrames(f -> {
			if (i[0] < depth) {
				i[0]++;
				return null;
			}
			return f.getFrame(FrameAccess.READ_WRITE);
		});
		frameToWrite.setObject(slot, value);
		return value;
	}
	
	public FrameSlot getSlot() {
		return slot;
	}
}
