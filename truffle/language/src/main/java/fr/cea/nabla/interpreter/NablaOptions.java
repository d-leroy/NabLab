package fr.cea.nabla.interpreter;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionMap;
import org.graalvm.options.OptionStability;

import com.oracle.truffle.api.Option;

@Option.Group("nabla")
public class NablaOptions {
    @Option(name = "model", help = "Model to run", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> MODEL = new OptionKey<>("");
    @Option(name = "genmodel", help = "Genmodel to run", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> GENMODEL = new OptionKey<>("");
    @Option(name = "options", help = "Model to run", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> OPTIONS = new OptionKey<>("");
    @Option(name = "meshlib", help = "Mesh Library", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> MESH_LIB = new OptionKey<>("");
	@Option(name = "extlib", help = "External library", category = OptionCategory.USER, stability = OptionStability.STABLE)
    public static final OptionKey<OptionMap<String>> LIBS = OptionKey.mapOf(String.class);
}
