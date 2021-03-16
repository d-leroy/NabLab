package fr.cea.nabla.interpreter.runtime;

import org.graalvm.polyglot.Value;

import com.google.gson.JsonObject;

public abstract class NablaProviderObject {

	protected final String providerName;
	protected final String extensionName;
	protected final String path;
	
	public NablaProviderObject(String providerName, String extensionName, String path) {
		this.providerName = providerName;
		this.extensionName = extensionName;
		this.path = path;
	}
	
	public abstract Value initialize(JsonObject options);
}
