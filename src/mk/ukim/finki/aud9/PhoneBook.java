package mk.ukim.finki.aud9;

import java.util.*;

class PhoneBook {
    private Set<String> allNumbers;
    private Map<String, Set<Contact>> byNumberPart;
    private Map<String, Set<Contact>> byName;
    private static Comparator<Contact> comparator = Comparator.
            comparing(Contact::getName)
            .thenComparing(Contact::getNumber);

    public PhoneBook() {
        this.byNumberPart = new TreeMap<>();
        this.byName = new HashMap<>();
        this.allNumbers = new HashSet<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (allNumbers.contains(number))
            throw new DuplicateNumberException(String.format("Duplicate number: %s", number));
        Contact contact = new Contact(name, number);

        Set<Contact> contactsByName = byName.computeIfAbsent(name, value -> new TreeSet<>(comparator));
        contactsByName.add(contact);

        List<String> keys = getKeys(number, 3);
        for (String key : keys) {
            Set<Contact> contactsByNumber = byNumberPart.computeIfAbsent(key, value -> new TreeSet<>(comparator));
            contactsByNumber.add(contact);
        }
    }

    public void contactsByNumber(String number) {
        if (byNumberPart.containsKey(number))
            byNumberPart.get(number).forEach(System.out::println);
    }

    public void contactsByName(String name) {
        if (byName.containsKey(name))
            byName.get(name).forEach(System.out::println);
    }

    private List<String> getKeys(String key, int minLength) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= key.length() - minLength; ++i)
            for (int j = minLength; j <= key.length() - i; ++j)
                result.add(key.substring(i, i + j));
        return result;
    }
}
