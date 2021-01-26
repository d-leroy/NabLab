// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;

@GeneratedBy(NablaReadArgumentNode.class)
public final class NablaReadArgumentNodeGen extends NablaReadArgumentNode {

    @CompilationFinal private int state_0_;

    private NablaReadArgumentNodeGen(int index) {
        super(index);
    }

    private NablaReadArgumentNodeGen(int index, FrameSlot[] sizeSlots) {
        super(index, sizeSlots);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if (state_0 != 0 /* is-state_0 doBasic(VirtualFrame) */) {
            assert (sizeSlots == null);
            return doBasic(frameValue);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if ((sizeSlots == null)) {
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doBasic(VirtualFrame) */;
            return doBasic(frameValue);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {});
    }

    @Override
    public NodeCost getCost() {
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaReadArgumentNode create(int index) {
        return new NablaReadArgumentNodeGen(index);
    }

    public static NablaReadArgumentNode create(int index, FrameSlot[] sizeSlots) {
        return new NablaReadArgumentNodeGen(index, sizeSlots);
    }

}
