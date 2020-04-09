package fr.cea.nabla.ir.truffle.nodes.job;

import java.util.List;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
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
import fr.cea.nabla.ir.truffle.utils.GetFrameNode;
import fr.cea.nabla.ir.truffle.values.NV0Int;

@NodeChild(value = "frameToWrite", type = GetFrameNode.class)
public abstract class NablaTimeLoopJobRepeatingNode extends Node implements RepeatingNode {

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

	/*
	 * Necessary to avoid errors in generated class.
	 */
	@Override
	public final Object executeRepeatingWithValue(VirtualFrame frame) {
		if (executeRepeating(frame)) {
            return CONTINUE_LOOP_STATUS;
        } else {
            return BREAK_LOOP_STATUS;
        }
	}
	
	@ExplodeLoop
	@Specialization
	public boolean doLoop(VirtualFrame frame, Frame toWrite) {
		try {
			final int i = NablaTypesGen.asNV0Int(frame.getObject(indexSlot)).getData() + 1;
			frame.setObject(indexSlot, new NV0Int(i));
			final Frame materializedFrame = frame.materialize();
			for (int j = 0; j < innerJobs.length; j++) {
				innerJobs[j].call(materializedFrame);
			}
			final boolean continueLoop = NablaTypesGen.asNV0Bool(conditionNode.executeGeneric(frame)).isData();
			if (CompilerDirectives.injectBranchProbability(0.9, continueLoop)) {
				for (int j = 0; j < copies.length; j++) {
					try {
						FrameSlot[] copy = copies[j];
						final Object left = toWrite.getObject(copy[1]);
						final Object right = toWrite.getObject(copy[0]);
						toWrite.setObject(copy[0], left);
						toWrite.setObject(copy[1], right);
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
