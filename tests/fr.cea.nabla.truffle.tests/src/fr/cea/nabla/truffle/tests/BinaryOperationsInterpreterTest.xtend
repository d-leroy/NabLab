/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.truffle.tests

import fr.cea.nabla.tests.NablaInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static fr.cea.nabla.tests.TestUtils.*
import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class BinaryOperationsInterpreterTest {

	@Test
	def void testGetValueOfNV0Bool_NV0Bool() {
		val model = testModuleForSimulation + '''
			ℾ b1 = true || false; // -> true
			ℾ b2 = true || true; // -> true
			ℾ b3 = false || false; // -> false
			
			ℾ b4 = true && false; // -> false
			ℾ b5 = true && true; // -> true
			ℾ b6 = false && false; // -> false
			
			ℾ b7 = true == false; // -> false
			ℾ b8 = true != false; // -> true
			ℾ b9 = true >= false; // -> true
			ℾ b10 = true <= false; // -> false
			ℾ b11 = true > false; // -> true
			ℾ b12 = true < false; // -> false
		'''

		val result = executeModel(model)

		assertVariableValue(result, "b1", true)
		assertVariableValue(result, "b2", true)
		assertVariableValue(result, "b3", false)

		assertVariableValue(result, "b4", false)
		assertVariableValue(result, "b5", true)
		assertVariableValue(result, "b6", false)

		assertVariableValue(result, "b7", false)
		assertVariableValue(result, "b8", true)
		assertVariableValue(result, "b9", true)
		assertVariableValue(result, "b10", false)
		assertVariableValue(result, "b11", true)
		assertVariableValue(result, "b12", false)
	}

	@Test
	def void testGetValueOfNV0Int_NV0Int() {
		val model = testModuleForSimulation + '''
			ℾ b1 = 1 == 2; // -> false
			ℾ b2 = 1 == 1; // -> true
			
			ℾ b3 = 1 != 2; // -> true
			ℾ b4 = 2 != 2; // -> false
			
			ℾ b5 = 1 >= 2; // -> false
			ℾ b6 = 2 >= 2; // -> true
			
			ℾ b7 = 1 <= 2; // -> true
			ℾ b8 = 2 <= 2; // -> true
			
			ℾ b9 = 1 > 2; // -> false
			ℾ b10 = 2 > 1; // -> true
			
			ℾ b11 = 1 < 2; // -> true
			ℾ b12 = 2 < 1; // -> false
			
			ℕ n1 = 1 + 2; // -> 3
			ℕ n2 = 2 - 1; // -> 1
			ℕ n3 = 2 * 3; // -> 6
			ℕ n4 = 6 / 3; // -> 2
			ℕ n5 = 7 / 3; // -> 2
			ℕ n6 = 7 % 3; // -> 1
		'''

		val result = executeModel(model)

		assertVariableValue(result, "b1", false)
		assertVariableValue(result, "b2", true)
		assertVariableValue(result, "b3", true)
		assertVariableValue(result, "b4", false)
		assertVariableValue(result, "b5", false)
		assertVariableValue(result, "b6", true)
		assertVariableValue(result, "b7", true)
		assertVariableValue(result, "b8", true)
		assertVariableValue(result, "b9", false)
		assertVariableValue(result, "b10", true)
		assertVariableValue(result, "b11", true)
		assertVariableValue(result, "b12", false)

		assertVariableValue(result, "n1", 3)
		assertVariableValue(result, "n2", 1)
		assertVariableValue(result, "n3", 6)
		assertVariableValue(result, "n4", 2)
		assertVariableValue(result, "n5", 2)
		assertVariableValue(result, "n6", 1)
	}

	@Test
	def void testGetValueOfNV0Int_NV0Real() {
		val model = testModuleForSimulation + '''
			ℾ b1 = 1 == 2.; // -> false
			ℾ b2 = 1 == 1; // -> true
			
			ℾ b3 = 1 != 2.; // -> true
			ℾ b4 = 2 != 2.; // -> false
			
			ℾ b5 = 1 >= 2.; // -> false
			ℾ b6 = 2 >= 2.; // -> true
			
			ℾ b7 = 1 <= 2.; // -> true
			ℾ b8 = 2 <= 2.; // -> true
			
			ℾ b9 = 1 > 2.; // -> false
			ℾ b10 = 2 > 1.; // -> true
			
			ℾ b11 = 1 < 2.; // -> true
			ℾ b12 = 2 < 1.; // -> false
			
			ℝ n1 = 1 + 2.; // -> 3.
			ℝ n2 = 2 - 1.; // -> 1.
			ℝ n3 = 2 * 3.; // -> 6.
			ℝ n4 = 6 / 3.; // -> 2.
			ℝ n5 = 7 / 2.; // -> 3.5.
		'''

		val result = executeModel(model)

		assertVariableValue(result, "b1", false)
		assertVariableValue(result, "b2", true)
		assertVariableValue(result, "b3", true)
		assertVariableValue(result, "b4", false)
		assertVariableValue(result, "b5", false)
		assertVariableValue(result, "b6", true)
		assertVariableValue(result, "b7", true)
		assertVariableValue(result, "b8", true)
		assertVariableValue(result, "b9", false)
		assertVariableValue(result, "b10", true)
		assertVariableValue(result, "b11", true)
		assertVariableValue(result, "b12", false)

		assertVariableValue(result, "n1", 3.0)
		assertVariableValue(result, "n2", 1.0)
		assertVariableValue(result, "n3", 6.0)
		assertVariableValue(result, "n4", 2.0)
		assertVariableValue(result, "n5", 3.5)
	}

	@Test
	def void testGetValueOfNV0Int_NV1Int() {
		val model = testModuleForSimulation + '''
			ℕ[2] n1 = [1,2];
			ℕ[2] n2 = 3 + n1;
			ℕ[2] n3 = 3 * n1;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "n1", #[1, 2] as int[])
		assertVariableValue(result, "n2", #[4, 5] as int[])
		assertVariableValue(result, "n3", #[3, 6] as int[])
	}
}
