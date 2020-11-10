#include "heatequation/HeatEquation.h"

using namespace nablalib;

/******************** Free functions definitions ********************/

double det(RealArray1D<2> a, RealArray1D<2> b)
{
	return (a[0] * b[1] - a[1] * b[0]);
}

template<size_t x>
double norm(RealArray1D<x> a)
{
	return std::sqrt(dot(a, a));
}

template<size_t x>
double dot(RealArray1D<x> a, RealArray1D<x> b)
{
	double result(0.0);
	for (size_t i=0; i<x; i++)
	{
		result = result + a[i] * b[i];
	}
	return result;
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


/******************** Options definition ********************/

void
HeatEquation::Options::jsonInit(const rapidjson::Value::ConstObject& d)
{
	// outputPath
	assert(d.HasMember("outputPath"));
	const rapidjson::Value& valueof_outputPath = d["outputPath"];
	assert(valueof_outputPath.IsString());
	outputPath = valueof_outputPath.GetString();
	// outputPeriod
	assert(d.HasMember("outputPeriod"));
	const rapidjson::Value& valueof_outputPeriod = d["outputPeriod"];
	assert(valueof_outputPeriod.IsInt());
	outputPeriod = valueof_outputPeriod.GetInt();
	// stopTime
	if (d.HasMember("stopTime"))
	{
		const rapidjson::Value& valueof_stopTime = d["stopTime"];
		assert(valueof_stopTime.IsDouble());
		stopTime = valueof_stopTime.GetDouble();
	}
	else
		stopTime = 0.1;
	// maxIterations
	if (d.HasMember("maxIterations"))
	{
		const rapidjson::Value& valueof_maxIterations = d["maxIterations"];
		assert(valueof_maxIterations.IsInt());
		maxIterations = valueof_maxIterations.GetInt();
	}
	else
		maxIterations = 500;
	// PI
	if (d.HasMember("PI"))
	{
		const rapidjson::Value& valueof_PI = d["PI"];
		assert(valueof_PI.IsDouble());
		PI = valueof_PI.GetDouble();
	}
	else
		PI = 3.1415926;
	// alpha
	if (d.HasMember("alpha"))
	{
		const rapidjson::Value& valueof_alpha = d["alpha"];
		assert(valueof_alpha.IsDouble());
		alpha = valueof_alpha.GetDouble();
	}
	else
		alpha = 1.0;
}

/******************** Module definition ********************/

HeatEquation::HeatEquation(CartesianMesh2D* aMesh, const Options& aOptions)
: mesh(aMesh)
, nbNodes(mesh->getNbNodes())
, nbCells(mesh->getNbCells())
, nbFaces(mesh->getNbFaces())
, nbNeighbourCells(CartesianMesh2D::MaxNbNeighbourCells)
, nbNodesOfFace(CartesianMesh2D::MaxNbNodesOfFace)
, nbNodesOfCell(CartesianMesh2D::MaxNbNodesOfCell)
, options(aOptions)
, writer("HeatEquation", options.outputPath)
, lastDump(numeric_limits<int>::min())
, t_n(0.0)
, t_nplus1(0.0)
, X(nbNodes)
, center(nbCells)
, u_n(nbCells)
, u_nplus1(nbCells)
, V(nbCells)
, f(nbCells)
, outgoingFlux(nbCells)
, surface(nbFaces)
{
	// Copy node coordinates
	const auto& gNodes = mesh->getGeometry()->getNodes();
	for (size_t rNodes=0; rNodes<nbNodes; rNodes++)
	{
		X[rNodes][0] = gNodes[rNodes][0];
		X[rNodes][1] = gNodes[rNodes][1];
	}
}

HeatEquation::~HeatEquation()
{
}

/**
 * Job ComputeOutgoingFlux called @1.0 in executeTimeLoopN method.
 * In variables: V, center, deltat, surface, u_n
 * Out variables: outgoingFlux
 */
void HeatEquation::computeOutgoingFlux() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& j1Cells)
	{
		const Id j1Id(j1Cells);
		double reduction0(0.0);
		{
			const auto neighbourCellsJ1(mesh->getNeighbourCells(j1Id));
			const size_t nbNeighbourCellsJ1(neighbourCellsJ1.size());
			for (size_t j2NeighbourCellsJ1=0; j2NeighbourCellsJ1<nbNeighbourCellsJ1; j2NeighbourCellsJ1++)
			{
				const Id j2Id(neighbourCellsJ1[j2NeighbourCellsJ1]);
				const size_t j2Cells(j2Id);
				const Id cfId(mesh->getCommonFace(j1Id, j2Id));
				const size_t cfFaces(cfId);
				double reduction1((u_n[j2Cells] - u_n[j1Cells]) / norm(center[j2Cells] - center[j1Cells]) * surface[cfFaces]);
				reduction0 = sumR0(reduction0, reduction1);
			}
		}
		outgoingFlux[j1Cells] = deltat / V[j1Cells] * reduction0;
	});
}

