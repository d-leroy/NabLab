// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.unary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.unary.NablaAbsNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;

@GeneratedBy(NablaAbsNode.class)
public final class NablaAbsNodeGen extends NablaAbsNode {

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private int state_;

    private NablaAbsNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active abs(NV0Int) */ && valueNodeValue_ instanceof NV0Int) {
            NV0Int valueNodeValue__ = (NV0Int) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b10) != 0 /* is-active abs(NV0Real) */ && valueNodeValue_ instanceof NV0Real) {
            NV0Real valueNodeValue__ = (NV0Real) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b100) != 0 /* is-active abs(NV1Int) */ && valueNodeValue_ instanceof NV1Int) {
            NV1Int valueNodeValue__ = (NV1Int) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b1000) != 0 /* is-active abs(NV1Real) */ && valueNodeValue_ instanceof NV1Real) {
            NV1Real valueNodeValue__ = (NV1Real) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b10000) != 0 /* is-active abs(NV2Int) */ && valueNodeValue_ instanceof NV2Int) {
            NV2Int valueNodeValue__ = (NV2Int) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        if ((state & 0b100000) != 0 /* is-active abs(NV2Real) */ && valueNodeValue_ instanceof NV2Real) {
            NV2Real valueNodeValue__ = (NV2Real) valueNodeValue_;
            return abs(valueNodeValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueNodeValue_);
    }

    private Object executeAndSpecialize(Object valueNodeValue) {
        int state = state_;
        if (valueNodeValue instanceof NV0Int) {
            NV0Int valueNodeValue_ = (NV0Int) valueNodeValue;
            this.state_ = state = state | 0b1 /* add-active abs(NV0Int) */;
            return abs(valueNodeValue_);
        }
        if (valueNodeValue instanceof NV0Real) {
            NV0Real valueNodeValue_ = (NV0Real) valueNodeValue;
            this.state_ = state = state | 0b10 /* add-active abs(NV0Real) */;
            return abs(valueNodeValue_);
        }
        if (valueNodeValue instanceof NV1Int) {
            NV1Int valueNodeValue_ = (NV1Int) valueNodeValue;
            this.state_ = state = state | 0b100 /* add-active abs(NV1Int) */;
            return abs(valueNodeValue_);
        }
        if (valueNodeValue instanceof NV1Real) {
            NV1Real valueNodeValue_ = (NV1Real) valueNodeValue;
            this.state_ = state = state | 0b1000 /* add-active abs(NV1Real) */;
            return abs(valueNodeValue_);
        }
        if (valueNodeValue instanceof NV2Int) {
            NV2Int valueNodeValue_ = (NV2Int) valueNodeValue;
            this.state_ = state = state | 0b10000 /* add-active abs(NV2Int) */;
            return abs(valueNodeValue_);
        }
        if (valueNodeValue instanceof NV2Real) {
            NV2Real valueNodeValue_ = (NV2Real) valueNodeValue;
            this.state_ = state = state | 0b100000 /* add-active abs(NV2Real) */;
            return abs(valueNodeValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.valueNode_}, valueNodeValue);
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

    public static NablaAbsNode create(NablaExpressionNode valueNode) {
        return new NablaAbsNodeGen(valueNode);
    }

}
