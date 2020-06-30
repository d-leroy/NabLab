package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnknownIdentifierException;
import com.oracle.truffle.api.interop.UnsupportedMessageException;

import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.utils.GetFrameNodeGen;

public class WriteVariablesEventNodeFactory implements ExecutionEventNodeFactory {

	private final NablaLogInstrument nablaLogInstrument;
	
	private final boolean doFormat;
	
	private final int range;

	WriteVariablesEventNodeFactory(NablaLogInstrument nablaLogVariablesInstrument, boolean doFormat, int range) {
		this.nablaLogInstrument = nablaLogVariablesInstrument;
		this.doFormat = doFormat;
		this.range = range;
	}

	public ExecutionEventNode create(final EventContext ec) {
		final Object obj = ec.getNodeObject();
		if (obj != null) {
			final InteropLibrary lib = InteropLibrary.getFactory().create(obj);
			try {
				final String name = lib.readMember(obj, StandardTags.WriteVariableTag.NAME).toString();
				if (nablaLogInstrument.isVariableObserved(name)) {
					final GetFrameNode frame = GetFrameNodeGen.create(name);
					final NablaReadVariableNode read = NablaReadVariableNodeGen.create(name, frame);
					final PrettyPrintValueNode print = PrettyPrintValueNodeGen.create(range, doFormat, read);
					return WriteVariableEventNodeGen.create(nablaLogInstrument, name, print);
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