/**
 * Job ComputeSurface called @1.0 in simulate method.
 * In variables: X
 * Out variables: surface
 */
void HeatEquation::computeSurface() noexcept
{
	parallel::parallel_exec(nbFaces, [&](const size_t& fFaces)
	{
		const Id fId(fFaces);
		double reduction0(0.0);
		{
			const auto nodesOfFaceF(mesh->getNodesOfFace(fId));
			const size_t nbNodesOfFaceF(nodesOfFaceF.size());
			for (size_t rNodesOfFaceF=0; rNodesOfFaceF<nbNodesOfFaceF; rNodesOfFaceF++)
			{
				const Id rId(nodesOfFaceF[rNodesOfFaceF]);
				const Id rPlus1Id(nodesOfFaceF[(rNodesOfFaceF+1+nbNodesOfFace)%nbNodesOfFace]);
				const size_t rNodes(rId);
				const size_t rPlus1Nodes(rPlus1Id);
				reduction0 = sumR0(reduction0, norm(X[rNodes] - X[rPlus1Nodes]));
			}
		}
		surface[fFaces] = 0.5 * reduction0;
	});
}

/**
 * Job ComputeTn called @1.0 in executeTimeLoopN method.
 * In variables: deltat, t_n
 * Out variables: t_nplus1
 */
void HeatEquation::computeTn() noexcept
{
	t_nplus1 = t_n + deltat;
}

/**
 * Job ComputeV called @1.0 in simulate method.
 * In variables: X
 * Out variables: V
 */
void HeatEquation::computeV() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& jCells)
	{
		const Id jId(jCells);
		double reduction0(0.0);
		{
			const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
			const size_t nbNodesOfCellJ(nodesOfCellJ.size());
			for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
			{
				const Id rId(nodesOfCellJ[rNodesOfCellJ]);
				const Id rPlus1Id(nodesOfCellJ[(rNodesOfCellJ+1+nbNodesOfCell)%nbNodesOfCell]);
				const size_t rNodes(rId);
				const size_t rPlus1Nodes(rPlus1Id);
				reduction0 = sumR0(reduction0, det(X[rNodes], X[rPlus1Nodes]));
			}
		}
		V[jCells] = 0.5 * reduction0;
	});
}

/**
 * Job IniCenter called @1.0 in simulate method.
 * In variables: X
 * Out variables: center
 */
void HeatEquation::iniCenter() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& jCells)
	{
		const Id jId(jCells);
		RealArray1D<2> reduction0({0.0, 0.0});
		{
			const auto nodesOfCellJ(mesh->getNodesOfCell(jId));
			const size_t nbNodesOfCellJ(nodesOfCellJ.size());
			for (size_t rNodesOfCellJ=0; rNodesOfCellJ<nbNodesOfCellJ; rNodesOfCellJ++)
			{
				const Id rId(nodesOfCellJ[rNodesOfCellJ]);
				const size_t rNodes(rId);
				reduction0 = sumR1(reduction0, X[rNodes]);
			}
		}
		center[jCells] = 0.25 * reduction0;
	});
}

/**
 * Job IniF called @1.0 in simulate method.
 * In variables: 
 * Out variables: f
 */
void HeatEquation::iniF() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& jCells)
	{
		f[jCells] = 0.0;
	});
}

/**
 * Job ComputeUn called @2.0 in executeTimeLoopN method.
 * In variables: deltat, f, outgoingFlux, u_n
 * Out variables: u_nplus1
 */
void HeatEquation::computeUn() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& jCells)
	{
		u_nplus1[jCells] = f[jCells] * deltat + u_n[jCells] + outgoingFlux[jCells];
	});
}

/**
 * Job IniUn called @2.0 in simulate method.
 * In variables: PI, alpha, center
 * Out variables: u_n
 */
void HeatEquation::iniUn() noexcept
{
	parallel::parallel_exec(nbCells, [&](const size_t& jCells)
	{
		u_n[jCells] = std::cos(2 * options.PI * options.alpha * center[jCells][0]);
	});
}

/**
 * Job ExecuteTimeLoopN called @3.0 in simulate method.
 * In variables: V, center, deltat, f, outgoingFlux, surface, t_n, u_n
 * Out variables: outgoingFlux, t_nplus1, u_nplus1
 */
