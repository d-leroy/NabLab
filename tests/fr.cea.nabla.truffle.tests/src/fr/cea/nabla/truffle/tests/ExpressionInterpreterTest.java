package fr.cea.nabla.truffle.tests;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.tests.NablaInjectorProvider;

import static fr.cea.nabla.tests.TestUtils.*;
import fr.cea.nabla.truffle.tests.ExpressionTestModels;

@RunWith(XtextRunner.class)
@InjectWith(NablaInjectorProvider.class)
public class ExpressionInterpreterTest {

	@Inject ExpressionTestModels testModels;
			
	@Test
	public void testInterpreteContractedIf() {
		final IrModule irModule = testModels.getContractedIf();
		System.out.println(irModule);
//		assertVariableDefaultValue(irModule, context, "r1", new NV0Real(1.0))
//		assertVariableDefaultValue(irModule, context, "r2", new NV0Real(2.0))
	}

	@Test
	public void testInterpreteBinaryExpression() {
		//cf BinaryOperationsInterpreter
	}

	@Test
	public void testInterpreteUnaryExpression() {
		final IrModule irModule = testModels.getUnaryExpression();
		
//		val handler = new ConsoleHandler
//		handler.level = Level::OFF
//		val moduleInterpreter = new ModuleInterpreter(irModule, handler)
//		val context = moduleInterpreter.interprete
//		
//		assertVariableValueInContext(irModule, context, "b0", new NV0Bool(true))
//		assertVariableValueInContext(irModule, context, "n0", new NV0Int(-1))
//		assertVariableValueInContext(irModule, context, "r0", new NV0Real(-1))
//		assertVariableValueInContext(irModule, context, "n1", new NV1Int(#[1, 2]))
//		assertVariableValueInContext(irModule, context, "n2", new NV1Int(#[-1, -2]))
//		assertVariableValueInContext(irModule, context, "r1", new NV1Real(#[1.0, 2.0]))
//		assertVariableValueInContext(irModule, context, "r2", new NV1Real(#[-1.0, -2.0]))
//		assertVariableValueInContext(irModule, context, "n3", new NV2Int(#[#[0, 1],#[1, 2]]))
//		assertVariableValueInContext(irModule, context, "n4", new NV2Int(#[#[0, -1],#[-1, -2]]))
//		assertVariableValueInContext(irModule, context, "r3", new NV2Real(#[#[0.0, 1.0],#[1.0, 2.0]]))
//		// Warning : -0.0 != 0.0
//		assertVariableValueInContext(irModule, context, "r4", new NV2Real(#[#[-0.0, -1.0],#[-1.0, -2.0]]))
	}

	@Test
	public void testInterpreteParenthesis() {
		final IrModule irModule = testModels.getParenthesis();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "b", new NV0Bool(true))
	}

	@Test
	public void testInterpreteConstant() {
		//NB : Constant only for Scalar
		final IrModule irModule = testModels.getConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "n1", new NV0Int(1))
//		assertVariableDefaultValue(irModule, context, "r1", new NV0Real(2.0))
//		assertVariableDefaultValue(irModule, context, "b1", new NV0Bool(true))
//		assertVariableDefaultValue(irModule, context, "b2", new NV0Bool(false))
	}

	@Test
	public void testInterpreteMinConstant() {
		final IrModule irModule = testModels.getMinConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "nMin", new NV0Int(Integer.MIN_VALUE))
//		assertVariableDefaultValue(irModule, context, "rMin", new NV0Real(Double.MIN_VALUE))
	}

	@Test
	public void testInterpreteMaxConstant() {
		final IrModule irModule = testModels.getMaxConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "nMax", new NV0Int(Integer.MAX_VALUE))
//		assertVariableDefaultValue(irModule, context, "rMax", new NV0Real(Double.MAX_VALUE))
	}

	@Test
	public void testInterpreteBaseTypeConstant() {
		final IrModule irModule = testModels.getBaseTypeConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "n1", new NV0Int(1))
