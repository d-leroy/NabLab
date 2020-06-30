// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.runtime.AbstractMeshLibrary;
import fr.cea.nabla.interpreter.runtime.CartesianMesh2DLibrary;
import fr.cea.nabla.javalib.mesh.Quad;

@GeneratedBy(CartesianMesh2DLibrary.class)
final class CartesianMesh2DLibraryGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(CartesianMesh2DLibrary.class, new AbstractMeshLibraryExports());
    }

    private CartesianMesh2DLibraryGen() {
    }

    @GeneratedBy(CartesianMesh2DLibrary.class)
    private static final class AbstractMeshLibraryExports extends LibraryExport<AbstractMeshLibrary> {

        private AbstractMeshLibraryExports() {
            super(AbstractMeshLibrary.class, CartesianMesh2DLibrary.class, false);
        }

        @Override
        protected AbstractMeshLibrary createUncached(Object receiver) {
            assert receiver instanceof CartesianMesh2DLibrary;
            AbstractMeshLibrary uncached = new Uncached(receiver);
            return uncached;
        }

        @Override
        protected AbstractMeshLibrary createCached(Object receiver) {
            assert receiver instanceof CartesianMesh2DLibrary;
            return new Cached(receiver);
        }

        @GeneratedBy(CartesianMesh2DLibrary.class)
        private static final class Cached extends AbstractMeshLibrary {

            private final Class<? extends CartesianMesh2DLibrary> receiverClass_;

            Cached(Object receiver) {
                this.receiverClass_ = ((CartesianMesh2DLibrary) receiver).getClass();
            }

            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.CartesianMesh2DLibrary'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_;
            }

            @Override
            public void initialize(Object receiver, int nbXQuads, int nbYQuads, double xSize, double ySize) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                (CompilerDirectives.castExact(receiver, receiverClass_)).initialize(nbXQuads, nbYQuads, xSize, ySize);
                return;
            }

            @Override
            public int[] getElements(Object receiver, String connectivityName, int[] args) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getElements(connectivityName, args);
            }

            @Override
            public int getSingleton(Object receiver, String connectivityName, int[] params) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getSingleton(connectivityName, params);
            }

            @Override
            public double[][] getNodes(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getNodes();
            }

            @Override
            public int getNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getNbElems(connectivityName);
            }

            @Override
            public int getMaxNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getMaxNbElems(connectivityName);
            }

            @Override
            public Quad[] getQuads(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).getQuads();
            }

        }
        @GeneratedBy(CartesianMesh2DLibrary.class)
        private static final class Uncached extends AbstractMeshLibrary {

            private final Class<? extends CartesianMesh2DLibrary> receiverClass_;

            Uncached(Object receiver) {
                this.receiverClass_ = ((CartesianMesh2DLibrary) receiver).getClass();
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.CartesianMesh2DLibrary'. Exported receiver with dynamic dispatch found but not expected.";
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
            public void initialize(Object receiver, int nbXQuads, int nbYQuads, double xSize, double ySize) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                ((CartesianMesh2DLibrary) receiver) .initialize(nbXQuads, nbYQuads, xSize, ySize);
                return;
            }

            @TruffleBoundary
            @Override
            public int[] getElements(Object receiver, String connectivityName, int[] args) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getElements(connectivityName, args);
            }

            @TruffleBoundary
            @Override
            public int getSingleton(Object receiver, String connectivityName, int[] params) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getSingleton(connectivityName, params);
            }

            @TruffleBoundary
            @Override
            public double[][] getNodes(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getNodes();
            }

            @TruffleBoundary
            @Override
            public int getNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getNbElems(connectivityName);
            }

            @TruffleBoundary
            @Override
            public int getMaxNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getMaxNbElems(connectivityName);
            }

            @TruffleBoundary
            @Override
            public Quad[] getQuads(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((CartesianMesh2DLibrary) receiver) .getQuads();
            }

        }
    }
}
