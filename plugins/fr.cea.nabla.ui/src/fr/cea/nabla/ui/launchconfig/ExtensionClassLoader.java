package fr.cea.nabla.ui.launchconfig;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

public class ExtensionClassLoader extends ClassLoader {

	private final Set<String> extensionPoints;
	
	private final ClassLoader sysClzLoader;

	public ExtensionClassLoader(Set<String> knownExtensionPoints, ClassLoader parent) {
		super(parent);
		extensionPoints = Collections.unmodifiableSet(knownExtensionPoints);
		sysClzLoader = getSystemClassLoader();
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		IConfigurationElement configurationElement = null;
		final Iterator<String> iterator = extensionPoints.iterator();
		while (configurationElement == null && iterator.hasNext()) {
			final String extensionPointName = iterator.next();
			final IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
					.getExtensionPoint(extensionPointName);
			final IConfigurationElement[] configurationElements = extensionPoint.getConfigurationElements();
			for (int i = 0; i < configurationElements.length; i++) {
				if (configurationElements[i].getAttribute("class").equals(name)) {
					configurationElement = configurationElements[i];
				}
			}
		}
		if (configurationElement != null) {
			Object executableExtension;
			try {
				executableExtension = configurationElement.createExecutableExtension("class");
			} catch (CoreException e) {
				e.printStackTrace();
				throw new ClassNotFoundException();
			}
			final Class<?> result = executableExtension.getClass();
			return result;
		} else {
			throw new ClassNotFoundException(
					"Could not find class " + name + "within extensions " + extensionPoints.toString());
		}
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// has the class loaded already?
		Class<?> loadedClass = findLoadedClass(name);
		if (loadedClass == null) {
			try {
				if (sysClzLoader != null) {
					loadedClass = sysClzLoader.loadClass(name);
				}
			} catch (ClassNotFoundException ex) {
				// class not found in system class loader... silently skipping
			}

			try {
				// find the class from given extension points as in first constructor parameter.
				if (loadedClass == null) {
					loadedClass = findClass(name);
				}
			} catch (ClassNotFoundException e) {
				// class is not found in the given extension points.
				// Let's try it in parent classloader.
				// If class is still not found, then this method will throw class not found ex.
				loadedClass = super.loadClass(name);
			}
		}

		return loadedClass;
	}
}
