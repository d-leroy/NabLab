// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import fr.cea.nabla.interpreter.tools.NablaLogInstrument;
import java.util.Arrays;
import java.util.Iterator;
import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptor;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionStability;

@GeneratedBy(NablaLogInstrument.class)
final class NablaLogInstrumentOptionDescriptors implements OptionDescriptors {

    @Override
    public OptionDescriptor get(String optionName) {
        switch (optionName) {
            case "nabla-logger" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.ENABLED, "nabla-logger").deprecated(false).help("Enable Nabla Logger (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.debug-source" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.DEBUG_SOURCE, "nabla-logger.debug-source").deprecated(false).help("Print Source Sections (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.format" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.FORMAT, "nabla-logger.format").deprecated(false).help("Enable Real Numbers Formatting (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.jobs" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.JOBS, "nabla-logger.jobs").deprecated(false).help("Observe Listed Nabla Jobs (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.jobs-all" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.JOBS_ALL, "nabla-logger.jobs-all").deprecated(false).help("Observe All Nabla Jobs (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.variables" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.VARIABLES, "nabla-logger.variables").deprecated(false).help("Observe Listed Nabla Variables (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-logger.variables-all" :
                return OptionDescriptor.newBuilder(NablaLogInstrument.VARIABLES_ALL, "nabla-logger.variables-all").deprecated(false).help("Observe All Nabla Variables (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(NablaLogInstrument.ENABLED, "nabla-logger").deprecated(false).help("Enable Nabla Logger (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.DEBUG_SOURCE, "nabla-logger.debug-source").deprecated(false).help("Print Source Sections (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.FORMAT, "nabla-logger.format").deprecated(false).help("Enable Real Numbers Formatting (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.JOBS, "nabla-logger.jobs").deprecated(false).help("Observe Listed Nabla Jobs (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.JOBS_ALL, "nabla-logger.jobs-all").deprecated(false).help("Observe All Nabla Jobs (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.VARIABLES, "nabla-logger.variables").deprecated(false).help("Observe Listed Nabla Variables (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaLogInstrument.VARIABLES_ALL, "nabla-logger.variables-all").deprecated(false).help("Observe All Nabla Variables (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build())
        .iterator();
    }

}
