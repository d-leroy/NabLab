package fr.cea.nabla.interpreter.runtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionValues;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Scope;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.Layout;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.NablaOptions;
import fr.cea.nabla.interpreter.nodes.local.NablaLexicalScope;

@SuppressWarnings("deprecation")
public final class NablaContext {

	static final Layout LAYOUT = Layout.createLayout();

	private final Env env;
	private final BufferedReader input;
	private final PrintWriter output;
	private final NablaFunctionRegistry functionRegistry;
	private final AllocationReporter allocationReporter;
	@CompilationFinal(dimensions = 1)
	private Value[] nativeLibraries;
	@CompilationFinal(dimensions = 1)
	private String[] libraryProviders;

	private final Assumption contextActive = Truffle.getRuntime().createAssumption("context active");

	public Assumption getContextActive() {
		return contextActive;
	}

	@CompilationFinal
	private MaterializedFrame globalFrame;
	@CompilationFinal
	@Deprecated
	private Iterable<Scope> topScopes;

	public NablaContext(NablaLanguage language, TruffleLanguage.Env env) {
		this.env = env;
		this.input = new BufferedReader(new InputStreamReader(env.in()));
		this.output = new PrintWriter(env.out(), true);
		this.allocationReporter = env.lookup(AllocationReporter.class);
		this.functionRegistry = new NablaFunctionRegistry(language);
	}

	public void initializeNativeExtensions() {
		final OptionValues options = env.getOptions();
		final Set<Entry<String, String>> extensions = options.get(NablaOptions.LIBS).entrySet();

		final String jsonOptionsString = options.get(NablaOptions.OPTIONS);
		final String jsonContent;
		if (jsonOptionsString != null && !jsonOptionsString.isEmpty()) {
			final Gson gson = new Gson();
			final JsonObject jsonOptions = gson.fromJson(jsonOptionsString, JsonObject.class);
			jsonContent = jsonOptions.toString() + "\0";
		} else {
			jsonContent = "\0";
		}

		this.nativeLibraries = new Value[extensions.size()];
		this.libraryProviders = new String[extensions.size()];
		final int[] iPtr = new int[] { 0 };

		extensions.forEach(extension -> {
			final String extensionProvider = extension.getKey();
			final String pathToNativeLibrary = extension.getValue();
			try {
				final Value llvmBindings = Context.getCurrent().getBindings("llvm");
				final org.graalvm.polyglot.Source source = org.graalvm.polyglot.Source
						.newBuilder("llvm", new File(pathToNativeLibrary)).internal(true).build();
				Context.getCurrent().eval(source);
				if (llvmBindings.hasMember(extensionProvider + "_jsonInit")) {
					final Value jsonInit = llvmBindings.getMember(extensionProvider + "_jsonInit");
					this.libraryProviders[iPtr[0]] = extensionProvider;
					this.nativeLibraries[iPtr[0]] = jsonInit.execute(jsonContent);
				} else {
					throw new IllegalArgumentException("Library " + pathToNativeLibrary + " is missing a \""
							+ extensionProvider + "_jsonInit(const void *value)\" initialization function.");
				}
			} catch (IOException e) {
				CompilerDirectives.shouldNotReachHere(e);
			} finally {
				iPtr[0]++;
			}
		});

//		final OptionValues options = env.getOptions();
//		String jsonOptionsString = options.get(NablaOptions.OPTIONS);
//		String pathToMeshLibrary = options.get(NablaOptions.MESH_LIB);
//		if (jsonOptionsString != null && !jsonOptionsString.isEmpty()) {
//			final Gson gson = new Gson();
//			final JsonObject jsonOptions = gson.fromJson(jsonOptionsString, JsonObject.class);
//			final String jsonContent = jsonOptions.toString() + "\0";
//			
//			try {
//				final Value llvmBindings = Context.getCurrent().getBindings("llvm");
//				final org.graalvm.polyglot.Source source = org.graalvm.polyglot.Source.newBuilder("llvm", new File(pathToMeshLibrary)).internal(true).build();
//				Context.getCurrent().eval(source);
//				if (llvmBindings.hasMember("MeshWrapper_jsonInit")) {
//					final Value jsonInit = llvmBindings.getMember("MeshWrapper_jsonInit");
//					this.meshWrapper = jsonInit.execute(jsonContent);
//				} else {
//					throw new IllegalArgumentException(
//							"Library " + pathToMeshLibrary + " is missing a \"MeshWrapper_jsonInit(const void *value)\" function.");
//				}
//			} catch (IOException e) {
//				CompilerDirectives.shouldNotReachHere(e);
//			}
//		}
	}

	public Env getEnv() {
		return env;
	}

	public BufferedReader getInput() {
		return input;
	}

	public PrintWriter getOutput() {
		return output;
	}

	public NablaFunctionRegistry getFunctionRegistry() {
		return functionRegistry;
	}

	// TODO
	@Deprecated
	public Iterable<Scope> getTopScopes() {
		assert topScopes != null;
		return topScopes;
	}

	public static NodeInfo lookupNodeInfo(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		NodeInfo info = clazz.getAnnotation(NodeInfo.class);
		if (info != null) {
			return info;
		} else {
			return lookupNodeInfo(clazz.getSuperclass());
		}
	}

	public static NablaContext getCurrent() {
		return NablaLanguage.getCurrentContext();
	}

	public static Object fromForeignValue(Object object) {
		return null;
	}

	public AllocationReporter getAllocationReporter() {
		return allocationReporter;
	}

	public boolean getOption(OptionKey<Boolean> key) {
		return this.getEnv().getOptions().get(key);
	}

	public Frame getGlobalFrame() {
		return globalFrame;
	}

	public void setGlobalFrame(Node node, MaterializedFrame globalFrame) {
		this.globalFrame = globalFrame;
		final NablaLexicalScope scope = NablaLexicalScope.createScope(node, globalFrame);
		final Scope vscope = Scope.newBuilder(scope.getName(), scope.getVariables(globalFrame)).node(scope.getNode())
				.arguments(scope.getArguments(globalFrame)).rootInstance(null).build();
		topScopes = Collections.singleton(vscope);
	}

//	public Value getMeshWrapper() {
//		return meshWrapper;
//	}

	@ExplodeLoop
	public Value getNativeLibrary(String providerName) {
		Value result = null;
		for (int i = 0; i < nativeLibraries.length; i++) {
			final String libraryProvider = libraryProviders[i];
			if (libraryProvider.equals(providerName)) {
				result = nativeLibraries[i];
				break;
			}
		}
		return result;
	}

}
