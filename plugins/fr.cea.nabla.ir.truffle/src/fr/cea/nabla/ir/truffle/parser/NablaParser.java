package fr.cea.nabla.ir.truffle.parser;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.xtext.testing.InjectWith;

import com.google.inject.Inject;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.nodes.NablaEvalRootNode;
import fr.cea.nabla.utils.CompilationChainHelper;
import fr.cea.nabla.utils.NablaInjectorProvider;

public class NablaParser {

	private String getGenModel() {
		return "with Test.*;" + "\n" +
		"workflow TestDefaultGenerationChain transforms Test" + "\n" +
		"{" + "\n" +
			"Nabla2Ir nabla2ir" + "\n" +
			"{" + "\n" +
				"timeVariable = t;" + "\n" +
				"deltatVariable = δt;" + "\n" +
				"nodeCoordVariable = X;" + "\n" +
			"}" + "\n" +
			"ReplaceUtf replaceUtf follows nabla2ir" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
			"ReplaceReductions replaceReductions follows replaceUtf" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
			"OptimizeConnectivities optimizeConnectivities follows replaceReductions" + "\n" +
			"{" + "\n" +
				"connectivities = nodes;" + "\n" +
			"}" + "\n" +
			"FillHLTs fillHlts follows optimizeConnectivities" + "\n" +
			"{" + "\n" +
			"}" + "\n" +
		"}";
	}

	public RootCallTarget parseNabla(NablaLanguage nablaLanguage, Source source) {
		String model = source.getCharacters().toString();
		NablaInjectorProvider inj = new NablaInjectorProvider();
		CompilationChainHelper helper = new CompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		final IrModule irModule = helper.getIrModule(model, getGenModel());
		final Map<String, RootCallTarget> callTargets = new HashMap<>();
		final RootCallTarget moduleCallTarget = Truffle.getRuntime()
				.createCallTarget(new NablaNodeFactory(nablaLanguage, callTargets).createModule(irModule));
        final RootNode evalModule = new NablaEvalRootNode(nablaLanguage, moduleCallTarget, callTargets);
        return Truffle.getRuntime().createCallTarget(evalModule);
	}
}
