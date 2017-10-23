package mk.ukim.finki.lab2;

class InsufficientElementsException extends Exception {
    private String errorMsg;

    public InsufficientElementsException() {
        super("Insufficient number of elements");
    }

    public InsufficientElementsException(String message) {
        super(message);
        this.errorMsg = message;
    }
}
