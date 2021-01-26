package fr.cea.nabla.ir.generator.jni

import fr.cea.nabla.ir.generator.CMakeUtils
import fr.cea.nabla.ir.generator.GenerationContent
import fr.cea.nabla.ir.generator.ProviderGenerator
import fr.cea.nabla.ir.generator.Utils
import fr.cea.nabla.ir.generator.cpp.Backend
import fr.cea.nabla.ir.generator.java.FunctionContentProvider
import fr.cea.nabla.ir.interpreter.ProviderClassCache
import fr.cea.nabla.ir.ir.ExtensionProvider
import fr.cea.nabla.ir.ir.Function
import java.util.ArrayList

import static fr.cea.nabla.ir.ExtensionProviderExtensions.*

class JniProviderGenerator implements ProviderGenerator
{
	public static val JNI = "Jni"

	val Backend cppBackend

	new(Backend cppBackend)
	{
		this.cppBackend = cppBackend
	}

	static def void convertToJni(ExtensionProvider it)
	{
		providerName = providerName + JNI
		projectDir = projectDir + JNI
		facadeClass = facadeClass + JNI
		facadeNamespace = facadeNamespace.replace('::', '.')
		libName = libName + JNI.toLowerCase
	}

	override getName() { JNI.toUpperCase }

	override getGenerationContents(ExtensionProvider provider, Iterable<Function> functions)
	{
		// the provider represents a C++ project
		// extract the needed infos and transform it to JNI provider
		val cppLibName = provider.libName
		val cppFullClassName = getNsPrefix(provider, '.', '::') + provider.facadeClass
		val cppProjectDir = provider.projectDir
		val cppProjectName = provider.providerName
		provider.convertToJni

		val fileContents = new ArrayList<GenerationContent>

		// .java
		val javaFileName = provider.facadeNamespace + "/" + provider.facadeClass + ".java"
		fileContents += new GenerationContent(javaFileName, getJavaFileContent(provider, functions), false)

		// .cc
		val sourceFileName =  provider.facadeNamespace + "_" + provider.facadeClass + ".cc"
		fileContents += new GenerationContent(sourceFileName, getCppFileContent(provider, functions, cppFullClassName), false)

		// CMakeLists.txt
		val cmakeFileName = "CMakeLists.txt"
		fileContents += new GenerationContent(cmakeFileName, getCMakeFileContent(provider, cppProjectName, cppProjectDir, cppLibName), false)

		return fileContents
	}

	private def getJavaFileContent(ExtensionProvider provider, Iterable<Function> functions)
	'''
	«Utils.fileHeader»

	/**
	 * Design Pattern inspired from https://dhilst.github.io/2016/10/15/JNI-CPP.html
	 * Principle: a java long attribute to keep the link to the C++ object
	 */
	«IF !provider.facadeNamespace.nullOrEmpty»
	package «provider.facadeNamespace»;

	«ENDIF»
	public class «provider.facadeClass»
	{
		static
		{
			System.load(System.getProperty("«ProviderClassCache.JNI_LIBRARY_PATH»") + "/lib«provider.libName».so");
		}

		// This is a long here (in Java) but is used as a pointer to hold the
		// address of our native object at "native world".
		private long nativeObjectPointer;

		// This method is used to allocate an instance of this class at native
		// world and return the address of it.
		private native long nativeNew();

		// Our constructor. The nativeNew() method is called to allocate a new
		// instance of our native object and return its address. The address is
		// assigned to nativeObjectPointer. Just as a note, Java forbiddes
		// native constructors, so we need a native method to allocate our
		// native object.
		public «provider.facadeClass»()
		{
			nativeObjectPointer = nativeNew();	
		}

		public native void jsonInit(String jsonContent);
		«FOR f : functions»
		public native «FunctionContentProvider.getHeaderContent(f)»;
		«ENDFOR»
	}
	'''

