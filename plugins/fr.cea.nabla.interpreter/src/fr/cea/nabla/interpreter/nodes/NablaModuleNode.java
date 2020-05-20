package fr.cea.nabla.interpreter.nodes;

import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLogger;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.NablaLogLevel;
import fr.cea.nabla.interpreter.NablaTypes;
import fr.cea.nabla.interpreter.NablaTypesGen;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NablaOutput;
import fr.cea.nabla.interpreter.values.NablaValue;

@GenerateWrapper
@TypeSystemReference(NablaTypes.class)
public class NablaModuleNode extends NablaNode {

	@Children
	private NablaExpressionNode[] mandatoryVariables;

	private final FrameSlot coordinatesSlot;
	@Children
	private NablaWriteVariableNode[] connectivityVariables;
	@Children
	private NablaWriteVariableNode[] variableDefinitions;
	@Children
	private NablaWriteVariableNode[] variableDeclarations;
	@Children
	private DirectCallNode[] jobs;

	public NablaModuleNode(NablaExpressionNode[] mandatoryVariables, FrameSlot coordinatesSlot,
			NablaWriteVariableNode[] connectivityVariables, NablaWriteVariableNode[] variableDeclarations,
			NablaWriteVariableNode[] variableDefinitions, NablaRootNode[] jobs) {
		this.mandatoryVariables = mandatoryVariables;
		this.coordinatesSlot = coordinatesSlot;
		this.variableDeclarations = variableDeclarations;
		this.variableDefinitions = variableDefinitions;
		this.connectivityVariables = connectivityVariables;
		this.jobs = new DirectCallNode[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			this.jobs[i] = Truffle.getRuntime().createDirectCallNode(Truffle.getRuntime().createCallTarget(jobs[i]));
		}
	}

	protected NablaModuleNode() {
		this.coordinatesSlot = null;
	}

	private static final TruffleLogger LOG = TruffleLogger.getLogger(NablaLanguage.ID, NablaModuleNode.class);

	@ExplodeLoop
	@Override
	public Object executeGeneric(VirtualFrame frame) {

		final MaterializedFrame globalFrame = frame.materialize();

		LOG.log(NablaLogLevel.INFO, " Start interpreting " + getRootNode().getName() + " module ");

		CompilerAsserts.compilationConstant(variableDefinitions.length);
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

		CompilerAsserts.compilationConstant(variableDeclarations.length);
		for (int i = 0; i < variableDeclarations.length; i++) {
			variableDeclarations[i].executeGeneric(globalFrame);
		}

		globalFrame.setObject(coordinatesSlot, new NV2Real(NablaContext.getMeshWrapper().getNodes()));
		globalFrame.getFrameDescriptor().setFrameSlotKind(coordinatesSlot, FrameSlotKind.Object);

		CompilerAsserts.compilationConstant(jobs.length);
		for (int i = 0; i < jobs.length; i++) {
			jobs[i].call(globalFrame, this.getRootNode());
		}

		LOG.log(NablaLogLevel.INFO, " End interpreting");

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

}
