package fr.cea.nabla.ir.truffle.nodes.expression.binary;

import com.google.common.primitives.Booleans;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;

@NodeInfo(shortName = ">=")
public abstract class NablaGeqNode extends NablaBinaryExpressionNode {
	
	@Specialization
	protected NV0Bool add(NV0Bool left, NV0Bool right) {
		return new NV0Bool(Booleans.compare(left.isData(), right.isData()) >= 0);
	}
	
	@Specialization
	protected NV0Bool geq(NV0Int left, NV0Int right) {
		return new NV0Bool(left.getData() >= right.getData());
	}
	
	@Specialization
	protected NV0Bool geq(NV0Int left, NV0Real right) {
		return new NV0Bool(left.getData() >= right.getData());
	}
	
	
	@Specialization
	protected NV0Bool geq(NV0Real left, NV0Int right) {
		return new NV0Bool(left.getData() >= right.getData());
	}
	
	@Specialization
	protected NV0Bool geq(NV0Real left, NV0Real right) {
		return new NV0Bool(left.getData() >= right.getData());
	}
	
}
