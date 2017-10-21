package mk.ukim.finki.aud2;

import java.math.BigDecimal;

public class BigComplex {
    private final BigDecimal real;
    private final BigDecimal imag;

    public BigComplex() {

        real = new BigDecimal(0);
        imag = new BigDecimal(0);
    }

    public BigComplex(BigDecimal real, BigDecimal imag) {
        this.real = real;
        this.imag = imag;
    }

    public BigDecimal getReal() {
        return real;
    }

    public BigDecimal getImag() {
        return imag;
    }

    public BigComplex add(BigComplex complex) {
        BigDecimal real = this.real.add(complex.real);
        BigDecimal imag = this.imag.add(complex.imag);
        return new BigComplex(real, imag);
    }

    public BigComplex substract(BigComplex complex) {
        BigDecimal real = this.real.subtract(complex.real);
        BigDecimal imag = this.imag.subtract(complex.imag);
        return new BigComplex(real, imag);
    }

    public BigComplex multiply(BigComplex complex) {
        BigDecimal temp1 = this.real.multiply(complex.real);  // real * real - imag * imag
        BigDecimal temp2 = this.imag.multiply(complex.imag);  // real * imag + imag * real
        BigDecimal real = temp1.subtract(temp2);
        temp1 = this.real.multiply(complex.imag);
        temp2 = this.imag.multiply(complex.real);
        BigDecimal imag = temp1.add(temp2);
        return new BigComplex(real, imag);
    }

    @Override
    public String toString() {
        String str = getReal().toString();
        if (getImag().compareTo(new BigDecimal(0)) > -1)
            str += "+" + getImag().toString() + "j";
        else
            str += getImag().toString() + "j";
        return str;
    }

    public static void main(String[] args) {
        BigComplex bc1 = new BigComplex(new BigDecimal(2), new BigDecimal(1));
        BigComplex bc2 = new BigComplex(new BigDecimal(3), new BigDecimal(-2));
        BigComplex add = new BigComplex();
        BigComplex substract = bc1.substract(bc2);
        BigComplex multiply = bc1.multiply(bc2);
        add = bc1.add(bc2);
        System.out.println(add.toString());
        System.out.println(substract.toString());
        System.out.println(multiply.toString());
    }
}
