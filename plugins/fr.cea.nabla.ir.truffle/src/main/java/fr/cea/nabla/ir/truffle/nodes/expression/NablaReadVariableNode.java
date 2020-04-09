package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.runtime.NablaInternalError;
import fr.cea.nabla.ir.truffle.utils.GetFrameNode;

@NodeChild(value = "frameToRead", type = GetFrameNode.class)
public abstract class NablaReadVariableNode extends NablaExpressionNode {

	private final FrameSlot slot;

	public NablaReadVariableNode(FrameSlot slot) {
		this.slot = slot;
	}

	@Specialization
	protected Object doRead(VirtualFrame frame, Frame toRead) {
		try {
			return toRead.getObject(slot);
		} catch (FrameSlotTypeException e) {
			e.printStackTrace();
			throw NablaInternalError.shouldNotReachHere();
		}
	}
	
	public FrameSlot getSlot() {
		return slot;
	}
}
