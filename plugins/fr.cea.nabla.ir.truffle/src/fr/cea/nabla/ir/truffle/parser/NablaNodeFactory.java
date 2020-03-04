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

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;

import fr.cea.nabla.ir.ir.ArgOrVar;
import fr.cea.nabla.ir.ir.ArgOrVarRef;
import fr.cea.nabla.ir.ir.BaseType;
import fr.cea.nabla.ir.ir.BaseTypeConstant;
import fr.cea.nabla.ir.ir.BinaryExpression;
import fr.cea.nabla.ir.ir.BoolConstant;
import fr.cea.nabla.ir.ir.Expression;
import fr.cea.nabla.ir.ir.IntConstant;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.IrType;
import fr.cea.nabla.ir.ir.RealConstant;
import fr.cea.nabla.ir.ir.SimpleVariable;
import fr.cea.nabla.ir.ir.SizeType;
import fr.cea.nabla.ir.ir.SizeTypeInt;
import fr.cea.nabla.ir.ir.SizeTypeOperation;
import fr.cea.nabla.ir.ir.SizeTypeSymbol;
import fr.cea.nabla.ir.ir.Variable;
import fr.cea.nabla.ir.ir.VectorConstant;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.nodes.NablaFunctionNode;
import fr.cea.nabla.ir.truffle.nodes.NablaJobNode;
import fr.cea.nabla.ir.truffle.nodes.NablaModuleNode;
import fr.cea.nabla.ir.truffle.nodes.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.nodes.NablaWriteVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBool2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaBoolConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaInt2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaIntConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReadVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal1NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaReal2NodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaRealConstantNodeGen;
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

public class NablaNodeFactory {

	static class LexicalScope {
		protected final LexicalScope outer;
		protected final Map<String, FrameSlot> locals;

		LexicalScope(LexicalScope outer) {
			this.outer = outer;
			this.locals = new HashMap<>();
			if (outer != null) {
				locals.putAll(outer.locals);
			}
		}
	}

	/* State while parsing an ir model. */
	private FrameDescriptor frameDescriptor;
	private final Map<String, RootCallTarget> callTargets;

	/* State while parsing a function. */
//    private String functionName;
//    private int functionBodyStartPos; // includes parameter list
//    private int parameterCount;
//    private List<NablaInstructionNode> methodNodes;

	/* State while parsing a block. */
	private LexicalScope lexicalScope;
	private final NablaLanguage language;

	public NablaNodeFactory(NablaLanguage language, Map<String, RootCallTarget> callTargets) {
		this.language = language;
		this.callTargets = callTargets;
	}

	public NablaModuleNode createModule(IrModule module) {
		assert lexicalScope == null;

		frameDescriptor = new FrameDescriptor();
		lexicalScope = new LexicalScope(lexicalScope);

		final FrameDescriptor moduleFrameDescriptor = frameDescriptor;

		final String moduleName = module.getName();

		final NablaWriteVariableNode[] constantDeclarations = module.getVariables().stream()
				.filter(v -> v instanceof SimpleVariable && v.isConst()).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		final NablaWriteVariableNode[] variableDeclarations = module.getVariables().stream()
				.filter(v -> v instanceof SimpleVariable && !v.isConst()).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		final NablaFunctionNode[] functionNodes = new NablaFunctionNode[module.getFunctions().size()];

		final NablaJobNode[] jobNodes = new NablaJobNode[module.getJobs().size()];

		final NablaModuleNode moduleNode = new NablaModuleNode(language, moduleFrameDescriptor, moduleName,
				constantDeclarations, variableDeclarations, functionNodes, jobNodes);

		return moduleNode;
	}

