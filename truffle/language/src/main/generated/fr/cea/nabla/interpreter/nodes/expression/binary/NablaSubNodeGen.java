// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.binary;

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
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaSubNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaSubNode.class)
public final class NablaSubNodeGen extends NablaSubNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private volatile long state_;
    @CompilationFinal private volatile int exclude_;
    @Child private Add2Data add2_cache;
    @Child private Add9Data add9_cache;
    @Child private Add14Data add14_cache;
    @Child private Add16Data add16_cache;
    @Child private Add18Data add18_cache;
    @Child private Add20Data add20_cache;
    @Child private Add24Data add24_cache;
    @CompilationFinal private Add26Data add26_cache;
    @CompilationFinal private Add33Data add33_cache;

    private NablaSubNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        long state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b1111111) != 0 /* is-active add(NV0Int, NV0Int) || add(NV0Int, NV0Real) || add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, NV1Real) || add(NV0Int, NV2Int) || add(NV0Int, NV2Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active add(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active add(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) || add(NV0Int, Object, NV1IntLibrary) */) {
                if ((state & 0b100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
                    Add2Data s3_ = this.add2_cache;
                    while (s3_ != null) {
                        if ((s3_.arrays_.accepts(rightNodeValue_)) && (s3_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s3_.arrays_);
                        }
                        s3_ = s3_.next_;
                    }
                }
                if ((state & 0b1000) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_));
                            if ((add3_arrays__.isArray(rightNodeValue_))) {
                                return add(leftNodeValue__, rightNodeValue_, add3_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0b10000) != 0 /* is-active add(NV0Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active add(NV0Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000) != 0 /* is-active add(NV0Int, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11111110000000) != 0 /* is-active add(NV0Real, NV0Int) || add(NV0Real, NV0Real) || add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, NV1Real) || add(NV0Real, NV2Int) || add(NV0Real, NV2Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b10000000) != 0 /* is-active add(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active add(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b11000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) || add(NV0Real, Object, NV1IntLibrary) */) {
                if ((state & 0b1000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
                    Add9Data s10_ = this.add9_cache;
                    while (s10_ != null) {
                        if ((s10_.arrays_.accepts(rightNodeValue_)) && (s10_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s10_.arrays_);
                        }
                        s10_ = s10_.next_;
                    }
                }
                if ((state & 0b10000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add10_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_));
                            if ((add10_arrays__.isArray(rightNodeValue_))) {
                                return add(leftNodeValue__, rightNodeValue_, add10_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0b100000000000) != 0 /* is-active add(NV0Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000) != 0 /* is-active add(NV0Real, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000000) != 0 /* is-active add(NV0Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x3fc000L) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, NV1Real, NV1IntLibrary) || add(Object, NV1Real, NV1IntLibrary) */) {
            if ((state & 0b1100000000000000) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) || add(Object, NV0Int, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                if ((state & 0b100000000000000) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
                    Add14Data s15_ = this.add14_cache;
                    while (s15_ != null) {
                        if ((s15_.arrays_.accepts(leftNodeValue_)) && (s15_.arrays_.isArray(leftNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue__, s15_.arrays_);
                        }
                        s15_ = s15_.next_;
                    }
                }
                if ((state & 0b1000000000000000) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add15_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_));
                            if ((add15_arrays__.isArray(leftNodeValue_))) {
                                return add(leftNodeValue_, rightNodeValue__, add15_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0x30000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) || add(Object, NV0Real, NV1IntLibrary) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                if ((state & 0x10000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
                    Add16Data s17_ = this.add16_cache;
                    while (s17_ != null) {
                        if ((s17_.arrays_.accepts(leftNodeValue_)) && (s17_.arrays_.isArray(leftNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue__, s17_.arrays_);
                        }
                        s17_ = s17_.next_;
                    }
                }
                if ((state & 0x20000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add17_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_));
                            if ((add17_arrays__.isArray(leftNodeValue_))) {
                                return add(leftNodeValue_, rightNodeValue__, add17_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0xc0000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) || add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                if ((state & 0x40000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    Add18Data s19_ = this.add18_cache;
                    while (s19_ != null) {
                        if ((s19_.arraysLeft_.accepts(leftNodeValue_)) && (s19_.arraysRight_.accepts(rightNodeValue_)) && (s19_.arraysLeft_.isArray(leftNodeValue_)) && (s19_.arraysRight_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue_, s19_.arraysLeft_, s19_.arraysRight_);
                        }
                        s19_ = s19_.next_;
                    }
                }
                if ((state & 0x80000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add19_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_));
                            if ((add19_arraysLeft__.isArray(leftNodeValue_))) {
                                NV1IntLibrary add19_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_));
                                if ((add19_arraysRight__.isArray(rightNodeValue_))) {
                                    return add(leftNodeValue_, rightNodeValue_, add19_arraysLeft__, add19_arraysRight__);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0x300000L) != 0 /* is-active add(Object, NV1Real, NV1IntLibrary) || add(Object, NV1Real, NV1IntLibrary) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0x100000L) != 0 /* is-active add(Object, NV1Real, NV1IntLibrary) */) {
                    Add20Data s21_ = this.add20_cache;
                    while (s21_ != null) {
                        if ((s21_.arrays_.accepts(leftNodeValue_)) && (s21_.arrays_.isArray(leftNodeValue_))) {
                            return add(leftNodeValue_, rightNodeValue__, s21_.arrays_);
                        }
                        s21_ = s21_.next_;
                    }
                }
                if ((state & 0x200000L) != 0 /* is-active add(Object, NV1Real, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add21_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue_));
                            if ((add21_arrays__.isArray(leftNodeValue_))) {
                                return add(leftNodeValue_, rightNodeValue__, add21_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
        }
        if ((state & 0xfc00000L) != 0 /* is-active add(NV1Real, NV0Int) || add(NV1Real, NV0Real) || add(NV1Real, Object, NV1IntLibrary) || add(NV1Real, Object, NV1IntLibrary) || add(NV1Real, NV1Real, int) || add(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0x400000L) != 0 /* is-active add(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x800000L) != 0 /* is-active add(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x3000000L) != 0 /* is-active add(NV1Real, Object, NV1IntLibrary) || add(NV1Real, Object, NV1IntLibrary) */) {
                if ((state & 0x1000000L) != 0 /* is-active add(NV1Real, Object, NV1IntLibrary) */) {
                    Add24Data s25_ = this.add24_cache;
                    while (s25_ != null) {
                        if ((s25_.arrays_.accepts(rightNodeValue_)) && (s25_.arrays_.isArray(rightNodeValue_))) {
                            return add(leftNodeValue__, rightNodeValue_, s25_.arrays_);
                        }
                        s25_ = s25_.next_;
                    }
                }
                if ((state & 0x2000000L) != 0 /* is-active add(NV1Real, Object, NV1IntLibrary) */) {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            NV1IntLibrary add25_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue_));
                            if ((add25_arrays__.isArray(rightNodeValue_))) {
                                return add(leftNodeValue__, rightNodeValue_, add25_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if ((state & 0xc000000L) != 0 /* is-active add(NV1Real, NV1Real, int) || add(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0x4000000L) != 0 /* is-active add(NV1Real, NV1Real, int) */) {
                    Add26Data s27_ = this.add26_cache;
                    while (s27_ != null) {
                        if ((leftNodeValue__.getData().length == s27_.cachedCount_)) {
                            return add(leftNodeValue__, rightNodeValue__, s27_.cachedCount_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if ((state & 0x8000000L) != 0 /* is-active add(NV1Real, NV1Real) */) {
                    return add(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        if ((state & 0x70000000L) != 0 /* is-active add(NV2Int, NV0Int) || add(NV2Int, NV0Real) || add(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0x10000000L) != 0 /* is-active add(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000000L) != 0 /* is-active add(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x40000000L) != 0 /* is-active add(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x780000000L) != 0 /* is-active add(NV2Real, NV0Int) || add(NV2Real, NV0Real) || add(NV2Real, NV2Real, int, int) || add(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x80000000L) != 0 /* is-active add(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x100000000L) != 0 /* is-active add(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x600000000L) != 0 /* is-active add(NV2Real, NV2Real, int, int) || add(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0x200000000L) != 0 /* is-active add(NV2Real, NV2Real, int, int) */) {
                    Add33Data s34_ = this.add33_cache;
                    while (s34_ != null) {
                        if ((leftNodeValue__.getData().length == s34_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s34_.cachedCountJ_)) {
                            return add(leftNodeValue__, rightNodeValue__, s34_.cachedCountI_, s34_.cachedCountJ_);
                        }
                        s34_ = s34_.next_;
                    }
                }
                if ((state & 0x400000000L) != 0 /* is-active add(NV2Real, NV2Real) */) {
                    return add(leftNodeValue__, rightNodeValue__);
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
                    this.state_ = state = state | 0b1 /* add-active add(NV0Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10 /* add-active add(NV0Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1)) == 0 /* is-not-excluded add(NV0Int, Object, NV1IntLibrary) */) {
                    int count3_ = 0;
                    Add2Data s3_ = this.add2_cache;
                    if ((state & 0b100) != 0 /* is-active add(NV0Int, Object, NV1IntLibrary) */) {
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
                                s3_ = super.insert(new Add2Data(add2_cache));
                                s3_.arrays_ = s3_.insertAccessor(arrays__);
                                this.add2_cache = s3_;
                                this.state_ = state = state | 0b100 /* add-active add(NV0Int, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s3_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s3_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add3_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add3_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                                if ((add3_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1 /* add-excluded add(NV0Int, Object, NV1IntLibrary) */;
                                    this.add2_cache = null;
                                    state = state & 0xfffffffffffffffbL /* remove-active add(NV0Int, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0b1000 /* add-active add(NV0Int, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add3_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b10000 /* add-active add(NV0Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b100000 /* add-active add(NV0Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active add(NV0Int, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active add(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active add(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b10)) == 0 /* is-not-excluded add(NV0Real, Object, NV1IntLibrary) */) {
                    int count10_ = 0;
                    Add9Data s10_ = this.add9_cache;
                    if ((state & 0b1000000000) != 0 /* is-active add(NV0Real, Object, NV1IntLibrary) */) {
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
                            NV1IntLibrary arrays__1 = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s10_.arrays_.accepts(rightNodeValue));
                            if ((arrays__1.isArray(rightNodeValue)) && count10_ < (3)) {
                                s10_ = super.insert(new Add9Data(add9_cache));
                                s10_.arrays_ = s10_.insertAccessor(arrays__1);
                                this.add9_cache = s10_;
                                this.state_ = state = state | 0b1000000000 /* add-active add(NV0Real, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s10_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s10_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add10_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add10_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                                if ((add10_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b10 /* add-excluded add(NV0Real, Object, NV1IntLibrary) */;
                                    this.add9_cache = null;
                                    state = state & 0xfffffffffffffdffL /* remove-active add(NV0Real, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0b10000000000 /* add-active add(NV0Real, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add10_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000000 /* add-active add(NV0Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000 /* add-active add(NV0Real, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000000000 /* add-active add(NV0Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                if (((exclude & 0b100)) == 0 /* is-not-excluded add(Object, NV0Int, NV1IntLibrary) */) {
                    int count15_ = 0;
                    Add14Data s15_ = this.add14_cache;
                    if ((state & 0b100000000000000) != 0 /* is-active add(Object, NV0Int, NV1IntLibrary) */) {
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
                            NV1IntLibrary arrays__2 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s15_.arrays_.accepts(leftNodeValue));
                            if ((arrays__2.isArray(leftNodeValue)) && count15_ < (3)) {
                                s15_ = super.insert(new Add14Data(add14_cache));
                                s15_.arrays_ = s15_.insertAccessor(arrays__2);
                                this.add14_cache = s15_;
                                this.state_ = state = state | 0b100000000000000 /* add-active add(Object, NV0Int, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s15_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue, rightNodeValue_, s15_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add15_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add15_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                                if ((add15_arrays__.isArray(leftNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b100 /* add-excluded add(Object, NV0Int, NV1IntLibrary) */;
                                    this.add14_cache = null;
                                    state = state & 0xffffffffffffbfffL /* remove-active add(Object, NV0Int, NV1IntLibrary) */;
                                    this.state_ = state = state | 0b1000000000000000 /* add-active add(Object, NV0Int, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue_, add15_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                if (((exclude & 0b1000)) == 0 /* is-not-excluded add(Object, NV0Real, NV1IntLibrary) */) {
                    int count17_ = 0;
                    Add16Data s17_ = this.add16_cache;
                    if ((state & 0x10000L) != 0 /* is-active add(Object, NV0Real, NV1IntLibrary) */) {
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
                            NV1IntLibrary arrays__3 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s17_.arrays_.accepts(leftNodeValue));
                            if ((arrays__3.isArray(leftNodeValue)) && count17_ < (3)) {
                                s17_ = super.insert(new Add16Data(add16_cache));
                                s17_.arrays_ = s17_.insertAccessor(arrays__3);
                                this.add16_cache = s17_;
                                this.state_ = state = state | 0x10000L /* add-active add(Object, NV0Real, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s17_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue, rightNodeValue_, s17_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add17_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add17_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                                if ((add17_arrays__.isArray(leftNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1000 /* add-excluded add(Object, NV0Real, NV1IntLibrary) */;
                                    this.add16_cache = null;
                                    state = state & 0xfffffffffffeffffL /* remove-active add(Object, NV0Real, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x20000L /* add-active add(Object, NV0Real, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue_, add17_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (((exclude & 0b10000)) == 0 /* is-not-excluded add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
                int count19_ = 0;
                Add18Data s19_ = this.add18_cache;
                if ((state & 0x40000L) != 0 /* is-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */) {
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
                                s19_ = super.insert(new Add18Data(add18_cache));
                                s19_.arraysLeft_ = s19_.insertAccessor(arraysLeft__);
                                s19_.arraysRight_ = s19_.insertAccessor(arraysRight__);
                                this.add18_cache = s19_;
                                this.state_ = state = state | 0x40000L /* add-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                            }
                        }
                    }
                }
                if (s19_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue, rightNodeValue, s19_.arraysLeft_, s19_.arraysRight_);
                }
            }
            {
                NV1IntLibrary add19_arraysRight__ = null;
                NV1IntLibrary add19_arraysLeft__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            add19_arraysLeft__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                            if ((add19_arraysLeft__.isArray(leftNodeValue))) {
                                add19_arraysRight__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                                if ((add19_arraysRight__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b10000 /* add-excluded add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    this.add18_cache = null;
                                    state = state & 0xfffffffffffbffffL /* remove-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x80000L /* add-active add(Object, Object, NV1IntLibrary, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue, add19_arraysLeft__, add19_arraysRight__);
                                }
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (rightNodeValue instanceof NV1Real) {
                NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                if (((exclude & 0b100000)) == 0 /* is-not-excluded add(Object, NV1Real, NV1IntLibrary) */) {
                    int count21_ = 0;
                    Add20Data s21_ = this.add20_cache;
                    if ((state & 0x100000L) != 0 /* is-active add(Object, NV1Real, NV1IntLibrary) */) {
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
                            NV1IntLibrary arrays__4 = super.insert((N_V1_INT_LIBRARY_.create(leftNodeValue)));
                            // assert (s21_.arrays_.accepts(leftNodeValue));
                            if ((arrays__4.isArray(leftNodeValue)) && count21_ < (3)) {
                                s21_ = super.insert(new Add20Data(add20_cache));
                                s21_.arrays_ = s21_.insertAccessor(arrays__4);
                                this.add20_cache = s21_;
                                this.state_ = state = state | 0x100000L /* add-active add(Object, NV1Real, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s21_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue, rightNodeValue_, s21_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add21_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add21_arrays__ = (N_V1_INT_LIBRARY_.getUncached(leftNodeValue));
                                if ((add21_arrays__.isArray(leftNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b100000 /* add-excluded add(Object, NV1Real, NV1IntLibrary) */;
                                    this.add20_cache = null;
                                    state = state & 0xffffffffffefffffL /* remove-active add(Object, NV1Real, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x200000L /* add-active add(Object, NV1Real, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue, rightNodeValue_, add21_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
            }
            if (leftNodeValue instanceof NV1Real) {
                NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x400000L /* add-active add(NV1Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x800000L /* add-active add(NV1Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (((exclude & 0b1000000)) == 0 /* is-not-excluded add(NV1Real, Object, NV1IntLibrary) */) {
                    int count25_ = 0;
                    Add24Data s25_ = this.add24_cache;
                    if ((state & 0x1000000L) != 0 /* is-active add(NV1Real, Object, NV1IntLibrary) */) {
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
                            NV1IntLibrary arrays__5 = super.insert((N_V1_INT_LIBRARY_.create(rightNodeValue)));
                            // assert (s25_.arrays_.accepts(rightNodeValue));
                            if ((arrays__5.isArray(rightNodeValue)) && count25_ < (3)) {
                                s25_ = super.insert(new Add24Data(add24_cache));
                                s25_.arrays_ = s25_.insertAccessor(arrays__5);
                                this.add24_cache = s25_;
                                this.state_ = state = state | 0x1000000L /* add-active add(NV1Real, Object, NV1IntLibrary) */;
                            }
                        }
                    }
                    if (s25_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue, s25_.arrays_);
                    }
                }
                {
                    NV1IntLibrary add25_arrays__ = null;
                    {
                        EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                        Node prev_ = encapsulating_.set(this);
                        try {
                            {
                                add25_arrays__ = (N_V1_INT_LIBRARY_.getUncached(rightNodeValue));
                                if ((add25_arrays__.isArray(rightNodeValue))) {
                                    this.exclude_ = exclude = exclude | 0b1000000 /* add-excluded add(NV1Real, Object, NV1IntLibrary) */;
                                    this.add24_cache = null;
                                    state = state & 0xfffffffffeffffffL /* remove-active add(NV1Real, Object, NV1IntLibrary) */;
                                    this.state_ = state = state | 0x2000000L /* add-active add(NV1Real, Object, NV1IntLibrary) */;
                                    lock.unlock();
                                    hasLock = false;
                                    return add(leftNodeValue_, rightNodeValue, add25_arrays__);
                                }
                            }
                        } finally {
                            encapsulating_.set(prev_);
                        }
                    }
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    int count27_ = 0;
                    Add26Data s27_ = this.add26_cache;
                    if ((state & 0x4000000L) != 0 /* is-active add(NV1Real, NV1Real, int) */) {
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
                            s27_ = new Add26Data(add26_cache);
                            s27_.cachedCount_ = (leftNodeValue_.getData().length);
                            this.add26_cache = s27_;
                            this.state_ = state = state | 0x4000000L /* add-active add(NV1Real, NV1Real, int) */;
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue_, s27_.cachedCount_);
                    }
                    this.state_ = state = state | 0x8000000L /* add-active add(NV1Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x10000000L /* add-active add(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x20000000L /* add-active add(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0x40000000L /* add-active add(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x80000000L /* add-active add(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x100000000L /* add-active add(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count34_ = 0;
                    Add33Data s34_ = this.add33_cache;
                    if ((state & 0x200000000L) != 0 /* is-active add(NV2Real, NV2Real, int, int) */) {
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
                                s34_ = new Add33Data(add33_cache);
                                s34_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s34_.cachedCountJ_ = cachedCountJ__;
                                this.add33_cache = s34_;
                                this.state_ = state = state | 0x200000000L /* add-active add(NV2Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s34_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue_, s34_.cachedCountI_, s34_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x400000000L /* add-active add(NV2Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
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
            Add2Data s3_ = this.add2_cache;
            Add9Data s10_ = this.add9_cache;
            Add14Data s15_ = this.add14_cache;
            Add16Data s17_ = this.add16_cache;
            Add18Data s19_ = this.add18_cache;
            Add20Data s21_ = this.add20_cache;
            Add24Data s25_ = this.add24_cache;
            Add26Data s27_ = this.add26_cache;
            Add33Data s34_ = this.add33_cache;
            if ((s3_ == null || s3_.next_ == null) && (s10_ == null || s10_.next_ == null) && (s15_ == null || s15_.next_ == null) && (s17_ == null || s17_.next_ == null) && (s19_ == null || s19_.next_ == null) && (s21_ == null || s21_.next_ == null) && (s25_ == null || s25_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s34_ == null || s34_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaSubNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaSubNodeGen(leftNode, rightNode);
    }

    @GeneratedBy(NablaSubNode.class)
    private static final class Add2Data extends Node {

        @Child Add2Data next_;
        @Child NV1IntLibrary arrays_;

        Add2Data(Add2Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add9Data extends Node {

        @Child Add9Data next_;
        @Child NV1IntLibrary arrays_;

        Add9Data(Add9Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add14Data extends Node {

        @Child Add14Data next_;
        @Child NV1IntLibrary arrays_;

        Add14Data(Add14Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add16Data extends Node {

        @Child Add16Data next_;
        @Child NV1IntLibrary arrays_;

        Add16Data(Add16Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add18Data extends Node {

        @Child Add18Data next_;
        @Child NV1IntLibrary arraysLeft_;
        @Child NV1IntLibrary arraysRight_;

        Add18Data(Add18Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add20Data extends Node {

        @Child Add20Data next_;
        @Child NV1IntLibrary arrays_;

        Add20Data(Add20Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add24Data extends Node {

        @Child Add24Data next_;
        @Child NV1IntLibrary arrays_;

        Add24Data(Add24Data next_) {
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
    @GeneratedBy(NablaSubNode.class)
    private static final class Add26Data {

        @CompilationFinal Add26Data next_;
        @CompilationFinal int cachedCount_;

        Add26Data(Add26Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaSubNode.class)
    private static final class Add33Data {

        @CompilationFinal Add33Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Add33Data(Add33Data next_) {
            this.next_ = next_;
        }

    }
}
