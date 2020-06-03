package fr.cea.nabla.interpreter.nodes.job;

import java.util.Map;

import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.Tag;

import fr.cea.nabla.interpreter.nodes.NablaNode;
import fr.cea.nabla.interpreter.tools.NablaTags;

@GenerateWrapper
public abstract class NablaJobNode extends NablaNode {

	protected final String name;
	
	protected NablaJobNode(String name) {
		this.name = name;
	}
	
	protected NablaJobNode() {
		this.name = "";
	}
	
    @Override
	public WrapperNode createWrapper(ProbeNode probeNode) {
		return new NablaJobNodeWrapper(this, probeNode);
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		return tag.equals(NablaTags.JobTag.class);
	}
	
	@Override
	public Map<String, Object> getDebugProperties() {
		Map<String, Object> debugProperties = super.getDebugProperties();
		if (this.name != null && !this.name.isEmpty()) {
			debugProperties.put("jobName", this.name);
		}
		return debugProperties;
	}
	
}
