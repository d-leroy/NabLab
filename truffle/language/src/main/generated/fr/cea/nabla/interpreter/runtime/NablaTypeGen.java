// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import fr.cea.nabla.interpreter.runtime.NablaType;
import fr.cea.nabla.interpreter.runtime.NablaType.IsMetaInstance;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaType.class)
@SuppressWarnings("unused")
final class NablaTypeGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);
    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);

    static  {
        LibraryExport.register(NablaType.class, new InteropLibraryExports());
    }

    private NablaTypeGen() {
    }

    @GeneratedBy(NablaType.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, NablaType.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NablaType;
            InteropLibrary uncached = new Uncached();
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NablaType;
            return new Cached();
        }

        @GeneratedBy(NablaType.class)
        private static final class Cached extends InteropLibrary {

            @CompilationFinal private int state_;
            @CompilationFinal private int exclude_;
            @Child private Cached0Data cached0_cache;
            @Child private Cached1Data cached1_cache;

            Cached() {
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NablaType) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.NablaType'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NablaType;
            }

            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).hasLanguage();
            }

            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).getLanguage();
            }

            @Override
            public boolean isMetaObject(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).isMetaObject();
            }

            @Override
            public Object getMetaQualifiedName(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).getName();
            }

            @Override
            public Object getMetaSimpleName(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).getName();
            }

            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((NablaType) receiver)).toDisplayString(allowSideEffects);
            }

            @ExplodeLoop
            @Override
            public boolean isMetaInstance(Object arg0Value_, Object arg1Value) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                NablaType arg0Value = ((NablaType) arg0Value_);
                int state = state_;
                if (state != 0 /* is-active doCached(NablaType, Object, NablaType, InteropLibrary) || doCached(NablaType, Object, NablaType, InteropLibrary) || doGeneric(NablaType, Object) */) {
                    if ((state & 0b1) != 0 /* is-active doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                        Cached0Data s1_ = this.cached0_cache;
                        while (s1_ != null) {
                            if ((s1_.valueLib_.accepts(arg1Value)) && (arg0Value == s1_.cachedType_)) {
                                return IsMetaInstance.doCached(arg0Value, arg1Value, s1_.cachedType_, s1_.valueLib_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state & 0b10) != 0 /* is-active doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                        Cached1Data s2_ = this.cached1_cache;
                        while (s2_ != null) {
                            if ((arg0Value == s2_.cachedType_)) {
                                return this.cached1Boundary(state, s2_, arg0Value, arg1Value);
                            }
                            s2_ = s2_.next_;
                        }
                    }
                    if ((state & 0b100) != 0 /* is-active doGeneric(NablaType, Object) */) {
                        return IsMetaInstance.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return executeAndSpecialize(arg0Value, arg1Value);
            }

            @TruffleBoundary
            private boolean cached1Boundary(int state, Cached1Data s2_, NablaType arg0Value, Object arg1Value) throws UnsupportedMessageException {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    return IsMetaInstance.doCached(arg0Value, arg1Value, s2_.cachedType_, (INTEROP_LIBRARY_.getUncached(arg1Value)));
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }

            private boolean executeAndSpecialize(NablaType arg0Value, Object arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state = state_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1)) == 0 /* is-not-excluded doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                        int count1_ = 0;
                        Cached0Data s1_ = this.cached0_cache;
                        if ((state & 0b1) != 0 /* is-active doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                            while (s1_ != null) {
                                if ((s1_.valueLib_.accepts(arg1Value)) && (arg0Value == s1_.cachedType_)) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            // assert (s1_.valueLib_.accepts(arg1Value));
                            // assert (arg0Value == s1_.cachedType_);
                            if (count1_ < (3)) {
                                s1_ = super.insert(new Cached0Data(cached0_cache));
                                s1_.cachedType_ = (arg0Value);
                                s1_.valueLib_ = s1_.insertAccessor((INTEROP_LIBRARY_.create(arg1Value)));
                                this.cached0_cache = s1_;
                                this.state_ = state = state | 0b1 /* add-active doCached(NablaType, Object, NablaType, InteropLibrary) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return IsMetaInstance.doCached(arg0Value, arg1Value, s1_.cachedType_, s1_.valueLib_);
                        }
                    }
                    if (((exclude & 0b10)) == 0 /* is-not-excluded doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                        {
                            Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                            try {
                                int count2_ = 0;
                                Cached1Data s2_ = this.cached1_cache;
                                if ((state & 0b10) != 0 /* is-active doCached(NablaType, Object, NablaType, InteropLibrary) */) {
                                    while (s2_ != null) {
                                        if ((arg0Value == s2_.cachedType_)) {
                                            break;
                                        }
                                        s2_ = s2_.next_;
                                        count2_++;
                                    }
                                }
                                if (s2_ == null) {
                                    // assert (arg0Value == s2_.cachedType_);
                                    if (count2_ < (3)) {
                                        s2_ = super.insert(new Cached1Data(cached1_cache));
                                        s2_.cachedType_ = (arg0Value);
                                        this.cached1_cache = s2_;
                                        this.exclude_ = exclude = exclude | 0b1 /* add-excluded doCached(NablaType, Object, NablaType, InteropLibrary) */;
                                        this.cached0_cache = null;
                                        state = state & 0xfffffffe /* remove-active doCached(NablaType, Object, NablaType, InteropLibrary) */;
                                        this.state_ = state = state | 0b10 /* add-active doCached(NablaType, Object, NablaType, InteropLibrary) */;
                                    }
                                }
                                if (s2_ != null) {
                                    lock.unlock();
                                    hasLock = false;
                                    return IsMetaInstance.doCached(arg0Value, arg1Value, s2_.cachedType_, (INTEROP_LIBRARY_.getUncached(arg1Value)));
                                }
                            } finally {
                                NodeUtil.popEncapsulatingNode(prev_);
                            }
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b11 /* add-excluded doCached(NablaType, Object, NablaType, InteropLibrary), doCached(NablaType, Object, NablaType, InteropLibrary) */;
                    this.cached0_cache = null;
                    this.cached1_cache = null;
                    state = state & 0xfffffffc /* remove-active doCached(NablaType, Object, NablaType, InteropLibrary), doCached(NablaType, Object, NablaType, InteropLibrary) */;
                    this.state_ = state = state | 0b100 /* add-active doGeneric(NablaType, Object) */;
                    lock.unlock();
                    hasLock = false;
                    return IsMetaInstance.doGeneric(arg0Value, arg1Value);
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
                    Cached0Data s1_ = this.cached0_cache;
                    Cached1Data s2_ = this.cached1_cache;
                    if ((s1_ == null || s1_.next_ == null) && (s2_ == null || s2_.next_ == null)) {
                        return NodeCost.MONOMORPHIC;
                    }
                }
                return NodeCost.POLYMORPHIC;
            }

            @GeneratedBy(NablaType.class)
            private static final class Cached0Data extends Node {

                @Child Cached0Data next_;
                @CompilationFinal NablaType cachedType_;
                @Child InteropLibrary valueLib_;

                Cached0Data(Cached0Data next_) {
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
            @GeneratedBy(NablaType.class)
            private static final class Cached1Data extends Node {

                @Child Cached1Data next_;
                @CompilationFinal NablaType cachedType_;

                Cached1Data(Cached1Data next_) {
                    this.next_ = next_;
                }

                @Override
                public NodeCost getCost() {
                    return NodeCost.NONE;
                }

            }
        }
        @GeneratedBy(NablaType.class)
        private static final class Uncached extends InteropLibrary {

            Uncached() {
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NablaType) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.NablaType'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NablaType;
            }

            @Override
            public boolean isAdoptable() {
                return false;
            }

            @Override
            public NodeCost getCost() {
                return NodeCost.MEGAMORPHIC;
            }

            @TruffleBoundary
            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .hasLanguage();
            }

            @TruffleBoundary
            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .getLanguage();
            }

            @TruffleBoundary
            @Override
            public boolean isMetaObject(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .isMetaObject();
            }

            @TruffleBoundary
            @Override
            public Object getMetaQualifiedName(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .getName();
            }

            @TruffleBoundary
            @Override
            public Object getMetaSimpleName(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .getName();
            }

            @TruffleBoundary
            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaType) receiver) .toDisplayString(allowSideEffects);
            }

            @TruffleBoundary
            @Override
            public boolean isMetaInstance(Object arg0Value_, Object arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaType arg0Value = ((NablaType) arg0Value_);
                return IsMetaInstance.doGeneric(arg0Value, arg1Value);
            }

        }
    }
}
