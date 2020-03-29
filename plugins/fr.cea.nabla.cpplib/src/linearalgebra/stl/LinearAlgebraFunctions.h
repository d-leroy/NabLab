/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
#ifndef LINEARALGEBRA_STL_LINEARALGEBRAFUNCTIONS_H_
#define LINEARALGEBRA_STL_LINEARALGEBRAFUNCTIONS_H_

#include "linearalgebra/stl/Matrix.h"

namespace nablalib
{

namespace LinearAlgebraFunctions
{
  struct CGInfo {
    int m_nb_it;
    double m_norm_res;
    std::stringstream m_display;
  };

  std::string print(const NablaSparseMatrix& M);
  std::string print(const SparseMatrixType& M);
  std::string print(const VectorType& v);
  void CGSolve(const SparseMatrixType& A, const VectorType& b, VectorType& x, CGInfo& info,
               const size_t max_it = 200, const double tolerance = std::numeric_limits<double>::epsilon());
  VectorType solveLinearSystem(NablaSparseMatrix& A, const VectorType& b, CGInfo& info);
}
}

#endif /* LINEARALGEBRA_STL_LINEARALGEBRAFUNCTIONS_H_ */
