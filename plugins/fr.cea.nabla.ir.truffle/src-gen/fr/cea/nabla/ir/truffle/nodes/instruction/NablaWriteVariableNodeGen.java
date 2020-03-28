// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@GeneratedBy(NablaWriteVariableNode.class)
public final class NablaWriteVariableNodeGen extends NablaWriteVariableNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;

    private NablaWriteVariableNodeGen(FrameSlot slot, NablaExpressionNode value) {
        super(slot);
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if (state != 0 /* is-active write(VirtualFrame, NablaValue) */ && valueValue_ instanceof NablaValue) {
            NablaValue valueValue__ = (NablaValue) valueValue_;
            return write(frameValue, valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
        int state = state_;
        if (valueValue instanceof NablaValue) {
            NablaValue valueValue_ = (NablaValue) valueValue;
            this.state_ = state = state | 0b1 /* add-active write(VirtualFrame, NablaValue) */;
            return write(frameValue, valueValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaWriteVariableNode create(FrameSlot slot, NablaExpressionNode value) {
        return new NablaWriteVariableNodeGen(slot, value);
    }

}
