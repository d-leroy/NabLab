/**
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 */
package fr.cea.nabla.interpreter.values;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

@ExportLibrary(InteropLibrary.class)
public class NV0Real implements NablaValue {
	private double data;

	public NV0Real(final double data) {
		this.data = data;
	}
	
	@ExportMessage
	@TruffleBoundary
	boolean isNumber() {
		return fitsInDouble();
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInByte() {
		return false;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInShort() {
		return false;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInFloat() {
		return false;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInLong() {
		return false;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInInt() {
		return false;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInDouble() {
		return true;
	}

	@ExportMessage
	@TruffleBoundary
	double asDouble() throws UnsupportedMessageException {
		return data;
	}

	@ExportMessage
	@TruffleBoundary
	long asLong() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@ExportMessage
	@TruffleBoundary
	byte asByte() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@ExportMessage
	@TruffleBoundary
	int asInt() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@ExportMessage
	@TruffleBoundary
	float asFloat() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@ExportMessage
	@TruffleBoundary
	short asShort() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@Override
	@TruffleBoundary
	public boolean equals(final Object obj) {
		if ((this == obj)) {
			return true;
		}
		if ((obj == null)) {
			return false;
		}
		Class<? extends NV0Real> _class = this.getClass();
		Class<?> _class_1 = obj.getClass();
		boolean _tripleNotEquals = (_class != _class_1);
		if (_tripleNotEquals) {
			return false;
		}
		final NV0Real other = ((NV0Real) obj);
		if ((other.data != this.data)) {
			return false;
		}
		return true;
	}
	
	@Override
	@TruffleBoundary
	public String toString() {
		return "" + this.data;
	}

	public double getData() {
		return this.data;
	}

	public void setData(final double data) {
		this.data = data;
	}
	
	@Override
	public int getDimension(int dimension) {
		throw new UnsupportedOperationException();
	}
}
