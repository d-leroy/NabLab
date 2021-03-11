// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.local;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
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
import com.oracle.truffle.api.source.SourceSection;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionBlockNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject.ExistsMember;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject.GetMembers;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject.ModifiableMember;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject.ReadMember;
import fr.cea.nabla.interpreter.nodes.local.NablaScopedNode.VariablesObject.WriteMember;
import java.util.concurrent.locks.Lock;

@GeneratedBy(VariablesObject.class)
final class VariablesObjectGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(VariablesObject.class, new InteropLibraryExports());
    }

    private VariablesObjectGen() {
    }

    @GeneratedBy(VariablesObject.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, VariablesObject.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof VariablesObject;
            InteropLibrary uncached = new Uncached();
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof VariablesObject;
            return new Cached(receiver);
        }

        @GeneratedBy(VariablesObject.class)
        private static final class Cached extends InteropLibrary {

            @CompilationFinal private volatile int state_0_;
            @CompilationFinal private volatile int exclude_;
            @CompilationFinal private NablaInstructionBlockNode block;
            @CompilationFinal private Node parentBlock;
            @CompilationFinal private NablaScopedNode acceptsNode__cachedNode_;
            @CompilationFinal private boolean acceptsNode__cachedNodeEnter_;
            @CompilationFinal private ExistsMemberCachedData existsMember_cached_cache;
            @CompilationFinal private ModifiableMemberCachedData modifiableMember_cached_cache;
            @CompilationFinal private ReadMemberCachedData readMember_cached_cache;
            @CompilationFinal private WriteMemberCachedData writeMember_cached_cache;
            @Child private GetMembersBlockData getMembers_block_cache;
            @Child private GetMembersModuleData getMembers_module_cache;

            protected Cached(Object receiver) {
                VariablesObject castReceiver = ((VariablesObject) receiver) ;
                this.acceptsNode__cachedNode_ = (castReceiver.node);
                this.acceptsNode__cachedNodeEnter_ = (castReceiver.nodeEnter);
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof VariablesObject) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof VariablesObject && accepts_(receiver);
            }

            private boolean accepts_(Object arg0Value_) {
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return arg0Value.accepts(this.acceptsNode__cachedNode_, this.acceptsNode__cachedNodeEnter_);
            }

            @Override
            public NodeCost getCost() {
                return NodeCost.MONOMORPHIC;
            }

            @Override
            public boolean isScope(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).isScope();
            }

            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).hasLanguage();
            }

            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).getLanguage();
            }

            @Override
            public Object toDisplayString(Object arg0Value_, boolean arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1) != 0 /* is-state_0 toDisplayString(VariablesObject, boolean, NablaInstructionBlockNode, Node) */) {
                    return arg0Value.toDisplayString(arg1Value, this.block, this.parentBlock);
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return toDisplayStringNode_AndSpecialize(arg0Value, arg1Value);
            }

            private Object toDisplayStringNode_AndSpecialize(VariablesObject arg0Value, boolean arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    if (this.block == null) {
                        this.block = (arg0Value.block);
                    }
                    if (this.parentBlock == null) {
                        this.parentBlock = (arg0Value.block.findBlock());
                    }
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 toDisplayString(VariablesObject, boolean, NablaInstructionBlockNode, Node) */;
                    lock.unlock();
                    hasLock = false;
                    return arg0Value.toDisplayString(arg1Value, this.block, this.parentBlock);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public boolean hasScopeParent(Object arg0Value_) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b10) != 0 /* is-state_0 hasScopeParent(VariablesObject, NablaInstructionBlockNode, Node) */) {
                    return arg0Value.hasScopeParent(this.block, this.parentBlock);
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return hasScopeParentNode_AndSpecialize(arg0Value);
            }

            private boolean hasScopeParentNode_AndSpecialize(VariablesObject arg0Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    if (this.block == null) {
                        this.block = (arg0Value.block);
                    }
                    if (this.parentBlock == null) {
                        this.parentBlock = (arg0Value.block.findBlock());
                    }
                    this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 hasScopeParent(VariablesObject, NablaInstructionBlockNode, Node) */;
                    lock.unlock();
                    hasLock = false;
                    return arg0Value.hasScopeParent(this.block, this.parentBlock);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public Object getScopeParent(Object arg0Value_) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b100) != 0 /* is-state_0 getScopeParent(VariablesObject, NablaInstructionBlockNode, Node) */) {
                    return arg0Value.getScopeParent(this.block, this.parentBlock);
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return getScopeParentNode_AndSpecialize(arg0Value);
            }

            private Object getScopeParentNode_AndSpecialize(VariablesObject arg0Value) throws UnsupportedMessageException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    if (this.block == null) {
                        this.block = (arg0Value.block);
                    }
                    if (this.parentBlock == null) {
                        this.parentBlock = (arg0Value.block.findBlock());
                    }
                    this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 getScopeParent(VariablesObject, NablaInstructionBlockNode, Node) */;
                    lock.unlock();
                    hasLock = false;
                    return arg0Value.getScopeParent(this.block, this.parentBlock);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public boolean hasSourceLocation(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).hasSourceLocation();
            }

            @Override
            public SourceSection getSourceLocation(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).getSourceLocation();
            }

            @Override
            public boolean hasMembers(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                return (((VariablesObject) receiver)).hasMembers();
            }

            @ExplodeLoop
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b11000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) || doGeneric(VariablesObject, String) */) {
                    if ((state_0 & 0b1000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) */) {
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b10000) != 0 /* is-state_0 doGeneric(VariablesObject, String) */) {
                        return ExistsMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return existsMemberAndSpecialize(arg0Value, arg1Value);
            }

            private boolean existsMemberAndSpecialize(VariablesObject arg0Value, String arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1)) == 0 /* is-not-exclude doCached(VariablesObject, String, String, boolean) */) {
                        int count1_ = 0;
                        ExistsMemberCachedData s1_ = this.existsMember_cached_cache;
                        if ((state_0 & 0b1000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) */) {
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
                            if (count1_ < (VariablesObject.LIMIT)) {
                                s1_ = new ExistsMemberCachedData(existsMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.cachedResult_ = (ExistsMember.doGeneric(arg0Value, arg1Value));
                                this.existsMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doCached(VariablesObject, String, String, boolean) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ExistsMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b1 /* add-exclude doCached(VariablesObject, String, String, boolean) */;
                    this.existsMember_cached_cache = null;
                    state_0 = state_0 & 0xfffffff7 /* remove-state_0 doCached(VariablesObject, String, String, boolean) */;
                    this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doGeneric(VariablesObject, String) */;
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
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) || doGeneric(VariablesObject, String) */) {
                    if ((state_0 & 0b100000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) */) {
                        ModifiableMemberCachedData s1_ = this.modifiableMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ModifiableMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b1000000) != 0 /* is-state_0 doGeneric(VariablesObject, String) */) {
                        return ModifiableMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return modifiableMemberAndSpecialize(arg0Value, arg1Value);
            }

            private boolean modifiableMemberAndSpecialize(VariablesObject arg0Value, String arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b10)) == 0 /* is-not-exclude doCached(VariablesObject, String, String, boolean) */) {
                        int count1_ = 0;
                        ModifiableMemberCachedData s1_ = this.modifiableMember_cached_cache;
                        if ((state_0 & 0b100000) != 0 /* is-state_0 doCached(VariablesObject, String, String, boolean) */) {
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
                            if (count1_ < (VariablesObject.LIMIT)) {
                                s1_ = new ModifiableMemberCachedData(modifiableMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.cachedResult_ = (arg0Value.hasWriteNode(arg1Value));
                                this.modifiableMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doCached(VariablesObject, String, String, boolean) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ModifiableMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.cachedResult_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b10 /* add-exclude doCached(VariablesObject, String, String, boolean) */;
                    this.modifiableMember_cached_cache = null;
                    state_0 = state_0 & 0xffffffdf /* remove-state_0 doCached(VariablesObject, String, String, boolean) */;
                    this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 doGeneric(VariablesObject, String) */;
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
                return (((VariablesObject) receiver)).isMemberInsertable(member);
            }

            @ExplodeLoop
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnsupportedMessageException, UnknownIdentifierException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b110000000) != 0 /* is-state_0 doCached(VariablesObject, String, String, FrameSlot) || doGeneric(VariablesObject, String) */) {
                    if ((state_0 & 0b10000000) != 0 /* is-state_0 doCached(VariablesObject, String, String, FrameSlot) */) {
                        ReadMemberCachedData s1_ = this.readMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                return ReadMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.slot_);
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b100000000) != 0 /* is-state_0 doGeneric(VariablesObject, String) */) {
                        return ReadMember.doGeneric(arg0Value, arg1Value);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return readMemberAndSpecialize(arg0Value, arg1Value);
            }

            private Object readMemberAndSpecialize(VariablesObject arg0Value, String arg1Value) throws UnknownIdentifierException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b100)) == 0 /* is-not-exclude doCached(VariablesObject, String, String, FrameSlot) */) {
                        int count1_ = 0;
                        ReadMemberCachedData s1_ = this.readMember_cached_cache;
                        if ((state_0 & 0b10000000) != 0 /* is-state_0 doCached(VariablesObject, String, String, FrameSlot) */) {
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
                            if (count1_ < (VariablesObject.LIMIT)) {
                                s1_ = new ReadMemberCachedData(readMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.slot_ = (arg0Value.findSlot(arg1Value));
                                this.readMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 doCached(VariablesObject, String, String, FrameSlot) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            return ReadMember.doCached(arg0Value, arg1Value, s1_.cachedMember_, s1_.slot_);
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b100 /* add-exclude doCached(VariablesObject, String, String, FrameSlot) */;
                    this.readMember_cached_cache = null;
                    state_0 = state_0 & 0xffffff7f /* remove-state_0 doCached(VariablesObject, String, String, FrameSlot) */;
                    this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 doGeneric(VariablesObject, String) */;
                    lock.unlock();
                    hasLock = false;
                    return ReadMember.doGeneric(arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @ExplodeLoop
            @Override
            public void writeMember(Object arg0Value_, String arg1Value, Object arg2Value) throws UnsupportedMessageException, UnknownIdentifierException, UnsupportedTypeException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b11000000000) != 0 /* is-state_0 doCached(VariablesObject, String, Object, String, FrameSlot) || doUncached(VariablesObject, String, Object) */) {
                    if ((state_0 & 0b1000000000) != 0 /* is-state_0 doCached(VariablesObject, String, Object, String, FrameSlot) */) {
                        WriteMemberCachedData s1_ = this.writeMember_cached_cache;
                        while (s1_ != null) {
                            if ((s1_.cachedMember_.equals(arg1Value))) {
                                WriteMember.doCached(arg0Value, arg1Value, arg2Value, s1_.cachedMember_, s1_.slot_);
                                return;
                            }
                            s1_ = s1_.next_;
                        }
                    }
                    if ((state_0 & 0b10000000000) != 0 /* is-state_0 doUncached(VariablesObject, String, Object) */) {
                        WriteMember.doUncached(arg0Value, arg1Value, arg2Value);
                        return;
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                writeMemberAndSpecialize(arg0Value, arg1Value, arg2Value);
                return;
            }

            private void writeMemberAndSpecialize(VariablesObject arg0Value, String arg1Value, Object arg2Value) throws UnknownIdentifierException, UnsupportedMessageException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                int exclude = exclude_;
                try {
                    if (((exclude & 0b1000)) == 0 /* is-not-exclude doCached(VariablesObject, String, Object, String, FrameSlot) */) {
                        int count1_ = 0;
                        WriteMemberCachedData s1_ = this.writeMember_cached_cache;
                        if ((state_0 & 0b1000000000) != 0 /* is-state_0 doCached(VariablesObject, String, Object, String, FrameSlot) */) {
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
                            if (count1_ < (VariablesObject.LIMIT)) {
                                s1_ = new WriteMemberCachedData(writeMember_cached_cache);
                                s1_.cachedMember_ = (arg1Value);
                                s1_.slot_ = (arg0Value.frame.getFrameDescriptor().findFrameSlot(s1_.cachedMember_));
                                this.writeMember_cached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 doCached(VariablesObject, String, Object, String, FrameSlot) */;
                            }
                        }
                        if (s1_ != null) {
                            lock.unlock();
                            hasLock = false;
                            WriteMember.doCached(arg0Value, arg1Value, arg2Value, s1_.cachedMember_, s1_.slot_);
                            return;
                        }
                    }
                    this.exclude_ = exclude = exclude | 0b1000 /* add-exclude doCached(VariablesObject, String, Object, String, FrameSlot) */;
                    this.writeMember_cached_cache = null;
                    state_0 = state_0 & 0xfffffdff /* remove-state_0 doCached(VariablesObject, String, Object, String, FrameSlot) */;
                    this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 doUncached(VariablesObject, String, Object) */;
                    lock.unlock();
                    hasLock = false;
                    WriteMember.doUncached(arg0Value, arg1Value, arg2Value);
                    return;
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public Object getMembers(Object arg0Value_, boolean arg1Value) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert assertAdopted();
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                int state_0 = state_0_;
                if ((state_0 & 0b1100000000000) != 0 /* is-state_0 doBlock(VariablesObject, boolean, NablaWriteVariableNode[], int, int) || doModule(VariablesObject, boolean, NablaWriteVariableNode[]) */) {
                    if ((state_0 & 0b100000000000) != 0 /* is-state_0 doBlock(VariablesObject, boolean, NablaWriteVariableNode[], int, int) */) {
                        GetMembersBlockData s1_ = this.getMembers_block_cache;
                        if (s1_ != null) {
                            if ((arg0Value.block != null)) {
                                return GetMembers.doBlock(arg0Value, arg1Value, s1_.writeNodes_, s1_.visibleVariablesIndex_, s1_.parentBlockIndex_);
                            }
                        }
                    }
                    if ((state_0 & 0b1000000000000) != 0 /* is-state_0 doModule(VariablesObject, boolean, NablaWriteVariableNode[]) */) {
                        GetMembersModuleData s2_ = this.getMembers_module_cache;
                        if (s2_ != null) {
                            if ((arg0Value.block == null)) {
                                return GetMembers.doModule(arg0Value, arg1Value, s2_.writeNodes_);
                            }
                        }
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return getMembersAndSpecialize(arg0Value, arg1Value);
            }

            private Object getMembersAndSpecialize(VariablesObject arg0Value, boolean arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    if ((arg0Value.block != null)) {
                        GetMembersBlockData s1_ = super.insert(new GetMembersBlockData());
                        s1_.writeNodes_ = (arg0Value.block.getDeclaredLocalVariables());
                        s1_.visibleVariablesIndex_ = (arg0Value.getVisibleVariablesIndex());
                        s1_.parentBlockIndex_ = (arg0Value.block.getParentBlockIndex());
                        this.getMembers_block_cache = s1_;
                        this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 doBlock(VariablesObject, boolean, NablaWriteVariableNode[], int, int) */;
                        lock.unlock();
                        hasLock = false;
                        return GetMembers.doBlock(arg0Value, arg1Value, s1_.writeNodes_, s1_.visibleVariablesIndex_, s1_.parentBlockIndex_);
                    }
                    if ((arg0Value.block == null)) {
                        GetMembersModuleData s2_ = super.insert(new GetMembersModuleData());
                        s2_.writeNodes_ = (arg0Value.module.getDeclaredGlobalVariables());
                        this.getMembers_module_cache = s2_;
                        this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 doModule(VariablesObject, boolean, NablaWriteVariableNode[]) */;
                        lock.unlock();
                        hasLock = false;
                        return GetMembers.doModule(arg0Value, arg1Value, s2_.writeNodes_);
                    }
                    throw new UnsupportedSpecializationException(this, new Node[] {null, null}, arg0Value, arg1Value);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @GeneratedBy(VariablesObject.class)
            private static final class ExistsMemberCachedData {

                @CompilationFinal ExistsMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal boolean cachedResult_;

                ExistsMemberCachedData(ExistsMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(VariablesObject.class)
            private static final class ModifiableMemberCachedData {

                @CompilationFinal ModifiableMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal boolean cachedResult_;

                ModifiableMemberCachedData(ModifiableMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(VariablesObject.class)
            private static final class ReadMemberCachedData {

                @CompilationFinal ReadMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal FrameSlot slot_;

                ReadMemberCachedData(ReadMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(VariablesObject.class)
            private static final class WriteMemberCachedData {

                @CompilationFinal WriteMemberCachedData next_;
                @CompilationFinal String cachedMember_;
                @CompilationFinal FrameSlot slot_;

                WriteMemberCachedData(WriteMemberCachedData next_) {
                    this.next_ = next_;
                }

            }
            @GeneratedBy(VariablesObject.class)
            private static final class GetMembersBlockData extends Node {

                @CompilationFinal(dimensions = 1) NablaWriteVariableNode[] writeNodes_;
                @CompilationFinal int visibleVariablesIndex_;
                @CompilationFinal int parentBlockIndex_;

                GetMembersBlockData() {
                }

                @Override
                public NodeCost getCost() {
                    return NodeCost.NONE;
                }

            }
            @GeneratedBy(VariablesObject.class)
            private static final class GetMembersModuleData extends Node {

                @CompilationFinal(dimensions = 1) NablaWriteVariableNode[] writeNodes_;

                GetMembersModuleData() {
                }

                @Override
                public NodeCost getCost() {
                    return NodeCost.NONE;
                }

            }
        }
        @GeneratedBy(VariablesObject.class)
        private static final class Uncached extends InteropLibrary {

            protected Uncached() {
            }

            @Override
            @TruffleBoundary
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof VariablesObject) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof VariablesObject && accepts_(receiver);
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
                return ((VariablesObject) receiver) .isScope();
            }

            @TruffleBoundary
            @Override
            public boolean hasLanguage(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .hasLanguage();
            }

            @TruffleBoundary
            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .getLanguage();
            }

            @TruffleBoundary
            @Override
            public Object toDisplayString(Object arg0Value_, boolean arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return arg0Value.toDisplayString(arg1Value, (arg0Value.block), (arg0Value.block.findBlock()));
            }

            @TruffleBoundary
            @Override
            public boolean hasScopeParent(Object arg0Value_) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return arg0Value.hasScopeParent((arg0Value.block), (arg0Value.block.findBlock()));
            }

            @TruffleBoundary
            @Override
            public Object getScopeParent(Object arg0Value_) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return arg0Value.getScopeParent((arg0Value.block), (arg0Value.block.findBlock()));
            }

            @TruffleBoundary
            @Override
            public boolean hasSourceLocation(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .hasSourceLocation();
            }

            @TruffleBoundary
            @Override
            public SourceSection getSourceLocation(Object receiver) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .getSourceLocation();
            }

            @TruffleBoundary
            @Override
            public boolean hasMembers(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .hasMembers();
            }

            @TruffleBoundary
            @Override
            public boolean isMemberReadable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return ExistsMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberModifiable(Object arg0Value_, String arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return ModifiableMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public boolean isMemberInsertable(Object receiver, String member) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((VariablesObject) receiver) .isMemberInsertable(member);
            }

            @TruffleBoundary
            @Override
            public Object readMember(Object arg0Value_, String arg1Value) throws UnknownIdentifierException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return ReadMember.doGeneric(arg0Value, arg1Value);
            }

            @TruffleBoundary
            @Override
            public void writeMember(Object arg0Value_, String arg1Value, Object arg2Value) throws UnknownIdentifierException, UnsupportedMessageException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                WriteMember.doUncached(arg0Value, arg1Value, arg2Value);
                return;
            }

            @TruffleBoundary
            @Override
            public Object getMembers(Object arg0Value_, boolean arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                if ((arg0Value.block != null)) {
                    return GetMembers.doBlock(arg0Value, arg1Value, (arg0Value.block.getDeclaredLocalVariables()), (arg0Value.getVisibleVariablesIndex()), (arg0Value.block.getParentBlockIndex()));
                }
                if ((arg0Value.block == null)) {
                    return GetMembers.doModule(arg0Value, arg1Value, (arg0Value.module.getDeclaredGlobalVariables()));
                }
                throw new UnsupportedSpecializationException(this, new Node[] {null, null}, arg0Value, arg1Value);
            }

            @TruffleBoundary
            private static boolean accepts_(Object arg0Value_) {
                VariablesObject arg0Value = ((VariablesObject) arg0Value_);
                return arg0Value.accepts((arg0Value.node), (arg0Value.nodeEnter));
            }

        }
    }
}
