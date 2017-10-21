package mk.ukim.finki.lab1;

import java.util.Arrays;

final class IntegerArray {
    private final int[] array;

    public IntegerArray(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public int length() {
        return array.length;
    }

    public int getElementAt(int i) {
        return array[i];
    }

    public int sum() {
        int sum = 0;
        for (int elem : array)
            sum += elem;
        return sum;
    }

    public double average() {
        return sum() / (double) length();
    }

    public IntegerArray getSorted() {
        IntegerArray ia = new IntegerArray(Arrays.copyOf(array, array.length));
        Arrays.sort(ia.array);
        return ia;
    }

    public IntegerArray concat(IntegerArray ia) {
        int[] concatenated = new int[this.length() + ia.length()];
        for (int i = 0; i < this.length(); ++i)
            concatenated[i] = this.array[i];
        for (int i = this.length(), j = 0; i < concatenated.length; ++i, ++j)
            concatenated[i] = ia.array[j];
        return new IntegerArray(concatenated);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.length() - 1; ++i) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[this.length() - 1]);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + Arrays.hashCode(array);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        IntegerArray ia = (IntegerArray) obj;
        return Arrays.equals(ia.array, ia.array) && this.hashCode() == ia.hashCode();
    }
}
