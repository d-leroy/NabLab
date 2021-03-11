/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ui.graalvm.launchconfig

import com.google.inject.Inject
import com.google.inject.Singleton
import fr.cea.nabla.generator.NablaGeneratorMessageDispatcher.MessageType
import fr.cea.nabla.ui.NabLabConsoleFactory
import fr.cea.nabla.ui.NabLabConsoleHandler
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.util.Map
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Platform
import org.eclipse.debug.core.ILaunchConfiguration
import org.gemoc.monilog.api.MoniLogLibraryLocator
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Engine
import org.graalvm.polyglot.Source
import java.io.InputStreamReader

@Singleton
class NablaRunner {

	@Inject NabLabConsoleFactory consoleFactory

	val Engine engine = Engine.newBuilder() //
				.allowExperimentalOptions(true) //
//				.option("engine.TraceCompilation", "true")
//				.option("engine.TraceNodeExpansion", "true")
//				.option("engine.TraceCompilationDetails", "true")
//				.option("engine.TraceAssumptions", "true")
//				.option("engine.TraceTransferToInterpreter", "true")
//				.option("engine.OSRCompilationThreshold", "2")
//				.option("engine.CompileImmediately", "true")
//				.option("engine.CompilationFailureAction", "Print")
//				.option("engine.TraceInlining", "true")
//				.option("engine.TraceInliningDetails", "true")
//				.option("engine.TracePerformanceWarnings", "all")
				.build()

	package def launch(ILaunchConfiguration configuration) {
		val project = NablaLaunchConstants::getProject(configuration)
		val ngenPath = NablaLaunchConstants::getFile(project, configuration, NablaLaunchConstants.NGEN_FILE_LOCATION).rawLocation.toString
		val nPath = NablaLaunchConstants::getFile(project, configuration, NablaLaunchConstants.N_FILE_LOCATION).rawLocation.toString
		val jsonPath = NablaLaunchConstants::getFile(project, configuration, NablaLaunchConstants.JSON_FILE_LOCATION).rawLocation.toString
		
		val ngenFile = new File(ngenPath)
		
		consoleFactory.printConsole(MessageType.Start, "Starting interpretation process for: " + ngenFile.name)

		val moniloggers = configuration.getAttribute(NablaLaunchConstants::MONILOGGER_FILES_LOCATIONS, newArrayList).filter[s|!s.blank].map[m|
							ResourcesPlugin.workspace.root.getFile(new Path(m)).rawLocation.makeAbsolute.toString]

		val name = ngenPath.substring(ngenPath.lastIndexOf(File::separator) + 1)
		
		Thread.currentThread.contextClassLoader = engine.class.classLoader

		consoleFactory.openConsole
		val thread = new Thread([
			consoleFactory.clearAndActivateConsole
			consoleFactory.printConsole(MessageType.Start, 'Starting execution: ' + name)
			val t0 = System::nanoTime
			
			doGraal(ngenFile, nPath, jsonPath, moniloggers)
			
			val t = (System::nanoTime - t0) * 0.000000001
			consoleFactory.printConsole(MessageType.End, 'End of execution: ' + name + ' (' + t + 's)')
		])
		
		thread.start
	}
	
	private def double doGraal(File ngenFile, String nPath, String jsonPath, Iterable<String> moniloggers) {
		
		val Map<String, String> optionsMap = newHashMap
		
		if (!moniloggers.empty) {
			// TODO get appender and layout classes from plugins, add to classpath
			
			
//			consoleFactory.printConsole(MessageType.Exec, urls.reduce[ s1, s2 | s1 + ', ' + s2 ])
			
			val moniloggerFiles = moniloggers.reduce[ s1, s2 | s1 + ', ' + s2 ]
//					+ ", " + urls.reduce[ s1, s2 | s1 + ', ' + s2 ]
					+ ", /home/dleroy/eclipse-workspace/fr.cea.nabla.ui.graalvm/nablab-monilog/fr/cea/nabla/monilog/nablalib/nablab-moniloglib.mnlg"
			
			optionsMap.put("monilogger.files", moniloggerFiles)
			optionsMap.put("python.Executable", "/home/dleroy/venv-graalpython/bin/graalpython")
			optionsMap.put("python.ForceImportSite", "true")
		}
		
//		println(ClassLoaderUtils::showClassLoaderHierarchy(Engine.classLoader))
//		println
//		println(ClassLoaderUtils::showClassLoaderHierarchy(Debugger.classLoader))
//		
//		val d = Debugger
		
//		val Debugger o = Debugger::find(engine)
//		println(o)
		
//		val Debugger debugger = Debugger::find(engine)
//		val DebuggerSession session = debugger.startSession([s|println("stopped") s.session.suspendAll s.session.resumeAll])
//		session.suspendAll
		
		var long t0
		
		val hostClassLoader = new ExtensionClassLoader(#{"org.gemoc.monilog.appender", "org.gemoc.monilog.layout"},
			Thread.currentThread.contextClassLoader)
		
		MoniLogLibraryLocator.locate().entrySet.map[e|
				val k = e.key
				val v = e.value
//				val bundle = Platform.getBundle(k.contributor.name)
//				val f = new File(FileLocator.getBundleFile(bundle), v)
//				Platform.getBundle(k.contributor.name).loadClass()
				
				consoleFactory.printConsole(MessageType.Exec, v)
				
//				val resourceStream = getClass().getResourceAsStream("/" + v)
				val resourceStream = hostClassLoader.getResourceAsStream(v)
				if (resourceStream !== null) {
					try (val reader = new BufferedReader(new InputStreamReader(resourceStream))) {
						reader.lines.forEach[l|consoleFactory.printConsole(MessageType.Exec, l)]
					}
				}
				
				return v
			].toList
		
		try (val context = Context.newBuilder().engine(engine) //
				.allowAllAccess(true)
				.hostClassLoader(hostClassLoader)
				.option("nabla.np", nPath)
				.option("nabla.options", jsonPath)
				.option("nabla.extlib.CartesianMesh2D", "/home/dleroy/git/NabLab/plugins/fr.cea.nabla.cpplib/src/libcppnabla.so")
				.options(optionsMap)
				.logHandler(new NabLabConsoleHandler(consoleFactory))
				.build()
		) {
//			val proxySource = Source.newBuilder("python",
//				'''
//				class proxy:
//				    def __init__(self, a):
//				        self.a = a
//				    def __len__(self):
//				        return self.a.__len__()
//				    def __getitem__(self, idx):
//				        return self.a.item(idx)
//				    def __setitem__(self, idx, value):
//				        return self.a.__setitem__(idx, value)
//				    def __str__(self):
//				        return self.a.__str__()
//				
//				''', "np-proxy").buildLiteral();
			
			val nablaSource = Source.newBuilder('nabla', ngenFile).build()
			t0 = System::nanoTime
//			context.eval(proxySource)
			val parsedSource = context.parse(nablaSource)
			try {
				parsedSource.execute
			} catch (Exception e) {
				consoleFactory.printConsole(MessageType.Error, e.message)
			}
		}
		
		val t = (System::nanoTime - t0) * 0.000000001
		
		return t
	}
}
