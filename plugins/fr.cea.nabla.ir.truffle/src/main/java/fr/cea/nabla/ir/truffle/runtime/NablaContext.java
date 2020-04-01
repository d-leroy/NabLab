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
package fr.cea.nabla.ir.truffle.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;

import org.graalvm.options.OptionKey;

import com.oracle.truffle.api.Scope;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Layout;
import com.oracle.truffle.api.object.Shape;

import fr.cea.nabla.ir.truffle.NablaLanguage;

/**
 * The run-time state of SL during execution. The context is created by the {@link SLLanguage}. It
 * is used, for example, by {@link SLBuiltinNode#getContext() builtin functions}.
 * <p>
 * It would be an error to have two different context instances during the execution of one script.
 * However, if two separate scripts run in one Java VM at the same time, they have a different
 * context. Therefore, the context is not a singleton.
 */
public final class NablaContext {

    static final Layout LAYOUT = Layout.createLayout();

    private final Env env;
    private final BufferedReader input;
    private final PrintWriter output;
    private final NablaFunctionRegistry functionRegistry;
    private final Shape emptyShape;
    private final AllocationReporter allocationReporter;
    private final Iterable<Scope> topScopes; // Cache the top scopes
    private final MeshWrapper meshWrapper;

    public NablaContext(NablaLanguage language, TruffleLanguage.Env env) {
        this.env = env;
        this.input = new BufferedReader(new InputStreamReader(env.in()));
        this.output = new PrintWriter(env.out(), true);
        this.allocationReporter = env.lookup(AllocationReporter.class);
        this.functionRegistry = new NablaFunctionRegistry(language);
        this.topScopes = Collections.singleton(Scope.newBuilder("global", functionRegistry.getFunctionsObject()).build());
        this.emptyShape = LAYOUT.createShape(NablaObjectType.SINGLETON);
        this.meshWrapper = new MeshWrapper();
    }

    /**
     * Return the current Truffle environment.
     */
    public Env getEnv() {
        return env;
    }

    /**
     * Returns the default input, i.e., the source for the {@link SLReadlnBuiltin}. To allow unit
     * testing, we do not use {@link System#in} directly.
     */
    public BufferedReader getInput() {
        return input;
    }

    /**
     * The default default, i.e., the output for the {@link SLPrintlnBuiltin}. To allow unit
     * testing, we do not use {@link System#out} directly.
     */
    public PrintWriter getOutput() {
        return output;
    }

    /**
     * Returns the registry of all functions that are currently defined.
     */
    public NablaFunctionRegistry getFunctionRegistry() {
        return functionRegistry;
    }
    
    public DynamicObject createObject(AllocationReporter reporter) {
        DynamicObject object = null;
        reporter.onEnter(null, 0, AllocationReporter.SIZE_UNKNOWN);
        object = emptyShape.newInstance();
        reporter.onReturnValue(object, 0, AllocationReporter.SIZE_UNKNOWN);
        return object;
    }

    public Iterable<Scope> getTopScopes() {
        return topScopes;
    }

    public static NodeInfo lookupNodeInfo(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        NodeInfo info = clazz.getAnnotation(NodeInfo.class);
        if (info != null) {
            return info;
        } else {
            return lookupNodeInfo(clazz.getSuperclass());
        }
    }
    
    public static NablaContext getCurrent() {
        return NablaLanguage.getCurrentContext();
    }
    
    public static MeshWrapper getMeshWrapper() {
    	return getCurrent().meshWrapper;
    }
    
	public static Object fromForeignValue(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public AllocationReporter getAllocationReporter() {
		return allocationReporter;
	}

	public boolean getOption(OptionKey<Boolean> key) {
		return this.getEnv().getOptions().get(key);
	}

}
