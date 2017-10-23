package mk.ukim.finki.lab2;

class InvalidColumnNumberException extends Exception {
    private String errorMsg;

    public InvalidColumnNumberException() {
        super("Insufficient number of elements");
    }

    public InvalidColumnNumberException(String message) {
        super(message);
        this.errorMsg = message;
    }
}
