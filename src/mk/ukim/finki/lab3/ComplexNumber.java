package mk.ukim.finki.lab3;

class ComplexNumber<T extends Number, U extends Number> implements Comparable {
    private T real;
    private U imag;

    public ComplexNumber(T real, U imag) {
        this.real = real;
        this.imag = imag;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imag;
    }

    public double modul() {
        return Math.sqrt(Math.pow(real.doubleValue(), 2) + Math.pow(imag.doubleValue(), 2));
    }

    public int compareTo(ComplexNumber<?, ?> o) {
        return Double.compare(this.modul(), o.modul());
    }

    @Override
    public int compareTo(Object o) {
        ComplexNumber<?, ?> cn = (ComplexNumber<?, ?>) o;
        if (Double.compare(this.modul(), cn.modul()) == 0)
            return 0;
        else if (Double.compare(this.modul(), cn.modul()) == 1)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (imag.doubleValue() >= 0.0)
            return String.format("%.2f", real.doubleValue()) + "+" + String.format("%.2f", imag.doubleValue()) + "i";
        else
            return String.format("%.2f", real.doubleValue()) + String.format("%.2f", imag.doubleValue()) + "i";
    }
}
