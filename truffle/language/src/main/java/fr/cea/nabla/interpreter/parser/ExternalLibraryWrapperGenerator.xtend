package fr.cea.nabla.interpreter.parser

import fr.cea.nabla.ir.generator.cpp.StlTypeContentProvider
import fr.cea.nabla.ir.generator.cpp.TypeContentProvider
import fr.cea.nabla.ir.ir.BaseType
import fr.cea.nabla.ir.ir.Function
import java.util.List

import static extension fr.cea.nabla.ir.IrTypeExtensions.*
import static extension fr.cea.nabla.ir.generator.cpp.Ir2CppUtils.*
import fr.cea.nabla.ir.ir.Connectivity

class ExternalLibraryWrapperGenerator {

	val extension TypeContentProvider typeContentProvider = new StlTypeContentProvider()

	def String getWrapper(String providerName, List<Function> functions) {
	}

	def String getJsonInit(String providerName) {
		'''
			void* jsonInit(const void *value) {
				assert(polyglot_is_string(value));
			
				uint64_t l = polyglot_get_string_size(value);
				char dataFile[l];
				polyglot_as_string(value, dataFile, l, "UTF-8");
			
				rapidjson::Document d;
				d.Parse(dataFile);
				assert(d.IsObject());
			
				assert(d.HasMember("«providerName.toFirstLower»"));
				const rapidjson::Value &valueof_provider = d["«providerName.toFirstLower»"];
				assert(valueof_provider.IsObject());
				«providerName» functions;
				functions.jsonInit(valueof_provider.GetObject());
				«providerName»Wrapper *wrapper = new «providerName»Wrapper();
				wrapper->functions = &functions;
				return wrapper;
			}
		'''
	}

	def getDeclarationContent(Function it, String providerName) {
		'''
		«FOR v : variables BEFORE "template<" SEPARATOR ", " AFTER ">"»size_t «v.name»«ENDFOR»
		«returnType.cppType» «mangledName»(«providerName»Wrapper *receiver«FOR a : inArgs BEFORE ',' SEPARATOR ', '»«a.type.cppType» «a.name»«ENDFOR»)'''
	}

	def String getFunctionWrapper(Function it, String providerName) {
		'''
			«getDeclarationContent(providerName)»
				return receiver->functions->«codeName»(«FOR a : inArgs SEPARATOR ', '»«a.name»«ENDFOR»);
			}
		'''
	}

	def String getMangledName(BaseType type) {
		type.primitive.getName() + type.dimension;
	}

	def String getMangledName(Function function) {
		return function.name + function.inArgs.map[a|a.type.mangledName].join + "R" + function.returnType.mangledName;
	}
}

class TruffleTypeContentProvider extends TypeContentProvider {
	def String getCppType(BaseType it)
	{
		if (it === null)
			'null'
		else if (sizes.empty)
			primitive.cppType
		else
			'void*'
	}
	
	override getCppType(BaseType baseType, Iterable<Connectivity> connectivities) {
		if(connectivities.empty) baseType.cppType else 'void*'
	}

	override protected getLinearAlgebraType(int dimension) {
		return 'void*'
	}
}
