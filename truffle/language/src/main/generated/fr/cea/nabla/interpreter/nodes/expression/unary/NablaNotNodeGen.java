// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.unary;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Bool;

@GeneratedBy(NablaNotNode.class)
public final class NablaNotNodeGen extends NablaNotNode {

    @Child private NablaExpressionNode valueNode_;
    @CompilationFinal private int state_0_;

    private NablaNotNodeGen(NablaExpressionNode valueNode) {
        this.valueNode_ = valueNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueNodeValue_ = this.valueNode_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 not(NV0Bool) */ && valueNodeValue_ instanceof NV0Bool) {
            NV0Bool valueNodeValue__ = (NV0Bool) valueNodeValue_;
            return not(valueNodeValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 typeError(Object) */) {
            if (fallbackGuard_(state_0, valueNodeValue_)) {
                return typeError(valueNodeValue_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueNodeValue_);
    }

    private Object executeAndSpecialize(Object valueNodeValue) {
        int state_0 = state_0_;
        if (valueNodeValue instanceof NV0Bool) {
            NV0Bool valueNodeValue_ = (NV0Bool) valueNodeValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 not(NV0Bool) */;
            return not(valueNodeValue_);
        }
        this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 typeError(Object) */;
        return typeError(valueNodeValue);
    }

    @Override
    public NodeCost getCost() {
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            if ((state_0 & (state_0 - 1)) == 0 /* is-single-state_0  */) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    private static boolean fallbackGuard_(int state_0, Object valueNodeValue) {
        if (((state_0 & 0b1)) == 0 /* is-not-state_0 not(NV0Bool) */ && valueNodeValue instanceof NV0Bool) {
            return false;
        }
        return true;
    }

    public static NablaNotNode create(NablaExpressionNode valueNode) {
        return new NablaNotNodeGen(valueNode);
    }

}
