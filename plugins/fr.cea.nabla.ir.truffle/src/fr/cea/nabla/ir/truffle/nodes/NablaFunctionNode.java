package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;

public class NablaFunctionNode extends NablaRootNode {

	@Child private NablaInstructionNode bodyNode;
	
	public NablaFunctionNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, NablaInstructionNode bodyNode, String name) {
		super(language, frameDescriptor, name);
		this.bodyNode = bodyNode; 
	}
	
	@Override
	public Object execute(VirtualFrame frame) {
		return bodyNode.executeGeneric(frame);
	}
}
