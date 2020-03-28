// CheckStyle: start generated
package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.dsl.GeneratedBy;
import fr.cea.nabla.ir.truffle.NablaOptions;
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
        if (optionName.startsWith("nabla.Properties.") || optionName.equals("nabla.Properties")) {
            return OptionDescriptor.newBuilder(NablaOptions.Properties, "nabla.Properties").deprecated(false).help("User-defined properties").category(OptionCategory.USER).stability(OptionStability.EXPERIMENTAL).build();
        }
        return null;
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return Arrays.asList(
            OptionDescriptor.newBuilder(NablaOptions.Properties, "nabla.Properties").deprecated(false).help("User-defined properties").category(OptionCategory.USER).stability(OptionStability.EXPERIMENTAL).build())
        .iterator();
    }

}
