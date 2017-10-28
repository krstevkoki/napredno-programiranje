package mk.ukim.finki.lab4;

class ResizableArray<T> {
    private T[] array;
    private int numElements;

    public ResizableArray() {
        array = (T[]) new Object[0];
        numElements = 0;
    }

    public void addElement(T element) {
        if (numElements == 0 || numElements == array.length) {
            T[] temp = (T[]) new Object[numElements + 5];
            System.arraycopy(array, 0, temp, 0, numElements);
            temp[numElements++] = element;
            array = temp;
        } else
            array[numElements++] = element;
    }

    public boolean removeElement(T element) {
        boolean flag = false;
        int i;
        for (i = 0; i < numElements; ++i) {
            if (array[i].equals(element)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            array[i] = array[--numElements];  // overwrite the element for removal with the last element
            T[] temp = (T[]) new Object[numElements];
            System.arraycopy(array, 0, temp, 0, numElements);
            array = temp;
        }
        return flag;
    }

    public boolean contains(T element) {
        for (T elem : array)
            if (elem != null)
                if (elem.equals(element))
                    return true;
        return false;
    }

    public int count() {
        int counter = 0;
        for (T anArray : array)
            if (anArray != null)
                ++counter;
        return counter;
    }

    public Object[] toArray() {
        Object[] array = new Object[numElements];
        System.arraycopy(this.array, 0, array, 0, numElements);
        return array;
    }

    public T elementAt(int idx) {
        if (idx >= 0 && idx <= count())
            return array[idx];
        throw new ArrayIndexOutOfBoundsException();
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        T[] temp = (T[]) new Object[dest.count() + src.count()];
        System.arraycopy(dest.array, 0, temp, 0, dest.count());
        System.arraycopy(src.array, 0, temp, dest.count(), src.count());
        dest.array = temp;
        dest.numElements = temp.length;
    }

    public boolean isEmpty() {
        return count() == 0;
    }
}
