// Author: Naomi Campbell

import java.util.Arrays;

//import java.util.*;


public class MatrixMultiplicationTest {

	public static void main(String[] args) {
		 //for(int i = 1; i <= 15; i++) //goes from 4 to 32768
		 //{
		 //Matrix m1 = new Matrix((int)Math.pow(2, i));
		 //Matrix m2 = new Matrix((int)Math.pow(2, i));
		 Matrix m1 = new Matrix(16);
		 Matrix m2 = new Matrix(16);
		 m1.randomize();
		 m2.randomize();
                 //System.out.println("Value of n is " + Math.pow(2,i));
		 System.out.println("Original Matrix A: ");
		 m1.revealMatrix();
		 System.out.println("Original Matrix B: ");
		 m2.revealMatrix();
		 Matrix productRegular = m1.multiply(m2);
		 Matrix productStrassen = m1.multiplyStrassen(m2);
		 System.out.println("Matrix Multiplication Results: ");
		 System.out.println("Regular Method: ");
		 productRegular.revealMatrix();
		 System.out.println("Strassen's Method: ");
		 productStrassen.revealMatrix();
		 //System.out.println("Are matrices the same? " + productStrassen.equals(productRegular));
		 System.out.println("Are matrices the same? " + Arrays.deepEquals(Matrix.getArray(productRegular), Matrix.getArray(productStrassen)));
		 }
	//}//

}
