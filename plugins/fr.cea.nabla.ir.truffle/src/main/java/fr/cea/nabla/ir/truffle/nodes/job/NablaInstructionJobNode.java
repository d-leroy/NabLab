package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.Map;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.ir.truffle.tools.NablaTags;

public class NablaInstructionJobNode extends NablaJobNode {

	@Child
	private NablaInstructionNode instruction;
	
	private final String name;

	public NablaInstructionJobNode(NablaInstructionNode instruction, String name) {
		this.instruction = instruction;
		this.name = name;
	}

	protected NablaInstructionJobNode() {
		this.name = "";
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return instruction.executeGeneric(frame);
	}
	
	@Override
	public Map<String, Object> getDebugProperties() {
		Map<String, Object> debugProperties = super.getDebugProperties();
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
