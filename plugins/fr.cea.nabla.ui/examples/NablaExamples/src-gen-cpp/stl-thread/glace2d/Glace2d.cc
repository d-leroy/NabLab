#include <iostream>
#include <iomanip>
#include <type_traits>
#include <limits>
#include <utility>
#include <cmath>
#include "mesh/CartesianMesh2DGenerator.h"
#include "mesh/CartesianMesh2D.h"
#include "mesh/PvdFileWriter2D.h"
#include "utils/Utils.h"
#include "utils/Timer.h"
#include "types/Types.h"
#include "types/MathFunctions.h"
#include "utils/stl/Parallel.h"

using namespace nablalib;


RealArray1D<2> perp(RealArray1D<2> a)
{
	return {a[1], -a[0]};
}

template<size_t l>
RealArray2D<l,l> tensProduct(RealArray1D<l> a, RealArray1D<l> b)
{
	RealArray2D<l,l> result;
	for (size_t ia=0; ia<l; ia++)
	{
		for (size_t ib=0; ib<l; ib++)
		{
			result[ia][ib] = a[ia] * b[ib];
		}
	}
	return result;
}

template<size_t l>
double trace(RealArray2D<l,l> a)
{
	double result(0.0);
	for (size_t ia=0; ia<l; ia++)
	{
		result = result + a[ia][ia];
	}
	return result;
}

RealArray2D<2,2> inverse(RealArray2D<2,2> a)
{
	const double alpha(1.0 / MathFunctions::det(a));
	return {a[1][1] * alpha, -a[0][1] * alpha, -a[1][0] * alpha, a[0][0] * alpha};
}

template<size_t x>
RealArray1D<x> sumR1(RealArray1D<x> a, RealArray1D<x> b)
{
	return a + b;
}

double sumR0(double a, double b)
{
	return a + b;
}

template<size_t x>
RealArray2D<x,x> sumR2(RealArray2D<x,x> a, RealArray2D<x,x> b)
{
	return a + b;
}

double minR0(double a, double b)
{
	return MathFunctions::min(a, b);
}

