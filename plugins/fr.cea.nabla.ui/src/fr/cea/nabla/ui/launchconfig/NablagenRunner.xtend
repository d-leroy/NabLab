/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ui.launchconfig

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.Singleton
import fr.cea.nabla.generator.NablaGeneratorMessageDispatcher
import fr.cea.nabla.generator.NablaGeneratorMessageDispatcher.MessageType
import fr.cea.nabla.generator.NablagenInterpreter
import fr.cea.nabla.ir.Utils
import fr.cea.nabla.ir.interpreter.IrInterpreter
import fr.cea.nabla.ir.ir.ExtensionProvider
import fr.cea.nabla.nablagen.NablagenRoot
import fr.cea.nabla.ui.NabLabConsoleFactory
import fr.cea.nabla.ui.NabLabConsoleHandler
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.ArrayList
import java.util.logging.Level
import java.util.regex.Pattern
import java.util.stream.Collectors
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil

@Singleton
class NablagenRunner
{
	@Inject Provider<ResourceSet> resourceSetProvider
	@Inject Provider<NablagenInterpreter> ngenInterpreterProvider
	@Inject NabLabConsoleFactory consoleFactory
	@Inject NablaGeneratorMessageDispatcher dispatcher

	package def launch(IFile nablagenFile, IFile jsonFile)
	{
		val ngenInterpreter = ngenInterpreterProvider.get
		consoleFactory.openConsole
		consoleFactory.clearAndActivateConsole
		val traceFunction = [MessageType type, String msg | consoleFactory.printConsole(type, msg)]
		dispatcher.traceListeners += traceFunction

		new Thread
		([
			try
			{
				consoleFactory.printConsole(MessageType.Start, "Starting interpretation process for: " + nablagenFile.name)
				consoleFactory.printConsole(MessageType.Exec, "Loading nablagen and nabla resources")

				if (nablagenFile === null || !nablagenFile.exists) throw new RuntimeException("Invalid file: " + nablagenFile.fullPath)
				val plaftormUri = URI::createPlatformResourceURI(nablagenFile.project.name + '/' + nablagenFile.projectRelativePath, true)
				val resourceSet = resourceSetProvider.get
				val uriMap = resourceSet.URIConverter.URIMap
				uriMap.put(URI::createURI('platform:/resource/fr.cea.nabla/'), URI::createURI('platform:/plugin/fr.cea.nabla/'))
				val emfResource = resourceSet.createResource(plaftormUri)
				EcoreUtil::resolveAll(resourceSet)
				emfResource.load(null)

				val baseDir = nablagenFile.project.location.toString
				val ngen = emfResource.contents.filter(NablagenRoot).head
				val ir = ngenInterpreter.buildInterpreterIr(ngen, baseDir)

				consoleFactory.printConsole(MessageType.Exec, "Starting code interpretation")
				val startTime = System.currentTimeMillis
				val handler = new NabLabConsoleHandler(consoleFactory)
				handler.level = Level.FINE
				val irInterpreter = new IrInterpreter(ir, handler)
				irInterpreter.classloaderUrls = buildUrls(ir.providers)
				if (jsonFile === null || !jsonFile.exists) throw new RuntimeException("Invalid file: " + jsonFile.fullPath)
				val jsonContent = new BufferedReader(new InputStreamReader(jsonFile.contents)).lines().collect(Collectors.joining("\n"))
				irInterpreter.interprete(jsonContent)
				val endTime = System.currentTimeMillis
				consoleFactory.printConsole(MessageType.Exec, "Code interpretation ended in " + (endTime-startTime)/1000.0 + "s")

				nablagenFile.project.refreshLocal(IResource::DEPTH_INFINITE, null)
				consoleFactory.printConsole(MessageType.End, "Interpretation ended successfully for: " + nablagenFile.name)
			}
			catch (Exception e)
			{
				consoleFactory.printConsole(MessageType.Error, "Interpretation failed for: " + nablagenFile.name)
				consoleFactory.printConsole(MessageType.Error, e.message)
				consoleFactory.printConsole(MessageType.Error, Utils.getStackTrace(e))
			}
		]).start

		dispatcher.traceListeners -= traceFunction
	}

	private def buildUrls(ExtensionProvider[] providers)
	{
		val pattern = Pattern.compile("\\$ENV\\{([\\w]+)\\}")
		val urls = new ArrayList<URL>
		for (p : providers)
		{
			if (!p.installDir.nullOrEmpty)
			{
				var expandedInstallDir = p.installDir
				var matcher = pattern.matcher(expandedInstallDir)
				while (matcher.find())
				{
					val envVarName = matcher.group(1)
					val envVar = System.getenv(envVarName)
					expandedInstallDir = matcher.replaceFirst(envVar)
					matcher = pattern.matcher(expandedInstallDir)
				}
				val urlText = "file://" + expandedInstallDir + "/" + p.libName + ".jar"
				urls += new URL(urlText)
			}
		}
		return urls
	}
}