// CheckStyle: start generated
package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleFile.FileTypeDetector;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.TruffleLanguage.Provider;
import com.oracle.truffle.api.TruffleLanguage.Registration;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags.CallTag;
import com.oracle.truffle.api.instrumentation.StandardTags.ExpressionTag;
import com.oracle.truffle.api.instrumentation.StandardTags.RootTag;
import com.oracle.truffle.api.instrumentation.StandardTags.StatementTag;
import fr.cea.nabla.ir.truffle.NablaFileDetector;
import fr.cea.nabla.ir.truffle.NablaLanguage;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@GeneratedBy(NablaLanguage.class)
@Registration(characterMimeTypes = {"application/x-nabla"}, contextPolicy = ContextPolicy.SHARED, defaultMimeType = "application/x-nabla", id = "nabla", name = "Nabla")
@ProvidedTags({CallTag.class, StatementTag.class, RootTag.class, ExpressionTag.class})
public class NablaLanguageProvider implements Provider {

    @Override
    public TruffleLanguage<?> create() {
        return new NablaLanguage();
    }

    @Override
    public List<FileTypeDetector> createFileTypeDetectors() {
        return Arrays.asList(new NablaFileDetector());
    }

    @Override
    public String getLanguageClassName() {
        return "fr.cea.nabla.ir.truffle.NablaLanguage";
    }

    @Override
    public Collection<String> getServicesClassNames() {
        return Collections.emptySet();
    }

}
