package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

import fr.cea.nabla.ir.truffle.NablaJobNode;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaRootNode;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;

public class NablaModuleNode extends NablaRootNode {

	@Children private NablaWriteVariableNode[] constants;
	@Children private NablaWriteVariableNode[] variables;
	@Children private NablaJobNode[] jobs;
	
	public NablaModuleNode(NablaLanguage language, FrameDescriptor frameDescriptor, String name,
			NablaWriteVariableNode[] constants, NablaWriteVariableNode[] variables,
			NablaFunctionNode[] functions, NablaJobNode[] jobs) {
		super(language, frameDescriptor, name);
		this.constants = constants;
		this.variables = variables;
		this.jobs = jobs;
	}
	
	@ExplodeLoop
	public Object execute(VirtualFrame frame) {
		CompilerAsserts.compilationConstant(constants.length);
		
		for (int i = 0; i < constants.length; i++) {
			constants[i].executeGeneric(frame);
		}
		
		CompilerAsserts.compilationConstant(variables.length);
		
		for (int i = 0; i < variables.length; i++) {
			variables[i].executeGeneric(frame);
		}
		
		
		NablaContext context = lookupContextReference(NablaLanguage.class).get();
		AllocationReporter reporter = context.getAllocationReporter();
		DynamicObject output = context.createObject(reporter);
		frame.getFrameDescriptor().getSlots().forEach(s -> {
			Object key = s.getIdentifier();
			Object value = frame.getValue(s);
			if (value instanceof NV0Bool) {
				output.define(key, ((NV0Bool) value).isData());
			} else if (value instanceof NV0Int) {
				output.define(key, ((NV0Int) value).getData());
			} else if (value instanceof NV1Int) {
				output.define(key, ((NV1Int) value).getData());
			} else if (value instanceof NV2Int) {
				output.define(key, ((NV2Int) value).getData());
			} else if (value instanceof NV0Real) {
				output.define(key, ((NV0Real) value).getData());
			} else if (value instanceof NV1Real) {
				output.define(key, ((NV1Real) value).getData());
			} else if (value instanceof NV2Real) {
				output.define(key, ((NV2Real) value).getData());
			}
		});
		
		return output;
	}

	
}
