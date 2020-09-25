#include <fstream>
#include <iomanip>
#include <type_traits>
#include <limits>
#include <utility>
#include <cmath>
#include <rapidjson/document.h>
#include <rapidjson/istreamwrapper.h>
#include "mesh/CartesianMesh2DFactory.h"
#include "mesh/CartesianMesh2D.h"
#include "utils/Utils.h"
#include "utils/Timer.h"
#include "types/Types.h"
#include "mesh/stl/PvdFileWriter2D.h"
#include "utils/stl/Parallel.h"

using namespace nablalib;

/******************** Free functions declarations ********************/

double det(RealArray1D<2> a, RealArray1D<2> b);
template<size_t x>
double norm(RealArray1D<x> a);
template<size_t x>
double dot(RealArray1D<x> a, RealArray1D<x> b);
template<size_t x>
RealArray1D<x> sumR1(RealArray1D<x> a, RealArray1D<x> b);
double sumR0(double a, double b);


/******************** Module declaration ********************/

class HeatEquation
{
public:
	struct Options
	{
		std::string outputPath;
		int outputPeriod;
		double stopTime;
		int maxIterations;
		double PI;
		double alpha;

		void jsonInit(const rapidjson::Value::ConstObject& d);
	};

	HeatEquation(CartesianMesh2D* aMesh, const Options& aOptions);
	~HeatEquation();

private:
	// Mesh and mesh variables
	CartesianMesh2D* mesh;
	size_t nbNodes, nbCells, nbFaces, nbNeighbourCells, nbNodesOfFace, nbNodesOfCell;
	
	// User options and external classes
	const Options& options;
	PvdFileWriter2D writer;
	
	// Global variables
	int lastDump;
	int n;
	double t_n;
	double t_nplus1;
	static constexpr double deltat = 0.001;
	std::vector<RealArray1D<2>> X;
	std::vector<RealArray1D<2>> center;
	std::vector<double> u_n;
	std::vector<double> u_nplus1;
	std::vector<double> V;
	std::vector<double> f;
	std::vector<double> outgoingFlux;
	std::vector<double> surface;
	
	utils::Timer globalTimer;
	utils::Timer cpuTimer;
	utils::Timer ioTimer;

	void computeOutgoingFlux() noexcept;
	
	void computeSurface() noexcept;
	
	void computeTn() noexcept;
	
	void computeV() noexcept;
	
	void iniCenter() noexcept;
	
	void iniF() noexcept;
	
	void computeUn() noexcept;
	
	void iniUn() noexcept;
	
	void executeTimeLoopN() noexcept;

	void dumpVariables(int iteration, bool useTimer=true);

public:
	void simulate();
};
