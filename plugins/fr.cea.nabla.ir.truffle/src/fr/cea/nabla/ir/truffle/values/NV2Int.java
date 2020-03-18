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
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

@Data
@ExportLibrary(InteropLibrary.class)
public class NV2Int implements NablaValue {
	private final int[][] data;

	public NV2Int(final int[][] data) {
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
			return new NV1Int(data[(int) index]);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	@Pure
	@TruffleBoundary
	public int hashCode() {
		return 31 * 1 + ((this.data == null) ? 0 : Arrays.deepHashCode(this.data));
	}

	@Override
	@Pure
	@TruffleBoundary
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NV2Int other = (NV2Int) obj;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!Arrays.deepEquals(this.data, other.data))
			return false;
		return true;
	}

	@Override
	@Pure
	@TruffleBoundary
	public String toString() {
		ToStringBuilder b = new ToStringBuilder(this);
		b.add("data", this.data);
		return b.toString();
	}

	@Pure
	public int[][] getData() {
		return this.data;
	}
	
	@Override
	public int getDimension(int dimension) {
		assert (dimension <= 2);
		switch (dimension) {
		case 1: return data.length;
		case 2: return data[0].length;
		default: throw new UnsupportedOperationException();
		}
	}
}
