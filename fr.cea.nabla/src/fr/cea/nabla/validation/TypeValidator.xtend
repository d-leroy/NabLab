/*******************************************************************************
 * Copyright (c) 2018 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * 	Benoit Lelandais - initial implementation
 * 	Marie-Pierre Oudot - initial implementation
 * 	Jean-Sylvain Camier - Nabla generation support
 *******************************************************************************/
package fr.cea.nabla.validation

import com.google.inject.Inject
import fr.cea.nabla.VarExtensions
import fr.cea.nabla.nabla.Affectation
import fr.cea.nabla.nabla.And
import fr.cea.nabla.nabla.Comparison
import fr.cea.nabla.nabla.ConnectivityVar
import fr.cea.nabla.nabla.ContractedIf
import fr.cea.nabla.nabla.Equality
import fr.cea.nabla.nabla.Expression
import fr.cea.nabla.nabla.FunctionCall
import fr.cea.nabla.nabla.If
import fr.cea.nabla.nabla.Minus
import fr.cea.nabla.nabla.Modulo
import fr.cea.nabla.nabla.MulOrDiv
import fr.cea.nabla.nabla.NablaPackage
import fr.cea.nabla.nabla.Not
import fr.cea.nabla.nabla.Or
import fr.cea.nabla.nabla.Plus
import fr.cea.nabla.nabla.ReductionArg
import fr.cea.nabla.nabla.ReductionCall
import fr.cea.nabla.nabla.ScalarVarDefinition
import fr.cea.nabla.nabla.VarRef
import fr.cea.nabla.typing.BinaryOperationsTypeProvider
import fr.cea.nabla.typing.BoolType
import fr.cea.nabla.typing.ExpressionType
import fr.cea.nabla.typing.ExpressionTypeProvider
import fr.cea.nabla.typing.IntType
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.validation.Check

class TypeValidator extends BasicValidator
{
	static val BOOL = new BoolType
	static val INT = new IntType
	
	val np = NablaPackage::eINSTANCE	
	@Inject extension ExpressionTypeProvider
	@Inject extension BinaryOperationsTypeProvider
	@Inject extension VarExtensions

	// *** LES INSTRUCTIONS
	@Check def checkType(ScalarVarDefinition it)
	{
		checkExpectedType(defaultValue?.typeFor, type.typeFor, np.scalarVarDefinition_DefaultValue)
	}
	
	@Check def checkType(Affectation it) 
	{ 
		checkExpectedType(expression?.typeFor, varRef.typeFor, np.affectation_Expression)
	}
	
	@Check def checkType(If it) { checkExpectedType(condition?.typeFor, BOOL, np.if_Condition) }
		
	// *** LES EXPRESSIONS
	@Check
	def checkSeedAndReturnTypes(ReductionArg it)
	{
		val seedType = seed.typeFor
		val rType = returnType.typeFor
		if (!seedType.undefined && seedType == rType)
		{
			var msg = 'Seed type and return types must be identical: ' + seedType.label + '!=' + rType.label
			error(msg, NablaPackage.Literals::REDUCTION_ARG__SEED)
		}
	}

	@Check
	def checkArgs(FunctionCall it)
	{
		if (typeFor.undefined)
		{
			val inTypes = args.map[x | x.typeFor.label]
			var msg = 'Wrong arguments : ' + inTypes.join(', ')
			error(msg, NablaPackage.Literals::FUNCTION_CALL__FUNCTION)
		}
	}
	
	@Check
	def checkArgs(ReductionCall it)
	{
		if (typeFor.undefined)
		{
			val inType = arg.typeFor.label
			var msg = 'Wrong arguments : ' + inType
			error(msg, NablaPackage.Literals::REDUCTION_CALL__REDUCTION)
		}
	}

	// Il ne faut pas vérifier le type de la variable : checkType(VarAffectationDeclaration) le fait
	@Check
	def checkType(VarRef it)
	{
		val varBaseType = variable.baseType
		val dimensions = if (variable instanceof ConnectivityVar) (variable as ConnectivityVar).dimensions.length else 0
		val dimSizes = varBaseType.sizes
		if (dimensions != spaceIterators.size) 
			error('Number of iterators and variable dimension must be equal' + dimSizes.size, NablaPackage.Literals::VAR_REF__SPACE_ITERATORS)
//		if (dimSizes.size != arrayTypeIndices.size)
//			error('Number of indices and variable type must be compatible: ' + arrayTypeIndices.size + ' indices ' + ' for ' + varBaseType.label, NablaPackage.Literals::VAR_REF__ARRAY_TYPE_INDICES)
		if (it.typeFor.undefined)
			error('Undefined type', NablaPackage.Literals::VAR_REF__VARIABLE)
	}
	
	
	@Check def checkType(ContractedIf it) 
	{ 
		val condType = condition.typeFor
		val thenType = then.typeFor
		val elseType = ^else.typeFor
		checkExpectedType(condType, BOOL, np.contractedIf_Condition)
		checkExpectedType(thenType, elseType, np.contractedIf_Else)
	}
	
	@Check def checkType(Not it) { checkExpectedType(expression?.typeFor, BOOL, np.not_Expression) }
	// UnaryMinus fonctionne avec tous les types
	
	@Check def checkType(MulOrDiv it) { checkBinaryOp(left, right, op, np.mulOrDiv_Op) }
	@Check def checkType(Plus it) { checkBinaryOp(left, right, op, np.plus_Op) }
	@Check def checkType(Minus it) { checkBinaryOp(left, right, op, np.minus_Op) }
	@Check def checkType(Comparison it) { checkBinaryOp(left, right, op, np.comparison_Op) }
	@Check def checkType(Equality it) { checkBinaryOp(left, right, op, np.comparison_Op) }

	@Check def checkType(Modulo it) 
	{ 
		checkExpectedType(left?.typeFor, INT, np.and_Left)
		checkExpectedType(right?.typeFor, INT, np.and_Right)
	}

	@Check def checkType(And it) 
	{ 
		checkExpectedType(left?.typeFor, BOOL, np.and_Left)
		checkExpectedType(right?.typeFor, BOOL, np.and_Right)
	}

	@Check
	def checkType(Or it) 
	{
		checkExpectedType(left?.typeFor, BOOL, np.or_Left)
		checkExpectedType(right?.typeFor, BOOL, np.or_Right)
	}

	private def void checkExpectedType(ExpressionType actualType, ExpressionType expectedType, EReference reference) 
	{
		// si un des 2 types est indéfini, il ne faut rien vérifier pour éviter les erreurs multiples due à la récursion
		if (! (actualType.undefined || expectedType.undefined || actualType == expectedType))
			error("Expected " + expectedType.label + " type, but was " + actualType.label, reference)
	}	

	private def void checkBinaryOp(Expression left, Expression right, String op, EAttribute operator)
	{
		val leftType = left?.typeFor
		val rightType = right?.typeFor
		
		// si un des 2 types est indéfini, il ne faut rien vérifier pour éviter les erreurs multiples due à la récursion
		if (! (leftType.undefined || rightType.undefined))
			if (getTypeFor(leftType, rightType, op).undefined)
				error('Binary operator ' + op + ' undefined on types ' + leftType.label + ' and ' + rightType.label, operator)
	}
}