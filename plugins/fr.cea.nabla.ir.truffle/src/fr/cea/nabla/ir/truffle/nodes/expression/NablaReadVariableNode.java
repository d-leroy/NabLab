package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeField(name = "slot", type = FrameSlot.class)
public abstract class NablaReadVariableNode extends NablaExpressionNode {

    protected abstract FrameSlot getSlot();

	public static interface FrameGet<T> {
		public T get(VirtualFrame frame, FrameSlot slot) throws FrameSlotTypeException;
	}

	public <T> T readUpStack(FrameGet<T> getter, VirtualFrame frame) {
		try {
			T value = getter.get(frame, this.getSlot());
			while (value == null) {
				if (frame == null) {
					throw new RuntimeException("Unknown variable: " + this.getSlot().getIdentifier());
				}
				value = getter.get(frame, this.getSlot());
			}
			return value;
		} catch (FrameSlotTypeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Specialization(guards = "frame.isBoolean(getSlot())")
	protected boolean readBoolean(VirtualFrame frame) {
		return readUpStack(FrameUtil::getBooleanSafe, frame);
	}

	@Specialization(guards = "frame.isInt(getSlot())")
	protected int readInt(VirtualFrame frame) {
		return readUpStack(FrameUtil::getIntSafe, frame);
	}

	@Specialization(guards = "frame.isDouble(getSlot())")
	protected double readDouble(VirtualFrame frame) {
		return FrameUtil.getDoubleSafe(frame, getSlot());
	}

	@Specialization(replaces = { "readBoolean", "readInt", "readDouble" })
	protected Object readObject(VirtualFrame frame) {
		if (!frame.isObject(getSlot())) {
			CompilerDirectives.transferToInterpreter();
			Object result = frame.getValue(getSlot());
			// Write slot as object so we don't go through this path again
			frame.setObject(getSlot(), result);
			return result;
		}

		return FrameUtil.getObjectSafe(frame, getSlot());
	}

}
