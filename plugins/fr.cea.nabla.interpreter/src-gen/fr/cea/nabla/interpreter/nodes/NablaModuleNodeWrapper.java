// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.InstrumentableNode.WrapperNode;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.NablaModuleNode;

@GeneratedBy(NablaModuleNode.class)
final class NablaModuleNodeWrapper extends NablaModuleNode implements WrapperNode {

    @Child private NablaModuleNode delegateNode;
    @Child private ProbeNode probeNode;

    NablaModuleNodeWrapper(NablaModuleNode delegateNode, ProbeNode probeNode) {
        this.delegateNode = delegateNode;
        this.probeNode = probeNode;
    }

    @Override
    public NablaModuleNode getDelegateNode() {
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
    public Object executeGeneric(VirtualFrame frame) {
        Object returnValue;
        for (;;) {
            boolean wasOnReturnExecuted = false;
            try {
                probeNode.onEnter(frame);
                returnValue = delegateNode.executeGeneric(frame);
                wasOnReturnExecuted = true;
                probeNode.onReturnValue(frame, returnValue);
                break;
            } catch (Throwable t) {
                Object result = probeNode.onReturnExceptionalOrUnwind(frame, t, wasOnReturnExecuted);
                if (result == ProbeNode.UNWIND_ACTION_REENTER) {
                    continue;
                } else if (result != null) {
                    returnValue = result;
                    break;
                }
                throw t;
            }
        }
        return returnValue;
    }

    @Override
    public boolean isInstrumentable() {
        return this.delegateNode.isInstrumentable();
    }

}
