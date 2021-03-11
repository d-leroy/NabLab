// CheckStyle: start generated
package fr.cea.nabla.interpreter.utils;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import java.util.concurrent.locks.Lock;

@GeneratedBy(GetFrameNode.class)
public final class GetFrameNodeGen extends GetFrameNode {

    @CompilationFinal private int state_0_;
    @CompilationFinal private int exclude_;

    private GetFrameNodeGen(String name) {
        super(name);
    }

    @Override
    public Frame execute(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if ((state_0 & 0b1) != 0 /* is-state_0 initialize(VirtualFrame) */) {
            try {
                return initialize(frameValue);
            } catch (NablaInitializationPerformedException ex) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                Lock lock = getLock();
                lock.lock();
                try {
                    this.exclude_ = this.exclude_ | 0b1 /* add-exclude initialize(VirtualFrame) */;
                    this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 initialize(VirtualFrame) */;
                } finally {
                    lock.unlock();
                }
                return executeAndSpecialize(frameValue);
            }
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 doCached(VirtualFrame) */) {
            return doCached(frameValue);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Frame executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-exclude initialize(VirtualFrame) */) {
                this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 initialize(VirtualFrame) */;
                try {
                    lock.unlock();
                    hasLock = false;
                    return initialize(frameValue);
                } catch (NablaInitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-exclude initialize(VirtualFrame) */;
                        this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 initialize(VirtualFrame) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue);
                }
            }
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doCached(VirtualFrame) */;
            lock.unlock();
            hasLock = false;
            return doCached(frameValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            if ((state_0 & (state_0 - 1)) == 0 /* is-single-state_0  */) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static GetFrameNode create(String name) {
        return new GetFrameNodeGen(name);
    }

}
