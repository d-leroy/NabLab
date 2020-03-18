package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeField(name = "slot", type = FrameSlot.class)
public abstract class NablaReadVariableNode extends NablaExpressionNode {

	protected abstract FrameSlot getSlot();
	
	// Whether the node was initialized and which type of variable it reads
	@CompilationFinal private boolean initialized;
	@CompilationFinal private FrameSlotKind slotKind;

	@Specialization(guards = "isBoolean()")
	protected boolean readBoolean(VirtualFrame frame) {
		return Truffle.getRuntime().iterateFrames(f -> {
			try {
				return f.getFrame(FrameAccess.READ_ONLY).getBoolean(getSlot());
			} catch (IllegalArgumentException e) {
				return null;
			} catch (FrameSlotTypeException e) {
				return null;
			}
		});
	}

	@Specialization(guards = "isInt()")
	protected int readInt(VirtualFrame frame) {
		return Truffle.getRuntime().iterateFrames(f -> {
			try {
				return f.getFrame(FrameAccess.READ_ONLY).getInt(getSlot());
			} catch (IllegalArgumentException e) {
				return null;
			} catch (FrameSlotTypeException e) {
				return null;
			}
		});
	}

	@Specialization(guards = "isDouble()")
	protected double readDouble(VirtualFrame frame) {
		return Truffle.getRuntime().iterateFrames(f -> {
			try {
				return f.getFrame(FrameAccess.READ_ONLY).getDouble(getSlot());
			} catch (IllegalArgumentException e) {
				return null;
			} catch (FrameSlotTypeException e) {
				return null;
			}
		});
	}

	@Specialization(replaces = { "readBoolean", "readInt", "readDouble" })
	protected Object readObject(VirtualFrame frame) {
		return Truffle.getRuntime().iterateFrames(f -> {
			try {
				return f.getFrame(FrameAccess.READ_ONLY).getObject(getSlot());
			} catch (IllegalArgumentException e) {
				return null;
			} catch (FrameSlotTypeException e) {
				return null;
			}
		});
	}
	
	private void initialize() {
		final FrameSlot slot = getSlot();
		CompilerDirectives.transferToInterpreterAndInvalidate();
		slotKind = Truffle.getRuntime().iterateFrames(f -> {
			final Frame frame = f.getFrame(FrameAccess.READ_ONLY);
			final FrameDescriptor descriptor = frame.getFrameDescriptor();
			if (descriptor.getSlots().contains(slot)) {
				return descriptor.getFrameSlotKind(slot);
			} else {
				return null;
			}
		});
		initialized = true;
	}
	
	private boolean isKind(FrameSlotKind kind) {
		if (!initialized) {
			initialize();
		}
		if (slotKind == kind) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isBoolean() {
		return isKind(FrameSlotKind.Boolean);
	}
	
	protected boolean isInt() {
		return isKind(FrameSlotKind.Int);
	}
	
	protected boolean isDouble() {
		return isKind(FrameSlotKind.Double);
	}
}
