package fr.cea.nabla.ir.truffle.nodes.instruction;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Int;

@NodeChild(value="count", type=NablaExpressionNode.class)
public abstract class NablaLoopNode extends NablaInstructionNode {

	private final FrameSlot indexSlot;

	@Child private NablaInstructionNode body;
	
	protected final Assumption lengthUnchanged = Truffle.getRuntime().createAssumption();
	
	public NablaLoopNode(FrameSlot indexSlot, NablaInstructionNode body) {
		this.indexSlot = indexSlot;
		this.body = body;
	}

	@Specialization(guards = "count.getData() == cachedCount")
	public Object doLoop(VirtualFrame frame, NV0Int count, @Cached("count.getData()") int cachedCount) {
		CompilerDirectives.isPartialEvaluationConstant(cachedCount);
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		//================================
//		final int offset = 1;
//		final int iMax = 20;
//		final int iiMax = (cachedCount - 1 - offset) / iMax;
//		
//		int ii = 0;
//		
//		for (; ii < iiMax; ii++) {
//			doLoopInternal(frame, ii * iMax, iMax, offset);
//		}
//		
//		for (int i = ii * iMax + offset; i < cachedCount - 1; i++) {
//			frame.setObject(indexSlot, new NV0Int(i));
//			body.executeGeneric(frame);
//		}
		//================================
		for (int i = 1; i < cachedCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(cachedCount - 1));
		return body.executeGeneric(frame);
	}

	@Specialization
	public Object doLoop(VirtualFrame frame, NV0Int count) {
		final int iterationCount = count.getData();
		CompilerDirectives.isPartialEvaluationConstant(iterationCount);
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		for (int i = 1; i < iterationCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(iterationCount - 1));
		return body.executeGeneric(frame);
	}

	@Specialization(guards = "elements_.getData().length == cachedCount")
	public Object doLoop(VirtualFrame frame, NV1Int elements_, @Cached("elements_.getData().length") int cachedCount) {
		CompilerDirectives.isPartialEvaluationConstant(cachedCount);
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		//================================
//		final int offset = 1;
//		final int iMax = 20;
//		final int iiMax = (cachedCount - 1 - offset) / iMax;
//		
//		int ii = 0;
//		
//		for (; ii < iiMax; ii++) {
//			doLoopInternal(frame, ii * iMax, iMax, offset);
//		}
//		
//		for (int i = ii * iMax + offset; i < cachedCount - 1; i++) {
//			frame.setObject(indexSlot, new NV0Int(i));
//			body.executeGeneric(frame);
//		}
		//================================
		for (int i = 1; i < cachedCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(cachedCount-1));
		return body.executeGeneric(frame);
	}
	
	@Specialization
	public Object doLoop(VirtualFrame frame, NV1Int elements_) {
		final int[] elements = elements_.getData();
		final int iterationCount = elements.length;
		CompilerDirectives.isPartialEvaluationConstant(iterationCount);
		frame.setObject(indexSlot, new NV0Int(0));
		frame.getFrameDescriptor().setFrameSlotKind(indexSlot, FrameSlotKind.Object);
		body.executeGeneric(frame);
		for (int i = 1; i < iterationCount - 1; i++) {
			frame.setObject(indexSlot, new NV0Int(i));
			body.executeGeneric(frame);
		}
		frame.setObject(indexSlot, new NV0Int(iterationCount-1));
		return body.executeGeneric(frame);
	}
	
	@ExplodeLoop
	private void doLoopInternal(VirtualFrame frame, int ii, int iMax, int offset) {
		for (int i = 0; i < iMax; i++) {
			frame.setObject(indexSlot, new NV0Int(ii+i+offset));
			body.executeGeneric(frame);
		}
	}
}
