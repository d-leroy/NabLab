#ifndef __BATILIB__BATILIBCPP
#define __BATILIB__BATILIBCPP

#include <iostream>
#include <string>
#include "batilib/IBatiLibCpp.h"

using namespace nablalib::types;

namespace batilib
{
	class BatiLibCpp : public IBatiLibCpp
	{
	public:
		void jsonInit(const char* jsonContent) override;

		double nextWaveHeight()
		{
			std::cout << "C++ BatiLib::nextWaveHeight" << std:: endl;
			return 1.0;
		}

		template<size_t x>
		double nextDepth1(double x0, RealArray1D<x> x1)
		{
			std::cout << "C++ BatiLib::nextDepth1" << std:: endl;
			double sum = x0;
			for (size_t i=0 ; i<x1.size() ; ++i)
				sum += x1[i];
			return sum;
		}

		template<size_t x, size_t y>
		double nextDepth2(double x0, RealArray2D<x,y> x1)
		{
			std::cout << "C++ BatiLib::nextDepth2" << std:: endl;
			double sum = x0;
			for (size_t i=0 ; i<x1.size() ; ++i)
				for (size_t j=0 ; j<x1[i].size() ; ++j)
					sum += x1[i][j];
			return sum;
		}

		template<size_t x>
		RealArray1D<x> nextDepth3(RealArray1D<x> x0)
		{
			std::cout << "C++ BatiLib::nextDepth3" << std:: endl;
			RealArray1D<x> ret;
			ret.initSize(x0.size());
			for (size_t i=0 ; i<x0.size() ; ++i)
				ret[i] = 2*x0[i];
			return ret;
		}

		template<size_t x, size_t y>
		RealArray2D<x,y> nextDepth4(RealArray2D<x,y> x0)
		{
			std::cout << "C++ BatiLib::nextDepth4" << std:: endl;
			RealArray2D<x,y> ret;
			ret.initSize(x0.size(), x0[0].size());
			for (size_t i=0 ; i<x0.size() ; ++i)
				for (size_t j=0 ; j<x0[i].size() ; ++j)
					ret[i][j] = 2*x0[i][j];
			return ret;
		}

	private:
			double depth = 4.3;
			std::string fileName = "";
	};
}

#endif // __BATILIB__BATILIBCPP
