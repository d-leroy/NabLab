package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;

import fr.cea.nabla.ir.truffle.nodes.NablaNode;
import fr.cea.nabla.ir.truffle.nodes.NablaRootNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.utils.GetFrameNode;
import fr.cea.nabla.ir.truffle.utils.GetFrameNodeGen;
import fr.cea.nabla.ir.truffle.values.NV0Int;

@NodeChild(value = "indexFrame", type = GetFrameNode.class)
public abstract class NablaTimeLoopJobNode extends NablaNode {

	@Child
	private LoopNode loopNode;
	private final FrameSlot indexSlot;
	
	public NablaTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs,
			String indentation, FrameSlot timeSlot, FrameSlot deltatSlot, boolean shouldDump) {
		this.indexSlot = indexSlot;
		this.loopNode = Truffle.getRuntime().createLoopNode(NablaTimeLoopJobRepeatingNodeGen.create(indexSlot, copies,
				conditionNode, innerJobs, shouldDump, GetFrameNodeGen.create(copies.get(0)[0]), GetFrameNodeGen.create(indexSlot)));
	}

	@Specialization
	public final Object executeGeneric(VirtualFrame frame, Frame indexFrame) {
		indexFrame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		indexFrame.setObject(indexSlot, new NV0Int(0));
		final Object result = loopNode.execute(frame);
		return result;
	}
}