class Glace2d
{
public:
	struct Options
	{
		// Should be const but usefull to set them from main args
		double X_EDGE_LENGTH = 0.01;
		double Y_EDGE_LENGTH = X_EDGE_LENGTH;
		size_t X_EDGE_ELEMS = 100;
		size_t Y_EDGE_ELEMS = 10;
		double option_stoptime = 0.2;
		size_t option_max_iterations = 20000;
		double gamma = 1.4;
		double option_x_interface = 0.5;
		double option_deltat_ini = 1.0E-5;
		double option_deltat_cfl = 0.4;
		double option_rho_ini_zg = 1.0;
		double option_rho_ini_zd = 0.125;
		double option_p_ini_zg = 1.0;
		double option_p_ini_zd = 0.1;
	};
	Options* options;

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

public:
	Glace2d(Options* aOptions, CartesianMesh2D* aCartesianMesh2D, string output)
	: options(aOptions)
	, mesh(aCartesianMesh2D)
	, writer("Glace2d", output)
	, nbNodes(mesh->getNbNodes())
	, nbCells(mesh->getNbCells())
	, nbNodesOfCell(CartesianMesh2D::MaxNbNodesOfCell)
	, nbCellsOfNode(CartesianMesh2D::MaxNbCellsOfNode)
	, nbInnerNodes(mesh->getNbInnerNodes())
	, nbOuterFaces(mesh->getNbOuterFaces())
	, nbNodesOfFace(CartesianMesh2D::MaxNbNodesOfFace)
	, t_n(0.0)
	, t_nplus1(0.0)
	, deltat_n(options->option_deltat_ini)
	, deltat_nplus1(options->option_deltat_ini)
	, X_n(nbNodes)
	, X_nplus1(nbNodes)
	, X_n0(nbNodes)
	, b(nbNodes)
	, bt(nbNodes)
	, Ar(nbNodes)
	, Mt(nbNodes)
	, ur(nbNodes)
	, c(nbCells)
	, m(nbCells)
	, p(nbCells)
	, rho(nbCells)
	, e(nbCells)
	, E_n(nbCells)
	, E_nplus1(nbCells)
	, V(nbCells)
	, deltatj(nbCells)
	, uj_n(nbCells)
	, uj_nplus1(nbCells)
	, l(nbCells, std::vector<double>(nbNodesOfCell))
	, Cjr_ic(nbCells, std::vector<RealArray1D<2>>(nbNodesOfCell))
	, C(nbCells, std::vector<RealArray1D<2>>(nbNodesOfCell))
	, F(nbCells, std::vector<RealArray1D<2>>(nbNodesOfCell))
	, Ajr(nbCells, std::vector<RealArray2D<2,2>>(nbNodesOfCell))
	, lastDump(numeric_limits<int>::min())
	{
		// Copy node coordinates
		const auto& gNodes = mesh->getGeometry()->getNodes();
		for (size_t rNodes=0; rNodes<nbNodes; rNodes++)
			X_n0[rNodes] = gNodes[rNodes];
	}

private:
	/**
	 * Job ComputeCjr called @1.0 in executeTimeLoopN method.
	 * In variables: X_n
	 * Out variables: C
	 */
	void computeCjr() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rPlus1Id(nodesOfCellJ[(rNodesOfCellJ+1+nbNodesOfCell)%nbNodesOfCell]);
					const Id rMinus1Id(nodesOfCellJ[(rNodesOfCellJ-1+nbNodesOfCell)%nbNodesOfCell]);
					const size_t rPlus1Nodes(rPlus1Id);
					const size_t rMinus1Nodes(rMinus1Id);
					C[jCells][rNodesOfCellJ] = 0.5 * perp(X_n[rPlus1Nodes] - X_n[rMinus1Nodes]);
				}
			}
		});
	}
	
	/**
	 * Job ComputeInternalEnergy called @1.0 in executeTimeLoopN method.
	 * In variables: E_n, uj_n
	 * Out variables: e
	 */
	void computeInternalEnergy() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			e[jCells] = E_n[jCells] - 0.5 * MathFunctions::dot(uj_n[jCells], uj_n[jCells]);
		});
	}
	
	/**
	 * Job IniCjrIc called @1.0 in simulate method.
	 * In variables: X_n0
	 * Out variables: Cjr_ic
	 */
	void iniCjrIc() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rPlus1Id(nodesOfCellJ[(rNodesOfCellJ+1+nbNodesOfCell)%nbNodesOfCell]);
					const Id rMinus1Id(nodesOfCellJ[(rNodesOfCellJ-1+nbNodesOfCell)%nbNodesOfCell]);
					const size_t rPlus1Nodes(rPlus1Id);
					const size_t rMinus1Nodes(rMinus1Id);
					Cjr_ic[jCells][rNodesOfCellJ] = 0.5 * perp(X_n0[rPlus1Nodes] - X_n0[rMinus1Nodes]);
				}
			}
		});
	}
	
	/**
	 * Job SetUpTimeLoopN called @1.0 in simulate method.
	 * In variables: X_n0
	 * Out variables: X_n
	 */
	void setUpTimeLoopN() noexcept
	{
		for (size_t i2(0) ; i2<X_n.size() ; i2++)
			for (size_t i1(0) ; i1<X_n[i2].size() ; i1++)
				X_n[i2][i1] = X_n0[i2][i1];
	}
	
	/**
	 * Job ComputeLjr called @2.0 in executeTimeLoopN method.
	 * In variables: C
	 * Out variables: l
	 */
	void computeLjr() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					l[jCells][rNodesOfCellJ] = MathFunctions::norm(C[jCells][rNodesOfCellJ]);
				}
			}
		});
	}
	
	/**
	 * Job ComputeV called @2.0 in executeTimeLoopN method.
	 * In variables: C, X_n
	 * Out variables: V
	 */
	void computeV() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			double reduction5(0.0);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rId(nodesOfCellJ[rNodesOfCellJ]);
					const size_t rNodes(rId);
					reduction5 = sumR0(reduction5, MathFunctions::dot(C[jCells][rNodesOfCellJ], X_n[rNodes]));
				}
			}
			V[jCells] = 0.5 * reduction5;
		});
	}
	
	/**
	 * Job Initialize called @2.0 in simulate method.
	 * In variables: Cjr_ic, X_n0, gamma, option_p_ini_zd, option_p_ini_zg, option_rho_ini_zd, option_rho_ini_zg, option_x_interface
	 * Out variables: E_n, m, p, rho, uj_n
	 */
	void initialize() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			double rho_ic;
			double p_ic;
			RealArray1D<2> reduction0({0.0, 0.0});
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rId(nodesOfCellJ[rNodesOfCellJ]);
					const size_t rNodes(rId);
					reduction0 = sumR1(reduction0, X_n0[rNodes]);
				}
			}
			const RealArray1D<2> center(0.25 * reduction0);
			if (center[0] < options->option_x_interface) 
			{
				rho_ic = options->option_rho_ini_zg;
				p_ic = options->option_p_ini_zg;
			}
			else
			{
				rho_ic = options->option_rho_ini_zd;
				p_ic = options->option_p_ini_zd;
			}
			double reduction1(0.0);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rId(nodesOfCellJ[rNodesOfCellJ]);
					const size_t rNodes(rId);
					reduction1 = sumR0(reduction1, MathFunctions::dot(Cjr_ic[jCells][rNodesOfCellJ], X_n0[rNodes]));
				}
			}
			const double V_ic(0.5 * reduction1);
			m[jCells] = rho_ic * V_ic;
			p[jCells] = p_ic;
			rho[jCells] = rho_ic;
			E_n[jCells] = p_ic / ((options->gamma - 1.0) * rho_ic);
			uj_n[jCells] = {0.0, 0.0};
		});
	}
	
	/**
	 * Job ComputeDensity called @3.0 in executeTimeLoopN method.
	 * In variables: V, m
	 * Out variables: rho
	 */
	void computeDensity() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			rho[jCells] = m[jCells] / V[jCells];
		});
	}
	
	/**
	 * Job ExecuteTimeLoopN called @3.0 in simulate method.
	 * In variables: Ajr, Ar, C, E_n, F, Mt, V, X_EDGE_ELEMS, X_EDGE_LENGTH, X_n, Y_EDGE_ELEMS, Y_EDGE_LENGTH, b, bt, c, deltat_n, deltat_nplus1, deltatj, e, gamma, l, m, option_deltat_cfl, p, rho, t_n, uj_n, ur
	 * Out variables: Ajr, Ar, C, E_nplus1, F, Mt, V, X_nplus1, b, bt, c, deltat_nplus1, deltatj, e, l, p, rho, t_nplus1, uj_nplus1, ur
	 */
	void executeTimeLoopN() noexcept
	{
		n = 0;
		bool continueLoop = true;
		do
		{
			globalTimer.start();
			cpuTimer.start();
			n++;
			dumpVariables(n);
			if (n!=1)
				std::cout << "[" << __CYAN__ << __BOLD__ << setw(3) << n << __RESET__ "] t = " << __BOLD__
					<< setiosflags(std::ios::scientific) << setprecision(8) << setw(16) << t_n << __RESET__;
		
			computeCjr(); // @1.0
			computeInternalEnergy(); // @1.0
			computeLjr(); // @2.0
			computeV(); // @2.0
			computeDensity(); // @3.0
			computeEOSp(); // @4.0
			computeEOSc(); // @5.0
			computeAjr(); // @6.0
			computedeltatj(); // @6.0
			computeAr(); // @7.0
			computeBr(); // @7.0
			computeDt(); // @7.0
			computeBoundaryConditions(); // @8.0
			computeBt(); // @8.0
			computeMt(); // @8.0
			computeTn(); // @8.0
			computeU(); // @9.0
			computeFjr(); // @10.0
			computeXn(); // @10.0
			computeEn(); // @11.0
			computeUn(); // @11.0
			
		
			// Evaluate loop condition with variables at time n
			continueLoop = (t_nplus1 < options->option_stoptime && n + 1 < options->option_max_iterations);
		
			if (continueLoop)
			{
				// Switch variables to prepare next iteration
				std::swap(t_nplus1, t_n);
				std::swap(deltat_nplus1, deltat_n);
				std::swap(X_nplus1, X_n);
				std::swap(E_nplus1, E_n);
				std::swap(uj_nplus1, uj_n);
			}
		
			cpuTimer.stop();
			globalTimer.stop();
		
			// Timers display
			if (!writer.isDisabled())
				std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __BLUE__ << ioTimer.print(true) << __RESET__ "} ";
			else
				std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __RED__ << "none" << __RESET__ << "} ";
			
			// Progress
			std::cout << utils::progress_bar(n, options->option_max_iterations, t_n, options->option_stoptime, 25);
			std::cout << __BOLD__ << __CYAN__ << utils::Timer::print(
				utils::eta(n, options->option_max_iterations, t_n, options->option_stoptime, deltat_n, globalTimer), true)
				<< __RESET__ << "\r";
			std::cout.flush();
		
			cpuTimer.reset();
			ioTimer.reset();
		} while (continueLoop);
	}
	
	/**
	 * Job ComputeEOSp called @4.0 in executeTimeLoopN method.
	 * In variables: e, gamma, rho
	 * Out variables: p
	 */
	void computeEOSp() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			p[jCells] = (options->gamma - 1.0) * rho[jCells] * e[jCells];
		});
	}
	
	/**
	 * Job ComputeEOSc called @5.0 in executeTimeLoopN method.
	 * In variables: gamma, p, rho
	 * Out variables: c
	 */
	void computeEOSc() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			c[jCells] = MathFunctions::sqrt(options->gamma * p[jCells] / rho[jCells]);
		});
	}
	
	/**
	 * Job ComputeAjr called @6.0 in executeTimeLoopN method.
	 * In variables: C, c, l, rho
	 * Out variables: Ajr
	 */
	void computeAjr() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					Ajr[jCells][rNodesOfCellJ] = ((rho[jCells] * c[jCells]) / l[jCells][rNodesOfCellJ]) * tensProduct(C[jCells][rNodesOfCellJ], C[jCells][rNodesOfCellJ]);
				}
			}
		});
	}
	
	/**
	 * Job Computedeltatj called @6.0 in executeTimeLoopN method.
	 * In variables: V, c, l
	 * Out variables: deltatj
	 */
	void computedeltatj() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			double reduction2(0.0);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					reduction2 = sumR0(reduction2, l[jCells][rNodesOfCellJ]);
				}
			}
			deltatj[jCells] = 2.0 * V[jCells] / (c[jCells] * reduction2);
		});
	}
	
	/**
	 * Job ComputeAr called @7.0 in executeTimeLoopN method.
	 * In variables: Ajr
	 * Out variables: Ar
	 */
	void computeAr() noexcept
	{
		parallel::parallel_exec(nbNodes, [&](const size_t& rNodes)
		{
			const Id rId(rNodes);
			RealArray2D<2,2> reduction3({0.0, 0.0,  0.0, 0.0});
			{
				const auto cellsOfNodeR(mesh->getCellsOfNode(rId));
				const size_t nbCellsOfNodeR(cellsOfNodeR.size());
				for (size_t jCellsOfNodeR=0; jCellsOfNodeR<nbCellsOfNodeR; jCellsOfNodeR++)
				{
					const Id jId(cellsOfNodeR[jCellsOfNodeR]);
					const size_t jCells(jId);
					const size_t rNodesOfCellJ(utils::indexOf(mesh->getNodesOfCell(jId), rId));
					reduction3 = sumR2(reduction3, Ajr[jCells][rNodesOfCellJ]);
				}
			}
			Ar[rNodes] = reduction3;
		});
	}
	
	/**
	 * Job ComputeBr called @7.0 in executeTimeLoopN method.
	 * In variables: Ajr, C, p, uj_n
	 * Out variables: b
	 */
	void computeBr() noexcept
	{
		parallel::parallel_exec(nbNodes, [&](const size_t& rNodes)
		{
			const Id rId(rNodes);
			RealArray1D<2> reduction4({0.0, 0.0});
			{
				const auto cellsOfNodeR(mesh->getCellsOfNode(rId));
				const size_t nbCellsOfNodeR(cellsOfNodeR.size());
				for (size_t jCellsOfNodeR=0; jCellsOfNodeR<nbCellsOfNodeR; jCellsOfNodeR++)
				{
					const Id jId(cellsOfNodeR[jCellsOfNodeR]);
					const size_t jCells(jId);
					const size_t rNodesOfCellJ(utils::indexOf(mesh->getNodesOfCell(jId), rId));
					reduction4 = sumR1(reduction4, p[jCells] * C[jCells][rNodesOfCellJ] + MathFunctions::matVectProduct(Ajr[jCells][rNodesOfCellJ], uj_n[jCells]));
				}
			}
			b[rNodes] = reduction4;
		});
	}
	
	/**
	 * Job ComputeDt called @7.0 in executeTimeLoopN method.
	 * In variables: deltatj, option_deltat_cfl
	 * Out variables: deltat_nplus1
	 */
	void computeDt() noexcept
	{
		double reduction8;
		reduction8 = parallel::parallel_reduce(nbCells, numeric_limits<double>::max(), [&](double& accu, const size_t& jCells)
			{
				return (accu = minR0(accu, deltatj[jCells]));
			},
			&minR0);
		deltat_nplus1 = options->option_deltat_cfl * reduction8;
	}
	
	/**
	 * Job ComputeBoundaryConditions called @8.0 in executeTimeLoopN method.
	 * In variables: Ar, X_EDGE_ELEMS, X_EDGE_LENGTH, X_n, Y_EDGE_ELEMS, Y_EDGE_LENGTH, b
	 * Out variables: Mt, bt
	 */
	void computeBoundaryConditions() noexcept
	{
		{
			const auto outerFaces(mesh->getOuterFaces());
			const size_t nbOuterFaces(outerFaces.size());
			parallel::parallel_exec(nbOuterFaces, [&](const size_t& fOuterFaces)
			{
				const Id fId(outerFaces[fOuterFaces]);
				const double epsilon(1.0E-10);
				const RealArray2D<2,2> I({1.0, 0.0, 0.0, 1.0});
				const double X_MIN(0.0);
				const double X_MAX(options->X_EDGE_ELEMS * options->X_EDGE_LENGTH);
				const double Y_MIN(0.0);
				const double Y_MAX(options->Y_EDGE_ELEMS * options->Y_EDGE_LENGTH);
				const RealArray1D<2> nY({0.0, 1.0});
				{
					const auto nodesOfFaceF(mesh->getNodesOfFace(fId));
					const size_t nbNodesOfFaceF(nodesOfFaceF.size());
					for (size_t rNodesOfFaceF=0; rNodesOfFaceF<nbNodesOfFaceF; rNodesOfFaceF++)
					{
						const Id rId(nodesOfFaceF[rNodesOfFaceF]);
						const size_t rNodes(rId);
						if ((X_n[rNodes][1] - Y_MIN < epsilon) || (X_n[rNodes][1] - Y_MAX < epsilon)) 
						{
							double sign(0.0);
							if (X_n[rNodes][1] - Y_MIN < epsilon) 
								sign = -1.0;
							else
								sign = 1.0;
							const RealArray1D<2> N(sign * nY);
							const RealArray2D<2,2> NxN(tensProduct(N, N));
							const RealArray2D<2,2> IcP(I - NxN);
							bt[rNodes] = MathFunctions::matVectProduct(IcP, b[rNodes]);
							Mt[rNodes] = IcP * (Ar[rNodes] * IcP) + NxN * trace(Ar[rNodes]);
						}
						if ((MathFunctions::fabs(X_n[rNodes][0] - X_MIN) < epsilon) || ((MathFunctions::fabs(X_n[rNodes][0] - X_MAX) < epsilon))) 
						{
							Mt[rNodes] = I;
							bt[rNodes] = {0.0, 0.0};
						}
					}
				}
			});
		}
	}
	
	/**
	 * Job ComputeBt called @8.0 in executeTimeLoopN method.
	 * In variables: b
	 * Out variables: bt
	 */
	void computeBt() noexcept
	{
		{
			const auto innerNodes(mesh->getInnerNodes());
			const size_t nbInnerNodes(innerNodes.size());
			parallel::parallel_exec(nbInnerNodes, [&](const size_t& rInnerNodes)
			{
				const Id rId(innerNodes[rInnerNodes]);
				const size_t rNodes(rId);
				bt[rNodes] = b[rNodes];
			});
		}
	}
	
	/**
	 * Job ComputeMt called @8.0 in executeTimeLoopN method.
	 * In variables: Ar
	 * Out variables: Mt
	 */
	void computeMt() noexcept
	{
		{
			const auto innerNodes(mesh->getInnerNodes());
			const size_t nbInnerNodes(innerNodes.size());
			parallel::parallel_exec(nbInnerNodes, [&](const size_t& rInnerNodes)
			{
				const Id rId(innerNodes[rInnerNodes]);
				const size_t rNodes(rId);
				Mt[rNodes] = Ar[rNodes];
			});
		}
	}
	
	/**
	 * Job ComputeTn called @8.0 in executeTimeLoopN method.
	 * In variables: deltat_nplus1, t_n
	 * Out variables: t_nplus1
	 */
	void computeTn() noexcept
	{
		t_nplus1 = t_n + deltat_nplus1;
	}
	
	/**
	 * Job ComputeU called @9.0 in executeTimeLoopN method.
	 * In variables: Mt, bt
	 * Out variables: ur
	 */
	void computeU() noexcept
	{
		parallel::parallel_exec(nbNodes, [&](const size_t& rNodes)
		{
			ur[rNodes] = MathFunctions::matVectProduct(inverse(Mt[rNodes]), bt[rNodes]);
		});
	}
	
	/**
	 * Job ComputeFjr called @10.0 in executeTimeLoopN method.
	 * In variables: Ajr, C, p, uj_n, ur
	 * Out variables: F
	 */
	void computeFjr() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rId(nodesOfCellJ[rNodesOfCellJ]);
					const size_t rNodes(rId);
					F[jCells][rNodesOfCellJ] = p[jCells] * C[jCells][rNodesOfCellJ] + MathFunctions::matVectProduct(Ajr[jCells][rNodesOfCellJ], (uj_n[jCells] - ur[rNodes]));
				}
			}
		});
	}
	
	/**
	 * Job ComputeXn called @10.0 in executeTimeLoopN method.
	 * In variables: X_n, deltat_n, ur
	 * Out variables: X_nplus1
	 */
	void computeXn() noexcept
	{
		parallel::parallel_exec(nbNodes, [&](const size_t& rNodes)
		{
			X_nplus1[rNodes] = X_n[rNodes] + deltat_n * ur[rNodes];
		});
	}
	
	/**
	 * Job ComputeEn called @11.0 in executeTimeLoopN method.
	 * In variables: E_n, F, deltat_n, m, ur
	 * Out variables: E_nplus1
	 */
	void computeEn() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			double reduction7(0.0);
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					const Id rId(nodesOfCellJ[rNodesOfCellJ]);
					const size_t rNodes(rId);
					reduction7 = sumR0(reduction7, MathFunctions::dot(F[jCells][rNodesOfCellJ], ur[rNodes]));
				}
			}
			E_nplus1[jCells] = E_n[jCells] - (deltat_n / m[jCells]) * reduction7;
		});
	}
	
	/**
	 * Job ComputeUn called @11.0 in executeTimeLoopN method.
	 * In variables: F, deltat_n, m, uj_n
	 * Out variables: uj_nplus1
	 */
	void computeUn() noexcept
	{
		parallel::parallel_exec(nbCells, [&](const size_t& jCells)
		{
			const Id jId(jCells);
			RealArray1D<2> reduction6({0.0, 0.0});
			{
				const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
				const size_t nbNodesOfCellJ(nodesOfCellJ.size());
				for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
				{
					reduction6 = sumR1(reduction6, F[jCells][rNodesOfCellJ]);
				}
			}
			uj_nplus1[jCells] = uj_n[jCells] - (deltat_n / m[jCells]) * reduction6;
		});
	}

	void dumpVariables(int iteration)
	{
		if (!writer.isDisabled() && n >= lastDump + 1.0)
		{
			cpuTimer.stop();
			ioTimer.start();
			std::map<string, double*> cellVariables;
			std::map<string, double*> nodeVariables;
			cellVariables.insert(pair<string,double*>("Density", rho.data()));
			auto quads = mesh->getGeometry()->getQuads();
			writer.writeFile(iteration, t_n, nbNodes, X_n.data(), nbCells, quads.data(), cellVariables, nodeVariables);
			lastDump = n;
			ioTimer.stop();
			cpuTimer.start();
		}
	}

