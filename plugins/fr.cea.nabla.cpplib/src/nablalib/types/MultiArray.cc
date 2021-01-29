/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/

#include "nablalib/types/MultiArray.h"
#include <iostream>
#include <typeinfo>

namespace nablalib::types
{

// Type alias
using Id = size_t;

template<size_t N>
using IntArray1D = MultiArray<int, N>;

template<size_t M, size_t N>
using IntArray2D = MultiArray<int, M, N>;

template<size_t N>
using  RealArray1D = MultiArray<double, N>;

template<size_t M, size_t N>
using RealArray2D = MultiArray<double, M, N>;

