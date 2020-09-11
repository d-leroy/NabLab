// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.binary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.binary.NablaLeqNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;

@GeneratedBy(NablaLeqNode.class)
public final class NablaLeqNodeGen extends NablaLeqNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;

    private NablaLeqNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active add(NV0Bool, NV0Bool) */ && leftNodeValue_ instanceof NV0Bool) {
            NV0Bool leftNodeValue__ = (NV0Bool) leftNodeValue_;
            if (rightNodeValue_ instanceof NV0Bool) {
                NV0Bool rightNodeValue__ = (NV0Bool) rightNodeValue_;
                return add(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b110) != 0 /* is-active leq(NV0Int, NV0Int) || leq(NV0Int, NV0Real) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if ((state & 0b10) != 0 /* is-active leq(NV0Int, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return leq(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b100) != 0 /* is-active leq(NV0Int, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return leq(leftNodeValue__, rightNodeValue__);
            }
        }
        if ((state & 0b11000) != 0 /* is-active leq(NV0Real, NV0Int) || leq(NV0Real, NV0Real) */ && leftNodeValue_ instanceof NV0Real) {
            NV0Real leftNodeValue__ = (NV0Real) leftNodeValue_;
            if ((state & 0b1000) != 0 /* is-active leq(NV0Real, NV0Int) */ && rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return leq(leftNodeValue__, rightNodeValue__);
            }
            if ((state & 0b10000) != 0 /* is-active leq(NV0Real, NV0Real) */ && rightNodeValue_ instanceof NV0Real) {
                NV0Real rightNodeValue__ = (NV0Real) rightNodeValue_;
                return leq(leftNodeValue__, rightNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    private NV0Bool executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        int state = state_;
        if (leftNodeValue instanceof NV0Bool) {
            NV0Bool leftNodeValue_ = (NV0Bool) leftNodeValue;
            if (rightNodeValue instanceof NV0Bool) {
                NV0Bool rightNodeValue_ = (NV0Bool) rightNodeValue;
                this.state_ = state = state | 0b1 /* add-active add(NV0Bool, NV0Bool) */;
                return add(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV0Int) {
            NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b10 /* add-active leq(NV0Int, NV0Int) */;
                return leq(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b100 /* add-active leq(NV0Int, NV0Real) */;
                return leq(leftNodeValue_, rightNodeValue_);
            }
        }
        if (leftNodeValue instanceof NV0Real) {
            NV0Real leftNodeValue_ = (NV0Real) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b1000 /* add-active leq(NV0Real, NV0Int) */;
                return leq(leftNodeValue_, rightNodeValue_);
            }
            if (rightNodeValue instanceof NV0Real) {
                NV0Real rightNodeValue_ = (NV0Real) rightNodeValue;
                this.state_ = state = state | 0b10000 /* add-active leq(NV0Real, NV0Real) */;
                return leq(leftNodeValue_, rightNodeValue_);
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

    public static NablaLeqNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaLeqNodeGen(leftNode, rightNode);
    }

}
