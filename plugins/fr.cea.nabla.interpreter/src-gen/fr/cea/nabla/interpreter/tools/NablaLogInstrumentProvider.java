// CheckStyle: start generated
package fr.cea.nabla.interpreter.tools;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.instrumentation.TruffleInstrument;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Provider;
import com.oracle.truffle.api.instrumentation.TruffleInstrument.Registration;
import fr.cea.nabla.interpreter.tools.NablaLogInstrument;
import java.util.Arrays;
import java.util.Collection;

@GeneratedBy(NablaLogInstrument.class)
@Registration(id = "nabla-logger", name = "Nabla Logger", version = "0.1.3")
public class NablaLogInstrumentProvider implements Provider {

    @Override
    public TruffleInstrument create() {
        return new NablaLogInstrument();
    }

    @Override
    public String getInstrumentClassName() {
        return "fr.cea.nabla.interpreter.tools.NablaLogInstrument";
    }

    @Override
    public Collection<String> getServicesClassNames() {
        return Arrays.asList("fr.cea.nabla.interpreter.tools.NablaLogInstrument");
    }

}
