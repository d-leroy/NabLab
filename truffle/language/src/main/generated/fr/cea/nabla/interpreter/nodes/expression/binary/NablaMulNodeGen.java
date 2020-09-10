// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.binary;

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
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaMulNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaMulNode.class)
public final class NablaMulNodeGen extends NablaMulNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private long state_;
    @CompilationFinal private int exclude_;
    @Child private Mul2Data mul2_cache;
    @Child private Mul9Data mul9_cache;
    @Child private Mul14Data mul14_cache;
    @Child private Mul16Data mul16_cache;
    @Child private Mul18Data mul18_cache;
    @Child private Mul20Data mul20_cache;
    @Child private Mul24Data mul24_cache;
    @CompilationFinal private Mul26Data mul26_cache;
    @CompilationFinal private Mul33Data mul33_cache;

    private NablaMulNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        long state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b1111111) != 0 /* is-active mul(NV0Int, NV0Int) || mul(NV0Int, NV0Real) || mul(NV0Int, Object, NV1IntLibrary) || mul(NV0Int, Object, NV1IntLibrary) || mul(NV0Int, NV1Real) || mul(NV0Int, NV2Int) || mul(NV0Int, NV2Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active mul(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active mul(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1100) != 0 /* is-active mul(NV0Int, Object, NV1IntLibrary) || mul(NV0Int, Object, NV1IntLibrary) */) {
                if ((state & 0b100) != 0 /* is-active mul(NV0Int, Object, NV1IntLibrary) */) {
                    Mul2Data s3_ = this.mul2_cache;
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(rightNodeValue_)) && (s3_.arrays_.isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, s3_.arrays_);
                        }
                        s3_ = s3_.next_;
                    }
                }
                if ((state & 0b1000) != 0 /* is-active mul(NV0Int, Object, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)).isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0b10000) != 0 /* is-active mul(NV0Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active mul(NV0Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000) != 0 /* is-active mul(NV0Int, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11111110000000) != 0 /* is-active mul(NV0Real, NV0Int) || mul(NV0Real, NV0Real) || mul(NV0Real, Object, NV1IntLibrary) || mul(NV0Real, Object, NV1IntLibrary) || mul(NV0Real, NV1Real) || mul(NV0Real, NV2Int) || mul(NV0Real, NV2Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b10000000) != 0 /* is-active mul(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active mul(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b11000000000) != 0 /* is-active mul(NV0Real, Object, NV1IntLibrary) || mul(NV0Real, Object, NV1IntLibrary) */) {
                if ((state & 0b1000000000) != 0 /* is-active mul(NV0Real, Object, NV1IntLibrary) */) {
                    Mul9Data s10_ = this.mul9_cache;
                    while (s10_ != null) {
                        if ((s10_.arrays_.accepts(rightNodeValue_)) && (s10_.arrays_.isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, s10_.arrays_);
                        }
                        s10_ = s10_.next_;
                    }
                }
                if ((state & 0b10000000000) != 0 /* is-active mul(NV0Real, Object, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)).isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0b100000000000) != 0 /* is-active mul(NV0Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000) != 0 /* is-active mul(NV0Real, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000000) != 0 /* is-active mul(NV0Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x3fc000L) != 0 /* is-active mul(Object, NV0Int, NV1IntLibrary) || mul(Object, NV0Int, NV1IntLibrary) || mul(Object, NV0Real, NV1IntLibrary) || mul(Object, NV0Real, NV1IntLibrary) || mul(Object, Object, NV1IntLibrary, NV1IntLibrary) || mul(Object, Object, NV1IntLibrary, NV1IntLibrary) || mul(Object, NV1Real, NV1IntLibrary) || mul(Object, NV1Real, NV1IntLibrary) */) {
            if ((state & 0b1100000000000000) != 0 /* is-active mul(Object, NV0Int, NV1IntLibrary) || mul(Object, NV0Int, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                if ((state & 0b100000000000000) != 0 /* is-active mul(Object, NV0Int, NV1IntLibrary) */) {
                    Mul14Data s15_ = this.mul14_cache;
                    while (s15_ != null) {
                        if ((s15_.arrays_.accepts(leftNodeValue_)) && (s15_.arrays_.isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, s15_.arrays_);
                        }
                        s15_ = s15_.next_;
                    }
                }
                if ((state & 0b1000000000000000) != 0 /* is-active mul(Object, NV0Int, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)).isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0x30000L) != 0 /* is-active mul(Object, NV0Real, NV1IntLibrary) || mul(Object, NV0Real, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                if ((state & 0x10000L) != 0 /* is-active mul(Object, NV0Real, NV1IntLibrary) */) {
                    Mul16Data s17_ = this.mul16_cache;
                    while (s17_ != null) {
                        if ((s17_.arrays_.accepts(leftNodeValue_)) && (s17_.arrays_.isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, s17_.arrays_);
                        }
                        s17_ = s17_.next_;
                    }
                }
                if ((state & 0x20000L) != 0 /* is-active mul(Object, NV0Real, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)).isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0xc0000L) != 0 /* is-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) || mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                if ((state & 0x40000L) != 0 /* is-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    Mul18Data s19_ = this.mul18_cache;
                    while (s19_ != null) {
                        if ((s19_.arraysLeft_.accepts(leftNodeValue_)) && (s19_.arraysRight_.accepts(rightNodeValue_)) && (s19_.arraysLeft_.isArray(leftNodeValue_)) && (s19_.arraysRight_.isArray(rightNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue_, s19_.arraysLeft_, s19_.arraysRight_);
                        }
                        s19_ = s19_.next_;
                    }
                }
                if ((state & 0x80000L) != 0 /* is-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)).isArray(leftNodeValue_)) && ((N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)).isArray(rightNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue_, (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)), (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0x300000L) != 0 /* is-active mul(Object, NV1Real, NV1IntLibrary) || mul(Object, NV1Real, NV1IntLibrary) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0x100000L) != 0 /* is-active mul(Object, NV1Real, NV1IntLibrary) */) {
                    Mul20Data s21_ = this.mul20_cache;
                    while (s21_ != null) {
                        if ((s21_.arrays_.accepts(leftNodeValue_)) && (s21_.arrays_.isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, s21_.arrays_);
                        }
                        s21_ = s21_.next_;
                    }
                }
                if ((state & 0x200000L) != 0 /* is-active mul(Object, NV1Real, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)).isArray(leftNodeValue_))) {
                            return mul(leftNodeValue_, rightNodeValue__, (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
        }
        if ((state & 0xfc00000L) != 0 /* is-active mul(NV1Real, NV0Int) || mul(NV1Real, NV0Real) || mul(NV1Real, Object, NV1IntLibrary) || mul(NV1Real, Object, NV1IntLibrary) || mul(NV1Real, NV1Real, int) || mul(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0x400000L) != 0 /* is-active mul(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x800000L) != 0 /* is-active mul(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x3000000L) != 0 /* is-active mul(NV1Real, Object, NV1IntLibrary) || mul(NV1Real, Object, NV1IntLibrary) */) {
                if ((state & 0x1000000L) != 0 /* is-active mul(NV1Real, Object, NV1IntLibrary) */) {
                    Mul24Data s25_ = this.mul24_cache;
                    while (s25_ != null) {
                        if ((s25_.arrays_.accepts(rightNodeValue_)) && (s25_.arrays_.isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, s25_.arrays_);
                        }
                        s25_ = s25_.next_;
                    }
                }
                if ((state & 0x2000000L) != 0 /* is-active mul(NV1Real, Object, NV1IntLibrary) */) {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        if (((N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)).isArray(rightNodeValue_))) {
                            return mul(leftNodeValue__, rightNodeValue_, (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_)));
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if ((state & 0xc000000L) != 0 /* is-active mul(NV1Real, NV1Real, int) || mul(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0x4000000L) != 0 /* is-active mul(NV1Real, NV1Real, int) */) {
                    Mul26Data s27_ = this.mul26_cache;
                    while (s27_ != null) {
                        if ((leftNodeValue__.getData().length == s27_.cachedCount_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s27_.cachedCount_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if ((state & 0x8000000L) != 0 /* is-active mul(NV1Real, NV1Real) */) {
                    return mul(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        if ((state & 0x70000000L) != 0 /* is-active mul(NV2Int, NV0Int) || mul(NV2Int, NV0Real) || mul(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0x10000000L) != 0 /* is-active mul(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000000L) != 0 /* is-active mul(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x40000000L) != 0 /* is-active mul(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x780000000L) != 0 /* is-active mul(NV2Real, NV0Int) || mul(NV2Real, NV0Real) || mul(NV2Real, NV2Real, int, int) || mul(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x80000000L) != 0 /* is-active mul(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x100000000L) != 0 /* is-active mul(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x600000000L) != 0 /* is-active mul(NV2Real, NV2Real, int, int) || mul(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0x200000000L) != 0 /* is-active mul(NV2Real, NV2Real, int, int) */) {
                    Mul33Data s34_ = this.mul33_cache;
                    while (s34_ != null) {
                        if ((leftNodeValue__.getData().length == s34_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s34_.cachedCountJ_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s34_.cachedCountI_, s34_.cachedCountJ_);
                        }
                        s34_ = s34_.next_;
                    }
                }
                if ((state & 0x400000000L) != 0 /* is-active mul(NV2Real, NV2Real) */) {
                    return mul(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    private Object executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        long state = state_;
        int exclude = exclude_;
        try {
            if (leftNodeValue instanceof NV0Int) {
                NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b1 /* add-active mul(NV0Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10 /* add-active mul(NV0Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1)) == 0 /* is-not-excluded mul(NV0Int, Object, NV1IntLibrary) */) {
                    int count3_ = 0;
                    Mul2Data s3_ = this.mul2_cache;
                    if ((state & 0b100) != 0 /* is-active mul(NV0Int, Object, NV1IntLibrary) */) {
                        while (s3_ != null) {
                            if ((s3_.arrays_.accepts(rightNodeValue)) && (s3_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s3_ = s3_.next_;
                            count3_++;
                        }
                    }
                    if (s3_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s3_.arrays_.accepts(rightNodeValue));
                            if ((arrays__.isArray(rightNodeValue)) && count3_ < (3)) {
                                s3_ = super.insert(new Mul2Data(mul2_cache));
                                s3_.arrays_ = s3_.insertAccessor(arrays__);
                                this.mul2_cache = s3_;
                                this.state_ = state = state | 0b100 /* add-active mul(NV0Int, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s3_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue, s3_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                            if ((mul3_arrays__.isArray(rightNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-excluded mul(NV0Int, Object, NV1IntLibrary) */;
                                this.mul2_cache = null;
                                state = state & 0xfffffffffffffffbL /* remove-active mul(NV0Int, Object, NV1IntLibrary) */;
                                this.state_ = state = state | 0b1000 /* add-active mul(NV0Int, Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue_, rightNodeValue, mul3_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b10000 /* add-active mul(NV0Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b100000 /* add-active mul(NV0Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active mul(NV0Int, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active mul(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active mul(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b10)) == 0 /* is-not-excluded mul(NV0Real, Object, NV1IntLibrary) */) {
                    int count10_ = 0;
                    Mul9Data s10_ = this.mul9_cache;
                    if ((state & 0b1000000000) != 0 /* is-active mul(NV0Real, Object, NV1IntLibrary) */) {
                        while (s10_ != null) {
                            if ((s10_.arrays_.accepts(rightNodeValue)) && (s10_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s10_ = s10_.next_;
                            count10_++;
                        }
                    }
                    if (s10_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s10_.arrays_.accepts(rightNodeValue));
                            if ((arrays__.isArray(rightNodeValue)) && count10_ < (3)) {
                                s10_ = super.insert(new Mul9Data(mul9_cache));
                                s10_.arrays_ = s10_.insertAccessor(arrays__);
                                this.mul9_cache = s10_;
                                this.state_ = state = state | 0b1000000000 /* add-active mul(NV0Real, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s10_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue, s10_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul10_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                            if ((mul10_arrays__.isArray(rightNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b10 /* add-excluded mul(NV0Real, Object, NV1IntLibrary) */;
                                this.mul9_cache = null;
                                state = state & 0xfffffffffffffdffL /* remove-active mul(NV0Real, Object, NV1IntLibrary) */;
                                this.state_ = state = state | 0b10000000000 /* add-active mul(NV0Real, Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue_, rightNodeValue, mul10_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000000 /* add-active mul(NV0Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000 /* add-active mul(NV0Real, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000000000 /* add-active mul(NV0Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                if (((exclude & 0b100)) == 0 /* is-not-excluded mul(Object, NV0Int, NV1IntLibrary) */) {
                    int count15_ = 0;
                    Mul14Data s15_ = this.mul14_cache;
                    if ((state & 0b100000000000000) != 0 /* is-active mul(Object, NV0Int, NV1IntLibrary) */) {
                        while (s15_ != null) {
                            if ((s15_.arrays_.accepts(leftNodeValue)) && (s15_.arrays_.isArray(leftNodeValue))) {
                                break;
                            }
                            s15_ = s15_.next_;
                            count15_++;
                        }
                    }
                    if (s15_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s15_.arrays_.accepts(leftNodeValue));
                            if ((arrays__.isArray(leftNodeValue)) && count15_ < (3)) {
                                s15_ = super.insert(new Mul14Data(mul14_cache));
                                s15_.arrays_ = s15_.insertAccessor(arrays__);
                                this.mul14_cache = s15_;
                                this.state_ = state = state | 0b100000000000000 /* add-active mul(Object, NV0Int, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s15_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue, rightNodeValue_, s15_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul15_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                            if ((mul15_arrays__.isArray(leftNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b100 /* add-excluded mul(Object, NV0Int, NV1IntLibrary) */;
                                this.mul14_cache = null;
                                state = state & 0xffffffffffffbfffL /* remove-active mul(Object, NV0Int, NV1IntLibrary) */;
                                this.state_ = state = state | 0b1000000000000000 /* add-active mul(Object, NV0Int, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue, rightNodeValue_, mul15_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                if (((exclude & 0b1000)) == 0 /* is-not-excluded mul(Object, NV0Real, NV1IntLibrary) */) {
                    int count17_ = 0;
                    Mul16Data s17_ = this.mul16_cache;
                    if ((state & 0x10000L) != 0 /* is-active mul(Object, NV0Real, NV1IntLibrary) */) {
                        while (s17_ != null) {
                            if ((s17_.arrays_.accepts(leftNodeValue)) && (s17_.arrays_.isArray(leftNodeValue))) {
                                break;
                            }
                            s17_ = s17_.next_;
                            count17_++;
                        }
                    }
                    if (s17_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s17_.arrays_.accepts(leftNodeValue));
                            if ((arrays__.isArray(leftNodeValue)) && count17_ < (3)) {
                                s17_ = super.insert(new Mul16Data(mul16_cache));
                                s17_.arrays_ = s17_.insertAccessor(arrays__);
                                this.mul16_cache = s17_;
                                this.state_ = state = state | 0x10000L /* add-active mul(Object, NV0Real, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s17_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue, rightNodeValue_, s17_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul17_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                            if ((mul17_arrays__.isArray(leftNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1000 /* add-excluded mul(Object, NV0Real, NV1IntLibrary) */;
                                this.mul16_cache = null;
                                state = state & 0xfffffffffffeffffL /* remove-active mul(Object, NV0Real, NV1IntLibrary) */;
                                this.state_ = state = state | 0x20000L /* add-active mul(Object, NV0Real, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue, rightNodeValue_, mul17_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if (((exclude & 0b10000)) == 0 /* is-not-excluded mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                int count19_ = 0;
                Mul18Data s19_ = this.mul18_cache;
                if ((state & 0x40000L) != 0 /* is-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    while (s19_ != null) {
                        if ((s19_.arraysLeft_.accepts(leftNodeValue)) && (s19_.arraysRight_.accepts(rightNodeValue)) && (s19_.arraysLeft_.isArray(leftNodeValue)) && (s19_.arraysRight_.isArray(rightNodeValue))) {
                            break;
                        }
                        s19_ = s19_.next_;
                        count19_++;
                    }
                }
                if (s19_ == null) {
                    {
                        NV1IntLibrary arraysLeft__ = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                        // assert (s19_.arraysLeft_.accepts(leftNodeValue));
                        // assert (s19_.arraysRight_.accepts(rightNodeValue));
                        if ((arraysLeft__.isArray(leftNodeValue))) {
                            NV1IntLibrary arraysRight__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            if ((arraysRight__.isArray(rightNodeValue)) && count19_ < (3)) {
                                s19_ = super.insert(new Mul18Data(mul18_cache));
                                s19_.arraysLeft_ = s19_.insertAccessor(arraysLeft__);
                                s19_.arraysRight_ = s19_.insertAccessor(arraysRight__);
                                this.mul18_cache = s19_;
                                this.state_ = state = state | 0x40000L /* add-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                            }
                        }
                    }
                }
                if (s19_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue, rightNodeValue, s19_.arraysLeft_, s19_.arraysRight_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    {
                        NV1IntLibrary mul19_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                        if ((mul19_arraysLeft__.isArray(leftNodeValue))) {
                            NV1IntLibrary mul19_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                            if ((mul19_arraysRight__.isArray(rightNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b10000 /* add-excluded mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                this.mul18_cache = null;
                                state = state & 0xfffffffffffbffffL /* remove-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                this.state_ = state = state | 0x80000L /* add-active mul(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue, rightNodeValue, mul19_arraysLeft__, mul19_arraysRight__);
                            }
                        }
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (rightNodeValue instanceof NV1Real) {
                NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                if (((exclude & 0b100000)) == 0 /* is-not-excluded mul(Object, NV1Real, NV1IntLibrary) */) {
                    int count21_ = 0;
                    Mul20Data s21_ = this.mul20_cache;
                    if ((state & 0x100000L) != 0 /* is-active mul(Object, NV1Real, NV1IntLibrary) */) {
                        while (s21_ != null) {
                            if ((s21_.arrays_.accepts(leftNodeValue)) && (s21_.arrays_.isArray(leftNodeValue))) {
                                break;
                            }
                            s21_ = s21_.next_;
                            count21_++;
                        }
                    }
                    if (s21_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s21_.arrays_.accepts(leftNodeValue));
                            if ((arrays__.isArray(leftNodeValue)) && count21_ < (3)) {
                                s21_ = super.insert(new Mul20Data(mul20_cache));
                                s21_.arrays_ = s21_.insertAccessor(arrays__);
                                this.mul20_cache = s21_;
                                this.state_ = state = state | 0x100000L /* add-active mul(Object, NV1Real, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s21_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue, rightNodeValue_, s21_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul21_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                            if ((mul21_arrays__.isArray(leftNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b100000 /* add-excluded mul(Object, NV1Real, NV1IntLibrary) */;
                                this.mul20_cache = null;
                                state = state & 0xffffffffffefffffL /* remove-active mul(Object, NV1Real, NV1IntLibrary) */;
                                this.state_ = state = state | 0x200000L /* add-active mul(Object, NV1Real, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue, rightNodeValue_, mul21_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
            }
            if (leftNodeValue instanceof NV1Real) {
                NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x400000L /* add-active mul(NV1Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x800000L /* add-active mul(NV1Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1000000)) == 0 /* is-not-excluded mul(NV1Real, Object, NV1IntLibrary) */) {
                    int count25_ = 0;
                    Mul24Data s25_ = this.mul24_cache;
                    if ((state & 0x1000000L) != 0 /* is-active mul(NV1Real, Object, NV1IntLibrary) */) {
                        while (s25_ != null) {
                            if ((s25_.arrays_.accepts(rightNodeValue)) && (s25_.arrays_.isArray(rightNodeValue))) {
                                break;
                            }
                            s25_ = s25_.next_;
                            count25_++;
                        }
                    }
                    if (s25_ == null) {
                        {
                            NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s25_.arrays_.accepts(rightNodeValue));
                            if ((arrays__.isArray(rightNodeValue)) && count25_ < (3)) {
                                s25_ = super.insert(new Mul24Data(mul24_cache));
                                s25_.arrays_ = s25_.insertAccessor(arrays__);
                                this.mul24_cache = s25_;
                                this.state_ = state = state | 0x1000000L /* add-active mul(NV1Real, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s25_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue, s25_.arrays_);
                    }
                }
                {
                    Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                    try {
                        {
                            NV1IntLibrary mul25_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                            if ((mul25_arrays__.isArray(rightNodeValue))) {
                                this.exclude_ = exclude = exclude | 0b1000000 /* add-excluded mul(NV1Real, Object, NV1IntLibrary) */;
                                this.mul24_cache = null;
                                state = state & 0xfffffffffeffffffL /* remove-active mul(NV1Real, Object, NV1IntLibrary) */;
                                this.state_ = state = state | 0x2000000L /* add-active mul(NV1Real, Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return mul(leftNodeValue_, rightNodeValue, mul25_arrays__);
                            }
                        }
                    } finally {
                        NodeUtil.popEncapsulatingNode(prev_);
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    int count27_ = 0;
                    Mul26Data s27_ = this.mul26_cache;
                    if ((state & 0x4000000L) != 0 /* is-active mul(NV1Real, NV1Real, int) */) {
                        while (s27_ != null) {
                            if ((leftNodeValue_.getData().length == s27_.cachedCount_)) {
                                break;
                            }
                            s27_ = s27_.next_;
                            count27_++;
                        }
                    }
                    if (s27_ == null) {
                        // assert (leftNodeValue_.getData().length == s27_.cachedCount_);
                        if (count27_ < (3)) {
                            s27_ = new Mul26Data(mul26_cache);
                            s27_.cachedCount_ = (leftNodeValue_.getData().length);
                            this.mul26_cache = s27_;
                            this.state_ = state = state | 0x4000000L /* add-active mul(NV1Real, NV1Real, int) */;
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s27_.cachedCount_);
                    }
                    this.state_ = state = state | 0x8000000L /* add-active mul(NV1Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x10000000L /* add-active mul(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x20000000L /* add-active mul(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0x40000000L /* add-active mul(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x80000000L /* add-active mul(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x100000000L /* add-active mul(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count34_ = 0;
                    Mul33Data s34_ = this.mul33_cache;
                    if ((state & 0x200000000L) != 0 /* is-active mul(NV2Real, NV2Real, int, int) */) {
                        while (s34_ != null) {
                            if ((leftNodeValue_.getData().length == s34_.cachedCountI_) && (getInnerArrayLength(leftNodeValue_.getData()) == s34_.cachedCountJ_)) {
                                break;
                            }
                            s34_ = s34_.next_;
                            count34_++;
                        }
                    }
                    if (s34_ == null) {
                        {
                            int cachedCountJ__ = (getInnerArrayLength(leftNodeValue_.getData()));
                            // assert (leftNodeValue_.getData().length == s34_.cachedCountI_);
                            if ((getInnerArrayLength(leftNodeValue_.getData()) == cachedCountJ__) && count34_ < (3)) {
                                s34_ = new Mul33Data(mul33_cache);
                                s34_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s34_.cachedCountJ_ = cachedCountJ__;
                                this.mul33_cache = s34_;
                                this.state_ = state = state | 0x200000000L /* add-active mul(NV2Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s34_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s34_.cachedCountI_, s34_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x400000000L /* add-active mul(NV2Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.leftNode_, this.rightNode_}, leftNodeValue, rightNodeValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        long state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Mul2Data s3_ = this.mul2_cache;
            Mul9Data s10_ = this.mul9_cache;
            Mul14Data s15_ = this.mul14_cache;
            Mul16Data s17_ = this.mul16_cache;
            Mul18Data s19_ = this.mul18_cache;
            Mul20Data s21_ = this.mul20_cache;
            Mul24Data s25_ = this.mul24_cache;
            Mul26Data s27_ = this.mul26_cache;
            Mul33Data s34_ = this.mul33_cache;
            if ((s3_ == null || s3_.next_ == null) && (s10_ == null || s10_.next_ == null) && (s15_ == null || s15_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s25_ == null || s25_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s34_ == null || s34_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaMulNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaMulNodeGen(leftNode, rightNode);
    }

    @GeneratedBy(NablaMulNode.class)
    private static final class Mul2Data extends Node {

        @Child Mul2Data next_;
        @Child NV1IntLibrary arrays_;

        Mul2Data(Mul2Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul9Data extends Node {

        @Child Mul9Data next_;
        @Child NV1IntLibrary arrays_;

        Mul9Data(Mul9Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul14Data extends Node {

        @Child Mul14Data next_;
        @Child NV1IntLibrary arrays_;

        Mul14Data(Mul14Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul16Data extends Node {

        @Child Mul16Data next_;
        @Child NV1IntLibrary arrays_;

        Mul16Data(Mul16Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul18Data extends Node {

        @Child Mul18Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1IntLibrary arraysRight_;

        Mul18Data(Mul18Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul20Data extends Node {

        @Child Mul20Data next_;
        @Child NV1IntLibrary arrays_;

        Mul20Data(Mul20Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul24Data extends Node {

        @Child Mul24Data next_;
        @Child NV1IntLibrary arrays_;

        Mul24Data(Mul24Data next_) {
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
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul26Data {

        @CompilationFinal Mul26Data next_;
        @CompilationFinal int cachedCount_;

        Mul26Data(Mul26Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul33Data {

        @CompilationFinal Mul33Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Mul33Data(Mul33Data next_) {
            this.next_ = next_;
        }

    }
}
