#include <iostream>
#include <iomanip>
#include <type_traits>
#include <limits>
#include <utility>
#include <cmath>
#include <cfenv>
#pragma STDC FENV_ACCESS ON

// Kokkos headers
#include <Kokkos_Core.hpp>
#include <Kokkos_hwloc.hpp>

// Project headers
#include "mesh/NumericMesh2D.h"
#include "mesh/CartesianMesh2DGenerator.h"
#include "mesh/VtkFileWriter2D.h"
#include "utils/Utils.h"
#include "utils/Timer.h"
#include "types/Types.h"
#include "types/MathFunctions.h"

using namespace nablalib;

class Test
{
public:
	struct Options
	{
		double X_EDGE_LENGTH = 1.0;
		double Y_EDGE_LENGTH = as_const(X_EDGE_LENGTH);
		int X_EDGE_ELEMS = 2;
		int Y_EDGE_ELEMS = 2;
		int Z_EDGE_ELEMS = 1;
		double option_stoptime = 0.1;
		int option_max_iterations = 500;
	};
	Options* options;

private:
	NumericMesh2D* mesh;
	VtkFileWriter2D writer;
	int nbNodes, nbCells, nbNodesOfCell;

	// Global Variables
	double total;

	// Array Variables
	Kokkos::View<Real2*> X;
	Kokkos::View<double*> v;
	Kokkos::View<double*> u;
	Kokkos::View<double**> Cjr;
	
	const size_t maxHardThread = Kokkos::DefaultExecutionSpace::max_hardware_threads();

public:
	Test(Options* aOptions, NumericMesh2D* aNumericMesh2D, string output)
	: options(aOptions)
	, mesh(aNumericMesh2D)
	, writer("Test", output)
	, nbNodes(mesh->getNbNodes())
	, nbCells(mesh->getNbCells())
	, nbNodesOfCell(NumericMesh2D::MaxNbNodesOfCell)
	, X("X", nbNodes)
	, v("v", nbNodes)
	, u("u", nbCells)
	, Cjr("Cjr", nbCells, nbNodesOfCell)
	{
		// Copy node coordinates
		const auto& gNodes = mesh->getGeometricMesh()->getNodes();
		Kokkos::parallel_for(nbNodes, KOKKOS_LAMBDA(const int& rNodes)
		{
			X(rNodes) = gNodes[rNodes];
		});
	}

private:
	/**
	 * Job IniU @-3.0
	 * In variables: 
	 * Out variables: u
	 */
	KOKKOS_INLINE_FUNCTION
	void iniU() noexcept
	{
		Kokkos::parallel_for(nbCells, KOKKOS_LAMBDA(const int& jCells)
		{
			u(jCells) = 2.0;
		});
	}
	
	/**
	 * Job IniV @-3.0
	 * In variables: 
	 * Out variables: v
	 */
	KOKKOS_INLINE_FUNCTION
	void iniV() noexcept
	{
		Kokkos::parallel_for(nbNodes, KOKKOS_LAMBDA(const int& rNodes)
		{
			v(rNodes) = 3.0;
		});
	}
	
	/**
	 * Job TestInternal @-2.0
	 * In variables: v
	 * Out variables: u
	 */
	KOKKOS_INLINE_FUNCTION
	void testInternal() noexcept
	{
		Kokkos::parallel_for(nbCells, KOKKOS_LAMBDA(const int& jCells)
		{
			int jId(jCells);
			double reduceProd1025701828 = 1.0;
			{
				auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				for (int r1NodesOfCellJ=0; r1NodesOfCellJ<nodesOfCellJ.size(); r1NodesOfCellJ++)
				{
					int r1Id(nodesOfCellJ[r1NodesOfCellJ]);
					int r1Nodes(r1Id);
					reduceProd1025701828 = reduceProd1025701828 * (v(r1Nodes));
				}
			}
			double reduceSum_981480406 = 0.0;
			{
				auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				for (int r2NodesOfCellJ=0; r2NodesOfCellJ<nodesOfCellJ.size(); r2NodesOfCellJ++)
				{
					int r2Id(nodesOfCellJ[r2NodesOfCellJ]);
					int r2Nodes(r2Id);
					reduceSum_981480406 = reduceSum_981480406 + (v(r2Nodes));
				}
			}
			u(jCells) = reduceProd1025701828 + reduceSum_981480406;
		});
	}
	
	/**
	 * Job TestInternal2 @-2.0
	 * In variables: v
	 * Out variables: u
	 */
	KOKKOS_INLINE_FUNCTION
	void testInternal2() noexcept
	{
		Kokkos::parallel_for(nbCells, KOKKOS_LAMBDA(const int& jCells)
		{
			int jId(jCells);
			double reduceProd1025701828 = 1.0;
			{
				auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				for (int r1NodesOfCellJ=0; r1NodesOfCellJ<nodesOfCellJ.size(); r1NodesOfCellJ++)
				{
					int r1Id(nodesOfCellJ[r1NodesOfCellJ]);
					int r1Nodes(r1Id);
					reduceProd1025701828 = reduceProd1025701828 * (v(r1Nodes));
				}
			}
			double a = reduceProd1025701828;
			double reduceSum_981480406 = 0.0;
			{
				auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				for (int r2NodesOfCellJ=0; r2NodesOfCellJ<nodesOfCellJ.size(); r2NodesOfCellJ++)
				{
					int r2Id(nodesOfCellJ[r2NodesOfCellJ]);
					int r2Nodes(r2Id);
					reduceSum_981480406 = reduceSum_981480406 + (v(r2Nodes));
				}
			}
			double b = reduceSum_981480406;
			u(jCells) = a + b;
		});
	}
	
