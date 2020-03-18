package fr.cea.nabla.truffle.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	BinaryOperationsInterpreterTest.class,
	ExpressionInterpreterTest.class,
	InstructionInterpreterTest.class,
	JobInterpreterTest.class,
	ModuleInterpreterTest.class,
	NablaExamplesInterpreterTest.class
})

public class TruffleTestSuite {}