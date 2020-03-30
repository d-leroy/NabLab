package fr.cea.nabla.ir.truffle.runtime;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.NablaException;

public final class NablaUndefinedNameException extends NablaException {

    private static final long serialVersionUID = 1L;

    @TruffleBoundary
    public static NablaUndefinedNameException undefinedFunction(Node location, Object name) {
        throw new NablaUndefinedNameException("Undefined function: " + name, location);
    }

    @TruffleBoundary
    public static NablaUndefinedNameException undefinedProperty(Node location, Object name) {
        throw new NablaUndefinedNameException("Undefined property: " + name, location);
    }

    private NablaUndefinedNameException(String message, Node node) {
        super(message, node);
    }
}
