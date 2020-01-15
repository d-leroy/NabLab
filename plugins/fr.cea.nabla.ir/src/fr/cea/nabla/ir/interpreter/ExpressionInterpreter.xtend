/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.interpreter

import fr.cea.nabla.ir.generator.Utils
import fr.cea.nabla.ir.ir.ArgOrVarRef
import fr.cea.nabla.ir.ir.BaseType
import fr.cea.nabla.ir.ir.BaseTypeConstant
import fr.cea.nabla.ir.ir.BinaryExpression
import fr.cea.nabla.ir.ir.BoolConstant
import fr.cea.nabla.ir.ir.ContractedIf
import fr.cea.nabla.ir.ir.SizeTypeSymbolRef
import fr.cea.nabla.ir.ir.FunctionCall
import fr.cea.nabla.ir.ir.IntConstant
import fr.cea.nabla.ir.ir.MaxConstant
import fr.cea.nabla.ir.ir.MinConstant
import fr.cea.nabla.ir.ir.Parenthesis
import fr.cea.nabla.ir.ir.RealConstant
import fr.cea.nabla.ir.ir.UnaryExpression
import fr.cea.nabla.ir.ir.VectorConstant
import java.util.Arrays

import static fr.cea.nabla.ir.interpreter.DimensionInterpreter.*
import static fr.cea.nabla.ir.interpreter.InstructionInterpreter.*
import static fr.cea.nabla.ir.interpreter.IrTypeExtensions.*
import static fr.cea.nabla.ir.interpreter.NablaValueGetter.*
import static fr.cea.nabla.ir.interpreter.NablaValueSetter.*

import static extension fr.cea.nabla.ir.Utils.*
import static extension fr.cea.nabla.ir.interpreter.NablaValueExtensions.*

class ExpressionInterpreter
{
	static def dispatch NablaValue interprete(ContractedIf it, Context context)
	{
		context.logFinest("Interprete ContractedIf")
		val condValue = condition.interprete(context)
		if ((condValue as NV0Bool).data) thenExpression.interprete(context)
		else elseExpression.interprete(context) 
	}

	static def dispatch NablaValue interprete(BinaryExpression it, Context context)	
	{
		context.logFinest("Interprete BinaryExpression")
		val lValue = left.interprete(context)
		val rValue = right.interprete(context)
		BinaryOperationsInterpreter::getValueOf(lValue, rValue, operator)
	}

	static def dispatch NablaValue interprete(UnaryExpression it, Context context)
	{
		context.logFinest("Interprete UnaryExpression")
		val eValue = expression.interprete(context)
		switch eValue
		{
			NV0Bool case operator == '!': new NV0Bool(!eValue.data)
			NV0Int case operator == '-': new NV0Int(-eValue.data)
			NV0Real case operator == '-': new NV0Real(-eValue.data)
			NV1Int case operator == '-': new NV1Int(eValue.data.map[x|-x])
			NV1Real case operator == '-': new NV1Real(eValue.data.map[x|-x])
			NV2Int case operator == '-': computeUnaryMinus(eValue)
			NV2Real case operator == '-': computeUnaryMinus(eValue)
			default: throw new RuntimeException('Wrong unary operator: ' + operator)
		}
	}

	static def dispatch NablaValue interprete(Parenthesis it, Context context)
	{
		context.logFinest("Interprete Parenthesis")
		expression.interprete(context)
	}

	static def dispatch NablaValue interprete(IntConstant it, Context context) 
	{
		context.logFinest("Interprete IntConstant")
		new NV0Int(value)
	}

	static def dispatch NablaValue interprete(RealConstant it, Context context) 
	{
		context.logFinest("Interprete RealConstant")
		new NV0Real(value)
	}

	static def dispatch NablaValue interprete(BoolConstant it, Context context) 
	{ 
		context.logFinest("Interprete BoolConstant")
		new NV0Bool(value)
	}

	static def dispatch NablaValue interprete(MinConstant it, Context context)
	{
		context.logFinest("Interprete MinConstant")
		val t = type as BaseType
		switch t.primitive
		{
			case BOOL: throw new RuntimeException('No min constant on bool')
			case INT: new NV0Int(Integer.MIN_VALUE)
			case REAL: new NV0Real(Double.MIN_VALUE)
		}
	}

	static def dispatch NablaValue interprete(MaxConstant it, Context context)
	{
		context.logFinest("Interprete MaxConstant")
		val t = type as BaseType
		switch t.primitive
		{
			case BOOL: throw new RuntimeException('No max constant on bool')
			case INT: new NV0Int(Integer.MAX_VALUE)
			case REAL: new NV0Real(Double.MAX_VALUE)
		}
	}

	static def dispatch NablaValue interprete(BaseTypeConstant it, Context context)
	{
		context.logFinest("Interprete BaseTypeConstant")
		val expressionValue = value.interprete(context)
		val t = type as BaseType
		val sizes = getIntSizes(t, context)
		switch sizes.size
		{
			case 0: expressionValue
			case 1: buildArrayValue(sizes.get(0), expressionValue)
			case 2: buildArrayValue(sizes.get(0), sizes.get(1), expressionValue)
			default: throw new RuntimeException('Wrong path...')
		}
	}

