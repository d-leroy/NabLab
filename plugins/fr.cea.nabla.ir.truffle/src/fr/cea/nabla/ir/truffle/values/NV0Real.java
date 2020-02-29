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

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class NV0Real implements NablaValue {
  @Accessors
  private double data;
  
  public NV0Real(final double data) {
    this.data = data;
  }
  
  @Override
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
  
  @Pure
  public double getData() {
    return this.data;
  }
  
  public void setData(final double data) {
    this.data = data;
  }
}
