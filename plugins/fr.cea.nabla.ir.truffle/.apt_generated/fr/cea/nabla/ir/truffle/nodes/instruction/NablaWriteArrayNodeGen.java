// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;

@GeneratedBy(NablaWriteArrayNode.class)
public final class NablaWriteArrayNodeGen extends NablaWriteArrayNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;

    private NablaWriteArrayNodeGen(FrameSlot slot, NablaExpressionNode[] indices, NablaExpressionNode value) {
        super(slot, indices);
        this.value_ = value;
    }

    private boolean fallbackGuard_(int state, Object valueValue) {
        if (valueValue instanceof NV0Int) {
            if (((state & 0b1)) == 0 /* is-not-active writeNV1Int(VirtualFrame, NV0Int) */) {
                assert (isNV1Int());
                return false;
            }
            if (((state & 0b10)) == 0 /* is-not-active writeNV2Int(VirtualFrame, NV0Int) */) {
                assert (isNV2Int());
                return false;
            }
        }
        if (valueValue instanceof NV1Int) {
            assert (isNV2Int());
            return false;
        }
        if (valueValue instanceof NV0Real) {
            if (((state & 0b1000)) == 0 /* is-not-active writeNV1Real(VirtualFrame, NV0Real) */) {
                assert (isNV1Real());
                return false;
            }
            if (((state & 0b10000)) == 0 /* is-not-active writeNV2Real(VirtualFrame, NV0Real) */) {
                assert (isNV2Real());
                return false;
            }
        }
        if (valueValue instanceof NV1Real) {
            assert (isNV2Real());
            return false;
        }
        return true;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state & 0b11) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int) || writeNV2Int(VirtualFrame, NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            if ((state & 0b1) != 0 /* is-active writeNV1Int(VirtualFrame, NV0Int) */) {
                assert (isNV1Int());
                return writeNV1Int(frameValue, valueValue__);
            }
            if ((state & 0b10) != 0 /* is-active writeNV2Int(VirtualFrame, NV0Int) */) {
                assert (isNV2Int());
                return writeNV2Int(frameValue, valueValue__);
            }
        }
        if ((state & 0b100) != 0 /* is-active writeNV2Int(VirtualFrame, NV1Int) */ && valueValue_ instanceof NV1Int) {
            NV1Int valueValue__ = (NV1Int) valueValue_;
            assert (isNV2Int());
            return writeNV2Int(frameValue, valueValue__);
        }
        if ((state & 0b11000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real) || writeNV2Real(VirtualFrame, NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            if ((state & 0b1000) != 0 /* is-active writeNV1Real(VirtualFrame, NV0Real) */) {
                assert (isNV1Real());
                return writeNV1Real(frameValue, valueValue__);
            }
            if ((state & 0b10000) != 0 /* is-active writeNV2Real(VirtualFrame, NV0Real) */) {
                assert (isNV2Real());
                return writeNV2Real(frameValue, valueValue__);
            }
        }
        if ((state & 0b100000) != 0 /* is-active writeNV2Real(VirtualFrame, NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            assert (isNV2Real());
            return writeNV2Real(frameValue, valueValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active write(VirtualFrame, Object) */) {
            if (fallbackGuard_(state, valueValue_)) {
                return write(frameValue, valueValue_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
        int state = state_;
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            if ((isNV1Int())) {
                this.state_ = state = state | 0b1 /* add-active writeNV1Int(VirtualFrame, NV0Int) */;
                return writeNV1Int(frameValue, valueValue_);
            }
            if ((isNV2Int())) {
                this.state_ = state = state | 0b10 /* add-active writeNV2Int(VirtualFrame, NV0Int) */;
                return writeNV2Int(frameValue, valueValue_);
            }
        }
        if (valueValue instanceof NV1Int) {
            NV1Int valueValue_ = (NV1Int) valueValue;
            if ((isNV2Int())) {
                this.state_ = state = state | 0b100 /* add-active writeNV2Int(VirtualFrame, NV1Int) */;
                return writeNV2Int(frameValue, valueValue_);
            }
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            if ((isNV1Real())) {
                this.state_ = state = state | 0b1000 /* add-active writeNV1Real(VirtualFrame, NV0Real) */;
                return writeNV1Real(frameValue, valueValue_);
            }
            if ((isNV2Real())) {
                this.state_ = state = state | 0b10000 /* add-active writeNV2Real(VirtualFrame, NV0Real) */;
                return writeNV2Real(frameValue, valueValue_);
            }
        }
        if (valueValue instanceof NV1Real) {
            NV1Real valueValue_ = (NV1Real) valueValue;
            if ((isNV2Real())) {
                this.state_ = state = state | 0b100000 /* add-active writeNV2Real(VirtualFrame, NV1Real) */;
                return writeNV2Real(frameValue, valueValue_);
            }
        }
        this.state_ = state = state | 0b1000000 /* add-active write(VirtualFrame, Object) */;
        return write(frameValue, valueValue);
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
