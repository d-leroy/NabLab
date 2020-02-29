package fr.cea.nabla.ir.truffle;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.source.Source;

import fr.cea.nabla.ir.truffle.parser.NablaParser;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.values.NablaValue;

@TruffleLanguage.Registration(
		id = NablaLanguage.ID,
		name = "Nabla",
		defaultMimeType = NablaLanguage.MIME_TYPE,
		characterMimeTypes = NablaLanguage.MIME_TYPE,
		contextPolicy = ContextPolicy.SHARED,
		fileTypeDetectors = NablaFileDetector.class)
@ProvidedTags({
		StandardTags.CallTag.class,
		StandardTags.StatementTag.class,
		StandardTags.RootTag.class,
		StandardTags.ExpressionTag.class})
public final class NablaLanguage extends TruffleLanguage<NablaContext> {
	
	public static final String ID = "nabla";
	
	public static final String MIME_TYPE = "application/x-nabla";
	
	@Override
	protected NablaContext createContext(Env env) {
		return new NablaContext(this, env);
	}
	
	@Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        Source source = request.getSource();
        return (new NablaParser()).parseNabla(this, source);
	}
	
	@Override
	protected boolean isObjectOfLanguage(Object object) {
//		if (!(object instanceof TruffleObject)) {
//            return false;
//        } else
    	if (object instanceof NablaValue || object instanceof NablaOutput) {
            return true;
        } else {
            return false;
        }
	}

	public static String toString(Object value) {
        try {
            if (value == null) {
                return "ANY";
            }
            InteropLibrary interop = InteropLibrary.getFactory().getUncached(value);
            if (interop.fitsInLong(value)) {
                return Long.toString(interop.asLong(value));
            } else if (interop.isBoolean(value)) {
                return Boolean.toString(interop.asBoolean(value));
            } else if (interop.isString(value)) {
                return interop.asString(value);
            } else if (interop.isNull(value)) {
                return "NULL";
            } else if (interop.isExecutable(value)) {
            	//TODO
//                if (value instanceof SLFunction) {
//                    return ((SLFunction) value).getName();
//                } else {
                    return "Function";
//                }
            } else if (interop.hasMembers(value)) {
                return "Object";
//            } else if (value instanceof SLBigNumber) {
//                return value.toString();
            } else {
                return "Unsupported";
            }
        } catch (UnsupportedMessageException e) {
            CompilerDirectives.transferToInterpreter();
            throw new AssertionError();
        }
    }
	
	public static NablaContext getCurrentContext() {
        return getCurrentContext(NablaLanguage.class);
    }
	
//	private static final List<NodeFactory<? extends NablaBuiltinNode>> EXTERNAL_BUILTINS = Collections.synchronizedList(new ArrayList<>());
}
