package mk.ukim.finki.lab3;

import java.io.*;
import java.util.Arrays;

class PhoneBook implements Serializable {
    private Contact[] contacts;

    public PhoneBook() {
        contacts = new Contact[0];
    }

    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if (contacts.length == 250)
            throw new MaximumSizeExceddedException();
        for (int i = 0; i < contacts.length; ++i)
            if (contacts[i].getName().equals(contact.getName()))
                throw new InvalidNameException(contact.getName());
        Contact[] temp = new Contact[contacts.length + 1];
        System.arraycopy(contacts, 0, temp, 0, contacts.length);
        temp[contacts.length] = contact;
        contacts = temp;
    }

    public Contact getContactForName(String name) {
        for (Contact contact : contacts)
            if (contact.getName().equals(name))
                return contact;
        return null;
    }

    public int numberOfContacts() {
        return contacts.length;
    }

    public Contact[] getContacts() {
        Contact[] tmp = new Contact[contacts.length];
        System.arraycopy(contacts, 0, tmp, 0, contacts.length);
        Arrays.sort(contacts);
        Arrays.sort(tmp);
        return tmp;
    }

    public boolean removeContact(String name) throws InvalidNameException, MaximumSizeExceddedException {
        if (getContactForName(name) == null)
            return false;
        PhoneBook temp = new PhoneBook();
        for (int i = 0; i < contacts.length; ++i)
            if (!(contacts[i].getName().equals(name)))
                temp.addContact(contacts[i]);
        contacts = temp.contacts;
        return true;
    }

    public static boolean saveAsTextFile(PhoneBook phonebook, String path) {
        File file = new File(path);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(phonebook);
            out.close();
        } catch (FileNotFoundException e) {
            try {
                if (file.createNewFile()) {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(phonebook);
                    out.close();
                }
            } catch (IOException e1) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static PhoneBook loadFromTextFile(String path) throws IOException, InvalidFormatException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        PhoneBook pb = null;
        try {
            pb = (PhoneBook) in.readObject();
            in.close();
        } catch (ClassNotFoundException e) {
            throw new InvalidFormatException();
        }
        return pb;
    }

    public Contact[] getContactsForNumber(String number_prefix) {
        int counter = 0;
        for (int i = 0; i < getContacts().length; ++i) {
            for (int j = 0; j < getContacts()[i].getNumbers().length; ++j) {
                String prefix = getContacts()[i].getNumbers()[j].substring(0, 3);
                if (prefix.equals(number_prefix)) {
                    ++counter;
                    break;
                }
            }
        }
        Contact[] contacts = new Contact[counter];
        for (int i = 0, k = 0; i < getContacts().length; ++i) {
            for (int j = 0; j < getContacts()[i].getNumbers().length; ++j) {
                String prefix = getContacts()[i].getNumbers()[j].substring(0, 3);
                if (prefix.equals(number_prefix)) {
                    contacts[k++] = this.contacts[i];
                    break;
                }
            }
        }
        return contacts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append(contact.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
