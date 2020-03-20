package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

public class NablaTimeLoopJobNode extends NablaJobNode {

	@Children
	private DirectCallNode[] innerJobs;
	@Child
	private NablaExpressionNode conditionNode;
	private final FrameSlot indexSlot;
	private final List<FrameSlot[]> copies;

	public NablaTimeLoopJobNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaJobNode[] innerJobs) {
		super(language, frameDescriptor, name);
		this.indexSlot = indexSlot;
		this.copies = copies;
		this.conditionNode = conditionNode;
		this.innerJobs = new DirectCallNode[innerJobs.length];
		for (int i = 0; i < innerJobs.length; i++) {
			this.innerJobs[i] = Truffle.getRuntime()
					.createDirectCallNode(Truffle.getRuntime().createCallTarget(innerJobs[i]));
		}
	}

	private int depth = 0;
	private boolean initReq = true;
	
	@Override
	@ExplodeLoop
	public Object execute(VirtualFrame frame) {
		if (initReq) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			FrameDescriptor descriptor = Truffle.getRuntime().iterateFrames(f -> {
				final FrameDescriptor result = f.getFrame(FrameAccess.READ_ONLY).getFrameDescriptor();
				if (result.getSlots().contains(indexSlot)) {
					return result;
				}
				depth++;
				return null;
			});
			descriptor.setFrameSlotKind(indexSlot, FrameSlotKind.Object);
			initReq = false;
		}
		int i = 0;
		boolean continueLoop = true;
		do  {
			i++;
			int[] iPtr = new int[] { 0 };
			final Frame frameToWrite = Truffle.getRuntime().iterateFrames(f -> {
				if (iPtr[0] < depth) {
					iPtr[0]++;
					return null;
				}
				return f.getFrame(FrameAccess.READ_WRITE);
			});
			frameToWrite.setObject(indexSlot, new NV0Int(i));
//			frame.setObject(indexSlot, new NV0Int(i));
			//TODO dump variables (interop message?)
			for (int j = 0; j < innerJobs.length; j++) {
				innerJobs[j].call();
			}
			continueLoop = NablaTypesGen.asNV0Bool(conditionNode.executeGeneric(frame)).isData();
			if (continueLoop) {
				for (FrameSlot[] copy : copies) {
					try {
						final Object left = frameToWrite.getObject(copy[1]);
						final Object right = frameToWrite.getObject(copy[0]);
						frameToWrite.setObject(copy[0], left);
						frameToWrite.setObject(copy[1], right);
					} catch (FrameSlotTypeException e) {
						e.printStackTrace();
					}
				}
			}
		} while (continueLoop);
		
		return null;
	}
}
