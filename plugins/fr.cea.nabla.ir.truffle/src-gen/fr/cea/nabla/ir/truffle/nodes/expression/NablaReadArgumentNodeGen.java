// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadArgumentNode;

@GeneratedBy(NablaReadArgumentNode.class)
public final class NablaReadArgumentNodeGen extends NablaReadArgumentNode {

    @CompilationFinal private int state_;

    private NablaReadArgumentNodeGen(int index) {
        super(index);
    }

    private NablaReadArgumentNodeGen(int index, FrameSlot[] sizeSlots) {
        super(index, sizeSlots);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active doBasic(VirtualFrame) */) {
            assert (sizeSlots == null);
            return doBasic(frameValue);
        }
        if ((state & 0b10) != 0 /* is-active doSizeVars(VirtualFrame) */) {
            return doSizeVars(frameValue);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        int state = state_;
        if ((sizeSlots == null)) {
            this.state_ = state = state | 0b1 /* add-active doBasic(VirtualFrame) */;
            return doBasic(frameValue);
        }
        this.state_ = state = state | 0b10 /* add-active doSizeVars(VirtualFrame) */;
        return doSizeVars(frameValue);
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

    public static NablaReadArgumentNode create(int index) {
        return new NablaReadArgumentNodeGen(index);
    }

    public static NablaReadArgumentNode create(int index, FrameSlot[] sizeSlots) {
        return new NablaReadArgumentNodeGen(index, sizeSlots);
    }

}
