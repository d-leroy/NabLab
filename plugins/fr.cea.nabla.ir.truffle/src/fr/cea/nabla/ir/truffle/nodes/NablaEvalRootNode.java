/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.cea.nabla.ir.truffle.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.RootNode;

import fr.cea.nabla.ir.truffle.NablaLanguage;
import fr.cea.nabla.ir.truffle.runtime.NablaContext;
import fr.cea.nabla.ir.truffle.runtime.NablaNull;

/**
 * This class performs two additional tasks:
 *
 * <ul>
 * <li>Lazily registration of functions on first execution. This fulfills the semantics of
 * "evaluating" source code in SL.</li>
 * <li>Conversion of arguments to types understood by SL. The SL source code can be evaluated from a
 * different language, i.e., the caller can be a node from a different language that uses types not
 * understood by SL.</li>
 * </ul>
 */
public final class NablaEvalRootNode extends RootNode {

//    private final Map<String, RootCallTarget> functions;
    @CompilationFinal private boolean registered;

    @Child private DirectCallNode mainCallNode;

//    public NablaEvalRootNode(NablaLanguage language, RootCallTarget rootFunction, Map<String, RootCallTarget> functions) {
//        super(language);
//        this.functions = functions;
//        this.mainCallNode = rootFunction != null ? DirectCallNode.create(rootFunction) : null;
//    }

    public NablaEvalRootNode(NablaLanguage language, RootCallTarget rootFunction) {
        super(language);
        this.mainCallNode = rootFunction != null ? DirectCallNode.create(rootFunction) : null;
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    protected boolean isInstrumentable() {
        return false;
    }

    @Override
    public String getName() {
        return "root eval";
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public Object execute(VirtualFrame frame) {
        /* Lazy registrations of functions on first execution. */
        if (!registered) {
            /* Function registration is a slow-path operation that must not be compiled. */
            CompilerDirectives.transferToInterpreterAndInvalidate();
//            lookupContextReference(NablaLanguage.class).get().getFunctionRegistry().register(functions);
            registered = true;
        }

        if (mainCallNode == null) {
            return NablaNull.SINGLETON;
        } else {
            Object[] arguments = frame.getArguments();
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = NablaContext.fromForeignValue(arguments[i]);
            }
            return mainCallNode.call(arguments);
        }
    }
}
