package fr.cea.nabla.interpreter.nodes;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.InstrumentableNode;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.NablaTypes;
import fr.cea.nabla.interpreter.NablaTypesGen;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.nodes.job.NablaJobBlockNode;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NablaOutput;
import fr.cea.nabla.interpreter.values.NablaValue;

@GenerateWrapper
@TypeSystemReference(NablaTypes.class)
public class NablaModuleNode extends NablaNode implements InstrumentableNode {

	@Children
	private NablaExpressionNode[] mandatoryVariables;

	private final FrameSlot coordinatesSlot;
	@Children
	private NablaWriteVariableNode[] connectivityVariables;
	@Children
	private NablaWriteVariableNode[] variableDefinitions;
	@Children
	private NablaWriteVariableNode[] variableDeclarations;
	@Child
	private NablaJobBlockNode jobBlock;

	public NablaModuleNode(NablaExpressionNode[] mandatoryVariables, FrameSlot coordinatesSlot,
			NablaWriteVariableNode[] connectivityVariables, NablaWriteVariableNode[] variableDeclarations,
			NablaWriteVariableNode[] variableDefinitions, NablaRootNode[] jobs) {
		this.mandatoryVariables = mandatoryVariables;
		this.coordinatesSlot = coordinatesSlot;
		this.variableDeclarations = variableDeclarations;
		this.variableDefinitions = variableDefinitions;
		this.connectivityVariables = connectivityVariables;
		this.jobBlock = new NablaJobBlockNode(jobs);
	}

	protected NablaModuleNode() {
		this.coordinatesSlot = null;
	}

	@ExplodeLoop
	@Override
	public Object executeGeneric(VirtualFrame frame) {

		final MaterializedFrame globalFrame = frame.materialize();

		for (int i = 0; i < variableDefinitions.length; i++) {
			variableDefinitions[i].executeGeneric(globalFrame);
		}

		if (mandatoryVariables.length == 4) {
			NablaContext.getMeshWrapper().initialize(
					NablaTypesGen.asNV0Int(mandatoryVariables[0].executeGeneric(globalFrame)).getData(),
					NablaTypesGen.asNV0Int(mandatoryVariables[1].executeGeneric(globalFrame)).getData(),
					NablaTypesGen.asNV0Real(mandatoryVariables[2].executeGeneric(globalFrame)).getData(),
					NablaTypesGen.asNV0Real(mandatoryVariables[3].executeGeneric(globalFrame)).getData());
		}

		for (int i = 0; i < connectivityVariables.length; i++) {
			connectivityVariables[i].executeGeneric(globalFrame);
		}

		for (int i = 0; i < variableDeclarations.length; i++) {
			variableDeclarations[i].executeGeneric(globalFrame);
		}

		globalFrame.setObject(coordinatesSlot, new NV2Real(NablaContext.getMeshWrapper().getNodes()));
		globalFrame.getFrameDescriptor().setFrameSlotKind(coordinatesSlot, FrameSlotKind.Object);

		jobBlock.executeGeneric(globalFrame);

		final Map<String, NablaValue> outputMap = new HashMap<>();
		globalFrame.getFrameDescriptor().getSlots().forEach(s -> {
			Object key = s.getIdentifier();
			Object value = globalFrame.getValue(s);
			if (value instanceof NablaValue) {
				outputMap.put(key.toString(), (NablaValue) value);
			}
		});

		final NablaOutput output = new NablaOutput(outputMap);

		return output;
	}

	@Override
	public WrapperNode createWrapper(ProbeNode probe) {
		return new NablaModuleNodeWrapper(this, probe);
	}
	
	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		return tag.equals(StandardTags.RootTag.class) || super.hasTag(tag);
	}

}