	/**
	 * Job TestExternal @-1.0
	 * In variables: u
	 * Out variables: total
	 */
	KOKKOS_INLINE_FUNCTION
	void testExternal() noexcept
	{
		double reduceProd106920220(1.0);
		{
			Kokkos::Prod<double> reducer(reduceProd106920220);
			Kokkos::parallel_reduce("ReductionreduceProd106920220", nbCells, KOKKOS_LAMBDA(const int& j1Cells, double& x)
			{
				reducer.join(x, u(j1Cells));
			}, reducer);
		}
		double reduceSum_1900262014(0.0);
		{
			Kokkos::Sum<double> reducer(reduceSum_1900262014);
			Kokkos::parallel_reduce("ReductionreduceSum_1900262014", nbCells, KOKKOS_LAMBDA(const int& j2Cells, double& x)
			{
				reducer.join(x, u(j2Cells));
			}, reducer);
		}
		total = reduceProd106920220 + reduceSum_1900262014;
	}

public:
	void simulate()
	{
		std::cout << "\n" << __BLUE_BKG__ << __YELLOW__ << __BOLD__ <<"\tStarting Test ..." << __RESET__ << "\n\n";

		std::cout << "[" << __GREEN__ << "MESH" << __RESET__ << "]      X=" << __BOLD__ << options->X_EDGE_ELEMS << __RESET__ << ", Y=" << __BOLD__ << options->Y_EDGE_ELEMS
			<< __RESET__ << ", X length=" << __BOLD__ << options->X_EDGE_LENGTH << __RESET__ << ", Y length=" << __BOLD__ << options->Y_EDGE_LENGTH << __RESET__ << std::endl;


		if (Kokkos::hwloc::available()) {
			std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  NUMA=" << __BOLD__ << Kokkos::hwloc::get_available_numa_count()
				<< __RESET__ << ", Cores/NUMA=" << __BOLD__ << Kokkos::hwloc::get_available_cores_per_numa()
				<< __RESET__ << ", Threads/Core=" << __BOLD__ << Kokkos::hwloc::get_available_threads_per_core() << __RESET__ << std::endl;
		} else {
			std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  HWLOC unavailable cannot get topological informations" << std::endl;
		}

		// std::cout << "[" << __GREEN__ << "KOKKOS" << __RESET__ << "]    " << __BOLD__ << (is_same<MyLayout,Kokkos::LayoutLeft>::value?"Left":"Right")" << __RESET__ << " layout" << std::endl;

		if (!writer.isDisabled())
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    VTK files stored in " << __BOLD__ << writer.outputDirectory() << __RESET__ << " directory" << std::endl;
		else
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    " << __BOLD__ << "Disabled" << __RESET__ << std::endl;

		utils::Timer timer(true);

		iniU(); // @-3.0
		iniV(); // @-3.0
		testInternal(); // @-2.0
		testInternal2(); // @-2.0
		testExternal(); // @-1.0
		timer.stop();
	}
};	

int main(int argc, char* argv[]) 
{
	Kokkos::initialize(argc, argv);
	auto o = new Test::Options();
	string output;
	if (argc == 5) {
		o->X_EDGE_ELEMS = std::atoi(argv[1]);
		o->Y_EDGE_ELEMS = std::atoi(argv[2]);
		o->X_EDGE_LENGTH = std::atof(argv[3]);
		o->Y_EDGE_LENGTH = std::atof(argv[4]);
	} else if (argc == 6) {
		o->X_EDGE_ELEMS = std::atoi(argv[1]);
		o->Y_EDGE_ELEMS = std::atoi(argv[2]);
		o->X_EDGE_LENGTH = std::atof(argv[3]);
		o->Y_EDGE_LENGTH = std::atof(argv[4]);
		output = argv[5];
	} else if (argc != 1) {
		std::cerr << "[ERROR] Wrong number of arguments. Expecting 4 or 5 args: X Y Xlength Ylength (output)." << std::endl;
		std::cerr << "(X=100, Y=10, Xlength=0.01, Ylength=0.01 output=current directory with no args)" << std::endl;
	}
	auto gm = CartesianMesh2DGenerator::generate(o->X_EDGE_ELEMS, o->Y_EDGE_ELEMS, o->X_EDGE_LENGTH, o->Y_EDGE_LENGTH);
	auto nm = new NumericMesh2D(gm);
	auto c = new Test(o, nm, output);
	c->simulate();
	delete c;
	delete nm;
	delete gm;
	delete o;
	Kokkos::finalize();
	return 0;
}