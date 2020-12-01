// CheckStyle: start generated
package fr.cea.nabla.interpreter;

import com.oracle.truffle.api.dsl.GeneratedBy;
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
        if (optionName.startsWith("nabla.extlib.") || optionName.equals("nabla.extlib")) {
            return OptionDescriptor.newBuilder(NablaOptions.LIBS, "nabla.extlib").deprecated(false).help("External library").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        switch (optionName) {
            case "nabla.genmodel" :
                return OptionDescriptor.newBuilder(NablaOptions.GENMODEL, "nabla.genmodel").deprecated(false).help("Genmodel to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.meshlib" :
                return OptionDescriptor.newBuilder(NablaOptions.MESH_LIB, "nabla.meshlib").deprecated(false).help("Mesh Library").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
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
            OptionDescriptor.newBuilder(NablaOptions.LIBS, "nabla.extlib").deprecated(false).help("External library").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.GENMODEL, "nabla.genmodel").deprecated(false).help("Genmodel to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.MESH_LIB, "nabla.meshlib").deprecated(false).help("Mesh Library").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.MODEL, "nabla.model").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.OPTIONS, "nabla.options").deprecated(false).help("Model to run").category(OptionCategory.USER).stability(OptionStability.STABLE).build())
        .iterator();
    }

}
