package mk.ukim.finki.lab3;

class InvalidNameException extends Exception {
    public String name;

    public InvalidNameException() {
    }

    public InvalidNameException(String name) {
        super(name);
        this.name = name;
    }
}
