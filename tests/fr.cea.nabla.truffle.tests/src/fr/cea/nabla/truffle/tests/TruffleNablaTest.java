package fr.cea.nabla.truffle.tests;


import static fr.cea.nabla.tests.TestUtils.readFileAsString;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.tests.CompilationChainHelper;
import fr.cea.nabla.tests.NablaInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(NablaInjectorProvider.class)
class TruffleNablaTest {
	static String wsPath;
	static String examplesProjectSubPath;
	static String examplesProjectPath;

	@Inject CompilationChainHelper compilationHelper;

	@BeforeClass
	public static void setup() {
		final String testProjectPath = System.getProperty("user.dir");
		final String wsPath = testProjectPath + "/../../";
		final String examplesProjectSubPath = "plugins/fr.cea.nabla.ui/examples/NablaExamples/";
		final String examplesProjectPath = wsPath + examplesProjectSubPath;
		
	}

	@Test
	public void testInterpreteGlace2D() {
		final String model = readFileAsString(examplesProjectPath + "src/glace2d/Glace2d.nabla");
		final String genmodel = readFileAsString("src/glace2d/Glace2d.nablagen");
		final IrModule irModule = compilationHelper.getIrModule(model, genmodel);
		
	}
}
