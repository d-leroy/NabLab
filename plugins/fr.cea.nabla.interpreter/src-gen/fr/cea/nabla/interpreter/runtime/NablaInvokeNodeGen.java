// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import fr.cea.nabla.interpreter.runtime.NablaInvokeNode;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaInvokeNode.class)
public final class NablaInvokeNodeGen extends NablaInvokeNode {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private Default0Data default0_cache;

    private NablaInvokeNodeGen() {
    }

    @ExplodeLoop
    @Override
    public Object execute(Object arg0Value, String arg1Value, Object[] arg2Value) {
        int state = state_;
        if (state != 0 /* is-active doDefault(Object, String, Object[], InteropLibrary) || doDefault(Object, String, Object[], InteropLibrary) */) {
            if ((state & 0b1) != 0 /* is-active doDefault(Object, String, Object[], InteropLibrary) */) {
                Default0Data s1_ = this.default0_cache;
                while (s1_ != null) {
                    if ((s1_.objLibrary_.accepts(arg0Value)) && (s1_.objLibrary_.hasMembers(arg0Value))) {
                        return doDefault(arg0Value, arg1Value, arg2Value, s1_.objLibrary_);
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state & 0b10) != 0 /* is-active doDefault(Object, String, Object[], InteropLibrary) */) {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if (((INTEROP_LIBRARY_.getUncached(arg0Value)).hasMembers(arg0Value))) {
                        return doDefault(arg0Value, arg1Value, arg2Value, (INTEROP_LIBRARY_.getUncached(arg0Value)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value, arg1Value, arg2Value);
    }

    private Object executeAndSpecialize(Object arg0Value, String arg1Value, Object[] arg2Value) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-excluded doDefault(Object, String, Object[], InteropLibrary) */) {
                int count1_ = 0;
                Default0Data s1_ = this.default0_cache;
                if ((state & 0b1) != 0 /* is-active doDefault(Object, String, Object[], InteropLibrary) */) {
                    while (s1_ != null) {
                        if ((s1_.objLibrary_.accepts(arg0Value)) && (s1_.objLibrary_.hasMembers(arg0Value))) {
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
                        if ((objLibrary__.hasMembers(arg0Value)) && count1_ < (3)) {
                            s1_ = super.insert(new Default0Data(default0_cache));
                            s1_.objLibrary_ = s1_.insertAccessor(objLibrary__);
                            this.default0_cache = s1_;
                            this.state_ = state = state | 0b1 /* add-active doDefault(Object, String, Object[], InteropLibrary) */;
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
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    {
                        InteropLibrary default1_objLibrary__ = (INTEROP_LIBRARY_.getUncached(arg0Value));
                        if ((default1_objLibrary__.hasMembers(arg0Value))) {
                            this.exclude_ = exclude = exclude | 0b1 /* add-excluded doDefault(Object, String, Object[], InteropLibrary) */;
                            this.default0_cache = null;
                            state = state & 0xfffffffe /* remove-active doDefault(Object, String, Object[], InteropLibrary) */;
                            this.state_ = state = state | 0b10 /* add-active doDefault(Object, String, Object[], InteropLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return doDefault(arg0Value, arg1Value, arg2Value, default1_objLibrary__);
                        }
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
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
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Default0Data s1_ = this.default0_cache;
            if ((s1_ == null || s1_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
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
