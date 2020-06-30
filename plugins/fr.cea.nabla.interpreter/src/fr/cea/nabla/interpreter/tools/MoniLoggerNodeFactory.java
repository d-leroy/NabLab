package fr.cea.nabla.interpreter.tools;

import java.util.function.Supplier;

import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;

public class MoniLoggerNodeFactory implements ExecutionEventNodeFactory {

	private final Supplier<ConditionNode> conditionNodeFactory;
	private final Supplier<ActionNode> actionNodeFactory;

	MoniLoggerNodeFactory(Supplier<ConditionNode> conditionNodefactory,
			Supplier<ActionNode> actionNodefactory) {
		this.conditionNodeFactory = conditionNodefactory;
		this.actionNodeFactory = actionNodefactory;
	}

	@Override
	public ExecutionEventNode create(final EventContext ec) {
		return MoniLoggerNodeGen.create(true, true, actionNodeFactory.get(), conditionNodeFactory.get());
	}

}
