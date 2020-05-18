package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;

public class JobEventNode extends ExecutionEventNode {

	private final NablaDumpVariablesInstrument nablaDumpVariablesInstrument;
	private final String jobName;

	public JobEventNode(NablaDumpVariablesInstrument nablaDumpVariablesInstrument, String jobName) {
		assert (jobName != null);
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
		this.jobName = jobName;
	}

	@Override
	protected void onEnter(VirtualFrame frame) {
		nablaDumpVariablesInstrument.jobAboutToExecute(jobName);
	}

	@Override
	protected void onReturnValue(VirtualFrame frame, Object result) {
		nablaDumpVariablesInstrument.jobExecuted(jobName);
	}
}
