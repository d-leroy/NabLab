/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
#include "linearalgebra/kokkos/LinearAlgebraFunctions.h"
#include <iostream>

namespace nablalib
{

/*
 * Simple pretty printing function for Sparse Matrix
 */
 std::string
 LinearAlgebraFunctions::print(const NablaSparseMatrix& M) {
   if (!M.m_matrix) {
     std::stringstream ss;
     for (auto i(0); i < M.m_nb_row; ++i) {
       for (auto j(0); j < M.m_nb_col; ++j) {
         if (j == 0)
           ss << "|";
         auto pos_line(std::find_if(M.m_building_struct.begin(), M.m_building_struct.end(),
                                    [&](const std::pair<int, std::list<std::pair<int, double>>>& line)
                                    {return (line.first == i);}));
         if (pos_line != M.m_building_struct.end()) {
           auto pos_col(std::find_if(pos_line->second.begin(), pos_line->second.end(),
                                     [&](const std::pair<int, double>& col){return col.first == j;}));
           if (pos_col != pos_line->second.end())
             ss << std::setprecision(2) << std::setw(6) << pos_col->second;
           else
             ss << std::setprecision(2) << std::setw(6) << "0";
         } else {
           ss << std::setprecision(2) << std::setw(6) << "0";
         }
         if (j == M.m_nb_col - 1)
           ss << "|";
       }
       ss << std::endl;
     }
     return std::string(ss.str());
   } else {
     return print(*M.m_matrix);
   }
 }  

/*
 * Matlab style printing function for Kokkos Sparse Matrix
 */
std::string
LinearAlgebraFunctions::printMatlabStyle(const NablaSparseMatrix& M, std::string A) {
  if (!M.m_matrix) {
    std::stringstream ss;
    ss << "\n"<< A <<" = sparse(" << M.m_nb_row << ", " << M.m_nb_col << ");\n";
    for (auto i(0); i < M.m_nb_row; ++i) {
      for (auto j(0); j < M.m_nb_col; ++j) {
        ss <<  A << "(" << i+1 << ", " << j+1 << ") = "  << std::setprecision(15) << M(i, j) << ";\n";
      }
    }
    return std::string(ss.str());
  } else {
    return printMatlabStyle(*M.m_matrix, A);
  } 
}

/*
 * Simple pretty printing function for Kokkos Sparse Matrix
 */
std::string
LinearAlgebraFunctions::print(const SparseMatrixType& M) {
  std::stringstream ss;
  for (auto i(0); i < M.numRows(); ++i) {
    for (auto j(0), k(0); j < M.numCols(); ++j) {
	  if (!j)
	    ss << "|";
	  if (!M.rowConst(i).length || j != M.rowConst(i).colidx(k)) {
	    ss << std::setw(2) << "0";
	  } else {
	    ss << std::setw(2) << M.rowConst(i).value(k);
		++k;
	  }
	  ss << " ";
	  if (j == M.numCols() - 1)
	    ss << "|" << std::endl;
	}
  }
  return std::string(ss.str());
}

/*
 * Returns the element (i,j) of a Kokkos Sparse Matrix
 */
double
LinearAlgebraFunctions::getMatrixElement(const SparseMatrixType& M, const size_t& i, const size_t& j) {
  for (auto k(0) ; k < M.rowConst(i).length ; k++)
    if (j == static_cast<size_t>(M.rowConst(i).colidx(k)))
      return M.rowConst(i).value(k);
  // If elements is not stored, then it is zero
  return 0;
}

/*
 * Simple pretty printing function for Nabla Sparse Matrix
 */
std::string
LinearAlgebraFunctions::printMatlabStyle(const SparseMatrixType& M, std::string A) {
  std::stringstream ss;
  ss << "\n"<< A <<" = sparse(" << M.numRows() << ", " << M.numCols() << ");\n";
  for (auto i(0); i < M.numRows(); ++i) {
    for (auto j(0); j < M.numCols(); ++j) {
      double elmt(getMatrixElement(M, i, j));
      if (elmt != 0.) 
	ss <<  A << "(" << i+1 << ", " << j+1 << ") = "  << std::setprecision(15) << elmt << ";\n";
    }
  }
  return std::string(ss.str());
}

/*
 * Simple pretty printing function for Kokkos Vector
 */
std::string
LinearAlgebraFunctions::print(const VectorType& v) {
  std::stringstream ss;

  for (size_t i(0); i < v.extent(0); ++i) {
  if (!i)
    ss << "|";
  ss << std::setw(2) << v(i) << " ";
  if (i == v.extent(0) - 1)
    ss << "|" << std::endl;
  }
  return std::string(ss.str());
}

/*
 * Matlab style pretty printing function for Kokkos Vector
 */
std::string
LinearAlgebraFunctions::printMatlabStyle(const VectorType& v, std::string A) {
  std::stringstream ss;
  ss << "\n"<< A <<" = zeros(" << v.extent(0) << ", 1);\n";
  for (size_t i(0); i < v.extent(0); ++i) {
    ss << A << "(" << i+1 << ") = " << std::setprecision(15) << v(i) << ";\n";
  }
  return std::string(ss.str());
}

/*
 * \brief Conjugate Gradient function (solves A x = b)
 * \param A:         [in] Kokkos sparse matrix
 * \param b:         [in] Kokkos vector
 * \param x0:        [in] Kokkos vector (initial guess, can be null vector)
 * \param info:      [in/out] Misc. informations on computation result
 * \param max_it:    [in] Iteration threshold (default = 200)
 * \param tolerance: [in] Convergence threshold (default = std::numeric_limits<double>::epsilon)
 * \return: Solution vector
 */
VectorType
LinearAlgebraFunctions::CGSolve(const SparseMatrixType& A, const VectorType& b, const VectorType& x0, CGInfo& info,
                                const size_t max_it, const double tolerance) {

  size_t it(0);
  double norm_res(0.0);
  const size_t count(x0.extent(0));

  VectorType p("p", count);
  VectorType r("r", count);
  VectorType Ap("Ap", count);
  VectorType x("x", count);
  // Copy of innitial guess
  Kokkos::deep_copy(x, x0);

  /* r = b - A * x ;*/

  /* p = x */
  Kokkos::deep_copy(p, x);
  /* Ap = A * p */
  KokkosSparse::spmv("N", 1.0, A, p, 0.0, Ap);
  /* b - Ap => r */
  KokkosBlas::update(1., b, -1.0, Ap, 0.0, r);
  /* p = r */
  Kokkos::deep_copy(p, r);

  double old_rdot(KokkosBlas::dot(r, r));
  norm_res = std::sqrt(old_rdot);

  while (tolerance < norm_res && it < max_it) {
    /* pAp_dot = dot(p, Ap = A * p) */

    /* Ap = A * p */
    KokkosSparse::spmv("N", 1.0, A, p, 0.0, Ap);

    VectorType::execution_space().fence();

    const double pAp_dot(KokkosBlas::dot(p, Ap));
    const double alpha(old_rdot / pAp_dot);

    /* x += alpha * p */
    KokkosBlas::axpby(alpha, p, 1.0, x);
    /* r += -alpha * Ap */
    KokkosBlas::axpby(-alpha, Ap, 1.0, r);

    const double r_dot(KokkosBlas::dot(r, r));
    const double beta(r_dot / old_rdot);

    /* p = r + beta * p */
    KokkosBlas::axpby(1.0, r, beta, p);

    norm_res = std::sqrt(old_rdot = r_dot);
    ++it;
  }
  VectorType::execution_space().fence();

  // fill infos
  info.m_display << "---== Solved A * x = b ==---" << std::endl;
  info.m_display << "Nb it = " << it << std::endl;
  info.m_display << "Res = " << norm_res << std::endl;
  info.m_display << "----------------------------" << std::endl;
  info.m_nb_it += it;
  info.m_nb_call++;
  info.m_norm_res += norm_res;

  return x;
}

/*
 * \brief Preconditioned Conjugate Gradient function (solves C^-1(Ax)=C^-1 b)
 * \param A:         [in] Kokkos sparse matrix
 * \param b:         [in] Kokkos vector
 * \param C_minus_1: [in] Kokkos sparse matrix (preconditioner matrix)
 * \param x0:        [in] Kokkos vector (initial guess, can be null vector)
 * \param info:      [in/out] Misc. informations on computation result
 * \param max_it:    [in] Iteration threshold (default = 200)
 * \param tolerance: [in] Convergence threshold (default = std::numeric_limits<double>::epsilon)
 * \return: Solution vector
 */
VectorType
LinearAlgebraFunctions::CGSolve(const SparseMatrixType& A, const VectorType& b,
                                const SparseMatrixType& C_minus_1, const VectorType& x0,
                                CGInfo& info, const size_t max_it, const double tolerance) {
  
  size_t it(0);
  double norm_res(0.0);
  const size_t count(x0.extent(0));

  VectorType p("p", count);
  VectorType r("r", count);
  VectorType Ap("Ap", count);
  VectorType x("x", count);
  // Copy of innitial guess
  Kokkos::deep_copy(x, x0);

  /* r = b - A * x ;*/

  /* p = x */
  Kokkos::deep_copy(p, x);
  /* Ap = A * p */
  KokkosSparse::spmv("N", 1.0, A, p, 0.0, Ap);
  /* b - Ap => r */
  KokkosBlas::update(1., b, -1.0, Ap, 0.0, r);
  
  VectorType z("z", count);
  /* z = C^-1 * r */
  KokkosSparse::spmv("N", 1.0, C_minus_1, r, 0.0, z);
  
  // Kokkos::deep_copy(z, r);
  /* p = z */
  Kokkos::deep_copy(p, z);

  double old_rTz(KokkosBlas::dot(r, z));
  norm_res = std::sqrt(old_rTz);

  while (tolerance < norm_res && it < max_it) {
    /* pAp_dot = dot(p, Ap = A * p) */

    /* Ap = A * p */
    KokkosSparse::spmv("N", 1.0, A, p, 0.0, Ap);

    VectorType::execution_space().fence();

    const double pAp_dot(KokkosBlas::dot(p, Ap));
    const double alpha(old_rTz / pAp_dot);

    /* x += alpha * p */
    KokkosBlas::axpby(alpha, p, 1.0, x);
    /* r += -alpha * Ap */
    KokkosBlas::axpby(-alpha, Ap, 1.0, r);

    /* z = C^-1 * r */
    KokkosSparse::spmv("N", 1.0, C_minus_1, r, 0.0, z);

    
    const double rTz(KokkosBlas::dot(r, z));
    const double beta(rTz / old_rTz);
    
    /* p = z + beta * p */
    KokkosBlas::axpby(1.0, z, beta, p);

    norm_res = std::sqrt(old_rTz = rTz);
    ++it;
  }
  VectorType::execution_space().fence();

  // fill infos
  info.m_display << "---== Solved A * x = b ==---" << std::endl;
  info.m_display << "Nb it = " << it << std::endl;
  info.m_display << "Res = " << norm_res << std::endl;
  info.m_display << "----------------------------" << std::endl;
  info.m_nb_it += it;
  info.m_nb_call++;
  info.m_norm_res += norm_res;

  return x;
}

/*
 * \brief Call to conjugate gradient to solve A x = b
 * \param A:         [in] Sparse matrix
 * \param b:         [in] Vector
 * \param info:      [in/out] Misc. informations on computation result
 * \param x0:        [in/out] Initial guess of the solution. If none is provided, a null vector is used.
 * \param max_it:    [in] Iteration threshold (default = 100)
 * \param tolerance: [in] Convergence threshold (default = 1.e-8)
 * \return: Solution vector
 */
VectorType
LinearAlgebraFunctions::solveLinearSystem(NablaSparseMatrix& A, const VectorType& b, CGInfo& info,
                                          VectorType* x0, const size_t max_it, const double tolerance)
{
  if (!x0) {
    VectorType default_x0("x0", b.extent(0));
    for (size_t i(0); i < b.extent(0); ++i)
      default_x0(i) = 0.0;
    return CGSolve(A.crsMatrix(), b, default_x0, info, max_it, tolerance);
  } else {
    return CGSolve(A.crsMatrix(), b, *x0, info, max_it, tolerance);
  }
}

/*
 * \brief Call to conjugate gradient to solve A x = b with a preconditioner
 *        Actually solves C^-1(Ax)=C^-1 b
 * \param A:         [in] Sparse matrix
 * \param b:         [in] Vector
 * \param C_minus_1: [in] Sparse matrix used as preconditioner
 * \param info:      [in/out] Misc. informations on computation result
 * \param x0:        [in/out] Initial guess of the solution. If none is provided, a null vector is used.
 * \param max_it:    [in] Iteration threshold (default = 100)
 * \param tolerance: [in] Convergence threshold (default = 1.e-8)
 * \return: Solution vector
 */
VectorType
LinearAlgebraFunctions::solveLinearSystem(NablaSparseMatrix& A, const VectorType& b, NablaSparseMatrix& C_minus_1, CGInfo& info,
                                          VectorType* x0, const size_t max_it, const double tolerance)
{
  if (!x0) {
    VectorType default_x0("x0", b.extent(0));
    for (size_t i(0); i < b.extent(0); ++i)
      default_x0(i) = 0.0;
    return CGSolve(A.crsMatrix(), b, C_minus_1.crsMatrix(), default_x0, info, max_it, tolerance);
  } else {
    return CGSolve(A.crsMatrix(), b, C_minus_1.crsMatrix(), *x0, info, max_it, tolerance);
  }
}
  
}  // nablalib
