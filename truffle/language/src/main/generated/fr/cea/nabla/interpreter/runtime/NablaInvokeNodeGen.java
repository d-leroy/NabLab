// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaInvokeNode.class)
@SuppressWarnings("unused")
public final class NablaInvokeNodeGen extends NablaInvokeNode {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private volatile int exclude_;
    @Child private Default0Data default0_cache;

    private NablaInvokeNodeGen() {
    }

    @ExplodeLoop
    @Override
    public Object execute(Object arg0Value, String arg1Value, Object[] arg2Value) {
        int state_0 = state_0_;
        if (state_0 != 0 /* is-state_0 doDefault(Object, String, Object[], InteropLibrary) || doDefault(Object, String, Object[], InteropLibrary) */) {
            if ((state_0 & 0b1) != 0 /* is-state_0 doDefault(Object, String, Object[], InteropLibrary) */) {
                Default0Data s1_ = this.default0_cache;
                while (s1_ != null) {
                    if ((s1_.objLibrary_.accepts(arg0Value)) && (s1_.objLibrary_.isMemberInvocable(arg0Value, arg1Value))) {
                        return doDefault(arg0Value, arg1Value, arg2Value, s1_.objLibrary_);
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state_0 & 0b10) != 0 /* is-state_0 doDefault(Object, String, Object[], InteropLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        InteropLibrary default1_objLibrary__ = (INTEROP_LIBRARY_.getUncached());
                        if ((default1_objLibrary__.isMemberInvocable(arg0Value, arg1Value))) {
                            return this.default1Boundary(state_0, arg0Value, arg1Value, arg2Value);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value, arg1Value, arg2Value);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object default1Boundary(int state_0, Object arg0Value, String arg1Value, Object[] arg2Value) {
        {
            InteropLibrary default1_objLibrary__ = (INTEROP_LIBRARY_.getUncached());
            return doDefault(arg0Value, arg1Value, arg2Value, default1_objLibrary__);
        }
    }

    private Object executeAndSpecialize(Object arg0Value, String arg1Value, Object[] arg2Value) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-exclude doDefault(Object, String, Object[], InteropLibrary) */) {
                int count1_ = 0;
                Default0Data s1_ = this.default0_cache;
                if ((state_0 & 0b1) != 0 /* is-state_0 doDefault(Object, String, Object[], InteropLibrary) */) {
                    while (s1_ != null) {
                        if ((s1_.objLibrary_.accepts(arg0Value)) && (s1_.objLibrary_.isMemberInvocable(arg0Value, arg1Value))) {
                            break;
                        }
                        s1_ = s1_.next_;
                        count1_++;
                    }
                }
                if (s1_ == null) {
                    {
                        InteropLibrary objLibrary__ = super.insert((INTEROP_LIBRARY_.create(arg0Value)));
                        // assert (s1_.objLibrary_.accepts(arg0Value));
                        if ((objLibrary__.isMemberInvocable(arg0Value, arg1Value)) && count1_ < (3)) {
                            s1_ = super.insert(new Default0Data(default0_cache));
                            s1_.objLibrary_ = s1_.insertAccessor(objLibrary__);
                            this.default0_cache = s1_;
                            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doDefault(Object, String, Object[], InteropLibrary) */;
                        }
                    }
                }
                if (s1_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doDefault(arg0Value, arg1Value, arg2Value, s1_.objLibrary_);
                }
            }
            {
                InteropLibrary default1_objLibrary__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            default1_objLibrary__ = (INTEROP_LIBRARY_.getUncached());
                            if ((default1_objLibrary__.isMemberInvocable(arg0Value, arg1Value))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-exclude doDefault(Object, String, Object[], InteropLibrary) */;
                                this.default0_cache = null;
                                state_0 = state_0 & 0xfffffffe /* remove-state_0 doDefault(Object, String, Object[], InteropLibrary) */;
                                this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doDefault(Object, String, Object[], InteropLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return doDefault(arg0Value, arg1Value, arg2Value, default1_objLibrary__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {null, null, null}, arg0Value, arg1Value, arg2Value);
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
                Default0Data s1_ = this.default0_cache;
                if ((s1_ == null || s1_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaInvokeNode create() {
        return new NablaInvokeNodeGen();
    }

    @GeneratedBy(NablaInvokeNode.class)
    private static final class Default0Data extends Node {

        @Child Default0Data next_;
        @Child InteropLibrary objLibrary_;

        Default0Data(Default0Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
}
