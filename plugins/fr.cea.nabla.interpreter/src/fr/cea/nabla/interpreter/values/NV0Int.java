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
public final class NV0Int implements NablaValue {
	private int data;

	public NV0Int(final int data) {
		this.data = data;
	}

	@ExportMessage
	@TruffleBoundary
	boolean isNumber() {
		return fitsInInt();
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
		return true;
	}

	@ExportMessage
	@TruffleBoundary
	boolean fitsInInt() {
		return true;
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
		return data;
	}

	@ExportMessage
	@TruffleBoundary
	byte asByte() throws UnsupportedMessageException {
		throw UnsupportedMessageException.create();
	}

	@ExportMessage
	@TruffleBoundary
	int asInt() throws UnsupportedMessageException {
		return data;
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
		Class<? extends NV0Int> _class = this.getClass();
		Class<?> _class_1 = obj.getClass();
		boolean _tripleNotEquals = (_class != _class_1);
		if (_tripleNotEquals) {
			return false;
		}
		final NV0Int other = ((NV0Int) obj);
		if ((other.data != this.data)) {
			return false;
		}
		return true;
	}

	public int getData() {
		return this.data;
	}

	public void setData(final int data) {
		this.data = data;
	}
	
	@Override
	public int getDimension(int dimension) {
		throw new UnsupportedOperationException();
	}
}
