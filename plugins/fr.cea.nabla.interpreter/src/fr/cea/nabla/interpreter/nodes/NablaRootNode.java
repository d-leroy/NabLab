package fr.cea.nabla.interpreter.nodes;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.SourceSection;
import com.oracle.truffle.api.utilities.CyclicAssumption;

import fr.cea.nabla.interpreter.NablaLanguage;

@NodeInfo(language = "Nabla", description = "The root of all Nabla execution trees")
public class NablaRootNode extends RootNode {

	@Child
	private NablaNode bodyNode;
	
	@CompilationFinal
	private boolean isCloningAllowed;

	private final String name;

	@CompilationFinal
    private SourceSection sourceSection;

	/**
	 * This assumption is only invalidated when invoking functions, as this is the
	 * only case where we cannot guarantee that the frame holding global variables
	 * can be found at a constant depth.
	 */
	protected final CyclicAssumption frameStable;

	public NablaRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, NablaNode bodyNode, String name) {
		super(language, frameDescriptor);
		this.name = name;
		this.bodyNode = bodyNode;
		this.frameStable = new CyclicAssumption(name);
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
		frameStable.invalidate();
        return bodyNode.executeGeneric(frame);
    }

	@Override
	public String getName() {
		return name;
	}

	public boolean isCloningAllowed() {
		return isCloningAllowed;
	}

	public void setCloningAllowed(boolean isCloningAllowed) {
		this.isCloningAllowed = isCloningAllowed;
	}

	public Assumption getFrameStableAssumption() {
		return frameStable.getAssumption();
	}
}
