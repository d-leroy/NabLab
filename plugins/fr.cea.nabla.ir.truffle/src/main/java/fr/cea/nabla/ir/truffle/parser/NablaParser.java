package fr.cea.nabla.ir.truffle.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.graalvm.options.OptionValues;

import com.oracle.truffle.api.InstrumentInfo;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.ir.ir.ConnectivityVariable;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.ir.PostProcessingInfo;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.NablaOptions;
import fr.cea.nabla.ir.truffle.nodes.NablaEvalRootNode;
import fr.cea.nabla.ir.truffle.tools.NablaDumpVariablesInstrument;

public class NablaParser {

	public RootCallTarget parseNabla(NablaLanguage nablaLanguage, Source source) {
		final Env env = NablaLanguage.getCurrentContext().getEnv();
		final OptionValues options = env.getOptions();
		String genModel = options.get(NablaOptions.Properties).get("genModel");
		String model = source.getCharacters().toString();
		NablaInjectorProvider inj = new NablaInjectorProvider();
		CompilationChainHelper helper = new CompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		final IrModule irModule = helper.getIrModule(model, genModel);
		final PostProcessingInfo postProcessingInfo = irModule.getPostProcessingInfo();
		final NablaDumpVariablesInstrument instrument = getDumpInstrument(env);
		if (instrument != null && postProcessingInfo != null) {
			final String iterationVariable = irModule.getMainTimeLoop().getIterationCounter().getName();
			final String timeVariable = irModule.getTimeVariable().getName();
			final String periodVariable = postProcessingInfo.getPeriodVariable().getName();
			final List<String> cellVariables = new ArrayList<>();
			final List<String> nodeVariables = new ArrayList<>();
			postProcessingInfo.getPostProcessedVariables().stream().filter(v -> v instanceof ConnectivityVariable)
					.map(v -> (ConnectivityVariable) v).forEach(v -> {
						final String name = v.getType().getConnectivities().get(0).getReturnType().getName();
						switch (name) {
						case "cell":
							cellVariables.add(v.getName());
						case "node":
							nodeVariables.add(v.getName());
						}
					});
			final String nodeCoordinatesVariable = irModule.getNodeCoordVariable().getName();
			final double period = postProcessingInfo.getPeriodValue();
			
			final int offset = Integer.parseInt(irModule.getMainTimeLoop().getAnnotations().stream()
					.filter(a -> a.getSource().equals("nabla-origin"))
					.findFirst().map(a -> a.getDetails().stream().filter(e -> e.getKey().equals("offset"))
							.findFirst().map(e -> e.getValue()).orElse("")).orElse(""));
			
			instrument.configure(offset, irModule.getName(), iterationVariable, timeVariable, periodVariable, cellVariables.toArray(new String[0]), nodeVariables.toArray(new String[0]), nodeCoordinatesVariable, period);
		}
		final RootCallTarget moduleCallTarget = Truffle.getRuntime()
				.createCallTarget(new NablaNodeFactory(nablaLanguage, source).createModule(irModule));
		final RootNode evalModule = new NablaEvalRootNode(nablaLanguage, moduleCallTarget);
		return Truffle.getRuntime().createCallTarget(evalModule);
	}

	private NablaDumpVariablesInstrument getDumpInstrument(Env env) {
		final Map<String, InstrumentInfo> instruments = env.getInstruments();
		final InstrumentInfo dumpInstrumentInfo = instruments.get(NablaDumpVariablesInstrument.ID);
		if (dumpInstrumentInfo == null) {
			return null;
		}
		return env.lookup(dumpInstrumentInfo, NablaDumpVariablesInstrument.class);
	}
}
