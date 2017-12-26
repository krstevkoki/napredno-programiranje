package mk.ukim.finki.kol2;

import java.time.LocalDateTime;
import java.util.Comparator;

class File implements Comparable<File> {
    private String name;
    private Integer size;
    private LocalDateTime dateOfCreation;

    public File(String name, Integer size, LocalDateTime dateOfCreation) {
        this.name = name;
        this.size = size;
        this.dateOfCreation = dateOfCreation;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public String getDayAndMonth() {
        return dateOfCreation.getMonth().toString().toUpperCase() + "-" + dateOfCreation.getDayOfMonth();
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s", name, size, dateOfCreation);
    }

    @Override
    public int compareTo(File o) {
        return Comparator
                .comparing(File::getDateOfCreation)
                .thenComparing(File::getName)
                .thenComparing(File::getSize)
                .compare(this, o);
    }
}
