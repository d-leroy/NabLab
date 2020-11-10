// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaExternalASTNode;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaExternalASTNode.class)
public final class NablaExternalASTNodeGen extends NablaExternalASTNode {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @CompilationFinal private volatile int state_;
    @Child private InteropLibrary function_;

    private NablaExternalASTNodeGen(Object member, Object receiver, NablaExpressionNode[] args) {
        super(member, receiver, args);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if (state != 0 /* is-active execute(VirtualFrame, InteropLibrary) */) {
            assert (this.function_.accepts(member));
            return execute(frameValue, this.function_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            // assert (this.function_.accepts(member));
            this.function_ = super.insert((INTEROP_LIBRARY_.create(member)));
            this.state_ = state = state | 0b1 /* add-active execute(VirtualFrame, InteropLibrary) */;
            lock.unlock();
            hasLock = false;
            return execute(frameValue, this.function_);
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
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaExternalASTNode create(Object member, Object receiver, NablaExpressionNode[] args) {
        return new NablaExternalASTNodeGen(member, receiver, args);
    }

}
