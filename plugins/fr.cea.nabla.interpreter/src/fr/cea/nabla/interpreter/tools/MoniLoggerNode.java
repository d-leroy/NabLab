package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.profiles.ConditionProfile;

@NodeChild(value = "condition", type = ConditionNode.class)
public abstract class MoniLoggerNode extends ExecutionEventNode {

	@Child
	private ActionNode action;

	private final boolean onEnter;
	private final boolean onReturnValue;

	private final ConditionProfile conditionProfile = ConditionProfile.createCountingProfile();

	public MoniLoggerNode(boolean onEnter, boolean onReturnValue, ActionNode action) {
		this.onEnter = onEnter;
		this.onReturnValue = onReturnValue;
		this.action = action;
	}

	@Override
	protected void onEnter(VirtualFrame frame) {
		if (onEnter) {
			execute(frame);
		}
	}

	@Override
	protected void onReturnValue(VirtualFrame frame, Object result) {
		if (onReturnValue) {
			execute(frame);
		}
	}

	protected abstract void execute(VirtualFrame frame);

	@Specialization
	protected void doAction(VirtualFrame frame, boolean condition) {
		if (conditionProfile.profile(condition)) {
			action.execute(frame);
		}
	}

}
