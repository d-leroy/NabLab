// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaIndexOfNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaIndexOfNode.class)
public final class NablaIndexOfNodeGen extends NablaIndexOfNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode arrayNode_;
    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private ReadNV1Int1Index0Data readNV1Int1Index0_cache;

    private NablaIndexOfNodeGen(NablaExpressionNode arrayNode, NablaExpressionNode value) {
        this.arrayNode_ = arrayNode;
        this.value_ = value;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object arrayNodeValue_ = this.arrayNode_.executeGeneric(frameValue);
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if (state != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) || readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            if ((state & 0b1) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */) {
                ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
                while (s1_ != null) {
                    if ((s1_.arrays_.accepts(arrayNodeValue_)) && (s1_.arrays_.isArray(arrayNodeValue_))) {
                        return readNV1Int1Index(frameValue, arrayNodeValue_, valueValue__, s1_.arrays_);
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state & 0b10) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */) {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if (((N_V1_INT_LIBRARY_.getUncached(arrayNodeValue_)).isArray(arrayNodeValue_))) {
                        return readNV1Int1Index(frameValue, arrayNodeValue_, valueValue__, (N_V1_INT_LIBRARY_.getUncached(arrayNodeValue_)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, arrayNodeValue_, valueValue_);
    }

    private NV0Int executeAndSpecialize(VirtualFrame frameValue, Object arrayNodeValue, Object valueValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                if ((exclude) == 0 /* is-not-excluded readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */) {
                    int count1_ = 0;
                    ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
                    if ((state & 0b1) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */) {
                        while (s1_ != null) {
                            if ((s1_.arrays_.accepts(arrayNodeValue)) && (s1_.arrays_.isArray(arrayNodeValue))) {
                                break;
                            }
                            s1_ = s1_.next_;
                            count1_++;
                        }
                    }
                    if (s1_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(arrayNodeValue)));
                            // assert (s1_.arrays_.accepts(arrayNodeValue));
                            if ((arrays__.isArray(arrayNodeValue)) && count1_ < (3)) {
                                s1_ = super.insert(new ReadNV1Int1Index0Data(readNV1Int1Index0_cache));
                                s1_.arrays_ = s1_.insertAccessor(arrays__);
                                this.readNV1Int1Index0_cache = s1_;
                                this.state_ = state = state | 0b1 /* add-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s1_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return readNV1Int1Index(frameValue, arrayNodeValue, valueValue_, s1_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary readNV1Int1Index1_arrays__ = (N_V1_INT_LIBRARY_.getUncached(arrayNodeValue));
                            if ((readNV1Int1Index1_arrays__.isArray(arrayNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-excluded readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */;
                                this.readNV1Int1Index0_cache = null;
                                state = state & 0xfffffffe /* remove-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */;
                                this.state_ = state = state | 0b10 /* add-active readNV1Int1Index(VirtualFrame, Object, NV0Int, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return readNV1Int1Index(frameValue, arrayNodeValue, valueValue_, readNV1Int1Index1_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.arrayNode_, this.value_}, arrayNodeValue, valueValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
            if ((s1_ == null || s1_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaIndexOfNode create(NablaExpressionNode arrayNode, NablaExpressionNode value) {
        return new NablaIndexOfNodeGen(arrayNode, value);
    }

    @GeneratedBy(NablaIndexOfNode.class)
    private static final class ReadNV1Int1Index0Data extends Node {

        @Child ReadNV1Int1Index0Data next_;
        @Child NV1IntLibrary arrays_;

        ReadNV1Int1Index0Data(ReadNV1Int1Index0Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
}
