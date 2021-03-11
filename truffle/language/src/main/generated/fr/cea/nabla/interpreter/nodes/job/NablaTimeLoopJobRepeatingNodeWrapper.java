// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.InstrumentableNode.WrapperNode;
import com.oracle.truffle.api.nodes.NodeCost;

@GeneratedBy(NablaTimeLoopJobRepeatingNode.class)
final class NablaTimeLoopJobRepeatingNodeWrapper extends NablaTimeLoopJobRepeatingNode implements WrapperNode {

    @Child private NablaTimeLoopJobRepeatingNode delegateNode;
    @Child private ProbeNode probeNode;

    NablaTimeLoopJobRepeatingNodeWrapper(NablaTimeLoopJobRepeatingNode delegateNode, ProbeNode probeNode) {
        this.delegateNode = delegateNode;
        this.probeNode = probeNode;
    }

    @Override
    public NablaTimeLoopJobRepeatingNode getDelegateNode() {
        return delegateNode;
    }

    @Override
    public ProbeNode getProbeNode() {
        return probeNode;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.NONE;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {
        boolean returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                probeNode.onEnter(frame);
                returnValue = delegateNode.executeRepeating(frame);
                wasOnReturnExecuted = true;
                probeNode.onReturnValue(frame, returnValue);
                break;
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                } else if (result != null) {
                    returnValue = (boolean) result;
                    break;
                }
                throw t;
            }
        }
        return returnValue;
    }

}
