// CheckStyle: start generated
package fr.cea.nabla.interpreter;

import com.oracle.truffle.api.dsl.GeneratedBy;
import fr.cea.nabla.interpreter.NablaOptions;
import java.util.Arrays;
import java.util.Iterator;
import org.graalvm.options.OptionCategory;
import org.graalvm.options.OptionDescriptor;
import org.graalvm.options.OptionDescriptors;
import org.graalvm.options.OptionStability;

@GeneratedBy(NablaOptions.class)
final class NablaOptionsOptionDescriptors implements OptionDescriptors {

    @Override
    public OptionDescriptor get(String optionName) {
        if (optionName.startsWith("nabla.properties.") || optionName.equals("nabla.properties")) {
            return OptionDescriptor.newBuilder(NablaOptions.properties, "nabla.properties").deprecated(false).help("User-defined properties").category(OptionCategory.USER).stability(OptionStability.EXPERIMENTAL).build();
        }
        switch (optionName) {
            case "nabla.PrintErrorStacktraces" :
                return OptionDescriptor.newBuilder(NablaOptions.PrintErrorStacktraces, "nabla.PrintErrorStacktraces").deprecated(false).help("Prints Java and Nabla stack traces for all errors").category(OptionCategory.INTERNAL).stability(OptionStability.EXPERIMENTAL).build();
            case "nabla.PrintErrorStacktracesToFile" :
                return OptionDescriptor.newBuilder(NablaOptions.PrintErrorStacktracesToFile, "nabla.PrintErrorStacktracesToFile").deprecated(false).help("Dumps Java and Nabla stack traces to 'nabla_errors-{context ID}_{PID}.log' for all internal errors").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.genmodel" :
                return OptionDescriptor.newBuilder(NablaOptions.GENMODEL, "nabla.genmodel").deprecated(false).help("Genmodel to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.model" :
                return OptionDescriptor.newBuilder(NablaOptions.MODEL, "nabla.model").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.options" :
                return OptionDescriptor.newBuilder(NablaOptions.OPTIONS, "nabla.options").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(NablaOptions.PrintErrorStacktraces, "nabla.PrintErrorStacktraces").deprecated(false).help("Prints Java and Nabla stack traces for all errors").category(OptionCategory.INTERNAL).stability(OptionStability.EXPERIMENTAL).build(),
            OptionDescriptor.newBuilder(NablaOptions.PrintErrorStacktracesToFile, "nabla.PrintErrorStacktracesToFile").deprecated(false).help("Dumps Java and Nabla stack traces to 'nabla_errors-{context ID}_{PID}.log' for all internal errors").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.GENMODEL, "nabla.genmodel").deprecated(false).help("Genmodel to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.MODEL, "nabla.model").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.OPTIONS, "nabla.options").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.properties, "nabla.properties").deprecated(false).help("User-defined properties").category(OptionCategory.USER).stability(OptionStability.EXPERIMENTAL).build())
        .iterator();
    }

}
