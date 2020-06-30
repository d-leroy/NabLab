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
import fr.cea.nabla.tests.interpreter.AbstractOptionsInterpreterTest
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class OptionsInterpreterTest extends AbstractOptionsInterpreterTest {
	
	override assertInterpreteDefaultOptions(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "A", 10)
		assertVariableValue(result, "B", 9)
		assertVariableValue(result, "C", 25.0)
		assertVariableValue(result, "D", #[25.0, 25.0, 25.0])
		assertVariableValue(result, "M", #[#[25.0, 25.0, 25.0],#[25.0, 25.0, 25.0]])
	}

	override assertInterpreteJsonOptions(String model, String jsonOptions) {
		val result = executeModelWithOptions(model, jsonOptions)
		
		assertVariableValue(result, "A", 10)
		assertVariableValue(result, "B", 2)
		assertVariableValue(result, "C", 27.0)
		assertVariableValue(result, "D", #[25.0, 12.12, 25.0])
		assertVariableValue(result, "M", #[#[25.0, 13.13, 25.0],#[25.0, 25.0, 5.4]])
	}
}