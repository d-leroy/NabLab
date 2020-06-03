package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.nodes.Node;

public class JobEventNodeFactory implements ExecutionEventNodeFactory {

	private NablaLogInstrument nablaDumpVariablesInstrument;

	JobEventNodeFactory(NablaLogInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
	}

	@Override
	public ExecutionEventNode create(final EventContext ec) {
		final Node n = ec.getInstrumentedNode();
		final Object o = n.getDebugProperties().get("jobName");
		if (o instanceof String) {
			final String jobName = (String) o;
			if (nablaDumpVariablesInstrument.isJobObserved(jobName)) {
				return new ExecutionEventNode() {
					@Override
					protected void onEnter(VirtualFrame frame) {
						nablaDumpVariablesInstrument.jobAboutToExecute(jobName);
					}

					@Override
					protected void onReturnValue(VirtualFrame frame, Object result) {
						nablaDumpVariablesInstrument.jobExecuted(jobName);
					}
				};
			}
		}
		return null;
	}

}
