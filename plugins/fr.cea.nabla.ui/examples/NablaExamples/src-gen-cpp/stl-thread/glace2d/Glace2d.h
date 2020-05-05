#include <fstream>
#include <iomanip>
#include <type_traits>
#include <limits>
#include <utility>
#include <cmath>
#include <rapidjson/document.h>
#include <rapidjson/istreamwrapper.h>
#include "mesh/CartesianMesh2DGenerator.h"
#include "mesh/CartesianMesh2D.h"
#include "mesh/PvdFileWriter2D.h"
#include "utils/Utils.h"
#include "utils/Timer.h"
#include "types/Types.h"
#include "utils/stl/Parallel.h"

using namespace nablalib;

/******************** Free functions declarations ********************/

double det(RealArray2D<2,2> a);
RealArray1D<2> perp(RealArray1D<2> a);
template<size_t x>
double dot(RealArray1D<x> a, RealArray1D<x> b);
template<size_t x>
double norm(RealArray1D<x> a);
template<size_t l>
RealArray2D<l,l> tensProduct(RealArray1D<l> a, RealArray1D<l> b);
template<size_t x, size_t y>
RealArray1D<x> matVectProduct(RealArray2D<x,y> a, RealArray1D<y> b);
template<size_t l>
double trace(RealArray2D<l,l> a);
RealArray2D<2,2> inverse(RealArray2D<2,2> a);
template<size_t x>
RealArray1D<x> sumR1(RealArray1D<x> a, RealArray1D<x> b);
double sumR0(double a, double b);
template<size_t x>
RealArray2D<x,x> sumR2(RealArray2D<x,x> a, RealArray2D<x,x> b);
double minR0(double a, double b);


/******************** Module declaration ********************/

class Glace2d
{
public:
	struct Options
	{
		double X_EDGE_LENGTH;
		double Y_EDGE_LENGTH;
		size_t X_EDGE_ELEMS;
		size_t Y_EDGE_ELEMS;
		double option_stoptime;
		size_t option_max_iterations;
		double gamma;
		double option_x_interface;
		double option_deltat_ini;
		double option_deltat_cfl;
		double option_rho_ini_zg;
		double option_rho_ini_zd;
		double option_p_ini_zg;
		double option_p_ini_zd;

		Options(const std::string& fileName);
	};

	Options* options;

	Glace2d(Options* aOptions, CartesianMesh2D* aCartesianMesh2D, string output);

private:
	CartesianMesh2D* mesh;
	PvdFileWriter2D writer;
	size_t nbNodes, nbCells, nbNodesOfCell, nbCellsOfNode, nbInnerNodes, nbOuterFaces, nbNodesOfFace;
	int n;
	double t_n;
	double t_nplus1;
	double deltat_n;
	double deltat_nplus1;
	std::vector<RealArray1D<2>> X_n;
	std::vector<RealArray1D<2>> X_nplus1;
	std::vector<RealArray1D<2>> X_n0;
	std::vector<RealArray1D<2>> b;
	std::vector<RealArray1D<2>> bt;
	std::vector<RealArray2D<2,2>> Ar;
	std::vector<RealArray2D<2,2>> Mt;
	std::vector<RealArray1D<2>> ur;
	std::vector<double> c;
	std::vector<double> m;
	std::vector<double> p;
	std::vector<double> rho;
	std::vector<double> e;
	std::vector<double> E_n;
	std::vector<double> E_nplus1;
	std::vector<double> V;
	std::vector<double> deltatj;
	std::vector<RealArray1D<2>> uj_n;
	std::vector<RealArray1D<2>> uj_nplus1;
	std::vector<std::vector<double>> l;
	std::vector<std::vector<RealArray1D<2>>> Cjr_ic;
	std::vector<std::vector<RealArray1D<2>>> C;
	std::vector<std::vector<RealArray1D<2>>> F;
	std::vector<std::vector<RealArray2D<2,2>>> Ajr;
	int lastDump;
	utils::Timer globalTimer;
	utils::Timer cpuTimer;
	utils::Timer ioTimer;

	void computeCjr() noexcept;
	
	void computeInternalEnergy() noexcept;
	
	void iniCjrIc() noexcept;
	
	void setUpTimeLoopN() noexcept;
	
	void computeLjr() noexcept;
	
	void computeV() noexcept;
	
	void initialize() noexcept;
	
	void computeDensity() noexcept;
	
	void executeTimeLoopN() noexcept;
	
	void computeEOSp() noexcept;
	
	void computeEOSc() noexcept;
	
	void computeAjr() noexcept;
	
	void computedeltatj() noexcept;
	
	void computeAr() noexcept;
	
	void computeBr() noexcept;
	
	void computeDt() noexcept;
	
	void computeBoundaryConditions() noexcept;
	
	void computeBt() noexcept;
	
	void computeMt() noexcept;
	
	void computeTn() noexcept;
	
	void computeU() noexcept;
	
	void computeFjr() noexcept;
	
	void computeXn() noexcept;
	
	void computeEn() noexcept;
	
	void computeUn() noexcept;

	void dumpVariables(int iteration);

public:
	void simulate();
};