package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.runtime.NablaNull;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public abstract class NablaReadArgumentNode extends NablaExpressionNode {

	private final int index;
	protected final FrameSlot[] sizeSlots;
	
	public NablaReadArgumentNode(int index) {
		this.index = index;
		this.sizeSlots = null;
	}
	
	public NablaReadArgumentNode(int index, FrameSlot[] sizeSlots) {
		this.index = index;
		this.sizeSlots = sizeSlots;
	}

	@Specialization(guards = "sizeSlots == null")
	public Object doBasic(VirtualFrame frame) {
		Object[] args = frame.getArguments();
		if (index+1 < args.length) {
			return args[index+1];
		} else {
			return NablaNull.SINGLETON;
		}
	}
	
	@Specialization
	@ExplodeLoop
	public Object doSizeVars(VirtualFrame frame) {
		Object[] args = frame.getArguments();
		if (index+1 < args.length) {
			final NablaValue val = (NablaValue) args[index+1];
			for (int i = 0; i < sizeSlots.length; i++) {
				final FrameSlot s = sizeSlots[i];
				if (s != null) {
					frame.setObject(s, new NV0Int(val.getDimension(i+1)));
					frame.getFrameDescriptor().setFrameSlotKind(s, FrameSlotKind.Object);
				}
			}
			return args[index+1];
		} else {
			return NablaNull.SINGLETON;
		}
	}
}