	static def dispatch NablaValue interprete(VectorConstant it, Context context)
	{
		context.logFinest("Interprete VectorConstant")
		val expressionValues = values.map[x | interprete(x, context)]
		val t = type as BaseType
		val value = BaseTypeValueFactory.createValue(t, context)
		for (i : 0..<expressionValues.length)
			setValue(value, #[i], expressionValues.get(i))
		return value
	}

	static def dispatch NablaValue interprete(FunctionCall it, Context context)
	{
		context.logFinest("Interprete FunctionCall " + function.name)
		val argValues = args.map[x|interprete(x, context)]
		if (function.body === null)
		{
			val providerClassName = irModule.name.toLowerCase + '.' + function.provider + Utils.FunctionReductionPrefix
			val tccl = Thread.currentThread().getContextClassLoader()
			val providerClass = Class.forName(providerClassName, true, tccl)
			val javaTypes = argValues.map[x | FunctionCallHelper.getJavaType(x) ]
			val method = providerClass.getDeclaredMethod(function.name, javaTypes)
			method.setAccessible(true)
			val javaValues = argValues.map[x | FunctionCallHelper.getJavaValue(x)].toArray
			val result = method.invoke(null, javaValues)
			return FunctionCallHelper.createNablaValue(result)
		}
		else
		{
			val innerContext = new Context(context)
			for (iArg : 0..<args.length)
			{
				// set DimensionSymbol values with argument types
				val callerArg = args.get(iArg)
				val calleeArg = function.inArgs.get(iArg)
				val callerArgTypeSizes = getIntSizes(callerArg.type, context)
				for (iSize : 0..<callerArgTypeSizes.length)
				{
					val callerArgTypeTypeSize = callerArgTypeSizes.get(iSize)
					val calleeArgTypeDimension = calleeArg.type.sizes.get(iSize)
					if (calleeArgTypeDimension instanceof SizeTypeSymbolRef)
						innerContext.addDimensionValue((calleeArgTypeDimension as SizeTypeSymbolRef).target, callerArgTypeTypeSize)
				}

				// set argument value
				innerContext.addVariableValue(calleeArg, argValues.get(iArg))
			}
			return interprete(function.body, innerContext)
		}
	}

	static def dispatch NablaValue interprete(ArgOrVarRef it, Context context)
	{
		context.logFinest("Interprete VarRef " + target.name)
		val value = context.getVariableValue(target)
		val iteratorValues = iterators.map[x | context.getIndexValue(x)]
		val indicesValues = indices.map[x | interprete(x, context)]
		val allIndices = (iteratorValues + indicesValues).toList
		getValue(value, allIndices)
	}

	private static def dispatch NablaValue buildArrayValue(int size, NV0Bool value)
	{
		val values = newBooleanArrayOfSize(size)
		Arrays.fill(values, value.data)
		return new NV1Bool(values)
	}

	private static def dispatch NablaValue buildArrayValue(int size, NV0Int value)
	{
		val values = newIntArrayOfSize(size)
		Arrays.fill(values, value.data)
		return new NV1Int(values)
	}

	private static def dispatch NablaValue buildArrayValue(int size, NV0Real value)
	{
		val values = newDoubleArrayOfSize(size)
		Arrays.fill(values, value.data)
		return new NV1Real(values)
	}

	private static def dispatch NablaValue buildArrayValue(int nbRows, int nbCols, NV0Bool value)
	{
		//If we don't precise type for values, Arrays.fill does not work ...
		val boolean[][]values = newArrayOfSize(nbRows).map[newBooleanArrayOfSize(nbCols)]
		values.forEach[x | Arrays.fill(x, value.data)]
		return new NV2Bool(values)
	}

	private static def dispatch NablaValue buildArrayValue(int nbRows, int nbCols, NV0Int value)
	{
		//If we don't precise type for values, Arrays.fill does not work ...
		val int[][] values = newArrayOfSize(nbRows).map[newIntArrayOfSize(nbCols)]
		values.forEach[x | Arrays.fill(x, value.data)]
		return new NV2Int(values)
	}

	private static def dispatch NablaValue buildArrayValue(int nbRows, int nbCols, NV0Real value)
	{
		//If we don't precise type for values, Arrays.fill does not work ...
		val double[][] values = newArrayOfSize(nbRows).map[newDoubleArrayOfSize(nbCols)]
		values.forEach[x | Arrays.fill(x, value.data)]
		return new NV2Real(values)
	}

	private static def computeUnaryMinus(NV2Int a)
	{
		val int[][] res = newArrayOfSize(a.nbRows).map[x | newIntArrayOfSize(a.nbCols)]
		for (i : 0..<a.nbRows)
			for (j : 0..<a.nbCols)
				res.get(i).set(j, -a.data.get(i).get(j))
		return new NV2Int(res)
	}

	private static def computeUnaryMinus(NV2Real a)
	{
		val double[][] res = newArrayOfSize(a.nbRows).map[x | newDoubleArrayOfSize(a.nbCols)]
		for (i : 0..<a.nbRows)
			for (j : 0..<a.nbCols)
				res.get(i).set(j, -a.data.get(i).get(j))
		return new NV2Real(res)
	}
}