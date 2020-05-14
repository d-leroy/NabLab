package fr.cea.nabla.ir.truffle.tools;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Bool;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Bool;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Bool;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Bool;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;

@NodeChild(value = "read", type = NablaReadVariableNode.class)
public abstract class PrettyPrintValueNode extends Node {

	private final String format;

	public PrettyPrintValueNode() {
		this("%.8f");
	}

	public PrettyPrintValueNode(String format) {
		this.format = format;
	}

	protected abstract String execute(VirtualFrame frame);

	@Specialization
	protected String onReturn(VirtualFrame frame, NV0Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV1Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV2Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV3Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV4Bool read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV0Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV1Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV2Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV3Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV4Int read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV0Real read) {
		return read.toString();
	}

	@ExplodeLoop
	@Specialization(guards = { "read.getData().length > 11", "read.getData().length == cachedLength" })
	protected String onReturn(VirtualFrame frame, NV1Real read, @Cached("read.getData().length") int cachedLength,
			@Cached("computeMid(cachedLength)") int cachedMid) {
		final double[] data = read.getData();
		final StringBuilder s = new StringBuilder();
		appendValue(s, "... ");
		for (int i = cachedMid - 5; i < cachedMid + 5; i++) {
			appendValue(s, data[i]);
			appendValue(s, " ");
		}
		appendValue(s, "...");
		return s.toString();
	}
	
	protected int computeMid(int i) {
		return i % 2 == 0 ? i / 2 : 1 + i / 2;
	}

	@ExplodeLoop
	@Specialization(guards = "read.getData().length > 11")
	protected String onReturnRange(VirtualFrame frame, NV1Real read) {
		final double[] data = read.getData();
		final int mid = data.length % 2 == 0 ? data.length / 2 : 1 + data.length / 2;
		final StringBuilder s = new StringBuilder();
		appendValue(s, "... ");
		for (int i = mid - 5; i < mid + 5; i++) {
			appendValue(s, data[i]);
			appendValue(s, " ");
		}
		appendValue(s, "...");
		return s.toString();
	}

	@ExplodeLoop
	@Specialization
	protected String onReturn(VirtualFrame frame, NV1Real read) {
		return read.toString();
	}

	@TruffleBoundary
	private void appendValue(StringBuilder s, String value) {
		s.append(value);
	}

	@TruffleBoundary
	private void appendValue(StringBuilder s, double value) {
		s.append(String.format(format, value));
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV2Real read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV3Real read) {
		return read.toString();
	}

	@Specialization
	protected String onReturn(VirtualFrame frame, NV4Real read) {
		return read.toString();
	}

}
