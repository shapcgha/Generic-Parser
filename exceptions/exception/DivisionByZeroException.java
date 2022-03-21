package expression.exceptions.exception;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
