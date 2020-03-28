// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaIndexOfNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Int;

@GeneratedBy(NablaIndexOfNode.class)
public final class NablaIndexOfNodeGen extends NablaIndexOfNode {

    @Child private NablaExpressionNode arrayNode_;
    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;

    private NablaIndexOfNodeGen(NablaExpressionNode arrayNode, NablaExpressionNode value) {
        this.arrayNode_ = arrayNode;
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object arrayNodeValue_ = this.arrayNode_.executeGeneric(frameValue);
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if (state != 0 /* is-active readNV1Int1Index(VirtualFrame, NV1Int, NV0Int) */ && arrayNodeValue_ instanceof NV1Int) {
            NV1Int arrayNodeValue__ = (NV1Int) arrayNodeValue_;
            if (valueValue_ instanceof NV0Int) {
                NV0Int valueValue__ = (NV0Int) valueValue_;
                return readNV1Int1Index(frameValue, arrayNodeValue__, valueValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, arrayNodeValue_, valueValue_);
    }

    private NV0Int executeAndSpecialize(VirtualFrame frameValue, Object arrayNodeValue, Object valueValue) {
        int state = state_;
        if (arrayNodeValue instanceof NV1Int) {
            NV1Int arrayNodeValue_ = (NV1Int) arrayNodeValue;
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                this.state_ = state = state | 0b1 /* add-active readNV1Int1Index(VirtualFrame, NV1Int, NV0Int) */;
                return readNV1Int1Index(frameValue, arrayNodeValue_, valueValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.arrayNode_, this.value_}, arrayNodeValue, valueValue);
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

    public static NablaIndexOfNode create(NablaExpressionNode arrayNode, NablaExpressionNode value) {
        return new NablaIndexOfNodeGen(arrayNode, value);
    }

}
