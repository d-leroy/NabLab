// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV0Int;
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
import fr.cea.nabla.interpreter.values.NablaValue;

@GeneratedBy(NablaReadArrayDimensionNode.class)
public final class NablaReadArrayDimensionNodeGen extends NablaReadArrayDimensionNode {

    @Child private NablaExpressionNode array_;
    @CompilationFinal private int state_0_;

    private NablaReadArrayDimensionNodeGen(int index, NablaExpressionNode array) {
        super(index);
        this.array_ = array;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object arrayValue_ = this.array_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 readDimension(VirtualFrame, NV1Bool) */ && arrayValue_ instanceof NV1Bool) {
            NV1Bool arrayValue__ = (NV1Bool) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 readDimension(VirtualFrame, NV2Bool) */ && arrayValue_ instanceof NV2Bool) {
            NV2Bool arrayValue__ = (NV2Bool) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 readDimension(VirtualFrame, NV3Bool) */ && arrayValue_ instanceof NV3Bool) {
            NV3Bool arrayValue__ = (NV3Bool) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b1000) != 0 /* is-state_0 readDimension(VirtualFrame, NV4Bool) */ && arrayValue_ instanceof NV4Bool) {
            NV4Bool arrayValue__ = (NV4Bool) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b10000) != 0 /* is-state_0 readDimension(VirtualFrame, NV1Int) */ && arrayValue_ instanceof NV1Int) {
            NV1Int arrayValue__ = (NV1Int) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b100000) != 0 /* is-state_0 readDimension(VirtualFrame, NV2Int) */ && arrayValue_ instanceof NV2Int) {
            NV2Int arrayValue__ = (NV2Int) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b1000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV3Int) */ && arrayValue_ instanceof NV3Int) {
            NV3Int arrayValue__ = (NV3Int) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b10000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV4Int) */ && arrayValue_ instanceof NV4Int) {
            NV4Int arrayValue__ = (NV4Int) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b100000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV1Real) */ && arrayValue_ instanceof NV1Real) {
            NV1Real arrayValue__ = (NV1Real) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b1000000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV2Real) */ && arrayValue_ instanceof NV2Real) {
            NV2Real arrayValue__ = (NV2Real) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b10000000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV3Real) */ && arrayValue_ instanceof NV3Real) {
            NV3Real arrayValue__ = (NV3Real) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b100000000000) != 0 /* is-state_0 readDimension(VirtualFrame, NV4Real) */ && arrayValue_ instanceof NV4Real) {
            NV4Real arrayValue__ = (NV4Real) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        if ((state_0 & 0b1000000000000) != 0 /* is-state_0 readDimension(VirtualFrame, NablaValue) */ && arrayValue_ instanceof NablaValue) {
            NablaValue arrayValue__ = (NablaValue) arrayValue_;
            return readDimension(frameValue, arrayValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, arrayValue_);
    }

    private NV0Int executeAndSpecialize(VirtualFrame frameValue, Object arrayValue) {
        int state_0 = state_0_;
        if (arrayValue instanceof NV1Bool) {
            NV1Bool arrayValue_ = (NV1Bool) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 readDimension(VirtualFrame, NV1Bool) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV2Bool) {
            NV2Bool arrayValue_ = (NV2Bool) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 readDimension(VirtualFrame, NV2Bool) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV3Bool) {
            NV3Bool arrayValue_ = (NV3Bool) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 readDimension(VirtualFrame, NV3Bool) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV4Bool) {
            NV4Bool arrayValue_ = (NV4Bool) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 readDimension(VirtualFrame, NV4Bool) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV1Int) {
            NV1Int arrayValue_ = (NV1Int) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 readDimension(VirtualFrame, NV1Int) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV2Int) {
            NV2Int arrayValue_ = (NV2Int) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 readDimension(VirtualFrame, NV2Int) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV3Int) {
            NV3Int arrayValue_ = (NV3Int) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 readDimension(VirtualFrame, NV3Int) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV4Int) {
            NV4Int arrayValue_ = (NV4Int) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 readDimension(VirtualFrame, NV4Int) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV1Real) {
            NV1Real arrayValue_ = (NV1Real) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 readDimension(VirtualFrame, NV1Real) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV2Real) {
            NV2Real arrayValue_ = (NV2Real) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 readDimension(VirtualFrame, NV2Real) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV3Real) {
            NV3Real arrayValue_ = (NV3Real) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 readDimension(VirtualFrame, NV3Real) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NV4Real) {
            NV4Real arrayValue_ = (NV4Real) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 readDimension(VirtualFrame, NV4Real) */;
            return readDimension(frameValue, arrayValue_);
        }
        if (arrayValue instanceof NablaValue) {
            NablaValue arrayValue_ = (NablaValue) arrayValue;
            this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 readDimension(VirtualFrame, NablaValue) */;
            return readDimension(frameValue, arrayValue_);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.array_}, arrayValue);
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

    public static NablaReadArrayDimensionNode create(int index, NablaExpressionNode array) {
        return new NablaReadArrayDimensionNodeGen(index, array);
    }

}
