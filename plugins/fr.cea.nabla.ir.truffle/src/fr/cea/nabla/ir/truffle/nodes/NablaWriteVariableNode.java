package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.values.NablaValue;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

@NodeChild(value = "defaultValue", type = NablaExpressionNode.class)
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class NablaWriteVariableNode extends NablaInstructionNode {

	public abstract FrameSlot getSlot();

    @Specialization
    protected Object write(VirtualFrame frame, NablaValue value) {
        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Object);
        frame.setObject(getSlot(), value);
        return value;
    }
}
