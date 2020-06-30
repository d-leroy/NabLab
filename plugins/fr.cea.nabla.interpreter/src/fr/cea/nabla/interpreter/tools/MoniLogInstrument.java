package fr.cea.nabla.interpreter.tools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionKey;
import org.graalvm.options.OptionStability;
import org.graalvm.options.OptionType;
import org.graalvm.options.OptionValues;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.common.client.configuration.common.ConfigurationCommonVariantStream;
import com.espertech.esper.common.client.configuration.common.ConfigurationCommonVariantStream.TypeVariance;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Option;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.instrumentation.Instrumenter;
import com.oracle.truffle.api.instrumentation.SourceSectionFilter;
import com.oracle.truffle.api.instrumentation.StandardTags.RootTag;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;

@Registration(id = MoniLogInstrument.ID, name = "MoniLogger", version = "0.0.1", services = MoniLogInstrument.class)
public class MoniLogInstrument extends TruffleInstrument {

	static final OptionType<List<String>> STRING_LIST_TYPE = new OptionType<>("String List",
			o -> Arrays.stream(o.split(",")).map(s -> s.trim()).collect(Collectors.toList()));

	@Option(name = "event", help = "Execution rules of interest (optional, default: all execution rules).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> EVENT = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);

	@Option(name = "condition", help = "Condition for executing the action (optional, default: none, an action is required).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<List<String>> CONDITION = new OptionKey<>(new ArrayList<>(), STRING_LIST_TYPE);

	@Option(name = "action", help = "Action to perform (required).", category = OptionCategory.USER, stability = OptionStability.STABLE)
	public static final OptionKey<String> ACTION = new OptionKey<>("");

	public static final String ID = "monilogger";
	public static final String EVENT_OPTION = ID + ".event";
	public static final String CONDITION_OPTION = ID + ".condition";
	public static final String ACTION_OPTION = ID + ".action";

	private Configuration configuration;
	private EPRuntime epRuntime;

	@CompilationFinal
	private int offset;

	@CompilationFinal
	private PrintStream out;

	@Override
	protected void onCreate(Env env) {
		configureEsper(env);
		final OptionValues options = env.getOptions();
		final String action = ACTION.getValue(options);
		if (!action.isEmpty()) {
			enable(env);
			env.registerService(this);
			out = new PrintStream(env.out());
		}
	}

	@TruffleBoundary
	private void configureEsper(Env env) {
		configuration = new Configuration();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(configuration.getClass().getClassLoader());
		try {
			final Map<String, Object> properties = new LinkedHashMap<String, Object>();
			properties.put("value", Object.class);
			configuration.getCommon().addEventType("one", properties);
			final ConfigurationCommonVariantStream variantStream = new ConfigurationCommonVariantStream();
			variantStream.setTypeVariance(TypeVariance.ANY);
			configuration.getCommon().addVariantStream("inputStream", variantStream);
			epRuntime = EPRuntimeProvider.getRuntime(ID, configuration);
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}
	}

	private void enable(final Env env) {
		final Instrumenter instrumenter = env.getInstrumenter();
		final OptionValues options = env.getOptions();

		final List<String> rules = EVENT.getValue(options);
		final SourceSectionFilter filterRules = SourceSectionFilter.newBuilder() //
				.tagIs(RootTag.class) //
				.rootNameIs(s -> rules.isEmpty() || rules.contains(s)) //
				.build();
		final Supplier<ConditionNode> conditionFactory = () -> new TrueNode();
		final Supplier<ActionNode> actionFactory = () -> StreamAppenderNodeGen.create(epRuntime, "one",
				new RootNodeNameNode());
		instrumenter.attachExecutionEventFactory(filterRules, new MoniLoggerNodeFactory(conditionFactory, actionFactory));
	}

	@Override
	protected OptionDescriptors getOptionDescriptors() {
		return new MoniLogInstrumentOptionDescriptors();
	}

}
