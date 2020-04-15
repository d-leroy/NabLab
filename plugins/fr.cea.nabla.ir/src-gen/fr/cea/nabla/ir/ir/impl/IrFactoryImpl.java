/**
 */
package fr.cea.nabla.ir.ir.impl;

import fr.cea.nabla.ir.ir.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IrFactoryImpl extends EFactoryImpl implements IrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IrFactory init() {
		try {
			IrFactory theIrFactory = (IrFactory)EPackage.Registry.INSTANCE.getEFactory(IrPackage.eNS_URI);
			if (theIrFactory != null) {
				return theIrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IrFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IrPackage.IR_ANNOTATION: return createIrAnnotation();
			case IrPackage.IR_MODULE: return createIrModule();
			case IrPackage.IMPORT: return createImport();
			case IrPackage.POST_PROCESSING_INFO: return createPostProcessingInfo();
			case IrPackage.TIME_LOOP: return createTimeLoop();
			case IrPackage.ARG: return createArg();
			case IrPackage.SIMPLE_VARIABLE: return createSimpleVariable();
			case IrPackage.CONNECTIVITY_VARIABLE: return createConnectivityVariable();
			case IrPackage.FUNCTION: return createFunction();
			case IrPackage.CONNECTIVITY: return createConnectivity();
			case IrPackage.INSTRUCTION_JOB: return createInstructionJob();
			case IrPackage.TIME_LOOP_JOB: return createTimeLoopJob();
			case IrPackage.BEFORE_TIME_LOOP_JOB: return createBeforeTimeLoopJob();
			case IrPackage.AFTER_TIME_LOOP_JOB: return createAfterTimeLoopJob();
			case IrPackage.TIME_LOOP_COPY: return createTimeLoopCopy();
			case IrPackage.INSTRUCTION_BLOCK: return createInstructionBlock();
			case IrPackage.VARIABLES_DEFINITION: return createVariablesDefinition();
			case IrPackage.AFFECTATION: return createAffectation();
			case IrPackage.REDUCTION_INSTRUCTION: return createReductionInstruction();
			case IrPackage.LOOP: return createLoop();
			case IrPackage.ITEM_INDEX_DEFINITION: return createItemIndexDefinition();
			case IrPackage.ITEM_ID_DEFINITION: return createItemIdDefinition();
			case IrPackage.SET_DEFINITION: return createSetDefinition();
			case IrPackage.IF: return createIf();
			case IrPackage.RETURN: return createReturn();
			case IrPackage.EXIT: return createExit();
			case IrPackage.ITERATOR: return createIterator();
			case IrPackage.INTERVAL: return createInterval();
			case IrPackage.CONTRACTED_IF: return createContractedIf();
			case IrPackage.BINARY_EXPRESSION: return createBinaryExpression();
			case IrPackage.UNARY_EXPRESSION: return createUnaryExpression();
			case IrPackage.PARENTHESIS: return createParenthesis();
			case IrPackage.INT_CONSTANT: return createIntConstant();
			case IrPackage.REAL_CONSTANT: return createRealConstant();
			case IrPackage.BOOL_CONSTANT: return createBoolConstant();
			case IrPackage.MIN_CONSTANT: return createMinConstant();
			case IrPackage.MAX_CONSTANT: return createMaxConstant();
			case IrPackage.FUNCTION_CALL: return createFunctionCall();
			case IrPackage.BASE_TYPE_CONSTANT: return createBaseTypeConstant();
			case IrPackage.VECTOR_CONSTANT: return createVectorConstant();
			case IrPackage.CARDINALITY: return createCardinality();
			case IrPackage.ARG_OR_VAR_REF: return createArgOrVarRef();
			case IrPackage.ITEM_TYPE: return createItemType();
			case IrPackage.IR_TYPE: return createIrType();
			case IrPackage.BASE_TYPE: return createBaseType();
			case IrPackage.CONNECTIVITY_TYPE: return createConnectivityType();
			case IrPackage.TIME_LOOP_VARIABLE: return createTimeLoopVariable();
			case IrPackage.CONNECTIVITY_CALL: return createConnectivityCall();
			case IrPackage.SET_REF: return createSetRef();
			case IrPackage.ITEM_ID: return createItemId();
			case IrPackage.ITEM_ID_VALUE_ITERATOR: return createItemIdValueIterator();
			case IrPackage.ITEM_ID_VALUE_CALL: return createItemIdValueCall();
			case IrPackage.ITEM_INDEX: return createItemIndex();
			case IrPackage.ITEM_INDEX_VALUE: return createItemIndexValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case IrPackage.PRIMITIVE_TYPE:
				return createPrimitiveTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case IrPackage.PRIMITIVE_TYPE:
				return convertPrimitiveTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IrAnnotation createIrAnnotation() {
		IrAnnotationImpl irAnnotation = new IrAnnotationImpl();
		return irAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IrModule createIrModule() {
		IrModuleImpl irModule = new IrModuleImpl();
		return irModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PostProcessingInfo createPostProcessingInfo() {
		PostProcessingInfoImpl postProcessingInfo = new PostProcessingInfoImpl();
		return postProcessingInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeLoop createTimeLoop() {
		TimeLoopImpl timeLoop = new TimeLoopImpl();
		return timeLoop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Arg createArg() {
		ArgImpl arg = new ArgImpl();
		return arg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimpleVariable createSimpleVariable() {
		SimpleVariableImpl simpleVariable = new SimpleVariableImpl();
		return simpleVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectivityVariable createConnectivityVariable() {
		ConnectivityVariableImpl connectivityVariable = new ConnectivityVariableImpl();
		return connectivityVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connectivity createConnectivity() {
		ConnectivityImpl connectivity = new ConnectivityImpl();
		return connectivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstructionJob createInstructionJob() {
		InstructionJobImpl instructionJob = new InstructionJobImpl();
		return instructionJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeLoopJob createTimeLoopJob() {
		TimeLoopJobImpl timeLoopJob = new TimeLoopJobImpl();
		return timeLoopJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BeforeTimeLoopJob createBeforeTimeLoopJob() {
		BeforeTimeLoopJobImpl beforeTimeLoopJob = new BeforeTimeLoopJobImpl();
		return beforeTimeLoopJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AfterTimeLoopJob createAfterTimeLoopJob() {
		AfterTimeLoopJobImpl afterTimeLoopJob = new AfterTimeLoopJobImpl();
		return afterTimeLoopJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeLoopCopy createTimeLoopCopy() {
		TimeLoopCopyImpl timeLoopCopy = new TimeLoopCopyImpl();
		return timeLoopCopy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstructionBlock createInstructionBlock() {
		InstructionBlockImpl instructionBlock = new InstructionBlockImpl();
		return instructionBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariablesDefinition createVariablesDefinition() {
		VariablesDefinitionImpl variablesDefinition = new VariablesDefinitionImpl();
		return variablesDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Affectation createAffectation() {
		AffectationImpl affectation = new AffectationImpl();
		return affectation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReductionInstruction createReductionInstruction() {
		ReductionInstructionImpl reductionInstruction = new ReductionInstructionImpl();
		return reductionInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Loop createLoop() {
		LoopImpl loop = new LoopImpl();
		return loop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIndexDefinition createItemIndexDefinition() {
		ItemIndexDefinitionImpl itemIndexDefinition = new ItemIndexDefinitionImpl();
		return itemIndexDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIdDefinition createItemIdDefinition() {
		ItemIdDefinitionImpl itemIdDefinition = new ItemIdDefinitionImpl();
		return itemIdDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SetDefinition createSetDefinition() {
		SetDefinitionImpl setDefinition = new SetDefinitionImpl();
		return setDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public If createIf() {
		IfImpl if_ = new IfImpl();
		return if_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Return createReturn() {
		ReturnImpl return_ = new ReturnImpl();
		return return_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Exit createExit() {
		ExitImpl exit = new ExitImpl();
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iterator createIterator() {
		IteratorImpl iterator = new IteratorImpl();
		return iterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Interval createInterval() {
		IntervalImpl interval = new IntervalImpl();
		return interval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContractedIf createContractedIf() {
		ContractedIfImpl contractedIf = new ContractedIfImpl();
		return contractedIf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BinaryExpression createBinaryExpression() {
		BinaryExpressionImpl binaryExpression = new BinaryExpressionImpl();
		return binaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnaryExpression createUnaryExpression() {
		UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
		return unaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parenthesis createParenthesis() {
		ParenthesisImpl parenthesis = new ParenthesisImpl();
		return parenthesis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntConstant createIntConstant() {
		IntConstantImpl intConstant = new IntConstantImpl();
		return intConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealConstant createRealConstant() {
		RealConstantImpl realConstant = new RealConstantImpl();
		return realConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoolConstant createBoolConstant() {
		BoolConstantImpl boolConstant = new BoolConstantImpl();
		return boolConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MinConstant createMinConstant() {
		MinConstantImpl minConstant = new MinConstantImpl();
		return minConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MaxConstant createMaxConstant() {
		MaxConstantImpl maxConstant = new MaxConstantImpl();
		return maxConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BaseTypeConstant createBaseTypeConstant() {
		BaseTypeConstantImpl baseTypeConstant = new BaseTypeConstantImpl();
		return baseTypeConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VectorConstant createVectorConstant() {
		VectorConstantImpl vectorConstant = new VectorConstantImpl();
		return vectorConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cardinality createCardinality() {
		CardinalityImpl cardinality = new CardinalityImpl();
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FunctionCall createFunctionCall() {
		FunctionCallImpl functionCall = new FunctionCallImpl();
		return functionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArgOrVarRef createArgOrVarRef() {
		ArgOrVarRefImpl argOrVarRef = new ArgOrVarRefImpl();
		return argOrVarRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemType createItemType() {
		ItemTypeImpl itemType = new ItemTypeImpl();
		return itemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IrType createIrType() {
		IrTypeImpl irType = new IrTypeImpl();
		return irType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BaseType createBaseType() {
		BaseTypeImpl baseType = new BaseTypeImpl();
		return baseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectivityType createConnectivityType() {
		ConnectivityTypeImpl connectivityType = new ConnectivityTypeImpl();
		return connectivityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeLoopVariable createTimeLoopVariable() {
		TimeLoopVariableImpl timeLoopVariable = new TimeLoopVariableImpl();
		return timeLoopVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectivityCall createConnectivityCall() {
		ConnectivityCallImpl connectivityCall = new ConnectivityCallImpl();
		return connectivityCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SetRef createSetRef() {
		SetRefImpl setRef = new SetRefImpl();
		return setRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemId createItemId() {
		ItemIdImpl itemId = new ItemIdImpl();
		return itemId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIdValueIterator createItemIdValueIterator() {
		ItemIdValueIteratorImpl itemIdValueIterator = new ItemIdValueIteratorImpl();
		return itemIdValueIterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIdValueCall createItemIdValueCall() {
		ItemIdValueCallImpl itemIdValueCall = new ItemIdValueCallImpl();
		return itemIdValueCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIndex createItemIndex() {
		ItemIndexImpl itemIndex = new ItemIndexImpl();
		return itemIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIndexValue createItemIndexValue() {
		ItemIndexValueImpl itemIndexValue = new ItemIndexValueImpl();
		return itemIndexValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveTypeFromString(EDataType eDataType, String initialValue) {
		PrimitiveType result = PrimitiveType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrimitiveTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IrPackage getIrPackage() {
		return (IrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IrPackage getPackage() {
		return IrPackage.eINSTANCE;
	}

} //IrFactoryImpl
