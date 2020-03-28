// CheckStyle: start generated
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNode;

@GeneratedBy(NablaReadVariableNode.class)
public final class NablaReadVariableNodeGen extends NablaReadVariableNode {

    private NablaReadVariableNodeGen(FrameSlot slot) {
        super(slot);
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        return read(frameValue);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReadVariableNode create(FrameSlot slot) {
        return new NablaReadVariableNodeGen(slot);
    }

}
