package fr.cea.nabla.interpreter.parser;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.xtext.util.StringInputStream;
import org.graalvm.options.OptionValues;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.NablaOptions;
import fr.cea.nabla.interpreter.nodes.NablaEvalRootNode;
import fr.cea.nabla.ir.ir.IrModule;

public class NablaParser {

	public RootCallTarget parseNabla(NablaLanguage nablaLanguage, Source source) {
		final Env env = NablaLanguage.getCurrentContext().getEnv();
		final OptionValues options = env.getOptions();
		String jsonOptionsString = options.get(NablaOptions.OPTIONS);
		String irString = source.getCharacters().toString();
		Resource r = new XMLResourceImpl();
		try {
			r.load(new StringInputStream(irString), Collections.emptyMap());
			final IrModule irModule = (IrModule) r.getContents().get(0);
			final Map<String, JsonElement> jsonOptions = new HashMap<>();
			if (jsonOptionsString != null && !jsonOptionsString.isEmpty()) {
				final Gson gson = new Gson();
				gson.fromJson(jsonOptionsString, JsonObject.class).entrySet().forEach(e -> jsonOptions.put(e.getKey(), e.getValue()));
			}
			final RootCallTarget moduleCallTarget = Truffle.getRuntime()
					.createCallTarget(new NablaNodeFactory(nablaLanguage, source).createModule(irModule, jsonOptions));
			final RootNode evalModule = new NablaEvalRootNode(nablaLanguage, moduleCallTarget);
			final RootCallTarget result = Truffle.getRuntime().createCallTarget(evalModule);
			return result;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
