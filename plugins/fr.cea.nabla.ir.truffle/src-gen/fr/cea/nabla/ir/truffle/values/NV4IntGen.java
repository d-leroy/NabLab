// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.values.NV4Int;

@GeneratedBy(NV4Int.class)
final class NV4IntGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NV4Int.class, new InteropLibraryExports());
    }

    private NV4IntGen() {
    }

    @GeneratedBy(NV4Int.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, NV4Int.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NV4Int;
            return new Uncached(receiver);
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NV4Int;
            return new Cached(receiver);
        }

        @GeneratedBy(NV4Int.class)
        private static final class Cached extends InteropLibrary {

            private final Class<? extends NV4Int> receiverClass_;

            Cached(Object receiver) {
                this.receiverClass_ = ((NV4Int) receiver).getClass();
            }

            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.values.NV4Int'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_;
            }

            @Override
            public boolean hasArrayElements(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).hasArrayElements();
            }

            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getArraySize();
            }

            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).isArrayElementReadable(index);
            }

            @Override
            public Object readArrayElement(Object receiver, long index) throws UnsupportedMessageException, InvalidArrayIndexException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).readArrayElement(index);
            }

        }
        @GeneratedBy(NV4Int.class)
        private static final class Uncached extends InteropLibrary {

            private final Class<? extends NV4Int> receiverClass_;

            Uncached(Object receiver) {
                this.receiverClass_ = ((NV4Int) receiver).getClass();
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.values.NV4Int'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_;
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
                return ((NV4Int) receiver) .hasArrayElements();
            }

            @TruffleBoundary
            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV4Int) receiver) .getArraySize();
            }

            @TruffleBoundary
            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV4Int) receiver) .isArrayElementReadable(index);
            }

            @TruffleBoundary
            @Override
            public Object readArrayElement(Object receiver, long index) throws UnsupportedMessageException, InvalidArrayIndexException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV4Int) receiver) .readArrayElement(index);
            }

        }
    }
}
