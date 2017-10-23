package mk.ukim.finki.lab2;

import java.util.Arrays;
import java.util.Collections;

class DoubleMatrix {
    private double[][] matrica;

    public DoubleMatrix(double[] a, int m, int n) throws InsufficientElementsException {
        double[][] mat = new double[m + 1][n + 1];
        if (a.length < m * n)
            throw new InsufficientElementsException("Insufficient number of elements");
        else if (a.length > m * n) {
            for (int i = 1, k = a.length - (m * n); i <= m; ++i)
                for (int j = 1; j <= n; ++j)
                    mat[i][j] = a[k++];
        } else {
            for (int i = 1, k = 0; i <= m; ++i)
                for (int j = 1; j <= n; ++j)
                    mat[i][j] = a[k++];
        }
        this.matrica = mat;
    }

    public String getDimensions() {
        return "[" + rows() + " x " + columns() + "]";
    }

    public int rows() {
        return matrica.length - 1;
    }

    public int columns() {
        return matrica[0].length - 1;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if (row >= 1 && row <= rows()) {
            double max = matrica[row][1];
            for (int j = 1; j <= columns(); ++j) {
                if (matrica[row][j] > max)
                    max = matrica[row][j];
            }
            return max;
        }
        throw new InvalidRowNumberException("Invalid row number");
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if (column >= 1 && column <= columns()) {
            double max = matrica[1][column];
            for (int i = 1; i <= rows(); ++i) {
                if (matrica[i][column] > max)
                    max = matrica[i][column];
            }
            return max;
        }
        throw new InvalidColumnNumberException("Invalid column number");
    }

    public double sum() {
        double sum = 0.0;
        for (int i = 1; i <= rows(); ++i) {
            for (int j = 1; j <= columns(); ++j) {
                sum += matrica[i][j];
            }
        }
        return sum;
    }

    public double[] toSortedArray() {
        Double[] sortedArray = new Double[rows() * columns()];
        for (int i = 1, k = 0; i <= rows(); ++i) {
            for (int j = 1; j <= columns(); ++j) {
                sortedArray[k++] = matrica[i][j];
            }
        }
        Arrays.sort(sortedArray, Collections.reverseOrder());
        double[] temp = new double[rows() * columns()];
        for (int i = 0; i < temp.length; ++i) {
            temp[i] = sortedArray[i];
        }
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DoubleMatrix that = (DoubleMatrix) o;
        return Arrays.deepEquals(matrica, that.matrica);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrica);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= rows(); ++i) {
            for (int j = 1; j <= columns(); ++j) {
                sb.append(String.format("%.2f", matrica[i][j]));
                if (j < columns())
                    sb.append("\t");
            }
            if (i < rows() - 1)
                sb.append("\n");
        }
        return sb.toString();
    }
}
