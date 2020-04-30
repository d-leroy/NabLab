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
import fr.cea.nabla.tests.interpreter.AbstractBinaryOperationsInterpreterTest
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class BinaryOperationsInterpreterTest extends AbstractBinaryOperationsInterpreterTest {

	override assertGetValueOfNV0Bool_NV0Bool(String model) {
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

	override assertGetValueOfNV0Int_NV0Int(String model) {
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

	override assertGetValueOfNV0Int_NV0Real(String model) {
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

	override assertGetValueOfNV0Int_NV1Int(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "n1", #[1, 2] as int[])
		assertVariableValue(result, "n2", #[4, 5] as int[])
		assertVariableValue(result, "n3", #[3, 6] as int[])
	}
}
