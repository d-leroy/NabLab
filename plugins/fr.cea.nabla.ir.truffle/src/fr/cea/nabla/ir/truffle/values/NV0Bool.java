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
public class NV0Bool implements NablaValue {
  @Accessors
  private boolean data;
  
  public NV0Bool(final boolean data) {
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
    Class<? extends NV0Bool> _class = this.getClass();
    Class<?> _class_1 = obj.getClass();
    boolean _tripleNotEquals = (_class != _class_1);
    if (_tripleNotEquals) {
      return false;
    }
    final NV0Bool other = ((NV0Bool) obj);
    if ((Boolean.valueOf(other.data) != Boolean.valueOf(this.data))) {
      return false;
    }
    return true;
  }
  
  @Pure
  public boolean isData() {
    return this.data;
  }
  
  public void setData(final boolean data) {
    this.data = data;
  }
}
