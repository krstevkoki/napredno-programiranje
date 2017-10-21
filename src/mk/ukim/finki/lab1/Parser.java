package mk.ukim.finki.lab1;

interface Parser {
    static double parseStringToDouble(String num) {
        return Double.parseDouble(num.substring(0, num.length() - 1));
    }
}
