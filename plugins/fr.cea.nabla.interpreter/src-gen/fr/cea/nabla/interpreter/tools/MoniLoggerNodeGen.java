// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.tools.ActionNode;
import fr.cea.nabla.interpreter.tools.ConditionNode;
import fr.cea.nabla.interpreter.tools.MoniLoggerNode;

@GeneratedBy(MoniLoggerNode.class)
public final class MoniLoggerNodeGen extends MoniLoggerNode {

    @Child private ConditionNode condition_;

    private MoniLoggerNodeGen(boolean onEnter, boolean onReturnValue, ActionNode action, ConditionNode condition) {
        super(onEnter, onReturnValue, action);
        this.condition_ = condition;
    }

    @Override
    protected void execute(VirtualFrame frameValue) {
        boolean conditionValue_ = this.condition_.execute(frameValue);
        doAction(frameValue, conditionValue_);
        return;
    }

    @Override
    public NodeCost getCost() {
        return NodeCost.MONOMORPHIC;
    }

    public static MoniLoggerNode create(boolean onEnter, boolean onReturnValue, ActionNode action, ConditionNode condition) {
        return new MoniLoggerNodeGen(onEnter, onReturnValue, action, condition);
    }

}
