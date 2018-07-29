// Author: Naomi Campbell

import java.util.*;

public class Matrix{
	
	//declare instance variables
	private int[][] matrix;
	private int n; //# of rows AND columns since it's an n x n matrix

	public Matrix (int rowcol)
	{
		n = rowcol;
		int[][] create = new int[rowcol][rowcol];
		matrix = create;
	}
	
	/*
	 * constructor for JUnit test; could also just use one above
	 */
	public Matrix (int[][] a)
	{
		n = a.length;
		matrix = a;
	}
	
        /*
        * For randomizing; inserts random elements from 0-9 into matrix
        */
	public void randomize()
	{
		Random r = new Random();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				matrix[i][j] = r.nextInt(10);
			}
		}
	}
	
	/*
	 * Multiplies two matrices together using the regular O(n^2) method
	 * @param second	the matrix being multiplied
	 * @return result	the final matrix after multiplying
	 */
	
	public Matrix multiply(Matrix second)
	{
		Matrix result = new Matrix(n);
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				for(int k = 0; k<n; k++)
				{
					result.matrix[i][j] += matrix[i][k] * second.matrix[k][j];
				}
			}
		}
		return result;
	}
	
	/*
	 * Divides parent matrix into submatrices by copying parent matrix values over to child matrices
	 * @param parent	the parent 2D array / matrix
	 * @param child		the child matrix (where the values are being copied to
	 * @param a			start
	 * @param b			end
	 */
	
	public static void store(int[][] parent, Matrix child, int a, int b)
	{
		for(int i1 = 0, i2 = a; i1 < child.n; i1++, i2++)
		{
			for(int j1 = 0, j2 = b; j1 < child.n; j1++, j2++)
			{
				child.matrix[i1][j1] = parent[i2][j2];
			}
		}
	}
	
	/*
	 * Copies values from smaller child matrix back to parent matrix
	 * @param parent	the parent 2D array / matrix
	 * @param child		the child matrix (where the values are being copied to
	 * @param a			start
	 * @param b			end
	 */
	
	public static void build(int[][]parent, Matrix child, int a, int b)
	{
		for(int i1 = 0, i2 = a; i1 < child.n; i1++, i2++)
		{
			for(int j1 = 0, j2 = b; j1 < child.n; j1++, j2++)
			{
				parent[i2][j2] = child.matrix[i1][j1];
			}
		}
	}
	
	/*
	 * Add two matrices together
	 * @param a			first matrix
	 * @param b			second matrix
	 * @return result               the final matrix after adding a and b
	 */
	
	public static Matrix add(Matrix a, Matrix b)
	{
		int size = a.matrix.length;
		Matrix result = new Matrix(size);
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				result.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
			}
		}
		return result;
	}
	
	/*
	 * Subtract two matrices
	 * @param a			first matrix
	 * @param b			second matrix
	 * @return result               the final matrix after subtracting b from a
	 */
	
	public static Matrix subtract(Matrix a, Matrix b)
	{
		int size = a.matrix.length;
		Matrix result = new Matrix(size);
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				result.matrix[i][j] = a.matrix[i][j] - b.matrix[i][j];
			}
		}
		return result;
	}
	
	/*
	 * Multiply two matrices using Strassen's algorithm
	 * @param second		matrix being multiplied
	 * @return result		the final matrix after multiplying the two matrices
	 */
	
	public Matrix multiplyStrassen(Matrix second)
	{
		//int[][] r = new int[n][n];
		//Matrix result = new Matrix(r);
		Matrix result = new Matrix(n);
		if (n==1)
		{
			result.matrix[0][0] = matrix[0][0] * second.matrix[0][0];
		}
		else
		{
			//STEP 1: divide input matrices A&B & output matrix C into n/2 x n/2 submatrices
			//Takes O(1) time; need to use store method to transfer values
			
			Matrix A11 = new Matrix(n/2);
			Matrix A12 = new Matrix(n/2);
			Matrix A21 = new Matrix(n/2);
			Matrix A22 = new Matrix(n/2);
			Matrix B11 = new Matrix(n/2);
			Matrix B12 = new Matrix(n/2);
			Matrix B21 = new Matrix(n/2);
			Matrix B22 = new Matrix(n/2);
			Matrix C11 = new Matrix(n/2);
			Matrix C12 = new Matrix(n/2);
			Matrix C21 = new Matrix(n/2);
			Matrix C22 = new Matrix(n/2);
			store(matrix, A11, 0, 0);
			store(matrix, A12, 0, n/2);
			store(matrix, A21, n/2, 0);
			store(matrix, A22, n/2, n/2);
			store(second.matrix, B11, 0, 0);
			store(second.matrix, B12, 0, n/2);
			store(second.matrix, B21, n/2, 0);
			store(second.matrix, B22, n/2, n/2);
			
			//STEP 2: create 10 matrices S1,S2,...,S10 each of which is the sum or difference
			//of 2 matrices created in step 1; THETA(n^2) time; need methods add & subtract
			
			Matrix S1 = subtract(B12, B22);
			Matrix S2 = add(A11, A12);
			Matrix S3 = add(A21, A22);
			Matrix S4 = subtract(B21, B11);
			Matrix S5 = add(A11, A22);
			Matrix S6 = add(B11, B22);
			Matrix S7 = subtract(A12, A22);
			Matrix S8 = add(B21, B22);
			Matrix S9 = subtract(A11, A21);
			Matrix S10 = add(B11, B12);
			
			//STEP 3: recursively multiply n/2xn/2 matrices 7 times to compute
			//detailed explanation page 80 text
			
			Matrix P1 = A11.multiplyStrassen(S1);
			Matrix P2 = S2.multiplyStrassen(B22);
			Matrix P3 = S3.multiplyStrassen(B11);
			Matrix P4 = A22.multiplyStrassen(S4);
			Matrix P5 = S5.multiplyStrassen(S6);
			Matrix P6 = S7.multiplyStrassen(S8);
			Matrix P7 = S9.multiplyStrassen(S10);
			
			 //STEP 4: add and subtract Pi matrices created in step 3 to construct 
			 //the four n/2 x n/2 submatrices of the product C
			 //C11 = P5 + P4 - P2 + P6
			 //C12 = P1 + P2
			 //C21 = P3 + P4
			 //C22 = P5 + P1 - P3 - P7
			
			C11 = add(subtract(add(P5, P4), P2), P6);
			C12 = add(P1, P2);
			C21 = add(P3, P4);
			C22 = subtract(subtract(add(P5, P1), P3), P7);
			
			//LAST STEP: store submatrices of C into a whole, complete matrix "result"
			
			build(result.matrix, C11, 0, 0);
			build(result.matrix, C12, 0, n/2);
			build(result.matrix, C21, n/2, 0);
			build(result.matrix, C22, n/2, n/2);
		}
		return result;
	}
	
	/*
	 * Reveals / prints out matrix
	 */
	
	public void revealMatrix() 
	{
	 for(int i = 0; i < n; i++)	
	 {
		 for (int j = 0; j < n; j++)
		 {
			 System.out.print(matrix[i][j] + " ");
		 }
		 System.out.println();
	 }
	}
	
	/*
	 * Method for tester, to check if 2D arrays are equal
	 * @param a		input matrix
	 * @return		the matrix as a 2D integer array
	 */
	public static int[][] getArray(Matrix a)
	{
		return a.matrix;
	}

}
