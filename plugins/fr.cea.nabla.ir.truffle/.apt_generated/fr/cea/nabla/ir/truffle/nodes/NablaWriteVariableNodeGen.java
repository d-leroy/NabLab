// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@GeneratedBy(NablaWriteVariableNode.class)
public final class NablaWriteVariableNodeGen extends NablaWriteVariableNode {

    private final FrameSlot slot;
    @Child private NablaExpressionNode defaultValue_;
    @CompilationFinal private int state_;

    private NablaWriteVariableNodeGen(NablaExpressionNode defaultValue, FrameSlot slot) {
        this.slot = slot;
        this.defaultValue_ = defaultValue;
    }

    @Override
    public FrameSlot getSlot() {
        return this.slot;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object defaultValueValue_ = this.defaultValue_.executeGeneric(frameValue);
        if (state != 0 /* is-active write(VirtualFrame, NablaValue) */ && defaultValueValue_ instanceof NablaValue) {
            NablaValue defaultValueValue__ = (NablaValue) defaultValueValue_;
            return write(frameValue, defaultValueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, defaultValueValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object defaultValueValue) {
        int state = state_;
        if (defaultValueValue instanceof NablaValue) {
            NablaValue defaultValueValue_ = (NablaValue) defaultValueValue;
            this.state_ = state = state | 0b1 /* add-active write(VirtualFrame, NablaValue) */;
            return write(frameValue, defaultValueValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.defaultValue_}, defaultValueValue);
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

    public static NablaWriteVariableNode create(NablaExpressionNode defaultValue, FrameSlot slot) {
        return new NablaWriteVariableNodeGen(defaultValue, slot);
    }

}
