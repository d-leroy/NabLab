// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntJava;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV2Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaWriteArrayNode.class)
public final class NablaWriteArrayNodeGen extends NablaWriteArrayNode {

    @Child private NablaExpressionNode value_;
    @Child private GetFrameNode frameToRead_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;

    private NablaWriteArrayNodeGen(FrameSlot slot, NablaExpressionNode[] indices, NablaExpressionNode value, GetFrameNode frameToRead) {
        super(slot, indices);
        this.value_ = value;
        this.frameToRead_ = frameToRead;
    }

    private NablaWriteArrayNodeGen(NablaExpressionNode value, GetFrameNode frameToRead) {
        this.value_ = value;
        this.frameToRead_ = frameToRead;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        Frame frameToReadValue_ = this.frameToRead_.execute(frameValue);
        if (state != 0 /* is-active write(VirtualFrame, Object, Frame) || writeNV1Int(VirtualFrame, NV0Int, Frame) || writeNV2Int(VirtualFrame, NV0Int, Frame) || writeNV2Int(VirtualFrame, NV1IntJava, Frame) || writeNV1Real(VirtualFrame, NV0Real, Frame) || writeNV2Real(VirtualFrame, NV0Real, Frame) || writeNV2Real(VirtualFrame, NV1RealJava, Frame) || writeNV3Real(VirtualFrame, NV1RealJava, Frame) || writeNV3Real(VirtualFrame, NV2Real, Frame) || writeNV4Real(VirtualFrame, NV2Real, Frame) */) {
            if ((state & 0b1) != 0 /* is-active write(VirtualFrame, Object, Frame) */) {
                try {
                    return write(frameValue, valueValue_, frameToReadValue_);
                } catch (NablaInitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    Lock lock = getLock();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-excluded write(VirtualFrame, Object, Frame) */;
                        this.state_ = this.state_ & 0xfffffffe /* remove-active write(VirtualFrame, Object, Frame) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue, valueValue_, frameToReadValue_);
                }
            }
            if ((state & 0b110) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int, Frame) || writeNV2Int(VirtualFrame, NV0Int, Frame) */ && valueValue_ instanceof NV0Int) {
                NV0Int valueValue__ = (NV0Int) valueValue_;
                if ((state & 0b10) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int, Frame) */) {
                    assert (isNV1Int());
                    return writeNV1Int(frameValue, valueValue__, frameToReadValue_);
                }
                if ((state & 0b100) != 0 /* is-active writeNV2Int(VirtualFrame, NV0Int, Frame) */) {
                    assert (isNV2Int());
                    return writeNV2Int(frameValue, valueValue__, frameToReadValue_);
                }
            }
            if ((state & 0b1000) != 0 /* is-active writeNV2Int(VirtualFrame, NV1IntJava, Frame) */ && valueValue_ instanceof NV1IntJava) {
                NV1IntJava valueValue__ = (NV1IntJava) valueValue_;
                assert (isNV2Int());
                return writeNV2Int(frameValue, valueValue__, frameToReadValue_);
            }
            if ((state & 0b110000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real, Frame) || writeNV2Real(VirtualFrame, NV0Real, Frame) */ && valueValue_ instanceof NV0Real) {
                NV0Real valueValue__ = (NV0Real) valueValue_;
                if ((state & 0b10000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real, Frame) */) {
                    assert (isNV1Real());
                    return writeNV1Real(frameValue, valueValue__, frameToReadValue_);
                }
                if ((state & 0b100000) != 0 /* is-active writeNV2Real(VirtualFrame, NV0Real, Frame) */) {
                    assert (isNV2Real());
                    return writeNV2Real(frameValue, valueValue__, frameToReadValue_);
                }
            }
            if ((state & 0b11000000) != 0 /* is-active writeNV2Real(VirtualFrame, NV1RealJava, Frame) || writeNV3Real(VirtualFrame, NV1RealJava, Frame) */ && valueValue_ instanceof NV1RealJava) {
                NV1RealJava valueValue__ = (NV1RealJava) valueValue_;
                if ((state & 0b1000000) != 0 /* is-active writeNV2Real(VirtualFrame, NV1RealJava, Frame) */) {
                    assert (isNV2Real());
                    return writeNV2Real(frameValue, valueValue__, frameToReadValue_);
                }
                if ((state & 0b10000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV1RealJava, Frame) */) {
                    assert (isNV3Real());
                    return writeNV3Real(frameValue, valueValue__, frameToReadValue_);
                }
            }
            if ((state & 0b1100000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV2Real, Frame) || writeNV4Real(VirtualFrame, NV2Real, Frame) */ && valueValue_ instanceof NV2Real) {
                NV2Real valueValue__ = (NV2Real) valueValue_;
                if ((state & 0b100000000) != 0 /* is-active writeNV3Real(VirtualFrame, NV2Real, Frame) */) {
                    assert (isNV3Real());
                    return writeNV3Real(frameValue, valueValue__, frameToReadValue_);
                }
                if ((state & 0b1000000000) != 0 /* is-active writeNV4Real(VirtualFrame, NV2Real, Frame) */) {
                    assert (isNV4Real());
                    return writeNV4Real(frameValue, valueValue__, frameToReadValue_);
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_, frameToReadValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue, Frame frameToReadValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-excluded write(VirtualFrame, Object, Frame) */) {
                this.state_ = state = state | 0b1 /* add-active write(VirtualFrame, Object, Frame) */;
                try {
                    lock.unlock();
                    hasLock = false;
                    return write(frameValue, valueValue, frameToReadValue);
                } catch (NablaInitializationPerformedException ex) {
                    CompilerDirectives.transferToInterpreterAndInvalidate();
                    lock.lock();
                    try {
                        this.exclude_ = this.exclude_ | 0b1 /* add-excluded write(VirtualFrame, Object, Frame) */;
                        this.state_ = this.state_ & 0xfffffffe /* remove-active write(VirtualFrame, Object, Frame) */;
                    } finally {
                        lock.unlock();
                    }
                    return executeAndSpecialize(frameValue, valueValue, frameToReadValue);
                }
            }
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                if ((isNV1Int())) {
                    this.state_ = state = state | 0b10 /* add-active writeNV1Int(VirtualFrame, NV0Int, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV1Int(frameValue, valueValue_, frameToReadValue);
                }
                if ((isNV2Int())) {
                    this.state_ = state = state | 0b100 /* add-active writeNV2Int(VirtualFrame, NV0Int, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Int(frameValue, valueValue_, frameToReadValue);
                }
            }
            if (valueValue instanceof NV1IntJava) {
                NV1IntJava valueValue_ = (NV1IntJava) valueValue;
                if ((isNV2Int())) {
                    this.state_ = state = state | 0b1000 /* add-active writeNV2Int(VirtualFrame, NV1IntJava, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Int(frameValue, valueValue_, frameToReadValue);
                }
            }
            if (valueValue instanceof NV0Real) {
                NV0Real valueValue_ = (NV0Real) valueValue;
                if ((isNV1Real())) {
                    this.state_ = state = state | 0b10000 /* add-active writeNV1Real(VirtualFrame, NV0Real, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV1Real(frameValue, valueValue_, frameToReadValue);
                }
                if ((isNV2Real())) {
                    this.state_ = state = state | 0b100000 /* add-active writeNV2Real(VirtualFrame, NV0Real, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Real(frameValue, valueValue_, frameToReadValue);
                }
            }
            if (valueValue instanceof NV1RealJava) {
                NV1RealJava valueValue_ = (NV1RealJava) valueValue;
                if ((isNV2Real())) {
                    this.state_ = state = state | 0b1000000 /* add-active writeNV2Real(VirtualFrame, NV1RealJava, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV2Real(frameValue, valueValue_, frameToReadValue);
                }
                if ((isNV3Real())) {
                    this.state_ = state = state | 0b10000000 /* add-active writeNV3Real(VirtualFrame, NV1RealJava, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV3Real(frameValue, valueValue_, frameToReadValue);
                }
            }
            if (valueValue instanceof NV2Real) {
                NV2Real valueValue_ = (NV2Real) valueValue;
                if ((isNV3Real())) {
                    this.state_ = state = state | 0b100000000 /* add-active writeNV3Real(VirtualFrame, NV2Real, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV3Real(frameValue, valueValue_, frameToReadValue);
                }
                if ((isNV4Real())) {
                    this.state_ = state = state | 0b1000000000 /* add-active writeNV4Real(VirtualFrame, NV2Real, Frame) */;
                    lock.unlock();
                    hasLock = false;
                    return writeNV4Real(frameValue, valueValue_, frameToReadValue);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.value_, this.frameToRead_}, valueValue, frameToReadValue);
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

    public static NablaWriteArrayNode create(FrameSlot slot, NablaExpressionNode[] indices, NablaExpressionNode value, GetFrameNode frameToRead) {
        return new NablaWriteArrayNodeGen(slot, indices, value, frameToRead);
    }

    public static NablaWriteArrayNode create(NablaExpressionNode value, GetFrameNode frameToRead) {
        return new NablaWriteArrayNodeGen(value, frameToRead);
    }

}
