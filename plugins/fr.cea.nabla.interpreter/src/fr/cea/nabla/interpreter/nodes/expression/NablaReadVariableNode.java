package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;

import fr.cea.nabla.interpreter.runtime.NablaInternalError;
import fr.cea.nabla.interpreter.utils.GetFrameNode;

@GenerateWrapper
@NodeChild(value = "frameToRead", type = GetFrameNode.class)
public abstract class NablaReadVariableNode extends NablaExpressionNode {

	private final FrameSlot slot;

	protected NablaReadVariableNode(FrameSlot slot) {
		this.slot = slot;
	}
	
	protected NablaReadVariableNode() {
		this.slot = null;
	}

	@Specialization
	protected Object doRead(VirtualFrame frame, Frame toRead) {
		try {
			return toRead.getObject(slot);
		} catch (FrameSlotTypeException e) {
			e.printStackTrace();
			throw NablaInternalError.shouldNotReachHere();
		}
	}
	
	public FrameSlot getSlot() {
		return slot;
	}
	
	@Override
	public WrapperNode createWrapper(ProbeNode probe) {
		return new NablaReadVariableNodeWrapper(this, probe);
	}
}
