// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadArrayNode;
import fr.cea.nabla.ir.truffle.values.NV1Bool;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Bool;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

@GeneratedBy(NablaReadArrayNode.class)
public final class NablaReadArrayNodeGen extends NablaReadArrayNode {

    @Child private NablaExpressionNode arrayNode_;
    @CompilationFinal private int state_;

    private NablaReadArrayNodeGen(NablaExpressionNode[] indices, NablaExpressionNode arrayNode) {
        super(indices);
        this.arrayNode_ = arrayNode;
    }

    @Override
    protected NablaExpressionNode getArrayNode() {
        return this.arrayNode_;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object arrayNodeValue_ = this.arrayNode_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active readNV1Int1Index(VirtualFrame, NV1Int) */ && arrayNodeValue_ instanceof NV1Int) {
            NV1Int arrayNodeValue__ = (NV1Int) arrayNodeValue_;
            assert (indices.length == 1);
            return readNV1Int1Index(frameValue, arrayNodeValue__);
        }
        if ((state & 0b110) != 0 /* is-active readNV2Int1Index(VirtualFrame, NV2Int) || readNV2Int2Indices(VirtualFrame, NV2Int) */ && arrayNodeValue_ instanceof NV2Int) {
            NV2Int arrayNodeValue__ = (NV2Int) arrayNodeValue_;
            if ((state & 0b10) != 0 /* is-active readNV2Int1Index(VirtualFrame, NV2Int) */) {
                assert (indices.length == 1);
                return readNV2Int1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b100) != 0 /* is-active readNV2Int2Indices(VirtualFrame, NV2Int) */) {
                assert (indices.length == 2);
                return readNV2Int2Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b1000) != 0 /* is-active readNV1Real1Index(VirtualFrame, NV1Real) */ && arrayNodeValue_ instanceof NV1Real) {
            NV1Real arrayNodeValue__ = (NV1Real) arrayNodeValue_;
            assert (indices.length == 1);
            return readNV1Real1Index(frameValue, arrayNodeValue__);
        }
        if ((state & 0b110000) != 0 /* is-active readNV2Real1Index(VirtualFrame, NV2Real) || readNV2Real2Indices(VirtualFrame, NV2Real) */ && arrayNodeValue_ instanceof NV2Real) {
            NV2Real arrayNodeValue__ = (NV2Real) arrayNodeValue_;
            if ((state & 0b10000) != 0 /* is-active readNV2Real1Index(VirtualFrame, NV2Real) */) {
                assert (indices.length == 1);
                return readNV2Real1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b100000) != 0 /* is-active readNV2Real2Indices(VirtualFrame, NV2Real) */) {
                assert (indices.length == 2);
                return readNV2Real2Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b1000000) != 0 /* is-active readNV1Bool1Index(VirtualFrame, NV1Bool) */ && arrayNodeValue_ instanceof NV1Bool) {
            NV1Bool arrayNodeValue__ = (NV1Bool) arrayNodeValue_;
            assert (indices.length == 1);
            return readNV1Bool1Index(frameValue, arrayNodeValue__);
        }
        if ((state & 0b110000000) != 0 /* is-active readNV2Bool1Index(VirtualFrame, NV2Bool) || readNV2Bool2Indices(VirtualFrame, NV2Bool) */ && arrayNodeValue_ instanceof NV2Bool) {
            NV2Bool arrayNodeValue__ = (NV2Bool) arrayNodeValue_;
            if ((state & 0b10000000) != 0 /* is-active readNV2Bool1Index(VirtualFrame, NV2Bool) */) {
                assert (indices.length == 1);
                return readNV2Bool1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active readNV2Bool2Indices(VirtualFrame, NV2Bool) */) {
                assert (indices.length == 2);
                return readNV2Bool2Indices(frameValue, arrayNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, arrayNodeValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object arrayNodeValue) {
        int state = state_;
        if (arrayNodeValue instanceof NV1Int) {
            NV1Int arrayNodeValue_ = (NV1Int) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b1 /* add-active readNV1Int1Index(VirtualFrame, NV1Int) */;
                return readNV1Int1Index(frameValue, arrayNodeValue_);
            }
        }
        if (arrayNodeValue instanceof NV2Int) {
            NV2Int arrayNodeValue_ = (NV2Int) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b10 /* add-active readNV2Int1Index(VirtualFrame, NV2Int) */;
                return readNV2Int1Index(frameValue, arrayNodeValue_);
            }
            if ((indices.length == 2)) {
                this.state_ = state = state | 0b100 /* add-active readNV2Int2Indices(VirtualFrame, NV2Int) */;
                return readNV2Int2Indices(frameValue, arrayNodeValue_);
            }
        }
        if (arrayNodeValue instanceof NV1Real) {
            NV1Real arrayNodeValue_ = (NV1Real) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b1000 /* add-active readNV1Real1Index(VirtualFrame, NV1Real) */;
                return readNV1Real1Index(frameValue, arrayNodeValue_);
            }
        }
        if (arrayNodeValue instanceof NV2Real) {
            NV2Real arrayNodeValue_ = (NV2Real) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b10000 /* add-active readNV2Real1Index(VirtualFrame, NV2Real) */;
                return readNV2Real1Index(frameValue, arrayNodeValue_);
            }
            if ((indices.length == 2)) {
                this.state_ = state = state | 0b100000 /* add-active readNV2Real2Indices(VirtualFrame, NV2Real) */;
                return readNV2Real2Indices(frameValue, arrayNodeValue_);
            }
        }
        if (arrayNodeValue instanceof NV1Bool) {
            NV1Bool arrayNodeValue_ = (NV1Bool) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b1000000 /* add-active readNV1Bool1Index(VirtualFrame, NV1Bool) */;
                return readNV1Bool1Index(frameValue, arrayNodeValue_);
            }
        }
        if (arrayNodeValue instanceof NV2Bool) {
            NV2Bool arrayNodeValue_ = (NV2Bool) arrayNodeValue;
            if ((indices.length == 1)) {
                this.state_ = state = state | 0b10000000 /* add-active readNV2Bool1Index(VirtualFrame, NV2Bool) */;
                return readNV2Bool1Index(frameValue, arrayNodeValue_);
            }
            if ((indices.length == 2)) {
                this.state_ = state = state | 0b100000000 /* add-active readNV2Bool2Indices(VirtualFrame, NV2Bool) */;
                return readNV2Bool2Indices(frameValue, arrayNodeValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.arrayNode_}, arrayNodeValue);
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

    public static NablaReadArrayNode create(NablaExpressionNode[] indices, NablaExpressionNode arrayNode) {
        return new NablaReadArrayNodeGen(indices, arrayNode);
    }

}
