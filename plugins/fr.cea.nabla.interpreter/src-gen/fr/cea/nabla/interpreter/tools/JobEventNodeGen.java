// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.tools.ActionNode;
import fr.cea.nabla.interpreter.tools.ConditionNode;
import fr.cea.nabla.interpreter.tools.JobEventNode;

@GeneratedBy(JobEventNode.class)
public final class JobEventNodeGen extends JobEventNode {

    @Child private ConditionNode condition_;

    private JobEventNodeGen(boolean onEnter, boolean onReturnValue, ActionNode action, ConditionNode condition) {
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

    public static JobEventNode create(boolean onEnter, boolean onReturnValue, ActionNode action, ConditionNode condition) {
        return new JobEventNodeGen(onEnter, onReturnValue, action, condition);
    }

}
