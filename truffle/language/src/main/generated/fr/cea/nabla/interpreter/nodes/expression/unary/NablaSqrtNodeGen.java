// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.unary;

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
import fr.cea.nabla.interpreter.nodes.expression.unary.NablaSqrtNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaSqrtNode.class)
public final class NablaSqrtNodeGen extends NablaSqrtNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private Sqrt2Data sqrt2_cache;

    private NablaSqrtNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active sqrt(NV0Int) */ && valueNodeValue_ instanceof NV0Int) {
            NV0Int valueNodeValue__ = (NV0Int) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state & 0b10) != 0 /* is-active sqrt(NV0Real) */ && valueNodeValue_ instanceof NV0Real) {
            NV0Real valueNodeValue__ = (NV0Real) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state & 0b1100) != 0 /* is-active sqrt(Object, NV1IntLibrary) || sqrt(Object, NV1IntLibrary) */) {
            if ((state & 0b100) != 0 /* is-active sqrt(Object, NV1IntLibrary) */) {
                Sqrt2Data s3_ = this.sqrt2_cache;
                while (s3_ != null) {
                    if ((s3_.arrays_.accepts(valueNodeValue_)) && (s3_.arrays_.isArray(valueNodeValue_))) {
                        return sqrt(valueNodeValue_, s3_.arrays_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state & 0b1000) != 0 /* is-active sqrt(Object, NV1IntLibrary) */) {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if (((N_V1_INT_LIBRARY_.getUncached(valueNodeValue_)).isArray(valueNodeValue_))) {
                        return sqrt(valueNodeValue_, (N_V1_INT_LIBRARY_.getUncached(valueNodeValue_)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
        }
        if ((state & 0b10000) != 0 /* is-active sqrt(NV1Real) */ && valueNodeValue_ instanceof NV1Real) {
            NV1Real valueNodeValue__ = (NV1Real) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state & 0b100000) != 0 /* is-active sqrt(NV2Int) */ && valueNodeValue_ instanceof NV2Int) {
            NV2Int valueNodeValue__ = (NV2Int) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active sqrt(NV2Real) */ && valueNodeValue_ instanceof NV2Real) {
            NV2Real valueNodeValue__ = (NV2Real) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueNodeValue_);
    }

    private Object executeAndSpecialize(Object valueNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if (valueNodeValue instanceof NV0Int) {
                NV0Int valueNodeValue_ = (NV0Int) valueNodeValue;
                this.state_ = state = state | 0b1 /* add-active sqrt(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV0Real) {
                NV0Real valueNodeValue_ = (NV0Real) valueNodeValue;
                this.state_ = state = state | 0b10 /* add-active sqrt(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if ((exclude) == 0 /* is-not-excluded sqrt(Object, NV1IntLibrary) */) {
                int count3_ = 0;
                Sqrt2Data s3_ = this.sqrt2_cache;
                if ((state & 0b100) != 0 /* is-active sqrt(Object, NV1IntLibrary) */) {
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(valueNodeValue)) && (s3_.arrays_.isArray(valueNodeValue))) {
                            break;
                        }
                        s3_ = s3_.next_;
                        count3_++;
                    }
                }
                if (s3_ == null) {
                    {
                        NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(valueNodeValue)));
                        // assert (s3_.arrays_.accepts(valueNodeValue));
                        if ((arrays__.isArray(valueNodeValue)) && count3_ < (3)) {
                            s3_ = super.insert(new Sqrt2Data(sqrt2_cache));
                            s3_.arrays_ = s3_.insertAccessor(arrays__);
                            this.sqrt2_cache = s3_;
                            this.state_ = state = state | 0b100 /* add-active sqrt(Object, NV1IntLibrary) */;
                        }
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return sqrt(valueNodeValue, s3_.arrays_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    {
                        NV1IntLibrary sqrt3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(valueNodeValue));
                        if ((sqrt3_arrays__.isArray(valueNodeValue))) {
                            this.exclude_ = exclude = exclude | 0b1 /* add-excluded sqrt(Object, NV1IntLibrary) */;
                            this.sqrt2_cache = null;
                            state = state & 0xfffffffb /* remove-active sqrt(Object, NV1IntLibrary) */;
                            this.state_ = state = state | 0b1000 /* add-active sqrt(Object, NV1IntLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return sqrt(valueNodeValue, sqrt3_arrays__);
                        }
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (valueNodeValue instanceof NV1Real) {
                NV1Real valueNodeValue_ = (NV1Real) valueNodeValue;
                this.state_ = state = state | 0b10000 /* add-active sqrt(NV1Real) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV2Int) {
                NV2Int valueNodeValue_ = (NV2Int) valueNodeValue;
                this.state_ = state = state | 0b100000 /* add-active sqrt(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV2Real) {
                NV2Real valueNodeValue_ = (NV2Real) valueNodeValue;
                this.state_ = state = state | 0b1000000 /* add-active sqrt(NV2Real) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.valueNode_}, valueNodeValue);
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
            Sqrt2Data s3_ = this.sqrt2_cache;
            if ((s3_ == null || s3_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaSqrtNode create(NablaExpressionNode valueNode) {
        return new NablaSqrtNodeGen(valueNode);
    }

    @GeneratedBy(NablaSqrtNode.class)
    private static final class Sqrt2Data extends Node {

        @Child Sqrt2Data next_;
        @Child NV1IntLibrary arrays_;

        Sqrt2Data(Sqrt2Data next_) {
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
