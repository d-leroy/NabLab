package fr.cea.nabla.interpreter.nodes;

import com.google.gson.JsonObject;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.NablaTypes;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.runtime.NablaNull;

@TypeSystemReference(NablaTypes.class)
public class NablaModulePrologNode extends NablaInstructionNode {
	JsonObject jsonMesh;
	@Children
	private NablaWriteVariableNode[] connectivityVariables;
	@Children
	private NablaWriteVariableNode[] variableDefinitions;
	@Children
	private NablaWriteVariableNode[] variableDeclarations;
	
	public NablaModulePrologNode(JsonObject jsonMesh,
			NablaWriteVariableNode[] connectivityVariables, NablaWriteVariableNode[] variableDeclarations,
			NablaWriteVariableNode[] variableDefinitions) {
		this.jsonMesh = jsonMesh;
		this.variableDeclarations = variableDeclarations;
		this.variableDefinitions = variableDefinitions;
		this.connectivityVariables = connectivityVariables;
	}

	@ExplodeLoop
	@Override
	public Object executeGeneric(VirtualFrame frame) {

		for (int i = 0; i < variableDefinitions.length; i++) {
			variableDefinitions[i].executeGeneric(frame);
		}

		NablaContext.initializeMesh(jsonMesh);

		for (int i = 0; i < connectivityVariables.length; i++) {
			connectivityVariables[i].executeGeneric(frame);
		}

		for (int i = 0; i < variableDeclarations.length; i++) {
			variableDeclarations[i].executeGeneric(frame);
		}

		return NablaNull.SINGLETON;
	}
	
	@Override
	public boolean isInstrumentable() {
		return false;
	}

}
