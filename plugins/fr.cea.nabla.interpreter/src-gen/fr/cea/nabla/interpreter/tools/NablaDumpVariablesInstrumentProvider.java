// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Provider;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;
import fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument;
import java.util.Arrays;
import java.util.Collection;

@GeneratedBy(NablaDumpVariablesInstrument.class)
@Registration(id = "nabla-dump-variables", name = "Nabla Variables Dump", version = "0.1.3")
public class NablaDumpVariablesInstrumentProvider implements Provider {

    @Override
    public TruffleInstrument create() {
        return new NablaDumpVariablesInstrument();
    }

    @Override
    public String getInstrumentClassName() {
        return "fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument";
    }

    @Override
    public Collection<String> getServicesClassNames() {
        return Arrays.asList("fr.cea.nabla.interpreter.tools.NablaDumpVariablesInstrument");
    }

}
