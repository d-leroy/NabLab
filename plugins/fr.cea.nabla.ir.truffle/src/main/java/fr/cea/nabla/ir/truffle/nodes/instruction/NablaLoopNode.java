package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Int;

@NodeChild(value="count", type=NablaExpressionNode.class)
public abstract class NablaLoopNode extends NablaInstructionNode {

	private final FrameSlot indexSlot;

	@Child private NablaInstructionNode body;
	
	public NablaLoopNode(FrameSlot indexSlot, NablaInstructionNode body) {
		this.indexSlot = indexSlot;
		this.body = body;
	}
	
	@Specialization
	
	public Object doLoop(VirtualFrame frame, NV0Int count) {
		final int iterationCount = count.getData();
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		for (int i = 1; i < iterationCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(iterationCount - 1));
		return body.executeGeneric(frame);
	}
	
	@Specialization
	
	public Object doLoop(VirtualFrame frame, NV1Int elements_) {
		final int[] elements = elements_.getData();
		final int iterationCount = elements.length;
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		for (int i = 1; i < iterationCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(iterationCount-1));
		return body.executeGeneric(frame);
	}
}
