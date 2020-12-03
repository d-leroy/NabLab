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

import fr.cea.nabla.ir.ir.BaseType
import fr.cea.nabla.ir.ir.ExecuteTimeLoopJob
import fr.cea.nabla.ir.ir.InstructionJob
import fr.cea.nabla.ir.ir.Job
import fr.cea.nabla.ir.ir.TimeLoopJob
import fr.cea.nabla.ir.ir.Variable
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

import static extension fr.cea.nabla.ir.ArgOrVarExtensions.*
import static extension fr.cea.nabla.ir.IrTypeExtensions.*
import static extension fr.cea.nabla.ir.JobCallerExtensions.*
import static extension fr.cea.nabla.ir.JobExtensions.*
import static extension fr.cea.nabla.ir.Utils.*
import static extension fr.cea.nabla.ir.generator.Utils.*

@Data
class JobContentProvider
{
	protected val TraceContentProvider traceContentProvider
	protected val extension ExpressionContentProvider
	protected val extension InstructionContentProvider
	protected val extension JobCallerContentProvider

	def getDeclarationContent(Job it)
	'''
		void «codeName»() noexcept;'''

	def getDefinitionContent(Job it)
	'''
		«comment»
		void «irModule.className»::«codeName»() noexcept
		{
			«innerContent»
		}
	'''

	def copy(Variable source, Variable destination)
	{
		copy(destination.name, source.name, destination.type.dimension)
	}

	protected def dispatch CharSequence getInnerContent(InstructionJob it)
	'''
		«instruction.innerContent»
	'''

	protected def dispatch CharSequence getInnerContent(ExecuteTimeLoopJob it)
	'''
		«callsHeader»
		«val itVar = iterationCounter.codeName»
		«itVar» = 0;
		bool continueLoop = true;
		do
		{
			«IF caller.main»
			globalTimer.start();
			cpuTimer.start();
			«ENDIF»
			«itVar»++;
			«val ppInfo = irRoot.postProcessing»
			«IF caller.main && ppInfo !== null»
				if (!writer.isDisabled() && «ppInfo.periodReference.codeName» >= «ppInfo.lastDumpVariable.codeName» + «ppInfo.periodValue.codeName»)
					dumpVariables(«itVar»);
			«ENDIF»
			«traceContentProvider.getBeginOfLoopTrace(irModule, itVar, caller.main)»

			«callsContent»

			// Evaluate loop condition with variables at time n
			continueLoop = («whileCondition.content»);

			if (continueLoop)
			{
				// Switch variables to prepare next iteration
				«FOR copy : copies»
					std::swap(«copy.source.name», «copy.destination.name»);
				«ENDFOR»
			}
			«IF caller.main»

			cpuTimer.stop();
			globalTimer.stop();
			«ENDIF»

			«traceContentProvider.getEndOfLoopTrace(irModule, itVar, caller.main, (ppInfo !== null))»

			«IF caller.main»
			cpuTimer.reset();
			ioTimer.reset();
			«ENDIF»
		} while (continueLoop);
		«IF caller.main && irRoot.postProcessing !== null»
			// force a last output at the end
			dumpVariables(«itVar», false);
		«ENDIF»
	'''

	protected def dispatch CharSequence getInnerContent(TimeLoopJob it)
	'''
		«FOR copy : copies»
			«copy(copy.source, copy.destination)»
		«ENDFOR»
	'''

	protected def CharSequence copy(String left, String right, int dimension)
	{
		if (dimension == 0)
			'''«left» = «right»;'''
		else
		{
			val indexName = 'i' + dimension
			val suffix = '[' + indexName + ']'
			'''
				for (size_t «indexName»(0) ; «indexName»<«left».size() ; «indexName»++)
					«copy(left + suffix, right + suffix, dimension-1)»
			'''
		}
	}
}

@Data
class KokkosJobContentProvider extends JobContentProvider
{
	override getDeclarationContent(Job it)
	'''
		KOKKOS_INLINE_FUNCTION
		void «codeName»(«FOR a : arguments SEPARATOR ', '»«a»«ENDFOR») noexcept;'''

	override getDefinitionContent(Job it)
	'''
		«comment»
		void «irModule.className»::«codeName»(«FOR a : arguments SEPARATOR ', '»«a»«ENDFOR») noexcept
		{
			«innerContent»
		}
	'''

	override copy(Variable source, Variable destination)
	{
		if (destination.type instanceof BaseType)
			copy(destination.name, source.name, (destination.type as BaseType).sizes.size)
		else
			'''deep_copy(«destination.name», «source.name»);'''
	}

	protected def List<String> getArguments(Job it) { #[] }
}

@Data
class KokkosTeamThreadJobContentProvider extends KokkosJobContentProvider
{
	override getArguments(Job it)
	{
		if (hasIterable) #["const member_type& teamMember"]
		else #[]
	}
}