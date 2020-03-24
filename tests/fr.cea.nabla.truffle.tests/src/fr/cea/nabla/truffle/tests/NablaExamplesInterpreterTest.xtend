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
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import static fr.cea.nabla.truffle.tests.TruffleTestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class NablaExamplesInterpreterTest {

	@Inject extension TestUtils
	
	static String wsPath
	static String examplesProjectSubPath
	static String examplesProjectPath

	@BeforeClass
	def static void setup() {
		val testProjectPath = System.getProperty("user.dir")
		wsPath = testProjectPath + "/../../"
		examplesProjectSubPath = "plugins/fr.cea.nabla.ui/examples/NablaExamples/"
		examplesProjectPath = wsPath + examplesProjectSubPath
	}

	@Test
	def void testInterpreteGlace2D() {
		val model = readFileAsString(examplesProjectPath + "src/glace2d/Glace2d.nabla")

		executeModel(model)
	}

	@Test
	def void testInterpreteHeatEquation() {
		val model = readFileAsString(examplesProjectPath + "src/heatequation/HeatEquation.nabla")

		for (var i = 0; i < 10; i++) {
			executeModel(model)
		}
	}
}
