package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.Node;

public abstract class CreateNablaValueNode extends Node {

	public abstract NablaValue execute(Object object);

	@Specialization
	protected NablaValue createNablaValue(Boolean x) {
		return new NV0Bool(x);
	}

	@Specialization
	protected NablaValue createNablaValue(boolean[] x) {
		return new NV1Bool(x);
	}

	@Specialization
	protected NablaValue createNablaValue(boolean[][] x) {
		return new NV2Bool(x);
	}

	@Specialization
	protected NablaValue createNablaValue(Integer x) {
		return new NV0Int(x);
	}

	@Specialization
	protected NablaValue createNablaValue(int[] x) {
		return new NV1Int(x);
	}

	@Specialization
	protected NablaValue createNablaValue(int[][] x) {
		return new NV2Int(x);
	}

	@Specialization
	protected NablaValue createNablaValue(Double x) {
		return new NV0Real(x);
	}

	@Specialization
	protected NablaValue createNablaValue(double[] x) {
		return new NV1Real(x);
	}

	@Specialization
	protected NablaValue createNablaValue(double[][] x) {
		return new NV2Real(x);
	}

}
