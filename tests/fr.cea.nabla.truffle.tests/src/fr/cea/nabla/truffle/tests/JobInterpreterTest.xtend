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
import fr.cea.nabla.tests.interpreter.AbstractJobInterpreterTest
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class JobInterpreterTest extends AbstractJobInterpreterTest {

	override assertInterpreteInstructionJob(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t", 5.0)
	}

	override assertInterpreteTimeLoopJob(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t_n0", 0.0)
//		assertVariableValue(result, "n", 10) TODO: currently not stored in top frame
		assertVariableValue(result, "t_n", 0.09)
		assertVariableValue(result, "t_nplus1", 0.1)
	}

	override assertInterpreteTimeLoopCopyJob(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "u_n0", #[0.0, 0.0])
		assertVariableValue(result, "u_n", #[1.0, 2.0])
		assertVariableValue(result, "X_n", "X_n0", typeof(double[][]))
	}
}
