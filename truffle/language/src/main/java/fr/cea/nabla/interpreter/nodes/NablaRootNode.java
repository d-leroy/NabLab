package fr.cea.nabla.interpreter.nodes;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;

import javax.print.attribute.SetOfIntegerSyntax;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.SourceSection;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.nodes.instruction.NablaInstructionBlockNode;
import fr.cea.nabla.interpreter.nodes.instruction.NablaPrologBlockNode;

@NodeInfo(language = "Nabla", description = "The root of all Nabla execution trees")
public class NablaRootNode extends RootNode {

	@Child
	private NablaPrologBlockNode prologNode;
	
	@Child
	private NablaInstructionBlockNode bodyNode;

	@CompilationFinal
	private boolean isCloningAllowed = true;

	private final String name;

	@CompilationFinal
	private SourceSection sourceSection;

	public NablaRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, NablaPrologBlockNode prologNode, NablaInstructionBlockNode bodyNode,
			String name) {
		super(language, frameDescriptor);
		this.name = name;
		this.prologNode = prologNode;
		this.bodyNode = bodyNode;
		if (this.bodyNode != null) {
			bodyNode.addRootBodyTag();
		}
	}
	
	@Override
	public SourceSection getSourceSection() {
		return sourceSection;
	}

	public final void setSourceSection(SourceSection sourceSection) {
		assert this.sourceSection == null : "source must only be set once";
		this.sourceSection = sourceSection;
	}
	
	@Override
	public Object execute(VirtualFrame frame) {
		assert lookupContextReference(NablaLanguage.class).get() != null;
		if (prologNode != null) {
			prologNode.executeGeneric(frame);
		}
		return bodyNode.executeGeneric(frame);
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public boolean isCloningAllowed() {
		return isCloningAllowed;
	}

	public void setCloningAllowed(boolean isCloningAllowed) {
		this.isCloningAllowed = isCloningAllowed;
	}

}
