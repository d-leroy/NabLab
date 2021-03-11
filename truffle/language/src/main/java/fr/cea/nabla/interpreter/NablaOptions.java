package fr.cea.nabla.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionMap;
import org.graalvm.options.OptionStability;
import org.graalvm.options.OptionType;

import com.oracle.truffle.api.Option;

@Option.Group("nabla")
public class NablaOptions {

	static final OptionType<List<String>> STRING_LIST_TYPE = new OptionType<>("String List",
			o -> Arrays.stream(o.split(":")).map(s -> s.trim()).collect(Collectors.toList()));

	@Option(name = "np", help = "Path to .n files and folders containing .n files (colon-separated list of paths)", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> NABLAPATH = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);
	@Option(name = "options", help = "Model to run", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> OPTIONS = new OptionKey<>("");
	@Option(name = "extlib", help = "External library", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<OptionMap<String>> LIBS = OptionKey.mapOf(String.class);
}
