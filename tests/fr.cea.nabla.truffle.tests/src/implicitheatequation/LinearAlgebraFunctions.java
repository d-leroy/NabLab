/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package implicitheatequation;

import java.text.DecimalFormat;

import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.ConjugateGradient;
import org.apache.commons.math3.linear.IterativeLinearSolver;
import org.apache.commons.math3.linear.RealVector;

import fr.cea.nabla.javalib.types.Matrix;
import fr.cea.nabla.javalib.types.Vector;

public class LinearAlgebraFunctions 
{
	
	public static double[] solveLinearSystem(double[][] a, double[] b) {
		final AbstractRealMatrix mA = new Array2DRowRealMatrix(a);
		final ArrayRealVector vB = new ArrayRealVector(b);
		final RealVector x = solveLinearSystem(mA, vB);
		return x.toArray();
	}
	
	public static Vector solveLinearSystem(Matrix a, Vector b)
	{
		final RealVector x = solveLinearSystem(a.getNativeMatrix(), b.getNativeVector());
		return new Vector(x);
	}

	public static RealVector solveLinearSystem(AbstractRealMatrix a, RealVector b)
	{
		final int maxIterations = 100;
		final IterativeLinearSolver m_solver = new ConjugateGradient(maxIterations, 1E-10, true);
		return m_solver.solve(a, b);
	}

	public static void print(double[] x, DecimalFormat df)
	{
		System.out.print("\t[ ");
		for (int i=0 ; i<x.length ; ++i) 
			System.out. print(df.format(x[i]) + " ");
		System.out.println("]");
	}

	public static void print(double[][] x, DecimalFormat df)
	{
		for (int i=0 ; i<x.length ; ++i)
			print(x[i], df);
	}
}
