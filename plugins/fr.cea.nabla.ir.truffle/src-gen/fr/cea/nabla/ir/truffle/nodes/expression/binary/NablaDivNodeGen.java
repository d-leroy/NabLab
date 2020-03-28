// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaDivNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

@GeneratedBy(NablaDivNode.class)
public final class NablaDivNodeGen extends NablaDivNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;

    private NablaDivNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b11) != 0 /* is-active div(NV0Int, NV0Int) || div(NV0Int, NV0Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b1) != 0 /* is-active div(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10) != 0 /* is-active div(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b1100) != 0 /* is-active div(NV0Real, NV0Int) || div(NV0Real, NV0Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b100) != 0 /* is-active div(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000) != 0 /* is-active div(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11110000) != 0 /* is-active div(NV1Int, NV0Int) || div(NV1Int, NV0Real) || div(NV1Int, NV1Int) || div(NV1Int, NV1Real) */ && leftNodeValue_ instanceof NV1Int) {
            NV1Int leftNodeValue__ = (NV1Int) leftNodeValue_;
            if ((state & 0b10000) != 0 /* is-active div(NV1Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active div(NV1Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000) != 0 /* is-active div(NV1Int, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000) != 0 /* is-active div(NV1Int, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b111100000000) != 0 /* is-active div(NV1Real, NV0Int) || div(NV1Real, NV0Real) || div(NV1Real, NV1Int) || div(NV1Real, NV1Real) */ && leftNodeValue_ instanceof NV1Real) {
            NV1Real leftNodeValue__ = (NV1Real) leftNodeValue_;
            if ((state & 0b100000000) != 0 /* is-active div(NV1Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active div(NV1Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000) != 0 /* is-active div(NV1Real, NV1Int) */ && rightNodeValue_ instanceof NV1Int) {
                NV1Int rightNodeValue__ = (NV1Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000000) != 0 /* is-active div(NV1Real, NV1Real) */ && rightNodeValue_ instanceof NV1Real) {
                NV1Real rightNodeValue__ = (NV1Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b111000000000000) != 0 /* is-active div(NV2Int, NV0Int) || div(NV2Int, NV0Real) || div(NV2Int, NV2Int) */ && leftNodeValue_ instanceof NV2Int) {
            NV2Int leftNodeValue__ = (NV2Int) leftNodeValue_;
            if ((state & 0b1000000000000) != 0 /* is-active div(NV2Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000000000000) != 0 /* is-active div(NV2Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100000000000000) != 0 /* is-active div(NV2Int, NV2Int) */ && rightNodeValue_ instanceof NV2Int) {
                NV2Int rightNodeValue__ = (NV2Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0x38000) != 0 /* is-active div(NV2Real, NV0Int) || div(NV2Real, NV0Real) || div(NV2Real, NV2Real) */ && leftNodeValue_ instanceof NV2Real) {
            NV2Real leftNodeValue__ = (NV2Real) leftNodeValue_;
            if ((state & 0b1000000000000000) != 0 /* is-active div(NV2Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x10000) != 0 /* is-active div(NV2Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0x20000) != 0 /* is-active div(NV2Real, NV2Real) */ && rightNodeValue_ instanceof NV2Real) {
                NV2Real rightNodeValue__ = (NV2Real) rightNodeValue_;
                return div(leftNodeValue__, rightNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    private Object executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        int state = state_;
        if (leftNodeValue instanceof NV0Int) {
            NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b1 /* add-active div(NV0Int, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b10 /* add-active div(NV0Int, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV0Real) {
            NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b100 /* add-active div(NV0Real, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b1000 /* add-active div(NV0Real, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV1Int) {
            NV1Int leftNodeValue_ = (NV1Int) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b10000 /* add-active div(NV1Int, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b100000 /* add-active div(NV1Int, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV1Int) {
                NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                this.state_ = state = state | 0b1000000 /* add-active div(NV1Int, NV1Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV1Real) {
                NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                this.state_ = state = state | 0b10000000 /* add-active div(NV1Int, NV1Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV1Real) {
            NV1Real leftNodeValue_ = (NV1Real) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b100000000 /* add-active div(NV1Real, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b1000000000 /* add-active div(NV1Real, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV1Int) {
                NV1Int rightNodeValue_ = (NV1Int) rightNodeValue;
                this.state_ = state = state | 0b10000000000 /* add-active div(NV1Real, NV1Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV1Real) {
                NV1Real rightNodeValue_ = (NV1Real) rightNodeValue;
                this.state_ = state = state | 0b100000000000 /* add-active div(NV1Real, NV1Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV2Int) {
            NV2Int leftNodeValue_ = (NV2Int) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b1000000000000 /* add-active div(NV2Int, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b10000000000000 /* add-active div(NV2Int, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV2Int) {
                NV2Int rightNodeValue_ = (NV2Int) rightNodeValue;
                this.state_ = state = state | 0b100000000000000 /* add-active div(NV2Int, NV2Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV2Real) {
            NV2Real leftNodeValue_ = (NV2Real) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b1000000000000000 /* add-active div(NV2Real, NV0Int) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0x10000 /* add-active div(NV2Real, NV0Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV2Real) {
                NV2Real rightNodeValue_ = (NV2Real) rightNodeValue;
                this.state_ = state = state | 0x20000 /* add-active div(NV2Real, NV2Real) */;
                return div(leftNodeValue_, rightNodeValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.leftNode_, this.rightNode_}, leftNodeValue, rightNodeValue);
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

    public static NablaDivNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaDivNodeGen(leftNode, rightNode);
    }

}
