/*******************************************************************************
 * Copyright (c) 2018 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.generator.java

import fr.cea.nabla.ir.Utils
import fr.cea.nabla.ir.ir.BaseTypeConstant
import fr.cea.nabla.ir.ir.BinaryExpression
import fr.cea.nabla.ir.ir.Constant
import fr.cea.nabla.ir.ir.ContractedIf
import fr.cea.nabla.ir.ir.FunctionCall
import fr.cea.nabla.ir.ir.IntMatrixConstant
import fr.cea.nabla.ir.ir.IntVectorConstant
import fr.cea.nabla.ir.ir.MaxConstant
import fr.cea.nabla.ir.ir.MinConstant
import fr.cea.nabla.ir.ir.Parenthesis
import fr.cea.nabla.ir.ir.RealMatrixConstant
import fr.cea.nabla.ir.ir.RealVectorConstant
import fr.cea.nabla.ir.ir.UnaryExpression
import fr.cea.nabla.ir.ir.VarRef

import static extension fr.cea.nabla.ir.BaseTypeExtensions.*
import static extension fr.cea.nabla.ir.generator.IteratorRefExtensions.*
import static extension fr.cea.nabla.ir.generator.Utils.*
import static extension fr.cea.nabla.ir.generator.java.VariableExtensions.*

class ExpressionContentProvider
{
	static def dispatch CharSequence getContent(ContractedIf it) 
	'''(«condition.content» ? «thenExpression.content» ':' «elseExpression.content»'''
	
	static def dispatch CharSequence getContent(BinaryExpression it) 
	{
		val lContent = left.content
		val rContent = right.content

		if (left.type.scalar && right.type.scalar) 
			'''«lContent» «operator» «rContent»'''
		else 
			'''ArrayOperations.«operator.operatorName»(«lContent», «rContent»)'''
	}

	static def dispatch CharSequence getContent(UnaryExpression it) '''«operator»«expression.content»'''
	static def dispatch CharSequence getContent(Parenthesis it) '''(«expression.content»)'''
	static def dispatch CharSequence getContent(Constant it) '''«value»'''
	
	static def dispatch CharSequence getContent(MinConstant it) 
	{
		switch getType().root
		{
			case INT  : '''Integer.MIN_VALUE'''
			case REAL : '''Double.MIN_VALUE'''
			default: throw new Exception('Invalid expression Min for type: ' + getType().label)
		}
	}

	static def dispatch CharSequence getContent(MaxConstant it) 
	{
		switch getType().root
		{
			case INT  : '''Integer.MAX_VALUE'''
			case REAL : '''Double.MAX_VALUE'''
			default: throw new Exception('Invalid expression Max for type: ' + getType().label)
		}
	}

	static def dispatch CharSequence getContent(BaseTypeConstant it) 
	{
		initConstant(type.sizes, value.content.toString)
	}
	
	static def dispatch CharSequence getContent(IntVectorConstant it) 
	'''«FOR v : values BEFORE '{' SEPARATOR ', ' AFTER '}'»«v»«ENDFOR»'''
	
	static def dispatch CharSequence getContent(IntMatrixConstant it) 
	'''«FOR v : values BEFORE '{' SEPARATOR ', ' AFTER '}'»«v.content»«ENDFOR»'''
	
	static def dispatch CharSequence getContent(RealVectorConstant it) 
	'''«FOR v : values BEFORE '{' SEPARATOR ', ' AFTER '}'»«v»«ENDFOR»'''
	
	static def dispatch CharSequence getContent(RealMatrixConstant it) 
	'''«FOR v : values BEFORE '{' SEPARATOR ', ' AFTER '}'»«v.content»«ENDFOR»'''
	
	static def dispatch CharSequence getContent(FunctionCall it) 
	'''«function.provider»«Utils::FunctionAndReductionproviderSuffix».«function.name»(«FOR a:args SEPARATOR ', '»«a.content»«ENDFOR»)'''
	
	static def dispatch CharSequence getContent(VarRef it)
	'''«variable.codeName»«FOR r : iterators BEFORE '[' SEPARATOR '][' AFTER ']'»«r.indexName»«ENDFOR»«FOR d:indices»[«d»]«ENDFOR»'''
}