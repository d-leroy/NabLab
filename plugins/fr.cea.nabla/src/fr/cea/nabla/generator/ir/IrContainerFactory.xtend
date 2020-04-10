/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.generator.ir

import com.google.inject.Inject
import fr.cea.nabla.ir.ir.IrFactory
import fr.cea.nabla.nabla.Connectivity
import fr.cea.nabla.nabla.ItemRef
import java.util.List

import static extension fr.cea.nabla.ConnectivityCallExtensions.*
import fr.cea.nabla.nabla.Container
import fr.cea.nabla.nabla.ConnectivityCall
import fr.cea.nabla.nabla.SetRef

class IrContainerFactory
{
	@Inject extension IrAnnotationHelper
	@Inject extension IrConnectivityFactory
	@Inject extension IrItemIdFactory
	@Inject extension IrSetDefinitionFactory

	// No create method: always an instance creation, always in containment true
	def toIrContainer(Container c)
	{
		switch c
		{
			ConnectivityCall: toIrConnectivityCall(c)
			SetRef: IrFactory::eINSTANCE.createSetRef => [ target = c.target.toIrSetDefinition ]
		}
	}

	// No create method: always an instance creation, always in containment true
	def fr.cea.nabla.ir.ir.ConnectivityCall toIrConnectivityCall(ConnectivityCall cc)
	{
		toIrConnectivityCall(cc.connectivity, cc.args)
	}

	def fr.cea.nabla.ir.ir.ConnectivityCall toIrConnectivityCall(Connectivity c, List<ItemRef> largs)
	{
		IrFactory::eINSTANCE.createConnectivityCall =>
		[
			annotations += c.toIrAnnotation
			connectivity = c.toIrConnectivity
			largs.forEach[x | args += x.toIrId]
		]
	}
}