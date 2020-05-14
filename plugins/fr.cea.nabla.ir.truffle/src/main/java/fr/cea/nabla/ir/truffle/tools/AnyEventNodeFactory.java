package fr.cea.nabla.ir.truffle.tools;

import com.oracle.truffle.api.instrumentation.EventContext;
import com.oracle.truffle.api.instrumentation.ExecutionEventNode;
import com.oracle.truffle.api.instrumentation.ExecutionEventNodeFactory;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.SourceSection;

public class AnyEventNodeFactory implements ExecutionEventNodeFactory {

	@Override
	public ExecutionEventNode create(final EventContext ec) {
		final Node n = ec.getInstrumentedNode();
		final SourceSection sc = n.getSourceSection();
		if (sc != null) {
			CharSequence cs = sc.getCharacters();
			System.out.println("=================" + sc.getStartLine() + ":" + sc.getCharIndex() + "=================");
			System.out.println(cs);
			if (cs.toString().equals(" ")) {
				System.out.println("non");
			}
			System.out.println("=================" + sc.getEndLine() + ":" + sc.getCharEndIndex() + "=================");
		}
		return null;
	}

}
