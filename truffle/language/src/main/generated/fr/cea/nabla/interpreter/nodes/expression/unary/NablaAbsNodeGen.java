// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.unary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.unary.NablaAbsNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaAbsNode.class)
public final class NablaAbsNodeGen extends NablaAbsNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private volatile int state_;
    @CompilationFinal private volatile int exclude_;
    @Child private Abs2Data abs2_cache;

    private NablaAbsNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active abs(NV0Int) */ && valueNodeValue_ instanceof NV0Int) {
            NV0Int valueNodeValue__ = (NV0Int) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b10) != 0 /* is-active abs(NV0Real) */ && valueNodeValue_ instanceof NV0Real) {
            NV0Real valueNodeValue__ = (NV0Real) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b1100) != 0 /* is-active abs(Object, NV1IntLibrary) || abs(Object, NV1IntLibrary) */) {
            if ((state & 0b100) != 0 /* is-active abs(Object, NV1IntLibrary) */) {
                Abs2Data s3_ = this.abs2_cache;
                while (s3_ != null) {
                    if ((s3_.arrays_.accepts(valueNodeValue_)) && (s3_.arrays_.isArray(valueNodeValue_))) {
                        return abs(valueNodeValue_, s3_.arrays_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state & 0b1000) != 0 /* is-active abs(Object, NV1IntLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1IntLibrary abs3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(valueNodeValue_));
                        if ((abs3_arrays__.isArray(valueNodeValue_))) {
                            return abs(valueNodeValue_, abs3_arrays__);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if ((state & 0b10000) != 0 /* is-active abs(NV1Real) */ && valueNodeValue_ instanceof NV1Real) {
            NV1Real valueNodeValue__ = (NV1Real) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b100000) != 0 /* is-active abs(NV2Int) */ && valueNodeValue_ instanceof NV2Int) {
            NV2Int valueNodeValue__ = (NV2Int) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active abs(NV2Real) */ && valueNodeValue_ instanceof NV2Real) {
            NV2Real valueNodeValue__ = (NV2Real) valueNodeValue_;
            return abs(valueNodeValue__);
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
                this.state_ = state = state | 0b1 /* add-active abs(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return abs(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV0Real) {
                NV0Real valueNodeValue_ = (NV0Real) valueNodeValue;
                this.state_ = state = state | 0b10 /* add-active abs(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return abs(valueNodeValue_);
            }
            if ((exclude) == 0 /* is-not-excluded abs(Object, NV1IntLibrary) */) {
                int count3_ = 0;
                Abs2Data s3_ = this.abs2_cache;
                if ((state & 0b100) != 0 /* is-active abs(Object, NV1IntLibrary) */) {
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
                            s3_ = super.insert(new Abs2Data(abs2_cache));
                            s3_.arrays_ = s3_.insertAccessor(arrays__);
                            this.abs2_cache = s3_;
                            this.state_ = state = state | 0b100 /* add-active abs(Object, NV1IntLibrary) */;
                        }
                    }
                }
                if (s3_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return abs(valueNodeValue, s3_.arrays_);
                }
            }
            {
                NV1IntLibrary abs3_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            abs3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(valueNodeValue));
                            if ((abs3_arrays__.isArray(valueNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-excluded abs(Object, NV1IntLibrary) */;
                                this.abs2_cache = null;
                                state = state & 0xfffffffb /* remove-active abs(Object, NV1IntLibrary) */;
                                this.state_ = state = state | 0b1000 /* add-active abs(Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return abs(valueNodeValue, abs3_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (valueNodeValue instanceof NV1Real) {
                NV1Real valueNodeValue_ = (NV1Real) valueNodeValue;
                this.state_ = state = state | 0b10000 /* add-active abs(NV1Real) */;
                lock.unlock();
                hasLock = false;
                return abs(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV2Int) {
                NV2Int valueNodeValue_ = (NV2Int) valueNodeValue;
                this.state_ = state = state | 0b100000 /* add-active abs(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return abs(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV2Real) {
                NV2Real valueNodeValue_ = (NV2Real) valueNodeValue;
                this.state_ = state = state | 0b1000000 /* add-active abs(NV2Real) */;
                lock.unlock();
                hasLock = false;
                return abs(valueNodeValue_);
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
            Abs2Data s3_ = this.abs2_cache;
            if ((s3_ == null || s3_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaAbsNode create(NablaExpressionNode valueNode) {
        return new NablaAbsNodeGen(valueNode);
    }

    @GeneratedBy(NablaAbsNode.class)
    private static final class Abs2Data extends Node {

        @Child Abs2Data next_;
        @Child NV1IntLibrary arrays_;

        Abs2Data(Abs2Data next_) {
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
