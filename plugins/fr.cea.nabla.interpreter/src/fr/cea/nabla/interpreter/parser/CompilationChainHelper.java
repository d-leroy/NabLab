/**
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 */
package fr.cea.nabla.interpreter.parser;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import fr.cea.nabla.NablaStandaloneSetup;
import fr.cea.nabla.NablagenStandaloneSetup;
import fr.cea.nabla.generator.IrModuleTransformer;
import fr.cea.nabla.generator.NablagenInterpreter;
import fr.cea.nabla.ir.ir.IrModule;
import fr.cea.nabla.ir.transformers.ReplaceReductions;
import fr.cea.nabla.nabla.NablaModule;
import fr.cea.nabla.nablagen.NablagenConfig;
import fr.cea.nabla.nablagen.NablagenModule;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@InjectWith(NablaInjectorProvider.class)
@SuppressWarnings("all")
public class CompilationChainHelper {
	@Inject
	@Extension
	private ValidationTestHelper validationTestHelper;

	@Inject
	private Provider<NablagenInterpreter> interpreterProvider;

	@Inject
	private Provider<ResourceSet> resourceSetProvider;

	@Inject
	private IrModuleTransformer transformer;

	private NablaStandaloneSetup nablaSetup = new NablaStandaloneSetup();

	private Injector nablaInjector = this.nablaSetup.createInjectorAndDoEMFRegistration();

	private ParseHelper<NablaModule> nablaParseHelper = this.nablaInjector.<ParseHelper>getInstance(ParseHelper.class);

	private NablagenStandaloneSetup nablagenSetup = new NablagenStandaloneSetup();

	private Injector nablagenInjector = this.nablagenSetup.createInjectorAndDoEMFRegistration();

	private ParseHelper<NablagenModule> nablagenParseHelper = this.nablagenInjector
			.<ParseHelper>getInstance(ParseHelper.class);

	public IrModule getIrModule(final CharSequence model, final CharSequence genModel) {
		try {
			final String testProjectPath = System.getProperty("user.dir");
			final String pluginsPath = (testProjectPath + "/../../plugins/");
			ResourceSet rs = this.resourceSetProvider.get();
			final String mathFunctionsPath = (pluginsPath + "fr.cea.nabla/nablalib/mathfunctions.nabla");
			byte[] _readAllBytes = Files.readAllBytes(Paths.get(mathFunctionsPath));
			String _string = new String(_readAllBytes);
			this.nablaParseHelper.parse(_string, rs);
			final String linearAlgebraFunctionsPath = (pluginsPath
					+ "fr.cea.nabla/nablalib/linearalgebrafunctions.nabla");
			byte[] _readAllBytes_1 = Files.readAllBytes(Paths.get(linearAlgebraFunctionsPath));
			String _string_1 = new String(_readAllBytes_1);
			this.nablaParseHelper.parse(_string_1, rs);
			NablaModule nablaModule = this.nablaParseHelper.parse(model, rs);
			this.validationTestHelper.assertNoErrors(nablaModule);
			rs.getResources().add(nablaModule.eResource());
			NablagenModule nablaGenModule = this.nablagenParseHelper.parse(genModel, rs);
			this.validationTestHelper.assertNoErrors(nablaGenModule);
			if (nablaGenModule.getConfig() != null) {
				NablagenInterpreter interpreter = this.interpreterProvider.get();
				final IrModule irModule = interpreter.buildIrModule(nablaGenModule.getConfig(),
						(pluginsPath + "fr.cea.nabla.ui/examples/NablaExamples"));
				ReplaceReductions _replaceReductions = new ReplaceReductions(true);
				final Procedure1<String> _function = (String msg) -> {
					InputOutput.<String>println(msg);
				};
				this.transformer.transformIr(_replaceReductions, irModule, _function);
				return irModule;
			} else {
				return null;
			}
		} catch (Throwable _e) {
			throw Exceptions.sneakyThrow(_e);
		}
	}
}
