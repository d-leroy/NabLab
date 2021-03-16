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
        if (optionName.startsWith("nabla.javalib.") || optionName.equals("nabla.javalib")) {
            return OptionDescriptor.newBuilder(NablaOptions.JAVA_LIBS, "nabla.javalib").deprecated(false).help("External java libraries (comma-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        } else if (optionName.startsWith("nabla.natlib.") || optionName.equals("nabla.natlib")) {
            return OptionDescriptor.newBuilder(NablaOptions.NAT_LIBS, "nabla.natlib").deprecated(false).help("External native libraries (comma-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        switch (optionName) {
            case "nabla.mesh" :
                return OptionDescriptor.newBuilder(NablaOptions.MESH, "nabla.mesh").deprecated(false).help("Mesh library").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.np" :
                return OptionDescriptor.newBuilder(NablaOptions.NABLAPATH, "nabla.np").deprecated(false).help("Paths to .n/.ngen files and folders containing .n/.ngen files (colon-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.options" :
                return OptionDescriptor.newBuilder(NablaOptions.OPTIONS, "nabla.options").deprecated(false).help("Json file to use for options").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
            case "nabla.wd" :
                return OptionDescriptor.newBuilder(NablaOptions.WD, "nabla.wd").deprecated(false).help("Working directory").category(OptionCategory.USER).stability(OptionStability.STABLE).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(NablaOptions.JAVA_LIBS, "nabla.javalib").deprecated(false).help("External java libraries (comma-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.MESH, "nabla.mesh").deprecated(false).help("Mesh library").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.NAT_LIBS, "nabla.natlib").deprecated(false).help("External native libraries (comma-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.NABLAPATH, "nabla.np").deprecated(false).help("Paths to .n/.ngen files and folders containing .n/.ngen files (colon-separated list of paths)").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.OPTIONS, "nabla.options").deprecated(false).help("Json file to use for options").category(OptionCategory.USER).stability(OptionStability.STABLE).build(),
            OptionDescriptor.newBuilder(NablaOptions.WD, "nabla.wd").deprecated(false).help("Working directory").category(OptionCategory.USER).stability(OptionStability.STABLE).build())
        .iterator();
    }

}
