package mk.ukim.finki.kol2;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class BookCollection {
    private Set<Book> books;
    private final static Comparator<Book> comparator =
            (book1, book2) -> {
                int result = book1.getTitle().compareToIgnoreCase(book2.getTitle());
                if (result == 0)
                    return Float.compare(book1.getPrice(), book2.getPrice());
                return result;
            };

    public BookCollection() {
        this.books = new TreeSet<>(comparator);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void printByCategory(String category) {
        Predicate<Book> sameCategory
                = book -> book.getCategory().equals(category);
        books.stream()
                .filter(sameCategory)
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n) {
        return this.books.stream()
                .sorted(Comparator.comparing(Book::getPrice)
                        .thenComparing(comparator))
                .limit(n)
                .collect(Collectors.toList());
    }
}
