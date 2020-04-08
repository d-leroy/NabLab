package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

public class NablaTimeLoopJobNode extends NablaJobNode {

	@Child
	private LoopNode loopNode;
	private final FrameSlot indexSlot;

	public NablaTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaJobNode[] innerJobs,
			String indentation, FrameSlot timeSlot, FrameSlot deltatSlot) {
		super(language, frameDescriptor, name);
		this.indexSlot = indexSlot;
		this.loopNode = Truffle.getRuntime()
				.createLoopNode(new NablaTimeLoopJobRepeatingNode(indexSlot, copies, conditionNode, innerJobs));
	}

	@Override
	public Object execute(VirtualFrame frame) {
		final Frame frameToWrite = (Frame) frame.getArguments()[0];
		frameToWrite.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		frameToWrite.setObject(indexSlot, new NV0Int(0));
		return loopNode.execute(frame);
	}

}
