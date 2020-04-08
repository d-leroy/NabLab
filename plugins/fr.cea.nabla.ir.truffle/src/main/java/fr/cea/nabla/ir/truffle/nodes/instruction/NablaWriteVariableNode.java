package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.utils.GetFrameNode;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@NodeChild(value = "value", type = NablaExpressionNode.class)
@NodeChild(value = "frameToWrite", type = GetFrameNode.class)
public abstract class NablaWriteVariableNode extends NablaInstructionNode {

	private final FrameSlot slot;

	public NablaWriteVariableNode(FrameSlot slot) {
		this.slot = slot;
	}

	@Specialization
	protected Object doWrite(VirtualFrame frame, NablaValue value, Frame toWrite) {
		toWrite.setObject(slot, value);
		return value;
	}
	
	public FrameSlot getSlot() {
		return slot;
	}

}
