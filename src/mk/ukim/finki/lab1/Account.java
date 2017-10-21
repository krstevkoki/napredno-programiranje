package mk.ukim.finki.lab1;

import java.util.Random;

class Account {
    private String name;
    private long id;
    private String balance;

    public Account(String name, String balance) {
        this.name = name;
        this.balance = balance;
        Random random = new Random();
        this.id = random.nextLong();
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + (int) id;
        hash = hash * prime + name.hashCode();
        hash = hash * prime + balance.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Account acc = (Account) obj;
        return this.id == acc.id && this.name.equals(acc.name)
                && this.balance.equals(acc.balance) && this.hashCode() == acc.hashCode();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nBalance: " + balance + "\n";
    }
}
