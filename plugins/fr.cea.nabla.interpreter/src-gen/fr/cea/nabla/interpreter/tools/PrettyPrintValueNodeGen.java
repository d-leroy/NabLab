// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.tools.PrettyPrintValueNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Bool;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Bool;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(PrettyPrintValueNode.class)
public final class PrettyPrintValueNodeGen extends PrettyPrintValueNode {

    @Child private NablaReadVariableNode read_;
    @CompilationFinal private int state_;
    @CompilationFinal private OnReturn11Data onReturn11_cache;

    private PrettyPrintValueNodeGen(int range, boolean doFormat, NablaReadVariableNode read) {
        super(range, doFormat);
        this.read_ = read;
    }

    @ExplodeLoop
    @Override
    protected String execute(VirtualFrame frameValue) {
        int state = state_;
        Object readValue_ = this.read_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active onReturn(NV0Bool) */ && readValue_ instanceof NV0Bool) {
            NV0Bool readValue__ = (NV0Bool) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b10) != 0 /* is-active onReturn(NV1Bool) */ && readValue_ instanceof NV1Bool) {
            NV1Bool readValue__ = (NV1Bool) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b100) != 0 /* is-active onReturn(NV2Bool) */ && readValue_ instanceof NV2Bool) {
            NV2Bool readValue__ = (NV2Bool) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b1000) != 0 /* is-active onReturn(NV3Bool) */ && readValue_ instanceof NV3Bool) {
            NV3Bool readValue__ = (NV3Bool) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b10000) != 0 /* is-active onReturn(NV4Bool) */ && readValue_ instanceof NV4Bool) {
            NV4Bool readValue__ = (NV4Bool) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b100000) != 0 /* is-active onReturn(NV0Int) */ && readValue_ instanceof NV0Int) {
            NV0Int readValue__ = (NV0Int) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active onReturn(NV1Int) */ && readValue_ instanceof NV1Int) {
            NV1Int readValue__ = (NV1Int) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b10000000) != 0 /* is-active onReturn(NV2Int) */ && readValue_ instanceof NV2Int) {
            NV2Int readValue__ = (NV2Int) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b100000000) != 0 /* is-active onReturn(NV3Int) */ && readValue_ instanceof NV3Int) {
            NV3Int readValue__ = (NV3Int) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b1000000000) != 0 /* is-active onReturn(NV4Int) */ && readValue_ instanceof NV4Int) {
            NV4Int readValue__ = (NV4Int) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b10000000000) != 0 /* is-active onReturn(NV0Real) */ && readValue_ instanceof NV0Real) {
            NV0Real readValue__ = (NV0Real) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b11100000000000) != 0 /* is-active onReturn(NV1Real, int, int) || onReturnRange(NV1Real) || onReturn(NV1Real) */ && readValue_ instanceof NV1Real) {
            NV1Real readValue__ = (NV1Real) readValue_;
            if ((state & 0b100000000000) != 0 /* is-active onReturn(NV1Real, int, int) */ && (readValue__.getData().length > computeWidth())) {
                OnReturn11Data s12_ = this.onReturn11_cache;
                while (s12_ != null) {
                    if ((readValue__.getData().length == s12_.cachedLength_)) {
                        return onReturn(readValue__, s12_.cachedLength_, s12_.cachedMid_);
                    }
                    s12_ = s12_.next_;
                }
            }
            if ((state & 0b1000000000000) != 0 /* is-active onReturnRange(NV1Real) */) {
                if ((readValue__.getData().length > computeWidth())) {
                    return onReturnRange(readValue__);
                }
            }
            if ((state & 0b10000000000000) != 0 /* is-active onReturn(NV1Real) */) {
                return onReturn(readValue__);
            }
        }
        if ((state & 0b100000000000000) != 0 /* is-active onReturn(NV2Real) */ && readValue_ instanceof NV2Real) {
            NV2Real readValue__ = (NV2Real) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0b1000000000000000) != 0 /* is-active onReturn(NV3Real) */ && readValue_ instanceof NV3Real) {
            NV3Real readValue__ = (NV3Real) readValue_;
            return onReturn(readValue__);
        }
        if ((state & 0x10000) != 0 /* is-active onReturn(NV4Real) */ && readValue_ instanceof NV4Real) {
            NV4Real readValue__ = (NV4Real) readValue_;
            return onReturn(readValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(readValue_);
    }

    private String executeAndSpecialize(Object readValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            if (readValue instanceof NV0Bool) {
                NV0Bool readValue_ = (NV0Bool) readValue;
                this.state_ = state = state | 0b1 /* add-active onReturn(NV0Bool) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV1Bool) {
                NV1Bool readValue_ = (NV1Bool) readValue;
                this.state_ = state = state | 0b10 /* add-active onReturn(NV1Bool) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV2Bool) {
                NV2Bool readValue_ = (NV2Bool) readValue;
                this.state_ = state = state | 0b100 /* add-active onReturn(NV2Bool) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV3Bool) {
                NV3Bool readValue_ = (NV3Bool) readValue;
                this.state_ = state = state | 0b1000 /* add-active onReturn(NV3Bool) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV4Bool) {
                NV4Bool readValue_ = (NV4Bool) readValue;
                this.state_ = state = state | 0b10000 /* add-active onReturn(NV4Bool) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV0Int) {
                NV0Int readValue_ = (NV0Int) readValue;
                this.state_ = state = state | 0b100000 /* add-active onReturn(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV1Int) {
                NV1Int readValue_ = (NV1Int) readValue;
                this.state_ = state = state | 0b1000000 /* add-active onReturn(NV1Int) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV2Int) {
                NV2Int readValue_ = (NV2Int) readValue;
                this.state_ = state = state | 0b10000000 /* add-active onReturn(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV3Int) {
                NV3Int readValue_ = (NV3Int) readValue;
                this.state_ = state = state | 0b100000000 /* add-active onReturn(NV3Int) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV4Int) {
                NV4Int readValue_ = (NV4Int) readValue;
                this.state_ = state = state | 0b1000000000 /* add-active onReturn(NV4Int) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV0Real) {
                NV0Real readValue_ = (NV0Real) readValue;
                this.state_ = state = state | 0b10000000000 /* add-active onReturn(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV1Real) {
                NV1Real readValue_ = (NV1Real) readValue;
                if ((readValue_.getData().length > computeWidth())) {
                    int count12_ = 0;
                    OnReturn11Data s12_ = this.onReturn11_cache;
                    if ((state & 0b100000000000) != 0 /* is-active onReturn(NV1Real, int, int) */) {
                        while (s12_ != null) {
                            if ((readValue_.getData().length == s12_.cachedLength_)) {
                                break;
                            }
                            s12_ = s12_.next_;
                            count12_++;
                        }
                    }
                    if (s12_ == null) {
                        // assert (readValue_.getData().length == s12_.cachedLength_);
                        if (count12_ < (3)) {
                            s12_ = new OnReturn11Data(onReturn11_cache);
                            s12_.cachedLength_ = (readValue_.getData().length);
                            s12_.cachedMid_ = (computeMid(s12_.cachedLength_));
                            this.onReturn11_cache = s12_;
                            this.state_ = state = state | 0b100000000000 /* add-active onReturn(NV1Real, int, int) */;
                        }
                    }
                    if (s12_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return onReturn(readValue_, s12_.cachedLength_, s12_.cachedMid_);
                    }
                }
                if ((readValue_.getData().length > computeWidth())) {
                    this.state_ = state = state | 0b1000000000000 /* add-active onReturnRange(NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return onReturnRange(readValue_);
                }
                this.state_ = state = state | 0b10000000000000 /* add-active onReturn(NV1Real) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV2Real) {
                NV2Real readValue_ = (NV2Real) readValue;
                this.state_ = state = state | 0b100000000000000 /* add-active onReturn(NV2Real) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV3Real) {
                NV3Real readValue_ = (NV3Real) readValue;
                this.state_ = state = state | 0b1000000000000000 /* add-active onReturn(NV3Real) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            if (readValue instanceof NV4Real) {
                NV4Real readValue_ = (NV4Real) readValue;
                this.state_ = state = state | 0x10000 /* add-active onReturn(NV4Real) */;
                lock.unlock();
                hasLock = false;
                return onReturn(readValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.read_}, readValue);
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
            OnReturn11Data s12_ = this.onReturn11_cache;
            if ((s12_ == null || s12_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static PrettyPrintValueNode create(int range, boolean doFormat, NablaReadVariableNode read) {
        return new PrettyPrintValueNodeGen(range, doFormat, read);
    }

    @GeneratedBy(PrettyPrintValueNode.class)
    private static final class OnReturn11Data {

        @CompilationFinal OnReturn11Data next_;
        @CompilationFinal int cachedLength_;
        @CompilationFinal int cachedMid_;

        OnReturn11Data(OnReturn11Data next_) {
            this.next_ = next_;
        }

    }
}
