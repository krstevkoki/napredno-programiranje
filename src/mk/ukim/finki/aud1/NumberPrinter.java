package mk.ukim.finki.aud1;

public class NumberPrinter {
    public static int numberDigit(int n) {
        int counter = 0;
        while(n != 0) {
            ++counter;
            n /= 10;
        }
        return counter;
    }

    public static void formatNumber(int howMany, int lineLength) {
        int printedChars = 0;
        for (int i = 1; i <= howMany; ++i) {
            if ((printedChars < lineLength) && (lineLength - printedChars >= 3)) {
                System.out.print("[" + i + "]");
                printedChars += 2 + numberDigit(i);
            } else {
                printedChars = 0;
                System.out.print("\n[" + i + "]");
                printedChars += 2 + numberDigit(i);
            }
        }
    }

    public static void main(String[] args) {
        formatNumber(250, 60);
    }
}
