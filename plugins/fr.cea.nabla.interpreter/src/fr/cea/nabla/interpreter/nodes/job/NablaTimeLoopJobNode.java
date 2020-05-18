package fr.cea.nabla.interpreter.nodes.job;

import java.util.List;
import java.util.Map;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.Tag;
import com.oracle.truffle.api.nodes.LoopNode;

import fr.cea.nabla.interpreter.nodes.NablaRootNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.tools.NablaTags;
import fr.cea.nabla.interpreter.utils.GetFrameNode;
import fr.cea.nabla.interpreter.utils.GetFrameNodeGen;
import fr.cea.nabla.interpreter.values.NV0Int;

@NodeChild(value = "indexFrame", type = GetFrameNode.class)
public abstract class NablaTimeLoopJobNode extends NablaJobNode {

	@Child
	private LoopNode loopNode;
	private final FrameSlot indexSlot;
	private final String name;

	public NablaTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaRootNode[] innerJobs,
			String indentation, FrameSlot timeSlot, FrameSlot deltatSlot, boolean shouldDump) {
		this.name = name;
		this.indexSlot = indexSlot;
		this.loopNode = Truffle.getRuntime()
				.createLoopNode(NablaTimeLoopJobRepeatingNodeGen.create(indexSlot, copies, conditionNode, innerJobs,
						shouldDump, GetFrameNodeGen.create(copies.get(0)[0]), GetFrameNodeGen.create(indexSlot)));
	}

	protected NablaTimeLoopJobNode() {
		this.name = null;
		this.indexSlot = null;
	}

	@Specialization
	public final Object executeGeneric(VirtualFrame frame, Frame indexFrame) {
		indexFrame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		indexFrame.setObject(indexSlot, new NV0Int(0));
		final Object result = loopNode.execute(frame);
		return result;
	}

	@Override
	public Map<String, Object> getDebugProperties() {
		Map<String, Object> debugProperties = super.getDebugProperties();
		debugProperties.put("indexSlot", indexSlot);
		if (this.name != null && !this.name.isEmpty()) {
			debugProperties.put("jobName", this.name);
		}
		return debugProperties;
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		return tag.equals(NablaTags.JobTag.class);
	}
}
