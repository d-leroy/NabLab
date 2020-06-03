package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.instrumentation.InstrumentableNode;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.utils.GetFrameNodeGen;

public class WriteVariablesEventNodeFactory implements ExecutionEventNodeFactory {

	private final NablaLogInstrument nablaLogInstrument;
	
	private final boolean doFormat;

	WriteVariablesEventNodeFactory(NablaLogInstrument nablaDumpVariablesInstrument, boolean doFormat) {
		this.nablaLogInstrument = nablaDumpVariablesInstrument;
		this.doFormat = doFormat;
	}

	public ExecutionEventNode create(final EventContext ec) {
		final Node n = ec.getInstrumentedNode();
		final InstrumentableNode in = (InstrumentableNode) n;
		final Object obj = in.getNodeObject();
		if (obj != null) {
			final InteropLibrary lib = InteropLibrary.getFactory().create(obj);
			try {
				String name = lib.readMember(obj, StandardTags.WriteVariableTag.NAME).toString();
				final Object o = n.getDebugProperties().get("variableSlot");
				if (o instanceof FrameSlot) {
					final FrameSlot variableSlot = (FrameSlot) o;
					if (nablaLogInstrument.isVariableObserved(variableSlot)) {
						final GetFrameNode frame = GetFrameNodeGen.create(variableSlot);
						final NablaReadVariableNode read = NablaReadVariableNodeGen.create(variableSlot, frame);
						final PrettyPrintValueNode print = PrettyPrintValueNodeGen.create(2, doFormat, read);
						return WriteVariableEventNodeGen.create(nablaLogInstrument, variableSlot, name, print);
					}
				}
			} catch (UnsupportedMessageException e) {
				e.printStackTrace();
			} catch (UnknownIdentifierException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
