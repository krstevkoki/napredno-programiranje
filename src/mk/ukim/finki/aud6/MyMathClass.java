package mk.ukim.finki.aud6;

import java.util.ArrayList;

public class MyMathClass {
    private static double standardDeviation(ArrayList<? extends Number> list) {
        double sum = 0;
        for (Number number : list)
            sum += number.doubleValue();
        double average = sum / list.size();
        sum = 0;
        for (Number number : list)
            sum += (number.doubleValue() - average) * (number.doubleValue() - average);
        return Math.sqrt(sum / list.size());
    }

    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(10);
        ints.add(20);
        ints.add(30);
        ints.add(40);
        ints.add(50);
        System.out.println(String.format("STD: %.2f", MyMathClass.standardDeviation(ints)));
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(3.4);
        System.out.println(String.format("STD: %.2f", MyMathClass.standardDeviation(doubles)));
    }
}
