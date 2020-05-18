// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.NablaRootNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.job.NablaTimeLoopJobRepeatingNode;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import java.util.List;

@GeneratedBy(NablaTimeLoopJobRepeatingNode.class)
public final class NablaTimeLoopJobRepeatingNodeGen extends NablaTimeLoopJobRepeatingNode {

    @Child private GetFrameNode frameToWrite_;
    @Child private GetFrameNode indexFrame_;

    private NablaTimeLoopJobRepeatingNodeGen(FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs, boolean shouldDump, GetFrameNode frameToWrite, GetFrameNode indexFrame) {
        super(indexSlot, copies, conditionNode, innerJobs, shouldDump);
        this.frameToWrite_ = frameToWrite;
        this.indexFrame_ = indexFrame;
    }

    private NablaTimeLoopJobRepeatingNodeGen(GetFrameNode frameToWrite, GetFrameNode indexFrame) {
        this.frameToWrite_ = frameToWrite;
        this.indexFrame_ = indexFrame;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frameValue) {
        Frame frameToWriteValue_ = this.frameToWrite_.execute(frameValue);
        Frame indexFrameValue_ = this.indexFrame_.execute(frameValue);
        return doLoop(frameValue, frameToWriteValue_, indexFrameValue_);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaTimeLoopJobRepeatingNode create(FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs, boolean shouldDump, GetFrameNode frameToWrite, GetFrameNode indexFrame) {
        return new NablaTimeLoopJobRepeatingNodeGen(indexSlot, copies, conditionNode, innerJobs, shouldDump, frameToWrite, indexFrame);
    }

    public static NablaTimeLoopJobRepeatingNode create(GetFrameNode frameToWrite, GetFrameNode indexFrame) {
        return new NablaTimeLoopJobRepeatingNodeGen(frameToWrite, indexFrame);
    }

}
