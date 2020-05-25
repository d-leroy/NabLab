package fr.cea.nabla.interpreter.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.graalvm.options.OptionValues;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oracle.truffle.api.InstrumentInfo;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.NablaOptions;
import fr.cea.nabla.interpreter.nodes.NablaEvalRootNode;
import fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument;
import fr.cea.nabla.ir.ir.ConnectivityVariable;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.ir.PostProcessingInfo;
import fr.cea.nabla.ir.ir.SimpleVariable;

public class NablaParser {

	public RootCallTarget parseNabla(NablaLanguage nablaLanguage, Source source) {
		System.out.println("Begin parsing");
		final long t = System.nanoTime();
		final Env env = NablaLanguage.getCurrentContext().getEnv();
		final OptionValues options = env.getOptions();
		String genModel = options.get(NablaOptions.MODEL);
		String jsonOptionsString = options.get(NablaOptions.OPTIONS);
		String model = source.getCharacters().toString();
		NablaInjectorProvider inj = new NablaInjectorProvider();
		CompilationChainHelper helper = new CompilationChainHelper();
		inj.getInjector().injectMembers(helper);
		final IrModule irModule = helper.getIrModule(model, genModel);
		final PostProcessingInfo postProcessingInfo = irModule.getPostProcessingInfo();
		if (postProcessingInfo != null) {
			final NablaDumpVariablesInstrument instrument = getDumpInstrument(env);
			if (instrument != null) {
				final String iterationVariable = irModule.getMainTimeLoop().getIterationCounter().getName();
				final String timeVariable = irModule.getTimeVariable().getName();
				final String periodVariable = postProcessingInfo.getPeriodReference().getName();
				final List<String> cellVariables = new ArrayList<>();
				final List<String> nodeVariables = new ArrayList<>();
				postProcessingInfo.getOutputVariables().stream().filter(v -> v instanceof ConnectivityVariable)
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
				final SimpleVariable period = postProcessingInfo.getPeriodValue();

				instrument.configure(irModule.getName(), iterationVariable, timeVariable, periodVariable,
						cellVariables.toArray(new String[0]), nodeVariables.toArray(new String[0]),
						nodeCoordinatesVariable, period);
			}
		}
		final Map<String, JsonElement> jsonOptions = new HashMap<>();
		if (jsonOptionsString != null && !jsonOptionsString.isEmpty()) {
			final Gson gson = new Gson();
			gson.fromJson(jsonOptionsString, JsonObject.class).entrySet().forEach(e -> jsonOptions.put(e.getKey(), e.getValue()));
		}
		final RootCallTarget moduleCallTarget = Truffle.getRuntime()
				.createCallTarget(new NablaNodeFactory(nablaLanguage, source).createModule(irModule, jsonOptions));
		final RootNode evalModule = new NablaEvalRootNode(nablaLanguage, moduleCallTarget);
		final RootCallTarget result = Truffle.getRuntime().createCallTarget(evalModule);
		System.out.println("Done parsing, duration: " + ((System.nanoTime() - t) / 1000000));
		return result;
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
