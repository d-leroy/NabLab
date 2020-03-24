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
class JobInterpreterTest {

	@Inject extension TestUtils

	@Test
	def void testInterpreteInstructionJob() {
		val model = testModuleForSimulation + '''
			initT : t = 5.;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "t", 5.0)
	}

	@Test
	def void testInterpreteTimeLoopJob() {
		val model = testModuleForSimulation + '''
			// Simulation options
			const ℝ option_stoptime = 0.2;
			const ℕ option_max_iterations = 10;
			
			iterate n while (t^{n} < option_stoptime && n < option_max_iterations);
			
			InitT: t^{n=0} = 0.;
			ComputeTn: t^{n+1} = t^{n} + 0.01;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "t_n0", 0.0)
		assertVariableValue(result, "n", 10)
		assertVariableValue(result, "t_n", 0.09)
		assertVariableValue(result, "t_nplus1", 0.1)
	}

	@Test
	def void testInterpreteTimeLoopCopyJob() {
		val model = getTestModule(10, 10) + '''
			// Simulation options
			const ℝ option_stoptime = 0.2;
			const ℕ option_max_iterations = 10;
			ℝ[2] u;
			ℝ[2] center{cells};
			
			iterate n while (t^{n} < option_stoptime && n < option_max_iterations);
			
			ComputeUx : u^{n}[0] = u^{n=0}[0] + 1.0;
			ComputeUy : u^{n}[1] = u^{n=0}[1] + 2.0;
			IniCenter: ∀j∈cells(), center{j} = 0.25 * ∑{r∈nodesOfCell(j)}(X^{n=0}{r});
		'''

		val result = executeModel(model)

		assertVariableValue(result, "u_n0", #[0.0, 0.0])
		assertVariableValue(result, "u_n", #[1.0, 2.0])
//		val X_n0 = context.getVariableValue("X_n0")
//		assertVariableValue(result, "X_n", X_n0)
		assertVariableValue(result, "X_n", "X_n0")
	}
}
