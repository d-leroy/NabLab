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

import com.google.common.collect.Streams;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;

import fr.cea.nabla.ir.IrModuleExtensions;
import fr.cea.nabla.ir.IrTypeExtensions;
import fr.cea.nabla.ir.MandatoryOptions;
import fr.cea.nabla.ir.generator.Utils;
import fr.cea.nabla.ir.ir.Affectation;
import fr.cea.nabla.ir.ir.AfterTimeLoopJob;
import fr.cea.nabla.ir.ir.Arg;
import fr.cea.nabla.ir.ir.ArgOrVar;
import fr.cea.nabla.ir.ir.ArgOrVarRef;
import fr.cea.nabla.ir.ir.BaseType;
import fr.cea.nabla.ir.ir.BaseTypeConstant;
import fr.cea.nabla.ir.ir.BeforeTimeLoopJob;
import fr.cea.nabla.ir.ir.BinaryExpression;
import fr.cea.nabla.ir.ir.BoolConstant;
import fr.cea.nabla.ir.ir.Connectivity;
import fr.cea.nabla.ir.ir.ConnectivityCall;
import fr.cea.nabla.ir.ir.ConnectivityVariable;
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
import fr.cea.nabla.ir.ir.ItemIdDefinition;
import fr.cea.nabla.ir.ir.ItemIdValue;
import fr.cea.nabla.ir.ir.ItemIdValueCall;
import fr.cea.nabla.ir.ir.ItemIdValueIterator;
import fr.cea.nabla.ir.ir.ItemIndex;
import fr.cea.nabla.ir.ir.ItemIndexDefinition;
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
import fr.cea.nabla.ir.ir.TimeLoop;
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
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalMethodCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaExternalSupplierCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaFunctionCallNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaGetMeshElementsNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaGetMeshMaxNbElementsNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaGetMeshNbElementsNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaGetMeshSingletonNode;
import fr.cea.nabla.ir.truffle.nodes.expression.NablaIndexOfNodeGen;
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
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool3ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBool4ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaBoolConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt1ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt2ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt3ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaInt4ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaIntConstantNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaIntConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal1ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal2ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal3ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaReal4ConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaRealConstantNode;
import fr.cea.nabla.ir.truffle.nodes.expression.constant.NablaRealConstantNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.unary.NablaMinusNodeGen;
import fr.cea.nabla.ir.truffle.nodes.expression.unary.NablaNotNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaIfNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaIfNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionBlockNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaInstructionNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaLoopNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaReturnNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteArrayNodeGen;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNode;
import fr.cea.nabla.ir.truffle.nodes.instruction.NablaWriteVariableNodeGen;
import fr.cea.nabla.ir.truffle.nodes.job.NablaAfterTimeLoopJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaBeforeTimeLoopJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaInstructionJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaTimeLoopJobNode;
import fr.cea.nabla.ir.truffle.nodes.job.NablaTimeLoopJobNode2;
import fr.cea.nabla.ir.truffle.utils.GetFrameNodeGen;
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
			this.locals = new HashMap<String, FrameSlot>() {
				private static final long serialVersionUID = 3595092172639258017L;

				@Override
				public FrameSlot put(String key, FrameSlot value) {
//					assertTrue("Duplicate key: " + key, !containsKey(key));
					return super.put(key, value);
				}
			};
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
			final List<NablaExpressionNode> sizes = baseType.getSizes().stream().map(s -> createNablaExpressionNode(s))
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
		final NablaExpressionNode[] sizes = baseType.getSizes().stream().map(s -> createNablaExpressionNode(s))
				.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);
		return createNablaDefaultValueNode(baseType.getPrimitive(), sizes);
	}

	private NablaExpressionNode createNablaDefaultValueNode(PrimitiveType primitive, NablaExpressionNode[] sizes) {
		switch (primitive) {
		case BOOL: {
			final NablaExpressionNode value = createNablaBoolConstantNode(false);
			if (sizes == null) {
				return value;
			}
			switch (sizes.length) {
			case 0:
				return value;
			case 1:
				return NablaBool1ConstantNodeGen.create(value, sizes[0]);
			case 2:
				return NablaBool2ConstantNodeGen.create(value, sizes);
			case 3:
				return NablaBool3ConstantNodeGen.create(value, sizes);
			case 4:
				return NablaBool4ConstantNodeGen.create(value, sizes);
			default:
				throw new UnsupportedOperationException();
			}
		}
		case INT: {
			final NablaExpressionNode value = createNablaIntConstantNode(0);
			if (sizes == null) {
				return value;
			}
			switch (sizes.length) {
			case 0:
				return value;
			case 1:
				return NablaInt1ConstantNodeGen.create(value, sizes[0]);
			case 2:
				return NablaInt2ConstantNodeGen.create(value, sizes);
			case 3:
				return NablaInt3ConstantNodeGen.create(value, sizes);
			case 4:
				return NablaInt4ConstantNodeGen.create(value, sizes);
			default:
				throw new UnsupportedOperationException();
			}
		}
		case REAL: {
			final NablaExpressionNode value = createNablaRealConstantNode(0);
			if (sizes == null) {
				return value;
			}
			switch (sizes.length) {
			case 0:
				return value;
			case 1:
				return NablaReal1ConstantNodeGen.create(value, sizes[0]);
			case 2:
				return NablaReal2ConstantNodeGen.create(value, sizes);
			case 3:
				return NablaReal3ConstantNodeGen.create(value, sizes);
			case 4:
				return NablaReal4ConstantNodeGen.create(value, sizes);
			default:
				throw new UnsupportedOperationException();
			}
		}
		default:
			throw new UnsupportedOperationException();
		}
	}

	private FrameDescriptor moduleFrameDescriptor;

	private FrameSlot timeVariable;

	private FrameSlot deltatVariable;

	public NablaModuleNode createModule(IrModule module) {
		assert lexicalScope == null;

		lexicalScope = new LexicalScope(lexicalScope);

		moduleFrameDescriptor = lexicalScope.descriptor;

		final String moduleName = module.getName();

		final NablaWriteVariableNode[] constantDeclarations = module.getVariables().stream()
				.filter(v -> v instanceof SimpleVariable && v.isConst()).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		final NablaReadVariableNode[] mandatoryOptions;
		final NablaWriteVariableNode[] connectivityVariables;

		if (IrModuleExtensions.withMesh(module)) {
			final List<String> options = new ArrayList<>();
			options.add(MandatoryOptions.X_EDGE_ELEMS);
			options.add(MandatoryOptions.Y_EDGE_ELEMS);
			options.add(MandatoryOptions.X_EDGE_LENGTH);
			options.add(MandatoryOptions.Y_EDGE_LENGTH);
			mandatoryOptions = options.stream().map(s -> {
				final FrameSlot slot = moduleFrameDescriptor.findFrameSlot(s);
				return getReadVariableNode(slot);
			}).collect(Collectors.toList()).toArray(new NablaReadVariableNode[0]);

			connectivityVariables = Streams.stream(IrModuleExtensions.getUsedConnectivities(module)).map(c -> {
				final String connectivityName = c.getName();
				final FrameSlot frameSlot = moduleFrameDescriptor.findOrAddFrameSlot(connectivityName, null,
						FrameSlotKind.Illegal);
				lexicalScope.locals.put(connectivityName, frameSlot);
				if (c.getInTypes().isEmpty()) {
					return getWriteVariableNode(frameSlot, new NablaGetMeshNbElementsNode(connectivityName));
				} else {
					return getWriteVariableNode(frameSlot, new NablaGetMeshMaxNbElementsNode(connectivityName));
				}
			}).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);
		} else {
			mandatoryOptions = new NablaReadVariableNode[0];
			connectivityVariables = new NablaWriteVariableNode[0];
		}

		final NablaWriteVariableNode[] variableDeclarations = module.getVariables().stream()
				.filter(v -> !(v instanceof SimpleVariable && v.isConst())).map(v -> createVariableDeclaration(v))
				.filter(n -> n != null).collect(Collectors.toList()).toArray(new NablaWriteVariableNode[0]);

		final FrameSlot coordinatesSlot = lexicalScope.locals.get(module.getInitNodeCoordVariable().getName());
		assert (coordinatesSlot != null);

		final NablaFunctionNode[] functionNodes = module.getFunctions().stream().filter(f -> f.getBody() != null)
				.map(f -> functions.computeIfAbsent(f, function -> createNablaFunctionNode(f)))
				.collect(Collectors.toList()).toArray(new NablaFunctionNode[0]);

		timeVariable = moduleFrameDescriptor.findFrameSlot(module.getTimeVariable().getName());
		deltatVariable = moduleFrameDescriptor.findFrameSlot(module.getDeltatVariable().getName());

		final NablaJobNode[] jobNodes = module.getJobs().stream().filter(j -> j.getJobContainer() == module)
				.sorted((j1, j2) -> Double.compare(j1.getAt(), j2.getAt())).map(j -> createNablaJobNode(j))
				.collect(Collectors.toList()).toArray(new NablaJobNode[0]);

		final NablaModuleNode moduleNode = new NablaModuleNode(language, moduleFrameDescriptor, moduleName,
				constantDeclarations, mandatoryOptions, coordinatesSlot, connectivityVariables, variableDeclarations,
				functionNodes, jobNodes);

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
		case IrPackage.AFTER_TIME_LOOP_JOB:
			lexicalScope = new LexicalScope(lexicalScope, true);
			result = createNablaAfterTimeLoopJobNode((AfterTimeLoopJob) job);
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

	private NablaAfterTimeLoopJobNode createNablaAfterTimeLoopJobNode(AfterTimeLoopJob job) {
		final NablaInstructionNode[] copyInstructions = job.getCopies().stream().map(c -> {
			final FrameSlot source = lexicalScope.locals.get(c.getSource().getName());
			final NablaReadVariableNode sourceReadNode = getReadVariableNode(source);
			final FrameSlot destination = lexicalScope.locals.get(c.getDestination().getName());
			return getWriteVariableNode(destination, sourceReadNode);
		}).collect(Collectors.toList()).toArray(new NablaInstructionNode[0]);
		final NablaInstructionBlockNode block = new NablaInstructionBlockNode(copyInstructions);
		return new NablaAfterTimeLoopJobNode(language, lexicalScope.descriptor, job.getName(), block);
	}

	private NablaBeforeTimeLoopJobNode createNablaBeforeTimeLoopJobNode(BeforeTimeLoopJob job) {
		final NablaInstructionNode[] copyInstructions = job.getCopies().stream().map(c -> {
			final FrameSlot source = lexicalScope.locals.get(c.getSource().getName());
			final NablaReadVariableNode sourceReadNode = getReadVariableNode(source);
			final FrameSlot destination = lexicalScope.locals.get(c.getDestination().getName());
			return getWriteVariableNode(destination, sourceReadNode);
		}).collect(Collectors.toList()).toArray(new NablaInstructionNode[0]);
		final NablaInstructionBlockNode block = new NablaInstructionBlockNode(copyInstructions);
		return new NablaBeforeTimeLoopJobNode(language, lexicalScope.descriptor, job.getName(), block);
	}

	private static final boolean USE_REPEATING = true;

	// FIXME: use module frame descriptor or more local one?
	private NablaJobNode createNablaTimeLoopJobNode(TimeLoopJob job) {
		final String indexName = job.getTimeLoop().getIterationCounter().getName();
		final FrameSlot indexSlot = lexicalScope.locals.computeIfAbsent(indexName,
				n -> moduleFrameDescriptor.findOrAddFrameSlot(n, null, FrameSlotKind.Illegal));
		final List<FrameSlot[]> copies = job.getCopies().stream().map(c -> {
			final String source = c.getSource().getName();
			final String destination = c.getDestination().getName();
			final FrameSlot sourceSlot = lexicalScope.locals.computeIfAbsent(source,
					n -> moduleFrameDescriptor.findOrAddFrameSlot(n, null, FrameSlotKind.Illegal));
			final FrameSlot destinationSlot = lexicalScope.locals.computeIfAbsent(destination,
					n -> moduleFrameDescriptor.findOrAddFrameSlot(n, null, FrameSlotKind.Illegal));
			return new FrameSlot[] { sourceSlot, destinationSlot };
		}).collect(Collectors.toList());
		final NablaExpressionNode conditionNode = createNablaExpressionNode(job.getTimeLoop().getWhileCondition());
		final NablaJobNode[] innerJobs = job.getInnerJobs().stream().filter(j -> j.getAt() > 0)
				.sorted((j1, j2) -> Double.compare(j1.getAt(), j2.getAt())).map(j -> createNablaJobNode(j))
				.collect(Collectors.toList()).toArray(new NablaJobNode[0]);
		if (USE_REPEATING) {
			return new NablaTimeLoopJobNode(language, lexicalScope.descriptor, job.getName(), indexSlot, copies,
					conditionNode, innerJobs, getIndentation(job.getTimeLoop()), timeVariable, deltatVariable);
		} else {
			return new NablaTimeLoopJobNode2(language, lexicalScope.descriptor, job.getName(), indexSlot, copies,
					conditionNode, innerJobs, getIndentation(job.getTimeLoop()), timeVariable, deltatVariable);
		}
	}

	private String getIndentation(TimeLoop timeLoop) {
		if (timeLoop.getOuterTimeLoop() == null) {
			return "";
		} else {
			return getIndentation(timeLoop.getOuterTimeLoop()) + "\t\t";
		}
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

	private final static boolean GRAALVM = true;

	private NablaExpressionNode createNablaExternalFunctionCallNode(FunctionCall functionCall) {
		final Function function = functionCall.getFunction();
		final IrModule module = (IrModule) function.eContainer();
		final ClassLoader tccl = Thread.currentThread().getContextClassLoader();
		final String receiverClassName = module.getName().toLowerCase() + '.' + function.getProvider()
				+ Utils.FunctionReductionPrefix;
		final BaseType baseReturnType = function.getReturnType();
		final Class<?> javaReturnType = FunctionCallHelper.getJavaType(baseReturnType.getPrimitive(),
				IrTypeExtensions.getDimension(baseReturnType));
		final String methodName = function.getName();
		final NablaExpressionNode[] argNodes = functionCall.getArgs().stream().map(e -> createNablaExpressionNode(e))
				.collect(Collectors.toList()).toArray(new NablaExpressionNode[0]);
		if (GRAALVM) {
			return new NablaExternalMethodCallNode(receiverClassName, methodName, javaReturnType, argNodes);
		} else {
			try {
				final Class<?> receiverClass = Class.forName(receiverClassName, true, tccl);
				final List<Class<?>> javaParameterTypes = function.getInArgs().stream().map(a -> {
					final PrimitiveType primitiveType = a.getType().getPrimitive();
					final int dimension = IrTypeExtensions.getDimension(a.getType());
					return FunctionCallHelper.getJavaType(primitiveType, dimension);
				}).collect(Collectors.toList());
				final MethodHandles.Lookup lookup = MethodHandles.lookup();
				if (javaParameterTypes.isEmpty()) {
					final MethodType methodType = MethodType.methodType(javaReturnType);
					CallSite site = LambdaMetafactory.metafactory(lookup, "get", MethodType.methodType(Supplier.class),
							methodType.generic(), lookup.findStatic(receiverClass, function.getName(), methodType),
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
					final NablaExpressionNode argNode = argNodes[0];
					return new NablaExternalFunctionCallNode(externalFunction, argNode);
				} else {
					final MethodType methodType = MethodType.methodType(javaReturnType, javaParameterTypes.get(0),
							javaParameterTypes.get(1));
					CallSite site = LambdaMetafactory.metafactory(lookup, "apply",
							MethodType.methodType(BiFunction.class), methodType.generic(),
							lookup.findStatic(receiverClass, function.getName(), methodType), methodType);
					final BiFunction<Object, Object, Object> externalFunction = (BiFunction<Object, Object, Object>) site
							.getTarget().invokeExact();
					return new NablaExternalBiFunctionCallNode(externalFunction, argNodes);
				}
			} catch (Throwable e) {
				e.printStackTrace();
				throw new UnsupportedOperationException();
			}
		}
	}

	private NablaFunctionNode createNablaFunctionNode(Function function) {
		lexicalScope = new LexicalScope(lexicalScope, true);
		final List<NablaInstructionNode> functionInstructions = new ArrayList<>();
		final Set<String> sizeVarSet = new HashSet<>();
		int nbSizeVars = function.getVariables().size();
		for (int i = 0; i < nbSizeVars; i++) {
			final String varName = function.getVariables().get(i).getName();
			sizeVarSet.add(varName);
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(varName, null,
					FrameSlotKind.Illegal);
			lexicalScope.locals.put(varName, frameSlot);
		}

		int nbArgs = function.getInArgs().size();
		for (int i = 0; i < nbArgs; i++) {
			final Arg arg = function.getInArgs().get(i);
			final String argName = arg.getName();
			final NablaReadArgumentNode readArg = NablaReadArgumentNodeGen.create(i);
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(argName, null,
					FrameSlotKind.Illegal);
			lexicalScope.locals.put(argName, frameSlot);
			functionInstructions.add(createNablaWriteVariableNode(argName, readArg, i));
		}
		functionInstructions.addAll(Arrays.asList(createNablaInstructionNode(function.getBody())));
		final NablaInstructionNode bodyNode = new NablaInstructionBlockNode(
				functionInstructions.toArray(new NablaInstructionNode[0]));
		final NablaFunctionNode result = new NablaFunctionNode(language, lexicalScope.descriptor, bodyNode,
				function.getName());
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

	private NablaInstructionNode[] createNablaInstructionNode(Instruction instruction) {
		switch (instruction.eClass().getClassifierID()) {
		case IrPackage.AFFECTATION: {
			final Affectation affectation = (Affectation) instruction;
			final NablaExpressionNode right = createNablaExpressionNode(affectation.getRight());
			final ArgOrVarRef argOrVarRef = affectation.getLeft();
			final ArgOrVar argOrVar = argOrVarRef.getTarget();
			final String name = argOrVar.getName();
			if (argOrVarRef.getIterators().isEmpty() && argOrVarRef.getIndices().isEmpty()) {
				return new NablaInstructionNode[] { createNablaWriteVariableNode(name, right) };
			} else {
				return new NablaInstructionNode[] {
						createNablaWriteArrayNode(name, getArrayIndices(argOrVarRef), right) };
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
		case IrPackage.VARIABLES_DEFINITION: {
			final VariablesDefinition varDefinition = (VariablesDefinition) instruction;
			final NablaInstructionNode[] varDefs = varDefinition.getVariables().stream()
					.map(v -> createVariableDeclaration(v)).filter(n -> n != null).collect(Collectors.toList())
					.toArray(new NablaInstructionNode[0]);
			return varDefs;
		}
		case IrPackage.ITEM_ID_DEFINITION:
			return new NablaInstructionNode[] { createItemIdDefinitionNode((ItemIdDefinition) instruction) };
		case IrPackage.ITEM_INDEX_DEFINITION:
			return new NablaInstructionNode[] { createItemIndexDefinitionNode((ItemIndexDefinition) instruction) };
		}
		throw new UnsupportedOperationException();
	}

	private NablaInstructionNode createItemIdDefinitionNode(ItemIdDefinition itemIdDefinition) {
		final String idName = itemIdDefinition.getId().getName();
		final NablaExpressionNode itemIdValueNode = createItemIdValueNode(itemIdDefinition.getValue());
		final FrameSlot idSlot = lexicalScope.descriptor.findOrAddFrameSlot(idName, null, FrameSlotKind.Illegal);
		lexicalScope.locals.put(idName, idSlot);
		final NablaWriteVariableNode result = getWriteVariableNode(idSlot, itemIdValueNode);
		return result;
	}

	private NablaExpressionNode createItemIdValueNode(ItemIdValue value) {
		switch (value.eClass().getClassifierID()) {
		case IrPackage.ITEM_ID_VALUE_CALL: {
			final ItemIdValueCall itemIdValueCall = (ItemIdValueCall) value;
			final ConnectivityCall connectivityCall = itemIdValueCall.getCall();
			final String connectivityName = connectivityCall.getConnectivity().getName();
			final NablaExpressionNode[] argIds = connectivityCall.getArgs().stream().map(iId -> iId.getName())
					.map(s -> lexicalScope.locals.get(s)).map(s -> getReadVariableNode(s)).collect(Collectors.toList())
					.toArray(new NablaExpressionNode[0]);
			if (connectivityName.equals("commonFace")) {
				return new NablaGetMeshSingletonNode(argIds, connectivityName);
			}
			return new NablaGetMeshElementsNode(argIds, connectivityName);
		}
		case IrPackage.ITEM_ID_VALUE_ITERATOR: {
			final ItemIdValueIterator itemIdValueIterator = (ItemIdValueIterator) value;
			final ConnectivityCall connectivityCall = itemIdValueIterator.getIterator().getContainer();
			final NablaExpressionNode indexValueNode = createGetIndexValueNode(itemIdValueIterator);
			if (connectivityCall.getConnectivity().isIndexEqualId()) {
				return indexValueNode;
			}
			final String connectivityName = connectivityCall.getConnectivity().getName();
			final NablaExpressionNode[] argIds = connectivityCall.getArgs().stream().map(iId -> iId.getName())
					.map(s -> lexicalScope.locals.get(s)).map(s -> getReadVariableNode(s)).collect(Collectors.toList())
					.toArray(new NablaExpressionNode[0]);
			final NablaExpressionNode[] index = new NablaExpressionNode[] {
					createGetIndexValueNode(itemIdValueIterator) };
			final NablaExpressionNode arrayNode = new NablaGetMeshElementsNode(argIds, connectivityName);

			return NablaReadArrayNodeGen.create(index, arrayNode);
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaInstructionNode createItemIndexDefinitionNode(ItemIndexDefinition itemIndexDefinition) {
		final String indexName = itemIndexDefinition.getIndex().getName();
		final FrameSlot indexSlot = lexicalScope.descriptor.findOrAddFrameSlot(indexName, null, FrameSlotKind.Illegal);
		lexicalScope.locals.put(indexName, indexSlot);
		final FrameSlot idSlot = lexicalScope.locals.get(itemIndexDefinition.getValue().getId().getName());
		final NablaExpressionNode idValueNode = getReadVariableNode(idSlot);
		if (itemIndexDefinition.getValue().getContainer().getConnectivity().isIndexEqualId()) {
			return getWriteVariableNode(indexSlot, idValueNode);
		}
		final ConnectivityCall connectivityCall = itemIndexDefinition.getValue().getContainer();
		final String connectivityName = connectivityCall.getConnectivity().getName();
		final NablaExpressionNode[] argIds = connectivityCall.getArgs().stream().map(iId -> iId.getName())
				.map(s -> lexicalScope.locals.get(s)).map(s -> getReadVariableNode(s)).collect(Collectors.toList())
				.toArray(new NablaExpressionNode[0]);
		final NablaExpressionNode arrayNode = new NablaGetMeshElementsNode(argIds, connectivityName);
		final NablaExpressionNode indexOfNode = NablaIndexOfNodeGen.create(arrayNode, idValueNode);
		return getWriteVariableNode(indexSlot, indexOfNode);
	}

	private NablaExpressionNode createGetIndexValueNode(ItemIdValueIterator itemIdValueIterator) {
		final FrameSlot indexSlot = lexicalScope.locals.get(itemIdValueIterator.getIterator().getIndex().getName());
		final NablaExpressionNode indexValueNode = getReadVariableNode(indexSlot);
		if (itemIdValueIterator.getShift() == 0) {
			return indexValueNode;
		}
		final NablaExpressionNode nbElemsNode = getReadVariableNode(
				lexicalScope.locals.get(itemIdValueIterator.getIterator().getContainer().getConnectivity().getName()));
		final NablaExpressionNode shiftNode = NablaIntConstantNodeGen.create(itemIdValueIterator.getShift());

		return createNablaBinaryExpressionNode(createNablaBinaryExpressionNode(indexValueNode, "+",
				createNablaBinaryExpressionNode(shiftNode, "+", nbElemsNode)), "%", nbElemsNode);
	}

	private NablaInstructionNode createNablaReturnNode(Return ret) {
		return NablaReturnNodeGen.create(createNablaExpressionNode(ret.getExpression()));
	}

	private NablaInstructionNode createNablaLoopNode(Loop loop) {
		final IterationBlock iterationBlock = loop.getIterationBlock();
		switch (iterationBlock.eClass().getClassifierID()) {
		case IrPackage.INTERVAL: {
			lexicalScope = new LexicalScope(lexicalScope);
			final Interval interval = (Interval) iterationBlock;
			final String indexName = interval.getIndex().getName();
			final FrameSlot indexSlot = lexicalScope.descriptor.findOrAddFrameSlot(indexName, null,
					FrameSlotKind.Illegal);
			lexicalScope.locals.put(indexName, indexSlot);
			final NablaInstructionNode bodyNode = new NablaInstructionBlockNode(
					createNablaInstructionNode(loop.getBody()));
			final NablaExpressionNode iterationCount = createNablaExpressionNode(interval.getNbElems());
			final NablaInstructionNode result = NablaLoopNodeGen.create(indexSlot, bodyNode, iterationCount);
			lexicalScope = lexicalScope.outer;
			return result;
		}
		case IrPackage.ITERATOR: {
			lexicalScope = new LexicalScope(lexicalScope);
			final Iterator iterator = (Iterator) iterationBlock;
			final String indexName = iterator.getIndex().getName();
			final FrameSlot indexSlot = lexicalScope.descriptor.findOrAddFrameSlot(indexName, null,
					FrameSlotKind.Illegal);
			lexicalScope.locals.put(indexName, indexSlot);
			final NablaInstructionNode bodyNode = new NablaInstructionBlockNode(
					createNablaInstructionNode(loop.getBody()));
			final String connectivityName = iterator.getContainer().getConnectivity().getName();
			final NablaExpressionNode[] argIds = iterator.getContainer().getArgs().stream().map(iId -> iId.getName())
					.map(s -> lexicalScope.locals.get(s)).map(s -> getReadVariableNode(s)).collect(Collectors.toList())
					.toArray(new NablaExpressionNode[0]);
			final NablaExpressionNode elements = new NablaGetMeshElementsNode(argIds, connectivityName);
			final NablaInstructionNode result = NablaLoopNodeGen.create(indexSlot, bodyNode, elements);
			lexicalScope = lexicalScope.outer;
			return result;
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaIfNode createNablaIfNode(If ifInstruction) {
		final NablaExpressionNode conditionNode = createNablaExpressionNode(ifInstruction.getCondition());
		final NablaInstructionNode thenNode = new NablaInstructionBlockNode(
				createNablaInstructionNode(ifInstruction.getThenInstruction()));
		final NablaInstructionNode elseNode = ifInstruction.getElseInstruction() == null ? null
				: new NablaInstructionBlockNode(createNablaInstructionNode(ifInstruction.getElseInstruction()));
		return NablaIfNodeGen.create(conditionNode, thenNode, elseNode);
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
		final FrameSlot frameSlot = lexicalScope.locals.get(target.getName());
		if (ref.getIterators().isEmpty() && ref.getIndices().isEmpty()) {
			return getReadVariableNode(frameSlot);
		} else {
			return NablaReadArrayNodeGen.create(getArrayIndices(ref), getReadVariableNode(frameSlot));
		}
	}

	private NablaExpressionNode[] getArrayIndices(ArgOrVarRef ref) {
		final NablaExpressionNode[] indices = new NablaExpressionNode[ref.getIterators().size()
				+ ref.getIndices().size()];
		int i = 0;
		for (ItemIndex item : ref.getIterators()) {
			indices[i] = getReadVariableNode(lexicalScope.locals.get(item.getName()));
			i++;
		}
		for (Expression idx : ref.getIndices()) {
			indices[i] = createNablaExpressionNode(idx);
			i++;
		}

		return indices;
	}

	private NablaRealConstantNode createNablaRealConstantNode(double value) {
		return NablaRealConstantNodeGen.create(value);
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
			final NablaExpressionNode[] dimensions = baseType.getSizes().stream().map(s -> createNablaExpressionNode(s))
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
		return getWriteVariableNode(frameSlot, value);
	}

	private NablaWriteArrayNode createNablaWriteArrayNode(String name, NablaExpressionNode[] indices,
			NablaExpressionNode value) {
		return createNablaWriteArrayNode(name, indices, value, null);
	}

	private NablaWriteArrayNode createNablaWriteArrayNode(String name, NablaExpressionNode[] indices,
			NablaExpressionNode value, Integer paramaterIndex) {
		final FrameSlot frameSlot = lexicalScope.locals.get(name);
		return NablaWriteArrayNodeGen.create(frameSlot, indices, value, GetFrameNodeGen.create(frameSlot));
	}

	private NablaWriteVariableNode createVariableDeclaration(Variable variable) {
		switch (variable.eClass().getClassifierID()) {
		case IrPackage.SIMPLE_VARIABLE: {
			final SimpleVariable simpleVariable = (SimpleVariable) variable;
			final String name = simpleVariable.getName();
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(name, frameSlot);
			if (simpleVariable.getDefaultValue() != null) {
				final NablaExpressionNode defaultValue = createNablaExpressionNode(simpleVariable.getDefaultValue());
				return getWriteVariableNode(frameSlot, defaultValue);
			} else {
				final NablaExpressionNode defaultValue = createNablaDefaultValueNode(simpleVariable.getType());
				return getWriteVariableNode(frameSlot, defaultValue);
			}
		}
		case IrPackage.CONNECTIVITY_VARIABLE: {
			final ConnectivityVariable connectivityVariable = (ConnectivityVariable) variable;
			final String name = connectivityVariable.getName();
			final FrameSlot frameSlot = lexicalScope.descriptor.findOrAddFrameSlot(name, null, FrameSlotKind.Illegal);
			lexicalScope.locals.put(name, frameSlot);
			if (connectivityVariable.getDefaultValue() == null) {
				final NablaExpressionNode[] sizeNodes = new NablaExpressionNode[connectivityVariable.getType()
						.getConnectivities().size() + connectivityVariable.getType().getBase().getSizes().size()];
				int i = 0;
				for (Connectivity c : connectivityVariable.getType().getConnectivities()) {
					sizeNodes[i] = getReadVariableNode(lexicalScope.locals.get(c.getName()));
					i++;
				}
				for (Expression s : connectivityVariable.getType().getBase().getSizes()) {
					sizeNodes[i] = createNablaExpressionNode(s);
					i++;
				}
				final NablaExpressionNode defaultValue = createNablaDefaultValueNode(
						connectivityVariable.getType().getBase().getPrimitive(), sizeNodes);
				return getWriteVariableNode(frameSlot, defaultValue);
			} else {
				final NablaExpressionNode defaultValue = createNablaReadArgOrVariableNode(
						connectivityVariable.getDefaultValue());
				return getWriteVariableNode(frameSlot, defaultValue);
			}
		}
		}
		throw new UnsupportedOperationException();
	}

	private NablaReadVariableNode getReadVariableNode(FrameSlot slot) {
		return NablaReadVariableNodeGen.create(slot, GetFrameNodeGen.create(slot));
	}

	private NablaWriteVariableNode getWriteVariableNode(FrameSlot slot, NablaExpressionNode value) {
		return NablaWriteVariableNodeGen.create(slot, value, GetFrameNodeGen.create(slot));
	}
}
