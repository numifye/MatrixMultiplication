# MatrixMultiplication

Report:


I have three classes: Matrix, MatrixMultiplication, and JUnitTest. In the Matrix class, I implemented the straight-forward matrix multiplication algorithm and Strassen's multiplication algorithm. The runtime of the straight-forward algorithm is THETA(n^3) because there are three for loops, each of which traverse through the array up until the value of n (which is the length of the array). According to the text, and by analyzing Strassen's algorithm, the runtime is THETA(n^lg7). I included a breakdown of Strassen's method using comments in the Matrix class.

In the Matrix class, I have two constructors; a randomize method (which stores random elements inside a matrix); the straight-forward matrix multiplication method; store, build, add, and subtract methods for Strassen's method; and revealMatrix (a method that prints out a matrix) and getArray (which returns a Matrix as an actual 2D int array instead of the Matrix object itself) methods.

My JUnit tests were successful, and I tested each method in the Matrix class to make sure that all the little sub-parts of the code are doing exactly what they are intended to do.

In my tester, I tested the time it took for the Simple method and Strassen's method using different values of n (a power of 2) each time. Each test included creating 2 matrices, randomizing the elements, calling the multiply method, and then printing out the final result matrix. I also did a test with everything altogether to see how well my code would work and how big I could grow my matrices. This involved creating a for loop, creating random matrices, incrementing i from 1 to 15 (meaning the maximum value of n would come out to 2^15, or 32768) and calling both the Simple and Strassen's method in the same loop while printing out the both of the resulting matrices.


RUNNING TIMES:
n = 2		time: Regular 0 seconds, Strassen's 0 seconds, both 0 seconds

n = 16		time: Regular 0 seconds, Strassen's 0 seconds, both 0 seconds

n = 512		time: Regular 0 seconds, Strassen's 25 seconds

n = 1024	time: Regular 32 seconds, Strassen's, 2 minutes 54 seconds

Other Times:

n = 2048	time: Regular 1 minute 44 seconds

n = 4096	time: Regular 24 minutes 56 seconds



NOTE: All of these times include the time it took to add random elements into the matrices when creating them. For the straight-forward 4096x4096 matrix I included the time it took to add random elements into each matrix and also print the final matrix, not just the method itself, which may reflect a higher number.

My implementations all returned the expected results and worked fairly well, but as the value of n increased to very large numbers, the program delayed a lot. I made sure I was getting the right matrix multiplication results by using the JUnit tests and also by calculating some of the very small-sized matrices (like 2x2 or 4x4) by hand. For some reason, my Strassen's method took a little bit longer than the straight-forward method, which is not really supposed to be the case. In the tester, I have the for loop commented out, and matrices of size 16x16 just to show that the code works properly. It prints out the first matrix, second matrix, the resulting matrices from the straight-forward method and Strassen's method, and the boolean value of the two results compared.