void HeatEquation::executeTimeLoopN() noexcept
{
	n = 0;
	bool continueLoop = true;
	do
	{
		globalTimer.start();
		cpuTimer.start();
		n++;
		if (!writer.isDisabled() && n >= lastDump + options.outputPeriod)
			dumpVariables(n);
		if (n!=1)
			std::cout << "[" << __CYAN__ << __BOLD__ << setw(3) << n << __RESET__ "] t = " << __BOLD__
				<< setiosflags(std::ios::scientific) << setprecision(8) << setw(16) << t_n << __RESET__;
	
		computeOutgoingFlux(); // @1.0
		computeTn(); // @1.0
		computeUn(); // @2.0
		
	
		// Evaluate loop condition with variables at time n
		continueLoop = (t_nplus1 < options.stopTime && n + 1 < options.maxIterations);
	
		if (continueLoop)
		{
			// Switch variables to prepare next iteration
			std::swap(t_nplus1, t_n);
			std::swap(u_nplus1, u_n);
		}
	
		cpuTimer.stop();
		globalTimer.stop();
	
		// Timers display
		if (!writer.isDisabled())
			std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __BLUE__ << ioTimer.print(true) << __RESET__ "} ";
		else
			std::cout << " {CPU: " << __BLUE__ << cpuTimer.print(true) << __RESET__ ", IO: " << __RED__ << "none" << __RESET__ << "} ";
		
		// Progress
		std::cout << utils::progress_bar(n, options.maxIterations, t_n, options.stopTime, 25);
		std::cout << __BOLD__ << __CYAN__ << utils::Timer::print(
			utils::eta(n, options.maxIterations, t_n, options.stopTime, deltat, globalTimer), true)
			<< __RESET__ << "\r";
		std::cout.flush();
	
		cpuTimer.reset();
		ioTimer.reset();
	} while (continueLoop);
	// force a last output at the end
	dumpVariables(n, false);
}

void HeatEquation::dumpVariables(int iteration, bool useTimer)
{
	if (!writer.isDisabled())
	{
		if (useTimer)
		{
			cpuTimer.stop();
			ioTimer.start();
		}
		auto quads = mesh->getGeometry()->getQuads();
		writer.startVtpFile(iteration, t_n, nbNodes, X.data(), nbCells, quads.data());
		writer.openNodeData();
		writer.closeNodeData();
		writer.openCellData();
		writer.write("Temperature", u_n);
		writer.closeCellData();
		writer.closeVtpFile();
		lastDump = n;
		if (useTimer)
		{
			ioTimer.stop();
			cpuTimer.start();
		}
	}
}

void HeatEquation::simulate()
{
	std::cout << "\n" << __BLUE_BKG__ << __YELLOW__ << __BOLD__ <<"\tStarting HeatEquation ..." << __RESET__ << "\n\n";
	
	std::cout << "[" << __GREEN__ << "TOPOLOGY" << __RESET__ << "]  HWLOC unavailable cannot get topological informations" << std::endl;
	
	if (!writer.isDisabled())
		std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    VTK files stored in " << __BOLD__ << writer.outputDirectory() << __RESET__ << " directory" << std::endl;
	else
		std::cout << "[" << __GREEN__ << "OUTPUT" << __RESET__ << "]    " << __BOLD__ << "Disabled" << __RESET__ << std::endl;

	computeSurface(); // @1.0
	computeV(); // @1.0
	iniCenter(); // @1.0
	iniF(); // @1.0
	iniUn(); // @2.0
	executeTimeLoopN(); // @3.0
	
	std::cout << __YELLOW__ << "\n\tDone ! Took " << __MAGENTA__ << __BOLD__ << globalTimer.print() << __RESET__ << std::endl;
}

/******************** Module definition ********************/

int main(int argc, char* argv[]) 
{
	string dataFile;
	int ret = 0;
	
	if (argc == 2)
	{
		dataFile = argv[1];
	}
	else
	{
		std::cerr << "[ERROR] Wrong number of arguments. Expecting 1 arg: dataFile." << std::endl;
		std::cerr << "(HeatEquationDefault.json)" << std::endl;
		return -1;
	}
	
	// read json dataFile
	ifstream ifs(dataFile);
	rapidjson::IStreamWrapper isw(ifs);
	rapidjson::Document d;
	d.ParseStream(isw);
	assert(d.IsObject());
	
	// mesh
	assert(d.HasMember("mesh"));
	const rapidjson::Value& valueof_mesh = d["mesh"];
	assert(valueof_mesh.IsObject());
	CartesianMesh2DFactory meshFactory;
	meshFactory.jsonInit(valueof_mesh.GetObject());
	CartesianMesh2D* mesh = meshFactory.create();
	
	// options
	HeatEquation::Options options;
	assert(d.HasMember("options"));
	const rapidjson::Value& valueof_options = d["options"];
	assert(valueof_options.IsObject());
	options.jsonInit(valueof_options.GetObject());
	
	// simulator must be a pointer if there is a finalize at the end (Kokkos, omp...)
	auto simulator = new HeatEquation(mesh, options);
	simulator->simulate();
	
	// simulator must be deleted before calling finalize
	delete simulator;
	delete mesh;
	return ret;
}
