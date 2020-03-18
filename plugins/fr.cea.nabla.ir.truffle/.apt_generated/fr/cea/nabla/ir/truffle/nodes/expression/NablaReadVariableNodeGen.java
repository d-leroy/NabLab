// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNode;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaReadVariableNode.class)
public final class NablaReadVariableNodeGen extends NablaReadVariableNode {

    private final FrameSlot slot;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;

    private NablaReadVariableNodeGen(FrameSlot slot) {
        this.slot = slot;
    }

    @Override
    protected FrameSlot getSlot() {
        return this.slot;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active readBoolean(VirtualFrame) */) {
            assert (isBoolean());
            return readBoolean(frameValue);
        }
        if ((state & 0b10) != 0 /* is-active readInt(VirtualFrame) */) {
            assert (isInt());
            return readInt(frameValue);
        }
        if ((state & 0b100) != 0 /* is-active readDouble(VirtualFrame) */) {
            assert (isDouble());
            return readDouble(frameValue);
        }
        if ((state & 0b1000) != 0 /* is-active readObject(VirtualFrame) */) {
            return readObject(frameValue);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if (((exclude & 0b1)) == 0 /* is-not-excluded readBoolean(VirtualFrame) */) {
                if ((isBoolean())) {
                    this.state_ = state = state | 0b1 /* add-active readBoolean(VirtualFrame) */;
                    lock.unlock();
                    hasLock = false;
                    return readBoolean(frameValue);
                }
            }
            if (((exclude & 0b10)) == 0 /* is-not-excluded readInt(VirtualFrame) */) {
                if ((isInt())) {
                    this.state_ = state = state | 0b10 /* add-active readInt(VirtualFrame) */;
                    lock.unlock();
                    hasLock = false;
                    return readInt(frameValue);
                }
            }
            if (((exclude & 0b100)) == 0 /* is-not-excluded readDouble(VirtualFrame) */) {
                if ((isDouble())) {
                    this.state_ = state = state | 0b100 /* add-active readDouble(VirtualFrame) */;
                    lock.unlock();
                    hasLock = false;
                    return readDouble(frameValue);
                }
            }
            this.exclude_ = exclude = exclude | 0b111 /* add-excluded readBoolean(VirtualFrame), readInt(VirtualFrame), readDouble(VirtualFrame) */;
            state = state & 0xfffffff8 /* remove-active readBoolean(VirtualFrame), readInt(VirtualFrame), readDouble(VirtualFrame) */;
            this.state_ = state = state | 0b1000 /* add-active readObject(VirtualFrame) */;
            lock.unlock();
            hasLock = false;
            return readObject(frameValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
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

    public static NablaReadVariableNode create(FrameSlot slot) {
        return new NablaReadVariableNodeGen(slot);
    }

}
