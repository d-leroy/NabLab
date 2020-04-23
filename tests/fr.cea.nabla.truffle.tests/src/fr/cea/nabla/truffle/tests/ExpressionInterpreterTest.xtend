package fr.cea.nabla.truffle.tests

import com.google.inject.Inject
import fr.cea.nabla.tests.NablaInjectorProvider
import fr.cea.nabla.tests.TestUtils
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class ExpressionInterpreterTest {

	@Inject extension TestUtils

	@Test
	def void testInterpreteContractedIf() {
		val model = testModuleForSimulation +
		'''	
		let r1 = true ? 1.0 : 2.0; // -> 1.0
		let r2 = false ? 1.0 : 2.0; // -> 1.0
		'''

		val result = executeModel(model)

		assertVariableValue(result, "r1", 1.0)
		assertVariableValue(result, "r2", 2.0)
	}

	@Test
	def void testInterpreteUnaryExpression() {
		val model = testModuleForSimulation +
		'''
		let b0 = !false; // -> true
		let n0 = -(1); // -> -1
		let r0 = -(1.); // -> -1.
		let n1 = [1, 2];
		let n2 = -n1; // -> [-1, -2]
		let r1 = [1., 2.];
		let r2 = -r1; // -> [-1., -2
		let n3 = [[0, 1],[1, 2]];
		let n4 = -n3; // -> [[0, -1],[-1, -2]]
		let r3 = [[0., 1.],[1., 2.]];
		let r4 = -r3; // -> [[-0., -1.],[-1., -2.]]*/
		'''
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

	@Test
	def void testInterpreteParenthesis() {
		val model = testModuleForSimulation +
		'''
		let b = (true);
		let n1 = 2 * (3 + 1);
		let n2 = 2 * 3 + 1;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "b", true)
		assertVariableValue(result, "n1", 8)
		assertVariableValue(result, "n2", 7)
	}
	
	@Test
	def void testInterpreteCardinality()
	{
		val model = testModuleForSimulation +
		'''
		let c = card(nodes());
		'''
		
		val result = executeModel(model)
		
		assertVariableValue(result, "c", 121)
	}

	@Test
	def void testInterpreteConstant() {
		// NB : Constant only for Scalar
		val model = testModuleForSimulation +
		'''
		let n1 = 1;
		let r1 = 2.0;
		let b1 = true;
		let b2 = false;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "n1", 1)
		assertVariableValue(result, "r1", 2.0)
		assertVariableValue(result, "b1", true)
		assertVariableValue(result, "b2", false)
	}

	@Test
	def void testInterpreteMinConstant() {
		val model = testModuleForSimulation +
		'''
		let nMin = ℕ.MinValue;
		let rMin = ℝ.MinValue;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "nMin", Integer.MIN_VALUE)
		assertVariableValue(result, "rMin", Double.MIN_VALUE)
	}

	@Test
	def void testInterpreteMaxConstant() {
		val model = testModuleForSimulation +
		'''
		let nMax = ℕ.MaxValue;
		let rMax = ℝ.MaxValue;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "nMax", Integer.MAX_VALUE)
		assertVariableValue(result, "rMax", Double.MAX_VALUE)
	}

	@Test
	def void testInterpreteBaseTypeConstant() {
		val model = testModuleForSimulation + '''
			let n1 = ℕ(1);
			let n2 = ℕ[2](1);
			let n3 = ℕ[2,3](1);
			
			let r1 = ℝ(1.);
			let r2 = ℝ[2](1.);
			let r3 = ℝ[2,3](1.);
			
			let b1 = ℾ(true);
			let b2 = ℾ[2](true);
			let b3 = ℾ[2,3](true);
		'''

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

	@Test
	def void testInterpreteIntVectorConstant() {
		val model = testModuleForSimulation +
		'''
		let n = [1, 2];
		'''

		val result = executeModel(model)
		val int[] expected = #[1, 2]
		assertVariableValue(result, "n", expected)
	}

	@Test
	def void testInterpreteIntMatrixConstant() {
		val model = testModuleForSimulation +
		'''
		let n = [[0, 1, 2],[1, 2, 3]];
		'''

		val result = executeModel(model)
		val int[][] expected = #[#[0, 1, 2], #[1, 2, 3]]
		assertVariableValue(result, "n", expected)
	}

	@Test
	def void testInterpreteRealVectorConstant() {
		val model = testModuleForSimulation +
		'''
		let r = [1.0, 2.0];
		'''

		val result = executeModel(model)

		assertVariableValue(result, "r", #[1.0, 2.0])
	}

	@Test
	def void testInterpreteRealMatrixConstant() {
		val model = testModuleForSimulation +
		'''
		let r = [[0., 1., 2.],[1., 2., 3.]];
		'''

		val result = executeModel(model)

		assertVariableValue(result, "r", #[#[0.0, 1.0, 2.0], #[1.0, 2.0, 3.0]])
	}

	@Test
	def void testInterpreteFunctionCall() {
		val model = getTestModule(defaultConnectivities,
		'''
		def getOne:  → ℕ;
		def addOne: ℕ → ℕ;
		def addOne: ℝ → ℝ;
		def add: ℕ × ℕ → ℕ;
		def add: ℝ × ℕ → ℝ;
		def add: ℝ × ℝ → ℝ;
		def add: x | ℝ[x] × ℝ[x] → ℝ[x];
		def add: x,y | ℝ[x,y] × ℝ[x,y] → ℝ[x,y];
		''')
		+ simulationVariables +
		'''
		let n0 = 0;
		let n1 = getOne(); 	//-> 1
		let n2 = addOne(n1); 	//-> 2
		let n3 = add(n1, n2); //-> 3
		let r0 = 0. ;
		let r1 = addOne(r0); 	//-> 1.
		let r2 = add(r1, n1); //-> 2.
		let r3 = add(r2, r1); //-> 3.

		let u = ℝ[2](1.);
		let v = ℝ[2](2.);
		let w = add(u,v); //-> [3., 3.]

		let α = ℝ[3](1.);
		let β = ℝ[3](2.);
		let res1 = add(α,β); //-> [3., 3., 3.]

		let δ = ℝ[2,2](1.);
		let ρ = ℝ[2,2](2.);
		let res2 = add(δ,ρ); //-> [3., 3][3., 3.]
		'''

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

	@Test
	def void testInterpreteFunctionCallWithBody() {
		val model = getTestModule(defaultConnectivities,
		'''
		def h: ℝ[2] → ℝ[2], (a) → return 2 * a;

		def i: a | ℝ[a] → ℝ[a], (x) → {
			return 2 * x;
		}

		def	j: a | ℝ[a] → ℝ[a], (x) → {
			ℝ[a] y;
			∀i∈[0;a[, y[i] = 2 * x[i];
			return y;
		}

		def k: b | ℝ[b] → ℝ[b], (x) → return j(x);
		''')
		+ simulationVariables +
		'''
		let u = [0.0, 0.1];
		let v = [0.0, 0.1, 0.2];
		ℝ[2] w1;
		ℝ[2] w2;
		ℝ[3] w3;
		ℝ[2] w4;
		ℝ[3] w5;
		ℝ[2] w6;

		j1: w1 = h(u);
		j2: w2 = i(u);
		j3: w3 = i(v);
		j4: w4 = j(u);
		j5: w5 = j(v);
		j6: w6 = k(u);
		'''

		val result = executeModel(model)

		assertVariableValue(result, "w1", #[0.0, 0.2])
		assertVariableValue(result, "w2", #[0.0, 0.2])
		assertVariableValue(result, "w3", #[0.0, 0.2, 0.4])
		assertVariableValue(result, "w4", #[0.0, 0.2])
		assertVariableValue(result, "w5", #[0.0, 0.2, 0.4])
		assertVariableValue(result, "w6", #[0.0, 0.2])
	}

	@Test
	def void testInterpreteVarRef() {
		val model = getTestModuleForSimulation +
		'''
		let b1 = true;
		let b2 = b1; // -> true

		let n1 = 1;
		let n2 = n1; // -> 1
		let n3 = [2,3];
		let n4 = n3; // -> [2,3]
		let n5 = n3[0]; // -> 2
		let n6 = [[2,3],[4,5],[6,7]];
		let n7 = n6; // -> [[2,3],[4,5],[6,7]]
		let n8 = n6[1,1]; // -> 5

		let r1 = 1.;
		let r2 = r1; // -> 1.
		let r3 = [2.,3.];
		let r4 = r3; // -> [2.,3.]
		let r5 = r3[0]; // -> 2.
		let r6 = [[2.,3.],[4.,5.],[6.,7.]];
		let r7 = r6; // -> [[2.,3.],[4.,5.],[6.,7.]]
		let r8 = r6[1,1]; // -> 5.
		'''

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
