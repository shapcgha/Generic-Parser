package expression.exceptions.exception;

public class OperationWithoutArgumentsException extends ParsingException {
    public OperationWithoutArgumentsException() {
        super("Operation without appropriate number of arguments");
    }
}
