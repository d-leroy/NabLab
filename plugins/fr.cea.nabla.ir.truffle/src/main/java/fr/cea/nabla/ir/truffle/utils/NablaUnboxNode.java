/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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
package fr.cea.nabla.ir.truffle.utils;

import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.GenerateUncached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.Node;

import fr.cea.nabla.ir.truffle.values.NablaValue;

/**
 * The node to normalize any value to an SL value. This is useful to reduce the
 * number of values expression nodes need to expect.
 */
@GenerateUncached
public abstract class NablaUnboxNode extends Node {

	public static NablaUnboxNode create() {
        return NablaUnboxNodeGen.create();
    }

    public static NablaUnboxNode getUncached() {
        return NablaUnboxNodeGen.getUncached();
    }

    public Object convert(Object obj) {
        return execute(obj, true);
    }

    public Object convert(Object obj, boolean printWarning) {
        return execute(obj, printWarning);
    }

    protected abstract Object execute(Object obj, boolean printWarning);

    @Specialization(guards = {"!isForeignObject(obj)", "!interop.isNull(obj)"}, limit = "3")
    public Object doForeignObject(TruffleObject obj, boolean printWarning,
                    @CachedLibrary("obj") InteropLibrary interop) {
        try {
			return unbox(obj, interop);
		} catch (UnsupportedMessageException e) {
			e.printStackTrace();
		}
        return null;
    }

    @Fallback
    public Object doObject(Object obj, @SuppressWarnings("unused") boolean printWarning) {
        return obj;
    }

    public static Object unbox(Object obj, InteropLibrary interop) throws UnsupportedMessageException {
        if (interop.isBoolean(obj)) {
            return interop.asBoolean(obj);
        } else if (interop.isNumber(obj)) {
            if (interop.fitsInInt(obj)) {
                return interop.asInt(obj);
            } else if (interop.fitsInDouble(obj)) {
                return interop.asDouble(obj);
            }
        }
        return null;
    }

    protected boolean isNull(Object o) {
        return o == null;
    }
    
    static boolean isForeignObject(Object o) {
    	return !(o instanceof NablaValue);
    }

}
