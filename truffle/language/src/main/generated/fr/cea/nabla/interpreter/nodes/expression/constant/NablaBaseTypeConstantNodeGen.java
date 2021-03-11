// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;

@GeneratedBy(NablaBaseTypeConstantNode.class)
public final class NablaBaseTypeConstantNodeGen extends NablaBaseTypeConstantNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_0_;

    private NablaBaseTypeConstantNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 get(VirtualFrame, NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return get(frameValue, valueValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 get(VirtualFrame, NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return get(frameValue, valueValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 get(VirtualFrame, NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return get(frameValue, valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue) {
        int state_0 = state_0_;
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 get(VirtualFrame, NV0Bool) */;
            return get(frameValue, valueValue_);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 get(VirtualFrame, NV0Int) */;
            return get(frameValue, valueValue_);
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 get(VirtualFrame, NV0Real) */;
            return get(frameValue, valueValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
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

    public static NablaBaseTypeConstantNode create(NablaExpressionNode value) {
        return new NablaBaseTypeConstantNodeGen(value);
    }

}
