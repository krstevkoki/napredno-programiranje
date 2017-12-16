package mk.ukim.finki.aud8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

public class StreamAPI {
    public static void main(String[] args) {
        /* ======= Method reference ======= */
        String[] parts = {"Koki", "Ko", "Ki"};
        Arrays.stream(parts).forEach(System.out::println);  // method reference

        PersonFactory<Person> personFactory = Person::new;  // constructor method reference
        Person p = personFactory.create("Petar", "Andreevski");
        System.out.println(p);

        /* ======= Predicate ======= */
        Predicate<String> predicate = str -> str.length() > 0;  // test if string is not empty
        System.out.println(predicate.test(""));  // false
        System.out.println(predicate.test("Hi"));  // true
        System.out.println(predicate.negate().test("Hi"));  // false

        Predicate<Object> nonNull = Objects::nonNull;
        Predicate<Object> isNull = Objects::isNull;

        Person pNull = null;
        System.out.println(nonNull.test(p));  // true
        System.out.println(nonNull.test(pNull));  // false

        System.out.println(isNull.test(p));  // false
        System.out.println(isNull.test(pNull));  // true

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.out.println(isEmpty.test(""));  // true
        System.out.println(isEmpty.test("Hi"));  // false

        System.out.println(isNotEmpty.test(""));  // false
        System.out.println(isNotEmpty.test("Hi"));  // true

        /* ======= Functions ======= */
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(toInteger.apply("123"));
        System.out.println(backToString.apply("231"));

        /* ======= Supplier ======= */
        Supplier<Person> personSupplier = Person::new;  // must have default constructor to work
        pNull = personSupplier.get();  // pNull is no more null
        System.out.println(isNull.test(pNull));  // false
        pNull.setFirstName("Kostadin");
        pNull.setLastName("Krstev");
        pNull.setAge(19);
        System.out.println(pNull);

        /* ======= Consumer ======= */
        Consumer<Person> greeting = person -> System.out.println(String.format("Hello, %s", person));
        greeting.accept(pNull);  // doesn't return value

        Consumer<Integer> multiplyByTwo = num -> System.out.println(num * 2);
        multiplyByTwo.accept(5);

        /* ======= Comparator ======= */
        Comparator<Person> comparator1 = (person1, person2) -> person1.firstName.compareTo(person2.firstName);
        System.out.println(comparator1.compare(p, pNull));  // > 0
        System.out.println(comparator1.compare(pNull, p));  // < 0

        Comparator<Person> comparator2 = Comparator.comparing(person -> person.lastName);  // returns Comparator
        System.out.println(comparator2.compare(p, pNull));  // < 0
        System.out.println(comparator2.compare(pNull, p));  // > 0

        /* ======= Optional ======= */
        Optional<String> optional1 = Optional.of("Hi");
        System.out.println(optional1.isPresent());  // true
        System.out.println(optional1.get());  // Hi
        System.out.println(optional1.orElse("fallback"));  // Hi
        optional1.ifPresent(s -> System.out.println(s.charAt(0)));  // H

        Optional<String> optional2 = Optional.empty();
        System.out.println(optional2.isPresent());  // false
        System.out.println(optional2.orElse("fallback"));  // fallback

        /* ======= Streams ======= */
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream()
                .filter(s -> s.endsWith("3"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);  // BBB3


        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream()
                .filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);  // A1 A2

        Arrays.asList("a1", "a2", "a3").stream()
                .findFirst()
                .ifPresent(System.out::println);  // a1

        Stream.of("b1", "b2", "b3")
                .findFirst()
                .ifPresent(System.out::println);  // b1

        IntStream.range(1, 4)
                .forEach(System.out::println);  // 1 2 3

        System.out.println();

        IntStream.rangeClosed(1, 4)
                .forEach(System.out::println);  // 1 2 3 4

        System.out.println();

        Arrays.stream(new int[]{1, 2, 3})
                .map(num -> num * 2 + 1)
                .forEach(System.out::println);

        System.out.println();

        Arrays.stream(new int[]{1, 2, 3})
                .map(num -> num * 2 + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0

        System.out.println();

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        System.out.println();

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        System.out.println();

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("Filter: " + s);
                    return true;
                });  // nothing is printed

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("Filter: " + s);
                    return true;
                })  // doesn't wait for every element to pass filter
                .forEach(System.out::println);

        System.out.println();

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("Filter: " + s);
                    return true;
                })
                .sorted()  // waits for every element to pass filter
                .forEach(System.out::println);

        System.out.println();

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        System.out.println();

        Stream.of("z2", "d3", "z1", "a1", "b2", "b1", "c1", "a2")
                .sorted()
                .filter(s -> s.endsWith("1"))
                .forEach(System.out::println);  // a1 b1 c1 z1

        System.out.println();

        Stream.of("z2 ", "d3 ", "z1 ", "a1", "b2 ", "b1 ", "c1 ", "a2 ")
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())  // .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::print);

        System.out.println();
        System.out.println();

        boolean anyMatch = Stream.of("z2 ", "d3 ", "z1 ", "a1", "b2 ", "b1 ", "c1 ", "a2 ")
                .anyMatch(s -> s.startsWith("a"));

        boolean allMatch = Stream.of("z2 ", "d3 ", "z1 ", "a1", "b2 ", "b1 ", "c1 ", "a2 ")
                .allMatch(s -> s.startsWith("a"));

        boolean noneMatch = Stream.of("z2 ", "d3 ", "z1 ", "a1", "b2 ", "b1 ", "c1 ", "a2 ")
                .noneMatch(s -> s.startsWith("k"));

        System.out.println(anyMatch);  // true
        System.out.println(allMatch);  // false
        System.out.println(noneMatch);  // true

        System.out.println();

        long startsWithB = Stream.of("z2 ", "d3 ", "z1 ", "a1", "b2 ", "b1 ", "c1 ", "a2 ")
                .filter(s -> s.startsWith("b") || s.startsWith("a"))  // a1 a2 b1 b2
                .count();  // 4
        System.out.println(startsWithB);

        System.out.println();

        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12)
        );

        List<Person> filtered =
                persons.stream()
                        .filter(person -> person.firstName.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(filtered);

        System.out.println();

        Map<String, List<Person>> personsByAge = persons.stream()  // .groupingBy() returns Map<T, List<K>>
                .collect(Collectors.groupingBy(person -> String.valueOf(person.firstName.charAt(0))));
        personsByAge.forEach((firstLetter, person) -> System.out.format("name %s: %s\n", firstLetter, person));

        System.out.println();

        double averageAge = persons.stream()
                .collect(Collectors.averagingInt(person -> person.age));  // 19.0
        System.out.println(averageAge);

        System.out.println();

        IntSummaryStatistics ageSummary = persons.stream()
                .collect(Collectors.summarizingInt(person -> person.age));
        System.out.println(ageSummary);

        System.out.println();

        String phrase = persons.stream()
                .filter(person -> person.age >= 18)
                .map(person -> person.firstName)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age"));
        System.out.println(phrase);

        System.out.println();

        Map<Integer, String> map = persons.stream()
                .collect(Collectors.toMap(
                        person -> person.age,  // key [Integer]
                        person -> person.firstName,  // value [String]
                        (name1, name2) -> name1 + ";" + name2  // merge function in case if key is already in the map
                ));
        System.out.println(map);

        System.out.println();

        Collector<Person, StringJoiner, String> personNameCollector = Collector.of(
                // Strings are immutable [supplier]
                () -> new StringJoiner(" <= ", "Persons: (sorted by age)\n[", "]"),
                (joiner, person) -> joiner.add(person.firstName.toUpperCase()),  // [accumulator]
                StringJoiner::merge,  // (joiner1, joiner2) -> joiner1.merge(joiner2)  [combiner]
                StringJoiner::toString  // [finisher]
        );
        String names = persons.stream()
                .sorted(Comparator.comparingInt(person -> person.age))
                .collect(personNameCollector);
        System.out.println(names);

        System.out.println();

        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo " + i)));
        foos.forEach(foo ->
                IntStream.range(1, 4)
                        .forEach(i -> foo.bars.add(new Bar("Bar " + i + " <- " + foo.name))));
        foos.stream()
                .flatMap(foo -> foo.bars.stream())
                .forEach(bar -> System.out.println(bar.name));

        System.out.println();

        // single pipeline
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo " + i))
                .peek(foo -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar " + 1 + " <- " + foo.name))
                        .forEach(foo.bars::add))
                .flatMap(foo -> foo.bars.stream())
                .forEach(bar -> System.out.println(bar.name));

        System.out.println();

        Optional<String> reduced = Stream.of("z2", "d3", "z1", "a1", "b2", "b1", "c1", "a2")
                .sorted()
                .reduce((str1, str2) -> str1 + "#" + str2);
        reduced.ifPresent(System.out::println);

        System.out.println();

        Person result = persons.stream()
                .reduce(new Person("", 0),  // identity value -> pocetna vrednost za akumuliranje
                        (person1, person2) -> {
                            person1.age += person2.age;
                            person1.firstName += person2.firstName;
                            return person1;
                        }
                );
        System.out.format("name=%s age=%d", result.firstName, result.age);

        System.out.println();

        Integer ageSum = persons.stream()
                .reduce(0,
                        (sum, person) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, person);
                            return sum + person.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);

        System.out.println();

        ageSum = persons.parallelStream()
                .reduce(0,
                        (sum, person) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, person);
                            return sum + person.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);

        System.out.println();

        /* ======= Map ======= */
        Map<Integer, String> hashMap = new HashMap<>();
        IntStream.range(0, 10)
                .forEach(i -> hashMap.putIfAbsent(i, "val " + i));
        hashMap.forEach((id, value) -> System.out.println(value));

        System.out.println();

        hashMap.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(hashMap.get(3));  // val33

        hashMap.computeIfPresent(9, (num, val) -> null);
        System.out.println(hashMap.containsKey(9));  // false

        hashMap.computeIfAbsent(23, num -> "val " + num);
        System.out.println(hashMap.containsKey(23));

        hashMap.remove(3, "val 3");
        System.out.println(map.get(3));

        System.out.println(hashMap.containsValue("val 33"));
        hashMap.remove(3, "val 33");
        System.out.println(map.get(3));

        System.out.println(map.getOrDefault(3, "not found"));

        map.merge(9, "val 9", String::concat);  // (oldValue, newValue) -> oldValue.concat(newValue)
        System.out.println(map.get(9));

        map.merge(9, " concat", String::concat);
        System.out.println(map.get(9));
    }
}

class Person {
    String firstName;
    String lastName;
    int age;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.lastName = "";
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + age;
    }
}

class Foo {
    String name;
    List<Bar> bars;

    public Foo(String name) {
        this.name = name;
        this.bars = new ArrayList<>();
    }
}

class Bar {
    String name;

    public Bar(String name) {
        this.name = name;
    }
}
