/*
 * Testing individual methods inside Matrix class
 * to make sure I'm getting expected results:
 * add, subtract, regular method, Strassen's method, store, and build
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//IDE automatically imported these

/**
 * @author Naomi Campbell
*/

public class JUnitTest {
    
    public JUnitTest() {
    }
    
    @Test
    public void testAdd() {
        int[][] first = {{1,2 },{1, 2}};
        int[][] second = {{1, 2},{1, 2}};
        int[][] expected = {{2, 4},{2, 4}};
        Matrix a = new Matrix(first);
        Matrix b = new Matrix(second);
        Matrix c = Matrix.add(a,b);
        Assert.assertArrayEquals(expected, Matrix.getArray(c));
    }
    @Test
    public void testSubtract() {
        int[][] first = {{1,2 },{1, 2}};
        int[][] second = {{1, 2},{1, 2}};
        int[][] expected = {{0, 0},{0, 0}};
        Matrix a = new Matrix(first);
        Matrix b = new Matrix(second);
        Matrix c = Matrix.subtract(a,b);
        Assert.assertArrayEquals(expected, Matrix.getArray(c));
    }
    
    @Test
    public void testRegularMultiply()
    {
        int[][] first = {{1,2 },{1, 2}};
        int[][] second = {{1, 2},{1, 2}};
        int[][] expected = {{3, 6},{3, 6}};
        Matrix a = new Matrix(first);
        Matrix b = new Matrix(second);
        Matrix c = a.multiply(b);
        Assert.assertArrayEquals(expected, Matrix.getArray(c));
    }
    
    @Test
    public void testStrassens()
    {
        int[][] first = {{1,2 },{1, 2}};
        int[][] second = {{1, 2},{1, 2}};
        int[][] expected = {{3, 6},{3, 6}};
        Matrix a = new Matrix(first);
        Matrix b = new Matrix(second);
        Matrix c = a.multiplyStrassen(b);
        Assert.assertArrayEquals(expected, Matrix.getArray(c));
    }
    
    @Test
    public void testStore()
    {
        int[][] parent = {{3,4},{5,4},{0,0,0,0},{0,0,0,0}};
        int[][] child = {{0, 0},{0, 0}};
        int[][] expected = {{3, 4},{5, 4}};
        Matrix b = new Matrix(child);
        Matrix.store(parent, b, 0, 0);
        Assert.assertArrayEquals(expected, Matrix.getArray(b));
        //b.revealMatrix();
        //remove comment symbol above to see result
    }
    
    @Test
    public void testBuild()
    {
        int[][] parent = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        int[][] child = {{3, 4},{5, 4}};
        int[][] expected = {{3,4,0,0},{5,4,0,0},{0,0,0,0},{0,0,0,0}};
        Matrix b = new Matrix(child);
        Matrix.build(parent, b, 0, 0);
        Assert.assertArrayEquals(expected, parent);
        Matrix result = new Matrix(parent);
        //result.revealMatrix();
        //remove comment symbol above to see result
    }
}


