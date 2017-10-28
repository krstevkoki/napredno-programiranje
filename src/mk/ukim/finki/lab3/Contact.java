package mk.ukim.finki.lab3;

import java.io.Serializable;
import java.util.Arrays;

class Contact implements Serializable, Comparable {
    private String name;
    private String[] phones;
    private int numPhones;

    public Contact(String name, String phone) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        if (isValidName(name) && isValidPhone(phone)) {
            this.name = name;
            phones = new String[5];
            numPhones = 0;
            addNumber(phone);
        }
    }

    public Contact(String name, String phone1, String phone2, String phone3) throws InvalidNameException,
            InvalidNumberException, MaximumSizeExceddedException {
        if (isValidName(name) && isValidPhone(phone1) && isValidPhone(phone2) && isValidPhone(phone3)) {
            this.name = name;
            phones = new String[5];
            numPhones = 0;
            addNumber(phone1);
            addNumber(phone2);
            addNumber(phone3);
        }
    }

    public Contact(String name) throws InvalidNameException {
        if (isValidName(name))
            this.name = name;
        this.phones = new String[0];
        numPhones = 0;
    }

    public Contact(String name, String[] phones) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        if (isValidName(name) && areValidPhones(phones)) {
            this.name = name;
            this.phones = new String[5];
            numPhones = 0;
            for (int i = 0; i < phones.length; ++i) {
                if (phones[i] != null)
                    addNumber(phones[i]);
            }
        }
    }


    private static boolean isValidName(String name) throws InvalidNameException {
        if (name.length() > 4 && name.length() <= 10 && name.matches("[a-zA-Z_0-9]+"))
            return true;
        throw new InvalidNameException(name);
    }

    private static boolean isValidPhone(String phoneNumber) throws InvalidNumberException {
        String[] validOperators = {"070", "071", "072", "075", "076", "077", "078"};
        if (phoneNumber.length() != 9)
            throw new InvalidNumberException();
        for (int i = 0; i < phoneNumber.length(); ++i)
            if (phoneNumber.charAt(i) < 48 || phoneNumber.charAt(i) > 57)
                throw new InvalidNumberException();
        String operator = phoneNumber.substring(0, 3);
        if (!(operator.equals(validOperators[0]))
                && !(operator.equals(validOperators[1]))
                && !(operator.equals(validOperators[2]))
                && !(operator.equals(validOperators[3]))
                && !(operator.equals(validOperators[4]))
                && !(operator.equals(validOperators[5]))
                && !(operator.equals(validOperators[6])))
            throw new InvalidNumberException();
        return true;
    }

    private static boolean areValidPhones(String[] phones) throws InvalidNumberException,
            MaximumSizeExceddedException {
        if (phones.length > 5)
            throw new MaximumSizeExceddedException();
        for (String phone : phones)
            if (!(isValidPhone(phone)))
                throw new InvalidNumberException();
        return true;
    }

    public String getName() {
        return name;
    }

    public String[] getNumbers() {
        String[] temp = new String[numPhones];
        System.arraycopy(phones, 0, temp, 0, numPhones);
        Arrays.sort(temp);
        return temp;
    }

    public void addNumber(String phoneNumber) throws MaximumSizeExceddedException, InvalidNumberException {
        if (numPhones == 5)
            throw new MaximumSizeExceddedException();
        if (isValidPhone(phoneNumber))
            phones[numPhones++] = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        sb.append(numPhones);
        sb.append("\n");
        for (int i = 0; i < numPhones; ++i) {
            sb.append(getNumbers()[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        Contact c = (Contact) o;
        return this.name.compareTo(c.name);
    }
}
