package fr.cea.nabla.ir.truffle.tools;

import com.oracle.truffle.api.instrumentation.Tag;

public final class NablaTags {

	private NablaTags() {
        /* No instances */
    }
	
    @Tag.Identifier("JOB")
    public static final class JobTag extends Tag {
        private JobTag() {
            /* No instances */
        }
    }
	
    @Tag.Identifier("DUMP")
    public static final class DumpTag extends Tag {
        private DumpTag() {
            /* No instances */
        }
    }
	
    @Tag.Identifier("Write")
    public static final class WriteTag extends Tag {
        private WriteTag() {
            /* No instances */
        }
    }
}
