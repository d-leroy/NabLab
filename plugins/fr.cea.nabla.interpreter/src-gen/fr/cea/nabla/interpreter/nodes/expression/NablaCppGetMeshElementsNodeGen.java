// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaCppGetMeshElementsNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaCppGetMeshElementsNode.class)
public final class NablaCppGetMeshElementsNodeGen extends NablaCppGetMeshElementsNode {

    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;

    private NablaCppGetMeshElementsNodeGen(NablaExpressionNode[] argIds, String connectivityName) {
        super(argIds, connectivityName);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active initialize(VirtualFrame) */) {
            assert (initializationRequired);
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
        if ((state & 0b10) != 0 /* is-active doGet(VirtualFrame) */) {
            assert (!(initializationRequired));
            return doGet(frameValue);
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
            if ((exclude) == 0 /* is-not-excluded initialize(VirtualFrame) */) {
                if ((initializationRequired)) {
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
            }
            if ((!(initializationRequired))) {
                this.state_ = state = state | 0b10 /* add-active doGet(VirtualFrame) */;
                lock.unlock();
                hasLock = false;
                return doGet(frameValue);
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

    public static NablaCppGetMeshElementsNode create(NablaExpressionNode[] argIds, String connectivityName) {
        return new NablaCppGetMeshElementsNodeGen(argIds, connectivityName);
    }

}
