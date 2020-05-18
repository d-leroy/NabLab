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
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaAddNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaAddNode.class)
public final class NablaAddNodeGen extends NablaAddNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;
    @CompilationFinal private Add19Data add19_cache;
    @CompilationFinal private Add26Data add26_cache;

    private NablaAddNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b111111) != 0 /* is-active add(NV0Int, NV0Int) || add(NV0Int, NV0Real) || add(NV0Int, NV1Int) || add(NV0Int, NV1Real) || add(NV0Int, NV2Int) || add(NV0Int, NV2Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active add(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active add(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100) != 0 /* is-active add(NV0Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000) != 0 /* is-active add(NV0Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000) != 0 /* is-active add(NV0Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active add(NV0Int, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b111111000000) != 0 /* is-active add(NV0Real, NV0Int) || add(NV0Real, NV0Real) || add(NV0Real, NV1Int) || add(NV0Real, NV1Real) || add(NV0Real, NV2Int) || add(NV0Real, NV2Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b1000000) != 0 /* is-active add(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active add(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active add(NV0Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active add(NV0Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000) != 0 /* is-active add(NV0Real, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000000) != 0 /* is-active add(NV0Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b1111000000000000) != 0 /* is-active add(NV1Int, NV0Int) || add(NV1Int, NV0Real) || add(NV1Int, NV1Int) || add(NV1Int, NV1Real) */ && leftNodeValue_ instanceof NV1Int) {
            NV1Int leftNodeValue__ = (NV1Int) leftNodeValue_;
            if ((state & 0b1000000000000) != 0 /* is-active add(NV1Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000000) != 0 /* is-active add(NV1Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000000000) != 0 /* is-active add(NV1Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000000000) != 0 /* is-active add(NV1Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x1f0000) != 0 /* is-active add(NV1Real, NV0Int) || add(NV1Real, NV0Real) || add(NV1Real, NV1Int) || add(NV1Real, NV1Real, int) || add(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0x10000) != 0 /* is-active add(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000) != 0 /* is-active add(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x40000) != 0 /* is-active add(NV1Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x180000) != 0 /* is-active add(NV1Real, NV1Real, int) || add(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                if ((state & 0x80000) != 0 /* is-active add(NV1Real, NV1Real, int) */) {
                    Add19Data s20_ = this.add19_cache;
                    while (s20_ != null) {
                        if ((leftNodeValue__.getData().length == s20_.cachedCount_)) {
                            return add(leftNodeValue__, rightNodeValue__, s20_.cachedCount_);
                        }
                        s20_ = s20_.next_;
                    }
                }
                if ((state & 0x100000) != 0 /* is-active add(NV1Real, NV1Real) */) {
                    return add(leftNodeValue__, rightNodeValue__);
                }
            }
        }
        if ((state & 0xe00000) != 0 /* is-active add(NV2Int, NV0Int) || add(NV2Int, NV0Real) || add(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0x200000) != 0 /* is-active add(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x400000) != 0 /* is-active add(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x800000) != 0 /* is-active add(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0xf000000) != 0 /* is-active add(NV2Real, NV0Int) || add(NV2Real, NV0Real) || add(NV2Real, NV2Real, int, int) || add(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0x1000000) != 0 /* is-active add(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x2000000) != 0 /* is-active add(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0xc000000) != 0 /* is-active add(NV2Real, NV2Real, int, int) || add(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                if ((state & 0x4000000) != 0 /* is-active add(NV2Real, NV2Real, int, int) */) {
                    Add26Data s27_ = this.add26_cache;
                    while (s27_ != null) {
                        if ((leftNodeValue__.getData().length == s27_.cachedCountI_) && (getInnerArrayLength(leftNodeValue__.getData()) == s27_.cachedCountJ_)) {
                            return add(leftNodeValue__, rightNodeValue__, s27_.cachedCountI_, s27_.cachedCountJ_);
                        }
                        s27_ = s27_.next_;
                    }
                }
                if ((state & 0x8000000) != 0 /* is-active add(NV2Real, NV2Real) */) {
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
        int state = state_;
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
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b100 /* add-active add(NV0Int, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b1000 /* add-active add(NV0Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b10000 /* add-active add(NV0Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b100000 /* add-active add(NV0Int, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV0Real) {
                NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000 /* add-active add(NV0Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000 /* add-active add(NV0Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000 /* add-active add(NV0Real, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000 /* add-active add(NV0Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0b10000000000 /* add-active add(NV0Real, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    this.state_ = state = state | 0b100000000000 /* add-active add(NV0Real, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Int) {
                NV1Int leftNodeValue_ = (NV1Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000 /* add-active add(NV1Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0b10000000000000 /* add-active add(NV1Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0b100000000000000 /* add-active add(NV1Int, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    this.state_ = state = state | 0b1000000000000000 /* add-active add(NV1Int, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV1Real) {
                NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x10000 /* add-active add(NV1Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x20000 /* add-active add(NV1Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Int) {
                    NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                    this.state_ = state = state | 0x40000 /* add-active add(NV1Real, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV1Real) {
                    NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                    int count20_ = 0;
                    Add19Data s20_ = this.add19_cache;
                    if ((state & 0x80000) != 0 /* is-active add(NV1Real, NV1Real, int) */) {
                        while (s20_ != null) {
                            if ((leftNodeValue_.getData().length == s20_.cachedCount_)) {
                                break;
                            }
                            s20_ = s20_.next_;
                            count20_++;
                        }
                    }
                    if (s20_ == null) {
                        // assert (leftNodeValue_.getData().length == s20_.cachedCount_);
                        if (count20_ < (3)) {
                            s20_ = new Add19Data(add19_cache);
                            s20_.cachedCount_ = (leftNodeValue_.getData().length);
                            this.add19_cache = s20_;
                            this.state_ = state = state | 0x80000 /* add-active add(NV1Real, NV1Real, int) */;
                        }
                    }
                    if (s20_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue_, s20_.cachedCount_);
                    }
                    this.state_ = state = state | 0x100000 /* add-active add(NV1Real, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Int) {
                NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x200000 /* add-active add(NV2Int, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x400000 /* add-active add(NV2Int, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Int) {
                    NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                    this.state_ = state = state | 0x800000 /* add-active add(NV2Int, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
            }
            if (leftNodeValue instanceof NV2Real) {
                NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
                if (rightNodeValue instanceof NV0Int) {
                    NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                    this.state_ = state = state | 0x1000000 /* add-active add(NV2Real, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV0Real) {
                    NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                    this.state_ = state = state | 0x2000000 /* add-active add(NV2Real, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return add(leftNodeValue_, rightNodeValue_);
                }
                if (rightNodeValue instanceof NV2Real) {
                    NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                    int count27_ = 0;
                    Add26Data s27_ = this.add26_cache;
                    if ((state & 0x4000000) != 0 /* is-active add(NV2Real, NV2Real, int, int) */) {
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
                                s27_ = new Add26Data(add26_cache);
                                s27_.cachedCountI_ = (leftNodeValue_.getData().length);
                                s27_.cachedCountJ_ = cachedCountJ__;
                                this.add26_cache = s27_;
                                this.state_ = state = state | 0x4000000 /* add-active add(NV2Real, NV2Real, int, int) */;
                            }
                        }
                    }
                    if (s27_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return add(leftNodeValue_, rightNodeValue_, s27_.cachedCountI_, s27_.cachedCountJ_);
                    }
                    this.state_ = state = state | 0x8000000 /* add-active add(NV2Real, NV2Real) */;
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
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            Add19Data s20_ = this.add19_cache;
            Add26Data s27_ = this.add26_cache;
            if ((s20_ == null || s20_.next_ == null) && (s27_ == null || s27_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaAddNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaAddNodeGen(leftNode, rightNode);
    }

    @GeneratedBy(NablaAddNode.class)
    private static final class Add19Data {

        @CompilationFinal Add19Data next_;
        @CompilationFinal int cachedCount_;

        Add19Data(Add19Data next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaAddNode.class)
    private static final class Add26Data {

        @CompilationFinal Add26Data next_;
        @CompilationFinal int cachedCountI_;
        @CompilationFinal int cachedCountJ_;

        Add26Data(Add26Data next_) {
            this.next_ = next_;
        }

    }
}
