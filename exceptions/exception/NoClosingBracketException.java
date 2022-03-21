package expression.exceptions.exception;

public class NoClosingBracketException extends ParsingException {
    public NoClosingBracketException() {
        super("No closing bracket");
    }
}