	private def getCppFileContent(ExtensionProvider provider, Iterable<Function> functions, String cppFullClassName)
	'''
	«Utils.fileHeader»

	#include "«getNsPrefix(provider, '.', '_')»«provider.facadeClass».h"
	#include "«cppFullClassName.replace('::', '/')».h"

	#ifdef __cplusplus
	extern "C" {
	#endif

	JNIEXPORT jlong JNICALL Java_«getNsPrefix(provider, '.', '_')»«provider.facadeClass»_nativeNew
	(JNIEnv *env, jobject self)
	{
		// Here we allocate our new object and return
		// its pointer casted as a jlong;
		«cppFullClassName» *o = new «cppFullClassName»();
		return reinterpret_cast<jlong>(o);
	}

	// This function is a helper providing the boiler
	// plate code to return the native object from
	// Java object. The "nativeObjectPointer" is reached
	// from this code, casted to People's pointer and
	// returned. This will be used in all our native
	// methods wrappers to recover the object before
	// invoking it's methods.
	static «cppFullClassName»* getObject
	(JNIEnv *env, jobject self)
	{
		jclass cls = env->GetObjectClass(self);
		if (!cls)
			env->FatalError("GetObjectClass failed");

		jfieldID nativeObjectPointerID = env->GetFieldID(cls, "nativeObjectPointer", "J");
		if (!nativeObjectPointerID)
			env->FatalError("GetFieldID failed");

		jlong nativeObjectPointer = env->GetLongField(self, nativeObjectPointerID);
		return reinterpret_cast<«cppFullClassName»*>(nativeObjectPointer);
	}

	// Here is our native methods wrappers, we simply recover
	// native BatiLib's instance invoke the requested method
	// and return its return value. jint can be casted to
	// Java's int. The string is a case apart. Since String
	// is an object and not a primitive type we have to
	// return it as reference (not by value). This is safe
	// since, as a Java String object the JVM can deallocate
	// it when is not being used anymore.
	JNIEXPORT void JNICALL Java_«getNsPrefix(provider, '.', '_')»«provider.facadeClass»_jsonInit
	(JNIEnv *env, jobject self, jstring jsonContent)
	{
		«cppFullClassName»* _self = getObject(env, self);
		const char *nativeJsonContent = env->GetStringUTFChars(jsonContent, 0);
		_self->jsonInit(nativeJsonContent);
		env->ReleaseStringUTFChars(jsonContent, nativeJsonContent);
	}
	«FOR f : functions»

	«cppBackend.functionContentProvider.getJniDefinitionContent(f, provider, cppFullClassName)»
	«ENDFOR»

	#ifdef __cplusplus
	}
	#endif
	'''

	private def getCMakeFileContent(ExtensionProvider provider, String cppProjectName, String cppProjectDir, String cppLibName)
	'''
	«CMakeUtils::fileHeader»

	set(«provider.extensionName.toUpperCase»_DIR «CMakeUtils.formatCMakePath(cppProjectDir)»)

	if(NOT DEFINED JAVA_HOME)
		message(FATAL_ERROR "JAVA_HOME variable undefined.\n"
			"You can launch cmake with -D option, for example: "
			"cmake -D JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 ../src")
	endif()

	project(«provider.providerName» CXX)

	MESSAGE(STATUS "Building library «provider.libName»")

	«CMakeUtils.setCompiler»

	add_subdirectory(${«provider.extensionName.toUpperCase»_DIR}/src ${CMAKE_BINARY_DIR}/«cppProjectName»)

	# The lib«provider.libName».so library
	add_library(«provider.libName» SHARED «getNsPrefix(provider, '.', '_')»«provider.facadeClass».cc «getNsPrefix(provider, '.', '_')»«provider.facadeClass».h)
	target_include_directories(«provider.libName»
		PUBLIC ${INCLUDE_DIR}
		PUBLIC ${JAVA_HOME}/include
		PUBLIC ${JAVA_HOME}/include/linux)
	target_link_libraries(«provider.libName» PUBLIC «cppLibName»)

	# Generation of «provider.facadeClass».h from «provider.facadeClass».java
	add_custom_command(
		OUTPUT ${CMAKE_CURRENT_SOURCE_DIR}/«getNsPrefix(provider, '.', '_')»«provider.facadeClass».h «getNsPrefix(provider, '.', '/')»«provider.facadeClass».class
		COMMENT "Generate «provider.facadeClass».h from «provider.facadeClass».java"
		COMMAND ${JAVA_HOME}/bin/javac -h ${CMAKE_CURRENT_SOURCE_DIR} -d ${CMAKE_CURRENT_BINARY_DIR} ${CMAKE_CURRENT_SOURCE_DIR}/«getNsPrefix(provider, '.', '/')»«provider.facadeClass».java
		DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/«getNsPrefix(provider, '.', '/')»«provider.facadeClass».java)

	# The «provider.libName».jar
	add_custom_target(«provider.libName»jar ALL DEPENDS «provider.libName».jar)
	add_custom_command(
		OUTPUT «provider.libName».jar
		COMMENT "Built «provider.libName».jar"
		WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
		COMMAND ${JAVA_HOME}/bin/jar cvf «provider.libName».jar «getNsPrefix(provider, '.', '/')»«provider.facadeClass».class
		DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/«getNsPrefix(provider, '.', '_')»«provider.facadeClass».h)

	INSTALL(TARGETS «provider.libName» DESTINATION ${CMAKE_SOURCE_DIR}/lib)
	INSTALL(FILES ${CMAKE_CURRENT_BINARY_DIR}/«provider.libName».jar DESTINATION ${CMAKE_SOURCE_DIR}/lib)

	«CMakeUtils.fileFooter»
	'''
}