// CheckStyle: start generated
package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.NablaOutput;

@GeneratedBy(NablaOutput.class)
final class NablaOutputGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NablaOutput.class, new InteropLibraryExports());
    }

    private NablaOutputGen() {
    }

    @GeneratedBy(NablaOutput.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private InteropLibraryExports() {
            super(InteropLibrary.class, NablaOutput.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NablaOutput;
            return new Uncached(receiver);
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NablaOutput;
            return new Cached(receiver);
        }

        @GeneratedBy(NablaOutput.class)
        private static final class Cached extends InteropLibrary {

            private final Class<? extends NablaOutput> receiverClass_;

            Cached(Object receiver) {
                this.receiverClass_ = ((NablaOutput) receiver).getClass();
            }

            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.NablaOutput'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_;
            }

        }
        @GeneratedBy(NablaOutput.class)
        private static final class Uncached extends InteropLibrary {

            private final Class<? extends NablaOutput> receiverClass_;

            Uncached(Object receiver) {
                this.receiverClass_ = ((NablaOutput) receiver).getClass();
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.ir.truffle.NablaOutput'. Exported receiver with dynamic dispatch found but not expected.";
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

        }
    }
}
