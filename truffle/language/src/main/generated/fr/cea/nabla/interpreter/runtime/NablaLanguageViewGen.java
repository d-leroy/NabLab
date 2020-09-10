// CheckStyle: start generated
package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.utilities.FinalBitSet;
import fr.cea.nabla.interpreter.runtime.NablaLanguageView;

@GeneratedBy(NablaLanguageView.class)
final class NablaLanguageViewGen {

    private static final LibraryFactory<InteropLibrary> INTEROP_LIBRARY_ = LibraryFactory.resolve(InteropLibrary.class);
    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NablaLanguageView.class, new InteropLibraryExports());
    }

    private NablaLanguageViewGen() {
    }

    @GeneratedBy(NablaLanguageView.class)
    private static final class InteropLibraryExports extends LibraryExport<InteropLibrary> {

        static final FinalBitSet ENABLED_MESSAGES = createMessageBitSet(INTEROP_LIBRARY_, "hasLanguage", "getLanguage", "hasMetaObject", "getMetaObject", "toDisplayString");
        private static final Uncached UNCACHED = new Uncached();

        private InteropLibraryExports() {
            super(InteropLibrary.class, NablaLanguageView.class, false);
        }

        @Override
        protected InteropLibrary createUncached(Object receiver) {
            assert receiver instanceof NablaLanguageView;
            InteropLibrary uncached = createDelegate(INTEROP_LIBRARY_, InteropLibraryExports.UNCACHED);
            return uncached;
        }

        @Override
        protected InteropLibrary createCached(Object receiver) {
            assert receiver instanceof NablaLanguageView;
            return createDelegate(INTEROP_LIBRARY_, new Cached(receiver));
        }

        @GeneratedBy(NablaLanguageView.class)
        private static final class Cached extends InteropLibrary implements DelegateExport {

            @Child private InteropLibrary receiverDelegateInteropLibrary_;

            Cached(Object originalReceiver) {
                NablaLanguageView receiver = ((NablaLanguageView) originalReceiver) ;
                this.receiverDelegateInteropLibrary_ = super.insert(INTEROP_LIBRARY_.create((receiver.delegate)));
            }

            @Override
            public FinalBitSet getDelegateExportMessages() {
                return ENABLED_MESSAGES;
            }

            @Override
            public Object readDelegateExport(Object receiver_) {
                return ((NablaLanguageView) receiver_).delegate;
            }

            @Override
            public Library getDelegateExportLibrary(Object delegate) {
                return this.receiverDelegateInteropLibrary_;
            }

            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NablaLanguageView) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.NablaLanguageView'. Exported receiver with dynamic dispatch found but not expected.";
                if (!(receiver instanceof NablaLanguageView)) {
                    return false;
                } else if (!this.receiverDelegateInteropLibrary_.accepts((((NablaLanguageView) receiver).delegate))) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NablaLanguageView) receiver)).hasLanguage();
            }

            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return (((NablaLanguageView) receiver)).getLanguage();
            }

            @Override
            public boolean hasMetaObject(Object arg0Value_) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.hasMetaObject(this.receiverDelegateInteropLibrary_);
            }

            @Override
            public NodeCost getCost() {
                return NodeCost.MONOMORPHIC;
            }

            @Override
            public Object getMetaObject(Object arg0Value_) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.getMetaObject(this.receiverDelegateInteropLibrary_);
            }

            @Override
            public Object toDisplayString(Object arg0Value_, boolean arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.toDisplayString(arg1Value, this.receiverDelegateInteropLibrary_);
            }

        }
        @GeneratedBy(NablaLanguageView.class)
        private static final class Uncached extends InteropLibrary implements DelegateExport {

            Uncached() {
            }

            @Override
            public FinalBitSet getDelegateExportMessages() {
                return ENABLED_MESSAGES;
            }

            @Override
            public Object readDelegateExport(Object receiver_) {
                return (((NablaLanguageView) receiver_)).delegate;
            }

            @Override
            public Library getDelegateExportLibrary(Object delegate_) {
                return INTEROP_LIBRARY_.getUncached(delegate_);
            }

            @TruffleBoundary
            @Override
            public boolean accepts(Object receiver) {
                assert !(receiver instanceof NablaLanguageView) || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export 'fr.cea.nabla.interpreter.runtime.NablaLanguageView'. Exported receiver with dynamic dispatch found but not expected.";
                return receiver instanceof NablaLanguageView;
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
            public boolean hasLanguage(Object receiver) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaLanguageView) receiver) .hasLanguage();
            }

            @TruffleBoundary
            @Override
            public Class<? extends TruffleLanguage<?>> getLanguage(Object receiver) throws UnsupportedMessageException {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaLanguageView) receiver) .getLanguage();
            }

            @TruffleBoundary
            @Override
            public boolean hasMetaObject(Object arg0Value_) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.hasMetaObject(INTEROP_LIBRARY_.getUncached((arg0Value.delegate)));
            }

            @TruffleBoundary
            @Override
            public Object getMetaObject(Object arg0Value_) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.getMetaObject(INTEROP_LIBRARY_.getUncached((arg0Value.delegate)));
            }

            @TruffleBoundary
            @Override
            public Object toDisplayString(Object arg0Value_, boolean arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaLanguageView arg0Value = ((NablaLanguageView) arg0Value_);
                return arg0Value.toDisplayString(arg1Value, INTEROP_LIBRARY_.getUncached((arg0Value.delegate)));
            }

        }
    }
}
