package fr.cea.nabla.aspects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gemoc.instrument.IContextWrapper;

import fr.cea.nabla.ir.interpreter.Context;
import fr.cea.nabla.ir.interpreter.FunctionCallHelper;
import fr.cea.nabla.ir.interpreter.NablaValue;
import fr.cea.nabla.ir.ir.Variable;

public class NablaContextWrapper implements IContextWrapper {

	private final Context context;
	private final Map<String, Variable> nameToVariable = new HashMap<>();
	
	public NablaContextWrapper(Context context) {
		this.context = context;
		this.context.getIr().getVariables().forEach(v -> nameToVariable.put(v.getName(), v));
	}
	
	@Override
	public Object getVariableValue(String variableName) {
		final NablaValue value = context.getVariableValue(nameToVariable.get(variableName));
		return FunctionCallHelper.getJavaValue(value);
	}

	@Override
	public void setVariableValue(String variableName, Object value) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<String> getVariableNames() {
		return nameToVariable.keySet().stream().collect(Collectors.toList());
	}
	
}
