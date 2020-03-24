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
class ModuleInterpreterTest {

	@Inject extension TestUtils
	
	@Test
	def void testInterpreteModule() {
		val model = getTestModule(10, 10) + '''
			// Simulation options
			const ℝ option_stoptime = 0.2;
			const ℕ option_max_iterations = 20000;
			
			iterate n while (t^{n} < option_stoptime && n < option_max_iterations);
			
			InitT: t^{n=0} = 0.;
			ComputeTn: t^{n+1} = t^{n} + 0.01;
		'''

		val result = executeModel(model)

		assertVariableValue(result, "t_n", 0.2)
	}
}
