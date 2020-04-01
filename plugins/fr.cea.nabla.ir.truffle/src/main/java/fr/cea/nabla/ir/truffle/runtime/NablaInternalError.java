package fr.cea.nabla.ir.truffle.runtime;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleException;
import com.oracle.truffle.api.nodes.Node;

public final class NablaInternalError  extends Error implements TruffleException {

//    private static final String NABLA_ERRORS_LOG = "nabla_errors";

    private static final long serialVersionUID = 80698622974155216L;

//    private static boolean initializing = false;

    private final String verboseStackTrace;

    public NablaInternalError(String message, Object... args) {
        super(String.format(message, args));
        verboseStackTrace = createVerboseStackTrace();
    }

    public NablaInternalError(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
        CompilerAsserts.neverPartOfCompilation();
        verboseStackTrace = createVerboseStackTrace();
    }

    /**
     * Constructor that does not use {@code String.format} so that the message may contain
     * formatting instructions.
     */
    public NablaInternalError(Throwable cause, String message) {
        super(message, cause);
        CompilerAsserts.neverPartOfCompilation();
        verboseStackTrace = createVerboseStackTrace();
    }

    public String getVerboseStackTrace() {
        return verboseStackTrace;
    }

    public static void guarantee(boolean condition, String message) {
        if (!condition) {
            CompilerDirectives.transferToInterpreter();
            throw shouldNotReachHere("failed guarantee: " + message);
        }
    }

    public static void guarantee(boolean condition) {
        if (!condition) {
            CompilerDirectives.transferToInterpreter();
            throw shouldNotReachHere("failed guarantee");
        }
    }

    public static <T> T guaranteeNonNull(T value) {
        if (value == null) {
            CompilerDirectives.transferToInterpreter();
            throw shouldNotReachHere("should not be null");
        }
        return value;
    }

    public static RuntimeException unimplemented() {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError("not implemented");
    }

    public static RuntimeException unimplemented(String message) {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError("not implemented: %s", message);
    }

    public static RuntimeException unimplemented(String format, Object... args) {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError("not implemented: %s", String.format(format, args));
    }

    public static RuntimeException shouldNotReachHere() {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError("should not reach here");
    }

    public static RuntimeException shouldNotReachHere(Throwable cause) {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError(cause, "should not reach here");
    }

    public static RuntimeException shouldNotReachHere(String message) {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError("should not reach here: %s", message);
    }

    public static RuntimeException shouldNotReachHere(Throwable cause, String message) {
        CompilerDirectives.transferToInterpreter();
        throw new NablaInternalError(cause, "should not reach here: %s", message);
    }

    static String createVerboseStackTrace() {
//        NablaContext ctx = NablaContext.getCurrent();
//        if (ctx.getOption(PrintErrorStacktracesToFile) || ctx.getOption(PrintErrorStacktraces)) {
//            if (!initializing) {
//                initializing = true;
//                try {
//                    return Utils.createStackTrace(true);
//                } catch (Throwable t) {
//                    StringWriter str = new StringWriter();
//                    t.printStackTrace(new PrintWriter(str));
//                    return str.toString();
//                } finally {
//                    initializing = false;
//                }
//            } else {
//                return "<exception during stack introspection>";
//            }
//        } else {
            return "";
//        }
    }

//    @TruffleBoundary
//    public static void reportErrorAndConsoleLog(Throwable throwable, int contextId) {
//        reportErrorDefault(throwable, null, contextId);
//    }

//    @TruffleBoundary
//    public static void reportError(Throwable t) {
//        reportErrorDefault(t, null, 0);
//    }

//    @TruffleBoundary
//    public static void reportError(Throwable t, NablaContext ctx) {
//        reportErrorDefault(t, ctx, 0);
//    }

//    @TruffleBoundary
//    private static void reportErrorDefault(Throwable t, NablaContext ctx, int contextId) {
//        NablaContext context = ctx != null ? ctx : NablaContext.getCurrent();
//        boolean detailedMessage = context.getOption(PrintErrorStacktraces) || context.getOption(PrintErrorStacktracesToFile);
//
//        String errMsg = ".";
//        if (detailedMessage) {
//            errMsg = ": \"";
//            errMsg += t instanceof NablaInternalError && t.getMessage() != null && !t.getMessage().isEmpty() ? t.getMessage() : t.getClass().getSimpleName();
//            errMsg += "\"";
//        }
//        reportError(errMsg, t, context, contextId);
//    }

//    private static void reportError(String errMsg, Throwable throwable, NablaContext ctx, int contextId) {
//        try {
//            Throwable t = throwable;
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            t.printStackTrace(new PrintStream(out));
//            String verboseStackTrace;
//            if (t.getCause() != null && t instanceof IOException) {
//                t = t.getCause();
//            }
//            if (t instanceof NablaInternalError) {
//                verboseStackTrace = ((NablaInternalError) t).getVerboseStackTrace();
//            } else if (t instanceof NablaError) {
//                verboseStackTrace = ((NablaError) t).getVerboseStackTrace();
//            } else {
//                verboseStackTrace = "";
//            }
//
//            boolean printErrorStacktraces = getOption(PrintErrorStacktraces, ctx);
//            boolean printErrorStacktracesToFile = getOption(PrintErrorStacktracesToFile, ctx);
//            if (printErrorStacktraces) {
//                System.err.println(out.toString());
//                System.err.println(verboseStackTrace);
//            }
//
//            String message = "An internal error occurred" + errMsg + "\nPlease report an issue at https://github.com/oracle/fastr including the commands";
//            // if ctx == null and we can't determine if stactrace should be logged, or not,
//            // then at least print to file
//            if (printErrorStacktracesToFile) {
//                TruffleFile logfile = Utils.getLogPath(ctx, getLogFileName(contextId));
//                if (logfile != null) {
//                    message += " and the error log file '" + logfile + "'.";
//                    try (BufferedWriter writer = logfile.newBufferedWriter(StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
//                        writer.append(new Date().toString()).append('\n');
//                        writer.append(out.toString()).append('\n');
//                        writer.append(verboseStackTrace).append("\n\n");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    message += ". Cannot write error log file (tried current working directory, user home directory, FastR home directory).";
//                }
//                System.err.println(message);
//                if (NablaContext.isEmbedded()) {
//                    RSuicide.rSuicide("Nabla internal error");
//                }
//            } else {
//                message += ". You can rerun Nabla with --Nabla.PrintErrorStacktracesToFile=true" +
//                                " to turn on internal errors logging. Please attach the log file to the issue if possible.";
//            }
//            if (!printErrorStacktraces && !printErrorStacktracesToFile) {
//                System.err.println(message);
//            }
//        } catch (ExitException | ThreadDeath t) {
//            throw t;
//        } catch (Throwable t) {
//            System.err.println("error while reporting internal error:");
//            t.printStackTrace();
//        }
//    }

//    private static boolean getOption(OptionKey<Boolean> key, NablaContext ctx) {
//        if (ctx != null) {
//            return ctx.getOption(key);
//        } else {
//            return (boolean) NablaOptions.getEnvValue(NablaOptions.getName(key), false);
//        }
//    }

//    private static String getLogFileName(int contextId) {
//        return contextId == 0 ? NABLA_ERRORS_LOG : NABLA_ERRORS_LOG + "-" + contextId;
//    }

    @Override
    public Node getLocation() {
        return null;
    }

    @Override
    public boolean isInternalError() {
        return true;
    }
}