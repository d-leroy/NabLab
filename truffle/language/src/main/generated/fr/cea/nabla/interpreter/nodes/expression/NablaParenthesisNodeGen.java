// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;

@GeneratedBy(NablaParenthesisNode.class)
public final class NablaParenthesisNodeGen extends NablaParenthesisNode {

    @Child private NablaExpressionNode expression_;
    @CompilationFinal private int state_0_;

    private NablaParenthesisNodeGen(NablaExpressionNode expression) {
        this.expression_ = expression;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object expressionValue_ = this.expression_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 executeNV0Bool(VirtualFrame, NV0Bool) */ && expressionValue_ instanceof NV0Bool) {
            NV0Bool expressionValue__ = (NV0Bool) expressionValue_;
            return executeNV0Bool(frameValue, expressionValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 executeNV0Int(VirtualFrame, NV0Int) */ && expressionValue_ instanceof NV0Int) {
            NV0Int expressionValue__ = (NV0Int) expressionValue_;
            return executeNV0Int(frameValue, expressionValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 executeNV0Real(VirtualFrame, NV0Real) */ && expressionValue_ instanceof NV0Real) {
            NV0Real expressionValue__ = (NV0Real) expressionValue_;
            return executeNV0Real(frameValue, expressionValue__);
        }
        if ((state_0 & 0b1000) != 0 /* is-state_0 executeNV1Bool(VirtualFrame, NV1Bool) */ && expressionValue_ instanceof NV1Bool) {
            NV1Bool expressionValue__ = (NV1Bool) expressionValue_;
            return executeNV1Bool(frameValue, expressionValue__);
        }
        if ((state_0 & 0b10000) != 0 /* is-state_0 executeNV1Int(VirtualFrame, NV1Int) */ && expressionValue_ instanceof NV1Int) {
            NV1Int expressionValue__ = (NV1Int) expressionValue_;
            return executeNV1Int(frameValue, expressionValue__);
        }
        if ((state_0 & 0b100000) != 0 /* is-state_0 executeNV1Real(VirtualFrame, NV1Real) */ && expressionValue_ instanceof NV1Real) {
            NV1Real expressionValue__ = (NV1Real) expressionValue_;
            return executeNV1Real(frameValue, expressionValue__);
        }
        if ((state_0 & 0b1000000) != 0 /* is-state_0 executeNV2Bool(VirtualFrame, NV2Bool) */ && expressionValue_ instanceof NV2Bool) {
            NV2Bool expressionValue__ = (NV2Bool) expressionValue_;
            return executeNV2Bool(frameValue, expressionValue__);
        }
        if ((state_0 & 0b10000000) != 0 /* is-state_0 executeNV2Int(VirtualFrame, NV2Int) */ && expressionValue_ instanceof NV2Int) {
            NV2Int expressionValue__ = (NV2Int) expressionValue_;
            return executeNV2Int(frameValue, expressionValue__);
        }
        if ((state_0 & 0b100000000) != 0 /* is-state_0 executeNV2Real(VirtualFrame, NV2Real) */ && expressionValue_ instanceof NV2Real) {
            NV2Real expressionValue__ = (NV2Real) expressionValue_;
            return executeNV2Real(frameValue, expressionValue__);
        }
        if ((state_0 & 0b1000000000) != 0 /* is-state_0 executeNV3Int(VirtualFrame, NV3Int) */ && expressionValue_ instanceof NV3Int) {
            NV3Int expressionValue__ = (NV3Int) expressionValue_;
            return executeNV3Int(frameValue, expressionValue__);
        }
        if ((state_0 & 0b10000000000) != 0 /* is-state_0 executeNV3Real(VirtualFrame, NV3Real) */ && expressionValue_ instanceof NV3Real) {
            NV3Real expressionValue__ = (NV3Real) expressionValue_;
            return executeNV3Real(frameValue, expressionValue__);
        }
        if ((state_0 & 0b100000000000) != 0 /* is-state_0 executeNV4Int(VirtualFrame, NV4Int) */ && expressionValue_ instanceof NV4Int) {
            NV4Int expressionValue__ = (NV4Int) expressionValue_;
            return executeNV4Int(frameValue, expressionValue__);
        }
        if ((state_0 & 0b1000000000000) != 0 /* is-state_0 executeNV4Real(VirtualFrame, NV4Real) */ && expressionValue_ instanceof NV4Real) {
            NV4Real expressionValue__ = (NV4Real) expressionValue_;
            return executeNV4Real(frameValue, expressionValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, expressionValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object expressionValue) {
        int state_0 = state_0_;
        if (expressionValue instanceof NV0Bool) {
            NV0Bool expressionValue_ = (NV0Bool) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 executeNV0Bool(VirtualFrame, NV0Bool) */;
            return executeNV0Bool(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV0Int) {
            NV0Int expressionValue_ = (NV0Int) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 executeNV0Int(VirtualFrame, NV0Int) */;
            return executeNV0Int(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV0Real) {
            NV0Real expressionValue_ = (NV0Real) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 executeNV0Real(VirtualFrame, NV0Real) */;
            return executeNV0Real(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV1Bool) {
            NV1Bool expressionValue_ = (NV1Bool) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 executeNV1Bool(VirtualFrame, NV1Bool) */;
            return executeNV1Bool(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV1Int) {
            NV1Int expressionValue_ = (NV1Int) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 executeNV1Int(VirtualFrame, NV1Int) */;
            return executeNV1Int(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV1Real) {
            NV1Real expressionValue_ = (NV1Real) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 executeNV1Real(VirtualFrame, NV1Real) */;
            return executeNV1Real(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV2Bool) {
            NV2Bool expressionValue_ = (NV2Bool) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 executeNV2Bool(VirtualFrame, NV2Bool) */;
            return executeNV2Bool(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV2Int) {
            NV2Int expressionValue_ = (NV2Int) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 executeNV2Int(VirtualFrame, NV2Int) */;
            return executeNV2Int(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV2Real) {
            NV2Real expressionValue_ = (NV2Real) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 executeNV2Real(VirtualFrame, NV2Real) */;
            return executeNV2Real(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV3Int) {
            NV3Int expressionValue_ = (NV3Int) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 executeNV3Int(VirtualFrame, NV3Int) */;
            return executeNV3Int(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV3Real) {
            NV3Real expressionValue_ = (NV3Real) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 executeNV3Real(VirtualFrame, NV3Real) */;
            return executeNV3Real(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV4Int) {
            NV4Int expressionValue_ = (NV4Int) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 executeNV4Int(VirtualFrame, NV4Int) */;
            return executeNV4Int(frameValue, expressionValue_);
        }
        if (expressionValue instanceof NV4Real) {
            NV4Real expressionValue_ = (NV4Real) expressionValue;
            this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 executeNV4Real(VirtualFrame, NV4Real) */;
            return executeNV4Real(frameValue, expressionValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.expression_}, expressionValue);
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

    public static NablaParenthesisNode create(NablaExpressionNode expression) {
        return new NablaParenthesisNodeGen(expression);
    }

}
