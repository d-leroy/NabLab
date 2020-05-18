// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument;
import fr.cea.nabla.interpreter.tools.PrettyPrintValueNode;
import fr.cea.nabla.interpreter.tools.WriteVariableEventNode;

@GeneratedBy(WriteVariableEventNode.class)
public final class WriteVariableEventNodeGen extends WriteVariableEventNode {

    @Child private PrettyPrintValueNode print_;

    private WriteVariableEventNodeGen(NablaDumpVariablesInstrument nablaDumpVariablesInstrument, FrameSlot variableSlot, PrettyPrintValueNode print) {
        super(nablaDumpVariablesInstrument, variableSlot);
        this.print_ = print;
    }

    @Override
    protected void execute(VirtualFrame frameValue) {
        String printValue_ = this.print_.execute(frameValue);
        print(frameValue, printValue_);
        return;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static WriteVariableEventNode create(NablaDumpVariablesInstrument nablaDumpVariablesInstrument, FrameSlot variableSlot, PrettyPrintValueNode print) {
        return new WriteVariableEventNodeGen(nablaDumpVariablesInstrument, variableSlot, print);
    }

}
