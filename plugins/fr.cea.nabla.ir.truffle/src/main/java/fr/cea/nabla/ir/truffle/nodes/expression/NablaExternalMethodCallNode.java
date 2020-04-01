package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.instrumentation.Tag;
import com.oracle.truffle.api.interop.InteropException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalMethodCallNodeFactory.InvokeMethodNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalMethodCallNodeFactory.UnboxResultNodeGen;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.runtime.NablaInternalError;
import fr.cea.nabla.ir.truffle.values.FunctionCallHelper;
import fr.cea.nabla.ir.truffle.values.NablaValue;

public class NablaExternalMethodCallNode extends NablaExpressionNode {

	@Children
	private NablaExpressionNode[] argumentNodes;
	@Child
	private InvokeMethod invokeNode;
	@Child
	private UnboxResult unboxNode;
	private final String methodName;
	private final Object receiverObject;

	public NablaExternalMethodCallNode(String receiverClass, String methodName, Class<?> returnType,
			NablaExpressionNode[] argumentNodes) {
		this.argumentNodes = argumentNodes;
		this.methodName = methodName;
		this.receiverObject = NablaContext.getCurrent().getEnv().lookupHostSymbol(receiverClass);
		this.invokeNode = InvokeMethodNodeGen.create();
		this.unboxNode = UnboxResultNodeGen.create(returnType);
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		final Object[] argumentValues = new Object[argumentNodes.length];
		for (int i = 0; i < argumentNodes.length; i++) {
			argumentValues[i] = FunctionCallHelper.getJavaValue((NablaValue) argumentNodes[i].executeGeneric(frame));
		}

		final Object result = invokeNode.execute(receiverObject, methodName, argumentValues);
		final Object unboxedResult = unboxNode.execute(result);
		final NablaValue nablaResult = FunctionCallHelper.createNablaValue(unboxedResult); 
		return nablaResult;
	}

	@Override
	public boolean hasTag(Class<? extends Tag> tag) {
		if (tag == StandardTags.CallTag.class) {
			return true;
		}
		return super.hasTag(tag);
	}

	abstract static class InvokeMethod extends Node {
		abstract Object execute(Object obj, String methodName, Object[] arguments);

		@Specialization(guards = "objLibrary.hasMembers(obj)", limit = "4")
		Object doDefault(Object obj, String methodName, Object[] arguments,
				@CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				return objLibrary.invokeMember(obj, methodName, arguments);
			} catch (InteropException e) {
				// TODO translate errors to your language errors
			}
			return null;
		}
	}

	abstract static class UnboxResult extends Node {

		protected final Class<?> type;

		protected UnboxResult(Class<?> type) {
			this.type = type;
		}

		abstract Object execute(Object obj);
		
		@Specialization(guards = "isBool()", limit = "3")
		Object doBool(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				return objLibrary.asBoolean(obj);
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}
		
		@Specialization(guards = "isBoolArray1()", limit = "3")
		Object doBoolArray1(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final boolean[] valArray = new boolean[iSize];
				for (int i = 0; i < iSize; i++) {
					valArray[i] = (boolean) objLibrary.readArrayElement(obj, i);
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isBoolArray2()", limit = "3")
		Object doBoolArray2(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final boolean[][] valArray = new boolean[iSize][jSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						valArray[i][j] = (boolean) objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j);
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isBoolArray3()", limit = "3")
		Object doBool3(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final boolean[][][] valArray = new boolean[iSize][jSize][kSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							valArray[i][j][k] = (boolean) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k);
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isBoolArray4()", limit = "3")
		Object doBool4(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final int lSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0), 0));
				final boolean[][][][] valArray = new boolean[iSize][jSize][kSize][lSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							for (int l = 0; l < lSize; l++) {
								valArray[i][j][k][l] = (boolean) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k), l);
							}
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}
		
		@Specialization(guards = "isInt()", limit = "3")
		Object doInt(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				return objLibrary.asInt(obj);
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isIntArray1()", limit = "3")
		Object doIntArray1(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int[] valArray = new int[iSize];
				for (int i = 0; i < iSize; i++) {
					valArray[i] = (int) objLibrary.readArrayElement(obj, i);
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isIntArray2()", limit = "3")
		Object doIntArray2(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int[][] valArray = new int[iSize][jSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						valArray[i][j] = (int) objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j);
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isIntArray3()", limit = "3")
		Object doInt3(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final int[][][] valArray = new int[iSize][jSize][kSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							valArray[i][j][k] = (int) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k);
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isIntArray4()", limit = "3")
		Object doInt4(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final int lSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0), 0));
				final int[][][][] valArray = new int[iSize][jSize][kSize][lSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							for (int l = 0; l < lSize; l++) {
								valArray[i][j][k][l] = (int) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k), l);
							}
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}
		
		@Specialization(guards = "isDouble()", limit = "3")
		Object doDouble(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				return objLibrary.asDouble(obj);
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isDoubleArray1()", limit = "3")
		Object doDoubleArray1(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final double[] valArray = new double[iSize];
				for (int i = 0; i < iSize; i++) {
					valArray[i] = (double) objLibrary.readArrayElement(obj, i);
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isDoubleArray2()", limit = "3")
		Object doDoubleArray2(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final double[][] valArray = new double[iSize][jSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						valArray[i][j] = (double) objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j);
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isDoubleArray3()", limit = "3")
		Object doDouble3(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final double[][][] valArray = new double[iSize][jSize][kSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							valArray[i][j][k] = (double) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k);
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		@Specialization(guards = "isDoubleArray4()", limit = "3")
		Object doDouble4(Object obj, @CachedLibrary("obj") InteropLibrary objLibrary) {
			try {
				final int iSize = (int) objLibrary.getArraySize(obj);
				final int jSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(obj, 0));
				final int kSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0));
				final int lSize = (int) objLibrary.getArraySize(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, 0), 0), 0));
				final double[][][][] valArray = new double[iSize][jSize][kSize][lSize];
				for (int i = 0; i < iSize; i++) {
					for (int j = 0; j < jSize; j++) {
						for (int k = 0; k < kSize; k++) {
							for (int l = 0; l < lSize; l++) {
								valArray[i][j][k][l] = (double) objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(objLibrary.readArrayElement(obj, i), j), k), l);
							}
						}
					}
				}
				return valArray;
			} catch (InteropException e) {
			}
			throw NablaInternalError.shouldNotReachHere();
		}

		protected boolean isBool() {
			return type.equals(boolean.class);
		}

		protected boolean isBoolArray1() {
			return type.equals(boolean[].class);
		}

		protected boolean isBoolArray2() {
			return type.equals(boolean[][].class);
		}

		protected boolean isBoolArray3() {
			return type.equals(boolean[][][].class);
		}

		protected boolean isBoolArray4() {
			return type.equals(boolean[][][][].class);
		}

		protected boolean isInt() {
			return type.equals(int.class);
		}

		protected boolean isIntArray1() {
			return type.equals(int[].class);
		}

		protected boolean isIntArray2() {
			return type.equals(int[][].class);
		}

		protected boolean isIntArray3() {
			return type.equals(int[][][].class);
		}

		protected boolean isIntArray4() {
			return type.equals(int[][][][].class);
		}

		protected boolean isDouble() {
			return type.equals(double.class);
		}

		protected boolean isDoubleArray1() {
			return type.equals(double[].class);
		}

		protected boolean isDoubleArray2() {
			return type.equals(double[][].class);
		}

		protected boolean isDoubleArray3() {
			return type.equals(double[][][].class);
		}

		protected boolean isDoubleArray4() {
			return type.equals(double[][][][].class);
		}
	}
}