public:
	void simulate()
	{
		std::cout << "\n" << __BLUE_BKG__ << __YELLOW__ << __BOLD__ <<"\tStarting Glace2d ..." << __RESET__ << "\n\n";
		
		std::cout << "[" << __GREEN__ << "MESH" << __RESET__ << "]      X=" << __BOLD__ << options->X_EDGE_ELEMS << __RESET__ << ", Y=" << __BOLD__ << options->Y_EDGE_ELEMS
			<< __RESET__ << ", X length=" << __BOLD__ << options->X_EDGE_LENGTH << __RESET__ << ", Y length=" << __BOLD__ << options->Y_EDGE_LENGTH << __RESET__ << std::endl;
		
		std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  HWLOC unavailable cannot get topological informations" << std::endl;
		
		if (!writer.isDisabled())
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    VTK files stored in " << __BOLD__ << writer.outputDirectory() << __RESET__ << " directory" << std::endl;
		else
			std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    " << __BOLD__ << "Disabled" << __RESET__ << std::endl;

		iniCjrIc(); // @1.0
		setUpTimeLoopN(); // @1.0
		initialize(); // @2.0
		executeTimeLoopN(); // @3.0
		
		std::cout << __YELLOW__ << "\n\tDone ! Took " << __MAGENTA__ << __BOLD__ << globalTimer.print() << __RESET__ << std::endl;
	}
};

