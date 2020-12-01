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
import com.google.inject.Singleton
import fr.cea.nabla.generator.NablaGeneratorMessageDispatcher.MessageType
import fr.cea.nabla.ui.NabLabConsoleFactory
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Map
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.debug.core.ILaunchConfiguration
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Engine
import org.graalvm.polyglot.Source

@Singleton
class NablaRunner {
	@Inject NabLabConsoleFactory consoleFactory

	static val Engine engine = Engine.newBuilder() //
				.allowExperimentalOptions(true) //
//				.option("engine.TraceCompilation", "true")
				.option("engine.TraceCompilationDetails", "true")
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
		val graalVMHome = configuration.getAttribute(NablaLaunchConstants::GRAAL_HOME_LOCATION, '')
		val source = ResourcesPlugin.workspace.root.getFile(new Path(configuration.getAttribute(NablaLaunchConstants::SOURCE_FILE_LOCATION, '')))	
				.rawLocation.makeAbsolute.toString
		val gen = ResourcesPlugin.workspace.root.getFile(new Path(configuration.getAttribute(NablaLaunchConstants::GEN_FILE_LOCATION, '')))
				.rawLocation.makeAbsolute.toString
		val options = ResourcesPlugin.workspace.root.getFile(new Path(configuration.getAttribute(NablaLaunchConstants::OPTIONS_FILE_LOCATION, '')))
				.rawLocation.makeAbsolute.toString
		val moniloggers = configuration.getAttribute(NablaLaunchConstants::MONILOGGER_FILES_LOCATIONS, newArrayList).filter[s|!s.blank].map[m|
							ResourcesPlugin.workspace.root.getFile(new Path(m)).rawLocation.makeAbsolute.toString]

		val name = source.substring(source.lastIndexOf(File::separator) + 1)

		consoleFactory.openConsole
		val thread = new Thread([
			consoleFactory.clearAndActivateConsole
			consoleFactory.printConsole(MessageType.Start, 'Starting execution: ' + name)
			val t0 = System::nanoTime
			
			doGraal(source, gen, options, moniloggers)
			
			val t = (System::nanoTime - t0) * 0.000000001
			consoleFactory.printConsole(MessageType.End, 'End of execution: ' + name + ' (' + t + 's)')
		])
		
		thread.contextClassLoader = engine.class.classLoader
		
		thread.start
	}
	
	private def double doGraal(String source, String gen, String options, Iterable<String> moniloggers) {
		
		val Map<String, String> optionsMap = newHashMap

		val genmodel = readFileAsString(gen);
		optionsMap.put("nabla.genmodel", genmodel);

		val optionsContent = readFileAsString(options);
		optionsMap.put("nabla.options", optionsContent);
		
		if (!moniloggers.empty) {
			optionsMap.put("monilogger.files", moniloggers.reduce [ s1, s2 |
				s1 + ',' + s2
			])
		}
		
//		val Debugger debugger = Debugger::find(engine)
//		val DebuggerSession session = debugger.startSession([s|println("stopped") s.session.suspendAll s.session.resumeAll])
//		session.suspendAll
		
		val out = new PipedOutputStream
		val consoleIn = new PipedInputStream(out)
		var long t0
		
		try (val context = Context.newBuilder().err(out).out(out).engine(engine) //
				.allowAllAccess(true) //
				.option("nabla.extlib.CartesianMesh2D", "/home/dleroy/git/NabLab/plugins/fr.cea.nabla.cpplib/src/libcppnabla.so") //
				.options(optionsMap) //
				.option("python.Executable", "/home/dleroy/venv-graal/bin/graalpython") //
				.option("python.ForceImportSite", "true") //
				.build();
		) {
			val nablaSource = Source.newBuilder('nabla', new File(source)).build()
			val outputGobbler = new StreamGobbler(consoleIn, consoleFactory)
			outputGobbler.start()
			t0 = System::nanoTime
			context.eval(nablaSource)
		}
		
		out.close
		val t = (System::nanoTime - t0) * 0.000000001
		
		return t
	}
	
	private def double doDefault(String graalVMHome, String source, String gen, String options, Iterable<String> moniloggers) {
		val args = if (moniloggers.empty) {
				#[graalVMHome + '/bin/nabla', source, gen, options]
			} else {
				#[
					graalVMHome + '/bin/nabla',
					source,
					gen,
					options,
					'--monilogger.files=' + moniloggers.reduce [ s1, s2 |
						s1 + ',' + s2
					]
				]
			}

		val pb = new ProcessBuilder(args)
		pb.redirectErrorStream(true)
		val t0 = System::nanoTime
		val p = pb.start
		val outputGobbler = new StreamGobbler(p.getInputStream(), consoleFactory)
		outputGobbler.start
		p.waitFor
		outputGobbler.join
		val t = (System::nanoTime - t0) * 0.000000001
		
		return t
	}
	
	
	private def String readFileAsString(String filePath) throws IOException	{
		return new String(Files.readAllBytes(Paths.get(filePath)))
	}
}

class StreamGobbler extends Thread {
	val InputStream is
	val NabLabConsoleFactory consoleFactory

	new(InputStream is, NabLabConsoleFactory consoleFactory) {
		this.is = is
		this.consoleFactory = consoleFactory
	}

	override void run() {
		try {
			try (val InputStreamReader isr = new InputStreamReader(is);
					val BufferedReader br = new BufferedReader(isr)) {
				var String line = null
				while ((line = br.readLine()) !== null) {
					consoleFactory.printConsole(MessageType.Exec, line)
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace()
		}
	}
}
