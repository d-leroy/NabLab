// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Location;
import com.oracle.truffle.api.object.Shape;
import fr.cea.nabla.interpreter.runtime.NablaObjectType.ExistsMember;
import fr.cea.nabla.interpreter.runtime.NablaObjectType.GetMembers;
import fr.cea.nabla.interpreter.runtime.NablaObjectType.Keys;
import fr.cea.nabla.interpreter.runtime.NablaObjectType.ReadMember;
import fr.cea.nabla.interpreter.runtime.NablaObjectType.WriteMember;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaObjectType.class)
final class NablaObjectTypeGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NablaObjectType.class, new InteropLibraryExports());
    }

    private NablaObjectTypeGen() {
    }

    @GeneratedBy(NablaObjectType.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, DynamicObject.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof DynamicObject;
            InteropLibrary uncached = new Uncached(receiver);
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof DynamicObject;
            return new Cached(receiver);
        }

        @GeneratedBy(NablaObjectType.class)
        private static final class Cached extends InteropLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;
            @CompilationFinal private volatile int state_0_;
            @CompilationFinal private volatile int exclude_;
            @CompilationFinal private GetMembersCachedData getMembers_cached_cache;
            @CompilationFinal private ExistsMemberCachedData existsMember_cached_cache;
            @CompilationFinal private ReadMemberReadCachedData readMember_readCached_cache;
            @CompilationFinal private WriteMemberWriteExistingPropertyCachedData writeMember_writeExistingPropertyCached_cache;
            @CompilationFinal private WriteMemberWriteNewPropertyCachedData writeMember_writeNewPropertyCached_cache;

            protected Cached(Object receiver) {
                DynamicObject castReceiver = ((DynamicObject) receiver) ;
                this.dynamicDispatch_ = insert(DYNAMIC_DISPATCH_LIBRARY_.create(castReceiver));
            }

            @Override
            public boolean accepts(Object receiver) {
                return dynamicDispatch_.accepts(receiver) && dynamicDispatch_.dispatch(receiver) == NablaObjectType.class;
            }

            @Override
            public boolean hasMembers(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return NablaObjectType.hasMembers(((DynamicObject) dynamicDispatch_.cast(receiver)));
            }

            @Override
            public void removeMember(Object receiver, String member) throws UnsupportedMessageException, UnknownIdentifierException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                NablaObjectType.removeMember(((DynamicObject) dynamicDispatch_.cast(receiver)), member);
                return;
            }

            @ExplodeLoop
            @Override
            public Object getMembers(Object arg0Value_, boolean arg1Value) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b11) != 0 /* is-state_0 doCached(DynamicObject, boolean, Shape, Keys) || doGeneric(DynamicObject, boolean) */) {
                    if ((state_0 & 0b1) != 0 /* is-state_0 doCached(DynamicObject, boolean, Shape, Keys) */) {
                        GetMembersCachedData s1_ = this.getMembers_cached_cache;
                        while (s1_ != null) {
                            if ((arg0Value.getShape() == s1_.cachedShape_)) {
                                return GetMembers.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedKeys_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b10) != 0 /* is-state_0 doGeneric(DynamicObject, boolean) */) {
                        return GetMembers.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return getMembersAndSpecialize(arg0Value, arg1Value);
            }

            private Keys getMembersAndSpecialize(DynamicObject arg0Value, boolean arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1)) == 0 /* is-not-exclude doCached(DynamicObject, boolean, Shape, Keys) */) {
                        int count1_ = 0;
                        GetMembersCachedData s1_ = this.getMembers_cached_cache;
                        if ((state_0 & 0b1) != 0 /* is-state_0 doCached(DynamicObject, boolean, Shape, Keys) */) {
                            while (s1_ != null) {
                                if ((arg0Value.getShape() == s1_.cachedShape_)) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            {
                                Shape cachedShape__ = (arg0Value.getShape());
                                if ((arg0Value.getShape() == cachedShape__) && count1_ < (3)) {
                                    s1_ = new GetMembersCachedData(getMembers_cached_cache);
                                    s1_.cachedShape_ = cachedShape__;
                                    s1_.cachedKeys_ = (GetMembers.doGeneric(arg0Value, arg1Value));
                                    this.getMembers_cached_cache = s1_;
                                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doCached(DynamicObject, boolean, Shape, Keys) */;
                                }
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return GetMembers.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedKeys_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b1 /* add-exclude doCached(DynamicObject, boolean, Shape, Keys) */;
                    this.getMembers_cached_cache = null;
                    state_0 = state_0 & 0xfffffffe /* remove-state_0 doCached(DynamicObject, boolean, Shape, Keys) */;
                    this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doGeneric(DynamicObject, boolean) */;
                    lock.unlock();
                    hasLock = false;
                    return GetMembers.doGeneric(arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public NodeCost getCost() {
                int state_0 = state_0_;
                if ((state_0 & 0b11) == 0) {
                    return NodeCost.UNINITIALIZED;
                } else {
                    if (((state_0 & 0b11) & ((state_0 & 0b11) - 1)) == 0 /* is-single-state_0  */) {
                        GetMembersCachedData s1_ = this.getMembers_cached_cache;
                        if ((s1_ == null || s1_.next_ == null)) {
                            return NodeCost.MONOMORPHIC;
                        }
                    }
                }
                return NodeCost.POLYMORPHIC;
            }

            @ExplodeLoop
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) || doGeneric(DynamicObject, String) */) {
                    if ((state_0 & 0b100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) */) {
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        while (s1_ != null) {
                            if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedMember_.equals(arg1Value))) {
                                return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b1000) != 0 /* is-state_0 doGeneric(DynamicObject, String) */) {
                        return ExistsMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return existsMemberAndSpecialize(arg0Value, arg1Value);
            }

            private boolean existsMemberAndSpecialize(DynamicObject arg0Value, String arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b10)) == 0 /* is-not-exclude doCached(DynamicObject, String, Shape, String, boolean) */) {
                        int count1_ = 0;
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        if ((state_0 & 0b100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) */) {
                            while (s1_ != null) {
                                if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedMember_.equals(arg1Value))) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            {
                                Shape cachedShape__ = (arg0Value.getShape());
                                if ((arg0Value.getShape() == cachedShape__)) {
                                    // assert (s1_.cachedMember_.equals(arg1Value));
                                    if (count1_ < (3)) {
                                        s1_ = new ExistsMemberCachedData(existsMember_cached_cache);
                                        s1_.cachedShape_ = cachedShape__;
                                        s1_.cachedMember_ = (arg1Value);
                                        s1_.cachedResult_ = (ExistsMember.doGeneric(arg0Value, arg1Value));
                                        this.existsMember_cached_cache = s1_;
                                        this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doCached(DynamicObject, String, Shape, String, boolean) */;
                                    }
                                }
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedMember_, s1_.cachedResult_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b10 /* add-exclude doCached(DynamicObject, String, Shape, String, boolean) */;
                    this.existsMember_cached_cache = null;
                    state_0 = state_0 & 0xfffffffb /* remove-state_0 doCached(DynamicObject, String, Shape, String, boolean) */;
                    this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doGeneric(DynamicObject, String) */;
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
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) || doGeneric(DynamicObject, String) */) {
                    if ((state_0 & 0b100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) */) {
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        while (s1_ != null) {
                            if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedMember_.equals(arg1Value))) {
                                return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b1000) != 0 /* is-state_0 doGeneric(DynamicObject, String) */) {
                        return ExistsMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return existsMemberAndSpecialize(arg0Value, arg1Value);
            }

            @ExplodeLoop
            @Override
            public boolean isMemberRemovable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) || doGeneric(DynamicObject, String) */) {
                    if ((state_0 & 0b100) != 0 /* is-state_0 doCached(DynamicObject, String, Shape, String, boolean) */) {
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        while (s1_ != null) {
                            if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedMember_.equals(arg1Value))) {
                                return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedShape_, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b1000) != 0 /* is-state_0 doGeneric(DynamicObject, String) */) {
                        return ExistsMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return existsMemberAndSpecialize(arg0Value, arg1Value);
            }

            @Override
            public boolean isMemberInsertable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                {
                    InteropLibrary isMemberInsertableNode__receivers__ = (this);
                    return NablaObjectType.isMemberInsertable(arg0Value, arg1Value, isMemberInsertableNode__receivers__);
                }
            }

            @ExplodeLoop
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnsupportedMessageException, UnknownIdentifierException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1110000) != 0 /* is-state_0 readCached(DynamicObject, String, String, Shape, Location) || readUncached(DynamicObject, String) || updateShape(DynamicObject, String) */) {
                    if ((state_0 & 0b10000) != 0 /* is-state_0 readCached(DynamicObject, String, String, Shape, Location) */) {
                        ReadMemberReadCachedData s1_ = this.readMember_readCached_cache;
                        while (s1_ != null) {
                            if (!Assumption.isValidAssumption(s1_.assumption0_)) {
                                CompilerDirectives.transferToInterpreterAndInvalidate();
                                removeReadCached_(s1_);
                                return readMemberAndSpecialize(arg0Value, arg1Value);
                            }
                            if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedName_.equals(arg1Value))) {
                                return ReadMember.readCached(arg0Value, arg1Value, s1_.cachedName_, s1_.cachedShape_, s1_.location_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b100000) != 0 /* is-state_0 readUncached(DynamicObject, String) */) {
                        if ((arg0Value.getShape().isValid())) {
                            return ReadMember.readUncached(arg0Value, arg1Value);
                        }
                    }
                    if ((state_0 & 0b1000000) != 0 /* is-state_0 updateShape(DynamicObject, String) */) {
                        if ((!(arg0Value.getShape().isValid()))) {
                            return ReadMember.updateShape(arg0Value, arg1Value);
                        }
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return readMemberAndSpecialize(arg0Value, arg1Value);
            }

            private Object readMemberAndSpecialize(DynamicObject arg0Value, String arg1Value) throws UnknownIdentifierException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b100)) == 0 /* is-not-exclude readCached(DynamicObject, String, String, Shape, Location) */) {
                        int count1_ = 0;
                        ReadMemberReadCachedData s1_ = this.readMember_readCached_cache;
                        if ((state_0 & 0b10000) != 0 /* is-state_0 readCached(DynamicObject, String, String, Shape, Location) */) {
                            while (s1_ != null) {
                                if ((arg0Value.getShape() == s1_.cachedShape_) && (s1_.cachedName_.equals(arg1Value)) && (s1_.assumption0_ == null || Assumption.isValidAssumption(s1_.assumption0_))) {
                                    break;
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            {
                                Shape cachedShape__ = (arg0Value.getShape());
                                if ((arg0Value.getShape() == cachedShape__)) {
                                    // assert (s1_.cachedName_.equals(arg1Value));
                                    Assumption assumption0 = (cachedShape__.getValidAssumption());
                                    if (Assumption.isValidAssumption(assumption0)) {
                                        if (count1_ < (NablaObjectType.CACHE_LIMIT)) {
                                            s1_ = new ReadMemberReadCachedData(readMember_readCached_cache);
                                            s1_.cachedName_ = (arg1Value);
                                            s1_.cachedShape_ = cachedShape__;
                                            s1_.location_ = (ReadMember.lookupLocation(cachedShape__, arg1Value));
                                            s1_.assumption0_ = assumption0;
                                            this.readMember_readCached_cache = s1_;
                                            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 readCached(DynamicObject, String, String, Shape, Location) */;
                                        }
                                    }
                                }
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ReadMember.readCached(arg0Value, arg1Value, s1_.cachedName_, s1_.cachedShape_, s1_.location_);
                        }
                    }
                    if ((arg0Value.getShape().isValid())) {
                        this.exclude_ = exclude = exclude | 0b100 /* add-exclude readCached(DynamicObject, String, String, Shape, Location) */;
                        this.readMember_readCached_cache = null;
                        state_0 = state_0 & 0xffffffef /* remove-state_0 readCached(DynamicObject, String, String, Shape, Location) */;
                        this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 readUncached(DynamicObject, String) */;
                        lock.unlock();
                        hasLock = false;
                        return ReadMember.readUncached(arg0Value, arg1Value);
                    }
                    if ((!(arg0Value.getShape().isValid()))) {
                        this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 updateShape(DynamicObject, String) */;
                        lock.unlock();
                        hasLock = false;
                        return ReadMember.updateShape(arg0Value, arg1Value);
                    }
                    throw new UnsupportedSpecializationException(this, new Node[] {null, null}, arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            void removeReadCached_(Object s1_) {
                Lock lock = getLock();
                lock.lock();
                try {
                    ReadMemberReadCachedData prev = null;
                    ReadMemberReadCachedData cur = this.readMember_readCached_cache;
                    while (cur != null) {
                        if (cur == s1_) {
                            if (prev == null) {
                                this.readMember_readCached_cache = cur.next_;
                            } else {
                                prev.next_ = cur.next_;
                            }
                            break;
                        }
                        prev = cur;
                        cur = cur.next_;
                    }
                    if (this.readMember_readCached_cache == null) {
                        this.state_0_ = this.state_0_ & 0xffffffef /* remove-state_0 readCached(DynamicObject, String, String, Shape, Location) */;
                    }
                } finally {
                    lock.unlock();
                }
            }

            @ExplodeLoop
            @Override
            public void writeMember(Object arg0Value_, String arg1Value, Object arg2Value) throws UnsupportedMessageException, UnknownIdentifierException, UnsupportedTypeException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                DynamicObject arg0Value = (DynamicObject) dynamicDispatch_.cast(arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b11110000000) != 0 /* is-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) || writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) || writeUncached(DynamicObject, String, Object) || updateShape(DynamicObject, String, Object) */) {
                    if ((state_0 & 0b10000000) != 0 /* is-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) */) {
                        WriteMemberWriteExistingPropertyCachedData s1_ = this.writeMember_writeExistingPropertyCached_cache;
                        while (s1_ != null) {
                            if (!Assumption.isValidAssumption(s1_.assumption0_)) {
                                CompilerDirectives.transferToInterpreterAndInvalidate();
                                removeWriteExistingPropertyCached_(s1_);
                                writeMemberAndSpecialize(arg0Value, arg1Value, arg2Value);
                                return;
                            }
                            if ((s1_.cachedName_.equals(arg1Value)) && (NablaObjectType.shapeCheck(s1_.shape_, arg0Value))) {
                                assert (s1_.location_ != null);
                                if ((WriteMember.canSet(s1_.location_, arg2Value))) {
                                    WriteMember.writeExistingPropertyCached(arg0Value, arg1Value, arg2Value, s1_.cachedName_, s1_.shape_, s1_.location_);
                                    return;
                                }
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b100000000) != 0 /* is-state_0 writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */) {
                        WriteMemberWriteNewPropertyCachedData s2_ = this.writeMember_writeNewPropertyCached_cache;
                        while (s2_ != null) {
                            if (!Assumption.isValidAssumption(s2_.assumption0_) || !Assumption.isValidAssumption(s2_.assumption1_)) {
                                CompilerDirectives.transferToInterpreterAndInvalidate();
                                removeWriteNewPropertyCached_(s2_);
                                writeMemberAndSpecialize(arg0Value, arg1Value, arg2Value);
                                return;
                            }
                            if ((s2_.cachedName_.equals(arg1Value)) && (arg0Value.getShape() == s2_.oldShape_)) {
                                assert (s2_.oldLocation_ == null);
                                if ((WriteMember.canStore(s2_.newLocation_, arg2Value))) {
                                    WriteMember.writeNewPropertyCached(arg0Value, arg1Value, arg2Value, s2_.cachedName_, s2_.oldShape_, s2_.oldLocation_, s2_.newShape_, s2_.newLocation_);
                                    return;
                                }
                            }
                            s2_ = s2_.next_;
                        }
                    }
                    if ((state_0 & 0b1000000000) != 0 /* is-state_0 writeUncached(DynamicObject, String, Object) */) {
                        if ((arg0Value.getShape().isValid())) {
                            WriteMember.writeUncached(arg0Value, arg1Value, arg2Value);
                            return;
                        }
                    }
                    if ((state_0 & 0b10000000000) != 0 /* is-state_0 updateShape(DynamicObject, String, Object) */) {
                        if ((!(arg0Value.getShape().isValid()))) {
                            WriteMember.updateShape(arg0Value, arg1Value, arg2Value);
                            return;
                        }
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                writeMemberAndSpecialize(arg0Value, arg1Value, arg2Value);
                return;
            }

            private void writeMemberAndSpecialize(DynamicObject arg0Value, String arg1Value, Object arg2Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1000)) == 0 /* is-not-exclude writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) */) {
                        int count1_ = 0;
                        WriteMemberWriteExistingPropertyCachedData s1_ = this.writeMember_writeExistingPropertyCached_cache;
                        if ((state_0 & 0b10000000) != 0 /* is-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) */) {
                            while (s1_ != null) {
                                if ((s1_.cachedName_.equals(arg1Value)) && (NablaObjectType.shapeCheck(s1_.shape_, arg0Value))) {
                                    assert (s1_.location_ != null);
                                    if ((WriteMember.canSet(s1_.location_, arg2Value)) && (s1_.assumption0_ == null || Assumption.isValidAssumption(s1_.assumption0_))) {
                                        break;
                                    }
                                }
                                s1_ = s1_.next_;
                                count1_++;
                            }
                        }
                        if (s1_ == null) {
                            {
                                Shape shape__ = (arg0Value.getShape());
                                // assert (s1_.cachedName_.equals(arg1Value));
                                if ((NablaObjectType.shapeCheck(shape__, arg0Value))) {
                                    Location location__ = (WriteMember.lookupLocation(shape__, arg1Value, arg2Value));
                                    if ((location__ != null) && (WriteMember.canSet(location__, arg2Value))) {
                                        Assumption assumption0 = (shape__.getValidAssumption());
                                        if (Assumption.isValidAssumption(assumption0)) {
                                            if (count1_ < (NablaObjectType.CACHE_LIMIT)) {
                                                s1_ = new WriteMemberWriteExistingPropertyCachedData(writeMember_writeExistingPropertyCached_cache);
                                                s1_.cachedName_ = (arg1Value);
                                                s1_.shape_ = shape__;
                                                s1_.location_ = location__;
                                                s1_.assumption0_ = assumption0;
                                                this.writeMember_writeExistingPropertyCached_cache = s1_;
                                                this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) */;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            WriteMember.writeExistingPropertyCached(arg0Value, arg1Value, arg2Value, s1_.cachedName_, s1_.shape_, s1_.location_);
                            return;
                        }
                    }
                    if (((exclude & 0b10000)) == 0 /* is-not-exclude writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */) {
                        int count2_ = 0;
                        WriteMemberWriteNewPropertyCachedData s2_ = this.writeMember_writeNewPropertyCached_cache;
                        if ((state_0 & 0b100000000) != 0 /* is-state_0 writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */) {
                            while (s2_ != null) {
                                if ((s2_.cachedName_.equals(arg1Value)) && (arg0Value.getShape() == s2_.oldShape_)) {
                                    assert (s2_.oldLocation_ == null);
                                    if ((WriteMember.canStore(s2_.newLocation_, arg2Value)) && (s2_.assumption0_ == null || Assumption.isValidAssumption(s2_.assumption0_)) && (s2_.assumption1_ == null || Assumption.isValidAssumption(s2_.assumption1_))) {
                                        break;
                                    }
                                }
                                s2_ = s2_.next_;
                                count2_++;
                            }
                        }
                        if (s2_ == null) {
                            {
                                Shape oldShape__ = (arg0Value.getShape());
                                // assert (s2_.cachedName_.equals(arg1Value));
                                if ((arg0Value.getShape() == oldShape__)) {
                                    Location oldLocation__ = (WriteMember.lookupLocation(oldShape__, arg1Value, arg2Value));
                                    if ((oldLocation__ == null)) {
                                        Shape newShape__ = (WriteMember.defineProperty(oldShape__, arg1Value, arg2Value));
                                        Location newLocation__ = (WriteMember.lookupLocation(newShape__, arg1Value));
                                        if ((WriteMember.canStore(newLocation__, arg2Value))) {
                                            Assumption assumption0 = (oldShape__.getValidAssumption());
                                            if (Assumption.isValidAssumption(assumption0)) {
                                                Assumption assumption1 = (newShape__.getValidAssumption());
                                                if (Assumption.isValidAssumption(assumption1)) {
                                                    if (count2_ < (NablaObjectType.CACHE_LIMIT)) {
                                                        s2_ = new WriteMemberWriteNewPropertyCachedData(writeMember_writeNewPropertyCached_cache);
                                                        s2_.cachedName_ = (arg1Value);
                                                        s2_.oldShape_ = oldShape__;
                                                        s2_.oldLocation_ = oldLocation__;
                                                        s2_.newShape_ = newShape__;
                                                        s2_.newLocation_ = newLocation__;
                                                        s2_.assumption0_ = assumption0;
                                                        s2_.assumption1_ = assumption1;
                                                        this.writeMember_writeNewPropertyCached_cache = s2_;
                                                        this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (s2_ != null) {
                            lock.unlock();
                            hasLock = false;
                            WriteMember.writeNewPropertyCached(arg0Value, arg1Value, arg2Value, s2_.cachedName_, s2_.oldShape_, s2_.oldLocation_, s2_.newShape_, s2_.newLocation_);
                            return;
                        }
                    }
                    if ((arg0Value.getShape().isValid())) {
                        this.exclude_ = exclude = exclude | 0b11000 /* add-exclude writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location), writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */;
                        this.writeMember_writeExistingPropertyCached_cache = null;
                        this.writeMember_writeNewPropertyCached_cache = null;
                        state_0 = state_0 & 0xfffffe7f /* remove-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location), writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */;
                        this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 writeUncached(DynamicObject, String, Object) */;
                        lock.unlock();
                        hasLock = false;
                        WriteMember.writeUncached(arg0Value, arg1Value, arg2Value);
                        return;
                    }
                    if ((!(arg0Value.getShape().isValid()))) {
                        this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 updateShape(DynamicObject, String, Object) */;
                        lock.unlock();
                        hasLock = false;
                        WriteMember.updateShape(arg0Value, arg1Value, arg2Value);
                        return;
                    }
                    throw new UnsupportedSpecializationException(this, new Node[] {null, null, null}, arg0Value, arg1Value, arg2Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            void removeWriteExistingPropertyCached_(Object s1_) {
                Lock lock = getLock();
                lock.lock();
                try {
                    WriteMemberWriteExistingPropertyCachedData prev = null;
                    WriteMemberWriteExistingPropertyCachedData cur = this.writeMember_writeExistingPropertyCached_cache;
                    while (cur != null) {
                        if (cur == s1_) {
                            if (prev == null) {
                                this.writeMember_writeExistingPropertyCached_cache = cur.next_;
                            } else {
                                prev.next_ = cur.next_;
                            }
                            break;
                        }
                        prev = cur;
                        cur = cur.next_;
                    }
                    if (this.writeMember_writeExistingPropertyCached_cache == null) {
                        this.state_0_ = this.state_0_ & 0xffffff7f /* remove-state_0 writeExistingPropertyCached(DynamicObject, String, Object, String, Shape, Location) */;
                    }
                } finally {
                    lock.unlock();
                }
            }

            void removeWriteNewPropertyCached_(Object s2_) {
                Lock lock = getLock();
                lock.lock();
                try {
                    WriteMemberWriteNewPropertyCachedData prev = null;
                    WriteMemberWriteNewPropertyCachedData cur = this.writeMember_writeNewPropertyCached_cache;
                    while (cur != null) {
                        if (cur == s2_) {
                            if (prev == null) {
                                this.writeMember_writeNewPropertyCached_cache = cur.next_;
                            } else {
                                prev.next_ = cur.next_;
                            }
                            break;
                        }
                        prev = cur;
                        cur = cur.next_;
                    }
                    if (this.writeMember_writeNewPropertyCached_cache == null) {
                        this.state_0_ = this.state_0_ & 0xfffffeff /* remove-state_0 writeNewPropertyCached(DynamicObject, String, Object, Object, Shape, Location, Shape, Location) */;
                    }
                } finally {
                    lock.unlock();
                }
            }

            @GeneratedBy(NablaObjectType.class)
            private static final class GetMembersCachedData {

                @CompilationFinal GetMembersCachedData next_;
                @CompilationFinal Shape cachedShape_;
                @CompilationFinal Keys cachedKeys_;

                GetMembersCachedData(GetMembersCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(NablaObjectType.class)
            private static final class ExistsMemberCachedData {

                @CompilationFinal ExistsMemberCachedData next_;
                @CompilationFinal Shape cachedShape_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal boolean cachedResult_;

                ExistsMemberCachedData(ExistsMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(NablaObjectType.class)
            private static final class ReadMemberReadCachedData {

                @CompilationFinal ReadMemberReadCachedData next_;
                @CompilationFinal String cachedName_;
                @CompilationFinal Shape cachedShape_;
                @CompilationFinal Location location_;
                @CompilationFinal Assumption assumption0_;

                ReadMemberReadCachedData(ReadMemberReadCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(NablaObjectType.class)
            private static final class WriteMemberWriteExistingPropertyCachedData {

                @CompilationFinal WriteMemberWriteExistingPropertyCachedData next_;
                @CompilationFinal String cachedName_;
                @CompilationFinal Shape shape_;
                @CompilationFinal Location location_;
                @CompilationFinal Assumption assumption0_;

                WriteMemberWriteExistingPropertyCachedData(WriteMemberWriteExistingPropertyCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(NablaObjectType.class)
            private static final class WriteMemberWriteNewPropertyCachedData {

                @CompilationFinal WriteMemberWriteNewPropertyCachedData next_;
                @CompilationFinal Object cachedName_;
                @CompilationFinal Shape oldShape_;
                @CompilationFinal Location oldLocation_;
                @CompilationFinal Shape newShape_;
                @CompilationFinal Location newLocation_;
                @CompilationFinal Assumption assumption0_;
                @CompilationFinal Assumption assumption1_;

                WriteMemberWriteNewPropertyCachedData(WriteMemberWriteNewPropertyCachedData next_) {
                    this.next_ = next_;
                }

            }
        }
        @GeneratedBy(NablaObjectType.class)
        private static final class Uncached extends InteropLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;

            protected Uncached(Object receiver) {
                this.dynamicDispatch_ = DYNAMIC_DISPATCH_LIBRARY_.getUncached(receiver);
            }

            @Override
            @TruffleBoundary
            public boolean accepts(Object receiver) {
                return dynamicDispatch_.accepts(receiver) && dynamicDispatch_.dispatch(receiver) == NablaObjectType.class;
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
            public boolean hasMembers(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return NablaObjectType.hasMembers(((DynamicObject) receiver) );
            }

            @TruffleBoundary
            @Override
            public void removeMember(Object receiver, String member) throws UnsupportedMessageException, UnknownIdentifierException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                NablaObjectType.removeMember(((DynamicObject) receiver) , member);
                return;
            }

            @TruffleBoundary
            @Override
            public Object getMembers(Object arg0Value_, boolean arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                return GetMembers.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                return ExistsMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberModifiable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                return ExistsMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberRemovable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                return ExistsMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberInsertable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                return NablaObjectType.isMemberInsertable(arg0Value, arg1Value, (this));
            }

            @TruffleBoundary
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnknownIdentifierException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                if ((arg0Value.getShape().isValid())) {
                    return ReadMember.readUncached(arg0Value, arg1Value);
                }
                if ((!(arg0Value.getShape().isValid()))) {
                    return ReadMember.updateShape(arg0Value, arg1Value);
                }
                throw new UnsupportedSpecializationException(this, new Node[] {null, null}, arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public void writeMember(Object arg0Value_, String arg1Value, Object arg2Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                DynamicObject arg0Value = ((DynamicObject) arg0Value_);
                if ((arg0Value.getShape().isValid())) {
                    WriteMember.writeUncached(arg0Value, arg1Value, arg2Value);
                    return;
                }
                if ((!(arg0Value.getShape().isValid()))) {
                    WriteMember.updateShape(arg0Value, arg1Value, arg2Value);
                    return;
                }
                throw new UnsupportedSpecializationException(this, new Node[] {null, null, null}, arg0Value, arg1Value, arg2Value);
            }

        }
    }
}
