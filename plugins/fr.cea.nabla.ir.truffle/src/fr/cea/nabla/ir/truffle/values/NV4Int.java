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

@Data
@SuppressWarnings("all")
public class NV4Int implements NablaValue {
	private final int[][][][] data;

	public NV4Int(final int[][][][] data) {
		super();
		this.data = data;
	}

	@Override
	@Pure
	public int hashCode() {
		return 31 * 1 + ((this.data == null) ? 0 : Arrays.deepHashCode(this.data));
	}

	@Override
	@Pure
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NV4Int other = (NV4Int) obj;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!Arrays.deepEquals(this.data, other.data))
			return false;
		return true;
	}

	@Override
	@Pure
	public String toString() {
		ToStringBuilder b = new ToStringBuilder(this);
		b.add("data", this.data);
		return b.toString();
	}

	@Pure
	public int[][][][] getData() {
		return this.data;
	}

	@Override
	public int getDimension(int dimension) {
		assert (dimension <= 4);
		switch (dimension) {
		case 1:
			return data.length;
		case 2:
			return data[0].length;
		case 3:
			return data[0][0].length;
		case 4:
			return data[0][0][0].length;
		default:
			throw new UnsupportedOperationException();
		}
	}
}
