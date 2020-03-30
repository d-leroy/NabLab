/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
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
package fr.cea.nabla.ir.truffle.nodes.expression;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import fr.cea.nabla.ir.truffle.values.NV0Bool;
import fr.cea.nabla.ir.truffle.values.NV0Int;
import fr.cea.nabla.ir.truffle.values.NV0Real;
import fr.cea.nabla.ir.truffle.values.NV1Bool;
import fr.cea.nabla.ir.truffle.values.NV1Int;
import fr.cea.nabla.ir.truffle.values.NV1Real;
import fr.cea.nabla.ir.truffle.values.NV2Bool;
import fr.cea.nabla.ir.truffle.values.NV2Int;
import fr.cea.nabla.ir.truffle.values.NV2Real;
import fr.cea.nabla.ir.truffle.values.NV3Int;
import fr.cea.nabla.ir.truffle.values.NV3Real;
import fr.cea.nabla.ir.truffle.values.NV4Int;
import fr.cea.nabla.ir.truffle.values.NV4Real;

public class NablaParenthesisNode extends NablaExpressionNode {

    @Child private NablaExpressionNode expression;

    public NablaParenthesisNode(NablaExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return expression.executeGeneric(frame);
    }
    
    @Override
    public NV0Bool executeNV0Bool(VirtualFrame frame) throws UnexpectedResultException {
    	return expression.executeNV0Bool(frame);
    }

	@Override
	public NV0Int executeNV0Int(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV0Int(frame);
	}

	@Override
	public NV0Real executeNV0Real(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV0Real(frame);
	}

	@Override
	public NV1Bool executeNV1Bool(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV1Bool(frame);
	}

	@Override
	public NV1Int executeNV1Int(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV1Int(frame);
	}

	@Override
	public NV1Real executeNV1Real(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV1Real(frame);
	}

	@Override
	public NV2Bool executeNV2Bool(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV2Bool(frame);
	}

	@Override
	public NV2Int executeNV2Int(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV2Int(frame);
	}

	@Override
	public NV2Real executeNV2Real(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV2Real(frame);
	}

	@Override
	public NV3Int executeNV3Int(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV3Int(frame);
	}

	@Override
	public NV3Real executeNV3Real(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV3Real(frame);
	}

	@Override
	public NV4Int executeNV4Int(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV4Int(frame);
	}

	@Override
	public NV4Real executeNV4Real(VirtualFrame frame) throws UnexpectedResultException {
		return expression.executeNV4Real(frame);
	}
}