int main(int argc, char* argv[]) 
{
	auto o = new Glace2d::Options();
	string output;
	if (argc == 5)
	{
		o->X_EDGE_ELEMS = std::atoi(argv[1]);
		o->Y_EDGE_ELEMS = std::atoi(argv[2]);
		o->X_EDGE_LENGTH = std::atof(argv[3]);
		o->Y_EDGE_LENGTH = std::atof(argv[4]);
	}
	else if (argc == 6)
	{
		o->X_EDGE_ELEMS = std::atoi(argv[1]);
		o->Y_EDGE_ELEMS = std::atoi(argv[2]);
		o->X_EDGE_LENGTH = std::atof(argv[3]);
		o->Y_EDGE_LENGTH = std::atof(argv[4]);
		output = argv[5];
	}
	else if (argc != 1)
	{
		std::cerr << "[ERROR] Wrong number of arguments. Expecting 4 or 5 args: X Y Xlength Ylength (output)." << std::endl;
		std::cerr << "(X=100, Y=10, Xlength=0.01, Ylength=0.01 output=current directory with no args)" << std::endl;
	}
	auto nm = CartesianMesh2DGenerator::generate(o->X_EDGE_ELEMS, o->Y_EDGE_ELEMS, o->X_EDGE_LENGTH, o->Y_EDGE_LENGTH);
	auto c = new Glace2d(o, nm, output);
	c->simulate();
	delete c;
	delete nm;
	delete o;
	return 0;
}
