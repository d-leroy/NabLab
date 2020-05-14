/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.generator.cpp

import fr.cea.nabla.ir.generator.Utils
import fr.cea.nabla.ir.ir.IrModule
import java.util.LinkedHashSet

import static extension fr.cea.nabla.ir.IrModuleExtensions.*

class IncludesContentProvider
{
	protected def Iterable<String> getAdditionalSystemIncludes(IrModule m) { #[] }
	protected def Iterable<String> getAdditionalUserIncludes(IrModule m) { #[] }

	def getContentFor(IrModule m)
	'''
	«FOR include : getSystemIncludesFor(m)»
	#include <«include»>
	«ENDFOR»
	«FOR include : getUserIncludesFor(m)»
	#include "«include»"
	«ENDFOR»
	'''

	private def getSystemIncludesFor(IrModule m)
	{
		val systemIncludes = new LinkedHashSet<String>

		systemIncludes += "fstream"
		systemIncludes += "iomanip"
		systemIncludes += "type_traits"
		systemIncludes += "limits"
		systemIncludes += "utility"
		systemIncludes += "cmath"
		systemIncludes += "rapidjson/document.h"
		systemIncludes += "rapidjson/istreamwrapper.h"
		systemIncludes += m.additionalSystemIncludes

		return systemIncludes
	}

	private def getUserIncludesFor(IrModule m)
	{
		val userIncludes = new LinkedHashSet<String>

		if (m.withMesh)
		{
			userIncludes += "mesh/CartesianMesh2DGenerator.h"
			userIncludes += "mesh/CartesianMesh2D.h"
		}

		userIncludes +=  "utils/Utils.h"
		userIncludes +=  "utils/Timer.h"
		userIncludes +=  "types/Types.h"

		if (m.functions.exists[f | f.body === null && f.provider == m.name]) 
			userIncludes += m.name.toLowerCase + "/" + m.name + Utils::FunctionReductionPrefix + ".h"

		userIncludes += m.additionalUserIncludes
		return userIncludes
	}
}

class StlThreadIncludesContentProvider extends IncludesContentProvider
{
	override getAdditionalUserIncludes(IrModule m)
	{
		val includes = new LinkedHashSet<String>
		if (m.withMesh) includes += "mesh/stl/PvdFileWriter2D.h"
		includes += "utils/stl/Parallel.h"
		if (m.linearAlgebra) includes += "linearalgebra/stl/LinearAlgebraFunctions.h"
		return includes
	}
}

class KokkosIncludesContentProvider extends IncludesContentProvider
{
	override getAdditionalSystemIncludes(IrModule m)
	{
		#["Kokkos_Core.hpp", "Kokkos_hwloc.hpp"]
	}

	override getAdditionalUserIncludes(IrModule m)
	{
		val includes = new LinkedHashSet<String>
		if (m.withMesh) includes += "mesh/kokkos/PvdFileWriter2D.h"
		includes += "utils/kokkos/Parallel.h"
		if (m.linearAlgebra) includes += "linearalgebra/kokkos/LinearAlgebraFunctions.h"
		return includes
	}
}

