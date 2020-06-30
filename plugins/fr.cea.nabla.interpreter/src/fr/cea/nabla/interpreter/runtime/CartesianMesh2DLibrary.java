package fr.cea.nabla.interpreter.runtime;

import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;
import com.oracle.truffle.api.library.Library;

import fr.cea.nabla.javalib.mesh.CartesianMesh2D;
import fr.cea.nabla.javalib.mesh.CartesianMesh2DGenerator;
import fr.cea.nabla.javalib.mesh.Quad;

@ExportLibrary(value = AbstractMeshLibrary.class)
public abstract class CartesianMesh2DLibrary extends Library {
	
	private CartesianMesh2D mesh;
	
	@ExportMessage
	public void initialize(final int nbXQuads, final int nbYQuads, final double xSize, final double ySize) {
		mesh = CartesianMesh2DGenerator.generate(nbXQuads, nbYQuads, xSize, ySize);
	}
	
	@ExportMessage
	final int[] getElements(String connectivityName, int[] args) {
		int[] _switchResult = null;
		if (connectivityName == null || connectivityName.isEmpty()) {
			throw new IllegalArgumentException("Connectivity name can't be null or empty");
		} else {
			switch (connectivityName) {
			case "nodes":
				_switchResult = mesh.getNodes();
				break;
			case "cells":
				_switchResult = mesh.getCells();
				break;
			case "faces":
				_switchResult = mesh.getFaces();
				break;
			case "innerNodes":
				_switchResult = mesh.getInnerNodes();
				break;
			case "outerFaces":
				_switchResult = mesh.getOuterFaces();
				break;
			case "nodesOfCell":
				_switchResult = mesh.getNodesOfCell(args[0]);
				break;
			case "cellsOfNode":
				_switchResult = mesh.getCellsOfNode(args[0]);
				break;
			case "nodesOfFace":
				_switchResult = mesh.getNodesOfFace(args[0]);
				break;
			case "neighbourCells":
				_switchResult = mesh.getNeighbourCells(args[0]);
				break;
			case "cellsOfFace":
				_switchResult = mesh.getCellsOfFace(args[0]);
				break;
			default:
				throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
			}
		}
		return _switchResult;
	}

	@ExportMessage
	final int getSingleton(String connectivityName, int[] params) {
		int _switchResult = (int) 0;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "commonFace":
				_switchResult = mesh.getCommonFace(params[0], params[1]);
				break;
			default:
				throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
			}
		} else {
			throw new RuntimeException((("Not implemented yet (" + connectivityName) + ")"));
		}
		return _switchResult;
	}

	@ExportMessage
	final double[][] getNodes() {
		return mesh.getGeometry().getNodes();
	}

	@ExportMessage
	final int getNbElems(String connectivityName) {
		int _switchResult = (int) 0;
		if (connectivityName != null) {
			switch (connectivityName) {
			case "nodes":
				_switchResult = mesh.getNbNodes();
				break;
			case "cells":
				_switchResult = mesh.getNbCells();
				break;
			case "faces":
				_switchResult = mesh.getNbFaces();
				break;
			case "outerFaces":
				_switchResult = mesh.getNbOuterFaces();
				break;
			case "innerNodes":
				_switchResult = mesh.getNbInnerNodes();
				break;
			default:
				throw new RuntimeException("Not implemented yet");
			}
		} else {
			throw new RuntimeException("Not implemented yet");
		}
		return _switchResult;
	}

	@ExportMessage
	final int getMaxNbElems(String connectivityName) {
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

	@ExportMessage
	final Quad[] getQuads() {
		return mesh.getGeometry().getQuads();
	}
	
}
