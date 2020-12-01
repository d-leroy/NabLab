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
import fr.cea.nabla.interpreter.values.NV0Bool;

@GeneratedBy(NablaBinaryBooleanNode.class)
public final class NablaBinaryBooleanNodeGen extends NablaBinaryBooleanNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;

    private NablaBinaryBooleanNodeGen(BooleanOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        super(operator);
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if (state != 0 /* is-active and(NV0Bool, NV0Bool) */ && leftNodeValue_ instanceof NV0Bool) {
            NV0Bool leftNodeValue__ = (NV0Bool) leftNodeValue_;
            if (rightNodeValue_ instanceof NV0Bool) {
                NV0Bool rightNodeValue__ = (NV0Bool) rightNodeValue_;
                return and(leftNodeValue__, rightNodeValue__);
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
                this.state_ = state = state | 0b1 /* add-active and(NV0Bool, NV0Bool) */;
                return and(leftNodeValue_, rightNodeValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.leftNode_, this.rightNode_}, leftNodeValue, rightNodeValue);
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaBinaryBooleanNode create(BooleanOperator operator, NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaBinaryBooleanNodeGen(operator, leftNode, rightNode);
    }

}
