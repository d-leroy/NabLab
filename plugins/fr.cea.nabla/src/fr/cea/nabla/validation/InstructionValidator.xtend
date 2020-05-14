/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.validation

import com.google.inject.Inject
import fr.cea.nabla.ExpressionExtensions
import fr.cea.nabla.nabla.Affectation
import fr.cea.nabla.nabla.If
import fr.cea.nabla.nabla.NablaModule
import fr.cea.nabla.nabla.NablaPackage
import fr.cea.nabla.nabla.SimpleVarDefinition
import fr.cea.nabla.typing.ExpressionTypeProvider
import org.eclipse.xtext.validation.Check
import fr.cea.nabla.nabla.VarGroupDeclaration
import fr.cea.nabla.nabla.ConnectivityVar

class InstructionValidator extends FunctionOrReductionValidator
{
	@Inject extension ValidationUtils
	@Inject extension ExpressionTypeProvider
	@Inject extension ExpressionExtensions

	public static val LOCAL_CONNECTIVITY_VAR = "Instructions::LocalConnectivityVar"
	public static val AFFECTATION_TYPE = "Instructions::AffectationType"
	public static val IF_CONDITION_BOOL = "Instructions::IfConditionBool"
	public static val GLOBAL_VAR_VALUE = "Instructions::GlobalVarValue"
	public static val LOCAL_OPTION = "Instructions::Local Option"

	static def getLocalConnectivityVarMsg() { "Local variables not allowed on connectivities"}
	static def getAffectationTypeMsg(String actualTypeName, String expectedTypeName) { "Expected " + expectedTypeName + ", but was " + actualTypeName }
	static def getIfConditionBoolMsg(String actualTypeName) { "Expected " + ValidationUtils::BOOL.label + ", but was " + actualTypeName }
	static def getGlobalVarValueMsg() { "Assignment with reduction, external function or card not allowed in options and global variables" }
	static def getLocalOptionMsg() { "Option definition not allowed in jobs and functions (options are global)" }

	@Check
	def checkLocalConnectivitityVar(VarGroupDeclaration it)
	{
		// Global or local variable ?
		if (eContainer !== null && !(eContainer instanceof NablaModule))
		{
			for (i : 0..<variables.size)
				if (variables.get(i) instanceof ConnectivityVar)
					error(getLocalConnectivityVarMsg(), NablaPackage.Literals::VAR_GROUP_DECLARATION__VARIABLES, i, LOCAL_CONNECTIVITY_VAR)
		}
	}

	@Check
	def checkAffectationType(Affectation it)
	{
		if (!checkExpectedType(right?.typeFor, left?.typeFor))
			error(getAffectationTypeMsg(right?.typeFor.label, left?.typeFor.label), NablaPackage.Literals.AFFECTATION__RIGHT, AFFECTATION_TYPE)
	}

	@Check
	def checkIfConditionBoolType(If it)
	{
		if (!checkExpectedType(condition?.typeFor, ValidationUtils::BOOL))
			error(getIfConditionBoolMsg(condition?.typeFor.label), NablaPackage.Literals.IF__CONDITION, IF_CONDITION_BOOL)
	}

	@Check
	def checkGlobalVarValue(SimpleVarDefinition it)
	{
		if (value !== null)
		{
			val global = (eContainer !== null && eContainer instanceof NablaModule)
			if (global && !value.nablaEvaluable)
				error(getGlobalVarValueMsg(), NablaPackage.Literals::SIMPLE_VAR_DEFINITION__VALUE, GLOBAL_VAR_VALUE)
		}
	}

	@Check
	def checkLocalOption(SimpleVarDefinition it)
	{
		if (value !== null && option)
		{
			val global = (eContainer !== null && eContainer instanceof NablaModule)
			if (!global)
				error(getLocalOptionMsg(), NablaPackage.Literals::SIMPLE_VAR_DEFINITION__VALUE, LOCAL_OPTION)
		}
	}
}