	private NablaWriteVariableNode createVariableDeclaration(Variable variable) {
		switch (variable.eClass().getClassifierID()) {
		case IrPackage.SIMPLE_VARIABLE: {
			final SimpleVariable simpleVariable = (SimpleVariable) variable;
			final String name = simpleVariable.getName();
			final FrameSlot frameSlot = frameDescriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(name, frameSlot);
			final Expression defaultValue = simpleVariable.getDefaultValue();
			if (defaultValue != null) {
				final NablaExpressionNode truffleDefaultValue = createNablaExpressionNode(defaultValue);
				return NablaWriteVariableNodeGen.create(truffleDefaultValue, frameSlot);
			}
			return null;
		}
		case IrPackage.CONNECTIVITY_VARIABLE: {
			throw new UnsupportedOperationException();
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaExpressionNode(Expression expression) {
		switch (expression.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE_CONSTANT:
			return createBaseTypeConstantNode((BaseTypeConstant) expression);
		case IrPackage.BOOL_CONSTANT:
			return createNablaBoolConstantNode((BoolConstant) expression);
		case IrPackage.INT_CONSTANT:
			return createNablaIntConstantNode(((IntConstant) expression).getValue());
		case IrPackage.REAL_CONSTANT:
			return createNablaRealConstantNode((RealConstant) expression);
		case IrPackage.VECTOR_CONSTANT:
			return createNablaVectorLiteralNode((VectorConstant) expression);
		case IrPackage.ARG_OR_VAR_REF:
			return createNablaReadArgOrVariableNode((ArgOrVarRef) expression);
		case IrPackage.BINARY_EXPRESSION: {
			final BinaryExpression binaryExpression = (BinaryExpression) expression;
			final NablaExpressionNode leftNode = createNablaExpressionNode(binaryExpression.getLeft());
			final NablaExpressionNode rightNode = createNablaExpressionNode(binaryExpression.getRight());
			return createNablaBinaryExpressionNode(leftNode, binaryExpression.getOperator(), rightNode);
		}
		}
		throw new UnsupportedOperationException();
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
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createBaseTypeConstantNode(BaseTypeConstant baseTypeConstant) {
		final IrType type = baseTypeConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			throw new UnsupportedOperationException();
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaBoolConstantNode(BoolConstant boolConstant) {
		return NablaBoolConstantNodeGen.create(boolConstant);
	}

	private NablaExpressionNode createNablaIntConstantNode(int value) {
		return NablaIntConstantNodeGen.create(value);
	}

	private NablaExpressionNode createNablaRealConstantNode(RealConstant realConstant) {
		return NablaRealConstantNodeGen.create(realConstant);
	}
	
	private NablaExpressionNode createNablaVectorLiteralNode(VectorConstant vectorConstant) {
		final IrType type = vectorConstant.getType();
		switch (type.eClass().getClassifierID()) {
		case IrPackage.BASE_TYPE: {
			final BaseType baseType = (BaseType) type;
			final NablaExpressionNode[] values = vectorConstant.getValues().stream().map(e -> createNablaExpressionNode(e))
					.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);
			final NablaExpressionNode[] dimensions = baseType.getSizes().stream().map(s -> createNablaSizeTypeNode(s))
					.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);
			
			switch (baseType.getPrimitive()) {
			case BOOL:
				switch (dimensions.length) {
				case 1: return NablaBool1NodeGen.create(values);
				case 2: return NablaBool2NodeGen.create(values, dimensions);
				default: throw new UnsupportedOperationException();
				}
			case INT:
				switch (dimensions.length) {
				case 1: return NablaInt1NodeGen.create(values);
				case 2: return NablaInt2NodeGen.create(values, dimensions);
				default: throw new UnsupportedOperationException();
				}
			case REAL:
				switch (dimensions.length) {
				case 1: return NablaReal1NodeGen.create(values);
				case 2: return NablaReal2NodeGen.create(values, dimensions);
				default: throw new UnsupportedOperationException();
				}
			default: throw new UnsupportedOperationException();
			}
		}
		case IrPackage.CONNECTIVITY_TYPE:
			throw new UnsupportedOperationException();
		}
		throw new UnsupportedOperationException();
	}

	private NablaExpressionNode createNablaReadArgOrVariableNode(ArgOrVarRef ref) {
		final ArgOrVar target = ref.getTarget();
		switch (target.eClass().getClassifierID()) {
		case IrPackage.SIMPLE_VARIABLE: {
			final SimpleVariable simpleVariable = (SimpleVariable) target;
			final String name = simpleVariable.getName();
			final FrameSlot frameSlot = frameDescriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(name, frameSlot);
			return NablaReadVariableNodeGen.create(frameSlot);
		}
		case IrPackage.CONNECTIVITY_VARIABLE: {
			throw new UnsupportedOperationException();
		}
		}
		throw new UnsupportedOperationException();
	}
	
	private NablaExpressionNode createNablaReadSizeTypeSymbolNode(SizeTypeSymbol symbol) {
		final String name = symbol.getName();
		final FrameSlot frameSlot = frameDescriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
		lexicalScope.locals.put(name, frameSlot);
		return NablaReadVariableNodeGen.create(frameSlot);
	}

	private NablaExpressionNode createNablaBinaryExpressionNode(NablaExpressionNode leftNode, String operator, NablaExpressionNode rightNode) {
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

//	public void startFunction(Function function) {
//		assert parameterCount == 0;
//		assert frameDescriptor == null;
//		assert lexicalScope == null;
//
//		functionStartPos = nameToken.getStartIndex();
//		functionName = nameToken.getText();
//		functionBodyStartPos = bodyStartToken.getStartIndex();
//		frameDescriptor = new FrameDescriptor();
//		methodNodes = new ArrayList<>();
//		startBlock();
//	}
//
//	public void addFormalParameter(Token nameToken) {
//		/*
//		 * Method parameters are assigned to local variables at the beginning of the
//		 * method. This ensures that accesses to parameters are specialized the same way
//		 * as local variables are specialized.
//		 */
//		final SLReadArgumentNode readArg = new SLReadArgumentNode(parameterCount);
//		SLExpressionNode assignment = createAssignment(createStringLiteral(nameToken, false), readArg, parameterCount);
//		methodNodes.add(assignment);
//		parameterCount++;
//	}
//
//	public void finishFunction(SLStatementNode bodyNode) {
//		if (bodyNode == null) {
//			// a state update that would otherwise be performed by finishBlock
//			lexicalScope = lexicalScope.outer;
//		} else {
//			methodNodes.add(bodyNode);
//			final int bodyEndPos = bodyNode.getSourceEndIndex();
//			final SourceSection functionSrc = source.createSection(functionStartPos, bodyEndPos - functionStartPos);
//			final SLStatementNode methodBlock = finishBlock(methodNodes, functionBodyStartPos,
//					bodyEndPos - functionBodyStartPos);
//			assert lexicalScope == null : "Wrong scoping of blocks in parser";
//
//			final SLFunctionBodyNode functionBodyNode = new SLFunctionBodyNode(methodBlock);
//			functionBodyNode.setSourceSection(functionSrc.getCharIndex(), functionSrc.getCharLength());
//
//			final SLRootNode rootNode = new SLRootNode(language, frameDescriptor, functionBodyNode, functionSrc,
//					functionName);
//			allFunctions.put(functionName, Truffle.getRuntime().createCallTarget(rootNode));
//		}
//
//		functionStartPos = 0;
//		functionName = null;
//		functionBodyStartPos = 0;
//		parameterCount = 0;
//		frameDescriptor = null;
//		lexicalScope = null;
//	}
//
//	public void startBlock() {
//		lexicalScope = new LexicalScope(lexicalScope);
//	}
//
//	public SLStatementNode finishBlock(List<SLStatementNode> bodyNodes, int startPos, int length) {
//		lexicalScope = lexicalScope.outer;
//
//		if (containsNull(bodyNodes)) {
//			return null;
//		}
//
//		List<SLStatementNode> flattenedNodes = new ArrayList<>(bodyNodes.size());
//		flattenBlocks(bodyNodes, flattenedNodes);
//		for (SLStatementNode statement : flattenedNodes) {
//			if (statement.hasSource() && !isHaltInCondition(statement)) {
//				statement.addStatementTag();
//			}
//		}
//		SLBlockNode blockNode = new SLBlockNode(flattenedNodes.toArray(new SLStatementNode[flattenedNodes.size()]));
//		blockNode.setSourceSection(startPos, length);
//		return blockNode;
//	}
//
//	private static boolean isHaltInCondition(SLStatementNode statement) {
//		return (statement instanceof SLIfNode) || (statement instanceof SLWhileNode);
//	}
//
//	private void flattenBlocks(Iterable<? extends SLStatementNode> bodyNodes, List<SLStatementNode> flattenedNodes) {
//		for (SLStatementNode n : bodyNodes) {
//			if (n instanceof SLBlockNode) {
//				flattenBlocks(((SLBlockNode) n).getStatements(), flattenedNodes);
//			} else {
//				flattenedNodes.add(n);
//			}
//		}
//	}
//
//	/**
//	 * Returns an {@link SLDebuggerNode} for the given token.
//	 *
//	 * @param debuggerToken The token containing the debugger node's info.
//	 * @return A SLDebuggerNode for the given token.
//	 */
//	SLStatementNode createDebugger(Token debuggerToken) {
//		final SLDebuggerNode debuggerNode = new SLDebuggerNode();
//		srcFromToken(debuggerNode, debuggerToken);
//		return debuggerNode;
//	}
//
//	/**
//	 * Returns an {@link SLBreakNode} for the given token.
//	 *
//	 * @param breakToken The token containing the break node's info.
//	 * @return A SLBreakNode for the given token.
//	 */
//	public SLStatementNode createBreak(Token breakToken) {
//		final SLBreakNode breakNode = new SLBreakNode();
//		srcFromToken(breakNode, breakToken);
//		return breakNode;
//	}
//
//	/**
//	 * Returns an {@link SLContinueNode} for the given token.
//	 *
//	 * @param continueToken The token containing the continue node's info.
//	 * @return A SLContinueNode built using the given token.
//	 */
//	public SLStatementNode createContinue(Token continueToken) {
//		final SLContinueNode continueNode = new SLContinueNode();
//		srcFromToken(continueNode, continueToken);
//		return continueNode;
//	}
//
//	/**
//	 * Returns an {@link SLWhileNode} for the given parameters.
//	 *
//	 * @param whileToken    The token containing the while node's info
//	 * @param conditionNode The conditional node for this while loop
//	 * @param bodyNode      The body of the while loop
//	 * @return A SLWhileNode built using the given parameters. null if either
//	 *         conditionNode or bodyNode is null.
//	 */
//	public SLStatementNode createWhile(Token whileToken, SLExpressionNode conditionNode, SLStatementNode bodyNode) {
//		if (conditionNode == null || bodyNode == null) {
//			return null;
//		}
//
//		conditionNode.addStatementTag();
//		final int start = whileToken.getStartIndex();
//		final int end = bodyNode.getSourceEndIndex();
//		final SLWhileNode whileNode = new SLWhileNode(conditionNode, bodyNode);
//		whileNode.setSourceSection(start, end - start);
//		return whileNode;
//	}
//
//	/**
//	 * Returns an {@link SLIfNode} for the given parameters.
//	 *
//	 * @param ifToken       The token containing the if node's info
//	 * @param conditionNode The condition node of this if statement
//	 * @param thenPartNode  The then part of the if
//	 * @param elsePartNode  The else part of the if (null if no else part)
//	 * @return An SLIfNode for the given parameters. null if either conditionNode or
//	 *         thenPartNode is null.
//	 */
//	public SLStatementNode createIf(Token ifToken, SLExpressionNode conditionNode, SLStatementNode thenPartNode,
//			SLStatementNode elsePartNode) {
//		if (conditionNode == null || thenPartNode == null) {
//			return null;
//		}
//
//		conditionNode.addStatementTag();
//		final int start = ifToken.getStartIndex();
//		final int end = elsePartNode == null ? thenPartNode.getSourceEndIndex() : elsePartNode.getSourceEndIndex();
//		final SLIfNode ifNode = new SLIfNode(conditionNode, thenPartNode, elsePartNode);
//		ifNode.setSourceSection(start, end - start);
//		return ifNode;
//	}
//
//	/**
//	 * Returns an {@link SLReturnNode} for the given parameters.
//	 *
//	 * @param t         The token containing the return node's info
//	 * @param valueNode The value of the return (null if not returning a value)
//	 * @return An SLReturnNode for the given parameters.
//	 */
//	public SLStatementNode createReturn(Token t, SLExpressionNode valueNode) {
//		final int start = t.getStartIndex();
//		final int length = valueNode == null ? t.getText().length() : valueNode.getSourceEndIndex() - start;
//		final SLReturnNode returnNode = new SLReturnNode(valueNode);
//		returnNode.setSourceSection(start, length);
//		return returnNode;
//	}
//
//	/**
//	 * Returns the corresponding subclass of {@link SLExpressionNode} for binary
//	 * expressions. </br>
//	 * These nodes are currently not instrumented.
//	 *
//	 * @param opToken   The operator of the binary expression
//	 * @param leftNode  The left node of the expression
//	 * @param rightNode The right node of the expression
//	 * @return A subclass of SLExpressionNode using the given parameters based on
//	 *         the given opToken. null if either leftNode or rightNode is null.
//	 */
//	public SLExpressionNode createBinary(Token opToken, SLExpressionNode leftNode, SLExpressionNode rightNode) {
//		if (leftNode == null || rightNode == null) {
//			return null;
//		}
//		final SLExpressionNode leftUnboxed = SLUnboxNodeGen.create(leftNode);
//		final SLExpressionNode rightUnboxed = SLUnboxNodeGen.create(rightNode);
//
//		final SLExpressionNode result;
//		switch (opToken.getText()) {
//		case "+":
//			result = SLAddNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "*":
//			result = SLMulNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "/":
//			result = SLDivNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "-":
//			result = SLSubNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "<":
//			result = SLLessThanNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "<=":
//			result = SLLessOrEqualNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case ">":
//			result = SLLogicalNotNodeGen.create(SLLessOrEqualNodeGen.create(leftUnboxed, rightUnboxed));
//			break;
//		case ">=":
//			result = SLLogicalNotNodeGen.create(SLLessThanNodeGen.create(leftUnboxed, rightUnboxed));
//			break;
//		case "==":
//			result = SLEqualNodeGen.create(leftUnboxed, rightUnboxed);
//			break;
//		case "!=":
//			result = SLLogicalNotNodeGen.create(SLEqualNodeGen.create(leftUnboxed, rightUnboxed));
//			break;
//		case "&&":
//			result = new SLLogicalAndNode(leftUnboxed, rightUnboxed);
//			break;
//		case "||":
//			result = new SLLogicalOrNode(leftUnboxed, rightUnboxed);
//			break;
//		default:
//			throw new RuntimeException("unexpected operation: " + opToken.getText());
//		}
//
//		int start = leftNode.getSourceCharIndex();
//		int length = rightNode.getSourceEndIndex() - start;
//		result.setSourceSection(start, length);
//		result.addExpressionTag();
//
//		return result;
//	}
//
//	/**
//	 * Returns an {@link SLInvokeNode} for the given parameters.
//	 *
//	 * @param functionNode   The function being called
//	 * @param parameterNodes The parameters of the function call
//	 * @param finalToken     A token used to determine the end of the
//	 *                       sourceSelection for this call
//	 * @return An SLInvokeNode for the given parameters. null if functionNode or any
//	 *         of the parameterNodes are null.
//	 */
//	public SLExpressionNode createCall(SLExpressionNode functionNode, List<SLExpressionNode> parameterNodes,
//			Token finalToken) {
//		if (functionNode == null || containsNull(parameterNodes)) {
//			return null;
//		}
//
//		final SLExpressionNode result = new SLInvokeNode(functionNode,
//				parameterNodes.toArray(new SLExpressionNode[parameterNodes.size()]));
//
//		final int startPos = functionNode.getSourceCharIndex();
//		final int endPos = finalToken.getStartIndex() + finalToken.getText().length();
//		result.setSourceSection(startPos, endPos - startPos);
//		result.addExpressionTag();
//
//		return result;
//	}
//
//	/**
//	 * Returns an {@link SLWriteLocalVariableNode} for the given parameters.
//	 *
//	 * @param nameNode  The name of the variable being assigned
//	 * @param valueNode The value to be assigned
//	 * @return An SLExpressionNode for the given parameters. null if nameNode or
//	 *         valueNode is null.
//	 */
//	public SLExpressionNode createAssignment(SLExpressionNode nameNode, SLExpressionNode valueNode) {
//		return createAssignment(nameNode, valueNode, null);
//	}
//
//	/**
//	 * Returns an {@link SLWriteLocalVariableNode} for the given parameters.
//	 *
//	 * @param nameNode      The name of the variable being assigned
//	 * @param valueNode     The value to be assigned
//	 * @param argumentIndex null or index of the argument the assignment is
//	 *                      assigning
//	 * @return An SLExpressionNode for the given parameters. null if nameNode or
//	 *         valueNode is null.
//	 */
//	public SLExpressionNode createAssignment(SLExpressionNode nameNode, SLExpressionNode valueNode,
//			Integer argumentIndex) {
//		if (nameNode == null || valueNode == null) {
//			return null;
//		}
//
//		String name = ((SLStringLiteralNode) nameNode).executeGeneric(null);
//		FrameSlot frameSlot = frameDescriptor.findOrAddFrameSlot(name, argumentIndex, FrameSlotKind.Illegal);
//		lexicalScope.locals.put(name, frameSlot);
//		final SLExpressionNode result = SLWriteLocalVariableNodeGen.create(valueNode, frameSlot);
//
//		if (valueNode.hasSource()) {
//			final int start = nameNode.getSourceCharIndex();
//			final int length = valueNode.getSourceEndIndex() - start;
//			result.setSourceSection(start, length);
//		}
//		result.addExpressionTag();
//
//		return result;
//	}
//
//	/**
//	 * Returns a {@link SLReadLocalVariableNode} if this read is a local variable or
//	 * a {@link SLFunctionLiteralNode} if this read is global. In SL, the only
//	 * global names are functions.
//	 *
//	 * @param nameNode The name of the variable/function being read
//	 * @return either:
//	 *         <ul>
//	 *         <li>A SLReadLocalVariableNode representing the local variable being
//	 *         read.</li>
//	 *         <li>A SLFunctionLiteralNode representing the function
//	 *         definition.</li>
//	 *         <li>null if nameNode is null.</li>
//	 *         </ul>
//	 */
//	public SLExpressionNode createRead(SLExpressionNode nameNode) {
//		if (nameNode == null) {
//			return null;
//		}
//
//		String name = ((SLStringLiteralNode) nameNode).executeGeneric(null);
//		final SLExpressionNode result;
//		final FrameSlot frameSlot = lexicalScope.locals.get(name);
//		if (frameSlot != null) {
//			/* Read of a local variable. */
//			result = SLReadLocalVariableNodeGen.create(frameSlot);
//		} else {
//			/*
//			 * Read of a global name. In our language, the only global names are functions.
//			 */
//			result = new SLFunctionLiteralNode(name);
//		}
//		result.setSourceSection(nameNode.getSourceCharIndex(), nameNode.getSourceLength());
//		result.addExpressionTag();
//		return result;
//	}
//
//	public SLExpressionNode createStringLiteral(Token literalToken, boolean removeQuotes) {
//		/* Remove the trailing and ending " */
//		String literal = literalToken.getText();
//		if (removeQuotes) {
//			assert literal.length() >= 2 && literal.startsWith("\"") && literal.endsWith("\"");
//			literal = literal.substring(1, literal.length() - 1);
//		}
//
//		final SLStringLiteralNode result = new SLStringLiteralNode(literal.intern());
//		srcFromToken(result, literalToken);
//		result.addExpressionTag();
//		return result;
//	}
//
//	public SLExpressionNode createNumericLiteral(Token literalToken) {
//		SLExpressionNode result;
//		try {
//			/* Try if the literal is small enough to fit into a long value. */
//			result = new SLLongLiteralNode(Long.parseLong(literalToken.getText()));
//		} catch (NumberFormatException ex) {
//			/* Overflow of long value, so fall back to BigInteger. */
//			result = new SLBigIntegerLiteralNode(new BigInteger(literalToken.getText()));
//		}
//		srcFromToken(result, literalToken);
//		result.addExpressionTag();
//		return result;
//	}
//
//	public SLExpressionNode createParenExpression(SLExpressionNode expressionNode, int start, int length) {
//		if (expressionNode == null) {
//			return null;
//		}
//
//		final SLParenExpressionNode result = new SLParenExpressionNode(expressionNode);
//		result.setSourceSection(start, length);
//		return result;
//	}
//
//	/**
//	 * Returns an {@link SLReadPropertyNode} for the given parameters.
//	 *
//	 * @param receiverNode The receiver of the property access
//	 * @param nameNode     The name of the property being accessed
//	 * @return An SLExpressionNode for the given parameters. null if receiverNode or
//	 *         nameNode is null.
//	 */
//	public SLExpressionNode createReadProperty(SLExpressionNode receiverNode, SLExpressionNode nameNode) {
//		if (receiverNode == null || nameNode == null) {
//			return null;
//		}
//
//		final SLExpressionNode result = SLReadPropertyNodeGen.create(receiverNode, nameNode);
//
//		final int startPos = receiverNode.getSourceCharIndex();
//		final int endPos = nameNode.getSourceEndIndex();
//		result.setSourceSection(startPos, endPos - startPos);
//		result.addExpressionTag();
//
//		return result;
//	}
//
//	/**
//	 * Returns an {@link SLWritePropertyNode} for the given parameters.
//	 *
//	 * @param receiverNode The receiver object of the property assignment
//	 * @param nameNode     The name of the property being assigned
//	 * @param valueNode    The value to be assigned
//	 * @return An SLExpressionNode for the given parameters. null if receiverNode,
//	 *         nameNode or valueNode is null.
//	 */
//	public SLExpressionNode createWriteProperty(SLExpressionNode receiverNode, SLExpressionNode nameNode,
//			SLExpressionNode valueNode) {
//		if (receiverNode == null || nameNode == null || valueNode == null) {
//			return null;
//		}
//
//		final SLExpressionNode result = SLWritePropertyNodeGen.create(receiverNode, nameNode, valueNode);
//
//		final int start = receiverNode.getSourceCharIndex();
//		final int length = valueNode.getSourceEndIndex() - start;
//		result.setSourceSection(start, length);
//		result.addExpressionTag();
//
//		return result;
//	}
//
//	/**
//	 * Creates source description of a single token.
//	 */
//	private static void srcFromToken(SLStatementNode node, Token token) {
//		node.setSourceSection(token.getStartIndex(), token.getText().length());
//	}
//
//	/**
//	 * Checks whether a list contains a null.
//	 */
//	private static boolean containsNull(List<?> list) {
//		for (Object e : list) {
//			if (e == null) {
//				return true;
//			}
//		}
//		return false;
//	}

}
