/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.transformers

import fr.cea.nabla.ir.ir.IrModule
import java.util.List

class OptimizeConnectivities extends IrTransformationStep
{
	val List<String> connectivities

	new(List<String> connectivities)
	{
		super('Annotate connectivities when Id and Index are equals (ex: cells)')
		this.connectivities = connectivities
	}

	override transform(IrModule m)
	{
		trace('IR -> IR: ' + description + '\n')
		for (c : m.connectivities)
		{
			if (!c.multiple) c.indexEqualId = true
			else if (connectivities.contains(c.name)) c.indexEqualId = true
		}
		return true
	}
}