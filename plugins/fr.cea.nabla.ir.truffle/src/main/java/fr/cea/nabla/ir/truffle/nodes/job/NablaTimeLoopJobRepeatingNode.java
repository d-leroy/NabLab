package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;

import fr.cea.nabla.ir.truffle.NablaTypesGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.runtime.NablaInternalError;
import fr.cea.nabla.ir.truffle.values.NV0Int;

public class NablaTimeLoopJobRepeatingNode extends Node implements RepeatingNode {

	@Children
	private DirectCallNode[] innerJobs;
	@Child
	private NablaExpressionNode conditionNode;
	private final FrameSlot indexSlot;
	@CompilationFinal(dimensions = 2)
	private FrameSlot[][] copies;

	public NablaTimeLoopJobRepeatingNode(FrameSlot indexSlot, List<FrameSlot[]> copies,
			NablaExpressionNode conditionNode, NablaJobNode[] innerJobs) {
		this.indexSlot = indexSlot;
		this.copies = copies.toArray(new FrameSlot[0][0]);
		this.conditionNode = conditionNode;
		this.innerJobs = new DirectCallNode[innerJobs.length];
		for (int i = 0; i < innerJobs.length; i++) {
			this.innerJobs[i] = Truffle.getRuntime()
					.createDirectCallNode(Truffle.getRuntime().createCallTarget(innerJobs[i]));
		}
	}

	@Override
	@ExplodeLoop
	public boolean executeRepeating(VirtualFrame frame) {
		try {
			final Frame frameToWrite = (Frame) frame.getArguments()[0];
			final int i = NablaTypesGen.asNV0Int(frameToWrite.getObject(indexSlot)).getData() + 1;
			frameToWrite.setObject(indexSlot, new NV0Int(i));
			for (int j = 0; j < innerJobs.length; j++) {
				innerJobs[j].call(frame.getArguments()[0]);
			}
			final boolean continueLoop = NablaTypesGen.asNV0Bool(conditionNode.executeGeneric(frame)).isData();
			if (CompilerDirectives.injectBranchProbability(0.9, continueLoop)) {
				for (int j = 0; j < copies.length; j++) {
					try {
						FrameSlot[] copy = copies[j];
						final Object left = frameToWrite.getObject(copy[1]);
						final Object right = frameToWrite.getObject(copy[0]);
						frameToWrite.setObject(copy[0], left);
						frameToWrite.setObject(copy[1], right);
					} catch (FrameSlotTypeException e) {
						e.printStackTrace();
					}
				}
			}
			return continueLoop;
		} catch (FrameSlotTypeException e1) {
			e1.printStackTrace();
			throw NablaInternalError.shouldNotReachHere();
		}
	}

}
