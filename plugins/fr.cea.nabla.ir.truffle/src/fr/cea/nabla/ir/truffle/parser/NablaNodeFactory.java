/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.cea.nabla.ir.truffle.parser;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;

import fr.cea.nabla.ir.IrTypeExtensions;
import fr.cea.nabla.ir.generator.Utils;
import fr.cea.nabla.ir.ir.Affectation;
import fr.cea.nabla.ir.ir.Arg;
import fr.cea.nabla.ir.ir.ArgOrVar;
import fr.cea.nabla.ir.ir.ArgOrVarRef;
import fr.cea.nabla.ir.ir.BaseType;
import fr.cea.nabla.ir.ir.BaseTypeConstant;
import fr.cea.nabla.ir.ir.BeforeTimeLoopJob;
import fr.cea.nabla.ir.ir.BinaryExpression;
import fr.cea.nabla.ir.ir.BoolConstant;
import fr.cea.nabla.ir.ir.ContractedIf;
import fr.cea.nabla.ir.ir.Expression;
import fr.cea.nabla.ir.ir.Function;
import fr.cea.nabla.ir.ir.FunctionCall;
import fr.cea.nabla.ir.ir.If;
import fr.cea.nabla.ir.ir.Instruction;
import fr.cea.nabla.ir.ir.InstructionBlock;
import fr.cea.nabla.ir.ir.InstructionJob;
import fr.cea.nabla.ir.ir.IntConstant;
import fr.cea.nabla.ir.ir.Interval;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.IrType;
import fr.cea.nabla.ir.ir.IterationBlock;
import fr.cea.nabla.ir.ir.Iterator;
import fr.cea.nabla.ir.ir.Job;
import fr.cea.nabla.ir.ir.Loop;
import fr.cea.nabla.ir.ir.MaxConstant;
import fr.cea.nabla.ir.ir.MinConstant;
import fr.cea.nabla.ir.ir.Parenthesis;
import fr.cea.nabla.ir.ir.PrimitiveType;
import fr.cea.nabla.ir.ir.RealConstant;
import fr.cea.nabla.ir.ir.Return;
import fr.cea.nabla.ir.ir.SimpleVariable;
import fr.cea.nabla.ir.ir.SizeType;
import fr.cea.nabla.ir.ir.SizeTypeInt;
import fr.cea.nabla.ir.ir.SizeTypeOperation;
import fr.cea.nabla.ir.ir.SizeTypeSymbol;
import fr.cea.nabla.ir.ir.SizeTypeSymbolRef;
import fr.cea.nabla.ir.ir.TimeLoopJob;
import fr.cea.nabla.ir.ir.UnaryExpression;
import fr.cea.nabla.ir.ir.Variable;
import fr.cea.nabla.ir.ir.VariablesDefinition;
import fr.cea.nabla.ir.ir.VectorConstant;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.nodes.NablaFunctionNode;
import fr.cea.nabla.ir.truffle.nodes.NablaModuleNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaContractedIfNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalBiFunctionCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalFunctionCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalSupplierCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaFunctionCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaParenthesisNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadArgumentNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadArgumentNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadArrayNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaAddNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaAndNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaDivNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaEqNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaGeqNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaGtNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaLeqNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaLtNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaModNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaMulNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaNeqNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaOrNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.binary.NablaSubNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool1ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool2ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBoolConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt1ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt2ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaIntConstantNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaIntConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal1ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal2ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaRealConstantNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaRealConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.unary.NablaMinusNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.unary.NablaNotNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaIfNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionBlockNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaLoopNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaReturnNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.job.NablaBeforeTimeLoopJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaInstructionJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaTimeLoopJobNode;
import fr.cea.nabla.ir.truffle.values.FunctionCallHelper;

public class NablaNodeFactory {

	static class LexicalScope {
		protected final Map<String, FrameSlot> locals;
		protected final LexicalScope outer;
		protected final FrameDescriptor descriptor;

		LexicalScope(LexicalScope outer) {
			this(outer, false);
		}

		LexicalScope(LexicalScope outer, boolean newDescriptor) {
			this.outer = outer;
			this.locals = new HashMap<>();
			if (outer != null) {
				locals.putAll(outer.locals);
			}
			if (newDescriptor || outer == null) {
				this.descriptor = new FrameDescriptor();
			} else {
				this.descriptor = outer.descriptor;
			}
		}
	}

	private final Map<Function, NablaFunctionNode> functions = new HashMap<>();

	private final NablaLanguage language;
	private LexicalScope lexicalScope;

