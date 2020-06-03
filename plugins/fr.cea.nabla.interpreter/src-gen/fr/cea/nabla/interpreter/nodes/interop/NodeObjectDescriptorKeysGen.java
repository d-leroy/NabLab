// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.interop;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.profiles.BranchProfile;
import fr.cea.nabla.interpreter.nodes.interop.NodeObjectDescriptorKeys;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NodeObjectDescriptorKeys.class)
final class NodeObjectDescriptorKeysGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NodeObjectDescriptorKeys.class, new InteropLibraryExports());
    }

    private NodeObjectDescriptorKeysGen() {
    }

    @GeneratedBy(NodeObjectDescriptorKeys.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, NodeObjectDescriptorKeys.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NodeObjectDescriptorKeys;
            InteropLibrary uncached = new Uncached();
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NodeObjectDescriptorKeys;
            return new Cached();
        }

        @GeneratedBy(NodeObjectDescriptorKeys.class)
        private static final class Cached extends InteropLibrary {

            @CompilationFinal private int state_;
            @CompilationFinal private BranchProfile exception_;

            Cached() {
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NodeObjectDescriptorKeys) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.nodes.interop.NodeObjectDescriptorKeys'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NodeObjectDescriptorKeys;
            }

            @Override
            public boolean hasArrayElements(Object receiver) {
                assert assertAdopted();
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver).hasArrayElements();
            }

            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert assertAdopted();
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver).isArrayElementReadable(index);
            }

            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert assertAdopted();
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver).getArraySize();
            }

            @Override
            public Object readArrayElement(Object arg0Value_, long arg1Value) throws UnsupportedMessageException, InvalidArrayIndexException {
                assert assertAdopted();
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NodeObjectDescriptorKeys arg0Value = (NodeObjectDescriptorKeys) arg0Value_;
                int state = state_;
                if (state != 0 /* is-active readArrayElement(NodeObjectDescriptorKeys, long, BranchProfile) */) {
                    return arg0Value.readArrayElement(arg1Value, this.exception_);
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return executeAndSpecialize(arg0Value, arg1Value);
            }

            private Object executeAndSpecialize(NodeObjectDescriptorKeys arg0Value, long arg1Value) throws InvalidArrayIndexException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state = state_;
                try {
                    this.exception_ = (BranchProfile.create());
                    this.state_ = state = state | 0b1 /* add-active readArrayElement(NodeObjectDescriptorKeys, long, BranchProfile) */;
                    lock.unlock();
                    hasLock = false;
                    return arg0Value.readArrayElement(arg1Value, this.exception_);
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

        }
        @GeneratedBy(NodeObjectDescriptorKeys.class)
        private static final class Uncached extends InteropLibrary {

            Uncached() {
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NodeObjectDescriptorKeys) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.nodes.interop.NodeObjectDescriptorKeys'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NodeObjectDescriptorKeys;
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
            public boolean hasArrayElements(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver) .hasArrayElements();
            }

            @TruffleBoundary
            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver) .isArrayElementReadable(index);
            }

            @TruffleBoundary
            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NodeObjectDescriptorKeys) receiver) .getArraySize();
            }

            @TruffleBoundary
            @Override
            public Object readArrayElement(Object arg0Value_, long arg1Value) throws InvalidArrayIndexException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NodeObjectDescriptorKeys arg0Value = (NodeObjectDescriptorKeys) arg0Value_;
                return arg0Value.readArrayElement(arg1Value, (BranchProfile.getUncached()));
            }

        }
    }
}
