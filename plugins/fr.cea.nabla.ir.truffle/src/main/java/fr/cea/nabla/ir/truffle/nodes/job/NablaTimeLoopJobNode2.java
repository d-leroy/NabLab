package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;
import java.util.Locale;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLogger;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameInstance.FrameAccess;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

//@NodeChild(value = "toWrite", type = GetFrameNode.class)
public class NablaTimeLoopJobNode2 extends NablaJobNode {

//	private static final TruffleLogger LOG = TruffleLogger.getLogger(NablaLanguage.ID, NablaTimeLoopJobNode.class);

	@Children
	private DirectCallNode[] innerJobs;
	@Child
	private NablaExpressionNode conditionNode;
	private final FrameSlot indexSlot;
	private final List<FrameSlot[]> copies;

	private final String indentation;
	private final FrameSlot timeSlot;
	private final FrameSlot deltatSlot;

	public NablaTimeLoopJobNode2(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, String name,
			FrameSlot indexSlot, List<FrameSlot[]> copies, NablaExpressionNode conditionNode, NablaJobNode[] innerJobs,
			String indentation, FrameSlot timeSlot, FrameSlot deltatSlot) {
		super(language, frameDescriptor, name);
		this.indexSlot = indexSlot;
		this.copies = copies;
		this.conditionNode = conditionNode;
		this.innerJobs = new DirectCallNode[innerJobs.length];
		for (int i = 0; i < innerJobs.length; i++) {
			this.innerJobs[i] = Truffle.getRuntime()
					.createDirectCallNode(Truffle.getRuntime().createCallTarget(innerJobs[i]));
		}

		this.indentation = indentation;
		this.timeSlot = timeSlot;
		this.deltatSlot = deltatSlot;
	}

	private int depth = 0;
	private boolean initReq = true;

	@Override
	public Object execute(VirtualFrame frame) {
//		if (initReq) {
//			CompilerDirectives.transferToInterpreterAndInvalidate();
//			FrameDescriptor descriptor = Truffle.getRuntime().iterateFrames(f -> {
//				final FrameDescriptor result = f.getFrame(FrameAccess.READ_ONLY).getFrameDescriptor();
//				if (result.getSlots().contains(indexSlot)) {
//					return result;
//				}
//				depth++;
//				return null;
//			});
//			descriptor.setFrameSlotKind(indexSlot, FrameSlotKind.Object);
//			initReq = false;
//		}
		int i = 0;
		boolean continueLoop = true;

//		int[] iPtr = new int[] { 0 };
		final Frame frameToWrite = (Frame) frame.getArguments()[0];
		frameToWrite.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
//		Truffle.getRuntime().iterateFrames(f -> {
//			if (iPtr[0] < depth) {
//				iPtr[0]++;
//				return null;
//			}
//			return f.getFrame(FrameAccess.READ_WRITE);
//		});

		do {
			i++;
			frameToWrite.setObject(indexSlot, new NV0Int(i));

//			try {
//				final String log = getTimeVariablesString(indentation, i,
//						NablaTypesGen.asNV0Real(frameToWrite.getObject(timeSlot)).getData(),
//						NablaTypesGen.asNV0Real(frameToWrite.getObject(deltatSlot)).getData());
//				LOG.log(NablaLogLevel.FINE, log);
//				System.out.println(log);
//			} catch (FrameSlotTypeException e1) {
//				e1.printStackTrace();
//			}

			// TODO dump variables (interop message?)
//			for (int j = 0; j < innerJobs.length; j++) {
//				innerJobs[j].call(frame.getArguments()[0]);
//			}
			callJobs(frame);
			
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
//		final String log = getIterationNumberString(i);
//		LOG.log(NablaLogLevel.INFO, log);
//		System.out.println(log);
		return null;
	}
	
	@ExplodeLoop
	private void callJobs(VirtualFrame frame) {
		for (int j = 0; j < innerJobs.length; j++) {
			innerJobs[j].call(frame.getArguments()[0]);
		}
	}
	
	@TruffleBoundary
	private String getIterationNumberString(int i) {
		return String.format("%1$s Nb iteration %2$s = %3$d", indentation, indexSlot.getIdentifier(), i);
	}
	
	@TruffleBoundary
	private String getTimeVariablesString(String indentation, int iteration, double t, double dt) {
		return String.format(Locale.ENGLISH, "%1$s [%2$d] t: %3$.5f - deltat: %4$.5f", indentation, iteration, t, dt);
	}
	
}
