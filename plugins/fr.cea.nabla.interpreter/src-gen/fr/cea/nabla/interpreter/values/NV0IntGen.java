// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV0Int;

@GeneratedBy(NV0Int.class)
final class NV0IntGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NV0Int.class, new InteropLibraryExports());
    }

    private NV0IntGen() {
    }

    @GeneratedBy(NV0Int.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        private static final Uncached UNCACHED = new Uncached();
        private static final Cached CACHE = new Cached();

        private InteropLibraryExports() {
            super(InteropLibrary.class, NV0Int.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NV0Int;
            InteropLibrary uncached = InteropLibraryExports.UNCACHED;
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NV0Int;
            return InteropLibraryExports.CACHE;
        }

        @GeneratedBy(NV0Int.class)
        private static final class Cached extends InteropLibrary {

            Cached() {
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV0Int) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV0Int'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV0Int;
            }

            @Override
            public boolean isAdoptable() {
                return false;
            }

            @Override
            public boolean isNumber(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).isNumber();
            }

            @Override
            public boolean fitsInByte(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInByte();
            }

            @Override
            public boolean fitsInShort(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInShort();
            }

            @Override
            public boolean fitsInFloat(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInFloat();
            }

            @Override
            public boolean fitsInLong(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInLong();
            }

            @Override
            public boolean fitsInInt(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInInt();
            }

            @Override
            public boolean fitsInDouble(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).fitsInDouble();
            }

            @Override
            public double asDouble(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asDouble();
            }

            @Override
            public long asLong(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asLong();
            }

            @Override
            public byte asByte(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asByte();
            }

            @Override
            public int asInt(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asInt();
            }

            @Override
            public float asFloat(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asFloat();
            }

            @Override
            public short asShort(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver).asShort();
            }

        }
        @GeneratedBy(NV0Int.class)
        private static final class Uncached extends InteropLibrary {

            Uncached() {
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NV0Int) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.values.NV0Int'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NV0Int;
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
            public boolean isNumber(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .isNumber();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInByte(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInByte();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInShort(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInShort();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInFloat(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInFloat();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInLong(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInLong();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInInt(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInInt();
            }

            @TruffleBoundary
            @Override
            public boolean fitsInDouble(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .fitsInDouble();
            }

            @TruffleBoundary
            @Override
            public double asDouble(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asDouble();
            }

            @TruffleBoundary
            @Override
            public long asLong(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asLong();
            }

            @TruffleBoundary
            @Override
            public byte asByte(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asByte();
            }

            @TruffleBoundary
            @Override
            public int asInt(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asInt();
            }

            @TruffleBoundary
            @Override
            public float asFloat(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asFloat();
            }

            @TruffleBoundary
            @Override
            public short asShort(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NV0Int) receiver) .asShort();
            }

        }
    }
}
