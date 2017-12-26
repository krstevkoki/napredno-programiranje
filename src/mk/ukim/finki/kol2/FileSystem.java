package mk.ukim.finki.kol2;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class FileSystem {
    private Map<Character, Set<File>> folders;

    public FileSystem() {
        this.folders = new HashMap<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt) {
        this.folders.computeIfAbsent(folder, value -> new TreeSet<>());

        this.folders.computeIfPresent(folder, (key, value) -> {
            value.add(new File(name, size, createdAt));
            return value;
        });
    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size) {
        Predicate<File> startsWithDot =
                file -> file.getName().startsWith(".");
        Predicate<File> sizeLessThan =
                file -> file.getSize() < size;

        return this.folders.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(startsWithDot)
                .filter(sizeLessThan)
                .collect(Collectors.toList());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders) {
        return folders.stream()
                .flatMap(character -> this.folders.get(character).stream())
                .mapToInt(File::getSize)
                .sum();
    }

    public Map<Integer, Set<File>> byYear() {
        return this.folders.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        file -> file.getDateOfCreation().getYear(),
                        Collectors.toSet()
                ));
    }

    public Map<String, Long> sizeByMonthAndDay() {
        return this.folders.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        File::getDayAndMonth,
                        Collectors.summingLong(File::getSize)
                        ));
    }
}
