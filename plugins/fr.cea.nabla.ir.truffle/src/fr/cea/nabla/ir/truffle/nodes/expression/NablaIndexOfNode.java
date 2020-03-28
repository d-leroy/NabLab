package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV1Int;

@NodeChild("arrayNode")
@NodeChild("value")
public abstract class NablaIndexOfNode extends NablaExpressionNode {
	
	@Specialization
	protected NV0Int readNV1Int1Index(VirtualFrame frame, NV1Int array, NV0Int value) {
		final int[] actualArray = array.getData();
		final int actualValue = value.getData();
		for (int i = 0; i < actualArray.length; i++) {
			if (actualArray[i] == actualValue) {
				return new NV0Int(i);
			}
		}
		return new NV0Int(-1);
	}
}
