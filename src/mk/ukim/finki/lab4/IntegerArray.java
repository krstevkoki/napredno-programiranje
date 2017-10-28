package mk.ukim.finki.lab4;

class IntegerArray extends ResizableArray<Integer> {
    public IntegerArray() {
        super();
    }

    public double sum() {
        Integer sum = 0;
        for (int i = 0; i < count(); ++i)
            sum += this.elementAt(i);
        return sum.doubleValue();
    }

    public double mean() {
        return sum() / count();
    }

    public int countNonZero() {
        int counter = 0;
        for (int i = 0; i < count(); ++i)
            if (!(elementAt(i).equals(0)))
                ++counter;
        return counter;
    }

    public IntegerArray distinct() {
        IntegerArray ia = new IntegerArray();
        for (int i = 0; i < count(); ++i)
            ia.addElement(this.elementAt(i));
        ia = deleteDuplicates();
        return ia;
    }

    private IntegerArray deleteDuplicates() {
        IntegerArray temp = new IntegerArray();
        for (int i = 0; i < count(); ++i)
            if (!(temp.contains(elementAt(i))))
                temp.addElement(elementAt(i));
        return temp;
    }

    public IntegerArray increment(int offset) {
        IntegerArray ia = new IntegerArray();
        for (int i = 0; i < count(); ++i)
            ia.addElement(elementAt(i) + offset);
        return ia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count(); ++i) {
            sb.append(elementAt(i));
            sb.append(" ");
        }
        return sb.toString();
    }
}
