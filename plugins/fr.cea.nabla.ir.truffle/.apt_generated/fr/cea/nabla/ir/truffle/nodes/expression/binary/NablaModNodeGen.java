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
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaModNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

@GeneratedBy(NablaModNode.class)
public final class NablaModNodeGen extends NablaModNode {

    @Child private NablaExpressionNode leftNode_;
    @Child private NablaExpressionNode rightNode_;
    @CompilationFinal private int state_;

    private NablaModNodeGen(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        this.leftNode_ = leftNode;
        this.rightNode_ = rightNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object leftNodeValue_ = this.leftNode_.executeGeneric(frameValue);
        Object rightNodeValue_ = this.rightNode_.executeGeneric(frameValue);
        if (state != 0 /* is-active mod(NV0Int, NV0Int) */ && leftNodeValue_ instanceof NV0Int) {
            NV0Int leftNodeValue__ = (NV0Int) leftNodeValue_;
            if (rightNodeValue_ instanceof NV0Int) {
                NV0Int rightNodeValue__ = (NV0Int) rightNodeValue_;
                return mod(leftNodeValue__, rightNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(leftNodeValue_, rightNodeValue_);
    }

    private NV0Int executeAndSpecialize(Object leftNodeValue, Object rightNodeValue) {
        int state = state_;
        if (leftNodeValue instanceof NV0Int) {
            NV0Int leftNodeValue_ = (NV0Int) leftNodeValue;
            if (rightNodeValue instanceof NV0Int) {
                NV0Int rightNodeValue_ = (NV0Int) rightNodeValue;
                this.state_ = state = state | 0b1 /* add-active mod(NV0Int, NV0Int) */;
                return mod(leftNodeValue_, rightNodeValue_);
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

    public static NablaModNode create(NablaExpressionNode leftNode, NablaExpressionNode rightNode) {
        return new NablaModNodeGen(leftNode, rightNode);
    }

}
