// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

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
import java.util.List;

@GeneratedBy(NablaTimeLoopJobRepeatingNode.class)
public final class NablaTimeLoopJobRepeatingNodeGen extends NablaTimeLoopJobRepeatingNode {

    @Child private NablaWriteVariableNode indexUpdate_;
    @Child private GetFrameNode frameToWrite_;
    @Child private NablaJobBlockNode innerJobBlock_;
    @Child private NablaExpressionNode conditionNode_;
    @CompilationFinal private int state_;

    private NablaTimeLoopJobRepeatingNodeGen(List<FrameSlot[]> copies, NablaWriteVariableNode indexUpdate, GetFrameNode frameToWrite, NablaJobBlockNode innerJobBlock, NablaExpressionNode conditionNode) {
        super(copies);
        this.indexUpdate_ = indexUpdate;
        this.frameToWrite_ = frameToWrite;
        this.innerJobBlock_ = innerJobBlock;
        this.conditionNode_ = conditionNode;
    }

    private NablaTimeLoopJobRepeatingNodeGen(NablaWriteVariableNode indexUpdate, GetFrameNode frameToWrite, NablaJobBlockNode innerJobBlock, NablaExpressionNode conditionNode) {
        this.indexUpdate_ = indexUpdate;
        this.frameToWrite_ = frameToWrite;
        this.innerJobBlock_ = innerJobBlock;
        this.conditionNode_ = conditionNode;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frameValue) {
        int state = state_;
        Object indexUpdateValue_ = this.indexUpdate_.executeGeneric(frameValue);
        Frame frameToWriteValue_ = this.frameToWrite_.execute(frameValue);
        Object innerJobBlockValue_ = this.innerJobBlock_.executeGeneric(frameValue);
        Object conditionNodeValue_ = this.conditionNode_.executeGeneric(frameValue);
        if (state != 0 /* is-active doLoop(VirtualFrame, NV0Int, Frame, Object, NV0Bool) */ && indexUpdateValue_ instanceof NV0Int) {
            NV0Int indexUpdateValue__ = (NV0Int) indexUpdateValue_;
            if (conditionNodeValue_ instanceof NV0Bool) {
                NV0Bool conditionNodeValue__ = (NV0Bool) conditionNodeValue_;
                return doLoop(frameValue, indexUpdateValue__, frameToWriteValue_, innerJobBlockValue_, conditionNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, indexUpdateValue_, frameToWriteValue_, innerJobBlockValue_, conditionNodeValue_);
    }

    private boolean executeAndSpecialize(VirtualFrame frameValue, Object indexUpdateValue, Frame frameToWriteValue, Object innerJobBlockValue, Object conditionNodeValue) {
        int state = state_;
        if (indexUpdateValue instanceof NV0Int) {
            NV0Int indexUpdateValue_ = (NV0Int) indexUpdateValue;
            if (conditionNodeValue instanceof NV0Bool) {
                NV0Bool conditionNodeValue_ = (NV0Bool) conditionNodeValue;
                this.state_ = state = state | 0b1 /* add-active doLoop(VirtualFrame, NV0Int, Frame, Object, NV0Bool) */;
                return doLoop(frameValue, indexUpdateValue_, frameToWriteValue, innerJobBlockValue, conditionNodeValue_);
            }
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.indexUpdate_, this.frameToWrite_, this.innerJobBlock_, this.conditionNode_}, indexUpdateValue, frameToWriteValue, innerJobBlockValue, conditionNodeValue);
    }

    @Override
    public NodeCost getCost() {
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static NablaTimeLoopJobRepeatingNode create(List<FrameSlot[]> copies, NablaWriteVariableNode indexUpdate, GetFrameNode frameToWrite, NablaJobBlockNode innerJobBlock, NablaExpressionNode conditionNode) {
        return new NablaTimeLoopJobRepeatingNodeGen(copies, indexUpdate, frameToWrite, innerJobBlock, conditionNode);
    }

    public static NablaTimeLoopJobRepeatingNode create(NablaWriteVariableNode indexUpdate, GetFrameNode frameToWrite, NablaJobBlockNode innerJobBlock, NablaExpressionNode conditionNode) {
        return new NablaTimeLoopJobRepeatingNodeGen(indexUpdate, frameToWrite, innerJobBlock, conditionNode);
    }

}
