// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.local;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.source.SourceSection;
import fr.cea.nabla.interpreter.nodes.NablaRootNode;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.ArgumentsObject;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.ArgumentsObject.ExistsMember;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.ArgumentsObject.ModifiableMember;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.ArgumentsObject.ReadMember;
import java.util.concurrent.locks.Lock;

@GeneratedBy(ArgumentsObject.class)
final class ArgumentsObjectGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(ArgumentsObject.class, new InteropLibraryExports());
    }

    private ArgumentsObjectGen() {
    }

    @GeneratedBy(ArgumentsObject.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, ArgumentsObject.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof ArgumentsObject;
            InteropLibrary uncached = new Uncached();
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof ArgumentsObject;
            return new Cached(receiver);
        }

        @GeneratedBy(ArgumentsObject.class)
        private static final class Cached extends InteropLibrary {

            @CompilationFinal private volatile int state_0_;
            @CompilationFinal private volatile int exclude_;
            @CompilationFinal private NablaRootNode acceptsNode__cachedRoot_;
            @CompilationFinal private ExistsMemberCachedData existsMember_cached_cache;
            @CompilationFinal private ModifiableMemberCachedData modifiableMember_cached_cache;
            @CompilationFinal private ReadMemberCachedData readMember_cached_cache;

            protected Cached(Object receiver) {
                ArgumentsObject castReceiver = ((ArgumentsObject) receiver) ;
                this.acceptsNode__cachedRoot_ = (castReceiver.root);
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof ArgumentsObject) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof ArgumentsObject && accepts_(receiver);
            }

            private boolean accepts_(Object arg0Value_) {
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                return arg0Value.accepts(this.acceptsNode__cachedRoot_);
            }

            @Override
            public NodeCost getCost() {
                return NodeCost.MONOMORPHIC;
            }

            @Override
            public boolean isScope(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).isScope();
            }

            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).hasLanguage();
            }

            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).getLanguage();
            }

            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).toDisplayString(allowSideEffects);
            }

            @Override
            public boolean hasSourceLocation(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).hasSourceLocation();
            }

            @Override
            public SourceSection getSourceLocation(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).getSourceLocation();
            }

            @Override
            public boolean hasMembers(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).hasMembers();
            }

            @Override
            public Object getMembers(Object receiver, boolean includeInternal) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).getMembers(includeInternal);
            }

            @ExplodeLoop
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b11) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) || doGeneric(ArgumentsObject, String) */) {
                    if ((state_0 & 0b1) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) */) {
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b10) != 0 /* is-state_0 doGeneric(ArgumentsObject, String) */) {
                        return ExistsMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return existsMemberAndSpecialize(arg0Value, arg1Value);
            }

            private boolean existsMemberAndSpecialize(ArgumentsObject arg0Value, String arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1)) == 0 /* is-not-exclude doCached(ArgumentsObject, String, String, boolean) */) {
                        int count1_ = 0;
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        if ((state_0 & 0b1) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) */) {
                            while (s1_ != null) {
                                if ((s1_.cachedMember_.equals(arg1Value))) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            // assert (s1_.cachedMember_.equals(arg1Value));
                            if (count1_ < (ArgumentsObject.LIMIT)) {
                                s1_ = new ExistsMemberCachedData(existsMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.cachedResult_ = (ExistsMember.doGeneric(arg0Value, arg1Value));
                                this.existsMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doCached(ArgumentsObject, String, String, boolean) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b1 /* add-exclude doCached(ArgumentsObject, String, String, boolean) */;
                    this.existsMember_cached_cache = null;
                    state_0 = state_0 & 0xfffffffe /* remove-state_0 doCached(ArgumentsObject, String, String, boolean) */;
                    this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doGeneric(ArgumentsObject, String) */;
                    lock.unlock();
                    hasLock = false;
                    return ExistsMember.doGeneric(arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @ExplodeLoop
            @Override
            public boolean isMemberModifiable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) || doGeneric(ArgumentsObject, String) */) {
                    if ((state_0 & 0b100) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) */) {
                        ModifiableMemberCachedData s1_ = this.modifiableMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ModifiableMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b1000) != 0 /* is-state_0 doGeneric(ArgumentsObject, String) */) {
                        return ModifiableMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return modifiableMemberAndSpecialize(arg0Value, arg1Value);
            }

            private boolean modifiableMemberAndSpecialize(ArgumentsObject arg0Value, String arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b10)) == 0 /* is-not-exclude doCached(ArgumentsObject, String, String, boolean) */) {
                        int count1_ = 0;
                        ModifiableMemberCachedData s1_ = this.modifiableMember_cached_cache;
                        if ((state_0 & 0b100) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, boolean) */) {
                            while (s1_ != null) {
                                if ((s1_.cachedMember_.equals(arg1Value))) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            // assert (s1_.cachedMember_.equals(arg1Value));
                            if (count1_ < (ArgumentsObject.LIMIT)) {
                                s1_ = new ModifiableMemberCachedData(modifiableMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.cachedResult_ = (arg0Value.hasArgumentIndex(arg1Value));
                                this.modifiableMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doCached(ArgumentsObject, String, String, boolean) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ModifiableMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b10 /* add-exclude doCached(ArgumentsObject, String, String, boolean) */;
                    this.modifiableMember_cached_cache = null;
                    state_0 = state_0 & 0xfffffffb /* remove-state_0 doCached(ArgumentsObject, String, String, boolean) */;
                    this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doGeneric(ArgumentsObject, String) */;
                    lock.unlock();
                    hasLock = false;
                    return ModifiableMember.doGeneric(arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public boolean isMemberInsertable(Object receiver, String member) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((ArgumentsObject) receiver)).isMemberInsertable(member);
            }

            @ExplodeLoop
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnsupportedMessageException, UnknownIdentifierException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b110000) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, int) || doGeneric(ArgumentsObject, String) */) {
                    if ((state_0 & 0b10000) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, int) */) {
                        ReadMemberCachedData s1_ = this.readMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ReadMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.index_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b100000) != 0 /* is-state_0 doGeneric(ArgumentsObject, String) */) {
                        return ReadMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return readMemberAndSpecialize(arg0Value, arg1Value);
            }

            private Object readMemberAndSpecialize(ArgumentsObject arg0Value, String arg1Value) throws UnknownIdentifierException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b100)) == 0 /* is-not-exclude doCached(ArgumentsObject, String, String, int) */) {
                        int count1_ = 0;
                        ReadMemberCachedData s1_ = this.readMember_cached_cache;
                        if ((state_0 & 0b10000) != 0 /* is-state_0 doCached(ArgumentsObject, String, String, int) */) {
                            while (s1_ != null) {
                                if ((s1_.cachedMember_.equals(arg1Value))) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            // assert (s1_.cachedMember_.equals(arg1Value));
                            if (count1_ < (ArgumentsObject.LIMIT)) {
                                s1_ = new ReadMemberCachedData(readMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.index_ = (arg0Value.findArgumentIndex(arg1Value));
                                this.readMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doCached(ArgumentsObject, String, String, int) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ReadMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.index_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b100 /* add-exclude doCached(ArgumentsObject, String, String, int) */;
                    this.readMember_cached_cache = null;
                    state_0 = state_0 & 0xffffffef /* remove-state_0 doCached(ArgumentsObject, String, String, int) */;
                    this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doGeneric(ArgumentsObject, String) */;
                    lock.unlock();
                    hasLock = false;
                    return ReadMember.doGeneric(arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public void writeMember(Object receiver, String member, Object value) throws UnsupportedMessageException, UnknownIdentifierException, UnsupportedTypeException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                (((ArgumentsObject) receiver)).writeMember(member, value);
                return;
            }

            @GeneratedBy(ArgumentsObject.class)
            private static final class ExistsMemberCachedData {

                @CompilationFinal ExistsMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal boolean cachedResult_;

                ExistsMemberCachedData(ExistsMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(ArgumentsObject.class)
            private static final class ModifiableMemberCachedData {

                @CompilationFinal ModifiableMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal boolean cachedResult_;

                ModifiableMemberCachedData(ModifiableMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(ArgumentsObject.class)
            private static final class ReadMemberCachedData {

                @CompilationFinal ReadMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal int index_;

                ReadMemberCachedData(ReadMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
        }
        @GeneratedBy(ArgumentsObject.class)
        private static final class Uncached extends InteropLibrary {

            protected Uncached() {
            }

            @Override
            @TruffleBoundary
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof ArgumentsObject) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof ArgumentsObject && accepts_(receiver);
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
            public boolean isScope(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .isScope();
            }

            @TruffleBoundary
            @Override
            public boolean hasLanguage(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .hasLanguage();
            }

            @TruffleBoundary
            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .getLanguage();
            }

            @TruffleBoundary
            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .toDisplayString(allowSideEffects);
            }

            @TruffleBoundary
            @Override
            public boolean hasSourceLocation(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .hasSourceLocation();
            }

            @TruffleBoundary
            @Override
            public SourceSection getSourceLocation(Object receiver) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .getSourceLocation();
            }

            @TruffleBoundary
            @Override
            public boolean hasMembers(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .hasMembers();
            }

            @TruffleBoundary
            @Override
            public Object getMembers(Object receiver, boolean includeInternal) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .getMembers(includeInternal);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                return ExistsMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberModifiable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                return ModifiableMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberInsertable(Object receiver, String member) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((ArgumentsObject) receiver) .isMemberInsertable(member);
            }

            @TruffleBoundary
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnknownIdentifierException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                return ReadMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public void writeMember(Object receiver, String member, Object value) throws UnsupportedMessageException, UnknownIdentifierException, UnsupportedTypeException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                ((ArgumentsObject) receiver) .writeMember(member, value);
                return;
            }

            @TruffleBoundary
            private static boolean accepts_(Object arg0Value_) {
                ArgumentsObject arg0Value = ((ArgumentsObject) arg0Value_);
                return arg0Value.accepts((arg0Value.root));
            }

        }
    }
}
