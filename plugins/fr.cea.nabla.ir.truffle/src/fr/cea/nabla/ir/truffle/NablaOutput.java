package fr.cea.nabla.ir.truffle;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;

@ExportLibrary(InteropLibrary.class)
public class NablaOutput implements TruffleObject {

	private final List<Object> output;
	
	public NablaOutput(List<Object> output) {
		this.output = new ArrayList<>(output);
	}
	
	public List<Object> getOutput() {
		return output;
	}
}
