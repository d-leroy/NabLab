package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.interpreter.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1Int;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Bool;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Bool;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;

@NodeChild(value = "read", type = NablaReadVariableNode.class)
public abstract class PrettyPrintValueNode extends Node {

	private final String format = "%.8f";
	
	private final boolean doFormat;
	
	private final int range;

	public PrettyPrintValueNode(int range, boolean doFormat) {
		this.range = range;
		this.doFormat = doFormat;
	}

	protected abstract String execute(VirtualFrame frame);

	@Specialization
	protected String onReturn(NV0Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV1Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV2Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV3Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV4Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV0Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV1Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV2Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV3Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV4Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV0Real read) {
		return read.toString();
	}

	@ExplodeLoop
	@Specialization(guards = { "read.getData().length > computeWidth()", "read.getData().length == cachedLength" })
	protected String onReturn(NV1Real read, @Cached("read.getData().length") int cachedLength,
			@Cached("computeMid(cachedLength)") int cachedMid) {
		final double[] data = read.getData();
		final StringBuilder s = new StringBuilder();
		appendValue(s, data[0]);
		appendValue(s, " ... ");
		for (int i = cachedMid - range; i < cachedMid + range; i++) {
			appendValue(s, data[i]);
			appendValue(s, " ");
		}
		appendValue(s, "... ");
		appendValue(s, data[cachedLength - 1]);
		return s.toString();
	}

	@ExplodeLoop
	@Specialization(guards = "read.getData().length > computeWidth()")
	protected String onReturnRange(NV1Real read) {
		final double[] data = read.getData();
		final int mid = computeMid(data.length);
		final StringBuilder s = new StringBuilder();
		appendValue(s, data[0]);
		appendValue(s, "... ");
		for (int i = mid - range; i < mid + range; i++) {
			appendValue(s, data[i]);
			appendValue(s, " ");
		}
		appendValue(s, "...");
		appendValue(s, data[data.length - 1]);
		return s.toString();
	}
	
	protected int computeMid(int i) {
		return i % 2 == 0 ? i / 2 : 1 + i / 2;
	}
	
	protected int computeWidth() {
		return 1 + 2 * range;
	}

	@ExplodeLoop
	@Specialization
	protected String onReturn(NV1Real read) {
		return read.toString();
	}

	@TruffleBoundary
	private void appendValue(StringBuilder s, String value) {
		s.append(value);
	}

	@TruffleBoundary
	private void appendValue(StringBuilder s, double value) {
		if (doFormat) {
			s.append(String.format(format, value));
		} else {
			s.append(value);
		}
	}

	@Specialization
	protected String onReturn(NV2Real read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV3Real read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(NV4Real read) {
		return read.toString();
	}

}
