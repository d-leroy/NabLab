package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.library.GenerateLibrary;
import com.oracle.truffle.api.library.Library;

import fr.cea.nabla.javalib.mesh.Quad;

@GenerateLibrary
public abstract class AbstractMeshLibrary extends Library {
	
	protected AbstractMeshLibrary() {
    }
	
	public abstract void initialize(Object receiver, final int nbXQuads, final int nbYQuads, final double xSize, final double ySize);
	
	public abstract int[] getElements(Object receiver, final String connectivityName, final int[] args);
	
	public abstract int getSingleton(Object receiver, final String connectivityName, final int[] params);
	
	public abstract double[][] getNodes(Object receiver);

	public abstract int getNbElems(Object receiver, final String connectivityName);

	public abstract int getMaxNbElems(Object receiver, final String connectivityName);

	public abstract Quad[] getQuads(Object receiver);
	
}
