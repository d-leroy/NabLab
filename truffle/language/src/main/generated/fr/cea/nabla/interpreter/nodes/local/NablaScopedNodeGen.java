// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.local;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.interop.NodeLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.DynamicDispatchLibrary;
import com.oracle.truffle.api.library.LibraryExport;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaScopedNode.class)
public final class NablaScopedNodeGen {

    private static final LibraryFactory<DynamicDispatchLibrary> DYNAMIC_DISPATCH_LIBRARY_ = LibraryFactory.resolve(DynamicDispatchLibrary.class);

    static  {
        LibraryExport.register(NablaScopedNode.class, new NodeLibraryExports());
    }

    private NablaScopedNodeGen() {
    }

    @GeneratedBy(NablaScopedNode.class)
    public static class NodeLibraryExports extends LibraryExport<NodeLibrary> {

        private NodeLibraryExports() {
            super(NodeLibrary.class, NablaScopedNode.class, false);
        }

        @Override
        protected NodeLibrary createUncached(Object receiver) {
            assert receiver instanceof NablaScopedNode;
            NodeLibrary uncached = new Uncached(receiver);
            return uncached;
        }

        @Override
        protected NodeLibrary createCached(Object receiver) {
            assert receiver instanceof NablaScopedNode;
            return new Cached(receiver);
        }

        @GeneratedBy(NablaScopedNode.class)
        public static class Cached extends NodeLibrary {

            private final Class<? extends NablaScopedNode> receiverClass_;
            @CompilationFinal private volatile int state_0_;
            @CompilationFinal private NablaScopedNode node;
            @CompilationFinal private ContextReference<NablaContext> nablaLanguageContextReference_;
            @CompilationFinal private Node getScopeNode__blockNode_;

            protected Cached(Object receiver) {
                NablaScopedNode castReceiver = ((NablaScopedNode) receiver) ;
                this.node = (castReceiver);
                this.receiverClass_ = castReceiver.getClass();
            }

            @Override
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_ && accepts_(receiver);
            }

            private boolean accepts_(Object arg0Value_) {
                NablaScopedNode arg0Value = CompilerDirectives.castExact(arg0Value_, receiverClass_);
                return arg0Value.accepts(this.node);
            }

            @Override
            public NodeCost getCost() {
                return NodeCost.MONOMORPHIC;
            }

            @Override
            public boolean hasScope(Object receiver, Frame frame) {
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
                return (CompilerDirectives.castExact(receiver, receiverClass_)).hasScope(frame);
            }

            @Override
            public Object getScope(Object arg0Value_, Frame arg1Value, boolean arg2Value) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
                NablaScopedNode arg0Value = CompilerDirectives.castExact(arg0Value_, receiverClass_);
                int state_0 = state_0_;
                if ((state_0 & 0b1) != 0 /* is-state_0 getScope(NablaScopedNode, Frame, boolean, NablaScopedNode, Node) */) {
                    return arg0Value.getScope(arg1Value, arg2Value, this.node, this.getScopeNode__blockNode_);
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return getScopeNode_AndSpecialize(arg0Value, arg1Value, arg2Value);
            }

