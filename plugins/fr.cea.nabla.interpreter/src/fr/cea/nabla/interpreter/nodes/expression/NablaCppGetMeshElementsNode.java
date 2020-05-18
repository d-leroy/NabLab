package fr.cea.nabla.interpreter.nodes.expression;

import org.graalvm.polyglot.TypeLiteral;
import org.graalvm.polyglot.Value;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

import fr.cea.nabla.interpreter.NablaTypesGen;
import fr.cea.nabla.interpreter.runtime.NablaContext;
import fr.cea.nabla.interpreter.runtime.NablaInitializationPerformedException;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV1Int;

public abstract class NablaCppGetMeshElementsNode extends NablaExpressionNode {

	@CompilationFinal
	protected boolean initializationRequired = true;
	@Children
	private final NablaExpressionNode[] argIds;
	private final String connectivityName;
	@CompilationFinal
	private Value mesh;
	@CompilationFinal
	private Value getter;
	
	static final TypeLiteral<Integer> NV0Int = new TypeLiteral<Integer>() {};
	static final TypeLiteral<int[]> NV1Int = new TypeLiteral<int[]>() {};
	
	public NablaCppGetMeshElementsNode(NablaExpressionNode[] argIds, String connectivityName) {
		this.argIds = argIds;
		this.connectivityName = connectivityName;
	}
	
	@Specialization(guards = "initializationRequired", //
			rewriteOn = NablaInitializationPerformedException.class)
	protected Object initialize(VirtualFrame frame) throws NablaInitializationPerformedException {
		CompilerDirectives.transferToInterpreterAndInvalidate();
		mesh = NablaContext.getMeshWrapper().getMesh();
		getter = NablaContext.getMeshWrapper().connectivityGetter(connectivityName);
		initializationRequired = false;
		throw new NablaInitializationPerformedException();
	}

	@ExplodeLoop
	@Specialization(guards = "!initializationRequired")
	protected Object doGet(VirtualFrame frame) {
		final Object[] argIdsValues = new Object[argIds.length + 1];
		argIdsValues[0] = mesh;
		for (int i = 0; i < argIds.length; i++) {
			argIdsValues[i + 1] = NablaTypesGen.asNV0Int(argIds[i].executeGeneric(frame)).getData();
		}
		Value v = getter.execute(argIdsValues);
		if (v.isNumber()) {
			return new NV0Int(v.as(NV0Int));
		} else {
			return new NV1Int(v.as(NV1Int));
		}
	}
}
