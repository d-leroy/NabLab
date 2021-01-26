// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaReadVariableNode.class)
public final class NablaReadVariableNodeGen extends NablaReadVariableNode {

    @Child private GetFrameNode frameToRead_;
    @CompilationFinal private int state_0_;
    @CompilationFinal private int exclude_;

    private NablaReadVariableNodeGen(String name, GetFrameNode frameToRead) {
        super(name);
        this.frameToRead_ = frameToRead;
    }

    private NablaReadVariableNodeGen(GetFrameNode frameToRead) {
        this.frameToRead_ = frameToRead;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Frame frameToReadValue_ = this.frameToRead_.execute(frameValue);
        if (state_0 != 0 /* is-state_0 initialize(VirtualFrame, Frame) || doRead(VirtualFrame, Frame) */) {
            if ((state_0 & 0b1) != 0 /* is-state_0 initialize(VirtualFrame, Frame) */) {
                assert (slot == null);
                try {
                    return initialize(frameValue, frameToReadValue_);
                } catch (NablaInitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    Lock lock = getLock();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-exclude initialize(VirtualFrame, Frame) */;
                        this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 initialize(VirtualFrame, Frame) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue, frameToReadValue_);
                }
            }
            if ((state_0 & 0b10) != 0 /* is-state_0 doRead(VirtualFrame, Frame) */) {
                return doRead(frameValue, frameToReadValue_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, frameToReadValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Frame frameToReadValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-exclude initialize(VirtualFrame, Frame) */) {
                if ((slot == null)) {
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 initialize(VirtualFrame, Frame) */;
                    try {
                        lock.unlock();
                        hasLock = false;
                        return initialize(frameValue, frameToReadValue);
                    } catch (NablaInitializationPerformedException ex) {
                        CompilerDirectives.transferToInterpreterAndInvalidate();
                        lock.lock();
                        try {
                            this.exclude_ = this.exclude_ | 0b1 /* add-exclude initialize(VirtualFrame, Frame) */;
                            this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 initialize(VirtualFrame, Frame) */;
                        } finally {
                            lock.unlock();
                        }
                        return executeAndSpecialize(frameValue, frameToReadValue);
                    }
                }
            }
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doRead(VirtualFrame, Frame) */;
            lock.unlock();
            hasLock = false;
            return doRead(frameValue, frameToReadValue);
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

    public static NablaReadVariableNode create(String name, GetFrameNode frameToRead) {
        return new NablaReadVariableNodeGen(name, frameToRead);
    }

    public static NablaReadVariableNode create(GetFrameNode frameToRead) {
        return new NablaReadVariableNodeGen(frameToRead);
    }

}
