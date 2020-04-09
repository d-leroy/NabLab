package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.utilities.CyclicAssumption;

public abstract class NablaRootNode extends RootNode implements NablaNode {

	@CompilationFinal
	private boolean isCloningAllowed;
	
	/** The name of the function, for printing purposes only. */
    private final String name;
    
    protected final CyclicAssumption frameStable;

	public NablaRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name) {
		super(language, frameDescriptor);
		this.name = name;
		this.frameStable = new CyclicAssumption(name);
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
