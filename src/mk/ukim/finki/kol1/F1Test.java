package mk.ukim.finki.kol1;

import java.io.IOException;

public class F1Test {
    public static void main(String[] args) throws IOException {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}
