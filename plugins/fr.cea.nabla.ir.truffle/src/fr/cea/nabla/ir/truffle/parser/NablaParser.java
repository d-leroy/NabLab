package fr.cea.nabla.ir.truffle.parser;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaOptions;
import fr.cea.nabla.ir.truffle.nodes.NablaEvalRootNode;
import fr.cea.nabla.utils.CompilationChainHelper;
import fr.cea.nabla.utils.NablaInjectorProvider;

public class NablaParser {

	public RootCallTarget parseNabla(NablaLanguage nablaLanguage, Source source) {
		String genModel = NablaLanguage.getCurrentContext().getEnv().getOptions().get(NablaOptions.Properties).get("genModel");
		String model = source.getCharacters().toString();
		NablaInjectorProvider inj = new NablaInjectorProvider();
		CompilationChainHelper helper = new CompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		final IrModule irModule = helper.getIrModule(model, genModel);
		final RootCallTarget moduleCallTarget = Truffle.getRuntime()
				.createCallTarget(new NablaNodeFactory(nablaLanguage).createModule(irModule));
        final RootNode evalModule = new NablaEvalRootNode(nablaLanguage, moduleCallTarget);
        return Truffle.getRuntime().createCallTarget(evalModule);
	}
}
