package mk.ukim.finki.lab1;

class RomanConverter {
    private static final int[] decimal = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static final String[] letters = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        // your solution here
        StringBuilder roman = new StringBuilder();
        while (n > 0) {
            int maxFound = 0;
            for (int i = 0; i < decimal.length; ++i)
                if (n >= decimal[i])
                    maxFound = i;
            roman.append(letters[maxFound]);
            n -= decimal[maxFound];
        }
        return roman.toString();
    }
}
