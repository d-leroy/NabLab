// CheckStyle: start generated
package fr.cea.nabla.interpreter;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleFile.FileTypeDetector;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.TruffleLanguage.Provider;
import com.oracle.truffle.api.TruffleLanguage.Registration;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags.CallTag;
import com.oracle.truffle.api.instrumentation.StandardTags.ExpressionTag;
import com.oracle.truffle.api.instrumentation.StandardTags.RootBodyTag;
import com.oracle.truffle.api.instrumentation.StandardTags.RootTag;
import com.oracle.truffle.api.instrumentation.StandardTags.StatementTag;
import com.oracle.truffle.api.instrumentation.StandardTags.WriteVariableTag;
import fr.cea.nabla.interpreter.NablaFileDetector;
import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.tools.NablaTags.DumpTag;
import fr.cea.nabla.interpreter.tools.NablaTags.JobTag;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@GeneratedBy(NablaLanguage.class)
@Registration(characterMimeTypes = {"application/x-nabla"}, contextPolicy = ContextPolicy.SHARED, defaultMimeType = "application/x-nabla", id = "nabla", name = "Nabla")
@ProvidedTags({CallTag.class, StatementTag.class, RootTag.class, RootBodyTag.class, ExpressionTag.class, WriteVariableTag.class, DumpTag.class, JobTag.class})
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
        return "fr.cea.nabla.interpreter.NablaLanguage";
    }

    @Override
    public Collection<String> getServicesClassNames() {
        return Collections.emptySet();
    }

}
