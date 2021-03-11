// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

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

@GeneratedBy(NablaReturnNode.class)
public final class NablaReturnNodeGen extends NablaReturnNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_0_;

    private NablaReturnNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 doReal(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return doReal(valueValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 doReal1(NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            return doReal1(valueValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 doBool(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return doBool(valueValue__);
        }
        if ((state_0 & 0b1000) != 0 /* is-state_0 doBool1(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return doBool1(valueValue__);
        }
        if ((state_0 & 0b10000) != 0 /* is-state_0 doBool2(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return doBool2(valueValue__);
        }
        if ((state_0 & 0b100000) != 0 /* is-state_0 doInt(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return doInt(valueValue__);
        }
        if ((state_0 & 0b1000000) != 0 /* is-state_0 doInt1(NV1Int) */ && valueValue_ instanceof NV1Int) {
            NV1Int valueValue__ = (NV1Int) valueValue_;
            return doInt1(valueValue__);
        }
        if ((state_0 & 0b10000000) != 0 /* is-state_0 doInt2(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return doInt2(valueValue__);
        }
        if ((state_0 & 0b100000000) != 0 /* is-state_0 doReal2(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return doReal2(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    private Object executeAndSpecialize(Object valueValue) {
        int state_0 = state_0_;
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doReal(NV0Real) */;
            return doReal(valueValue_);
        }
        if (valueValue instanceof NV1Real) {
            NV1Real valueValue_ = (NV1Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doReal1(NV1Real) */;
            return doReal1(valueValue_);
        }
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doBool(NV0Bool) */;
            return doBool(valueValue_);
        }
        if (valueValue instanceof NV1Bool) {
            NV1Bool valueValue_ = (NV1Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doBool1(NV1Bool) */;
            return doBool1(valueValue_);
        }
        if (valueValue instanceof NV2Bool) {
            NV2Bool valueValue_ = (NV2Bool) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doBool2(NV2Bool) */;
            return doBool2(valueValue_);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doInt(NV0Int) */;
            return doInt(valueValue_);
        }
        if (valueValue instanceof NV1Int) {
            NV1Int valueValue_ = (NV1Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 doInt1(NV1Int) */;
            return doInt1(valueValue_);
        }
        if (valueValue instanceof NV2Int) {
            NV2Int valueValue_ = (NV2Int) valueValue;
            this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 doInt2(NV2Int) */;
            return doInt2(valueValue_);
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 doReal2(NV2Real) */;
            return doReal2(valueValue_);
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

    public static NablaReturnNode create(NablaExpressionNode value) {
        return new NablaReturnNodeGen(value);
    }

}
