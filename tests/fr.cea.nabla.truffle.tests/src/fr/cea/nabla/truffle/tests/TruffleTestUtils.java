package fr.cea.nabla.truffle.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;
import org.junit.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

import fr.cea.nabla.ir.ir.Instruction;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.ir.PostProcessingInfo;
import fr.cea.nabla.ir.ir.SimpleVariable;
import fr.cea.nabla.ir.ir.TimeLoop;
import fr.cea.nabla.tests.CompilationChainHelper;
import fr.cea.nabla.tests.NablaInjectorProvider;
import fr.cea.nabla.tests.TestUtils;

public class TruffleTestUtils {

	public static final double DOUBLE_TOLERANCE = 1e-15;

	private static final TestUtils TEST_UTILS = new TestUtils();

	private static final String DEFAULT_GEN_MODEL = TEST_UTILS.getTestGenModel().toString();

	public static Value executeModel(String model) throws IOException {
		return executeModel(model, DEFAULT_GEN_MODEL);
	}

	public static Value executeModelWithOptions(String model, String jsonOptions) throws IOException {
		return executeModel(model, DEFAULT_GEN_MODEL, jsonOptions);
	}

	public static Value executeModel(String model, Handler handler) {
		return executeModel(model, DEFAULT_GEN_MODEL, handler);
	}

	public static Value executeModel(String model, String genModel) throws IOException {
		final IrModule module = getIrModule(model, genModel);
		final String irString = getIrString(module);
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).allowAllAccess(true)
				.allowExperimentalOptions(true).option("nabla.model", genModel).build();
		return context.eval("nabla", irString);
	}

	public static Value executeModel(String model, String genModel, String jsonOptions) throws IOException {
//		final IrModule irModule = getIrModule(model, genModel);
//		final Object test = getInstruction("∀c∈cells(), {\n" + 
//				"	xc{c} = Xc{c}[0]; \n" + 
//				"	yc{c} = Xc{c}[1];\n" + 
//				"}", genModel);
//		System.out.println(test);
//		final Map<String, List<String>> info = processPostProcessingInfo(irModule);
//		final String irString = getIrString(irModule);
		
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).allowAllAccess(true)
				.allowExperimentalOptions(true) //
				.option("nabla.genmodel", genModel) //
				.option("nabla.options", jsonOptions) //
				.option("nabla-logger", "true") //
				.option("nabla-logger.format", "true") //
				.option("nabla-logger.jobs", "UpdateU") //
				.option("nabla-logger.variables", "n, t_nplus1, u_nplus1") //
				.option("nabla-logger.range", "3") //
				.option("nabla-logger.toPrint", "At t={1}, U={2}\n\tn={0}") //
				.build();
//		final Debugger debugger = Debugger.find(context.getEngine());
//		final DebuggerSession session = debugger.startSession(e -> {
//			System.out.println("executing: " + e.getTopStackFrame().getName());
//			e.prepareStepInto(1);
//		}, SourceElement.ROOT);
//		session.suspendNextExecution();
		
		final double t1 = System.nanoTime();
		final Value result = context.eval("nabla", model);
		final double t2 = (System.nanoTime() - t1) * 0.000001;
		System.out.println("GraalVM Execution Finished (execution time: " + t2 + "ms)");
		return result;
	}

	public static Value executeModel(String model, String genModel, Handler handler) {
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).allowAllAccess(true)
				.allowExperimentalOptions(true).logHandler(handler)
				.option("log.nabla.fr.cea.nabla.ir.truffle.nodes.job.NablaTimeLoopJobNode.level", "FINE")
				.option("nabla.model", genModel).build();
		return context.eval("nabla", model);
	}

	public static Value executeModel(String model, String genModel, String jsonOptions, Handler handler) {
		final Context context = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).allowAllAccess(true)
				.allowExperimentalOptions(true).logHandler(handler)
				.option("log.nabla.fr.cea.nabla.ir.truffle.nodes.job.NablaTimeLoopJobNode.level", "FINE")
				.option("nabla.model", genModel).option("nabla.options", jsonOptions).build();
		return context.eval("nabla", model);
	}

	private static String getIrString(IrModule irModule) throws IOException {
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Resource r = new XMLResourceImpl();
		r.getContents().add(irModule);
		r.save(outStream, Collections.emptyMap());
		return outStream.toString();
	}

	private static IrModule getIrModule(String model, String genModel) {
		NablaInjectorProvider inj = new NablaInjectorProvider();
		CompilationChainHelper helper = new CompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		return helper.getIrModuleForInterpretation(model, genModel);
	}
	
	private static Instruction getInstruction(String model, String genModel) {
		NablaREPLInjectorProvider inj = new NablaREPLInjectorProvider();
		REPLCompilationChainHelper helper = new REPLCompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		final Instruction i = helper.getIrInstruction(model, genModel);
		return i;
	}

	private static Map<String, List<String>> processPostProcessingInfo(IrModule irModule) {
		final Map<String, List<String>> result = new HashMap<>();
		final PostProcessingInfo postProcessingInfo = irModule.getPostProcessingInfo();
		if (postProcessingInfo != null) {
			System.out.println("Post Processing Info != null");
			final SimpleVariable periodVariable = postProcessingInfo.getPeriodReference();
			System.out.println("Period Variable = " + periodVariable);
			Optional<String> loopJobName = Streams.stream(irModule.eAllContents()).filter(o -> o instanceof TimeLoop)
					.map(o -> (TimeLoop) o).filter(l -> l.getIterationCounter() == periodVariable)
					.map(l -> l.getAssociatedJob().getName()).findFirst();
			if (loopJobName.isPresent()) {
				System.out.println("Outermost Loop: " + loopJobName);
				result.put("nabla-logger.jobs", Lists.newArrayList(loopJobName.get()));
			}
			final List<String> outputVariables = postProcessingInfo.getOutputVariables().stream().map(v -> v.getName())
					.collect(Collectors.toList());
			result.put("nabla-logger.variables", outputVariables);
//			final List<String> cellVariables = new ArrayList<>();
//			final List<String> nodeVariables = new ArrayList<>();
//			postProcessingInfo.getOutputVariables().stream().filter(v -> v instanceof ConnectivityVariable)
//					.map(v -> (ConnectivityVariable) v).forEach(v -> {
//						final String name = v.getType().getConnectivities().get(0).getReturnType().getName();
//						switch (name) {
//						case "cell":
//							cellVariables.add(v.getName());
//						case "node":
//							nodeVariables.add(v.getName());
//						}
//					});
//			final String nodeCoordinatesVariable = irModule.getNodeCoordVariable().getName();
//			final SimpleVariable period = postProcessingInfo.getPeriodValue();

//			TODO
//			instrument.configure(irModule.getName(), iterationVariable, timeVariable, periodVariable,
//					cellVariables.toArray(new String[0]), nodeVariables.toArray(new String[0]),
//					nodeCoordinatesVariable, period);
		}
		return result;
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

	public static <T> void assertVariableValue(Value result, String variableName, String otherVariableName,
			Class<T> type) {
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
