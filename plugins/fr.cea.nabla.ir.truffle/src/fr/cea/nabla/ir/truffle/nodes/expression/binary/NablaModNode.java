package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.ir.truffle.values.NV0Int;

@NodeInfo(shortName = "%")
public abstract class NablaModNode extends NablaBinaryExpressionNode {
	
	
	@Specialization
	protected NV0Int mod(NV0Int left, NV0Int right) {
		return new NV0Int(left.getData() % right.getData());
	}
	
	
	
	
}
