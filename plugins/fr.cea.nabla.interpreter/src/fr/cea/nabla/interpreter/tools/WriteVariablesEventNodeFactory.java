package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.SourceSection;

import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.utils.GetFrameNodeGen;

public class WriteVariablesEventNodeFactory implements ExecutionEventNodeFactory {

	private NablaDumpVariablesInstrument nablaDumpVariablesInstrument;

	WriteVariablesEventNodeFactory(NablaDumpVariablesInstrument nablaDumpVariablesInstrument) {
		this.nablaDumpVariablesInstrument = nablaDumpVariablesInstrument;
	}

	public ExecutionEventNode create(final EventContext ec) {
		final Node n = ec.getInstrumentedNode();
		final SourceSection sc = n.getSourceSection();
		if (sc != null) {
			System.out.println(sc.getCharacters());
		}
		final Object o = n.getDebugProperties().get("variableSlot");
		if (o instanceof FrameSlot) {
			final FrameSlot variableSlot = (FrameSlot) o;
			if (nablaDumpVariablesInstrument.isVariableObserved(variableSlot)) {
				final GetFrameNode frame = GetFrameNodeGen.create(variableSlot);
				final NablaReadVariableNode read = NablaReadVariableNodeGen.create(variableSlot, frame);
				final PrettyPrintValueNode print = PrettyPrintValueNodeGen.create(read);
				return WriteVariableEventNodeGen.create(nablaDumpVariablesInstrument, variableSlot, print);
			}
		}
		return null;
	}

}
