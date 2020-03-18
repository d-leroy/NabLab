package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

public class NablaLoopNode extends NablaInstructionNode {

	@Child NablaInstructionNode instruction;
	@Child NablaExpressionNode count;
	private final FrameSlot indexSlot;

	public NablaLoopNode(FrameSlot indexSlot, NablaExpressionNode count, NablaInstructionNode instruction) {
		this.indexSlot = indexSlot;
		this.count = count;
		this.instruction = instruction;
	}
	
	@Override
	@ExplodeLoop
	public Object executeGeneric(VirtualFrame frame) {
		final int iterationCount = NablaTypesGen.asNV0Int(count.executeGeneric(frame)).getData();
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		instruction.executeGeneric(frame);
		for (int i = 1; i < iterationCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			instruction.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(iterationCount - 1));
		return instruction.executeGeneric(frame);
	}
}
