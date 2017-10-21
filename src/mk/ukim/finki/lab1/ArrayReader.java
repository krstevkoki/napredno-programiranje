package mk.ukim.finki.lab1;

import java.io.InputStream;
import java.util.Scanner;

class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) {
        Scanner intScanner = new Scanner(input);
        int n = intScanner.nextInt();
        int[] temp = new int[n];
        for (int i = 0; i < n; ++i)
            temp[i] = intScanner.nextInt();
        return new IntegerArray(temp);
    }
}
