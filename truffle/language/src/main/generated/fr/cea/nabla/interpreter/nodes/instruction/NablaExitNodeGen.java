// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.profiles.BranchProfile;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaExitNode.class)
public final class NablaExitNodeGen extends NablaExitNode {

    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private BranchProfile exception_;

    private NablaExitNodeGen(String message) {
        super(message);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if (state_0 != 0 /* is-state_0 doDefault(VirtualFrame, BranchProfile) */) {
            return doDefault(frameValue, this.exception_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        try {
            this.exception_ = (BranchProfile.create());
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doDefault(VirtualFrame, BranchProfile) */;
            lock.unlock();
            hasLock = false;
            return doDefault(frameValue, this.exception_);
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
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaExitNode create(String message) {
        return new NablaExitNodeGen(message);
    }

}
