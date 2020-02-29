package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public abstract class NablaRootNode extends RootNode {

	@CompilationFinal
	private boolean isCloningAllowed;
	
	/** The name of the function, for printing purposes only. */
    private final String name;

	public NablaRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name) {
		super(language, frameDescriptor);
		this.name = name;
	}

	@Override
	public Object execute(VirtualFrame frame) {
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
