// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.CartesianMesh2DWrapper;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.Value;

@GeneratedBy(NablaMeshWrapperCallNode.class)
@SuppressWarnings("unused")
public final class NablaMeshWrapperCallNodeGen extends NablaMeshWrapperCallNode {

    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private ContextReference<NablaContext> nablaLanguageContextReference_;
    @CompilationFinal private ExecuteGenericData executeGeneric_cache;

    private NablaMeshWrapperCallNodeGen(String methodName, NablaExpressionNode[] argumentNodes) {
        super(methodName, argumentNodes);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if (state_0 != 0 /* is-state_0 executeGeneric(VirtualFrame, NablaContext, Assumption, CartesianMesh2DWrapper, Value) */) {
            ExecuteGenericData s1_ = this.executeGeneric_cache;
            if (s1_ != null) {
                if (!Assumption.isValidAssumption(s1_.assumption0_)) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    removeExecuteGeneric_(s1_);
                    return executeAndSpecialize(frameValue);
                }
                {
                    ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                    NablaContext context__ = nablaLanguageContextReference__.get();
                    return executeGeneric(frameValue, context__, s1_.contextActive_, s1_.meshWrapper_, s1_.connectivity_);
                }
            }
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
            {
                NablaContext context__ = null;
                {
                    ContextReference<NablaContext> nablaLanguageContextReference__1 = this.nablaLanguageContextReference_;
                    if (nablaLanguageContextReference__1 == null) {
                        this.nablaLanguageContextReference_ = nablaLanguageContextReference__1 = super.lookupContextReference(NablaLanguage.class);
                    }
                    context__ = nablaLanguageContextReference__1.get();
                    Assumption contextActive__ = (context__.getContextActive());
                    Assumption assumption0 = (contextActive__);
                    if (Assumption.isValidAssumption(assumption0)) {
                        ExecuteGenericData s1_ = new ExecuteGenericData();
                        s1_.contextActive_ = contextActive__;
                        s1_.meshWrapper_ = (context__.getMeshWrapper());
                        s1_.connectivity_ = (s1_.meshWrapper_.connectivityGetter(methodName));
                        s1_.assumption0_ = assumption0;
                        this.executeGeneric_cache = s1_;
                        this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 executeGeneric(VirtualFrame, NablaContext, Assumption, CartesianMesh2DWrapper, Value) */;
                        lock.unlock();
                        hasLock = false;
                        return executeGeneric(frameValue, context__, contextActive__, s1_.meshWrapper_, s1_.connectivity_);
                    }
                }
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
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    void removeExecuteGeneric_(Object s1_) {
        Lock lock = getLock();
        lock.lock();
        try {
            this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 executeGeneric(VirtualFrame, NablaContext, Assumption, CartesianMesh2DWrapper, Value) */;
            this.executeGeneric_cache = null;
        } finally {
            lock.unlock();
        }
    }

    public static NablaMeshWrapperCallNode create(String methodName, NablaExpressionNode[] argumentNodes) {
        return new NablaMeshWrapperCallNodeGen(methodName, argumentNodes);
    }

    @GeneratedBy(NablaMeshWrapperCallNode.class)
    private static final class ExecuteGenericData {

        @CompilationFinal Assumption contextActive_;
        @CompilationFinal CartesianMesh2DWrapper meshWrapper_;
        @CompilationFinal Value connectivity_;
        @CompilationFinal Assumption assumption0_;

        ExecuteGenericData() {
        }

    }
}
