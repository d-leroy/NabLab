// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.tools.NablaLogInstrument;
import fr.cea.nabla.interpreter.tools.PrettyPrintValueNode;
import fr.cea.nabla.interpreter.tools.WriteVariableEventNode;
import java.util.function.Supplier;

@GeneratedBy(WriteVariableEventNode.class)
public final class WriteVariableEventNodeGen extends WriteVariableEventNode {

    @Child private PrettyPrintValueNode print_;

    private WriteVariableEventNodeGen(NablaLogInstrument nablaLogInstrument, String name, PrettyPrintValueNode print) {
        super(nablaLogInstrument, name);
        this.print_ = print;
    }

    @Override
    protected void execute(VirtualFrame frameValue) {
        Supplier<String> printValue_ = this.print_.execute(frameValue);
        print(frameValue, printValue_);
        return;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static WriteVariableEventNode create(NablaLogInstrument nablaLogInstrument, String name, PrettyPrintValueNode print) {
        return new WriteVariableEventNodeGen(nablaLogInstrument, name, print);
    }

}
