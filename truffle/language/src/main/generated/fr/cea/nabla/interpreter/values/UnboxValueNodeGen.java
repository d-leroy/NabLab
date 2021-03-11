// CheckStyle: start generated
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;

@GeneratedBy(UnboxValueNode.class)
public final class UnboxValueNodeGen extends UnboxValueNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_0_;

    private UnboxValueNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @Override
    public Object execute(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 unbox(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 unbox(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 unbox(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b1000) != 0 /* is-state_0 unbox(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b10000) != 0 /* is-state_0 unbox(NV1IntJava) */ && valueValue_ instanceof NV1IntJava) {
            NV1IntJava valueValue__ = (NV1IntJava) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b100000) != 0 /* is-state_0 unbox(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b1000000) != 0 /* is-state_0 unbox(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b10000000) != 0 /* is-state_0 unbox(NV1RealJava) */ && valueValue_ instanceof NV1RealJava) {
            NV1RealJava valueValue__ = (NV1RealJava) valueValue_;
            return unbox(valueValue__);
        }
        if ((state_0 & 0b100000000) != 0 /* is-state_0 unbox(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return unbox(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    private Object executeAndSpecialize(Object valueValue) {
        int state_0 = state_0_;
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 unbox(NV0Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1Bool) {
            NV1Bool valueValue_ = (NV1Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 unbox(NV1Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Bool) {
            NV2Bool valueValue_ = (NV2Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 unbox(NV2Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 unbox(NV0Int) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1IntJava) {
            NV1IntJava valueValue_ = (NV1IntJava) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 unbox(NV1IntJava) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Int) {
            NV2Int valueValue_ = (NV2Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 unbox(NV2Int) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 unbox(NV0Real) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1RealJava) {
            NV1RealJava valueValue_ = (NV1RealJava) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 unbox(NV1RealJava) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 unbox(NV2Real) */;
            return unbox(valueValue_);
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

    public static UnboxValueNode create(NablaExpressionNode value) {
        return new UnboxValueNodeGen(value);
    }

}
