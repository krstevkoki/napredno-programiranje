package mk.ukim.finki.aud3;

public class DateTester {
    public static void main(String[] args) {
        System.out.println("First year is: " + Date.getFirstYear());
        System.out.println("Last year is: " + Date.getLastYear());

        System.out.println();

        Date date = new Date(1, 2, 1804);
        System.out.println(date.toString());

        System.out.println();

        Date sample = new Date(2, 1, 1800);
        date = new Date(1, 1, 1800);
        Date substr = new Date(sample.substract(date));
        System.out.println(sample.toString() + " - " + date.toString() + " = " + substr.toString());
        System.out.println(sample.getDays());
        System.out.println(date.getDays());
        System.out.println(substr.getDays());

        System.out.println();

        sample = new Date(1, 1, 1800);
        System.out.println(sample);

        System.out.println();

        sample = new Date(31, 12, 2500);
        System.out.println(Date.getDaysTillJan1()[Date.getDaysTillJan1().length - 1]);
        System.out.println(sample.getDays());
        System.out.println(sample.toString());

        System.out.println();

        sample = new Date(30, 11, 2012);
        System.out.println(sample.toString());

        System.out.println();

        sample = sample.increment(1);
        System.out.println(sample.toString());

        System.out.println();

        sample = sample.increment(365);
        System.out.println(sample.toString());
    }
}
