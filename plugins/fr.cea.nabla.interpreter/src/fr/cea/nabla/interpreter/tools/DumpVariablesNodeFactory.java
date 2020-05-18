package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;

public class DumpVariablesNodeFactory implements ExecutionEventNodeFactory {

	private NablaDumpVariablesInstrument nablaDumpVariablesInstrument;

	DumpVariablesNodeFactory(NablaDumpVariablesInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
	}

	public ExecutionEventNode create(final EventContext ec) {
		return new DumpVariablesNode(nablaDumpVariablesInstrument);
	}

}
