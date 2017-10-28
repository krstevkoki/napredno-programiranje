package mk.ukim.finki.lab4;

import java.time.LocalDateTime;

class Timestamp<T> implements Comparable<Timestamp<?>> {
    private final LocalDateTime time;
    private final T element;

    public Timestamp(final LocalDateTime time, final T element) {
        this.time = time;
        this.element = element;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public T getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Timestamp<?> timestamp = (Timestamp<?>) o;
        return time != null ? time.equals(timestamp.time) : timestamp.time == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (element != null ? element.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Timestamp<?> o) {
        return this.time.compareTo(o.time);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(time.toString());
        sb.append(" ");
        sb.append(element.toString());
        return sb.toString();
    }
}
