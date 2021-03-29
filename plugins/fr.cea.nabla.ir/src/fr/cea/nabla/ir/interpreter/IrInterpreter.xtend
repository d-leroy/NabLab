/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.interpreter

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import fr.cea.nabla.ir.Utils
import fr.cea.nabla.ir.ir.IrModule
import fr.cea.nabla.ir.ir.IrRoot
import fr.cea.nabla.javalib.LevelDBUtils
import java.io.File
import java.util.logging.Logger
import java.util.logging.StreamHandler
import org.iq80.leveldb.Options

import static fr.cea.nabla.ir.interpreter.ExpressionInterpreter.*
import static fr.cea.nabla.ir.interpreter.VariableValueFactory.*
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes
import static org.iq80.leveldb.impl.Iq80DBFactory.factory

import static extension fr.cea.nabla.ir.ExtensionProviderExtensions.*
import static extension fr.cea.nabla.ir.IrModuleExtensions.*
import static extension fr.cea.nabla.ir.IrRootExtensions.*
import static extension fr.cea.nabla.ir.interpreter.NablaValueExtensions.*

class IrInterpreter
{
	public static val ITERATION_VARIABLE_NAME = "InterpreterIteration"

	val Logger logger

	new(StreamHandler handler)
	{
		// create a Logger and a Handler
		logger = Logger.getLogger(IrInterpreter.name)
		logger.setLevel(handler.level)  //Create only logs if needed by handler
		logger.setUseParentHandlers(false) // Suppress default console
		logger.handlers.forEach(h | logger.removeHandler(h))
		logger.addHandler(handler)
	}

	/**
	 * Interpret variable with default values.
	 * This method is public to be used by default Json file generation.
	 */
	def interpreteOptionsDefaultValues(IrRoot ir)
	{
		val context = new Context(logger, ir, null)
		return interpreteOptionsDefaultValues(context)
	}

	def interprete(IrRoot ir, String jsonContent, String wsPath)
	{
		val context = new Context(logger, ir, wsPath)
		context.logInfo("  Start interpreting " + context.ir.name + " module")

		// Start initializing the variables with default values
		interpreteOptionsDefaultValues(context)

		val gson = new Gson
		val jsonObject = gson.fromJson(jsonContent, JsonObject)

		// Create mesh and mesh variables
		if (!jsonObject.has("mesh")) throw new RuntimeException("Mesh block missing in Json")
		context.initMesh(gson, jsonObject.get("mesh").toString, context.ir.connectivities)

		// Read options in Json
		for (m : context.ir.modules)
			init(context, m, jsonObject.get(m.name), wsPath)

		// Interprete variables that are not options
		for (v : context.ir.variables.filter[!option])
			context.addVariableValue(v, createValue(v.type, v.name, v.defaultValue, context))

		// Copy Node Cooords
		context.addVariableValue(context.ir.initNodeCoordVariable, new NV2Real(context.meshWrapper.nodes))

		// Interprete Top level jobs
		for (j : context.ir.main.calls)
			JobInterpreter.interprete(j, context)

		// Non regression testing
		val mainModuleName = context.ir.mainModule.name
		if (jsonObject.has(mainModuleName))
		{
			val jsonMainModuleOptions = jsonObject.get(mainModuleName).asJsonObject
			val nrName = Utils.NonRegressionNameAndValue.key
			if (jsonMainModuleOptions.has(nrName))
			{
				val dbBaseName = "results/interpreter/" + context.ir.name.toLowerCase + "/" + context.ir.name + "DB."
				val refDBName = dbBaseName + "ref"
				val jsonNrName = jsonMainModuleOptions.get(nrName).asString
				if (jsonNrName.equals(Utils.NonRegressionValues.CreateReference.toString))
					createDB(context, refDBName)
				if (jsonNrName.equals(Utils.NonRegressionValues.CompareToReference.toString))
				{
					val curDBName = dbBaseName + "current"
					createDB(context, curDBName)
					context.levelDBCompareResult = LevelDBUtils.compareDB(curDBName, refDBName)
					LevelDBUtils.destroyDB(curDBName)
				}
			}
		}

		context.logVariables("At the end")
		context.logInfo("  End interpreting")

		return context
	}

	private def interpreteOptionsDefaultValues(Context context)
	{
		for (v : context.ir.options)
			context.addVariableValue(v, createValue(v.type, v.name, v.defaultValue, context))
		return context
	}

	private def init(Context context, IrModule m, JsonElement jsonElt, String wsPath)
	{
		val jsonOptions = (jsonElt === null ? null : jsonElt.asJsonObject)

		if (jsonOptions !== null && m.main)
		{
			val outputPath = jsonOptions.get("outputPath")
			if (outputPath !== null)
				context.initWriter(outputPath.asString)
		}

		for (v : m.options)
		{
			if (jsonOptions !== null && jsonOptions.has(v.name))
			{
				val vValue = context.getVariableValue(v)
				val jsonOpt = jsonOptions.get(v.name)
				NablaValueJsonSetter::setValue(vValue, jsonOpt)
			}
			else
			{
				if (v.defaultValue === null)
					// v is not present in json file and is mandatory
					throw new IllegalStateException("Mandatory option missing in Json file: " + v.name)
				else
					context.setVariableValue(v, interprete(v.defaultValue, context))
			}
		}

		for (provider : m.providers.filter[extensionName != "Math"])
		{
			val providerHelper = context.extensionProviderCache.get(provider) as DefaultExtensionProviderHelper
			providerHelper.createInstance(m)
			if (jsonOptions !== null && jsonOptions.has(provider.instanceName))
				providerHelper.jsonInit(m, jsonOptions.get(provider.instanceName).toString)
		}
	}

	private def createDB(Context context, String db_name)
	{
		val levelDBOptions = new Options()

		// Destroy if exists
		factory.destroy(new File(db_name), levelDBOptions)

		// Create data base
		levelDBOptions.createIfMissing(true)
		val db = factory.open(new File(db_name), levelDBOptions);

		val batch = db.createWriteBatch();
		try
		{
			for (v : context.ir.variables.filter[!option])
				batch.put(bytes(v.name), context.getVariableValue(v).serialize);
			db.write(batch);
		}
		finally
		{
			// Make sure you close the batch to avoid resource leaks.
			batch.close();
		}
		db.close();
		System.out.println("Reference database " + db_name + " created.");
	}
}
