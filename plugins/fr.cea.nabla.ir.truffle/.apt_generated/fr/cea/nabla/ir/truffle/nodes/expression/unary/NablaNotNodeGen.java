// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression.unary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.unary.NablaNotNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;

@GeneratedBy(NablaNotNode.class)
public final class NablaNotNodeGen extends NablaNotNode {

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private int state_;

    private NablaNotNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active not(NV0Bool) */ && valueNodeValue_ instanceof NV0Bool) {
            NV0Bool valueNodeValue__ = (NV0Bool) valueNodeValue_;
            return not(valueNodeValue__);
        }
        if ((state & 0b10) != 0 /* is-active typeError(Object) */) {
            if (fallbackGuard_(state, valueNodeValue_)) {
                return typeError(valueNodeValue_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueNodeValue_);
    }

    private Object executeAndSpecialize(Object valueNodeValue) {
        int state = state_;
        if (valueNodeValue instanceof NV0Bool) {
            NV0Bool valueNodeValue_ = (NV0Bool) valueNodeValue;
            this.state_ = state = state | 0b1 /* add-active not(NV0Bool) */;
            return not(valueNodeValue_);
        }
        this.state_ = state = state | 0b10 /* add-active typeError(Object) */;
        return typeError(valueNodeValue);
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

    private static boolean fallbackGuard_(int state, Object valueNodeValue) {
        if (((state & 0b1)) == 0 /* is-not-active not(NV0Bool) */ && valueNodeValue instanceof NV0Bool) {
            return false;
        }
        return true;
    }

    public static NablaNotNode create(NablaExpressionNode valueNode) {
        return new NablaNotNodeGen(valueNode);
    }

}
