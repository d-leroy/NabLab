// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.google.gson.JsonElement;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaInitializeVariableFromJsonNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Bool;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Bool;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;

@GeneratedBy(NablaInitializeVariableFromJsonNode.class)
public final class NablaInitializeVariableFromJsonNodeGen extends NablaInitializeVariableFromJsonNode {

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;

    private NablaInitializeVariableFromJsonNodeGen(JsonElement initialValue, NablaExpressionNode value) {
        super(initialValue);
        this.value_ = value;
    }

    private NablaInitializeVariableFromJsonNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active doInitialize(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10) != 0 /* is-active doInitialize(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100) != 0 /* is-active doInitialize(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000) != 0 /* is-active doInitialize(NV3Bool) */ && valueValue_ instanceof NV3Bool) {
            NV3Bool valueValue__ = (NV3Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000) != 0 /* is-active doInitialize(NV4Bool) */ && valueValue_ instanceof NV4Bool) {
            NV4Bool valueValue__ = (NV4Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000) != 0 /* is-active doInitialize(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000) != 0 /* is-active doInitialize(NV1Int) */ && valueValue_ instanceof NV1Int) {
            NV1Int valueValue__ = (NV1Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000000) != 0 /* is-active doInitialize(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000000) != 0 /* is-active doInitialize(NV3Int) */ && valueValue_ instanceof NV3Int) {
            NV3Int valueValue__ = (NV3Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000000) != 0 /* is-active doInitialize(NV4Int) */ && valueValue_ instanceof NV4Int) {
            NV4Int valueValue__ = (NV4Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000000000) != 0 /* is-active doInitialize(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000000000) != 0 /* is-active doInitialize(NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000000000) != 0 /* is-active doInitialize(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000000000000) != 0 /* is-active doInitialize(NV3Real) */ && valueValue_ instanceof NV3Real) {
            NV3Real valueValue__ = (NV3Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000000000000) != 0 /* is-active doInitialize(NV4Real) */ && valueValue_ instanceof NV4Real) {
            NV4Real valueValue__ = (NV4Real) valueValue_;
            return doInitialize(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    private Object executeAndSpecialize(Object valueValue) {
        int state = state_;
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_ = state = state | 0b1 /* add-active doInitialize(NV0Bool) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV1Bool) {
            NV1Bool valueValue_ = (NV1Bool) valueValue;
            this.state_ = state = state | 0b10 /* add-active doInitialize(NV1Bool) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV2Bool) {
            NV2Bool valueValue_ = (NV2Bool) valueValue;
            this.state_ = state = state | 0b100 /* add-active doInitialize(NV2Bool) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV3Bool) {
            NV3Bool valueValue_ = (NV3Bool) valueValue;
            this.state_ = state = state | 0b1000 /* add-active doInitialize(NV3Bool) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV4Bool) {
            NV4Bool valueValue_ = (NV4Bool) valueValue;
            this.state_ = state = state | 0b10000 /* add-active doInitialize(NV4Bool) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_ = state = state | 0b100000 /* add-active doInitialize(NV0Int) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV1Int) {
            NV1Int valueValue_ = (NV1Int) valueValue;
            this.state_ = state = state | 0b1000000 /* add-active doInitialize(NV1Int) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV2Int) {
            NV2Int valueValue_ = (NV2Int) valueValue;
            this.state_ = state = state | 0b10000000 /* add-active doInitialize(NV2Int) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV3Int) {
            NV3Int valueValue_ = (NV3Int) valueValue;
            this.state_ = state = state | 0b100000000 /* add-active doInitialize(NV3Int) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV4Int) {
            NV4Int valueValue_ = (NV4Int) valueValue;
            this.state_ = state = state | 0b1000000000 /* add-active doInitialize(NV4Int) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_ = state = state | 0b10000000000 /* add-active doInitialize(NV0Real) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV1Real) {
            NV1Real valueValue_ = (NV1Real) valueValue;
            this.state_ = state = state | 0b100000000000 /* add-active doInitialize(NV1Real) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            this.state_ = state = state | 0b1000000000000 /* add-active doInitialize(NV2Real) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV3Real) {
            NV3Real valueValue_ = (NV3Real) valueValue;
            this.state_ = state = state | 0b10000000000000 /* add-active doInitialize(NV3Real) */;
            return doInitialize(valueValue_);
        }
        if (valueValue instanceof NV4Real) {
            NV4Real valueValue_ = (NV4Real) valueValue;
            this.state_ = state = state | 0b100000000000000 /* add-active doInitialize(NV4Real) */;
            return doInitialize(valueValue_);
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

    public static NablaInitializeVariableFromJsonNode create(JsonElement initialValue, NablaExpressionNode value) {
        return new NablaInitializeVariableFromJsonNodeGen(initialValue, value);
    }

    public static NablaInitializeVariableFromJsonNode create(NablaExpressionNode value) {
        return new NablaInitializeVariableFromJsonNodeGen(value);
    }

}
