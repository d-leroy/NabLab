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
import com.google.inject.Provider
import fr.cea.nabla.NablagenStandaloneSetup
import fr.cea.nabla.generator.IrModuleTransformer
import fr.cea.nabla.generator.NablagenInterpreter
import fr.cea.nabla.nablagen.NablagenModule
import fr.cea.nabla.repl.NablaREPLStandaloneSetup
import fr.cea.nabla.repl.nabla_repl.Instruction_Instruction
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.runner.RunWith
import fr.cea.nabla.generator.ir.IrInstructionFactory

@RunWith(XtextRunner)
@InjectWith(NablaREPLInjectorProvider)
class REPLCompilationChainHelper {
	@Inject extension ValidationTestHelper
	@Inject extension IrInstructionFactory
	@Inject Provider<NablagenInterpreter> interpreterProvider
	@Inject Provider<ResourceSet> resourceSetProvider
	@Inject IrModuleTransformer transformer

	var nablaSetup = new NablaREPLStandaloneSetup
	var nablaInjector = nablaSetup.createInjectorAndDoEMFRegistration
	var ParseHelper<Instruction_Instruction> nablaParseHelper = nablaInjector.getInstance(ParseHelper)

	var nablagenSetup = new NablagenStandaloneSetup
	var nablagenInjector = nablagenSetup.createInjectorAndDoEMFRegistration
	var ParseHelper<NablagenModule> nablagenParseHelper = nablagenInjector.getInstance(ParseHelper)

	def getIrInstruction(CharSequence model, CharSequence genModel) {
		val testProjectPath = System.getProperty("user.dir")
		val pluginsPath = testProjectPath + "/../../plugins/"
		var rs = resourceSetProvider.get

//		// Read MathFunctions
//		val mathFunctionsPath = pluginsPath + "fr.cea.nabla/nablalib/mathfunctions.nabla"
//		nablaParseHelper.parse(new String(Files.readAllBytes(Paths.get(mathFunctionsPath))), rs)
//
//		// Read LinearAlgebraFunctions
//		val linearAlgebraFunctionsPath = pluginsPath + "fr.cea.nabla/nablalib/linearalgebrafunctions.nabla"
//		nablaParseHelper.parse(new String(Files.readAllBytes(Paths.get(linearAlgebraFunctionsPath))), rs)
		var nablaInstruction = nablaParseHelper.parse(model, rs).original
		var irInstruction = nablaInstruction.toIrInstruction

		return irInstruction

//		nablaInstruction.assertNoErrors
//		rs.resources.add(nablaInstruction.eResource)
//		var nablaGenModule = nablagenParseHelper.parse(genModel, rs)
//		nablaGenModule.assertNoErrors
//
//		if (nablaGenModule.config === null)
//			throw new RuntimeException("Problem with nablagen configuration file")
//
//		val interpreter = interpreterProvider.get
//		val projectDir = pluginsPath + "fr.cea.nabla.ui/examples/NablaExamples"
//		val irModule = interpreter.buildIrModule(nablaGenModule.config, projectDir)
//
//		if (generateCode)
//			interpreter.generateCode(irModule, nablaGenModule.config.targets, nablaGenModule.config.simulation.iterationMax.name, nablaGenModule.config.simulation.timeMax.name, projectDir)
//
//		return irModule
	}
}
