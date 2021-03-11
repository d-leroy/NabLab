package fr.cea.nabla.ui.graalvm;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.inject.AbstractModule;

public class NablaGraalVMModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ResourceSet.class).to(ResourceSetImpl.class);
	}

}
