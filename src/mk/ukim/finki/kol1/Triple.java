package mk.ukim.finki.kol1;

class Triple<T extends Number> {
    private T a, b, c;

    public Triple(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public T getC() {
        return c;
    }

    public double max() {
        double max = a.doubleValue();
        if (max < b.doubleValue())
            max = b.doubleValue();
        if (max < c.doubleValue())
            max = c.doubleValue();
        return max;
    }

    public double average() {
        return (a.doubleValue() + b.doubleValue() + c.doubleValue()) / 3.0;
    }

    public void sort() {
        T temp;
        if (a.doubleValue() > b.doubleValue()) {
            temp = a;
            a = b;
            b = temp;
        }

        if (a.doubleValue() > c.doubleValue()) {
            temp = a;
            a = c;
            c = temp;
        }

        if (b.doubleValue() > c.doubleValue()) {
            temp = b;
            b = c;
            c = temp;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", a.doubleValue(), b.doubleValue(), c.doubleValue());
    }
}
