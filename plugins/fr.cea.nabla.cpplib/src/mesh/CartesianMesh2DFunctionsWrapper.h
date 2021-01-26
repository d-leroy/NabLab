#include "mesh/CartesianMesh2DFactory.h"
#include "mesh/CartesianMesh2D.h"
#include "polyglot/polyglot.h"

class CartesianMesh2DFunctionsWrapper {
public:

	void* CartesianMesh2D_jsonInit(const void *value);

	nablalib::MeshGeometry<2>* getGeometry(CartesianMesh2DFunctionsWrapper *receiver) noexcept;

	int getMaxNbNodesOfCell(CartesianMesh2DFunctionsWrapper *receiver);
	int getMaxNbNodesOfFace(CartesianMesh2DFunctionsWrapper *receiver);
	int getMaxNbCellsOfNode(CartesianMesh2DFunctionsWrapper *receiver);
	int getMaxNbCellsOfFace(CartesianMesh2DFunctionsWrapper *receiver);
	int getMaxNbFacesOfCell(CartesianMesh2DFunctionsWrapper *receiver);
	int getMaxNbNeighbourCells(CartesianMesh2DFunctionsWrapper *receiver);

	size_t getNbNodes(CartesianMesh2DFunctionsWrapper *receiver) noexcept;
	size_t getNbCells(CartesianMesh2DFunctionsWrapper *receiver) noexcept;

	void* getFaces(CartesianMesh2DFunctionsWrapper *receiver) noexcept;
	size_t getNbFaces(CartesianMesh2DFunctionsWrapper *receiver) noexcept;

	vector<size_t> getNeighbourCells(CartesianMesh2DFunctionsWrapper *receiver,
			const size_t &cellId);
	const array<size_t, 2>& getNodesOfFace(CartesianMesh2DFunctionsWrapper *receiver,
			const size_t &faceId) noexcept;
	vector<size_t> getCellsOfFace(CartesianMesh2DFunctionsWrapper *receiver, const size_t &faceId);
	const array<size_t, 4>& getNodesOfCell(CartesianMesh2DFunctionsWrapper *receiver,
			const size_t &cellId) noexcept;
	size_t getCommonFace(CartesianMesh2DFunctionsWrapper *receiver, const size_t &cellId1,
			const size_t &cellId2);

	nablalib::CartesianMesh2D *cartesianMesh2D;

private:

	const void* asPolyglotValue(const vector<size_t> v);

	const void* asPolyglotValue(const array<size_t, 2> &v);

	const void* asPolyglotValue(const array<size_t, 4> &v);
};

