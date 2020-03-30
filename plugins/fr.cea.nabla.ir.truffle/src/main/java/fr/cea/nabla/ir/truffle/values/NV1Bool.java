/**
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 */
package fr.cea.nabla.ir.truffle.values;

import java.util.Arrays;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

@ExportLibrary(InteropLibrary.class)
public class NV1Bool implements NablaValue {
	
	private final boolean[] data;

	public NV1Bool(final boolean[] data) {
		this.data = data;
	}

	@ExportMessage
	@TruffleBoundary
	boolean hasArrayElements() {
		return data.length > 0;
	}

	@ExportMessage
	@TruffleBoundary
	long getArraySize() throws UnsupportedMessageException {
		return data.length;
	}

	@ExportMessage
	@TruffleBoundary
	boolean isArrayElementReadable(long index) {
		return index < data.length;
	}

	@ExportMessage
	@TruffleBoundary
	Object readArrayElement(long index) {
		if (index < data.length) {
			return data[(int) index];
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	@TruffleBoundary
	public int hashCode() {
		return 31 * 1 + ((this.data == null) ? 0 : Arrays.hashCode(this.data));
	}

	@Override
	@TruffleBoundary
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NV1Bool other = (NV1Bool) obj;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!Arrays.equals(this.data, other.data))
			return false;
		return true;
	}

	public boolean[] getData() {
		return this.data;
	}
	
	@Override
	public int getDimension(int dimension) {
		assert (dimension == 1);
		return data.length;
	}
}
