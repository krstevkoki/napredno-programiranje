package mk.ukim.finki.lab2;

import java.io.InputStream;
import java.util.Scanner;

class MatrixReader {
    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
        Scanner doubleScanner = new Scanner(input);
        int rows = doubleScanner.nextInt();
        int cols = doubleScanner.nextInt();
        double[] mat = new double[rows * cols];
        for (int i = 0; i < rows * cols; ++i) {
            mat[i] = doubleScanner.nextDouble();
        }
        return new DoubleMatrix(mat, rows, cols);
    }
}