	public NablaNodeFactory(NablaLanguage language) {
		this.language = language;
	}

	private NablaExpressionNode createBaseTypeConstantNode(BaseTypeConstant baseTypeConstant) {
		final IrType type = baseTypeConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			final BaseType baseType = (BaseType) type;
			final List<NablaExpressionNode> sizes = baseType.getSizes().stream().map(s -> createNablaSizeTypeNode(s))
					.collect(Collectors.toList());
			final NablaExpressionNode value = createNablaExpressionNode(baseTypeConstant.getValue());
			switch (baseType.getPrimitive()) {
			case BOOL:
				if (sizes == null) {
					return value;
				}
				switch (sizes.size()) {
				case 0:
					return value;
				case 1:
					return NablaBool1ConstantNodeGen.create(value, sizes.get(0));
				case 2:
					return NablaBool2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
				default:
					throw new UnsupportedOperationException();
				}
			case INT:
				if (sizes == null) {
					return value;
				}
				switch (sizes.size()) {
				case 0:
					return value;
				case 1:
					return NablaInt1ConstantNodeGen.create(value, sizes.get(0));
				case 2:
					return NablaInt2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
				default:
					throw new UnsupportedOperationException();
				}
			case REAL:
				if (sizes == null) {
					return value;
				}
				switch (sizes.size()) {
				case 0:
					return value;
				case 1:
					return NablaReal1ConstantNodeGen.create(value, sizes.get(0));
				case 2:
					return NablaReal2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
				default:
					throw new UnsupportedOperationException();
				}
			default:
				throw new UnsupportedOperationException();
			}
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaDefaultValueNode(BaseType baseType) {
		final List<NablaExpressionNode> sizes = baseType.getSizes().stream().map(s -> createNablaSizeTypeNode(s))
				.collect(Collectors.toList());
		switch (baseType.getPrimitive()) {
		case BOOL: {
			final NablaExpressionNode value = createNablaBoolConstantNode(false);
			if (sizes == null) {
				return value;
			}
			switch (sizes.size()) {
			case 0:
				return value;
			case 1:
				return NablaBool1ConstantNodeGen.create(value, sizes.get(0));
			case 2:
				return NablaBool2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
			default:
				throw new UnsupportedOperationException();
			}
		}
		case INT: {
			final NablaExpressionNode value = createNablaIntConstantNode(0);
			if (sizes == null) {
				return value;
			}
			switch (sizes.size()) {
			case 0:
				return value;
			case 1:
				return NablaInt1ConstantNodeGen.create(value, sizes.get(0));
			case 2:
				return NablaInt2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
			default:
				throw new UnsupportedOperationException();
			}
		}
		case REAL: {
			final NablaExpressionNode value = createNablaRealConstantNode(0);
			if (sizes == null) {
				return value;
			}
			switch (sizes.size()) {
			case 0:
				return value;
			case 1:
				return NablaReal1ConstantNodeGen.create(value, sizes.get(0));
			case 2:
				return NablaReal2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
			default:
				throw new UnsupportedOperationException();
			}
		}
		default:
			throw new UnsupportedOperationException();
		}
	}

	private FrameDescriptor moduleFrameDescriptor; 
	
	public NablaModuleNode createModule(IrModule module) {
		assert lexicalScope == null;

		lexicalScope = new LexicalScope(lexicalScope);

		moduleFrameDescriptor = lexicalScope.descriptor;

		final String moduleName = module.getName();

		// Initializing constant slots and adding them to the finalized module.
		// FIXME isConst == default value != null? -> if yes, remove filtering on
		// non-null values
		final NablaWriteVariableNode[] constantDeclarations = module.getVariables().stream()
				.filter(v -> v instanceof SimpleVariable && v.isConst()).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		// Initializing variable slots and keeping variables with a default value to add
		// them to the finalized module.
		final NablaWriteVariableNode[] variableDeclarations = module.getVariables().stream()
				.filter(v -> v instanceof SimpleVariable && !v.isConst()).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		final NablaFunctionNode[] functionNodes = module.getFunctions().stream().filter(f -> f.getBody() != null)
				.map(f -> createNablaFunctionNode(f)).collect(Collectors.toList()).toArray(new NablaFunctionNode[0]);

		final NablaJobNode[] jobNodes = module.getJobs().stream()
				.sorted((j1, j2) -> Double.compare(j1.getAt(), j2.getAt()))
				.map(j -> createNablaJobNode(j))
				.collect(Collectors.toList()).toArray(new NablaJobNode[0]);

		final NablaModuleNode moduleNode = new NablaModuleNode(language, moduleFrameDescriptor, moduleName,
				constantDeclarations, variableDeclarations, functionNodes, jobNodes);

		return moduleNode;
	}

	private NablaJobNode createNablaJobNode(Job job) {
		final NablaJobNode result;
		switch (job.eClass().getClassifierID()) {
		case IrPackage.INSTRUCTION_JOB:
			lexicalScope = new LexicalScope(lexicalScope, true);
			result = createNablaInstructionJobNode((InstructionJob) job);
			lexicalScope = lexicalScope.outer;
			return result;
		case IrPackage.BEFORE_TIME_LOOP_JOB:
			lexicalScope = new LexicalScope(lexicalScope, true);
			result = createNablaBeforeTimeLoopJobNode((BeforeTimeLoopJob) job);
			lexicalScope = lexicalScope.outer;
			return result;
		case IrPackage.TIME_LOOP_JOB:
			lexicalScope = new LexicalScope(lexicalScope, true);
			result = createNablaTimeLoopJobNode((TimeLoopJob) job);
			lexicalScope = lexicalScope.outer;
			return result;
		}
		throw new UnsupportedOperationException();
	}

	private NablaInstructionJobNode createNablaInstructionJobNode(InstructionJob job) {
		return new NablaInstructionJobNode(language, lexicalScope.descriptor, job.getName(),
				new NablaInstructionBlockNode(createNablaInstructionNode(job.getInstruction())));
	}

	private NablaBeforeTimeLoopJobNode createNablaBeforeTimeLoopJobNode(BeforeTimeLoopJob job) {
		final NablaInstructionNode[] copyInstructions = job.getCopies().stream().map(c -> {
			final FrameSlot source = lexicalScope.locals.get(c.getSource().getName());
			final NablaReadVariableNode sourceReadNode = NablaReadVariableNodeGen.create(source);
			final FrameSlot destination = lexicalScope.locals.get(c.getDestination().getName());
			return NablaWriteVariableNodeGen.create(destination, sourceReadNode);
		}).collect(Collectors.toList()).toArray(new NablaInstructionNode[0]);
		return new NablaBeforeTimeLoopJobNode(language, lexicalScope.descriptor, job.getName(), copyInstructions);
	}
	
	//FIXME: use module frame descriptor or more local one?
	private NablaTimeLoopJobNode createNablaTimeLoopJobNode(TimeLoopJob job) {
		final String indexName = job.getTimeLoop().getIterationCounter().getName();
		final FrameSlot indexSlot = moduleFrameDescriptor.findOrAddFrameSlot(indexName, null, FrameSlotKind.Illegal);
		lexicalScope.locals.put(indexName, indexSlot);
		final List<FrameSlot[]> copies = job.getCopies().stream().map(c -> {
			final String source = c.getSource().getName();
			final String destination = c.getDestination().getName();
			final FrameSlot sourceSlot = moduleFrameDescriptor.findOrAddFrameSlot(source, null, FrameSlotKind.Illegal);
			final FrameSlot destinationSlot = moduleFrameDescriptor.findOrAddFrameSlot(destination, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(source, sourceSlot);
			lexicalScope.locals.put(destination, destinationSlot);
			return new FrameSlot[] { sourceSlot, destinationSlot };
		}).collect(Collectors.toList());
		final NablaExpressionNode conditionNode = createNablaExpressionNode(job.getTimeLoop().getWhileCondition());
		final NablaJobNode[] innerJobs = job.getInnerJobs().stream()
				.filter(j -> j.getAt() > 0)
				.sorted((j1, j2) -> Double.compare(j1.getAt(), j2.getAt()))
				.map(j -> createNablaJobNode(j))
				.collect(Collectors.toList())
				.toArray(new NablaJobNode[0]);
		return new NablaTimeLoopJobNode(language, lexicalScope.descriptor, job.getName(),
				indexSlot, copies, conditionNode, innerJobs);
	}

	private NablaExpressionNode createNablaBinaryExpressionNode(NablaExpressionNode leftNode, String operator,
			NablaExpressionNode rightNode) {
		switch (operator) {
		case "||":
			return NablaOrNodeGen.create(leftNode, rightNode);
		case "&&":
			return NablaAndNodeGen.create(leftNode, rightNode);
		case "==":
			return NablaEqNodeGen.create(leftNode, rightNode);
		case "!=":
			return NablaNeqNodeGen.create(leftNode, rightNode);
		case ">=":
			return NablaGeqNodeGen.create(leftNode, rightNode);
		case "<=":
			return NablaLeqNodeGen.create(leftNode, rightNode);
		case ">":
			return NablaGtNodeGen.create(leftNode, rightNode);
		case "<":
			return NablaLtNodeGen.create(leftNode, rightNode);
		case "+":
			return NablaAddNodeGen.create(leftNode, rightNode);
		case "-":
			return NablaSubNodeGen.create(leftNode, rightNode);
		case "*":
			return NablaMulNodeGen.create(leftNode, rightNode);
		case "/":
			return NablaDivNodeGen.create(leftNode, rightNode);
		case "%":
			return NablaModNodeGen.create(leftNode, rightNode);
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaBoolConstantNode(boolean value) {
		return NablaBoolConstantNodeGen.create(value);
	}

	private NablaContractedIfNode createNablaContractedIfNode(ContractedIf contractedIf) {
		return new NablaContractedIfNode(createNablaExpressionNode(contractedIf.getCondition()),
				createNablaExpressionNode(contractedIf.getThenExpression()),
				createNablaExpressionNode(contractedIf.getElseExpression()));
	}

	private NablaExpressionNode createNablaExpressionNode(Expression expression) {
		switch (expression.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE_CONSTANT:
			return createBaseTypeConstantNode((BaseTypeConstant) expression);
		case IrPackage.BOOL_CONSTANT:
			return createNablaBoolConstantNode(((BoolConstant) expression).isValue());
		case IrPackage.INT_CONSTANT:
			return createNablaIntConstantNode(((IntConstant) expression).getValue());
		case IrPackage.REAL_CONSTANT:
			return createNablaRealConstantNode(((RealConstant) expression).getValue());
		case IrPackage.VECTOR_CONSTANT:
			return createNablaVectorLiteralNode((VectorConstant) expression);
		case IrPackage.MAX_CONSTANT:
			return createNablaMaxConstant((MaxConstant) expression);
		case IrPackage.MIN_CONSTANT:
			return createNablaMinConstant((MinConstant) expression);
		case IrPackage.ARG_OR_VAR_REF:
			return createNablaReadArgOrVariableNode((ArgOrVarRef) expression);
		case IrPackage.PARENTHESIS:
			return createNablaParenthesisNode((Parenthesis) expression);
		case IrPackage.CONTRACTED_IF:
			return createNablaContractedIfNode((ContractedIf) expression);
		case IrPackage.FUNCTION_CALL:
			return createNablaFunctionCallNode((FunctionCall) expression);
		case IrPackage.UNARY_EXPRESSION: {
			final UnaryExpression unaryExpression = (UnaryExpression) expression;
			final NablaExpressionNode subNode = createNablaExpressionNode(unaryExpression.getExpression());
			return createNablaUnaryExpressionNode(subNode, unaryExpression.getOperator());
		}
		case IrPackage.BINARY_EXPRESSION: {
			final BinaryExpression binaryExpression = (BinaryExpression) expression;
			final NablaExpressionNode leftNode = createNablaExpressionNode(binaryExpression.getLeft());
			final NablaExpressionNode rightNode = createNablaExpressionNode(binaryExpression.getRight());
			return createNablaBinaryExpressionNode(leftNode, binaryExpression.getOperator(), rightNode);
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaFunctionCallNode(FunctionCall functionCall) {
		if (functionCall.getFunction().getBody() != null) {
			final NablaFunctionNode functionNode = functions.computeIfAbsent(functionCall.getFunction(),
					f -> createNablaFunctionNode(f));
			final NablaExpressionNode[] argNodes = functionCall.getArgs().stream()
					.map(e -> createNablaExpressionNode(e)).collect(Collectors.toList())
					.toArray(new NablaExpressionNode[0]);
			return new NablaFunctionCallNode(functionNode, argNodes);
		} else {
			return createNablaExternalFunctionCallNode(functionCall);
		}
	}

	private NablaExpressionNode createNablaExternalFunctionCallNode(FunctionCall functionCall) {
		final Function function = functionCall.getFunction();
		final IrModule module = (IrModule) function.eContainer();
		final ClassLoader tccl = Thread.currentThread().getContextClassLoader();
		final String receiverClassName = module.getName().toLowerCase() + '.' + function.getProvider()
				+ Utils.FunctionReductionPrefix;
		try {
			final Class<?> receiverClass = Class.forName(receiverClassName, true, tccl);
			final BaseType baseReturnType = function.getReturnType();
			final Class<?> javaReturnType = FunctionCallHelper.getJavaType(baseReturnType.getPrimitive(),
					IrTypeExtensions.getDimension(baseReturnType));
			final List<Class<?>> javaParameterTypes = function.getInArgs().stream().map(a -> {
				final PrimitiveType primitiveType = a.getType().getPrimitive();
				final int dimension = IrTypeExtensions.getDimension(a.getType());
				return FunctionCallHelper.getJavaType(primitiveType, dimension);
			}).collect(Collectors.toList());
			final MethodHandles.Lookup lookup = MethodHandles.lookup();
			if (javaParameterTypes.isEmpty()) {
				final MethodType methodType = MethodType.methodType(javaReturnType);
				CallSite site = LambdaMetafactory.metafactory(lookup, "get", MethodType.methodType(Supplier.class),
						methodType.generic(),
						lookup.findStatic(receiverClass, function.getName(), methodType),
						methodType);
				final java.util.function.Supplier<Object> externalSupplier = (java.util.function.Supplier<Object>) site
						.getTarget().invokeExact();
				return new NablaExternalSupplierCallNode(externalSupplier);
			} else if (javaParameterTypes.size() == 1) {
				final MethodType methodType = MethodType.methodType(javaReturnType, javaParameterTypes.get(0));
				CallSite site = LambdaMetafactory.metafactory(lookup, "apply",
						MethodType.methodType(java.util.function.Function.class), methodType.generic(),
						lookup.findStatic(receiverClass, function.getName(), methodType), methodType);
				final java.util.function.Function<Object, Object> externalFunction = (java.util.function.Function<Object, Object>) site
						.getTarget().invokeExact();
				final NablaExpressionNode argNode = functionCall.getArgs().stream()
						.map(e -> createNablaExpressionNode(e)).findFirst().orElse(null);
				return new NablaExternalFunctionCallNode(externalFunction, argNode);
			} else {
				final MethodType methodType = MethodType.methodType(javaReturnType, javaParameterTypes.get(0),
						javaParameterTypes.get(1));
				CallSite site = LambdaMetafactory.metafactory(lookup, "apply", MethodType.methodType(BiFunction.class),
						methodType.generic(), lookup.findStatic(receiverClass, function.getName(), methodType),
						methodType);
				final BiFunction<Object, Object, Object> externalFunction = (BiFunction<Object, Object, Object>) site
						.getTarget().invokeExact();
				final NablaExpressionNode[] argNodes = functionCall.getArgs().stream()
						.map(e -> createNablaExpressionNode(e)).collect(Collectors.toList())
						.toArray(new NablaExpressionNode[0]);
				return new NablaExternalBiFunctionCallNode(externalFunction, argNodes);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new UnsupportedOperationException();
		}
	}

	private String getSizeTypeName(SizeType sizeType) {
		switch (sizeType.eClass().getClassifierID()) {
		case IrPackage.SIZE_TYPE_INT:
			return "";
		case IrPackage.SIZE_TYPE_SYMBOL:
			return ((SizeTypeSymbol) sizeType).getName();
		case IrPackage.SIZE_TYPE_SYMBOL_REF:
			return ((SizeTypeSymbolRef) sizeType).getTarget().getName();
		}
		throw new IllegalArgumentException();
	}

	private NablaFunctionNode createNablaFunctionNode(Function function) {
		lexicalScope = new LexicalScope(lexicalScope, true);
		final List<NablaInstructionNode> functionInstructions = new ArrayList<>();
		final Set<String> sizeVarSet = new HashSet<>();
		int nbSizeVars = function.getVariables().size();
		for (int i = 0; i < nbSizeVars; i++) {
			final String varName = function.getVariables().get(i).getName();
			sizeVarSet.add(varName);
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(varName, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(varName, frameSlot);
		}

		int nbArgs = function.getInArgs().size();
		for (int i = 0; i < nbArgs; i++) {
			final Arg arg = function.getInArgs().get(i);
			boolean[] providesSizes = new boolean[] { false };
			final FrameSlot[] sizeSlots = arg.getType().getSizes().stream().map(s -> getSizeTypeName(s)).map(s -> {
				if (sizeVarSet.remove(s)) {
					providesSizes[0] = true;
					return lexicalScope.locals.get(s);
				}
				return null;
			}).collect(Collectors.toList()).toArray(new FrameSlot[0]);
			final String argName = arg.getName();
			final NablaReadArgumentNode readArg;
			if (providesSizes[0]) {
				readArg = NablaReadArgumentNodeGen.create(i, sizeSlots);
			} else {
				readArg = NablaReadArgumentNodeGen.create(i);
			}
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(argName, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(argName, frameSlot);
			functionInstructions.add(createNablaWriteVariableNode(argName, readArg, i));
		}
		functionInstructions.addAll(Arrays.asList(createNablaInstructionNode(function.getBody())));
		final NablaInstructionNode bodyNode = new NablaInstructionBlockNode(
				functionInstructions.toArray(new NablaInstructionNode[0]));
		final NablaFunctionNode result = new NablaFunctionNode(language, lexicalScope.descriptor, bodyNode, function.getName());
		lexicalScope = lexicalScope.outer;
		return result;
	}

	private NablaInstructionNode createNablaInstructionBlockNode(InstructionBlock instructionBlock) {
		lexicalScope = new LexicalScope(lexicalScope);
		final NablaInstructionNode[] instructions = instructionBlock.getInstructions().stream()
				.flatMap(i -> Arrays.stream(createNablaInstructionNode(i))).collect(Collectors.toList())
				.toArray(new NablaInstructionNode[0]);
		lexicalScope = lexicalScope.outer;
		return new NablaInstructionBlockNode(instructions);
	}

//	private NablaInstructionNode createNablaWriteArrayNode() {
//		switch (baseType.getPrimitive()) {
//		case BOOL:
//			if (sizes == null) {
//				return value;
//			}
//			switch (sizes.size()) {
//			case 0:
//				return value;
//			case 1:
//				return NablaBool1ConstantNodeGen.create(value, sizes.get(0));
//			case 2:
//				return NablaBool2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
//			default:
//				throw new UnsupportedOperationException();
//			}
//		case INT:
//			if (sizes == null) {
//				return value;
//			}
//			switch (sizes.size()) {
//			case 0:
//				return value;
//			case 1:
//				return NablaInt1ConstantNodeGen.create(value, sizes.get(0));
//			case 2:
//				return NablaInt2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
//			default:
//				throw new UnsupportedOperationException();
//			}
//		case REAL:
//			if (sizes == null) {
//				return value;
//			}
//			switch (sizes.size()) {
//			case 0:
//				return value;
//			case 1:
//				return NablaReal1ConstantNodeGen.create(value, sizes.get(0));
//			case 2:
//				return NablaReal2ConstantNodeGen.create(value, sizes.toArray(new NablaExpressionNode[0]));
//			default:
//				throw new UnsupportedOperationException();
//			}
//		default:
//			throw new UnsupportedOperationException();
//		}
//	}

	private NablaInstructionNode[] createNablaInstructionNode(Instruction instruction) {
		switch (instruction.eClass().getClassifierID()) {
		case IrPackage.AFFECTATION: {
			final Affectation affectation = (Affectation) instruction;
			final NablaExpressionNode right = createNablaExpressionNode(affectation.getRight());
			final ArgOrVarRef argOrVarRef = affectation.getLeft();
			final ArgOrVar argOrVar = argOrVarRef.getTarget();
			final String name = argOrVar.getName();
			if (argOrVarRef.getIndices().isEmpty() && argOrVarRef.getIterators().isEmpty()) {
				return new NablaInstructionNode[] { createNablaWriteVariableNode(name, right) };
			} else {
				final NablaExpressionNode[] indices = argOrVarRef.getIndices().stream()
						.map(s -> createNablaSizeTypeNode(s)).collect(Collectors.toList())
						.toArray(new NablaExpressionNode[0]);
				return new NablaInstructionNode[] { createNablaWriteArrayNode(name, indices, right) };
			}
		}
		case IrPackage.IF:
			return new NablaInstructionNode[] { createNablaIfNode((If) instruction) };
		case IrPackage.INSTRUCTION_BLOCK:
			return new NablaInstructionNode[] { createNablaInstructionBlockNode((InstructionBlock) instruction) };
		case IrPackage.ITERABLE_INSTRUCTION:
			throw new UnsupportedOperationException();
		case IrPackage.LOOP:
			return new NablaInstructionNode[] { createNablaLoopNode((Loop) instruction) };
		case IrPackage.RETURN:
			return new NablaInstructionNode[] { createNablaReturnNode((Return) instruction) };
		case IrPackage.VARIABLES_DEFINITION:
			final VariablesDefinition varDefinition = (VariablesDefinition) instruction;
			final NablaInstructionNode[] varDefs = varDefinition.getVariables().stream()
					.map(v -> createVariableDeclaration(v)).filter(n -> n != null).collect(Collectors.toList())
					.toArray(new NablaInstructionNode[0]);
			return varDefs;
		}
		throw new UnsupportedOperationException();
	}

	private NablaInstructionNode createNablaReturnNode(Return ret) {
		return NablaReturnNodeGen.create(createNablaExpressionNode(ret.getExpression()));
	}

	private NablaInstructionNode createNablaLoopNode(Loop loop) {
		final IterationBlock iterationBlock = loop.getIterationBlock();
		switch (iterationBlock.eClass().getClassifierID()) {
		case IrPackage.INTERVAL:
			lexicalScope = new LexicalScope(lexicalScope);
			final Interval interval = (Interval) iterationBlock;
			final String indexName = interval.getIndex().getName();
			final FrameSlot indexSlot = lexicalScope.descriptor.findOrAddFrameSlot(indexName, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(indexName, indexSlot);
			final NablaInstructionNode bodyNode = new NablaInstructionBlockNode(
					createNablaInstructionNode(loop.getBody()));
			final NablaExpressionNode iterationCount = createNablaSizeTypeNode(interval.getNbElems());
			final NablaInstructionNode result = new NablaLoopNode(indexSlot, iterationCount, bodyNode);
			lexicalScope = lexicalScope.outer;
			return result;
		case IrPackage.ITERATOR:
			lexicalScope = new LexicalScope(lexicalScope);
			final Iterator iterator = (Iterator) iterationBlock;
//			final String indexName = spaceIterationBlock.get
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaIfNode createNablaIfNode(If ifInstruction) {
		final NablaExpressionNode conditionNode = createNablaExpressionNode(ifInstruction.getCondition());
		final NablaInstructionNode thenNode = new NablaInstructionBlockNode(
				createNablaInstructionNode(ifInstruction.getThenInstruction()));
		final NablaInstructionNode elseNode = new NablaInstructionBlockNode(
				createNablaInstructionNode(ifInstruction.getElseInstruction()));
		return new NablaIfNode(conditionNode, thenNode, elseNode);
	}

	private NablaIntConstantNode createNablaIntConstantNode(int value) {
		return NablaIntConstantNodeGen.create(value);
	}

	private NablaExpressionNode createNablaMaxConstant(MaxConstant maxConstant) {
		final IrType type = maxConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			final BaseType baseType = (BaseType) type;
			switch (baseType.getPrimitive()) {
			case BOOL:
				throw new UnsupportedOperationException();
			case INT:
				return createNablaIntConstantNode(Integer.MAX_VALUE);
			case REAL:
				return createNablaRealConstantNode(Double.MAX_VALUE);
			}
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaMinConstant(MinConstant minConstant) {
		final IrType type = minConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			final BaseType baseType = (BaseType) type;
			switch (baseType.getPrimitive()) {
			case BOOL:
				throw new UnsupportedOperationException();
			case INT:
				return createNablaIntConstantNode(Integer.MIN_VALUE);
			case REAL:
				return createNablaRealConstantNode(Double.MIN_VALUE);
			}
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaParenthesisNode createNablaParenthesisNode(Parenthesis expression) {
		return new NablaParenthesisNode(createNablaExpressionNode(expression.getExpression()));
	}

	private NablaExpressionNode createNablaReadArgOrVariableNode(ArgOrVarRef ref) {
		final ArgOrVar target = ref.getTarget();
		switch (target.eClass().getClassifierID()) {
		case IrPackage.ARG:
		case IrPackage.SIMPLE_VARIABLE: {
			final FrameSlot frameSlot = lexicalScope.locals.get(target.getName());
			if (ref.getIndices().isEmpty()) {
				return NablaReadVariableNodeGen.create(frameSlot);
			} else {
				final NablaExpressionNode[] indices = ref.getIndices().stream().map(s -> createNablaSizeTypeNode(s))
						.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);
				return NablaReadArrayNodeGen.create(indices, NablaReadVariableNodeGen.create(frameSlot));
			}
		}
		case IrPackage.CONNECTIVITY_VARIABLE: {
			throw new UnsupportedOperationException();
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaReadVariableNode createNablaReadSizeTypeSymbolNode(SizeTypeSymbol symbol) {
		final String name = symbol.getName();
		final FrameSlot frameSlot = lexicalScope.locals.get(name);
		assert (frameSlot != null);
		return NablaReadVariableNodeGen.create(frameSlot);
	}

	private NablaRealConstantNode createNablaRealConstantNode(double value) {
		return NablaRealConstantNodeGen.create(value);
	}

	private NablaExpressionNode createNablaSizeTypeNode(SizeType sizeType) {
		switch (sizeType.eClass().getClassifierID()) {
		case IrPackage.SIZE_TYPE_INT:
			return createNablaIntConstantNode(((SizeTypeInt) sizeType).getValue());
		case IrPackage.SIZE_TYPE_SYMBOL:
			return createNablaReadSizeTypeSymbolNode((SizeTypeSymbol) sizeType);
		case IrPackage.SIZE_TYPE_OPERATION: {
			final SizeTypeOperation operation = (SizeTypeOperation) sizeType;
			final NablaExpressionNode leftNode = createNablaSizeTypeNode(operation.getLeft());
			final NablaExpressionNode rightNode = createNablaSizeTypeNode(operation.getRight());
			return createNablaBinaryExpressionNode(leftNode, ((SizeTypeOperation) sizeType).getOperator(), rightNode);
		}
		case IrPackage.SIZE_TYPE_SYMBOL_REF:
			return createNablaReadSizeTypeSymbolNode(((SizeTypeSymbolRef) sizeType).getTarget());
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaUnaryExpressionNode(NablaExpressionNode subNode, String operator) {
		switch (operator) {
		case "!":
			return NablaNotNodeGen.create(subNode);
		case "-":
			return NablaMinusNodeGen.create(subNode);
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaVectorLiteralNode(VectorConstant vectorConstant) {
		final IrType type = vectorConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			final BaseType baseType = (BaseType) type;
			final NablaExpressionNode[] values = vectorConstant.getValues().stream()
					.map(e -> createNablaExpressionNode(e)).collect(Collectors.toList())
					.toArray(new NablaExpressionNode[0]);
			final NablaExpressionNode[] dimensions = baseType.getSizes().stream().map(s -> createNablaSizeTypeNode(s))
					.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);

			switch (baseType.getPrimitive()) {
			case BOOL:
				switch (dimensions.length) {
				case 1:
					return NablaBool1NodeGen.create(values);
				case 2:
					return NablaBool2NodeGen.create(values, dimensions);
				default:
					throw new UnsupportedOperationException();
				}
			case INT:
				switch (dimensions.length) {
				case 1:
					return NablaInt1NodeGen.create(values);
				case 2:
					return NablaInt2NodeGen.create(values, dimensions);
				default:
					throw new UnsupportedOperationException();
				}
			case REAL:
				switch (dimensions.length) {
				case 1:
					return NablaReal1NodeGen.create(values);
				case 2:
					return NablaReal2NodeGen.create(values, dimensions);
				default:
					throw new UnsupportedOperationException();
				}
			}
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaWriteVariableNode createNablaWriteVariableNode(String name, NablaExpressionNode value) {
		return createNablaWriteVariableNode(name, value, null);
	}

	private NablaWriteVariableNode createNablaWriteVariableNode(String name, NablaExpressionNode value,
			Integer paramaterIndex) {
		final FrameSlot frameSlot = lexicalScope.locals.get(name);
		return NablaWriteVariableNodeGen.create(frameSlot, value);
	}

	private NablaWriteArrayNode createNablaWriteArrayNode(String name, NablaExpressionNode[] indices,
			NablaExpressionNode value) {
		return createNablaWriteArrayNode(name, indices, value, null);
	}

	private NablaWriteArrayNode createNablaWriteArrayNode(String name, NablaExpressionNode[] indices,
			NablaExpressionNode value, Integer paramaterIndex) {
		final FrameSlot frameSlot = lexicalScope.locals.get(name);
		return NablaWriteArrayNodeGen.create(frameSlot, indices, value);
	}

	private NablaWriteVariableNode createVariableDeclaration(Variable variable) {
		switch (variable.eClass().getClassifierID()) {
		case IrPackage.SIMPLE_VARIABLE: {
			final SimpleVariable simpleVariable = (SimpleVariable) variable;
			final String name = simpleVariable.getName();
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(name, frameSlot);
			final Expression defaultValue = simpleVariable.getDefaultValue();
			if (defaultValue != null) {
				final NablaExpressionNode truffleDefaultValue = createNablaExpressionNode(defaultValue);
				return NablaWriteVariableNodeGen.create(frameSlot, truffleDefaultValue);
			} else {
				final NablaExpressionNode truffleDefaultValue = createNablaDefaultValueNode(simpleVariable.getType());
				return NablaWriteVariableNodeGen.create(frameSlot, truffleDefaultValue);
			}
		}
		case IrPackage.CONNECTIVITY_VARIABLE: {
			throw new UnsupportedOperationException();
		}
		}
		throw new UnsupportedOperationException();
	}
}
