package fr.cea.nabla.truffle.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	ExpressionInterpreterTest.class,
	BinaryOperationsInterpreterTest.class
})

public class TruffleTestSuite {}