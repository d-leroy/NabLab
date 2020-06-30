// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Provider;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;
import fr.cea.nabla.interpreter.tools.MoniLogInstrument;
import java.util.Arrays;
import java.util.Collection;

@GeneratedBy(MoniLogInstrument.class)
@Registration(id = "monilogger", name = "MoniLogger", version = "0.0.1")
public class MoniLogInstrumentProvider implements Provider {

    @Override
    public TruffleInstrument create() {
        return new MoniLogInstrument();
    }

    @Override
    public String getInstrumentClassName() {
        return "fr.cea.nabla.interpreter.tools.MoniLogInstrument";
    }

    @Override
    public Collection<String> getServicesClassNames() {
        return Arrays.asList("fr.cea.nabla.interpreter.tools.MoniLogInstrument");
    }

}
