// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.job;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionBlockNode;
import fr.cea.nabla.interpreter.nodes.job.NablaInstructionJobNode;

@GeneratedBy(NablaInstructionJobNode.class)
public final class NablaInstructionJobNodeGen extends NablaInstructionJobNode {

    @Child private NablaInstructionBlockNode instructionBlock_;

    private NablaInstructionJobNodeGen(String name, NablaInstructionBlockNode instructionBlock) {
        super(name);
        this.instructionBlock_ = instructionBlock;
    }

    private NablaInstructionJobNodeGen(NablaInstructionBlockNode instructionBlock) {
        this.instructionBlock_ = instructionBlock;
    }

    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        Object instructionBlockValue_ = this.instructionBlock_.executeGeneric(frameValue);
        return execute(frameValue, instructionBlockValue_);
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static NablaInstructionJobNode create(String name, NablaInstructionBlockNode instructionBlock) {
        return new NablaInstructionJobNodeGen(name, instructionBlock);
    }

    public static NablaInstructionJobNode create(NablaInstructionBlockNode instructionBlock) {
        return new NablaInstructionJobNodeGen(instructionBlock);
    }

}
