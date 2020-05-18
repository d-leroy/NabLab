package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.SourceSection;

public class JobEventNodeFactory implements ExecutionEventNodeFactory {

	private NablaDumpVariablesInstrument nablaDumpVariablesInstrument;

	JobEventNodeFactory(NablaDumpVariablesInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
	}

	@Override
	public ExecutionEventNode create(final EventContext ec) {
		final Node n = ec.getInstrumentedNode();
		final SourceSection sc = n.getSourceSection();
		if (sc != null) {
			CharSequence cs = sc.getCharacters();
			System.out.println(cs);
		}
		final Object o = n.getDebugProperties().get("jobName");
		if (o instanceof String) {
			final String jobName = (String) o;
			if (nablaDumpVariablesInstrument.isJobObserved(jobName)) {
				return new JobEventNode(nablaDumpVariablesInstrument, jobName);
			}
		}
		return null;
	}

}
