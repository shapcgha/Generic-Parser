package expression.exceptions.exception;

public class ArgumentWithoutOperationException extends ParsingException {
    public ArgumentWithoutOperationException() {
        super("Argument without operation");
    }
}
