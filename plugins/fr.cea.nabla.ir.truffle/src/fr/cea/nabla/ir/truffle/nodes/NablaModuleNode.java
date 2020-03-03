package fr.cea.nabla.ir.truffle.nodes;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaTypes;
import fr.cea.nabla.ir.truffle.values.NablaOutput;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@TypeSystemReference(NablaTypes.class)
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
		
		final Map<String, NablaValue> outputMap = new HashMap<>();
		frame.getFrameDescriptor().getSlots().forEach(s -> {
			Object key = s.getIdentifier();
			Object value = frame.getValue(s);
			if (value instanceof NablaValue) {
				outputMap.put(key.toString(), (NablaValue) value);
			}
		});

		final NablaOutput output = new NablaOutput(outputMap);
		
		return output;
	}

	
}
