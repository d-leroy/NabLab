// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.NablaRootNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.job.NablaTimeLoopJobNode;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import java.util.List;

@GeneratedBy(NablaTimeLoopJobNode.class)
public final class NablaTimeLoopJobNodeGen extends NablaTimeLoopJobNode {

    @Child private GetFrameNode indexFrame_;

    private NablaTimeLoopJobNodeGen(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name, FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs, String indentation, FrameSlot timeSlot, FrameSlot deltatSlot, boolean shouldDump, GetFrameNode indexFrame) {
        super(language, frameDescriptor, name, indexSlot, copies, conditionNode, innerJobs, indentation, timeSlot, deltatSlot, shouldDump);
        this.indexFrame_ = indexFrame;
    }

    private NablaTimeLoopJobNodeGen(GetFrameNode indexFrame) {
        this.indexFrame_ = indexFrame;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        Frame indexFrameValue_ = this.indexFrame_.execute(frameValue);
        return executeGeneric(frameValue, indexFrameValue_);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaTimeLoopJobNode create(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name, FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs, String indentation, FrameSlot timeSlot, FrameSlot deltatSlot, boolean shouldDump, GetFrameNode indexFrame) {
        return new NablaTimeLoopJobNodeGen(language, frameDescriptor, name, indexSlot, copies, conditionNode, innerJobs, indentation, timeSlot, deltatSlot, shouldDump, indexFrame);
    }

    public static NablaTimeLoopJobNode create(GetFrameNode indexFrame) {
        return new NablaTimeLoopJobNodeGen(indexFrame);
    }

}
