package fr.cea.nabla.truffle.tests;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.Assert;

public class TruffleTestUtils {

	public static final double DOUBLE_TOLERANCE = 1e-15;

	public static Value executeModel(String model) {
		final Context context = org.graalvm.polyglot.Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL)
				.build();
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
	}

	public static void assertVariableValue(Value result, String variableName, boolean[] value) {
		Assert.assertFalse("Feature not yet supported", true);
	}

	public static void assertVariableValue(Value result, String variableName, boolean[][] value) {
		Assert.assertFalse("Feature not yet supported", true);
	}

	public static void assertVariableValue(Value result, String variableName, int value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isNumber());
		Assert.assertTrue(val.fitsInInt());
		Assert.assertEquals(value, val.asInt());
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
		Assert.assertArrayEquals(value, valArray);
	}

	public static void assertVariableValue(Value result, String variableName, int[][] value) {
		Assert.assertFalse("Feature not yet supported", true);
	}

	public static void assertVariableValue(Value result, String variableName, double value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isNumber());
		Assert.assertTrue(val.fitsInDouble());
		Assert.assertEquals(value, val.asDouble(), DOUBLE_TOLERANCE);
	}

	public static void assertVariableValue(Value result, String variableName, double[] value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		final int size = (int) val.getArraySize();
		final double[] valArray = new double[size];
		for (int i = 0; i < size; i++) {
			Assert.assertTrue(val.getArrayElement(i).fitsInDouble());
			valArray[i] = val.getArrayElement(i).asDouble();
		}
		Assert.assertArrayEquals(value, valArray, DOUBLE_TOLERANCE);
	}

	public static void assertVariableValue(Value result, String variableName, double[][] value) {
		Assert.assertFalse("Feature not yet supported", true);
	}

}
