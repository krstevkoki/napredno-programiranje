package mk.ukim.finki.lab2;

class InvalidRowNumberException extends Exception {
    private String errorMsg;

    public InvalidRowNumberException() {
        super("Insufficient number of elements");
    }

    public InvalidRowNumberException(String message) {
        super(message);
        this.errorMsg = message;
    }
}
