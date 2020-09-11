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
import fr.cea.nabla.interpreter.nodes.instruction.NablaIfNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionNode;

@GeneratedBy(NablaIfNode.class)
public final class NablaIfNodeGen extends NablaIfNode {

    @CompilationFinal private int state_;

    private NablaIfNodeGen(NablaExpressionNode conditionNode, NablaInstructionNode thenPartNode, NablaInstructionNode elsePartNode) {
        super(conditionNode, thenPartNode, elsePartNode);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        if ((state & 0b1) != 0 /* is-active doThenElse(VirtualFrame) */) {
            assert (elsePartNode != null);
            return doThenElse(frameValue);
        }
        if ((state & 0b10) != 0 /* is-active doThen(VirtualFrame) */) {
            assert (elsePartNode == null);
            return doThen(frameValue);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue) {
        int state = state_;
        if ((elsePartNode != null)) {
            this.state_ = state = state | 0b1 /* add-active doThenElse(VirtualFrame) */;
            return doThenElse(frameValue);
        }
        if ((elsePartNode == null)) {
            this.state_ = state = state | 0b10 /* add-active doThen(VirtualFrame) */;
            return doThen(frameValue);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {});
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

    public static NablaIfNode create(NablaExpressionNode conditionNode, NablaInstructionNode thenPartNode, NablaInstructionNode elsePartNode) {
        return new NablaIfNodeGen(conditionNode, thenPartNode, elsePartNode);
    }

}
