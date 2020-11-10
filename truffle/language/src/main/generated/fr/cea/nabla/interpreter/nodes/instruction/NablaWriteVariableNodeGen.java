// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1IntJava;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Bool;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Bool;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;

@GeneratedBy(NablaWriteVariableNode.class)
public final class NablaWriteVariableNodeGen extends NablaWriteVariableNode {

    @Child private NablaExpressionNode value_;
    @Child private GetFrameNode frameToWrite_;
    @CompilationFinal private int state_;

    private NablaWriteVariableNodeGen(FrameSlot slot, NablaExpressionNode value, GetFrameNode frameToWrite) {
        super(slot);
        this.value_ = value;
        this.frameToWrite_ = frameToWrite;
    }

    private NablaWriteVariableNodeGen(NablaExpressionNode value, GetFrameNode frameToWrite) {
        this.value_ = value;
        this.frameToWrite_ = frameToWrite;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        Frame frameToWriteValue_ = this.frameToWrite_.execute(frameValue);
        if (state != 0 /* is-active doWrite(NV0Bool, Frame) || doWrite(NV1Bool, Frame) || doWrite(NV2Bool, Frame) || doWrite(NV3Bool, Frame) || doWrite(NV4Bool, Frame) || doWrite(NV0Int, Frame) || doWrite(NV1IntJava, Frame) || doWrite(NV2Int, Frame) || doWrite(NV3Int, Frame) || doWrite(NV4Int, Frame) || doWrite(NV0Real, Frame) || doWrite(NV1RealJava, Frame) || doWrite(NV2Real, Frame) || doWrite(NV3Real, Frame) || doWrite(NV4Real, Frame) */) {
            if ((state & 0b1) != 0 /* is-active doWrite(NV0Bool, Frame) */ && valueValue_ instanceof NV0Bool) {
                NV0Bool valueValue__ = (NV0Bool) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b10) != 0 /* is-active doWrite(NV1Bool, Frame) */ && valueValue_ instanceof NV1Bool) {
                NV1Bool valueValue__ = (NV1Bool) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b100) != 0 /* is-active doWrite(NV2Bool, Frame) */ && valueValue_ instanceof NV2Bool) {
                NV2Bool valueValue__ = (NV2Bool) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b1000) != 0 /* is-active doWrite(NV3Bool, Frame) */ && valueValue_ instanceof NV3Bool) {
                NV3Bool valueValue__ = (NV3Bool) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b10000) != 0 /* is-active doWrite(NV4Bool, Frame) */ && valueValue_ instanceof NV4Bool) {
                NV4Bool valueValue__ = (NV4Bool) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b100000) != 0 /* is-active doWrite(NV0Int, Frame) */ && valueValue_ instanceof NV0Int) {
                NV0Int valueValue__ = (NV0Int) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b1000000) != 0 /* is-active doWrite(NV1IntJava, Frame) */ && valueValue_ instanceof NV1IntJava) {
                NV1IntJava valueValue__ = (NV1IntJava) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b10000000) != 0 /* is-active doWrite(NV2Int, Frame) */ && valueValue_ instanceof NV2Int) {
                NV2Int valueValue__ = (NV2Int) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b100000000) != 0 /* is-active doWrite(NV3Int, Frame) */ && valueValue_ instanceof NV3Int) {
                NV3Int valueValue__ = (NV3Int) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b1000000000) != 0 /* is-active doWrite(NV4Int, Frame) */ && valueValue_ instanceof NV4Int) {
                NV4Int valueValue__ = (NV4Int) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b10000000000) != 0 /* is-active doWrite(NV0Real, Frame) */ && valueValue_ instanceof NV0Real) {
                NV0Real valueValue__ = (NV0Real) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b100000000000) != 0 /* is-active doWrite(NV1RealJava, Frame) */ && valueValue_ instanceof NV1RealJava) {
                NV1RealJava valueValue__ = (NV1RealJava) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b1000000000000) != 0 /* is-active doWrite(NV2Real, Frame) */ && valueValue_ instanceof NV2Real) {
                NV2Real valueValue__ = (NV2Real) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b10000000000000) != 0 /* is-active doWrite(NV3Real, Frame) */ && valueValue_ instanceof NV3Real) {
                NV3Real valueValue__ = (NV3Real) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
            if ((state & 0b100000000000000) != 0 /* is-active doWrite(NV4Real, Frame) */ && valueValue_ instanceof NV4Real) {
                NV4Real valueValue__ = (NV4Real) valueValue_;
                return doWrite(valueValue__, frameToWriteValue_);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_, frameToWriteValue_);
    }

    private Object executeAndSpecialize(Object valueValue, Frame frameToWriteValue) {
        int state = state_;
        if (valueValue instanceof NV0Bool) {
            NV0Bool valueValue_ = (NV0Bool) valueValue;
            this.state_ = state = state | 0b1 /* add-active doWrite(NV0Bool, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV1Bool) {
            NV1Bool valueValue_ = (NV1Bool) valueValue;
            this.state_ = state = state | 0b10 /* add-active doWrite(NV1Bool, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV2Bool) {
            NV2Bool valueValue_ = (NV2Bool) valueValue;
            this.state_ = state = state | 0b100 /* add-active doWrite(NV2Bool, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV3Bool) {
            NV3Bool valueValue_ = (NV3Bool) valueValue;
            this.state_ = state = state | 0b1000 /* add-active doWrite(NV3Bool, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV4Bool) {
            NV4Bool valueValue_ = (NV4Bool) valueValue;
            this.state_ = state = state | 0b10000 /* add-active doWrite(NV4Bool, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            this.state_ = state = state | 0b100000 /* add-active doWrite(NV0Int, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV1IntJava) {
            NV1IntJava valueValue_ = (NV1IntJava) valueValue;
            this.state_ = state = state | 0b1000000 /* add-active doWrite(NV1IntJava, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV2Int) {
            NV2Int valueValue_ = (NV2Int) valueValue;
            this.state_ = state = state | 0b10000000 /* add-active doWrite(NV2Int, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV3Int) {
            NV3Int valueValue_ = (NV3Int) valueValue;
            this.state_ = state = state | 0b100000000 /* add-active doWrite(NV3Int, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV4Int) {
            NV4Int valueValue_ = (NV4Int) valueValue;
            this.state_ = state = state | 0b1000000000 /* add-active doWrite(NV4Int, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            this.state_ = state = state | 0b10000000000 /* add-active doWrite(NV0Real, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV1RealJava) {
            NV1RealJava valueValue_ = (NV1RealJava) valueValue;
            this.state_ = state = state | 0b100000000000 /* add-active doWrite(NV1RealJava, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            this.state_ = state = state | 0b1000000000000 /* add-active doWrite(NV2Real, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV3Real) {
            NV3Real valueValue_ = (NV3Real) valueValue;
            this.state_ = state = state | 0b10000000000000 /* add-active doWrite(NV3Real, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        if (valueValue instanceof NV4Real) {
            NV4Real valueValue_ = (NV4Real) valueValue;
            this.state_ = state = state | 0b100000000000000 /* add-active doWrite(NV4Real, Frame) */;
            return doWrite(valueValue_, frameToWriteValue);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.value_, this.frameToWrite_}, valueValue, frameToWriteValue);
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

    public static NablaWriteVariableNode create(FrameSlot slot, NablaExpressionNode value, GetFrameNode frameToWrite) {
        return new NablaWriteVariableNodeGen(slot, value, frameToWrite);
    }

    public static NablaWriteVariableNode create(NablaExpressionNode value, GetFrameNode frameToWrite) {
        return new NablaWriteVariableNodeGen(value, frameToWrite);
    }

}
