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

import java.util.Arrays;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

import fr.cea.nabla.interpreter.NablaLanguage;
import fr.cea.nabla.interpreter.runtime.NablaType;

@ExportLibrary(InteropLibrary.class)
public class NV2Int implements NablaValue {
	private final int[][] data;

	public NV2Int(final int[][] data) {
		this.data = data;
	}

	@ExportMessage
	@TruffleBoundary
	boolean hasArrayElements() {
		return true;
	}

	@ExportMessage
	@TruffleBoundary
	long getArraySize() {
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
			return new NV1IntJava(data[(int) index]);
		}
		throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	@TruffleBoundary
	public int hashCode() {
		return 31 * 1 + ((this.data == null) ? 0 : Arrays.deepHashCode(this.data));
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
		NV2Int other = (NV2Int) obj;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!Arrays.deepEquals(this.data, other.data))
			return false;
		return true;
	}

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
	
	@ExportMessage
    boolean hasLanguage() {
        return true;
    }

    @ExportMessage
    Class<? extends TruffleLanguage<?>> getLanguage() {
        return NablaLanguage.class;
    }

    @ExportMessage
    boolean hasMetaObject() {
        return true;
    }

    @ExportMessage
    Object getMetaObject() {
        return NablaType.ARRAY;
    }

    @ExportMessage
    @TruffleBoundary
    Object toDisplayString(@SuppressWarnings("unused") boolean allowSideEffects) {
        return data;
    }
}