            private Object getScopeNode_AndSpecialize(NablaScopedNode arg0Value, Frame arg1Value, boolean arg2Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    this.getScopeNode__blockNode_ = (arg0Value.findBlock());
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 getScope(NablaScopedNode, Frame, boolean, NablaScopedNode, Node) */;
                    lock.unlock();
                    hasLock = false;
                    return arg0Value.getScope(arg1Value, arg2Value, this.node, this.getScopeNode__blockNode_);
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public boolean hasRootInstance(Object arg0Value_, Frame arg1Value) {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
                NablaScopedNode arg0Value = CompilerDirectives.castExact(arg0Value_, receiverClass_);
                int state_0 = state_0_;
                if ((state_0 & 0b10) != 0 /* is-state_0 hasRootInstance(NablaScopedNode, Frame, ContextReference<NablaContext>) */) {
                    {
                        ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                        ContextReference<NablaContext> hasRootInstanceNode__contextRef__ = nablaLanguageContextReference__;
                        return arg0Value.hasRootInstance(arg1Value, hasRootInstanceNode__contextRef__);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return hasRootInstanceNode_AndSpecialize(arg0Value, arg1Value);
            }

            private boolean hasRootInstanceNode_AndSpecialize(NablaScopedNode arg0Value, Frame arg1Value) {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    {
                        ContextReference<NablaContext> hasRootInstanceNode__contextRef__ = null;
                        ContextReference<NablaContext> nablaLanguageContextReference__1 = this.nablaLanguageContextReference_;
                        if (nablaLanguageContextReference__1 == null) {
                            this.nablaLanguageContextReference_ = nablaLanguageContextReference__1 = super.lookupContextReference(NablaLanguage.class);
                        }
                        hasRootInstanceNode__contextRef__ = nablaLanguageContextReference__1;
                        this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 hasRootInstance(NablaScopedNode, Frame, ContextReference<NablaContext>) */;
                        lock.unlock();
                        hasLock = false;
                        return arg0Value.hasRootInstance(arg1Value, hasRootInstanceNode__contextRef__);
                    }
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

            @Override
            public Object getRootInstance(Object arg0Value_, Frame arg1Value) throws UnsupportedMessageException {
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                assert getRootNode() != null : "Invalid libray usage. Cached library must be adopted by a RootNode before it is executed.";
                NablaScopedNode arg0Value = CompilerDirectives.castExact(arg0Value_, receiverClass_);
                int state_0 = state_0_;
                if ((state_0 & 0b100) != 0 /* is-state_0 getRootInstance(NablaScopedNode, Frame, ContextReference<NablaContext>) */) {
                    {
                        ContextReference<NablaContext> nablaLanguageContextReference__ = this.nablaLanguageContextReference_;
                        ContextReference<NablaContext> getRootInstanceNode__contextRef__ = nablaLanguageContextReference__;
                        return arg0Value.getRootInstance(arg1Value, getRootInstanceNode__contextRef__);
                    }
                }
                CompilerDirectives.transferToInterpreterAndInvalidate();
                return getRootInstanceNode_AndSpecialize(arg0Value, arg1Value);
            }

            private Object getRootInstanceNode_AndSpecialize(NablaScopedNode arg0Value, Frame arg1Value) throws UnsupportedMessageException {
                Lock lock = getLock();
                boolean hasLock = true;
                lock.lock();
                int state_0 = state_0_;
                try {
                    {
                        ContextReference<NablaContext> getRootInstanceNode__contextRef__ = null;
                        ContextReference<NablaContext> nablaLanguageContextReference__1 = this.nablaLanguageContextReference_;
                        if (nablaLanguageContextReference__1 == null) {
                            this.nablaLanguageContextReference_ = nablaLanguageContextReference__1 = super.lookupContextReference(NablaLanguage.class);
                        }
                        getRootInstanceNode__contextRef__ = nablaLanguageContextReference__1;
                        this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 getRootInstance(NablaScopedNode, Frame, ContextReference<NablaContext>) */;
                        lock.unlock();
                        hasLock = false;
                        return arg0Value.getRootInstance(arg1Value, getRootInstanceNode__contextRef__);
                    }
                } finally {
                    if (hasLock) {
                        lock.unlock();
                    }
                }
            }

        }
        @GeneratedBy(NablaScopedNode.class)
        public static class Uncached extends NodeLibrary {

            private final Class<? extends NablaScopedNode> receiverClass_;
            private final ContextReference<NablaContext> nablaLanguageContextReference_ = lookupContextReference(NablaLanguage.class);

            protected Uncached(Object receiver) {
                this.receiverClass_ = ((NablaScopedNode) receiver).getClass();
            }

            @Override
            @TruffleBoundary
            public boolean accepts(Object receiver) {
                assert receiver.getClass() != this.receiverClass_ || DYNAMIC_DISPATCH_LIBRARY_.getUncached().dispatch(receiver) == null : "Invalid library export. Exported receiver with dynamic dispatch found but not expected.";
                return receiver.getClass() == this.receiverClass_ && accepts_(receiver);
            }

            @Override
            public final boolean isAdoptable() {
                return false;
            }

            @Override
            public final NodeCost getCost() {
                return NodeCost.MEGAMORPHIC;
            }

            @TruffleBoundary
            @Override
            public boolean hasScope(Object receiver, Frame frame) {
                // declared: true
                assert this.accepts(receiver) : "Invalid library usage. Library does not accept given receiver.";
                return ((NablaScopedNode) receiver) .hasScope(frame);
            }

            @TruffleBoundary
            @Override
            public Object getScope(Object arg0Value_, Frame arg1Value, boolean arg2Value) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaScopedNode arg0Value = ((NablaScopedNode) arg0Value_);
                return arg0Value.getScope(arg1Value, arg2Value, (arg0Value), (arg0Value.findBlock()));
            }

            @TruffleBoundary
            @Override
            public boolean hasRootInstance(Object arg0Value_, Frame arg1Value) {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaScopedNode arg0Value = ((NablaScopedNode) arg0Value_);
                return arg0Value.hasRootInstance(arg1Value, this.nablaLanguageContextReference_);
            }

            @TruffleBoundary
            @Override
            public Object getRootInstance(Object arg0Value_, Frame arg1Value) throws UnsupportedMessageException {
                // declared: true
                assert this.accepts(arg0Value_) : "Invalid library usage. Library does not accept given receiver.";
                NablaScopedNode arg0Value = ((NablaScopedNode) arg0Value_);
                return arg0Value.getRootInstance(arg1Value, this.nablaLanguageContextReference_);
            }

            @TruffleBoundary
            private static boolean accepts_(Object arg0Value_) {
                NablaScopedNode arg0Value = ((NablaScopedNode) arg0Value_);
                return arg0Value.accepts((arg0Value));
            }

        }
    }
}
