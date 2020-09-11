// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.nodes.job.NablaTimeLoopJobNode;
import fr.cea.nabla.interpreter.values.NV0Int;

@GeneratedBy(NablaTimeLoopJobNode.class)
public final class NablaTimeLoopJobNodeGen extends NablaTimeLoopJobNode {

    @Child private NablaWriteVariableNode indexInitializer_;
    @Child private LoopNode loopNode_;
    @CompilationFinal private int state_;

    private NablaTimeLoopJobNodeGen(String name, NablaWriteVariableNode indexInitializer, LoopNode loopNode) {
        super(name);
        this.indexInitializer_ = indexInitializer;
        this.loopNode_ = loopNode;
    }

    private NablaTimeLoopJobNodeGen(NablaWriteVariableNode indexInitializer, LoopNode loopNode) {
        this.indexInitializer_ = indexInitializer;
        this.loopNode_ = loopNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object indexInitializerValue_ = this.indexInitializer_.executeGeneric(frameValue);
        Object loopNodeValue_ = this.loopNode_.execute(frameValue);
        if (state != 0 /* is-active execute(VirtualFrame, NV0Int, Object) */ && indexInitializerValue_ instanceof NV0Int) {
            NV0Int indexInitializerValue__ = (NV0Int) indexInitializerValue_;
            return execute(frameValue, indexInitializerValue__, loopNodeValue_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, indexInitializerValue_, loopNodeValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object indexInitializerValue, Object loopNodeValue) {
        int state = state_;
        if (indexInitializerValue instanceof NV0Int) {
            NV0Int indexInitializerValue_ = (NV0Int) indexInitializerValue;
            this.state_ = state = state | 0b1 /* add-active execute(VirtualFrame, NV0Int, Object) */;
            return execute(frameValue, indexInitializerValue_, loopNodeValue);
        }
        throw new UnsupportedSpecializationException(this, new Node[] {this.indexInitializer_, this.loopNode_}, indexInitializerValue, loopNodeValue);
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

    public static NablaTimeLoopJobNode create(String name, NablaWriteVariableNode indexInitializer, LoopNode loopNode) {
        return new NablaTimeLoopJobNodeGen(name, indexInitializer, loopNode);
    }

    public static NablaTimeLoopJobNode create(NablaWriteVariableNode indexInitializer, LoopNode loopNode) {
        return new NablaTimeLoopJobNodeGen(indexInitializer, loopNode);
    }

}
