// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.unary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1RealLibrary;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaSqrtNode.class)
@SuppressWarnings("unused")
public final class NablaSqrtNodeGen extends NablaSqrtNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);
    private static final LibraryFactory<NV1RealLibrary> N_V1_REAL_LIBRARY_ = LibraryFactory.resolve(NV1RealLibrary.class);

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private volatile int exclude_;
    @Child private Sqrt2Data sqrt2_cache;
    @Child private Sqrt4Data sqrt4_cache;

    private NablaSqrtNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 sqrt(NV0Int) */ && valueNodeValue_ instanceof NV0Int) {
            NV0Int valueNodeValue__ = (NV0Int) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 sqrt(NV0Real) */ && valueNodeValue_ instanceof NV0Real) {
            NV0Real valueNodeValue__ = (NV0Real) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state_0 & 0b111100) != 0 /* is-state_0 sqrt(Object, NV1IntLibrary) || sqrt(Object, NV1IntLibrary) || sqrt(Object, NV1RealLibrary) || sqrt(Object, NV1RealLibrary) */) {
            if ((state_0 & 0b100) != 0 /* is-state_0 sqrt(Object, NV1IntLibrary) */) {
                Sqrt2Data s3_ = this.sqrt2_cache;
                while (s3_ != null) {
                    if ((s3_.arrays_.accepts(valueNodeValue_)) && (s3_.arrays_.isArray(valueNodeValue_))) {
                        return sqrt(valueNodeValue_, s3_.arrays_);
                    }
                    s3_ = s3_.next_;
                }
            }
            if ((state_0 & 0b1000) != 0 /* is-state_0 sqrt(Object, NV1IntLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1IntLibrary sqrt3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                        if ((sqrt3_arrays__.isArray(valueNodeValue_))) {
                            return this.sqrt3Boundary(state_0, valueNodeValue_);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
            if ((state_0 & 0b10000) != 0 /* is-state_0 sqrt(Object, NV1RealLibrary) */) {
                Sqrt4Data s5_ = this.sqrt4_cache;
                while (s5_ != null) {
                    if ((s5_.arrays_.accepts(valueNodeValue_)) && (s5_.arrays_.isArray(valueNodeValue_))) {
                        return sqrt(valueNodeValue_, s5_.arrays_);
                    }
                    s5_ = s5_.next_;
                }
            }
            if ((state_0 & 0b100000) != 0 /* is-state_0 sqrt(Object, NV1RealLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1RealLibrary sqrt5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                        if ((sqrt5_arrays__.isArray(valueNodeValue_))) {
                            return this.sqrt5Boundary(state_0, valueNodeValue_);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if ((state_0 & 0b1000000) != 0 /* is-state_0 sqrt(NV2Int) */ && valueNodeValue_ instanceof NV2Int) {
            NV2Int valueNodeValue__ = (NV2Int) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        if ((state_0 & 0b10000000) != 0 /* is-state_0 sqrt(NV2Real) */ && valueNodeValue_ instanceof NV2Real) {
            NV2Real valueNodeValue__ = (NV2Real) valueNodeValue_;
            return sqrt(valueNodeValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueNodeValue_);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object sqrt3Boundary(int state_0, Object valueNodeValue_) {
        {
            NV1IntLibrary sqrt3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return sqrt(valueNodeValue_, sqrt3_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object sqrt5Boundary(int state_0, Object valueNodeValue_) {
        {
            NV1RealLibrary sqrt5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return sqrt(valueNodeValue_, sqrt5_arrays__);
        }
    }

    private Object executeAndSpecialize(Object valueNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int exclude = exclude_;
        try {
            if (valueNodeValue instanceof NV0Int) {
                NV0Int valueNodeValue_ = (NV0Int) valueNodeValue;
                this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 sqrt(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV0Real) {
                NV0Real valueNodeValue_ = (NV0Real) valueNodeValue;
                this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 sqrt(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (((exclude & 0b1)) == 0 /* is-not-exclude sqrt(Object, NV1IntLibrary) */) {
                int count3_ = 0;
                Sqrt2Data s3_ = this.sqrt2_cache;
                if ((state_0 & 0b100) != 0 /* is-state_0 sqrt(Object, NV1IntLibrary) */) {
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
                            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 sqrt(Object, NV1IntLibrary) */;
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
                NV1IntLibrary sqrt3_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            sqrt3_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((sqrt3_arrays__.isArray(valueNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-exclude sqrt(Object, NV1IntLibrary) */;
                                this.sqrt2_cache = null;
                                state_0 = state_0 & 0xfffffffb /* remove-state_0 sqrt(Object, NV1IntLibrary) */;
                                this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 sqrt(Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return sqrt(valueNodeValue, sqrt3_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (((exclude & 0b10)) == 0 /* is-not-exclude sqrt(Object, NV1RealLibrary) */) {
                int count5_ = 0;
                Sqrt4Data s5_ = this.sqrt4_cache;
                if ((state_0 & 0b10000) != 0 /* is-state_0 sqrt(Object, NV1RealLibrary) */) {
                    while (s5_ != null) {
                        if ((s5_.arrays_.accepts(valueNodeValue)) && (s5_.arrays_.isArray(valueNodeValue))) {
                            break;
                        }
                        s5_ = s5_.next_;
                        count5_++;
                    }
                }
                if (s5_ == null) {
                    {
                        NV1RealLibrary arrays__1 = super.insert((N_V1_REAL_LIBRARY_.create(valueNodeValue)));
                        // assert (s5_.arrays_.accepts(valueNodeValue));
                        if ((arrays__1.isArray(valueNodeValue)) && count5_ < (3)) {
                            s5_ = super.insert(new Sqrt4Data(sqrt4_cache));
                            s5_.arrays_ = s5_.insertAccessor(arrays__1);
                            this.sqrt4_cache = s5_;
                            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 sqrt(Object, NV1RealLibrary) */;
                        }
                    }
                }
                if (s5_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return sqrt(valueNodeValue, s5_.arrays_);
                }
            }
            {
                NV1RealLibrary sqrt5_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            sqrt5_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((sqrt5_arrays__.isArray(valueNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b10 /* add-exclude sqrt(Object, NV1RealLibrary) */;
                                this.sqrt4_cache = null;
                                state_0 = state_0 & 0xffffffef /* remove-state_0 sqrt(Object, NV1RealLibrary) */;
                                this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 sqrt(Object, NV1RealLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return sqrt(valueNodeValue, sqrt5_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (valueNodeValue instanceof NV2Int) {
                NV2Int valueNodeValue_ = (NV2Int) valueNodeValue;
                this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 sqrt(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return sqrt(valueNodeValue_);
            }
            if (valueNodeValue instanceof NV2Real) {
                NV2Real valueNodeValue_ = (NV2Real) valueNodeValue;
                this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 sqrt(NV2Real) */;
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
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            if ((state_0 & (state_0 - 1)) == 0 /* is-single-state_0  */) {
                Sqrt2Data s3_ = this.sqrt2_cache;
                Sqrt4Data s5_ = this.sqrt4_cache;
                if ((s3_ == null || s3_.next_ == null) && (s5_ == null || s5_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
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
    @GeneratedBy(NablaSqrtNode.class)
    private static final class Sqrt4Data extends Node {

        @Child Sqrt4Data next_;
        @Child NV1RealLibrary arrays_;

        Sqrt4Data(Sqrt4Data next_) {
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
