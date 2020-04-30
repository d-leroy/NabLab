package fr.cea.nabla.truffle.tests

import fr.cea.nabla.tests.NablaInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*
import fr.cea.nabla.tests.interpreter.AbstractExpressionInterpreterTest

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class ExpressionInterpreterTest extends AbstractExpressionInterpreterTest {

	override assertInterpreteContractedIf(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "r1", 1.0)
		assertVariableValue(result, "r2", 2.0)
	}
	
	override assertInterpreteUnaryExpression(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "b0", true)
		assertVariableValue(result, "n0", -1)
		assertVariableValue(result, "r0", -1.0)
		val int[] n1Expected = #[1, 2]
		assertVariableValue(result, "n1", n1Expected)
		val int[] n2Expected = #[-1, -2]
		assertVariableValue(result, "n2", n2Expected)
		assertVariableValue(result, "r1", #[1.0, 2.0])
		assertVariableValue(result, "r2", #[-1.0, -2.0])
		val int[][] n3Expected = #[#[0, 1], #[1, 2]]
		assertVariableValue(result, "n3", n3Expected)
		val int[][] n4Expected = #[#[0, -1], #[-1, -2]]
		assertVariableValue(result, "n4", n4Expected)
		assertVariableValue(result, "r3", #[#[0.0, 1.0], #[1.0, 2.0]])
		// Warning : -0.0 != 0.0
		assertVariableValue(result, "r4", #[#[-0.0, -1.0], #[-1.0, -2.0]])
	}

	override assertInterpreteParenthesis(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "b", true)
	}
	
	override assertInterpreteCardinality(String model) {
		val result = executeModel(model)
		
		assertVariableValue(result, "c", 121)
	}

	override assertInterpreteConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "n1", 1)
		assertVariableValue(result, "r1", 2.0)
		assertVariableValue(result, "b1", true)
		assertVariableValue(result, "b2", false)
	}

	override assertInterpreteMinConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "nMin", Integer.MIN_VALUE)
		assertVariableValue(result, "rMin", Double.MIN_VALUE)
	}

	override assertInterpreteMaxConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "nMax", Integer.MAX_VALUE)
		assertVariableValue(result, "rMax", Double.MAX_VALUE)
	}

	override assertInterpreteBaseTypeConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "n1", 1)
		val int[] n2Expected = #[1, 1]
		assertVariableValue(result, "n2", n2Expected)
		val int[][] n3Expected = #[#[1, 1, 1], #[1, 1, 1]]
		assertVariableValue(result, "n3", n3Expected)

		assertVariableValue(result, "r1", 1.0)
		assertVariableValue(result, "r2", #[1.0, 1.0])
		assertVariableValue(result, "r3", #[#[1.0, 1.0, 1.0], #[1.0, 1.0, 1.0]])

		assertVariableValue(result, "b1", true)
		assertVariableValue(result, "b2", #[true, true])
		assertVariableValue(result, "b3", #[#[true, true, true], #[true, true, true]])
	}

	override assertInterpreteIntVectorConstant(String model) {
		val result = executeModel(model)
		val int[] expected = #[1, 2]
		assertVariableValue(result, "n", expected)
	}

	override assertInterpreteIntMatrixConstant(String model) {
		val result = executeModel(model)
		val int[][] expected = #[#[0, 1, 2], #[1, 2, 3]]
		assertVariableValue(result, "n", expected)
	}

	override assertInterpreteRealVectorConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "r", #[1.0, 2.0])
	}

	override assertInterpreteRealMatrixConstant(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "r", #[#[0.0, 1.0, 2.0], #[1.0, 2.0, 3.0]])
	}

	override assertInterpreteFunctionCall(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "n0", 0)
		assertVariableValue(result, "n1", 1)
		assertVariableValue(result, "n2", 2)
		assertVariableValue(result, "n3", 3)

		assertVariableValue(result, "r1", 1.0)
		assertVariableValue(result, "r2", 2.0)
		assertVariableValue(result, "r3", 3.0)

		assertVariableValue(result, "w", #[3.0, 3.0])
		assertVariableValue(result, "res1", #[3.0, 3.0, 3.0])
		assertVariableValue(result, "res2", #[#[3.0, 3.0], #[3.0, 3.0]])
	}

	override assertInterpreteFunctionCallWithBody(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "w1", #[0.0, 0.2])
		assertVariableValue(result, "w2", #[0.0, 0.2])
		assertVariableValue(result, "w3", #[0.0, 0.2, 0.4])
		assertVariableValue(result, "w4", #[0.0, 0.2])
		assertVariableValue(result, "w5", #[0.0, 0.2, 0.4])
		assertVariableValue(result, "w6", #[0.0, 0.2])
	}

	override assertInterpreteVarRef(String model) {
		val result = executeModel(model)

		val int[] n4Expected = #[2, 3]
		val int[][] n7Expected = #[#[2, 3], #[4, 5], #[6, 7]]
		
		assertVariableValue(result, "b2", true)

		assertVariableValue(result, "n2", 1)
		assertVariableValue(result, "n4", n4Expected)
		assertVariableValue(result, "n7", n7Expected)
		assertVariableValue(result, "n8", 5)

		assertVariableValue(result, "r2", 1.0)
		assertVariableValue(result, "r4", #[2.0,3.0])
		assertVariableValue(result, "r7", #[#[2.0,3.0],#[4.0,5.0],#[6.0,7.0]])
		assertVariableValue(result, "r8", 5.0)
	}
}
