/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.generator.java

import fr.cea.nabla.ir.generator.CodeGenerator
import fr.cea.nabla.ir.ir.Connectivity
import fr.cea.nabla.ir.ir.ConnectivityVariable
import fr.cea.nabla.ir.ir.Function
import fr.cea.nabla.ir.ir.IrModule
import fr.cea.nabla.ir.transformers.TagOutputVariables

import static extension fr.cea.nabla.ir.ArgOrVarExtensions.*
import static extension fr.cea.nabla.ir.IrModuleExtensions.*
import static extension fr.cea.nabla.ir.IrTypeExtensions.*
import static extension fr.cea.nabla.ir.Utils.*
import static extension fr.cea.nabla.ir.generator.Utils.*
import static extension fr.cea.nabla.ir.generator.java.ArgOrVarExtensions.*
import static extension fr.cea.nabla.ir.generator.java.ExpressionContentProvider.*
import static extension fr.cea.nabla.ir.generator.java.FunctionContentProvider.*
import static extension fr.cea.nabla.ir.generator.java.JobContentProvider.*

class Ir2Java extends CodeGenerator
{
	new() { super('Java') }

	override getFileContentsByName(IrModule it)
	{
		#{ name + '.java' -> javaFileContent }
	}

	private def getJavaFileContent(IrModule it)
	'''
		package «name.toLowerCase»;

		import java.io.FileNotFoundException;
		import java.io.FileReader;
		import java.util.HashMap;
		import java.util.stream.IntStream;

		import com.google.gson.Gson;
		import com.google.gson.GsonBuilder;
		import com.google.gson.JsonObject;
		import com.google.gson.JsonParser;
		import com.google.gson.stream.JsonReader;

		import fr.cea.nabla.javalib.types.*;
		import fr.cea.nabla.javalib.mesh.*;

		«IF linearAlgebra»
		import org.apache.commons.math3.linear.*;

		«ENDIF»
		@SuppressWarnings("all")
		public final class «name»
		{
			public final static class Options
			{
				«IF postProcessingInfo !== null»
				public String «TagOutputVariables.OutputPathNameAndValue.key»;
				«ENDIF»
				«FOR v : allOptions»
				public «v.javaType» «v.name»;
				«ENDFOR»
			}

			// Mesh and mesh variables
			private final «javaMeshClassName» mesh;
			«FOR c : connectivities.filter[multiple] BEFORE 'private final int ' SEPARATOR ', '»«c.nbElemsVar»«ENDFOR»;

			// User options and external classes
			private final Options options;
			«FOR s : allProviders»
			private «s» «s.toFirstLower»;
			«ENDFOR»
			«IF postProcessingInfo !== null»private final FileWriter writer;«ENDIF»

			// Global definitions
			«FOR v : allDefinitions»
			private «IF v.const»final «ENDIF»«v.javaType» «v.name»;
			«ENDFOR»

			// Global declarations
			«FOR v : declarations»
			private «v.javaType» «v.name»;
			«ENDFOR»

			public «name»(«javaMeshClassName» aMesh, Options aOptions«FOR s : allProviders BEFORE ', ' SEPARATOR ', '»«s» a«s»«ENDFOR»)
			{
				// Mesh and mesh variables initialization
				mesh = aMesh;
				«FOR c : connectivities.filter[multiple]»
				«c.nbElemsVar» = «c.connectivityAccessor»;
				«ENDFOR»

				// User options and external classes initialization
				options = aOptions;
				«FOR s : allProviders»
					«s.toFirstLower» = a«s»;
				«ENDFOR»
				«IF postProcessingInfo !== null»writer = new PvdFileWriter2D("«name»", options.«TagOutputVariables.OutputPathNameAndValue.key»);«ENDIF»

				// Initialize variables with default values
				«FOR v : allDefinitions»
					«v.name» = «v.defaultValue.content»;
				«ENDFOR»

				// Allocate arrays
				«FOR v : declarations.filter[!type.scalar]»
					«IF v.linearAlgebra»
						«v.name» = «(v as ConnectivityVariable).linearAlgebraDefinition»;
					«ELSE»
						«v.name»«v.javaAllocation»;
					«ENDIF»
				«ENDFOR»

				// Copy node coordinates
				double[][] gNodes = mesh.getGeometry().getNodes();
				IntStream.range(0, nbNodes).parallel().forEach(rNodes ->
				{
					«initNodeCoordVariable.name»[rNodes][0] = gNodes[rNodes][0];
					«initNodeCoordVariable.name»[rNodes][1] = gNodes[rNodes][1];
				});
			}

			public void simulate()
			{
				System.out.println("Start execution of module «name»");
				«FOR j : innerJobs»
					«j.codeName»(); // @«j.at»
				«ENDFOR»
				System.out.println("End of execution of module «name»");
			}

			public static void main(String[] args) throws FileNotFoundException
			{
				if (args.length == 1)
				{
					String dataFileName = args[0];
					JsonParser parser = new JsonParser();
					JsonObject o = parser.parse(new FileReader(dataFileName)).getAsJsonObject();
					Gson gson = new Gson();

					assert(o.has("mesh"));
					«javaMeshClassName»Factory meshFactory = gson.fromJson(o.get("mesh"), «javaMeshClassName»Factory.class);
					«javaMeshClassName» mesh = meshFactory.create();
					assert(o.has("options"));
					«name».Options options = gson.fromJson(o.get("options"), «name».Options.class);
					«FOR s : allProviders»
					«s» «s.toFirstLower» = (o.has("«s.toFirstLower»") ? gson.fromJson(o.get("«s.toFirstLower»"), «s».class) : new «s»());
					«ENDFOR»

					«name» simulator = new «name»(mesh, options«FOR s : allProviders BEFORE ', ' SEPARATOR ', '»«s.toFirstLower»«ENDFOR»);
					simulator.simulate();
				}
				else
				{
					System.out.println("[ERROR] Wrong number of arguments: expected 1, actual " + args.length);
					System.out.println("        Expecting user data file name, for example «name»Default.json");
				}
			}
			«FOR j : jobs»

				«j.content»
			«ENDFOR»
			«FOR f : functions.filter(Function).filter[body !== null]»

				«f.content»
			«ENDFOR»
			«IF postProcessingInfo !== null»

			private void dumpVariables(int iteration)
			{
				if (!writer.isDisabled())
				{
					VtkFileContent content = new VtkFileContent(iteration, «irModule.timeVariable.name», «irModule.nodeCoordVariable.name», mesh.getGeometry().getQuads());
					«FOR v : postProcessingInfo.outputVariables.filter(ConnectivityVariable)»
					content.add«v.type.connectivities.head.returnType.name.toFirstUpper»Variable("«v.outputName»", «v.name»«IF v.linearAlgebra».toArray()«ENDIF»);
					«ENDFOR»
					writer.writeFile(content);
					«postProcessingInfo.lastDumpVariable.name» = «postProcessingInfo.periodReference.name»;
				}
			}
			«ENDIF»
		};
	'''

	private def getConnectivityAccessor(Connectivity c)
	{
		if (c.inTypes.empty)
			'''mesh.getNb«c.name.toFirstUpper»()'''
		else
			'''CartesianMesh2D.MaxNb«c.name.toFirstUpper»'''
	}

	private def getLinearAlgebraDefinition(ConnectivityVariable v)
	{
		switch v.type.connectivities.size
		{
			case 1: 'Vector.createDenseVector(' + v.type.connectivities.get(0).nbElemsVar + ')'
			case 2: 'Matrix.createDenseMatrix(' + v.type.connectivities.map[nbElemsVar].join(', ') + ')'
			default: throw new RuntimeException("Not implemented exception")
		}
	}

	private def String getJavaMeshClassName(IrModule it)
	{
		meshClassName.replace('::', '.')
	}
}