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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import fr.cea.nabla.NablaStandaloneSetup;
import fr.cea.nabla.NablagenStandaloneSetup;
import fr.cea.nabla.generator.ir.IrRootBuilder;
import fr.cea.nabla.ir.ir.IrRoot;
import fr.cea.nabla.nabla.NablaModule;
import fr.cea.nabla.nabla.SimpleVar;
import fr.cea.nabla.nablagen.MainModule;
import fr.cea.nabla.nablagen.NablagenApplication;

@InjectWith(NablaInjectorProvider.class)
@SuppressWarnings("all")
public class CompilationChainHelper {
	@Inject
	@Extension
	private ValidationTestHelper validationTestHelper;

	@Inject
	private Provider<IrRootBuilder> irRootBuilderProvider;

	@Inject
	private Provider<ResourceSet> resourceSetProvider;

	private NablaStandaloneSetup nablaSetup = new NablaStandaloneSetup();

	private Injector nablaInjector = this.nablaSetup.createInjectorAndDoEMFRegistration();

	private ParseHelper<NablaModule> nablaParseHelper = this.nablaInjector.<ParseHelper>getInstance(ParseHelper.class);

	private NablagenStandaloneSetup nablagenSetup = new NablagenStandaloneSetup();

	private Injector nablagenInjector = this.nablagenSetup.createInjectorAndDoEMFRegistration();

	private ParseHelper<NablagenApplication> nablagenParseHelper = this.nablagenInjector
			.<ParseHelper>getInstance(ParseHelper.class);

	public IrRoot getIrRoot(final CharSequence nablaGenModel, final List<URI> nablaPaths) {
		final String mathPath = "/math.n";
		final String linearAlgebraPath = "/linearalgebra.n";
		final String linearAlgebraProviderPath = "/linearalgebra.ngen";
		return getIrRoot(nablaGenModel, nablaPaths, mathPath, linearAlgebraPath, linearAlgebraProviderPath);
	}
	
	private InputStream getNablaResourceAsStream(String path) throws FileNotFoundException {
		InputStream result = getClass().getResourceAsStream(path);
		if (result == null) {
			final String testProjectPath = System.getProperty("user.dir");
			final String nablaPluginsPath = testProjectPath + "/../../plugins/";
			final String mathFunctionsPath = nablaPluginsPath + "fr.cea.nabla";
			result = new FileInputStream(new File(mathFunctionsPath + path));
		}
		return result;
	}

	public IrRoot getIrRoot(final CharSequence genModel, final List<URI> nablaPaths, String mathPath,
			String linearAlgebraPath, String linearAlgebraProviderPath) {
		try {
			final InputStream inMath = getNablaResourceAsStream(mathPath);
			final InputStream inLinear = getNablaResourceAsStream(linearAlgebraPath);
			final InputStream inLinearProvider = getNablaResourceAsStream(linearAlgebraProviderPath);
			final String math = new BufferedReader(new InputStreamReader(inMath)).lines()
					.reduce((s1, s2) -> s1 + "\n" + s2).get();
			final String linearAlgebra = new BufferedReader(new InputStreamReader(inLinear)).lines()
					.reduce((s1, s2) -> s1 + "\n" + s2).get();
			final String linearAlgebraProvider = new BufferedReader(new InputStreamReader(inLinearProvider)).lines()
					.reduce((s1, s2) -> s1 + "\n" + s2).get();
			
			final ResourceSet rs = resourceSetProvider.get();
			
			final NablagenApplication nablaGenRoot = nablagenParseHelper.parse(genModel, rs);
			nablagenParseHelper.parse(linearAlgebraProvider, rs);
			
			nablaParseHelper.parse(math, rs);
			nablaParseHelper.parse(linearAlgebra, rs);
			
			nablaPaths.forEach(p -> {
				try {
					rs.createResource(p).load(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			EcoreUtil.resolveAll(rs);
			validate(nablaGenRoot);
			
			if (nablaGenRoot != null) {
				final IrRootBuilder interpreter = irRootBuilderProvider.get();
				final IrRoot irRoot = interpreter.buildInterpreterIr(nablaGenRoot, "");
				return irRoot;
			} else {
				return null;
			}
		} catch  (Exception e) {
			throw Exceptions.sneakyThrow(e);
		}
	}
	
	private void validate(EObject modelElement) {
		final List<Issue> issues = validationTestHelper.validate(modelElement);
		if (!issues.isEmpty()) {
			final String msg = issues.stream().map(i -> "At line " + i.getLineNumber() + ": " + i.getMessage())
					.reduce((i1, i2) -> i1 + "\n" + i2).get();
			throw new IllegalArgumentException(msg);
		}
	}
}
