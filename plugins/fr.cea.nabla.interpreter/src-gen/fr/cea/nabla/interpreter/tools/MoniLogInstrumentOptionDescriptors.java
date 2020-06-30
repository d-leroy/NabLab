// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import fr.cea.nabla.interpreter.tools.MoniLogInstrument;
import java.util.Arrays;
import java.util.Iterator;
import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptor;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionStability;

@GeneratedBy(MoniLogInstrument.class)
final class MoniLogInstrumentOptionDescriptors implements OptionDescriptors {

    @Override
    public OptionDescriptor get(String optionName) {
        switch (optionName) {
            case "monilogger.action" :
                return OptionDescriptor.newBuilder(MoniLogInstrument.ACTION, "monilogger.action").deprecated(false).help("Action to perform (required).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "monilogger.condition" :
                return OptionDescriptor.newBuilder(MoniLogInstrument.CONDITION, "monilogger.condition").deprecated(false).help("Condition for executing the action (optional, default: none, an action is required).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "monilogger.event" :
                return OptionDescriptor.newBuilder(MoniLogInstrument.EVENT, "monilogger.event").deprecated(false).help("Execution rules of interest (optional, default: all execution rules).").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(MoniLogInstrument.ACTION, "monilogger.action").deprecated(false).help("Action to perform (required).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(MoniLogInstrument.CONDITION, "monilogger.condition").deprecated(false).help("Condition for executing the action (optional, default: none, an action is required).").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(MoniLogInstrument.EVENT, "monilogger.event").deprecated(false).help("Execution rules of interest (optional, default: all execution rules).").category(OptionCategory.USER).stability(OptionStability.STABLE).build())
        .iterator();
    }

}
