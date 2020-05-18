// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument;
import java.util.Arrays;
import java.util.Iterator;
import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptor;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionStability;

@GeneratedBy(NablaDumpVariablesInstrument.class)
final class NablaDumpVariablesInstrumentOptionDescriptors implements OptionDescriptors {

    @Override
    public OptionDescriptor get(String optionName) {
        switch (optionName) {
            case "nabla-dump-variables" :
                return OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.ENABLED, "nabla-dump-variables").deprecated(false).help("Enable Nabla Variables Dump (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-dump-variables.jobs" :
                return OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.JOBS, "nabla-dump-variables.jobs").deprecated(false).help("Observe Listed Nabla Jobs (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-dump-variables.jobs-all" :
                return OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.JOBS_ALL, "nabla-dump-variables.jobs-all").deprecated(false).help("Observe All Nabla Jobs (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-dump-variables.variables" :
                return OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.VARIABLES, "nabla-dump-variables.variables").deprecated(false).help("Observe Listed Nabla Variables (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla-dump-variables.variables-all" :
                return OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.VARIABLES_ALL, "nabla-dump-variables.variables-all").deprecated(false).help("Observe All Nabla Variables (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.ENABLED, "nabla-dump-variables").deprecated(false).help("Enable Nabla Variables Dump (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.JOBS, "nabla-dump-variables.jobs").deprecated(false).help("Observe Listed Nabla Jobs (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.JOBS_ALL, "nabla-dump-variables.jobs-all").deprecated(false).help("Observe All Nabla Jobs (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.VARIABLES, "nabla-dump-variables.variables").deprecated(false).help("Observe Listed Nabla Variables (default: \"\").").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaDumpVariablesInstrument.VARIABLES_ALL, "nabla-dump-variables.variables-all").deprecated(false).help("Observe All Nabla Variables (default: false).").category(OptionCategory.USER).stability(OptionStability.STABLE).build())
        .iterator();
    }

}
