// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaWriteArrayNode.class)
public final class NablaWriteArrayNodeGen extends NablaWriteArrayNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;

    private NablaWriteArrayNodeGen(FrameSlot slot, NablaExpressionNode[] indices, NablaExpressionNode value) {
        super(slot, indices);
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active write(VirtualFrame, Object) */) {
            try {
                return write(frameValue, valueValue_);
            } catch (InitializationPerformedException ex) {
                CompilerDirectives.transferToInterpreterAndInvalidate();
                Lock lock = getLock();
                lock.lock();
                try {
                    this.exclude_ = this.exclude_ | 0b1 /* add-excluded write(VirtualFrame, Object) */;
                    this.state_ = this.state_ & 0xfffffffe /* remove-active write(VirtualFrame, Object) */;
                } finally {
                    lock.unlock();
                }
                return executeAndSpecialize(frameValue, valueValue_);
            }
        }
        if ((state & 0b110) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int) || writeNV2Int(VirtualFrame, NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            if ((state & 0b10) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int) */) {
                assert (isNV1Int());
                return writeNV1Int(frameValue, valueValue__);
            }
            if ((state & 0b100) != 0 /* is-active writeNV2Int(VirtualFrame, NV0Int) */) {
                assert (isNV2Int());
                return writeNV2Int(frameValue, valueValue__);
            }
        }
        if ((state & 0b1000) != 0 /* is-active writeNV2Int(VirtualFrame, NV1Int) */ && valueValue_ instanceof NV1Int) {
            NV1Int valueValue__ = (NV1Int) valueValue_;
            assert (isNV2Int());
            return writeNV2Int(frameValue, valueValue__);
        }
        if ((state & 0b110000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real) || writeNV2Real(VirtualFrame, NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            if ((state & 0b10000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real) */) {
                assert (isNV1Real());
                return writeNV1Real(frameValue, valueValue__);
            }
            if ((state & 0b100000) != 0 /* is-active writeNV2Real(VirtualFrame, NV0Real) */) {
                assert (isNV2Real());
                return writeNV2Real(frameValue, valueValue__);
            }
        }
        if ((state & 0b11000000) != 0 /* is-active writeNV2Real(VirtualFrame, NV1Real) || writeNV3Real(VirtualFrame, NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            if ((state & 0b1000000) != 0 /* is-active writeNV2Real(VirtualFrame, NV1Real) */) {
                assert (isNV2Real());
                return writeNV2Real(frameValue, valueValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV1Real) */) {
                assert (isNV3Real());
                return writeNV3Real(frameValue, valueValue__);
            }
        }
        if ((state & 0b1100000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV2Real) || writeNV4Real(VirtualFrame, NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            if ((state & 0b100000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV2Real) */) {
                assert (isNV3Real());
                return writeNV3Real(frameValue, valueValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active writeNV4Real(VirtualFrame, NV2Real) */) {
                assert (isNV4Real());
                return writeNV4Real(frameValue, valueValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-excluded write(VirtualFrame, Object) */) {
                this.state_ = state = state | 0b1 /* add-active write(VirtualFrame, Object) */;
                try {
                    lock.unlock();
                    hasLock = false;
                    return write(frameValue, valueValue);
                } catch (InitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-excluded write(VirtualFrame, Object) */;
                        this.state_ = this.state_ & 0xfffffffe /* remove-active write(VirtualFrame, Object) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue, valueValue);
                }
            }
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                if ((isNV1Int())) {
                    this.state_ = state = state | 0b10 /* add-active writeNV1Int(VirtualFrame, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV1Int(frameValue, valueValue_);
                }
                if ((isNV2Int())) {
                    this.state_ = state = state | 0b100 /* add-active writeNV2Int(VirtualFrame, NV0Int) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Int(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof NV1Int) {
                NV1Int valueValue_ = (NV1Int) valueValue;
                if ((isNV2Int())) {
                    this.state_ = state = state | 0b1000 /* add-active writeNV2Int(VirtualFrame, NV1Int) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Int(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof NV0Real) {
                NV0Real valueValue_ = (NV0Real) valueValue;
                if ((isNV1Real())) {
                    this.state_ = state = state | 0b10000 /* add-active writeNV1Real(VirtualFrame, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV1Real(frameValue, valueValue_);
                }
                if ((isNV2Real())) {
                    this.state_ = state = state | 0b100000 /* add-active writeNV2Real(VirtualFrame, NV0Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Real(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof NV1Real) {
                NV1Real valueValue_ = (NV1Real) valueValue;
                if ((isNV2Real())) {
                    this.state_ = state = state | 0b1000000 /* add-active writeNV2Real(VirtualFrame, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Real(frameValue, valueValue_);
                }
                if ((isNV3Real())) {
                    this.state_ = state = state | 0b10000000 /* add-active writeNV3Real(VirtualFrame, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV3Real(frameValue, valueValue_);
                }
            }
            if (valueValue instanceof NV2Real) {
                NV2Real valueValue_ = (NV2Real) valueValue;
                if ((isNV3Real())) {
                    this.state_ = state = state | 0b100000000 /* add-active writeNV3Real(VirtualFrame, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV3Real(frameValue, valueValue_);
                }
                if ((isNV4Real())) {
                    this.state_ = state = state | 0b1000000000 /* add-active writeNV4Real(VirtualFrame, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV4Real(frameValue, valueValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
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
            return NodeCost.MONOMORPHIC;
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaWriteArrayNode create(FrameSlot slot, NablaExpressionNode[] indices, NablaExpressionNode value) {
        return new NablaWriteArrayNodeGen(slot, indices, value);
    }

}
