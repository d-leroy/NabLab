package fr.cea.nabla.ir.truffle.values;

import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.dsl.Layout;

@Layout
public interface NV0BoolLayout {

	public static final String DATA_IDENTIFIER = "data";
	
	DynamicObject createNV0Bool(boolean data);
	
	boolean getData(DynamicObject object);
}
