/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.tests

import fr.cea.nabla.ir.interpreter.Context
import fr.cea.nabla.ir.interpreter.NV0Real
import fr.cea.nabla.ir.interpreter.NablaValue
import fr.cea.nabla.ir.ir.IrModule
import fr.cea.nabla.ir.ir.SimpleVariable
import fr.cea.nabla.nabla.Affectation
import fr.cea.nabla.nabla.Connectivity
import fr.cea.nabla.nabla.ConnectivityCall
import fr.cea.nabla.nabla.SimpleVarDefinition
import fr.cea.nabla.nabla.Var
import fr.cea.nabla.nabla.VarGroupDeclaration
import java.nio.file.Files
import java.nio.file.Paths
import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.junit.Assert

import static extension fr.cea.nabla.ir.IrModuleExtensions.*
import static extension fr.cea.nabla.ir.interpreter.ExpressionInterpreter.*

class TestUtils 
{
	static val DoubleTolerance = 1e-15

	static def getAllVars(EObject it)
	{
		val allVariables = new ArrayList<Var>
		for (i : eAllContents.toIterable)
			switch i
			{
				VarGroupDeclaration : allVariables += i.variables
				SimpleVarDefinition : allVariables += i.variable
			}
		return allVariables
	}

	static def getAllAffectations(EObject it)
	{
		eAllContents.filter(Affectation)
	}

	// ===== CharSequence utils =====

	static def getVariableByName(EObject it, String variableName)
	{
		allVars.findFirst[v | v.name == variableName]
	}

	static def getVarAffectationByName(EObject it, String variableName)
	{
		allAffectations.findFirst[a | a.left.target.name == variableName]
	}

	static def getConnectivityCallFor(EObject it, Connectivity connectivity)
	{
		eAllContents.filter(ConnectivityCall).findFirst[ cc | cc.connectivity == connectivity]
	}

	// ===== CharSequence utils =====
	private static def String getEmptyTestModule()
	'''
	module Test;
	with Math.*;
	'''

	//TODO These options should be filled in nablagen
	private static def String getMandatoryOptions(int xQuads, int yQuads)
	'''
	const ℝ X_EDGE_LENGTH = 0.01;
	const ℝ Y_EDGE_LENGTH = X_EDGE_LENGTH;
	const ℕ X_EDGE_ELEMS = «xQuads»;
	const ℕ Y_EDGE_ELEMS = «yQuads»;
	'''

	static def String getMandatoryOptions()
	{
		return getMandatoryOptions(10, 10)
	}

//	static def String getSimulationOptions(double stopTime, int maxIterations)
//	'''
//	const ℝ option_stoptime = «stopTime»;
//	const ℕ option_max_iterations = «maxIterations»;
//	'''
//
//	static def String getSimulationOptions()
//	{
//		return getSimulationOptions(0.2, 1)
//	}

	static def CharSequence getConnectivities()
	'''
	items { node, cell }

	set nodes: → {node};
	set cells: → {cell};
	set nodesOfCell: cell → {node};
	'''

	static def CharSequence getNodesConnectivity()
	'''
	items { node }

	set nodes: → {node};
	'''

//	static def String getMandatoryMeshVariables()
//	'''
//	ℝ[2] X{nodes};
//	'''
//
//	static def String getMandatorySimulationVariables()
//	'''
//	ℝ t = 0.0;
//	'''
//
//	static def String getMandatoryMeshOptionsAndVariables()
//	{
//		mandatoryMeshOptions + mandatoryMeshVariables
//	}
//
//	static def String getMandatorySimulationOptionsAndVariables()
//	{
//		simulationOptions + mandatorySimulationVariables
//	}
//
//	static def String getMandatoryOptionsAndVariables()
//	{
//		mandatoryMeshOptions + simulationOptions + mandatoryMeshVariables + mandatorySimulationVariables
//	}

	static def CharSequence getInitTJob()
	'''
	initT: t = 0.0;
	'''

	static def CharSequence getTestModule(CharSequence connectivities, CharSequence functions)
	{
		emptyTestModule + connectivities + functions + mandatoryOptions
	}

	static def CharSequence getTestModule(int xQuads, int yQuads)
	{
		emptyTestModule + connectivities + getMandatoryOptions(xQuads, yQuads)
	}

//	static def getTestModuleWithCustomFunctions(CharSequence functions, boolean withConnectivities)
//	{
//		emptyTestModule + connectivities + functions + mandatoryOptions
//	}
//
//	static def getTestModuleWithCustomConnectivities(CharSequence connectivities)
//	{
//		emptyTestModule + connectivities + mandatoryOptions
//	}

//	//Useful to prevent warnings
//	static def getTestModuleWithMandatoryOptions()
//	{
//		emptyTestModule + mandatoryOptions
//	}
//
	//Useful to prevent warnings
//	static def getTestModuleWithCustomVars(CharSequence variables)
//	{
//		emptyTestModule + mandatoryOptions + variables
//	}
//
//	//Useful to prevent warnings
//	static def getTestModuleWithCoordVariableWithCustomFunctions(CharSequence functions)
//	{
//		emptyTestModule + nodesConnectivity + functions + mandatoryOptions
//	}

	//Useful to prevent warnings
//	static def getTestModuleWithCoordVariableWithCustomConnectivities(CharSequence connectivities)
//	{
//		emptyTestModule + connectivities + mandatoryOptions
//	}
	
	static def getTestGenModel()
	{
		'''
		with Test.*;

		workflow TestDefaultGenerationChain transforms Test
		{
			Nabla2Ir nabla2ir
			{
			}
			ReplaceUtf replaceUtf follows nabla2ir
			{
			}
			ReplaceReductions replaceReductions follows replaceUtf
			{
			}
			OptimizeConnectivities optimizeConnectivities follows replaceReductions
			{
				connectivities = cells, nodes;
			}
			FillHLTs fillHlts follows optimizeConnectivities
			{
			}
		}
		'''
	}

	// Interpreter asserts
	static def assertVariableDefaultValue(IrModule irModule, Context context, String variableName, NablaValue value)
	{
		Assert.assertEquals(value, (irModule.getVariableByName(variableName) as SimpleVariable).defaultValue.interprete(context))
	}

	static def dispatch assertVariableValueInContext(IrModule irModule, Context context, String variableName, NablaValue value)
	{
		Assert.assertEquals(value, context.getVariableValue(irModule.getVariableByName(variableName)))
	}

	static def dispatch assertVariableValueInContext(IrModule irModule, Context context, String variableName, NV0Real value)
	{
		val variableValue = context.getVariableValue(irModule.getVariableByName(variableName))
		Assert.assertNotNull(variableValue)
		Assert.assertTrue(variableValue instanceof NV0Real)
		Assert.assertEquals(value.data, (variableValue as NV0Real).data, TestUtils.DoubleTolerance)
	}

	//Read File to String
	static def readFileAsString(String filePath)
	{
		new String(Files.readAllBytes(Paths.get(filePath)))
	}
}