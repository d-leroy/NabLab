/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla

import com.google.inject.Inject
import fr.cea.nabla.nabla.ArgOrVarRef
import fr.cea.nabla.nabla.Cardinality
import fr.cea.nabla.nabla.Expression
import fr.cea.nabla.nabla.FunctionCall
import fr.cea.nabla.nabla.ReductionCall

class ExpressionExtensions
{
	@Inject extension ArgOrVarExtensions

	def boolean isReductionLess(Expression e) { check(e, [checkReductionLess]) }
	def boolean isNablaEvaluable(Expression e) { check(e, [checkNablaEvaluable]) }
	def boolean isConstExpr(Expression e) { check(e, [checkConstExpr]) }

	private def check(Expression e, (Expression) => boolean checker)
	{
		checker.apply(e) && e.eAllContents.filter(Expression).forall[x | checker.apply(x)]
	}

	private def boolean checkReductionLess(Expression e) 
	{
		!(e instanceof ReductionCall)
	}

	private def boolean checkNablaEvaluable(Expression e) 
	{
		switch e
		{
			ReductionCall, Cardinality: false
			FunctionCall: !e.function.external
			ArgOrVarRef: e.timeIterators.empty && e.spaceIterators.empty && e.target.nablaEvaluable
			default: true
		}
	}

	private def boolean checkConstExpr(Expression e)
	{
		switch e
		{
			ReductionCall, FunctionCall, Cardinality: false
			ArgOrVarRef: e.timeIterators.empty && e.spaceIterators.empty && e.target.constExpr
			default: true
		}
	}
}