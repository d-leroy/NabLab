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

import fr.cea.nabla.ir.MandatoryVariables
import fr.cea.nabla.tests.NablaInjectorProvider
import fr.cea.nabla.tests.TestUtils
import fr.cea.nabla.tests.interpreter.AbstractInstructionInterpreterTest
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Assert
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class InstructionInterpreterTest extends AbstractInstructionInterpreterTest {

	override assertInterpreteVarDefinition(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t", 1.0)
	}

	override assertInterpreteInstructionBlock(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t", 1.0)
	}

	override assertInterpreteAffectation(String model) {
		val result = executeModel(model)

		assertVariableValue(result, "t", 1.0)
	}

	override assertInterpreteLoop(String model, int xQuads, int yQuads) {
		val result = executeModel(model)

		val nbCells = xQuads * yQuads
		val double[] u = newDoubleArrayOfSize(nbCells)
		for (var i = 0; i < u.length; i++)
			u.set(i, 1.0)

		assertVariableValue(result, "U", u)

		val cjr = getVariableValue(result, "C", typeof(double[][][]))

		val nbNodesOfCell = 4
		val xEdgeLength = getVariableValue(result, MandatoryVariables.X_EDGE_LENGTH, typeof(double))
		val yEdgeLength = getVariableValue(result, MandatoryVariables.Y_EDGE_LENGTH, typeof(double))
		for (var j = 0; j < nbCells; j++) {
			for (var r = 0; r < nbNodesOfCell; r++) {
				Assert.assertEquals(0.5 * (xEdgeLength), Math.abs(cjr.get(j, r).get(0)), TestUtils.DoubleTolerance)
				Assert.assertEquals(0.5 * (yEdgeLength), Math.abs(cjr.get(j, r).get(1)), TestUtils.DoubleTolerance)
			}
		}
	}
	
	override assertInterpreteWhile(String model, int xQuads, int yQuads) {
		val result = executeModel(model)
		
		val nbCells = xQuads * yQuads
		val double[] u = newDoubleArrayOfSize(nbCells)
		for (i : 0..<u.length)
			u.set(i, 2.0)

		assertVariableValue(result, "U", u)
	}
	
	override assertInterpreteIf(String model, int xQuads, int yQuads) {
		val result = executeModel(model)

		val nbCells = xQuads * yQuads
		val double[] u = newDoubleArrayOfSize(nbCells)
		for (i : 0..<u.length)
			if (i % 2 == 0)
				u.set(i, 0.0)
			else
				u.set(i, 1.0)

		assertVariableValue(result, "U", u)
	}

	override assertInterpreteSetDefinition(String model, int xQuads, int yQuads) {
		val result = executeModel(model)

		val nbCells = xQuads * yQuads
		val double[] u = newDoubleArrayOfSize(nbCells)
		for (var i = 0; i < u.length; i++)
			u.set(i, 1.0)

		assertVariableValue(result, "U", u)
	}

	override assertInterpreteExit(String model) {
		try {
			executeModel(model)
			Assert::fail("Should throw exception")
		} catch (RuntimeException e) {
			Assert.assertTrue(e.message.contains("V must be less than 100"))
		}
	}
}
