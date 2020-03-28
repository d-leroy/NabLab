package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public abstract class NablaReadVariableNode extends NablaExpressionNode {

	private final FrameSlot slot;
	
	@CompilationFinal
	boolean initializationRequired = true;

	@CompilationFinal
	int depth = 0;

	public NablaReadVariableNode(FrameSlot slot) {
		this.slot = slot;
	}
	
	@ExplodeLoop
	@Specialization
	protected Object read(VirtualFrame frame) {
		if (initializationRequired) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			Truffle.getRuntime().iterateFrames(f -> {
				final FrameDescriptor result = f.getFrame(FrameAccess.READ_ONLY).getFrameDescriptor();
				if (result.getSlots().contains(slot)) {
					return result;
				}
				depth++;
				return null;
			});
			initializationRequired = false;
		}
		int[] i = new int[] { 0 };
		final Frame frameToRead = Truffle.getRuntime().iterateFrames(f -> {
			if (i[0] < depth) {
				i[0]++;
				return null;
			}
			return f.getFrame(FrameAccess.READ_ONLY);
		});
		
		try {
			return frameToRead.getObject(slot);
		} catch (FrameSlotTypeException e) {
			e.printStackTrace();
			return null;
		}
	}
}
