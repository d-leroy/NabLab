// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.library.Message;
import com.oracle.truffle.api.library.ReflectionLibrary;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import com.oracle.truffle.api.utilities.FinalBitSet;
import fr.cea.nabla.interpreter.runtime.AbstractMeshLibrary;
import fr.cea.nabla.javalib.mesh.Quad;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.concurrent.locks.Lock;

@GeneratedBy(AbstractMeshLibrary.class)
final class AbstractMeshLibraryGen extends LibraryFactory<AbstractMeshLibrary> {

    private static final Class<AbstractMeshLibrary> LIBRARY_CLASS = AbstractMeshLibraryGen.lazyLibraryClass();
    private static final Message INITIALIZE = new MessageImpl("initialize", 0, void.class, Object.class, int.class, int.class, double.class, double.class);
    private static final Message GET_ELEMENTS = new MessageImpl("getElements", 1, int[].class, Object.class, String.class, int[].class);
    private static final Message GET_SINGLETON = new MessageImpl("getSingleton", 2, int.class, Object.class, String.class, int[].class);
    private static final Message GET_NODES = new MessageImpl("getNodes", 3, double[][].class, Object.class);
    private static final Message GET_NB_ELEMS = new MessageImpl("getNbElems", 4, int.class, Object.class, String.class);
    private static final Message GET_MAX_NB_ELEMS = new MessageImpl("getMaxNbElems", 5, int.class, Object.class, String.class);
    private static final Message GET_QUADS = new MessageImpl("getQuads", 6, Quad[].class, Object.class);
    private static final AbstractMeshLibraryGen INSTANCE = new AbstractMeshLibraryGen();
    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(AbstractMeshLibraryGen.LIBRARY_CLASS, new Default());
        LibraryFactory.register(AbstractMeshLibraryGen.LIBRARY_CLASS, INSTANCE);
    }

    private AbstractMeshLibraryGen() {
        super(AbstractMeshLibraryGen.LIBRARY_CLASS, Collections.unmodifiableList(Arrays.asList(AbstractMeshLibraryGen.INITIALIZE, AbstractMeshLibraryGen.GET_ELEMENTS, AbstractMeshLibraryGen.GET_SINGLETON, AbstractMeshLibraryGen.GET_NODES, AbstractMeshLibraryGen.GET_NB_ELEMS, AbstractMeshLibraryGen.GET_MAX_NB_ELEMS, AbstractMeshLibraryGen.GET_QUADS)));
    }

    @Override
    protected Class<?> getDefaultClass(Object receiver) {
        return AbstractMeshLibrary.class;
    }

    @Override
    protected AbstractMeshLibrary createProxy(ReflectionLibrary library) {
        return new Proxy(library);
    }

    @Override
    protected FinalBitSet createMessageBitSet(Message... messages) {
        BitSet bitSet = new BitSet(2);
        for (Message message : messages) {
            bitSet.set(((MessageImpl) message).index);
        }
        return FinalBitSet.valueOf(bitSet);
    }

    @Override
    protected AbstractMeshLibrary createDelegate(AbstractMeshLibrary delegateLibrary) {
        return new Delegate(delegateLibrary);
    }

    @Override
    protected Object genericDispatch(Library originalLib, Object receiver, Message message, Object[] args, int offset) throws Exception {
        AbstractMeshLibrary lib = (AbstractMeshLibrary) originalLib;
        MessageImpl messageImpl = (MessageImpl) message;
        if (messageImpl.getParameterCount() - 1 != args.length - offset) {
            CompilerDirectives.transferToInterpreter();
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        switch (messageImpl.index) {
            case 0 :
                lib.initialize(receiver, (int) args[offset], (int) args[offset + 1], (double) args[offset + 2], (double) args[offset + 3]);
                return null;
            case 1 :
                return lib.getElements(receiver, (String) args[offset], (int[]) args[offset + 1]);
            case 2 :
                return lib.getSingleton(receiver, (String) args[offset], (int[]) args[offset + 1]);
            case 3 :
                return lib.getNodes(receiver);
            case 4 :
                return lib.getNbElems(receiver, (String) args[offset]);
            case 5 :
                return lib.getMaxNbElems(receiver, (String) args[offset]);
            case 6 :
                return lib.getQuads(receiver);
        }
        CompilerDirectives.transferToInterpreter();
        throw new AbstractMethodError(message.toString());
    }

    @Override
    protected AbstractMeshLibrary createDispatchImpl(int limit) {
        return new CachedDispatchFirst(null, null, limit);
    }

    @Override
    protected AbstractMeshLibrary createUncachedDispatch() {
        return new UncachedDispatch();
    }

    @SuppressWarnings("unchecked")
    private static Class<AbstractMeshLibrary> lazyLibraryClass() {
        try {
            return (Class<AbstractMeshLibrary>) Class.forName("fr.cea.nabla.interpreter.runtime.AbstractMeshLibrary", false, AbstractMeshLibraryGen.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class Default extends LibraryExport<AbstractMeshLibrary> {

        private Default() {
            super(AbstractMeshLibrary.class, Object.class, false);
        }

        @Override
        protected AbstractMeshLibrary createUncached(Object receiver) {
            AbstractMeshLibrary uncached = new Uncached(receiver);
            return uncached;
        }

        @Override
        protected AbstractMeshLibrary createCached(Object receiver) {
            return new Cached(receiver);
        }

        @GeneratedBy(AbstractMeshLibrary.class)
        private static final class Cached extends AbstractMeshLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;
            private final Class<?> dynamicDispatchTarget_;

            Cached(Object receiver) {
                this.dynamicDispatch_ = insert(DYNAMIC_DISPATCH_LIBRARY_.create(receiver));
                this.dynamicDispatchTarget_ = DYNAMIC_DISPATCH_LIBRARY_.getUncached(receiver).dispatch(receiver);
            }

            @Override
            public boolean accepts(Object receiver) {
                return dynamicDispatch_.accepts(receiver) && dynamicDispatch_.dispatch(receiver) == dynamicDispatchTarget_;
            }

            @Override
            public void initialize(Object receiver, int nbXQuads, int nbYQuads, double xSize, double ySize) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public int[] getElements(Object receiver, String connectivityName, int[] args) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public int getSingleton(Object receiver, String connectivityName, int[] params) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public double[][] getNodes(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public int getNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public int getMaxNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @Override
            public Quad[] getQuads(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

        }
        @GeneratedBy(AbstractMeshLibrary.class)
        private static final class Uncached extends AbstractMeshLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;
            private final Class<?> dynamicDispatchTarget_;

            Uncached(Object receiver) {
                this.dynamicDispatch_ = DYNAMIC_DISPATCH_LIBRARY_.getUncached(receiver);
                this.dynamicDispatchTarget_ = dynamicDispatch_.dispatch(receiver);
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                return dynamicDispatch_.accepts(receiver) && dynamicDispatch_.dispatch(receiver) == dynamicDispatchTarget_;
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
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int[] getElements(Object receiver, String connectivityName, int[] args) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int getSingleton(Object receiver, String connectivityName, int[] params) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public double[][] getNodes(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int getNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int getMaxNbElems(Object receiver, String connectivityName) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public Quad[] getQuads(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

        }
    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static class MessageImpl extends Message {

        final int index;

        MessageImpl(String name, int index, Class<?> returnType, Class<?>... parameters) {
            super(AbstractMeshLibraryGen.LIBRARY_CLASS, name, returnType, parameters);
            this.index = index;
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class Proxy extends AbstractMeshLibrary {

        @Child private ReflectionLibrary lib;

        Proxy(ReflectionLibrary lib) {
            this.lib = lib;
        }

        @Override
        public void initialize(Object receiver_, int nbXQuads, int nbYQuads, double xSize, double ySize) {
            try {
                lib.send(receiver_, AbstractMeshLibraryGen.INITIALIZE, nbXQuads, nbYQuads, xSize, ySize);
                return;
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public int[] getElements(Object receiver_, String connectivityName, int[] args) {
            try {
                return (int[]) lib.send(receiver_, AbstractMeshLibraryGen.GET_ELEMENTS, connectivityName, args);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public int getSingleton(Object receiver_, String connectivityName, int[] params) {
            try {
                return (int) lib.send(receiver_, AbstractMeshLibraryGen.GET_SINGLETON, connectivityName, params);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public double[][] getNodes(Object receiver_) {
            try {
                return (double[][]) lib.send(receiver_, AbstractMeshLibraryGen.GET_NODES);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public int getNbElems(Object receiver_, String connectivityName) {
            try {
                return (int) lib.send(receiver_, AbstractMeshLibraryGen.GET_NB_ELEMS, connectivityName);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public int getMaxNbElems(Object receiver_, String connectivityName) {
            try {
                return (int) lib.send(receiver_, AbstractMeshLibraryGen.GET_MAX_NB_ELEMS, connectivityName);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public Quad[] getQuads(Object receiver_) {
            try {
                return (Quad[]) lib.send(receiver_, AbstractMeshLibraryGen.GET_QUADS);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                CompilerDirectives.transferToInterpreter();
                throw new AssertionError(e_);
            }
        }

        @Override
        public boolean accepts(Object receiver_) {
            return lib.accepts(receiver_);
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class Delegate extends AbstractMeshLibrary {

        @Child private AbstractMeshLibrary delegateLibrary;

        Delegate(AbstractMeshLibrary delegateLibrary) {
            this.delegateLibrary = delegateLibrary;
        }

        @Override
        public void initialize(Object receiver_, int nbXQuads, int nbYQuads, double xSize, double ySize) {
            if (LibraryFactory.isDelegated(delegateLibrary, 0)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).initialize(delegate, nbXQuads, nbYQuads, xSize, ySize);
                return;
            } else {
                this.delegateLibrary.initialize(receiver_, nbXQuads, nbYQuads, xSize, ySize);
                return;
            }
        }

        @Override
        public int[] getElements(Object receiver_, String connectivityName, int[] args) {
            if (LibraryFactory.isDelegated(delegateLibrary, 1)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getElements(delegate, connectivityName, args);
            } else {
                return this.delegateLibrary.getElements(receiver_, connectivityName, args);
            }
        }

        @Override
        public int getSingleton(Object receiver_, String connectivityName, int[] params) {
            if (LibraryFactory.isDelegated(delegateLibrary, 2)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getSingleton(delegate, connectivityName, params);
            } else {
                return this.delegateLibrary.getSingleton(receiver_, connectivityName, params);
            }
        }

        @Override
        public double[][] getNodes(Object receiver_) {
            if (LibraryFactory.isDelegated(delegateLibrary, 3)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getNodes(delegate);
            } else {
                return this.delegateLibrary.getNodes(receiver_);
            }
        }

        @Override
        public int getNbElems(Object receiver_, String connectivityName) {
            if (LibraryFactory.isDelegated(delegateLibrary, 4)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getNbElems(delegate, connectivityName);
            } else {
                return this.delegateLibrary.getNbElems(receiver_, connectivityName);
            }
        }

        @Override
        public int getMaxNbElems(Object receiver_, String connectivityName) {
            if (LibraryFactory.isDelegated(delegateLibrary, 5)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getMaxNbElems(delegate, connectivityName);
            } else {
                return this.delegateLibrary.getMaxNbElems(receiver_, connectivityName);
            }
        }

        @Override
        public Quad[] getQuads(Object receiver_) {
            if (LibraryFactory.isDelegated(delegateLibrary, 6)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).getQuads(delegate);
            } else {
                return this.delegateLibrary.getQuads(receiver_);
            }
        }

        @Override
        public boolean accepts(Object receiver_) {
            return delegateLibrary.accepts(receiver_);
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        @Override
        public boolean isAdoptable() {
            return this.delegateLibrary.isAdoptable();
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class CachedToUncachedDispatch extends AbstractMeshLibrary {

        @Override
        public NodeCost getCost() {
            return NodeCost.MEGAMORPHIC;
        }

        @TruffleBoundary
        @Override
        public void initialize(Object receiver_, int nbXQuads, int nbYQuads, double xSize, double ySize) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                INSTANCE.getUncached(receiver_).initialize(receiver_, nbXQuads, nbYQuads, xSize, ySize);
                return;
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int[] getElements(Object receiver_, String connectivityName, int[] args) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getElements(receiver_, connectivityName, args);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int getSingleton(Object receiver_, String connectivityName, int[] params) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getSingleton(receiver_, connectivityName, params);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public double[][] getNodes(Object receiver_) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getNodes(receiver_);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int getNbElems(Object receiver_, String connectivityName) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getNbElems(receiver_, connectivityName);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int getMaxNbElems(Object receiver_, String connectivityName) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getMaxNbElems(receiver_, connectivityName);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public Quad[] getQuads(Object receiver_) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            Node prev_ = NodeUtil.pushEncapsulatingNode(getParent());
            try {
                return INSTANCE.getUncached(receiver_).getQuads(receiver_);
            } finally {
                NodeUtil.popEncapsulatingNode(prev_);
            }
        }

        @Override
        public boolean accepts(Object receiver_) {
            return true;
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class UncachedDispatch extends AbstractMeshLibrary {

        @Override
        public NodeCost getCost() {
            return NodeCost.MEGAMORPHIC;
        }

        @TruffleBoundary
        @Override
        public void initialize(Object receiver_, int nbXQuads, int nbYQuads, double xSize, double ySize) {
            INSTANCE.getUncached(receiver_).initialize(receiver_, nbXQuads, nbYQuads, xSize, ySize);
            return;
        }

        @TruffleBoundary
        @Override
        public int[] getElements(Object receiver_, String connectivityName, int[] args) {
            return INSTANCE.getUncached(receiver_).getElements(receiver_, connectivityName, args);
        }

        @TruffleBoundary
        @Override
        public int getSingleton(Object receiver_, String connectivityName, int[] params) {
            return INSTANCE.getUncached(receiver_).getSingleton(receiver_, connectivityName, params);
        }

        @TruffleBoundary
        @Override
        public double[][] getNodes(Object receiver_) {
            return INSTANCE.getUncached(receiver_).getNodes(receiver_);
        }

        @TruffleBoundary
        @Override
        public int getNbElems(Object receiver_, String connectivityName) {
            return INSTANCE.getUncached(receiver_).getNbElems(receiver_, connectivityName);
        }

        @TruffleBoundary
        @Override
        public int getMaxNbElems(Object receiver_, String connectivityName) {
            return INSTANCE.getUncached(receiver_).getMaxNbElems(receiver_, connectivityName);
        }

        @TruffleBoundary
        @Override
        public Quad[] getQuads(Object receiver_) {
            return INSTANCE.getUncached(receiver_).getQuads(receiver_);
        }

        @TruffleBoundary
        @Override
        public boolean accepts(Object receiver_) {
            return true;
        }

        @Override
        public boolean isAdoptable() {
            return false;
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class CachedDispatchNext extends CachedDispatch {

        CachedDispatchNext(AbstractMeshLibrary library, CachedDispatch next) {
            super(library, next);
        }

        @Override
        int getLimit() {
            throw new AssertionError();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private static final class CachedDispatchFirst extends CachedDispatch {

        private final int limit_;

        CachedDispatchFirst(AbstractMeshLibrary library, CachedDispatch next, int limit_) {
            super(library, next);
            this.limit_ = limit_;
        }

        @Override
        int getLimit() {
            return this.limit_;
        }

        @Override
        public NodeCost getCost() {
            if (this.library instanceof CachedToUncachedDispatch) {
                return NodeCost.MEGAMORPHIC;
            }
            CachedDispatch current = this;
            int count = 0;
            do {
                if (current.library != null) {
                    count++;
                }
                current = current.next;
            } while (current != null);
            return NodeCost.fromCount(count);
        }

    }
    @GeneratedBy(AbstractMeshLibrary.class)
    private abstract static class CachedDispatch extends AbstractMeshLibrary {

        @Child AbstractMeshLibrary library;
        @Child CachedDispatch next;

        CachedDispatch(AbstractMeshLibrary library, CachedDispatch next) {
            this.library = library;
            this.next = next;
        }

        abstract int getLimit();

        @ExplodeLoop
        @Override
        public void initialize(Object receiver_, int nbXQuads, int nbYQuads, double xSize, double ySize) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        thisLibrary.initialize(receiver_, nbXQuads, nbYQuads, xSize, ySize);
                        return;
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int[] getElements(Object receiver_, String connectivityName, int[] args) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getElements(receiver_, connectivityName, args);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int getSingleton(Object receiver_, String connectivityName, int[] params) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getSingleton(receiver_, connectivityName, params);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public double[][] getNodes(Object receiver_) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getNodes(receiver_);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int getNbElems(Object receiver_, String connectivityName) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getNbElems(receiver_, connectivityName);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int getMaxNbElems(Object receiver_, String connectivityName) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getMaxNbElems(receiver_, connectivityName);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public Quad[] getQuads(Object receiver_) {
            do {
                CachedDispatch current = this;
                do {
                    AbstractMeshLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.getQuads(receiver_);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @Override
        public boolean accepts(Object receiver_) {
            return true;
        }

        private void specialize(Object receiver_) {
            CachedDispatch current = this;
            AbstractMeshLibrary thisLibrary = current.library;
            if (thisLibrary == null) {
                this.library = insert(INSTANCE.create(receiver_));
            } else {
                Lock lock = getLock();
                lock.lock();
                try {
                    int count = 0;
                    do {
                        AbstractMeshLibrary currentLibrary = current.library;
                        if (currentLibrary != null && currentLibrary.accepts(receiver_)) {
                            return;
                        }
                        count++;
                        current = current.next;
                    } while (current != null);
                    if (count >= getLimit()) {
                        this.library = insert(new CachedToUncachedDispatch());
                        this.next = null;
                    } else {
                        this.next = insert(new CachedDispatchNext(INSTANCE.create(receiver_), next));
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

    }
}
