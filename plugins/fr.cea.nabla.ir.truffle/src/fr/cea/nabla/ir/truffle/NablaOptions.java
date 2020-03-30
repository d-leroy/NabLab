package fr.cea.nabla.ir.truffle;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionMap;

import com.oracle.truffle.api.Option;

@Option.Group("nabla")
public class NablaOptions {
	@Option(help = "User-defined properties", category = OptionCategory.USER)
    public static final OptionKey<OptionMap<String>> Properties = OptionKey.mapOf(String.class);
}
