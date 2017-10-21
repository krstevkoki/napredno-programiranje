package mk.ukim.finki.aud2;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
    public static double sum(double[][] mat) {
        double sum = 0;
        for (int i = 0; i < mat.length; ++i)
            for (int j = 0; j < mat.length; ++j)
                sum += mat[i][j];
        return sum;
    }

    public static double sumStream(double[][] mat) {
        return Arrays.stream(mat).mapToDouble(row -> Arrays.stream(row).sum()).sum();
    }

    public static double average(double[][] mat) {
        // double s = sum(mat);
        double s = sumStream(mat);
        return s / (mat.length * mat.length);
    }

    public static void main(String[] args) {
        double[][] mat = new double[3][3];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                Scanner doubleScanner = new Scanner(System.in);
                mat[i][j] = doubleScanner.nextDouble();
            }
        }
        // System.out.println("Sumata na elementite na matricata e: " + sum(mat));
        System.out.println("Sumata na elementite na matricata e: " + sumStream(mat));
        System.out.println("Prosekot na elementite na matricata e: " + average(mat));
    }
}
