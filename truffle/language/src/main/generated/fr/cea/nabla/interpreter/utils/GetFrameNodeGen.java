// CheckStyle: start generated
package fr.cea.nabla.interpreter.utils;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import java.util.concurrent.locks.Lock;

@GeneratedBy(GetFrameNode.class)
public final class GetFrameNodeGen extends GetFrameNode {

    @CompilationFinal private volatile int state_;
    @CompilationFinal private volatile int exclude_;
    @CompilationFinal private Frame cached_result_;

    private GetFrameNodeGen(String name) {
        super(name);
    }

    @Override
    public Frame execute(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active initialize(VirtualFrame) */) {
            try {
                return initialize(frameValue);
            } catch (NablaInitializationPerformedException ex) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                Lock lock = getLock();
                lock.lock();
                try {
                    this.exclude_ = this.exclude_ | 0b1 /* add-excluded initialize(VirtualFrame) */;
                    this.state_ = this.state_ & 0xfffffffe /* remove-active initialize(VirtualFrame) */;
                } finally {
                    lock.unlock();
                }
                return executeAndSpecialize(frameValue);
            }
        }
        if ((state & 0b10) != 0 /* is-active doLocal(VirtualFrame) */) {
            assert (isLocal);
            return doLocal(frameValue);
        }
        if ((state & 0b100) != 0 /* is-active doCached(VirtualFrame, Frame) */) {
            assert (!(isLocal));
            return doCached(frameValue, this.cached_result_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Frame executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-excluded initialize(VirtualFrame) */) {
                this.state_ = state = state | 0b1 /* add-active initialize(VirtualFrame) */;
                try {
                    lock.unlock();
                    hasLock = false;
                    return initialize(frameValue);
                } catch (NablaInitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-excluded initialize(VirtualFrame) */;
                        this.state_ = this.state_ & 0xfffffffe /* remove-active initialize(VirtualFrame) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue);
                }
            }
            if ((isLocal)) {
                this.state_ = state = state | 0b10 /* add-active doLocal(VirtualFrame) */;
                lock.unlock();
                hasLock = false;
                return doLocal(frameValue);
            }
            if ((!(isLocal))) {
                this.cached_result_ = (getFrame(frameValue));
                this.state_ = state = state | 0b100 /* add-active doCached(VirtualFrame, Frame) */;
                lock.unlock();
                hasLock = false;
                return doCached(frameValue, this.cached_result_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {});
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

    public static GetFrameNode create(String name) {
        return new GetFrameNodeGen(name);
    }

}
