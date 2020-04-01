package fr.cea.nabla.ir.truffle;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionMap;
import org.graalvm.options.OptionStability;

import com.oracle.truffle.api.Option;

@Option.Group("nabla")
public class NablaOptions {
	@Option(help = "User-defined properties", category = OptionCategory.USER)
    public static final OptionKey<OptionMap<String>> Properties = OptionKey.mapOf(String.class);
	@Option(category = OptionCategory.INTERNAL, help = "Prints Java and Nabla stack traces for all errors")
    public static final OptionKey<Boolean> PrintErrorStacktraces = new OptionKey<>(false);
    @Option(category = OptionCategory.USER, stability = OptionStability.STABLE, help = "Dumps Java and Nabla stack traces to 'nabla_errors-{context ID}_{PID}.log' for all internal errors")
    public static final OptionKey<Boolean> PrintErrorStacktracesToFile = new OptionKey<>(false);
	
	public static Object getName(OptionKey<Boolean> key) {
		return null;
	}
}
