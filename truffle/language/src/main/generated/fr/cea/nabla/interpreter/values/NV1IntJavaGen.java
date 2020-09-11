// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV1IntJava;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;

@GeneratedBy(NV1IntJava.class)
final class NV1IntJavaGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NV1IntJava.class, new InteropLibraryExports(), new NV1IntLibraryExports());
    }

    private NV1IntJavaGen() {
    }

    @GeneratedBy(NV1IntJava.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private static final Uncached UNCACHED = new Uncached();
        private static final Cached CACHE = new Cached();

        private InteropLibraryExports() {
            super(InteropLibrary.class, NV1IntJava.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NV1IntJava;
            InteropLibrary uncached = InteropLibraryExports.UNCACHED;
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NV1IntJava;
            return InteropLibraryExports.CACHE;
        }

        @GeneratedBy(NV1IntJava.class)
        private static final class Cached extends InteropLibrary {

            Cached() {
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV1IntJava) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV1IntJava'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV1IntJava;
            }

            @Override
            public boolean isAdoptable() {
                return false;
            }

            @Override
            public boolean hasArrayElements(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).hasArrayElements();
            }

            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).getArraySize();
            }

            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).isArrayElementReadable(index);
            }

            @Override
            public Object readArrayElement(Object receiver, long index) throws UnsupportedMessageException, InvalidArrayIndexException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).readArrayElement(index);
            }

            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).hasLanguage();
            }

            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).getLanguage();
            }

            @Override
            public boolean hasMetaObject(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).hasMetaObject();
            }

            @Override
            public Object getMetaObject(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).getMetaObject();
            }

            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).toDisplayString(allowSideEffects);
            }

        }
        @GeneratedBy(NV1IntJava.class)
        private static final class Uncached extends InteropLibrary {

            Uncached() {
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV1IntJava) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV1IntJava'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV1IntJava;
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
                return ((NV1IntJava) receiver) .hasArrayElements();
            }

            @TruffleBoundary
            @Override
            public long getArraySize(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .getArraySize();
            }

            @TruffleBoundary
            @Override
            public boolean isArrayElementReadable(Object receiver, long index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .isArrayElementReadable(index);
            }

            @TruffleBoundary
            @Override
            public Object readArrayElement(Object receiver, long index) throws UnsupportedMessageException, InvalidArrayIndexException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .readArrayElement(index);
            }

            @TruffleBoundary
            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .hasLanguage();
            }

            @TruffleBoundary
            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .getLanguage();
            }

            @TruffleBoundary
            @Override
            public boolean hasMetaObject(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .hasMetaObject();
            }

            @TruffleBoundary
            @Override
            public Object getMetaObject(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .getMetaObject();
            }

            @TruffleBoundary
            @Override
            public Object toDisplayString(Object receiver, boolean allowSideEffects) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .toDisplayString(allowSideEffects);
            }

        }
    }
    @GeneratedBy(NV1IntJava.class)
    private static final class NV1IntLibraryExports extends LibraryExport<NV1IntLibrary> {

        private static final Uncached UNCACHED = new Uncached();
        private static final Cached CACHE = new Cached();

        private NV1IntLibraryExports() {
            super(NV1IntLibrary.class, NV1IntJava.class, false);
        }

        @Override
        protected NV1IntLibrary createUncached(Object receiver) {
            assert receiver instanceof NV1IntJava;
            NV1IntLibrary uncached = NV1IntLibraryExports.UNCACHED;
            return uncached;
        }

        @Override
        protected NV1IntLibrary createCached(Object receiver) {
            assert receiver instanceof NV1IntJava;
            return NV1IntLibraryExports.CACHE;
        }

        @GeneratedBy(NV1IntJava.class)
        private static final class Cached extends NV1IntLibrary {

            Cached() {
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV1IntJava) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV1IntJava'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV1IntJava;
            }

            @Override
            public boolean isAdoptable() {
                return false;
            }

            @Override
            public boolean isArray(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).isArray();
            }

            @Override
            public int read(Object receiver, int index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).read(index);
            }

            @Override
            public int length(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NV1IntJava) receiver)).length();
            }

        }
        @GeneratedBy(NV1IntJava.class)
        private static final class Uncached extends NV1IntLibrary {

            Uncached() {
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV1IntJava) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV1IntJava'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV1IntJava;
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
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .isArray();
            }

            @TruffleBoundary
            @Override
            public int read(Object receiver, int index) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .read(index);
            }

            @TruffleBoundary
            @Override
            public int length(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV1IntJava) receiver) .length();
            }

        }
    }
}
