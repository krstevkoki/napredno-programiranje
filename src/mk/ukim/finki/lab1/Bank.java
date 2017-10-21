package mk.ukim.finki.lab1;

import java.util.Arrays;

class Bank implements Parser {
    private String name;
    private Account[] accounts;
    private double totalTransferSum;
    private double totalProvisionSum;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.totalTransferSum = 0;
        this.totalProvisionSum = 0;
        this.accounts = new Account[accounts.length];
        for (int i = 0; i < accounts.length; ++i)
            this.accounts[i] = accounts[i];
    }

    private int findId(long id) {
        int index = -1;
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i].getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean makeTransaction(Transaction t) {
        int indexFromId = findId(t.getFromId());
        int indexToId = findId(t.getToId());

        // if ID's are not in the list of accounts
        if (indexFromId == -1 || indexToId == -1)
            return false;

        double balanceFromId = Parser.parseStringToDouble(accounts[indexFromId].getBalance());
        double balanceToId = Parser.parseStringToDouble(accounts[indexToId].getBalance());
        double transactionAmount = Parser.parseStringToDouble(t.getAmount());

        // if there is not enough money on the account balance
        if (balanceFromId < transactionAmount)
            return false;

        double provision = t.getProvision();
        totalTransferSum += transactionAmount;
        totalProvisionSum += provision;
        balanceFromId -= (transactionAmount + provision);
        if (indexFromId == indexToId)  // if someone makes a transaction to him/her self charge only the provision
            balanceToId -= provision;
        else
            balanceToId += transactionAmount;  // else add the transactionAmount
        accounts[indexFromId].setBalance(String.format("%.2f", balanceFromId) + "$");
        accounts[indexToId].setBalance(String.format("%.2f", balanceToId) + "$");
        return true;
    }

    public String totalTransfers() {
        return String.format("%.2f", totalTransferSum) + "$";
    }

    public String totalProvision() {
        return String.format("%.2f", totalProvisionSum) + "$";
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\n\n");
        for (Account account : accounts)
            sb.append(account);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + name.hashCode();
        hash = hash * prime + (int) totalTransferSum;
        hash = hash * prime + (int) totalProvisionSum;
        hash = hash * prime + Arrays.hashCode(accounts);
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
        Bank bank = (Bank) obj;
        return this.name.equals(bank.name) && Arrays.equals(this.accounts, bank.accounts)
                && this.totalTransferSum == bank.totalTransferSum && this.totalProvisionSum == bank.totalProvisionSum
                && this.hashCode() == bank.hashCode();
    }
}
