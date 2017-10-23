package mk.ukim.finki.lab2;

class Student {
    private Contact[] contacts;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        contacts = new Contact[0];
    }

    public void addEmailContact(String date, String email) {
        Contact[] temp = new Contact[contacts.length + 1];
        for (int i = 0; i < contacts.length; ++i) {
            temp[i] = contacts[i];
        }
        temp[contacts.length] = new EmailContact(date, email);
        contacts = temp;
    }

    public void addPhoneContact(String date, String phone) {
        Contact[] temp = new Contact[contacts.length + 1];
        for (int i = 0; i < contacts.length; ++i) {
            temp[i] = contacts[i];
        }
        temp[contacts.length] = new PhoneContact(date, phone);
        contacts = temp;
    }

    public Contact[] getEmailContacts() {
        int length = 0;
        for (int i = 0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Email"))
                ++length;
        }
        Contact[] temp = new Contact[length];
        for (int i = 0, j = 0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Email"))
                temp[j++] = contacts[i];
        }
        return temp;
    }

    public Contact[] getPhoneContacts() {
        int length = 0;
        for (int i = 0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Phone"))
                ++length;
        }
        Contact[] temp = new Contact[length];
        for (int i = 0, j = 0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Phone"))
                temp[j++] = contacts[i];
        }
        return temp;
    }

    public Contact getLatestContact() {
        Contact latest = contacts[0];
        for (int i = 1; i < contacts.length; ++i) {
            if (contacts[i].isNewerThan(latest))
                latest = contacts[i];
        }
        return latest;
    }

    public String getCity() {
        return city;
    }

    public long getIndex() {
        return index;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"ime\":");
        sb.append("\"" + firstName + "\", ");
        sb.append("\"prezime\":");
        sb.append("\"" + lastName + "\", ");
        sb.append("\"vozrast\":");
        sb.append(age + ", ");
        sb.append("\"grad\":");
        sb.append("\"" + city + "\", ");
        sb.append("\"indeks\":");
        sb.append(index + ", ");
        sb.append("\"telefonskiKontakti\":");
        sb.append("[");
        for (int i = 0; i < getPhoneContacts().length; ++i) {
            PhoneContact pc = (PhoneContact) getPhoneContacts()[i];
            sb.append("\"" + pc.getPhone() + "\"");
            if (i < getPhoneContacts().length - 1)
                sb.append(", ");
        }
        sb.append("], ");
        sb.append("\"emailKontakti\":");
        sb.append("[");
        for (int i = 0; i < getEmailContacts().length; ++i) {
            EmailContact ec = (EmailContact) getEmailContacts()[i];
            sb.append("\"" + ec.getEmail() + "\"");
            if (i < getEmailContacts().length - 1)
                sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
