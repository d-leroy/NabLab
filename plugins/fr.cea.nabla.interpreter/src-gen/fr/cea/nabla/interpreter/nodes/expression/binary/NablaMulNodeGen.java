// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.binary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaMulNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaMulNode.class)
public final class NablaMulNodeGen extends NablaMulNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;
    @CompilationFinal private Mul9Data mul9_cache;
    @CompilationFinal private Mul12Data mul12_cache;
    @CompilationFinal private Mul26Data mul26_cache;
    @CompilationFinal private Mul28Data mul28_cache;

    private NablaMulNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b111111) != 0 /* is-active mul(NV0Int, NV0Int) || mul(NV0Int, NV0Real) || mul(NV0Int, NV1Int) || mul(NV0Int, NV1Real) || mul(NV0Int, NV2Int) || mul(NV0Int, NV2Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active mul(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active mul(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100) != 0 /* is-active mul(NV0Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000) != 0 /* is-active mul(NV0Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000) != 0 /* is-active mul(NV0Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active mul(NV0Int, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11111111000000) != 0 /* is-active mul(NV0Real, NV0Int) || mul(NV0Real, NV0Real) || mul(NV0Real, NV1Int) || mul(NV0Real, NV1Real, int) || mul(NV0Real, NV1Real) || mul(NV0Real, NV2Int) || mul(NV0Real, NV2Real, int, int) || mul(NV0Real, NV2Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b1000000) != 0 /* is-active mul(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active mul(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active mul(NV0Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b11000000000) != 0 /* is-active mul(NV0Real, NV1Real, int) || mul(NV0Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0b1000000000) != 0 /* is-active mul(NV0Real, NV1Real, int) */) {
                    Mul9Data s10_ = this.mul9_cache;
                    while (s10_ != null) {
                        if ((rightNodeValue__.getData().length == s10_.cachedCount_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s10_.cachedCount_);
                        }
                        s10_ = s10_.next_;
                    }
                }
                if ((state & 0b10000000000) != 0 /* is-active mul(NV0Real, NV1Real) */) {
                    return mul(leftNodeValue__, rightNodeValue__);
                }
            }
            if ((state & 0b100000000000) != 0 /* is-active mul(NV0Real, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b11000000000000) != 0 /* is-active mul(NV0Real, NV2Real, int, int) || mul(NV0Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0b1000000000000) != 0 /* is-active mul(NV0Real, NV2Real, int, int) */) {
                    Mul12Data s13_ = this.mul12_cache;
                    while (s13_ != null) {
                        if ((rightNodeValue__.getData().length == s13_.cachedCountI_) && (getInnerArrayLength(rightNodeValue__.getData()) == s13_.cachedCountJ_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s13_.cachedCountI_, s13_.cachedCountJ_);
                        }
                        s13_ = s13_.next_;
                    }
                }
                if ((state & 0b10000000000000) != 0 /* is-active mul(NV0Real, NV2Real) */) {
                    return mul(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        if ((state & 0x3c000) != 0 /* is-active mul(NV1Int, NV0Int) || mul(NV1Int, NV0Real) || mul(NV1Int, NV1Int) || mul(NV1Int, NV1Real) */ && leftNodeValue_ instanceof NV1Int) {
            NV1Int leftNodeValue__ = (NV1Int) leftNodeValue_;
            if ((state & 0b100000000000000) != 0 /* is-active mul(NV1Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000000) != 0 /* is-active mul(NV1Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x10000) != 0 /* is-active mul(NV1Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000) != 0 /* is-active mul(NV1Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x3c0000) != 0 /* is-active mul(NV1Real, NV0Int) || mul(NV1Real, NV0Real) || mul(NV1Real, NV1Int) || mul(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0x40000) != 0 /* is-active mul(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x80000) != 0 /* is-active mul(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x100000) != 0 /* is-active mul(NV1Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x200000) != 0 /* is-active mul(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x1c00000) != 0 /* is-active mul(NV2Int, NV0Int) || mul(NV2Int, NV0Real) || mul(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0x400000) != 0 /* is-active mul(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x800000) != 0 /* is-active mul(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x1000000) != 0 /* is-active mul(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x3e000000) != 0 /* is-active mul(NV2Real, NV0Int) || mul(NV2Real, NV0Real, int, int) || mul(NV2Real, NV0Real) || mul(NV2Real, NV2Real, int, int) || mul(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x2000000) != 0 /* is-active mul(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mul(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0xc000000) != 0 /* is-active mul(NV2Real, NV0Real, int, int) || mul(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                if ((state & 0x4000000) != 0 /* is-active mul(NV2Real, NV0Real, int, int) */) {
                    Mul26Data s27_ = this.mul26_cache;
                    while (s27_ != null) {
                        if ((leftNodeValue__.getData().length == s27_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s27_.cachedCountJ_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s27_.cachedCountI_, s27_.cachedCountJ_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if ((state & 0x8000000) != 0 /* is-active mul(NV2Real, NV0Real) */) {
                    return mul(leftNodeValue__, rightNodeValue__);
                }
            }
            if ((state & 0x30000000) != 0 /* is-active mul(NV2Real, NV2Real, int, int) || mul(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0x10000000) != 0 /* is-active mul(NV2Real, NV2Real, int, int) */) {
                    Mul28Data s29_ = this.mul28_cache;
                    while (s29_ != null) {
                        if ((leftNodeValue__.getData().length == s29_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s29_.cachedCountJ_)) {
                            return mul(leftNodeValue__, rightNodeValue__, s29_.cachedCountI_, s29_.cachedCountJ_);
                        }
                        s29_ = s29_.next_;
                    }
                }
                if ((state & 0x20000000) != 0 /* is-active mul(NV2Real, NV2Real) */) {
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
        int state = state_;
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
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b100 /* add-active mul(NV0Int, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b1000 /* add-active mul(NV0Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b10000 /* add-active mul(NV0Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b100000 /* add-active mul(NV0Int, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active mul(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active mul(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active mul(NV0Real, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    int count10_ = 0;
                    Mul9Data s10_ = this.mul9_cache;
                    if ((state & 0b1000000000) != 0 /* is-active mul(NV0Real, NV1Real, int) */) {
                        while (s10_ != null) {
                            if ((rightNodeValue_.getData().length == s10_.cachedCount_)) {
                                break;
                            }
                            s10_ = s10_.next_;
                            count10_++;
                        }
                    }
                    if (s10_ == null) {
                        // assert (rightNodeValue_.getData().length == s10_.cachedCount_);
                        if (count10_ < (3)) {
                            s10_ = new Mul9Data(mul9_cache);
                            s10_.cachedCount_ = (rightNodeValue_.getData().length);
                            this.mul9_cache = s10_;
                            this.state_ = state = state | 0b1000000000 /* add-active mul(NV0Real, NV1Real, int) */;
                        }
                    }
                    if (s10_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s10_.cachedCount_);
                    }
                    this.state_ = state = state | 0b10000000000 /* add-active mul(NV0Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000000 /* add-active mul(NV0Real, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count13_ = 0;
                    Mul12Data s13_ = this.mul12_cache;
                    if ((state & 0b1000000000000) != 0 /* is-active mul(NV0Real, NV2Real, int, int) */) {
                        while (s13_ != null) {
                            if ((rightNodeValue_.getData().length == s13_.cachedCountI_) && (getInnerArrayLength(rightNodeValue_.getData()) == s13_.cachedCountJ_)) {
                                break;
                            }
                            s13_ = s13_.next_;
                            count13_++;
                        }
                    }
                    if (s13_ == null) {
                        {
                            int cachedCountJ__ = (getInnerArrayLength(rightNodeValue_.getData()));
                            // assert (rightNodeValue_.getData().length == s13_.cachedCountI_);
                            if ((getInnerArrayLength(rightNodeValue_.getData()) == cachedCountJ__) && count13_ < (3)) {
                                s13_ = new Mul12Data(mul12_cache);
                                s13_.cachedCountI_ = (rightNodeValue_.getData().length);
                                s13_.cachedCountJ_ = cachedCountJ__;
                                this.mul12_cache = s13_;
                                this.state_ = state = state | 0b1000000000000 /* add-active mul(NV0Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s13_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s13_.cachedCountI_, s13_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0b10000000000000 /* add-active mul(NV0Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Int) {
                NV1Int leftNodeValue_ = (NV1Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000000000 /* add-active mul(NV1Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000000 /* add-active mul(NV1Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0x10000 /* add-active mul(NV1Int, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0x20000 /* add-active mul(NV1Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Real) {
                NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x40000 /* add-active mul(NV1Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x80000 /* add-active mul(NV1Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0x100000 /* add-active mul(NV1Real, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0x200000 /* add-active mul(NV1Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x400000 /* add-active mul(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x800000 /* add-active mul(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0x1000000 /* add-active mul(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x2000000 /* add-active mul(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    int count27_ = 0;
                    Mul26Data s27_ = this.mul26_cache;
                    if ((state & 0x4000000) != 0 /* is-active mul(NV2Real, NV0Real, int, int) */) {
                        while (s27_ != null) {
                            if ((leftNodeValue_.getData().length == s27_.cachedCountI_) && (getInnerArrayLength(leftNodeValue_.getData()) == s27_.cachedCountJ_)) {
                                break;
                            }
                            s27_ = s27_.next_;
                            count27_++;
                        }
                    }
                    if (s27_ == null) {
                        {
                            int cachedCountJ__ = (getInnerArrayLength(leftNodeValue_.getData()));
                            // assert (leftNodeValue_.getData().length == s27_.cachedCountI_);
                            if ((getInnerArrayLength(leftNodeValue_.getData()) == cachedCountJ__) && count27_ < (3)) {
                                s27_ = new Mul26Data(mul26_cache);
                                s27_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s27_.cachedCountJ_ = cachedCountJ__;
                                this.mul26_cache = s27_;
                                this.state_ = state = state | 0x4000000 /* add-active mul(NV2Real, NV0Real, int, int) */;
                            }
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s27_.cachedCountI_, s27_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x8000000 /* add-active mul(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return mul(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count29_ = 0;
                    Mul28Data s29_ = this.mul28_cache;
                    if ((state & 0x10000000) != 0 /* is-active mul(NV2Real, NV2Real, int, int) */) {
                        while (s29_ != null) {
                            if ((leftNodeValue_.getData().length == s29_.cachedCountI_) && (getInnerArrayLength(leftNodeValue_.getData()) == s29_.cachedCountJ_)) {
                                break;
                            }
                            s29_ = s29_.next_;
                            count29_++;
                        }
                    }
                    if (s29_ == null) {
                        {
                            int cachedCountJ__ = (getInnerArrayLength(leftNodeValue_.getData()));
                            // assert (leftNodeValue_.getData().length == s29_.cachedCountI_);
                            if ((getInnerArrayLength(leftNodeValue_.getData()) == cachedCountJ__) && count29_ < (3)) {
                                s29_ = new Mul28Data(mul28_cache);
                                s29_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s29_.cachedCountJ_ = cachedCountJ__;
                                this.mul28_cache = s29_;
                                this.state_ = state = state | 0x10000000 /* add-active mul(NV2Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s29_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return mul(leftNodeValue_, rightNodeValue_, s29_.cachedCountI_, s29_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x20000000 /* add-active mul(NV2Real, NV2Real) */;
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
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Mul9Data s10_ = this.mul9_cache;
            Mul12Data s13_ = this.mul12_cache;
            Mul26Data s27_ = this.mul26_cache;
            Mul28Data s29_ = this.mul28_cache;
            if ((s10_ == null || s10_.next_ == null) && (s13_ == null || s13_.next_ == null) && (s27_ == null || s27_.next_ == null) && (s29_ == null || s29_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaMulNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaMulNodeGen(leftNode, rightNode);
    }

    @GeneratedBy(NablaMulNode.class)
    private static final class Mul9Data {

        @CompilationFinal Mul9Data next_;
        @CompilationFinal int cachedCount_;

        Mul9Data(Mul9Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul12Data {

        @CompilationFinal Mul12Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Mul12Data(Mul12Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul26Data {

        @CompilationFinal Mul26Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Mul26Data(Mul26Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaMulNode.class)
    private static final class Mul28Data {

        @CompilationFinal Mul28Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Mul28Data(Mul28Data next_) {
            this.next_ = next_;
        }

    }
}
