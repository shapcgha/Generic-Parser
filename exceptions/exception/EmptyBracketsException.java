package expression.exceptions.exception;

public class EmptyBracketsException extends ParsingException {
    public EmptyBracketsException() {
        super("Empty brackets");
    }
}
