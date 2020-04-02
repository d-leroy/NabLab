package fr.cea.nabla.ir.truffle.runtime;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

import fr.cea.nabla.ir.ir.Iterator;
import fr.cea.nabla.javalib.mesh.CartesianMesh2D;
import fr.cea.nabla.javalib.mesh.CartesianMesh2DGenerator;
import fr.cea.nabla.javalib.mesh.Quad;

public class MeshWrapper {
	
	@CompilationFinal
	private CartesianMesh2D mesh;

	@TruffleBoundary
	public MeshWrapper() {
	}

	@TruffleBoundary
	public MeshWrapper(final int nbXQuads, final int nbYQuads, final double xSize, final double ySize) {
		this.mesh = CartesianMesh2DGenerator.generate(nbXQuads, nbYQuads, xSize, ySize);
	}

	@TruffleBoundary
	public void initialize(final int nbXQuads, final int nbYQuads, final double xSize, final double ySize) {
		assert(this.mesh == null);
		this.mesh = CartesianMesh2DGenerator.generate(nbXQuads, nbYQuads, xSize, ySize);
	}

	@TruffleBoundary
	public int[] getElements(final String connectivityName, final int[] args) {
		int[] _switchResult = null;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "nodes":
				_switchResult = this.mesh.getNodes();
				break;
			case "cells":
				_switchResult = this.mesh.getCells();
				break;
			case "faces":
				_switchResult = this.mesh.getFaces();
				break;
			case "innerNodes":
				_switchResult = this.mesh.getInnerNodes();
				break;
			case "outerFaces":
				_switchResult = this.mesh.getOuterFaces();
				break;
			case "nodesOfCell":
				_switchResult = this.mesh.getNodesOfCell(args[0]);
				break;
			case "cellsOfNode":
				_switchResult = this.mesh.getCellsOfNode(args[0]);
				break;
			case "nodesOfFace":
				_switchResult = this.mesh.getNodesOfFace(args[0]);
				break;
			case "neighbourCells":
				_switchResult = this.mesh.getNeighbourCells(args[0]);
				break;
			case "cellsOfFace":
				_switchResult = this.mesh.getCellsOfFace(args[0]);
				break;
			default:
				throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
			}
		} else {
			throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
		}
		return _switchResult;
	}

	@TruffleBoundary
	public int getIndexOf(final Iterator iterator, final int id) {
		throw new RuntimeException("Not implemented yet");
	}

	@TruffleBoundary
	public int getSingleton(final String connectivityName, final int[] params) {
		int _switchResult = (int) 0;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "commonFace":
				_switchResult = this.mesh.getCommonFace(params[0], params[1]);
				break;
			default:
				throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
			}
		} else {
			throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
		}
		return _switchResult;
	}

	@TruffleBoundary
	public double[][] getNodes() {
		return this.mesh.getGeometry().getNodes();
	}

	@TruffleBoundary
	public int getNbElems(final String connectivityName) {
		int _switchResult = (int) 0;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "nodes":
				_switchResult = this.mesh.getNbNodes();
				break;
			case "cells":
				_switchResult = this.mesh.getNbCells();
				break;
			case "faces":
				_switchResult = this.mesh.getNbFaces();
				break;
			case "outerFaces":
				_switchResult = this.mesh.getNbOuterFaces();
				break;
			case "innerNodes":
				_switchResult = this.mesh.getNbInnerNodes();
				break;
			default:
				throw new RuntimeException("Not implemented yet");
			}
		} else {
			throw new RuntimeException("Not implemented yet");
		}
		return _switchResult;
	}

	@TruffleBoundary
	public int getMaxNbElems(final String connectivityName) {
		int _switchResult = (int) 0;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "nodesOfCell":
				_switchResult = CartesianMesh2D.MaxNbNodesOfCell;
				break;
			case "nodesOfFace":
				_switchResult = CartesianMesh2D.MaxNbNodesOfFace;
				break;
			case "cellsOfNode":
				_switchResult = CartesianMesh2D.MaxNbCellsOfNode;
				break;
			case "cellsOfFace":
				_switchResult = CartesianMesh2D.MaxNbCellsOfFace;
				break;
			case "neighbourCells":
				_switchResult = CartesianMesh2D.MaxNbNeighbourCells;
				break;
			default:
				throw new RuntimeException("Not implemented yet");
			}
		} else {
			throw new RuntimeException("Not implemented yet");
		}
		return _switchResult;
	}

	@TruffleBoundary
	public Quad[] getQuads() {
		return this.mesh.getGeometry().getQuads();
	}
}
