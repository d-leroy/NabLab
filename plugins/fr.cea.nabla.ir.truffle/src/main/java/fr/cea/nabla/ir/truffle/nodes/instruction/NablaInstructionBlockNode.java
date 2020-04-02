package fr.cea.nabla.ir.truffle.nodes.instruction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.BlockNode;

public class NablaInstructionBlockNode extends NablaInstructionNode
		implements BlockNode.ElementExecutor<NablaInstructionNode> {

	@Child
	private BlockNode<NablaInstructionNode> block;

	public NablaInstructionBlockNode(NablaInstructionNode[] bodyNodes) {
		this.block = bodyNodes.length > 0 ? BlockNode.create(bodyNodes, this) : null;
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		if (this.block != null) {
			return this.block.executeGeneric(frame, BlockNode.NO_ARGUMENT);
		}
		return null;
	}

	public List<NablaInstructionNode> getStatements() {
		if (block == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(Arrays.asList(block.getElements()));
	}

	public Object executeGeneric(VirtualFrame frame, NablaInstructionNode node, int index, int argument) {
		return node.executeGeneric(frame);
	}

	@Override
	public void executeVoid(VirtualFrame frame, NablaInstructionNode node, int index, int argument) {
		node.executeGeneric(frame);
	}
}
