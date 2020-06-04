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

import fr.cea.nabla.ir.MandatoryVariables
import fr.cea.nabla.ir.ir.IrModule
import org.eclipse.xtend.lib.annotations.Data

import static extension fr.cea.nabla.ir.IrModuleExtensions.*
import static extension fr.cea.nabla.ir.generator.Utils.*

@Data
class TraceContentProvider
{
	val String maxIterationsVarName
	val String stopTimeVarName

	protected def getHwlocTraceContent()
	'''
		std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  HWLOC unavailable cannot get topological informations" << std::endl;
	'''

	def getBeginOfSimuTrace(IrModule it, String simuName, boolean useMesh)
	'''
		std::cout << "\n" << __BLUE_BKG__ << __YELLOW__ << __BOLD__ <<"\tStarting «simuName» ..." << __RESET__ << "\n\n";

		«IF useMesh»
		«val xee = getVariableByName(MandatoryVariables.X_EDGE_ELEMS).codeName»
		«val yee = getVariableByName(MandatoryVariables.Y_EDGE_ELEMS).codeName»
		«val xel = getVariableByName(MandatoryVariables.X_EDGE_LENGTH).codeName»
		«val yel = getVariableByName(MandatoryVariables.Y_EDGE_LENGTH).codeName»
		std::cout << "[" << __GREEN__ << "MESH" << __RESET__ << "]      X=" << __BOLD__ << «xee» << __RESET__ << ", Y=" << __BOLD__ << «yee»
			<< __RESET__ << ", X length=" << __BOLD__ << «xel» << __RESET__ << ", Y length=" << __BOLD__ << «yel» << __RESET__ << std::endl;
		«ENDIF»

		«hwlocTraceContent»
		«IF useMesh»

		«IF postProcessingInfo !== null»
		if (!writer.isDisabled())
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    VTK files stored in " << __BOLD__ << writer.outputDirectory() << __RESET__ << " directory" << std::endl;
		else
		«ENDIF»
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    " << __BOLD__ << "Disabled" << __RESET__ << std::endl;
		«ENDIF»
	'''

	def getBeginOfLoopTrace(String iterationVarName, String timeVarName, boolean isTopLoop)
	'''
		«IF isTopLoop»
		if («iterationVarName»!=1)
			std::cout << "[" << __CYAN__ << __BOLD__ << setw(3) << «iterationVarName» << __RESET__ "] t = " << __BOLD__
				<< setiosflags(std::ios::scientific) << setprecision(8) << setw(16) << «timeVarName» << __RESET__;
		«ENDIF»
	'''

	def getEndOfLoopTrace(String iterationVarName, String timeVarName, String deltatVarName, boolean isTopLoop, boolean hasWriter)
	'''
		«IF isTopLoop»
		// Timers display
		«IF hasWriter»
		if (!writer.isDisabled())
			std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __BLUE__ << ioTimer.print(true) << __RESET__ "} ";
		else
		«ENDIF»
			std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __RED__ << "none" << __RESET__ << "} ";

		// Progress
		std::cout << utils::progress_bar(«iterationVarName», options.«maxIterationsVarName», «timeVarName», options.«stopTimeVarName», 25);
		std::cout << __BOLD__ << __CYAN__ << utils::Timer::print(
			utils::eta(«iterationVarName», options.«maxIterationsVarName», «timeVarName», options.«stopTimeVarName», «deltatVarName», globalTimer), true)
			<< __RESET__ << "\r";
		std::cout.flush();
		«ENDIF»
	'''

	def getEndOfSimuTrace()
	'''
		std::cout << __YELLOW__ << "\n\tDone ! Took " << __MAGENTA__ << __BOLD__ << globalTimer.print() << __RESET__ << std::endl;
	'''

	def getCGInfoTrace(String iterationVarName)
	'''
		std::cout << "[CG] average iteration: " << cg_info.m_nb_it / «iterationVarName» << std::endl;
	'''
}

@Data
class KokkosTraceContentProvider extends TraceContentProvider
{
	override getHwlocTraceContent()
	'''
		if (Kokkos::hwloc::available())
		{
			std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  NUMA=" << __BOLD__ << Kokkos::hwloc::get_available_numa_count()
				<< __RESET__ << ", Cores/NUMA=" << __BOLD__ << Kokkos::hwloc::get_available_cores_per_numa()
				<< __RESET__ << ", Threads/Core=" << __BOLD__ << Kokkos::hwloc::get_available_threads_per_core() << __RESET__ << std::endl;
		}
		else
		{
			«super.hwlocTraceContent»
		}

		// std::cout << "[" << __GREEN__ << "KOKKOS" << __RESET__ << "]    " << __BOLD__ << (is_same<MyLayout,Kokkos::LayoutLeft>::value?"Left":"Right")" << __RESET__ << " layout" << std::endl;
	'''
}
