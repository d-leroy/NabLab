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
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaSubNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaSubNode.class)
public final class NablaSubNodeGen extends NablaSubNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;
    @CompilationFinal private Sub11Data sub11_cache;
    @CompilationFinal private Sub18Data sub18_cache;

    private NablaSubNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b11) != 0 /* is-active sub(NV0Int, NV0Int) || sub(NV0Int, NV0Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active sub(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active sub(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b1100) != 0 /* is-active sub(NV0Real, NV0Int) || sub(NV0Real, NV0Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b100) != 0 /* is-active sub(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000) != 0 /* is-active sub(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11110000) != 0 /* is-active sub(NV1Int, NV0Int) || sub(NV1Int, NV0Real) || sub(NV1Int, NV1Int) || sub(NV1Int, NV1Real) */ && leftNodeValue_ instanceof NV1Int) {
            NV1Int leftNodeValue__ = (NV1Int) leftNodeValue_;
            if ((state & 0b10000) != 0 /* is-active sub(NV1Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active sub(NV1Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000) != 0 /* is-active sub(NV1Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active sub(NV1Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b1111100000000) != 0 /* is-active sub(NV1Real, NV0Int) || sub(NV1Real, NV0Real) || sub(NV1Real, NV1Int) || sub(NV1Real, NV1Real, int) || sub(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0b100000000) != 0 /* is-active sub(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active sub(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000) != 0 /* is-active sub(NV1Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1100000000000) != 0 /* is-active sub(NV1Real, NV1Real, int) || sub(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0b100000000000) != 0 /* is-active sub(NV1Real, NV1Real, int) */) {
                    Sub11Data s12_ = this.sub11_cache;
                    while (s12_ != null) {
                        if ((leftNodeValue__.getData().length == s12_.cachedCount_)) {
                            return sub(leftNodeValue__, rightNodeValue__, s12_.cachedCount_);
                        }
                        s12_ = s12_.next_;
                    }
                }
                if ((state & 0b1000000000000) != 0 /* is-active sub(NV1Real, NV1Real) */) {
                    return sub(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        if ((state & 0b1110000000000000) != 0 /* is-active sub(NV2Int, NV0Int) || sub(NV2Int, NV0Real) || sub(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0b10000000000000) != 0 /* is-active sub(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000000000) != 0 /* is-active sub(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000000) != 0 /* is-active sub(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0xf0000) != 0 /* is-active sub(NV2Real, NV0Int) || sub(NV2Real, NV0Real) || sub(NV2Real, NV2Real, int, int) || sub(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x10000) != 0 /* is-active sub(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000) != 0 /* is-active sub(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return sub(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0xc0000) != 0 /* is-active sub(NV2Real, NV2Real, int, int) || sub(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0x40000) != 0 /* is-active sub(NV2Real, NV2Real, int, int) */) {
                    Sub18Data s19_ = this.sub18_cache;
                    while (s19_ != null) {
                        if ((leftNodeValue__.getData().length == s19_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s19_.cachedCountJ_)) {
                            return sub(leftNodeValue__, rightNodeValue__, s19_.cachedCountI_, s19_.cachedCountJ_);
                        }
                        s19_ = s19_.next_;
                    }
                }
                if ((state & 0x80000) != 0 /* is-active sub(NV2Real, NV2Real) */) {
                    return sub(leftNodeValue__, rightNodeValue__);
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
                    this.state_ = state = state | 0b1 /* add-active sub(NV0Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10 /* add-active sub(NV0Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b100 /* add-active sub(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b1000 /* add-active sub(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Int) {
                NV1Int leftNodeValue_ = (NV1Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b10000 /* add-active sub(NV1Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b100000 /* add-active sub(NV1Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active sub(NV1Int, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active sub(NV1Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Real) {
                NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active sub(NV1Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000 /* add-active sub(NV1Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b10000000000 /* add-active sub(NV1Real, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    int count12_ = 0;
                    Sub11Data s12_ = this.sub11_cache;
                    if ((state & 0b100000000000) != 0 /* is-active sub(NV1Real, NV1Real, int) */) {
                        while (s12_ != null) {
                            if ((leftNodeValue_.getData().length == s12_.cachedCount_)) {
                                break;
                            }
                            s12_ = s12_.next_;
                            count12_++;
                        }
                    }
                    if (s12_ == null) {
                        // assert (leftNodeValue_.getData().length == s12_.cachedCount_);
                        if (count12_ < (3)) {
                            s12_ = new Sub11Data(sub11_cache);
                            s12_.cachedCount_ = (leftNodeValue_.getData().length);
                            this.sub11_cache = s12_;
                            this.state_ = state = state | 0b100000000000 /* add-active sub(NV1Real, NV1Real, int) */;
                        }
                    }
                    if (s12_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return sub(leftNodeValue_, rightNodeValue_, s12_.cachedCount_);
                    }
                    this.state_ = state = state | 0b1000000000000 /* add-active sub(NV1Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b10000000000000 /* add-active sub(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000000000 /* add-active sub(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000000 /* add-active sub(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x10000 /* add-active sub(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x20000 /* add-active sub(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count19_ = 0;
                    Sub18Data s19_ = this.sub18_cache;
                    if ((state & 0x40000) != 0 /* is-active sub(NV2Real, NV2Real, int, int) */) {
                        while (s19_ != null) {
                            if ((leftNodeValue_.getData().length == s19_.cachedCountI_) && (getInnerArrayLength(leftNodeValue_.getData()) == s19_.cachedCountJ_)) {
                                break;
                            }
                            s19_ = s19_.next_;
                            count19_++;
                        }
                    }
                    if (s19_ == null) {
                        {
                            int cachedCountJ__ = (getInnerArrayLength(leftNodeValue_.getData()));
                            // assert (leftNodeValue_.getData().length == s19_.cachedCountI_);
                            if ((getInnerArrayLength(leftNodeValue_.getData()) == cachedCountJ__) && count19_ < (3)) {
                                s19_ = new Sub18Data(sub18_cache);
                                s19_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s19_.cachedCountJ_ = cachedCountJ__;
                                this.sub18_cache = s19_;
                                this.state_ = state = state | 0x40000 /* add-active sub(NV2Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s19_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return sub(leftNodeValue_, rightNodeValue_, s19_.cachedCountI_, s19_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x80000 /* add-active sub(NV2Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return sub(leftNodeValue_, rightNodeValue_);
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
            Sub11Data s12_ = this.sub11_cache;
            Sub18Data s19_ = this.sub18_cache;
            if ((s12_ == null || s12_.next_ == null) && (s19_ == null || s19_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaSubNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaSubNodeGen(leftNode, rightNode);
    }

    @GeneratedBy(NablaSubNode.class)
    private static final class Sub11Data {

        @CompilationFinal Sub11Data next_;
        @CompilationFinal int cachedCount_;

        Sub11Data(Sub11Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaSubNode.class)
    private static final class Sub18Data {

        @CompilationFinal Sub18Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Sub18Data(Sub18Data next_) {
            this.next_ = next_;
        }

    }
}