//		assertVariableDefaultValue(irModule, context, "n2", new NV1Int(#[1,1]))
//		assertVariableDefaultValue(irModule, context, "n3", new NV2Int(#[#[1, 1, 1],#[1, 1, 1]]))
//		
//		assertVariableDefaultValue(irModule, context, "r1", new NV0Real(1.0))
//		assertVariableDefaultValue(irModule, context, "r2", new NV1Real(#[1.0,1.0]))
//		assertVariableDefaultValue(irModule, context, "r3", new NV2Real(#[#[1.0, 1.0, 1.0],#[1.0, 1.0, 1.0]]))
//
//		assertVariableDefaultValue(irModule, context, "b1", new NV0Bool(true))
//		assertVariableDefaultValue(irModule, context, "b2", new NV1Bool(#[true, true]))
//		assertVariableDefaultValue(irModule, context, "b3", new NV2Bool(#[#[true, true, true],#[true, true, true]]))
	}

	@Test
	public void testInterpreteIntVectorConstant() {
		final IrModule irModule = testModels.getIntVectorConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "n", new NV1Int(#[1,2]))
	}

	@Test
	public void testInterpreteIntMatrixConstant() {
		final IrModule irModule = testModels.getIntMatrixConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "n", new NV2Int(#[#[0, 1, 2],#[1, 2, 3]]))
	}

	@Test
	public void testInterpreteRealVectorConstant() {
		final IrModule irModule = testModels.getRealVectorConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "r", new NV1Real(#[1.0, 2.0]))
	}

	@Test
	public void testInterpreteRealMatrixConstant() {
		final IrModule irModule = testModels.getRealMatrixConstant();
		
//		val context = new Context(irModule, Logger.getLogger(ExpressionInterpreterTest.name))
//
//		assertVariableDefaultValue(irModule, context, "r", new NV2Real(#[#[0.0, 1.0, 2.0],#[1.0, 2.0, 3.0]]))
	}

	@Test
	public void testInterpreteFunctionCall() {
		final IrModule irModule = testModels.getFunctionCall();
		
//		val handler = new ConsoleHandler
//		handler.level = Level::OFF
//		val moduleInterpreter = new ModuleInterpreter(irModule, handler)
//		val context = moduleInterpreter.interprete
//
//		assertVariableValueInContext(irModule, context, "n0", new NV0Int(0))
//		assertVariableValueInContext(irModule, context, "n1", new NV0Int(1))
//		assertVariableValueInContext(irModule, context, "n2", new NV0Int(2))
//		assertVariableValueInContext(irModule, context, "n3", new NV0Int(3))
//
//		assertVariableValueInContext(irModule, context, "r1", new NV0Real(1.0))
//		assertVariableValueInContext(irModule, context, "r2", new NV0Real(2.0))
//		assertVariableValueInContext(irModule, context, "r3", new NV0Real(3.0))
//
//		assertVariableValueInContext(irModule, context, "w", new NV1Real(#[3.0, 3.0]))
//		assertVariableValueInContext(irModule, context, "res1", new NV1Real(#[3.0, 3.0, 3.0]))
//		assertVariableValueInContext(irModule, context, "res2", new NV2Real(#[#[3.0, 3.0],#[3.0, 3.0]]))
	}

	@Test
	public void testInterpreteFunctionCallWithBody() {
		final IrModule irModule = testModels.getFunctionCallWithBody();
		
//		val handler = new ConsoleHandler
//		handler.level = Level::OFF
//		val moduleInterpreter = new ModuleInterpreter(irModule, handler)
//		val context = moduleInterpreter.interprete
//
//		assertVariableValueInContext(irModule, context, "w1", new NV1Real(#[0.0, 0.2]))
//		assertVariableValueInContext(irModule, context, "w2", new NV1Real(#[0.0, 0.2]))
//		assertVariableValueInContext(irModule, context, "w3", new NV1Real(#[0.0, 0.2, 0.4]))
//		assertVariableValueInContext(irModule, context, "w4", new NV1Real(#[0.0, 0.2]))
//		assertVariableValueInContext(irModule, context, "w5", new NV1Real(#[0.0, 0.2, 0.4]))
//		assertVariableValueInContext(irModule, context, "w6", new NV1Real(#[0.0, 0.2]))
	}

	@Test
	public void testInterpreteVarRef() {
		final IrModule irModule = testModels.getVarRef();
		
//		val handler = new ConsoleHandler
//		handler.level = Level::OFF
//		val moduleInterpreter = new ModuleInterpreter(irModule, handler)
//		val context = moduleInterpreter.interprete
//
//		assertVariableValueInContext(irModule, context, "b2", new NV0Bool(true))
//
//		assertVariableValueInContext(irModule, context, "n2", new NV0Int(1))
//		assertVariableValueInContext(irModule, context, "n4", new NV1Int(#[2,3]))
//		assertVariableValueInContext(irModule, context, "n7", new NV2Int(#[#[2,3],#[4,5],#[6,7]]))
//		assertVariableValueInContext(irModule, context, "n8", new NV0Int(5))
//
//		assertVariableValueInContext(irModule, context, "r2", new NV0Real(1.0))
//		assertVariableValueInContext(irModule, context, "r4", new NV1Real(#[2.0,3.0]))
//		assertVariableValueInContext(irModule, context, "r7", new NV2Real(#[#[2.0,3.0],#[4.0,5.0],#[6.0,7.0]]))
//		assertVariableValueInContext(irModule, context, "r8", new NV0Real(5.0))
	}
}
