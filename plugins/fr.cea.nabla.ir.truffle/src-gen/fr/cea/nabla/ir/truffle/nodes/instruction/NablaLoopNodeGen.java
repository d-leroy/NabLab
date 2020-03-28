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
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaLoopNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Int;

@GeneratedBy(NablaLoopNode.class)
public final class NablaLoopNodeGen extends NablaLoopNode {

    @Child private NablaExpressionNode count_;
    @CompilationFinal private int state_;

    private NablaLoopNodeGen(FrameSlot indexSlot, NablaInstructionNode body, NablaExpressionNode count) {
        super(indexSlot, body);
        this.count_ = count;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object countValue_ = this.count_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active doLoop(VirtualFrame, NV0Int) */ && countValue_ instanceof NV0Int) {
            NV0Int countValue__ = (NV0Int) countValue_;
            return doLoop(frameValue, countValue__);
        }
        if ((state & 0b10) != 0 /* is-active doLoop(VirtualFrame, NV1Int) */ && countValue_ instanceof NV1Int) {
            NV1Int countValue__ = (NV1Int) countValue_;
            return doLoop(frameValue, countValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, countValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object countValue) {
        int state = state_;
        if (countValue instanceof NV0Int) {
            NV0Int countValue_ = (NV0Int) countValue;
            this.state_ = state = state | 0b1 /* add-active doLoop(VirtualFrame, NV0Int) */;
            return doLoop(frameValue, countValue_);
        }
        if (countValue instanceof NV1Int) {
            NV1Int countValue_ = (NV1Int) countValue;
            this.state_ = state = state | 0b10 /* add-active doLoop(VirtualFrame, NV1Int) */;
            return doLoop(frameValue, countValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.count_}, countValue);
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            return NodeCost.MONOMORPHIC;
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaLoopNode create(FrameSlot indexSlot, NablaInstructionNode body, NablaExpressionNode count) {
        return new NablaLoopNodeGen(indexSlot, body, count);
    }

}
