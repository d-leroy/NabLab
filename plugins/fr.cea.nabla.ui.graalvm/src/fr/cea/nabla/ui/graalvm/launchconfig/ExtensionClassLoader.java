package fr.cea.nabla.ui.graalvm.launchconfig;

import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class ExtensionClassLoader extends ClassLoader {

	private final Set<String> extensionPoints;

	private final ClassLoader sysClzLoader;

	public ExtensionClassLoader(Set<String> knownExtensionPoints, ClassLoader parent) {
		super(parent);
		extensionPoints = Collections.unmodifiableSet(knownExtensionPoints);
		sysClzLoader = getSystemClassLoader();
	}
	
	@Override
	public InputStream getResourceAsStream(String arg0) {
		return super.getResourceAsStream(arg0);
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
			final Bundle bundle = Platform.getBundle(configurationElement.getContributor().getName());
			final Class<?> result = bundle.loadClass(name);
			return result;
			
//			try {
//				final URL bundleURL = FileLocator.resolve(bundle.getEntry("/"));
//				System.out.println(bundleURL);
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
////				System.out.println(bundleURL);
//
//			final Enumeration<URL> urls = bundle
//					.findEntries("/target/xtend/classes72536118965618/org/gemoc/monilog/stl/", "*", true);
//			final URL[] urlArray = Streams.stream(urls.asIterator()).map(url -> {
//				try {
//					return FileLocator.resolve(url);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//					return null;
//				}
//			}).filter(url -> url != null).collect(Collectors.toList()).toArray(new URL[0]);
//
//			try (final URLClassLoader urlClassLoader = new URLClassLoader(urlArray, this.getParent())) {
//				final Class<?> result = urlClassLoader.loadClass(name);
//				return result;
//			} catch (IOException e) {
//				throw new ClassNotFoundException(
//						"Could not find class " + name + " within extensions " + extensionPoints.toString(), e);
//			}
			
//			Object executableExtension;
//			try {
//				executableExtension = configurationElement.createExecutableExtension("class");
//			} catch (CoreException e) {
//				e.printStackTrace();
//				throw new ClassNotFoundException();
//			}
//			final Class<?> clazz = executableExtension.getClass();
//			final URL classURL = clazz.getResource(name.substring(name.lastIndexOf('.') + 1) + ".class");
//			try (final URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { classURL }, this.getParent())) {
//				final Class<?> result = urlClassLoader.loadClass(name);
//				return result;
//			} catch (IOException e) {
//				throw new ClassNotFoundException(
//						"Could not find class " + name + " within extensions " + extensionPoints.toString(), e);
//			}
		} else {
			throw new ClassNotFoundException(
					"Could not find class " + name + " within extensions " + extensionPoints.toString());
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
