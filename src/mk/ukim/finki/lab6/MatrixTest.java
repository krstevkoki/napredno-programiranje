package mk.ukim.finki.lab6;

import java.util.Scanner;

public class MatrixTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int t = jin.nextInt();
        if (t == 0) {
            int r = jin.nextInt();
            int c = jin.nextInt();
            Matrix<Integer> matrix = new Matrix<Integer>(r, c);
            print(matrix);
        }
        if (t == 1) {
            int r = jin.nextInt();
            int c = jin.nextInt();
            Matrix<Integer> matrix = new Matrix<Integer>(r, c);
            for (int i = 0; i < r; ++i) {
                for (int k = 0; k < c; ++k) {
                    matrix.setElementAt(i, k, jin.nextInt());
                }
            }
            print(matrix);
        }
        if (t == 2) {
            int r = jin.nextInt();
            int c = jin.nextInt();
            Matrix<String> matrix = new Matrix<String>(r, c);
            for (int i = 0; i < r; ++i) {
                for (int k = 0; k < c; ++k) {
                    matrix.setElementAt(i, k, jin.next());
                }
            }
            print(matrix);
        }
        if (t == 3) {
            int r = jin.nextInt();
            int c = jin.nextInt();
            Matrix<String> matrix = new Matrix<String>(r, c);
            for (int i = 0; i < r; ++i) {
                for (int k = 0; k < c; ++k) {
                    matrix.setElementAt(i, k, jin.next());
                }
            }
            print(matrix);
            matrix.deleteRow(jin.nextInt());
            matrix.deleteRow(jin.nextInt());
            print(matrix);
            int ir = jin.nextInt();
            matrix.insertRow(ir);
            for (int k = 0; k < c; ++k) {
                matrix.setElementAt(ir, k, jin.next());
            }
            ir = jin.nextInt();
            matrix.insertRow(ir);
            for (int k = 0; k < c; ++k) {
                matrix.setElementAt(ir, k, jin.next());
            }
            print(matrix);
            matrix.deleteColumn(jin.nextInt());
            matrix.deleteColumn(jin.nextInt());
            print(matrix);
            int ic = jin.nextInt();
            matrix.insertColumn(ir);
            for (int i = 0; i < r; ++i) {
                matrix.setElementAt(i, ic, jin.next());
            }
            ic = jin.nextInt();
            matrix.insertColumn(ic);
            for (int i = 0; i < r; ++i) {
                matrix.setElementAt(i, ic, jin.next());
            }
            print(matrix);
        }
        if (t == 4) {
            int r = jin.nextInt();
            int c = jin.nextInt();
            Matrix<Integer> matrix = new Matrix<Integer>(r, c);
            for (int i = 0; i < r; ++i) {
                for (int k = 0; k < c; ++k) {
                    matrix.setElementAt(i, k, jin.nextInt());
                }
            }
            print(matrix);
            int nr = jin.nextInt();
            int nc = jin.nextInt();
            matrix.resize(nr, nc);
            print(matrix);
            matrix.fill(jin.nextInt());
            print(matrix);
        }
    }

    public static void print(Matrix<?> m) {
        int r = m.getNumRows();
        int c = m.getNumColumns();
        System.out.println("  " + r + " x " + c);
        System.out.print("    ");
        for (int k = 0; k < c; ++k) {
            System.out.print(k + "    ");
        }
        System.out.println();
        System.out.print("  ");
        for (int k = 0; k < c; ++k) {
            System.out.print("-----");
        }
        System.out.println();
        for (int i = 0; i < r; ++i) {
            System.out.print(i + "|");
            for (int k = 0; k < c; ++k) {
                if (k > 0) System.out.print(" ");
                System.out.print(m.getElementAt(i, k));
            }
            System.out.println();
        }
        System.out.println();
    }
}
