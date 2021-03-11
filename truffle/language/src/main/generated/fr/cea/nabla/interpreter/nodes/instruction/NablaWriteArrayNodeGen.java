// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.instruction;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1IntJava;
import fr.cea.nabla.interpreter.values.NV1RealJava;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Real;
import org.graalvm.polyglot.Value;

@GeneratedBy(NablaWriteArrayNode.class)
public final class NablaWriteArrayNodeGen extends NablaWriteArrayNode {

    @Child private NablaExpressionNode value_;
    @Child private NablaReadVariableNode toWrite_;
    @CompilationFinal private int state_0_;

    private NablaWriteArrayNodeGen(FrameSlot slot, Class<?> baseType, int dimensions, NablaExpressionNode[] indices, NablaExpressionNode value, NablaReadVariableNode toWrite) {
        super(slot, baseType, dimensions, indices);
        this.value_ = value;
        this.toWrite_ = toWrite;
    }

    private NablaWriteArrayNodeGen(NablaExpressionNode value, NablaReadVariableNode toWrite) {
        this.value_ = value;
        this.toWrite_ = toWrite;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        Object toWriteValue_ = this.toWrite_.executeGeneric(frameValue);
        if ((state_0 & 0b11) != 0 /* is-state_0 writeNV1Int(VirtualFrame, NV0Int, NV1IntJava) || writeNV2Int(VirtualFrame, NV0Int, NV2Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            if ((state_0 & 0b1) != 0 /* is-state_0 writeNV1Int(VirtualFrame, NV0Int, NV1IntJava) */ && toWriteValue_ instanceof NV1IntJava) {
                NV1IntJava toWriteValue__ = (NV1IntJava) toWriteValue_;
                assert (isNV1Int());
                return writeNV1Int(frameValue, valueValue__, toWriteValue__);
            }
            if ((state_0 & 0b10) != 0 /* is-state_0 writeNV2Int(VirtualFrame, NV0Int, NV2Int) */ && toWriteValue_ instanceof NV2Int) {
                NV2Int toWriteValue__ = (NV2Int) toWriteValue_;
                assert (isNV2Int());
                return writeNV2Int(frameValue, valueValue__, toWriteValue__);
            }
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 writeNV2Int(VirtualFrame, NV1IntJava, NV2Int) */ && valueValue_ instanceof NV1IntJava) {
            NV1IntJava valueValue__ = (NV1IntJava) valueValue_;
            if (toWriteValue_ instanceof NV2Int) {
                NV2Int toWriteValue__ = (NV2Int) toWriteValue_;
                assert (isNV2Int());
                return writeNV2Int(frameValue, valueValue__, toWriteValue__);
            }
        }
        if ((state_0 & 0b111000) != 0 /* is-state_0 writeNV1Real(VirtualFrame, NV0Real, NV1RealJava) || writeNV1Real(VirtualFrame, NV0Real, Value) || writeNV2Real(VirtualFrame, NV0Real, NV2Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            if ((state_0 & 0b1000) != 0 /* is-state_0 writeNV1Real(VirtualFrame, NV0Real, NV1RealJava) */ && toWriteValue_ instanceof NV1RealJava) {
                NV1RealJava toWriteValue__ = (NV1RealJava) toWriteValue_;
                assert (isNV1Real());
                return writeNV1Real(frameValue, valueValue__, toWriteValue__);
            }
            if ((state_0 & 0b10000) != 0 /* is-state_0 writeNV1Real(VirtualFrame, NV0Real, Value) */ && toWriteValue_ instanceof Value) {
                Value toWriteValue__ = (Value) toWriteValue_;
                assert (isNV1Real());
                return writeNV1Real(frameValue, valueValue__, toWriteValue__);
            }
            if ((state_0 & 0b100000) != 0 /* is-state_0 writeNV2Real(VirtualFrame, NV0Real, NV2Real) */ && toWriteValue_ instanceof NV2Real) {
                NV2Real toWriteValue__ = (NV2Real) toWriteValue_;
                assert (isNV2Real());
                return writeNV2Real(frameValue, valueValue__, toWriteValue__);
            }
        }
        if ((state_0 & 0b11000000) != 0 /* is-state_0 writeNV2Real(VirtualFrame, NV1RealJava, NV2Real) || writeNV3Real(VirtualFrame, NV1RealJava, NV3Real) */ && valueValue_ instanceof NV1RealJava) {
            NV1RealJava valueValue__ = (NV1RealJava) valueValue_;
            if ((state_0 & 0b1000000) != 0 /* is-state_0 writeNV2Real(VirtualFrame, NV1RealJava, NV2Real) */ && toWriteValue_ instanceof NV2Real) {
                NV2Real toWriteValue__ = (NV2Real) toWriteValue_;
                assert (isNV2Real());
                return writeNV2Real(frameValue, valueValue__, toWriteValue__);
            }
            if ((state_0 & 0b10000000) != 0 /* is-state_0 writeNV3Real(VirtualFrame, NV1RealJava, NV3Real) */ && toWriteValue_ instanceof NV3Real) {
                NV3Real toWriteValue__ = (NV3Real) toWriteValue_;
                assert (isNV3Real());
                return writeNV3Real(frameValue, valueValue__, toWriteValue__);
            }
        }
        if ((state_0 & 0b1100000000) != 0 /* is-state_0 writeNV3Real(VirtualFrame, NV2Real, NV3Real) || writeNV4Real(VirtualFrame, NV2Real, NV4Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            if ((state_0 & 0b100000000) != 0 /* is-state_0 writeNV3Real(VirtualFrame, NV2Real, NV3Real) */ && toWriteValue_ instanceof NV3Real) {
                NV3Real toWriteValue__ = (NV3Real) toWriteValue_;
                assert (isNV3Real());
                return writeNV3Real(frameValue, valueValue__, toWriteValue__);
            }
            if ((state_0 & 0b1000000000) != 0 /* is-state_0 writeNV4Real(VirtualFrame, NV2Real, NV4Real) */ && toWriteValue_ instanceof NV4Real) {
                NV4Real toWriteValue__ = (NV4Real) toWriteValue_;
                assert (isNV4Real());
                return writeNV4Real(frameValue, valueValue__, toWriteValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_, toWriteValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object valueValue, Object toWriteValue) {
        int state_0 = state_0_;
        if (valueValue instanceof NV0Int) {
            NV0Int valueValue_ = (NV0Int) valueValue;
            if (toWriteValue instanceof NV1IntJava) {
                NV1IntJava toWriteValue_ = (NV1IntJava) toWriteValue;
                if ((isNV1Int())) {
                    this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 writeNV1Int(VirtualFrame, NV0Int, NV1IntJava) */;
                    return writeNV1Int(frameValue, valueValue_, toWriteValue_);
                }
            }
            if (toWriteValue instanceof NV2Int) {
                NV2Int toWriteValue_ = (NV2Int) toWriteValue;
                if ((isNV2Int())) {
                    this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 writeNV2Int(VirtualFrame, NV0Int, NV2Int) */;
                    return writeNV2Int(frameValue, valueValue_, toWriteValue_);
                }
            }
        }
        if (valueValue instanceof NV1IntJava) {
            NV1IntJava valueValue_ = (NV1IntJava) valueValue;
            if (toWriteValue instanceof NV2Int) {
                NV2Int toWriteValue_ = (NV2Int) toWriteValue;
                if ((isNV2Int())) {
                    this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 writeNV2Int(VirtualFrame, NV1IntJava, NV2Int) */;
                    return writeNV2Int(frameValue, valueValue_, toWriteValue_);
                }
            }
        }
        if (valueValue instanceof NV0Real) {
            NV0Real valueValue_ = (NV0Real) valueValue;
            if (toWriteValue instanceof NV1RealJava) {
                NV1RealJava toWriteValue_ = (NV1RealJava) toWriteValue;
                if ((isNV1Real())) {
                    this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 writeNV1Real(VirtualFrame, NV0Real, NV1RealJava) */;
                    return writeNV1Real(frameValue, valueValue_, toWriteValue_);
                }
            }
            if (toWriteValue instanceof Value) {
                Value toWriteValue_ = (Value) toWriteValue;
                if ((isNV1Real())) {
                    this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 writeNV1Real(VirtualFrame, NV0Real, Value) */;
                    return writeNV1Real(frameValue, valueValue_, toWriteValue_);
                }
            }
            if (toWriteValue instanceof NV2Real) {
                NV2Real toWriteValue_ = (NV2Real) toWriteValue;
                if ((isNV2Real())) {
                    this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 writeNV2Real(VirtualFrame, NV0Real, NV2Real) */;
                    return writeNV2Real(frameValue, valueValue_, toWriteValue_);
                }
            }
        }
        if (valueValue instanceof NV1RealJava) {
            NV1RealJava valueValue_ = (NV1RealJava) valueValue;
            if (toWriteValue instanceof NV2Real) {
                NV2Real toWriteValue_ = (NV2Real) toWriteValue;
                if ((isNV2Real())) {
                    this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 writeNV2Real(VirtualFrame, NV1RealJava, NV2Real) */;
                    return writeNV2Real(frameValue, valueValue_, toWriteValue_);
                }
            }
            if (toWriteValue instanceof NV3Real) {
                NV3Real toWriteValue_ = (NV3Real) toWriteValue;
                if ((isNV3Real())) {
                    this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 writeNV3Real(VirtualFrame, NV1RealJava, NV3Real) */;
                    return writeNV3Real(frameValue, valueValue_, toWriteValue_);
                }
            }
        }
        if (valueValue instanceof NV2Real) {
            NV2Real valueValue_ = (NV2Real) valueValue;
            if (toWriteValue instanceof NV3Real) {
                NV3Real toWriteValue_ = (NV3Real) toWriteValue;
                if ((isNV3Real())) {
                    this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 writeNV3Real(VirtualFrame, NV2Real, NV3Real) */;
                    return writeNV3Real(frameValue, valueValue_, toWriteValue_);
                }
            }
            if (toWriteValue instanceof NV4Real) {
                NV4Real toWriteValue_ = (NV4Real) toWriteValue;
                if ((isNV4Real())) {
                    this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 writeNV4Real(VirtualFrame, NV2Real, NV4Real) */;
                    return writeNV4Real(frameValue, valueValue_, toWriteValue_);
                }
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.value_, this.toWrite_}, valueValue, toWriteValue);
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

    public static NablaWriteArrayNode create(FrameSlot slot, Class<?> baseType, int dimensions, NablaExpressionNode[] indices, NablaExpressionNode value, NablaReadVariableNode toWrite) {
        return new NablaWriteArrayNodeGen(slot, baseType, dimensions, indices, value, toWrite);
    }

    public static NablaWriteArrayNode create(NablaExpressionNode value, NablaReadVariableNode toWrite) {
        return new NablaWriteArrayNodeGen(value, toWrite);
    }

}
