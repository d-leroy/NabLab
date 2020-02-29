package fr.cea.nabla.truffle.tests;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.Assert;

public class TruffleTestUtils {

	public static final double DOUBLE_TOLERANCE = 1e-15;
	
	public static Value executeModel(String model) {
		final Context context = org.graalvm.polyglot.Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).build();
		return context.eval("nabla", model);
	}
	
	private static Value getVariable(Value result, String variableName) {
		Assert.assertTrue(result.hasMember(variableName));
		return result.getMember(variableName);
	}
	
	public static void assertVariableValue(Value result, String variableName, boolean value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isBoolean());
		Assert.assertEquals(value, val.asBoolean());
		System.out.println(variableName + " = " + value);
	}

	public static void assertVariableValue(Value result, String variableName, int value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isNumber());
		Assert.assertTrue(val.fitsInInt());
		Assert.assertEquals(value, val.asInt());
		System.out.println(variableName + " = " + value);
	}

	public static void assertVariableValue(Value result, String variableName, int[] value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		final int size = (int) val.getArraySize();
		final int[] valArray = new int[size];
		for (int i = 0; i < size; i++) {
			Assert.assertTrue(val.getArrayElement(i).fitsInInt());
			valArray[i] = val.getArrayElement(i).asInt();
		}
		Assert.assertEquals(value, valArray);
		System.out.println(variableName + " = " + value);
	}
	
	public static void assertVariableValue(Value result, String variableName, double value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isNumber());
		Assert.assertTrue(val.fitsInDouble());
		Assert.assertEquals(value, val.asDouble(), DOUBLE_TOLERANCE);
		System.out.println(variableName + " = " + value);
	}
	
}
