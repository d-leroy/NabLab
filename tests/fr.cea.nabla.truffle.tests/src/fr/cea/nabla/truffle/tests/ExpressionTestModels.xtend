package fr.cea.nabla.truffle.tests

import com.google.inject.Inject
import fr.cea.nabla.ir.ir.IrModule
import fr.cea.nabla.tests.CompilationChainHelper
import fr.cea.nabla.tests.TestUtils

import static fr.cea.nabla.tests.TestUtils.*

class ExpressionTestModels {

	@Inject CompilationChainHelper compilationHelper

	def IrModule getContractedIf() {
		val model = testModuleForSimulation + '''	
			ℝ r1 = true ? 1.0 : 2.0; // -> 1.0
			ℝ r2 = false ? 1.0 : 2.0; // -> 1.0
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getUnaryExpression() {
		val model = testModuleForSimulation + '''
			ℾ b0 = !false; // -> true
			ℕ n0 = -(1); // -> -1
			ℝ r0 = -(1.); // -> -1.
			ℕ[2] n1 = [1, 2];
			ℕ[2] n2 = -n1; // -> [-1, -2]
			ℝ[2] r1 = [1., 2.];
			ℝ[2] r2 = -r1; // -> [-1., -2
			ℕ[2,2] n3 = [[0, 1],[1, 2]];
			ℕ[2,2] n4 = -n3; // -> [[0, -1],[-1, -2]]
			ℝ[2,2] r3 = [[0., 1.],[1., 2.]];
			ℝ[2,2] r4 = -r3; // -> [[-0., -1.],[-1., -2.]]*/
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getParenthesis() {
		val model = testModuleForSimulation + '''
			ℾ b = (true);
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getConstant() {
		// NB : Constant only for Scalar
		val model = testModuleForSimulation + '''
			ℕ n1 = 1;
			ℝ r1 = 2.0;
			ℾ b1 = true;
			ℾ b2 = false;
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getMinConstant() {
		val model = testModuleForSimulation + '''
			ℕ nMin = ℕ.MinValue;
			ℝ rMin = ℝ.MinValue;
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getMaxConstant() {
		val model = testModuleForSimulation + '''
			ℕ nMax = ℕ.MaxValue;
			ℝ rMax = ℝ.MaxValue;
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getBaseTypeConstant() {
		val model = testModuleForSimulation + '''
			ℕ n1 = ℕ(1);
			ℕ[2] n2 = ℕ[2](1);
			ℕ[2,3] n3 = ℕ[2,3](1);
			
			ℝ r1 = ℝ(1.);
			ℝ[2] r2 = ℝ[2](1.);
			ℝ[2,3] r3 = ℝ[2,3](1.);
			
			ℾ b1 = ℾ(true);
			ℾ[2] b2 = ℾ[2](true);
			ℾ[2,3] b3 = ℾ[2,3](true);
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getIntVectorConstant() {
		val model = testModuleForSimulation + '''
			ℕ[2] n = [1, 2];
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getIntMatrixConstant() {
		val model = testModuleForSimulation + '''
			ℕ[2,3] n = [[0, 1, 2],[1, 2, 3]];
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getRealVectorConstant() {
		val model = testModuleForSimulation + '''
			ℝ[2] r = [1.0, 2.0];
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getRealMatrixConstant() {
		val model = testModuleForSimulation + '''
			ℝ[2,3] r = [[0., 1., 2.],[1., 2., 3.]];
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getFunctionCall() {
		val model = TestUtils::getTestModule(defaultConnectivities, '''
			def getOne:  → ℕ;
			def addOne: ℕ → ℕ;
			def addOne: ℝ → ℝ;
			def add: ℕ × ℕ → ℕ;
			def add: ℝ × ℕ → ℝ;
			def add: ℝ × ℝ → ℝ;
			def add: x | ℝ[x] × ℝ[x] → ℝ[x];
			def add: x,y | ℝ[x,y] × ℝ[x,y] → ℝ[x,y];
		''') + TestUtils::simulationVariables + '''
			ℕ n0 = 0;
			ℕ n1 = getOne(); 	//-> 1
			ℕ n2 = addOne(n1); 	//-> 2
			ℕ n3 = add(n1, n2); //-> 3
			ℝ r0 = 0. ;
			ℝ r1 = addOne(r0); 	//-> 1.
			ℝ r2 = add(r1, n1); //-> 2.
			ℝ r3 = add(r2, r1); //-> 3.
			
			ℝ[2] u = ℝ[2](1.);
			ℝ[2] v = ℝ[2](2.);
			ℝ[2] w = add(u,v); //-> [3., 3.]
			
			ℝ[3] α = ℝ[3](1.);
			ℝ[3] β = ℝ[3](2.);
			ℝ[3] res1 = add(α,β); //-> [3., 3., 3.]
			
			ℝ[2,2] δ = ℝ[2,2](1.);
			ℝ[2,2] ρ = ℝ[2,2](2.);
			ℝ[2,2] res2 = add(δ,ρ); //-> [3., 3][3., 3.]
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getFunctionCallWithBody() {
		val model = TestUtils::getTestModule(defaultConnectivities, '''
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
		''') + TestUtils::simulationVariables + '''
			ℝ[2] u = [0.0, 0.1];
			ℝ[3] v = [0.0, 0.1, 0.2];
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

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}

	def IrModule getVarRef() {
		val model = getTestModuleForSimulation + '''
			ℾ b1 = true;
			ℾ b2 = b1; // -> true
			
			ℕ n1 = 1;
			ℕ n2 = n1; // -> 1
			ℕ[2] n3 = [2,3];
			ℕ[2] n4 = n3; // -> [2,3]
			ℕ n5 = n3[0]; // -> 2
			ℕ[3,2] n6 = [[2,3],[4,5],[6,7]];
			ℕ[3,2] n7 = n6; // -> [[2,3],[4,5],[6,7]]
			ℕ n8 = n6[1,1]; // -> 5
			
			ℝ r1 = 1.;
			ℝ r2 = r1; // -> 1.
			ℝ[2] r3 = [2.,3.];
			ℝ[2] r4 = r3; // -> [2.,3.]
			ℝ r5 = r3[0]; // -> 2.
			ℝ[3,2] r6 = [[2.,3.],[4.,5.],[6.,7.]];
			ℝ[3,2] r7 = r6; // -> [[2.,3.],[4.,5.],[6.,7.]]
			ℝ r8 = r6[1,1]; // -> 5.
		'''

		return compilationHelper.getIrModule(model, TestUtils::testGenModel)
	}
}
