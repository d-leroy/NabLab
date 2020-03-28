package fr.cea.nabla.truffle.tests;

import java.util.logging.Handler;
import java.util.logging.Level;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.Assert;

public class TruffleTestUtils {

	public static final double DOUBLE_TOLERANCE = 1e-15;

	private static final String DEFAULT_GEN_MODEL =
		"with Test.*;" + "\n" +
		"workflow TestDefaultGenerationChain transforms Test" + "\n" +
		"{" + "\n" +
			"Nabla2Ir nabla2ir" + "\n" +
			"{" + "\n" +
				"timeVariable = t;" + "\n" +
				"deltatVariable = δt;" + "\n" +
				"nodeCoordVariable = X;" + "\n" +
			"}" + "\n" +
			"ReplaceUtf replaceUtf follows nabla2ir" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
			"ReplaceReductions replaceReductions follows replaceUtf" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
			"OptimizeConnectivities optimizeConnectivities follows replaceReductions" + "\n" +
			"{" + "\n" +
				"connectivities = nodes;" + "\n" +
			"}" + "\n" +
			"FillHLTs fillHlts follows optimizeConnectivities" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
		"}";
	
	public static Value executeModel(String model) {
		return executeModel(model, DEFAULT_GEN_MODEL);
	}
	
	public static Value executeModel(String model, String genModel) {
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL)
				.allowExperimentalOptions(true)
				.option("log.level", "FINE")
				.option("nabla.Properties.genModel", genModel)
				.build();
		return context.eval("nabla", model);
	}
	
	public static Value executeModel(String model, String genModel, Handler handler) {
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL)
				.allowExperimentalOptions(true)
				.logHandler(handler)
				.option("log.level", "FINE")
				.option("nabla.Properties.genModel", genModel)
				.build();
		return context.eval("nabla", model);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getVariableValue(Value result, String variableName, Class<T> type) {
		if (type.equals(double.class)) {
			final Value val = getVariable(result, variableName);
			Assert.assertTrue(val.fitsInDouble());
			return (T) Double.valueOf(val.asDouble());
		} else if (type.equals(double[][].class)) {
			final Value val = getVariable(result, variableName);
			Assert.assertTrue(val.hasArrayElements());
			Assert.assertTrue(val.getArrayElement(0).hasArrayElements());
			final int iSize = (int) val.getArraySize();
			final int jSize = (int) val.getArrayElement(0).getArraySize();
			final double[][] valArray = new double[iSize][jSize];
			for (int i = 0; i < iSize; i++) {
				final Value iValue = val.getArrayElement(i);
				for (int j = 0; j < jSize; j++) {
					Assert.assertTrue(iValue.getArrayElement(j).fitsInDouble());
					valArray[i][j] = iValue.getArrayElement(j).asDouble();
				}
			}
			return (T) valArray;
		} else if (type.equals(double[][][].class)) {
			final Value val = getVariable(result, variableName);
			Assert.assertTrue(val.hasArrayElements());
			Assert.assertTrue(val.getArrayElement(0).hasArrayElements());
			Assert.assertTrue(val.getArrayElement(0).getArrayElement(0).hasArrayElements());
			final int iSize = (int) val.getArraySize();
			final int jSize = (int) val.getArrayElement(0).getArraySize();
			final int kSize = (int) val.getArrayElement(0).getArrayElement(0).getArraySize();
			final double[][][] valArray = new double[iSize][jSize][kSize];
			for (int i = 0; i < iSize; i++) {
				final Value iValue = val.getArrayElement(i);
				for (int j = 0; j < jSize; j++) {
					for (int k = 0; k < kSize; k++) {
						Assert.assertTrue(iValue.getArrayElement(j).getArrayElement(k).fitsInDouble());
						valArray[i][j][k] = iValue.getArrayElement(j).getArrayElement(k).asDouble();
					}
				}
			}
			return (T) valArray;
		}
		throw new IllegalArgumentException("Unsupported type: " + type);
	}

	private static Value getVariable(Value result, String variableName) {
		Assert.assertTrue(result.hasMember(variableName));
		return result.getMember(variableName);
	}

	public static <T> void assertVariableValue(Value result, String variableName, String otherVariableName, Class<T> type) {
		final T val1 = getVariableValue(result, variableName, type);
		final T val2 = getVariableValue(result, variableName, type);
		if (type.equals(double[][].class)) {
			assertVariableValue(result, variableName, (double[][]) val2);
			assertVariableValue(result, otherVariableName, (double[][]) val1);
			return;
		}
		throw new IllegalArgumentException("Unsupported type: " + type);
	}

	public static void assertVariableValue(Value result, String variableName, boolean value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.isBoolean());
		Assert.assertEquals(value, val.asBoolean());
	}

	public static void assertVariableValue(Value result, String variableName, boolean[] value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		final int size = (int) val.getArraySize();
		final boolean[] valArray = new boolean[size];
		for (int i = 0; i < size; i++) {
			Assert.assertTrue(val.getArrayElement(i).isBoolean());
			valArray[i] = val.getArrayElement(i).asBoolean();
		}
		Assert.assertArrayEquals(value, valArray);
	}

	public static void assertVariableValue(Value result, String variableName, boolean[][] value) {
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		Assert.assertTrue(val.getArrayElement(0).hasArrayElements());
		final int iSize = (int) val.getArraySize();
		final int jSize = (int) val.getArrayElement(0).getArraySize();
		final boolean[][] valArray = new boolean[iSize][jSize];
		for (int i = 0; i < iSize; i++) {
			final Value iValue = val.getArrayElement(i);
			for (int j = 0; j < jSize; j++) {
				Assert.assertTrue(iValue.getArrayElement(j).isBoolean());
				valArray[i][j] = iValue.getArrayElement(j).asBoolean();
			}
		}
		for (int i = 0; i < iSize; i++) {
			Assert.assertArrayEquals(value[i], valArray[i]);
		}
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
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		Assert.assertTrue(val.getArrayElement(0).hasArrayElements());
		final int iSize = (int) val.getArraySize();
		final int jSize = (int) val.getArrayElement(0).getArraySize();
		final int[][] valArray = new int[iSize][jSize];
		for (int i = 0; i < iSize; i++) {
			final Value iValue = val.getArrayElement(i);
			for (int j = 0; j < jSize; j++) {
				Assert.assertTrue(iValue.getArrayElement(j).fitsInInt());
				valArray[i][j] = iValue.getArrayElement(j).asInt();
			}
		}
		for (int i = 0; i < iSize; i++) {
			Assert.assertArrayEquals(value[i], valArray[i]);
		}
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
		final Value val = getVariable(result, variableName);
		Assert.assertTrue(val.hasArrayElements());
		Assert.assertTrue(val.getArrayElement(0).hasArrayElements());
		final int iSize = (int) val.getArraySize();
		final int jSize = (int) val.getArrayElement(0).getArraySize();
		final double[][] valArray = new double[iSize][jSize];
		for (int i = 0; i < iSize; i++) {
			final Value iValue = val.getArrayElement(i);
			for (int j = 0; j < jSize; j++) {
				Assert.assertTrue(iValue.getArrayElement(j).fitsInDouble());
				valArray[i][j] = iValue.getArrayElement(j).asDouble();
			}
		}
		for (int i = 0; i < iSize; i++) {
			Assert.assertArrayEquals(value[i], valArray[i], DOUBLE_TOLERANCE);
		}
	}
}
