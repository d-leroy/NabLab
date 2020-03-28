package fr.cea.nabla.ir.truffle.nodes;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaTypes;
import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaJobNode;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NablaOutput;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@TypeSystemReference(NablaTypes.class)
public class NablaModuleNode extends NablaRootNode {

	@Children
	private NablaWriteVariableNode[] constants;
	@Children
	private NablaExpressionNode[] mandatoryOptions;
	private final FrameSlot coordinatesSlot;
	@Children
	private NablaWriteVariableNode[] connectivityVariables;
	@Children
	private NablaWriteVariableNode[] variables;
	@Children
	private DirectCallNode[] jobs;

	public NablaModuleNode(NablaLanguage language, FrameDescriptor frameDescriptor, String name,
			NablaWriteVariableNode[] constants, NablaExpressionNode[] mandatoryOptions, FrameSlot coordinatesSlot,
			NablaWriteVariableNode[] connectivityVariables, NablaWriteVariableNode[] variables,
			NablaFunctionNode[] functions, NablaJobNode[] jobs) {
		super(language, frameDescriptor, name);
		this.constants = constants;
		this.mandatoryOptions = mandatoryOptions;
		this.coordinatesSlot = coordinatesSlot;
		this.variables = variables;
		this.connectivityVariables = connectivityVariables;
		this.jobs = new DirectCallNode[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			this.jobs[i] = Truffle.getRuntime().createDirectCallNode(Truffle.getRuntime().createCallTarget(jobs[i]));
		}
	}

	@ExplodeLoop
	public Object execute(VirtualFrame frame) {
		CompilerAsserts.compilationConstant(constants.length);
		for (int i = 0; i < constants.length; i++) {
			constants[i].executeGeneric(frame);
		}

		if (mandatoryOptions.length == 4) {
			NablaContext.getMeshWrapper().initialize(
					NablaTypesGen.asNV0Int(mandatoryOptions[0].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Int(mandatoryOptions[1].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Real(mandatoryOptions[2].executeGeneric(frame)).getData(),
					NablaTypesGen.asNV0Real(mandatoryOptions[3].executeGeneric(frame)).getData());
		}

		for (int i = 0; i < connectivityVariables.length; i++) {
			connectivityVariables[i].executeGeneric(frame);
		}

		CompilerAsserts.compilationConstant(variables.length);
		for (int i = 0; i < variables.length; i++) {
			variables[i].executeGeneric(frame);
		}
		
		frame.setObject(coordinatesSlot, new NV2Real(NablaContext.getMeshWrapper().getNodes()));
		frame.getFrameDescriptor().setFrameSlotKind(coordinatesSlot, FrameSlotKind.Object);

		CompilerAsserts.compilationConstant(jobs.length);
		for (int i = 0; i < jobs.length; i++) {
			jobs[i].call();
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
