package fr.cea.nabla.ir.truffle.utils

import java.io.File
import java.io.FileWriter

class BinaryExpressionGenerator {

	def static void main(String[] args) {
		val generator = new BinaryExpressionGenerator
		val operators = #{
				'or' -> '||',
				'and' -> '&&',
				'eq' -> '==',
				'neq' -> '!=',
				'geq' -> '>=',
				'leq' -> '<=',
				'gt' -> '>',
				'lt' -> '<',
				'add' -> '+',
				'sub' -> '-',
				'mul' -> '*',
				'div' -> '/',
				'mod' -> '%'}
		operators.forEach[methodName, op|
			val path = System.getProperty("user.dir") + "/src/fr/cea/nabla/ir/truffle/nodes/expression/binary/" +
					generator.getClassName(methodName) + ".java"
			val file = new File(path)
			if (file.exists) {
				file.delete
			}
			file.createNewFile
			val writer = new FileWriter(file);
			writer.write(generator.generateClass(methodName, op));
			writer.close();
		]
	}

	var boolean importBooleans = false

	def String getClassName(String methodName) {
		return '''Nabla«methodName.toFirstUpper»Node'''
	}

	def String generateNumericalOperationCode(String left, String op, String right) {
		switch (op) {
			case '+': '''Math.addExact(«left», «right»)'''
			case '-': '''Math.subtractExact(«left», «right»)'''
			case '*': '''Math.multiplyExact(«left», «right»)'''
			default: '''«left» «op» «right»'''
		}
	}

	def String generateBooleanOperationCode(String left, String op, String right) {
		switch (op) {
			case '<',
			case '>',
			case '>=',
			case '<=': {
				importBooleans = true
				'''Booleans.compare(«left», «right») «op» 0'''
			}
			default: '''«left» «op» «right»'''
		}
	}

	def boolean isCommutative(String op) {
		switch (op) {
			case '+',
			case '*': return true
			default: return false
		}
	}
	
	def boolean isVector(String op) {
		switch (op) {
			case '+',
			case '-',
			case '*',
			case '/': return true
			default: return false
		}
	}
	
	def boolean isBool(String op) {
		switch (op) {
			case '+',
			case '-',
			case '*',
			case '/',
			case '%': return false
			default: return true
		}
	}
	
	def boolean isInteger(String op) {
		switch (op) {
			case '||',
			case '&&': return false
			default: return true
		}
	}
	
	def boolean isReal(String op) {
		switch (op) {
			case '||',
			case '&&',
			case '%': return false
			default: return true
		}
	}

	def String generateClass(String methodName, String op) {
		importBooleans = false
		val classBody = generateClassBody(methodName, op)
		val imports = generateImports(op)
		
		return '''
			package fr.cea.nabla.ir.truffle.nodes.expression.binary;
			
			«imports»
			
			«classBody»
		'''
	}
	
	def String generateImports(String op) {
		return '''
			«IF importBooleans»
			import com.google.common.primitives.Booleans;
			«ENDIF»
			«IF op.vector»
			import com.oracle.truffle.api.CompilerAsserts;
			«ENDIF»
			import com.oracle.truffle.api.dsl.Specialization;
			«IF op.vector»
			import com.oracle.truffle.api.nodes.ExplodeLoop;
			«ENDIF»
			import com.oracle.truffle.api.nodes.NodeInfo;
			
			«IF op.bool»
			import fr.cea.nabla.ir.truffle.values.NV0Bool;
			«ENDIF»
			«IF op.integer»
			import fr.cea.nabla.ir.truffle.values.NV0Int;
			«ENDIF»
			«IF op.real»
			import fr.cea.nabla.ir.truffle.values.NV0Real;
			«ENDIF»
			«IF op.vector»
			«IF op.integer»
			import fr.cea.nabla.ir.truffle.values.NV1Int;
			«ENDIF»
			«IF op.real»
			import fr.cea.nabla.ir.truffle.values.NV1Real;
			«ENDIF»
			«IF op.integer»
			import fr.cea.nabla.ir.truffle.values.NV2Int;
			«ENDIF»
			«IF op.real»
			import fr.cea.nabla.ir.truffle.values.NV2Real;
			«ENDIF»
			«ENDIF»
		'''
	}

	def String generateClassBody(String methodName, String op) {
		return '''
			@NodeInfo(shortName = "«op»")
			public abstract class «methodName.className» extends NablaBinaryExpressionNode {
				
				«IF op.bool»
				@Specialization
				protected NV0Bool add(NV0Bool left, NV0Bool right) {
					return new NV0Bool(«generateBooleanOperationCode("left.isData()", op, "right.isData()")»);
				}
				«ENDIF»««« bool
				
				
				«IF op.integer»
				@Specialization
				protected «IF op.bool»NV0Bool«ELSE»NV0Int«ENDIF» «methodName»(NV0Int left, NV0Int right) {
					return new «IF op.bool»NV0Bool«ELSE»NV0Int«ENDIF»(«generateNumericalOperationCode("left.getData()", op, "right.getData()")»);
				}
				
				«IF op.real»
				@Specialization
				protected «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF» «methodName»(NV0Int left, NV0Real right) {
					return new «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF»(left.getData() «op» right.getData());
				}
				«ENDIF»««« real
				«ENDIF»««« integer
				
				
				«IF op.vector»
				«IF op.commutative»
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV1Int «methodName»(NV0Int left, NV1Int right) {
					final int leftData = left.getData();
					final int[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					
					final int[] result = new int[rightData.length];
					
					for (int i=0; i < rightData.length; i++) {
						result[i] = «generateNumericalOperationCode("leftData", op, "rightData[i]")»;
					}
					
					return new NV1Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV0Int left, NV1Real right) {
					final int leftData = left.getData();
					final double[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					
					final double[] result = new double[rightData.length];
					
					for (int i=0; i < rightData.length; i++) {
						result[i] = leftData «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« real
				
				
				@ExplodeLoop
				@Specialization
				protected NV2Int «methodName»(NV0Int left, NV2Int right) {
					final int leftData = left.getData();
					final int[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					CompilerAsserts.compilationConstant(rightData[0].length);
					
					final int[][] result = new int[rightData.length][rightData[0].length];
					
					for (int i=0; i < rightData.length; i++) {
						for (int j=0; j < rightData[0].length; j++) {
							result[i][j] = «generateNumericalOperationCode("leftData", op, "rightData[i][j]")»;
						}
					}
					
					return new NV2Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV0Int left, NV2Real right) {
					final int leftData = left.getData();
					final double[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					CompilerAsserts.compilationConstant(rightData[0].length);
					
					final double[][] result = new double[rightData.length][rightData[0].length];
					
					for (int i=0; i < rightData.length; i++) {
						for (int j=0; j < rightData[0].length; j++) {
							result[i][j] = leftData «op» rightData[i][j];
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« real
				«ENDIF»««« integer
				«ENDIF»««« commutative
				«ENDIF»««« vector
				
				
				«IF op.real»
				«IF op.integer»
				@Specialization
				protected «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF» «methodName»(NV0Real left, NV0Int right) {
					return new «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF»(left.getData() «op» right.getData());
				}
				«ENDIF»««« integer
				
				
				@Specialization
				protected «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF» «methodName»(NV0Real left, NV0Real right) {
					return new «IF op.bool»NV0Bool«ELSE»NV0Real«ENDIF»(left.getData() «op» right.getData());
				}
				«ENDIF»««« real
				
				
				«IF op.vector»
				«IF op.commutative»
				«IF op.real»
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV0Real left, NV1Int right) {
					final double leftData = left.getData();
					final int[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					
					final double[] result = new double[rightData.length];
					
					for (int i=0; i < rightData.length; i++) {
						result[i] = leftData «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« integer
				
				
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV0Real left, NV1Real right) {
					final double leftData = left.getData();
					final double[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					
					final double[] result = new double[rightData.length];
					
					for (int i=0; i < rightData.length; i++) {
						result[i] = leftData «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV0Real left, NV2Int right) {
					final double leftData = left.getData();
					final int[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					CompilerAsserts.compilationConstant(rightData[0].length);
					
					final double[][] result = new double[rightData.length][rightData[0].length];
					
					for (int i=0; i < rightData.length; i++) {
						for (int j=0; j < rightData[0].length; j++) {
							result[i][j] = leftData «op» rightData[i][j];
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« integer
				
				
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV0Real left, NV2Real right) {
					final double leftData = left.getData();
					final double[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(rightData.length);
					CompilerAsserts.compilationConstant(rightData[0].length);
					
					final double[][] result = new double[rightData.length][rightData[0].length];
					
					for (int i=0; i < rightData.length; i++) {
						for (int j=0; j < rightData[0].length; j++) {
							result[i][j] = leftData «op» rightData[i][j];
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« real
				«ENDIF»««« commutative
				
				
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV1Int «methodName»(NV1Int left, NV0Int right) {
					final int[] leftData = left.getData();
					final int rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final int[] result = new int[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = «generateNumericalOperationCode("leftData[i]", op, "rightData")»;
					}
					
					return new NV1Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Int left, NV0Real right) {
					final int[] leftData = left.getData();
					final double rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData;
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« real
				
				
				@ExplodeLoop
				@Specialization
				protected NV1Int «methodName»(NV1Int left, NV1Int right) {
					final int[] leftData = left.getData();
					final int[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final int[] result = new int[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = «generateNumericalOperationCode("leftData[i]", op, "rightData[i]")»;
					}
					
					return new NV1Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Int left, NV1Real right) {
					final int[] leftData = left.getData();
					final double[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« real
				«ENDIF»««« integer
				
				
				«IF op.real»
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Real left, NV0Int right) {
					final double[] leftData = left.getData();
					final int rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData;
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« integer
				
				
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Real left, NV0Real right) {
					final double[] leftData = left.getData();
					final double rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData;
					}
					
					return new NV1Real(result);
				}
				
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Real left, NV1Int right) {
					final double[] leftData = left.getData();
					final int[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« integer
				
				
				@ExplodeLoop
				@Specialization
				protected NV1Real «methodName»(NV1Real left, NV1Real right) {
					final double[] leftData = left.getData();
					final double[] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					
					final double[] result = new double[leftData.length];
					
					for (int i=0; i < leftData.length; i++) {
						result[i] = leftData[i] «op» rightData[i];
					}
					
					return new NV1Real(result);
				}
				«ENDIF»««« real
				
				
				«IF op.integer»
				@ExplodeLoop
				@Specialization
				protected NV2Int «methodName»(NV2Int left, NV0Int right) {
					final int[][] leftData = left.getData();
					final int rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final int[][] result = new int[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = «generateNumericalOperationCode("leftData[i][j]", op, "rightData")»;
						}
					}
					
					return new NV2Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV2Int left, NV0Real right) {
					final int[][] leftData = left.getData();
					final double rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final double[][] result = new double[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = leftData[i][j] «op» rightData;
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« real
				
				
				@ExplodeLoop
				@Specialization
				protected NV2Int «methodName»(NV2Int left, NV2Int right) {
					final int[][] leftData = left.getData();
					final int[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final int[][] result = new int[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = «generateNumericalOperationCode("leftData[i][j]", op, "rightData[i][j]")»;
						}
					}
					
					return new NV2Int(result);
				}
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV2Real left, NV0Int right) {
					final double[][] leftData = left.getData();
					final int rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final double[][] result = new double[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = leftData[i][j] «op» rightData;
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« real
				«ENDIF»««« integer
				
				
				«IF op.real»
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV2Real left, NV0Real right) {
					final double[][] leftData = left.getData();
					final double rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final double[][] result = new double[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = leftData[i][j] «op» rightData;
						}
					}
					
					return new NV2Real(result);
				}
				
				@ExplodeLoop
				@Specialization
				protected NV2Real «methodName»(NV2Real left, NV2Real right) {
					final double[][] leftData = left.getData();
					final double[][] rightData = right.getData();
					
					CompilerAsserts.compilationConstant(leftData.length);
					CompilerAsserts.compilationConstant(leftData[0].length);
					
					final double[][] result = new double[leftData.length][leftData[0].length];
					
					for (int i=0; i < leftData.length; i++) {
						for (int j=0; j < leftData[0].length; j++) {
							result[i][j] = leftData[i][j] «op» rightData[i][j];
						}
					}
					
					return new NV2Real(result);
				}
				«ENDIF»««« real
				«ENDIF»««« vector
				
			}
		'''
	}
}
