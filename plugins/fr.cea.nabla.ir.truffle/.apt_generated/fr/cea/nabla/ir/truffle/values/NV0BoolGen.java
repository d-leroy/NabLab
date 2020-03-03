// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.values.NV0Bool;

@GeneratedBy(NV0Bool.class)
final class NV0BoolGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NV0Bool.class, new InteropLibraryExports());
    }

    private NV0BoolGen() {
    }

    @GeneratedBy(NV0Bool.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, NV0Bool.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NV0Bool;
            return new Uncached(receiver);
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NV0Bool;
            return new Cached(receiver);
        }

        @GeneratedBy(NV0Bool.class)
        private static final class Cached extends InteropLibrary {

            private final Class<? extends NV0Bool> receiverClass_;

            Cached(Object receiver) {
                this.receiverClass_ = ((NV0Bool) receiver).getClass();
            }

            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.values.NV0Bool'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_;
            }

            @Override
            public boolean isBoolean(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).isBoolean();
            }

            @Override
            public boolean asBoolean(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).asBoolean();
            }

        }
        @GeneratedBy(NV0Bool.class)
        private static final class Uncached extends InteropLibrary {

            private final Class<? extends NV0Bool> receiverClass_;

            Uncached(Object receiver) {
                this.receiverClass_ = ((NV0Bool) receiver).getClass();
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.values.NV0Bool'. Exported receiver with dynamic dispatch found but not expected.";
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
            public boolean isBoolean(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Bool) receiver) .isBoolean();
            }

            @TruffleBoundary
            @Override
            public boolean asBoolean(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Bool) receiver) .asBoolean();
            }

        }
    }
}
