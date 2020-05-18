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
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.UnboxValueNode;

@GeneratedBy(UnboxValueNode.class)
public final class UnboxValueNodeGen extends UnboxValueNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;

    private UnboxValueNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @Override
    public Object execute(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active unbox(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b10) != 0 /* is-active unbox(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b100) != 0 /* is-active unbox(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b1000) != 0 /* is-active unbox(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b10000) != 0 /* is-active unbox(NV1Int) */ && valueValue_ instanceof NV1Int) {
            NV1Int valueValue__ = (NV1Int) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b100000) != 0 /* is-active unbox(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active unbox(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b10000000) != 0 /* is-active unbox(NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            return unbox(valueValue__);
        }
        if ((state & 0b100000000) != 0 /* is-active unbox(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return unbox(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    private Object executeAndSpecialize(Object valueValue) {
        int state = state_;
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_ = state = state | 0b1 /* add-active unbox(NV0Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1Bool) {
            NV1Bool valueValue_ = (NV1Bool) valueValue;
            this.state_ = state = state | 0b10 /* add-active unbox(NV1Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Bool) {
            NV2Bool valueValue_ = (NV2Bool) valueValue;
            this.state_ = state = state | 0b100 /* add-active unbox(NV2Bool) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_ = state = state | 0b1000 /* add-active unbox(NV0Int) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1Int) {
            NV1Int valueValue_ = (NV1Int) valueValue;
            this.state_ = state = state | 0b10000 /* add-active unbox(NV1Int) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Int) {
            NV2Int valueValue_ = (NV2Int) valueValue;
            this.state_ = state = state | 0b100000 /* add-active unbox(NV2Int) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_ = state = state | 0b1000000 /* add-active unbox(NV0Real) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV1Real) {
            NV1Real valueValue_ = (NV1Real) valueValue;
            this.state_ = state = state | 0b10000000 /* add-active unbox(NV1Real) */;
            return unbox(valueValue_);
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            this.state_ = state = state | 0b100000000 /* add-active unbox(NV2Real) */;
            return unbox(valueValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
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

    public static UnboxValueNode create(NablaExpressionNode value) {
        return new UnboxValueNodeGen(value);
    }

}
