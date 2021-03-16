package fr.cea.nabla.interpreter.runtime;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.xtext.util.Strings;
import org.graalvm.polyglot.Value;

import com.google.gson.JsonObject;

public class NablaJNIProviderObject extends NablaProviderObject {

	public NablaJNIProviderObject(String providerName, String extensionName, String path) {
		super(providerName, extensionName, path);
	}

	@Override
	public Value initialize(JsonObject options) {
		try {
			final URL url = new URL(path);
			try (final URLClassLoader javaLibClassLoader = new URLClassLoader(new URL[] { url },
					Thread.currentThread().getContextClassLoader())) {
				try {
					final Class<?> facadeClass = javaLibClassLoader
							.loadClass(providerName.toLowerCase() + '.' + extensionName);
					final Value javaLibValue = Value.asValue(facadeClass.getConstructor().newInstance());
					final String optionString = Strings.toFirstLower(facadeClass.getSimpleName());
					if (options.has(optionString)) {
						javaLibValue.invokeMember("jsonInit", new Object[] { options.get(optionString).toString() });
					}
					return javaLibValue;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
