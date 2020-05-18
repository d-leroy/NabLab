// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.utils.GetFrameNode;

@GeneratedBy(NablaReadVariableNode.class)
public final class NablaReadVariableNodeGen extends NablaReadVariableNode {

    @Child private GetFrameNode frameToRead_;

    private NablaReadVariableNodeGen(FrameSlot slot, GetFrameNode frameToRead) {
        super(slot);
        this.frameToRead_ = frameToRead;
    }

    private NablaReadVariableNodeGen(GetFrameNode frameToRead) {
        this.frameToRead_ = frameToRead;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        Frame frameToReadValue_ = this.frameToRead_.execute(frameValue);
        return doRead(frameValue, frameToReadValue_);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaReadVariableNode create(FrameSlot slot, GetFrameNode frameToRead) {
        return new NablaReadVariableNodeGen(slot, frameToRead);
    }

    public static NablaReadVariableNode create(GetFrameNode frameToRead) {
        return new NablaReadVariableNodeGen(frameToRead);
    }

}
