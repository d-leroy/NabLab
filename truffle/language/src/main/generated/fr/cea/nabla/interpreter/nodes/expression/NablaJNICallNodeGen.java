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
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.runtime.NablaProviderObject;
import java.util.concurrent.locks.Lock;
import org.graalvm.polyglot.Value;

@GeneratedBy(NablaJNICallNode.class)
@SuppressWarnings("unused")
public final class NablaJNICallNodeGen extends NablaJNICallNode {

    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private ContextReference<NablaContext> nablaLanguageContextReference_;
    @CompilationFinal private CachedData cached_cache;

    private NablaJNICallNodeGen(NablaProviderObject providerObject, String memberName, NablaExpressionNode[] args) {
        super(providerObject, memberName, args);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        if (state_0 != 0 /* is-state_0 doCached(VirtualFrame, NablaContext, Value, Value, Assumption) */) {
            CachedData s1_ = this.cached_cache;
            if (s1_ != null) {
                if (!Assumption.isValidAssumption(s1_.assumption0_)) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    removeCached_(s1_);
                    return executeAndSpecialize(frameValue);
                }
                {
                    ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                    NablaContext context__ = nablaLanguageContextReference__.get();
                    return doCached(frameValue, context__, s1_.facadeObject_, s1_.methodMember_, s1_.contextActive_);
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
                        CachedData s1_ = new CachedData();
                        s1_.facadeObject_ = (providerObject.initialize());
                        s1_.methodMember_ = (getMember(s1_.facadeObject_));
                        s1_.contextActive_ = contextActive__;
                        s1_.assumption0_ = assumption0;
                        this.cached_cache = s1_;
                        this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doCached(VirtualFrame, NablaContext, Value, Value, Assumption) */;
                        lock.unlock();
                        hasLock = false;
                        return doCached(frameValue, context__, s1_.facadeObject_, s1_.methodMember_, contextActive__);
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

    void removeCached_(Object s1_) {
        Lock lock = getLock();
        lock.lock();
        try {
            this.state_0_ = this.state_0_ & 0xfffffffe /* remove-state_0 doCached(VirtualFrame, NablaContext, Value, Value, Assumption) */;
            this.cached_cache = null;
        } finally {
            lock.unlock();
        }
    }

    public static NablaJNICallNode create(NablaProviderObject providerObject, String memberName, NablaExpressionNode[] args) {
        return new NablaJNICallNodeGen(providerObject, memberName, args);
    }

    @GeneratedBy(NablaJNICallNode.class)
    private static final class CachedData {

        @CompilationFinal Value facadeObject_;
        @CompilationFinal Value methodMember_;
        @CompilationFinal Assumption contextActive_;
        @CompilationFinal Assumption assumption0_;

        CachedData() {
        }

    }
}
