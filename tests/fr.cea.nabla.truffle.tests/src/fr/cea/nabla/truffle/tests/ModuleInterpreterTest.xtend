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
import fr.cea.nabla.tests.interpreter.AbstractModuleInterpreterTest
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class ModuleInterpreterTest extends AbstractModuleInterpreterTest {

	override assertInterpreteModule(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t_n", 0.2)
	}
}
