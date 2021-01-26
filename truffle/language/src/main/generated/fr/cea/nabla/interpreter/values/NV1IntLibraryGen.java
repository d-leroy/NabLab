// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.library.Message;
import com.oracle.truffle.api.library.ReflectionLibrary;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.utilities.FinalBitSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NV1IntLibrary.class)
final class NV1IntLibraryGen extends LibraryFactory<NV1IntLibrary> {

    private static final Class<NV1IntLibrary> LIBRARY_CLASS = NV1IntLibraryGen.lazyLibraryClass();
    private static final Message IS_ARRAY = new MessageImpl("isArray", 0, boolean.class, Object.class);
    private static final Message READ = new MessageImpl("read", 1, int.class, Object.class, int.class);
    private static final Message LENGTH = new MessageImpl("length", 2, int.class, Object.class);
    private static final NV1IntLibraryGen INSTANCE = new NV1IntLibraryGen();
    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NV1IntLibraryGen.LIBRARY_CLASS, new Default());
        LibraryFactory.register(NV1IntLibraryGen.LIBRARY_CLASS, INSTANCE);
    }

    private NV1IntLibraryGen() {
        super(NV1IntLibraryGen.LIBRARY_CLASS, Collections.unmodifiableList(Arrays.asList(NV1IntLibraryGen.IS_ARRAY, NV1IntLibraryGen.READ, NV1IntLibraryGen.LENGTH)));
    }

    @Override
    protected Class<?> getDefaultClass(Object receiver) {
        return NV1IntLibrary.class;
    }

    @Override
    protected NV1IntLibrary createProxy(ReflectionLibrary library) {
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
    protected NV1IntLibrary createDelegate(NV1IntLibrary delegateLibrary) {
        return new Delegate(delegateLibrary);
    }

    @Override
    protected Object genericDispatch(Library originalLib, Object receiver, Message message, Object[] args, int offset) throws Exception {
        NV1IntLibrary lib = (NV1IntLibrary) originalLib;
        MessageImpl messageImpl = (MessageImpl) message;
        if (messageImpl.getParameterCount() - 1 != args.length - offset) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        switch (messageImpl.index) {
            case 0 :
                return lib.isArray(receiver);
            case 1 :
                return lib.read(receiver, (int) args[offset]);
            case 2 :
                return lib.length(receiver);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        throw new AbstractMethodError(message.toString());
    }

    @Override
    protected NV1IntLibrary createDispatchImpl(int limit) {
        return new CachedDispatchFirst(null, null, limit);
    }

    @Override
    protected NV1IntLibrary createUncachedDispatch() {
        return new UncachedDispatch();
    }

    @SuppressWarnings("unchecked")
    private static Class<NV1IntLibrary> lazyLibraryClass() {
        try {
            return (Class<NV1IntLibrary>) Class.forName("fr.cea.nabla.interpreter.values.NV1IntLibrary", false, NV1IntLibraryGen.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw CompilerDirectives.shouldNotReachHere(e);
        }
    }

    @GeneratedBy(NV1IntLibrary.class)
    private static final class Default extends LibraryExport<NV1IntLibrary> {

        private Default() {
            super(NV1IntLibrary.class, Object.class, false);
        }

        @Override
        protected NV1IntLibrary createUncached(Object receiver) {
            NV1IntLibrary uncached = new Uncached(receiver);
            return uncached;
        }

        @Override
        protected NV1IntLibrary createCached(Object receiver) {
            return new Cached(receiver);
        }

        @GeneratedBy(NV1IntLibrary.class)
        private static final class Cached extends NV1IntLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;
            private final Class<?> dynamicDispatchTarget_;

            protected Cached(Object receiver) {
                this.dynamicDispatch_ = insert(DYNAMIC_DISPATCH_LIBRARY_.create(receiver));
                this.dynamicDispatchTarget_ = DYNAMIC_DISPATCH_LIBRARY_.getUncached(receiver).dispatch(receiver);
            }

            @Override
            public boolean accepts(Object receiver) {
                return dynamicDispatch_.accepts(receiver) && dynamicDispatch_.dispatch(receiver) == dynamicDispatchTarget_;
            }

            @Override
            public boolean isArray(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return super.isArray((dynamicDispatch_.cast(receiver)));
            }

            @TruffleBoundary
            @Override
            public int read(Object receiver, int index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int length(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

        }
        @GeneratedBy(NV1IntLibrary.class)
        private static final class Uncached extends NV1IntLibrary {

            @Child private DynamicDispatchLibrary dynamicDispatch_;
            private final Class<?> dynamicDispatchTarget_;

            protected Uncached(Object receiver) {
                this.dynamicDispatch_ = DYNAMIC_DISPATCH_LIBRARY_.getUncached(receiver);
                this.dynamicDispatchTarget_ = dynamicDispatch_.dispatch(receiver);
            }

            @Override
            @TruffleBoundary
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
            public boolean isArray(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return super.isArray(receiver);
            }

            @TruffleBoundary
            @Override
            public int read(Object receiver, int index) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

            @TruffleBoundary
            @Override
            public int length(Object receiver) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                throw new AbstractMethodError();
            }

        }
    }
    @GeneratedBy(NV1IntLibrary.class)
    private static class MessageImpl extends Message {

        final int index;

        MessageImpl(String name, int index, Class<?> returnType, Class<?>... parameters) {
            super(NV1IntLibraryGen.LIBRARY_CLASS, name, returnType, parameters);
            this.index = index;
        }

    }
    @GeneratedBy(NV1IntLibrary.class)
    private static final class Proxy extends NV1IntLibrary {

        @Child private ReflectionLibrary lib;

        Proxy(ReflectionLibrary lib) {
            this.lib = lib;
        }

        @Override
        public boolean isArray(Object receiver_) {
            try {
                return (boolean) lib.send(receiver_, NV1IntLibraryGen.IS_ARRAY);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                throw CompilerDirectives.shouldNotReachHere(e_);
            }
        }

        @Override
        public int read(Object receiver_, int index) {
            try {
                return (int) lib.send(receiver_, NV1IntLibraryGen.READ, index);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                throw CompilerDirectives.shouldNotReachHere(e_);
            }
        }

        @Override
        public int length(Object receiver_) {
            try {
                return (int) lib.send(receiver_, NV1IntLibraryGen.LENGTH);
            } catch (RuntimeException e_) {
                throw e_;
            } catch (Exception e_) {
                throw CompilerDirectives.shouldNotReachHere(e_);
            }
        }

        @Override
        public boolean accepts(Object receiver_) {
            return lib.accepts(receiver_);
        }

    }
    @GeneratedBy(NV1IntLibrary.class)
    private static final class Delegate extends NV1IntLibrary {

        @Child private NV1IntLibrary delegateLibrary;

        Delegate(NV1IntLibrary delegateLibrary) {
            this.delegateLibrary = delegateLibrary;
        }

        @Override
        public boolean isArray(Object receiver_) {
            if (LibraryFactory.isDelegated(delegateLibrary, 0)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).isArray(delegate);
            } else {
                return this.delegateLibrary.isArray(receiver_);
            }
        }

        @Override
        public int read(Object receiver_, int index) {
            if (LibraryFactory.isDelegated(delegateLibrary, 1)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).read(delegate, index);
            } else {
                return this.delegateLibrary.read(receiver_, index);
            }
        }

        @Override
        public int length(Object receiver_) {
            if (LibraryFactory.isDelegated(delegateLibrary, 2)) {
                Object delegate = LibraryFactory.readDelegate(this.delegateLibrary, receiver_);
                return LibraryFactory.getDelegateLibrary(this.delegateLibrary, delegate).length(delegate);
            } else {
                return this.delegateLibrary.length(receiver_);
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
    @GeneratedBy(NV1IntLibrary.class)
    private static final class CachedToUncachedDispatch extends NV1IntLibrary {

        @Override
        public NodeCost getCost() {
            return NodeCost.MEGAMORPHIC;
        }

        @TruffleBoundary
        @Override
        public boolean isArray(Object receiver_) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
            Node prev_ = encapsulating_.set(getParent());
            try {
                return INSTANCE.getUncached(receiver_).isArray(receiver_);
            } finally {
                encapsulating_.set(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int read(Object receiver_, int index) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
            Node prev_ = encapsulating_.set(getParent());
            try {
                return INSTANCE.getUncached(receiver_).read(receiver_, index);
            } finally {
                encapsulating_.set(prev_);
            }
        }

        @TruffleBoundary
        @Override
        public int length(Object receiver_) {
            assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
            EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
            Node prev_ = encapsulating_.set(getParent());
            try {
                return INSTANCE.getUncached(receiver_).length(receiver_);
            } finally {
                encapsulating_.set(prev_);
            }
        }

        @Override
        public boolean accepts(Object receiver_) {
            return true;
        }

    }
    @GeneratedBy(NV1IntLibrary.class)
    private static final class UncachedDispatch extends NV1IntLibrary {

        @Override
        public NodeCost getCost() {
            return NodeCost.MEGAMORPHIC;
        }

        @TruffleBoundary
        @Override
        public boolean isArray(Object receiver_) {
            return INSTANCE.getUncached(receiver_).isArray(receiver_);
        }

        @TruffleBoundary
        @Override
        public int read(Object receiver_, int index) {
            return INSTANCE.getUncached(receiver_).read(receiver_, index);
        }

        @TruffleBoundary
        @Override
        public int length(Object receiver_) {
            return INSTANCE.getUncached(receiver_).length(receiver_);
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
    @GeneratedBy(NV1IntLibrary.class)
    private static final class CachedDispatchNext extends CachedDispatch {

        CachedDispatchNext(NV1IntLibrary library, CachedDispatch next) {
            super(library, next);
        }

        @Override
        int getLimit() {
            throw CompilerDirectives.shouldNotReachHere();
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

    }
    @GeneratedBy(NV1IntLibrary.class)
    private static final class CachedDispatchFirst extends CachedDispatch {

        private final int limit_;

        CachedDispatchFirst(NV1IntLibrary library, CachedDispatch next, int limit_) {
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
    @GeneratedBy(NV1IntLibrary.class)
    private abstract static class CachedDispatch extends NV1IntLibrary {

        @Child NV1IntLibrary library;
        @Child CachedDispatch next;

        CachedDispatch(NV1IntLibrary library, CachedDispatch next) {
            this.library = library;
            this.next = next;
        }

        abstract int getLimit();

        @ExplodeLoop
        @Override
        public boolean isArray(Object receiver_) {
            do {
                CachedDispatch current = this;
                do {
                    NV1IntLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.isArray(receiver_);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int read(Object receiver_, int index) {
            do {
                CachedDispatch current = this;
                do {
                    NV1IntLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.read(receiver_, index);
                    }
                    current = current.next;
                } while (current != null);
                CompilerDirectives.transferToInterpreterAndInvalidate();
                specialize(receiver_);
            } while (true);
        }

        @ExplodeLoop
        @Override
        public int length(Object receiver_) {
            do {
                CachedDispatch current = this;
                do {
                    NV1IntLibrary thisLibrary = current.library;
                    if (thisLibrary != null && thisLibrary.accepts(receiver_)) {
                        return thisLibrary.length(receiver_);
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
            NV1IntLibrary thisLibrary = current.library;
            if (thisLibrary == null) {
                this.library = insert(INSTANCE.create(receiver_));
            } else {
                Lock lock = getLock();
                lock.lock();
                try {
                    int count = 0;
                    do {
                        NV1IntLibrary currentLibrary = current.library;
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
