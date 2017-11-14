package mk.ukim.finki.kol1;

class MinMax<T extends Comparable<T>> {
    private T max;
    private T min;
    private int processedElements;
    private int maxCount;
    private int minCount;

    public MinMax() {
        max = null;
        min = null;
        processedElements = 0;
        maxCount = 0;
        minCount = 0;
    }

    public T max() {
        return max;
    }

    public T min() {
        return min;
    }

    public void update(T element) {
        if (processedElements == 0)
            max = min = element;
        if (max.compareTo(element) == 0)
            ++maxCount;
        if (min.compareTo(element) == 0)
            ++minCount;
        if (max.compareTo(element) < 0) {
            max = element;
            maxCount = 1;
        }
        if (min.compareTo(element) > 0) {
            min = element;
            minCount = 1;
        }
        ++processedElements;
    }

    @Override
    public String toString() {
        return min.toString() + " " + max.toString() + " " + (processedElements - (maxCount + minCount)) + "\n";
    }
}
