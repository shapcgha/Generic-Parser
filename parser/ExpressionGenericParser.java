package expression.parser;

import expression.exceptions.*;
import expression.exceptions.exception.*;
import expression.generic.math.MathMode;

public class ExpressionGenericParser<T> implements GenericParser<T> {
    private String data;
    private int pos;
    MathMode<T> mode;
    private PartOfExpression<T> lastPartOfPartOfExpression;
    private char lastOperation;
    private char lastBinaryOperation;
    private int balance;


    @Override
    public PartOfExpression<T> parse(String expression, MathMode<T> mode) throws ParsingException {
        pos = 0;
        this.mode = mode;
        data = expression;
        balance = 0;
        reset();
        lastPartOfPartOfExpression = parsePartOfExpression();
        return lastPartOfPartOfExpression;
    }

    private void reset() {
        lastPartOfPartOfExpression = null;
        lastOperation = 's';
        lastBinaryOperation = 's';
    }

    private PartOfExpression<T> parsePartOfExpression() throws ParsingException {
        skipWhiteSpaces();
        if (hasNext()) {
            char ch = next();
            if (ch == '(') {
                parseExpressionInBrackets();
            } else if (Character.isDigit(ch)) {
                final StringBuilder sb = new StringBuilder(Character.toString(ch));
                parseConst(sb);
            } else if (Character.isAlphabetic(ch)) {
                final StringBuilder sb = new StringBuilder(Character.toString(ch));
                parseVariable(sb);
            } else if (ch == '-' && lastOperation != ' ') {
                parseUnaryMines();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (lastOperation == '+' || lastOperation == '-' || lastOperation == '*' || lastOperation == '/' || lastOperation == 's') {
                    throw new OperationWithoutArgumentsException();
                }
                parseBinaryOperation(ch);
            } else if (ch == ')') {
                if (lastPartOfPartOfExpression == null) {
                    throw new EmptyBracketsException();
                } else {
                    throw new OperationWithoutArgumentsException();
                }
            } else {
                throw new IncorrectSymbolException();
            }
            if (isNeededUp(lastOperation)) {
                return lastPartOfPartOfExpression;
            }
            if (isNeededUp(lastBinaryOperation)) {
                return lastPartOfPartOfExpression;
            }
            if (hasNext()) {
                lastPartOfPartOfExpression = parsePartOfExpression();
            } else {
                if (lastOperation != ' ') {
                    throw new OperationWithoutArgumentsException();
                }
            }
            return lastPartOfPartOfExpression;
        } else {
            if (lastOperation == ' ') {
                return lastPartOfPartOfExpression;
            } else {
                throw new OperationWithoutArgumentsException();
            }
        }

    }

    private void parseExpressionInBrackets() throws ParsingException {
        skipWhiteSpaces();
        if (hasNext()) {
            if (lastOperation == ' ') {
                throw new ArgumentWithoutOperationException();
            }
            balance++;
            char tempLastOperation = lastOperation;
            char tempLastBinaryOperation = lastBinaryOperation;
            reset();
            parsePartOfExpression();
            if (!hasNext() && data.charAt(pos - 1) != ')') {
                throw new NoClosingBracketException();
            }
            next();
            lastOperation = tempLastOperation;
            lastBinaryOperation = tempLastBinaryOperation;
            balance--;
            skipWhiteSpaces();
        } else {
            throw new NoClosingBracketException();
        }
    }

    private void parseBinaryOperation(char ch) throws ParsingException {
        skipWhiteSpaces();
        if (hasNext()) {
            char temp = lastBinaryOperation;
            lastOperation = ch;
            lastBinaryOperation = ch;
            if (ch == '-') {
                lastPartOfPartOfExpression = new CheckedSubtract<>(lastPartOfPartOfExpression, parsePartOfExpression());
            } else if (ch == '+') {
                lastPartOfPartOfExpression = new CheckedAdd<>(lastPartOfPartOfExpression, parsePartOfExpression());
            } else if (ch == '*') {
                lastPartOfPartOfExpression = new CheckedMultiply<>(lastPartOfPartOfExpression, parsePartOfExpression());
            } else if (ch == '/') {
                lastPartOfPartOfExpression = new CheckedDivide<>(lastPartOfPartOfExpression, parsePartOfExpression());
            }
            lastBinaryOperation = temp;
            skipWhiteSpaces();
        } else {
            throw new OperationWithoutArgumentsException();
        }
    }

    private void parseABS() throws ParsingException {
        skipWhiteSpaces();
        if (hasNext()) {
            char temp = lastOperation;
            lastOperation = 'A';
            lastPartOfPartOfExpression = new CheckedABS<>(parsePartOfExpression());
            lastOperation = temp;
            skipWhiteSpaces();
        } else {
            throw new OperationWithoutArgumentsException();
        }
    }

    private void parseUnaryMines() throws ParsingException {
        skipWhiteSpaces();
        if (hasNext()) {
            char temp = lastOperation;
            lastOperation = '~';
            char ch = next();
            if (Character.isDigit(ch)) {
                parseConst(new StringBuilder("-" + ch));
            } else {
                pos--;
                lastPartOfPartOfExpression = new CheckedNegate<>(parsePartOfExpression());
            }
            lastOperation = temp;
            skipWhiteSpaces();
        } else {
            throw new OperationWithoutArgumentsException();
        }
    }

    private void parseVariable(StringBuilder sb) throws ParsingException {
        while (hasNext() && Character.isAlphabetic(data.charAt(pos))) {
            sb.append(next());
        }
        switch (sb.toString()) {
            case "x", "y", "z" -> lastPartOfPartOfExpression = new Variable<>(sb.toString());
            case "abs" -> parseABS();
            default -> throw new IncorrectVariableException();
        }
        skipWhiteSpaces();
    }

    private void parseConst(StringBuilder sb) {
        while (hasNext() && Character.isDigit(data.charAt(pos))) {
            sb.append(next());
        }
        try {
            lastPartOfPartOfExpression = new Const<T>(mode.valueOf(sb.toString()));
        } catch (NumberFormatException e) {
            throw new OverFlowException();
        }
        skipWhiteSpaces();
    }

    private boolean isNeededUp(char flag) throws ParsingException {
        lastOperation = ' ';
        if (!hasNext()) {
            return true;
        } else {
            char ch = data.charAt(pos);
            if (getPriority(flag) >= getPriority(ch)) {
                if (ch == ')') {
                    if (balance - 1 < 0) {
                        throw new NegativeBalanceException();
                    }
                } else if (getPriority(ch) == 0) {
                    throw new ArgumentWithoutOperationException();
                } else if (getPriority(ch) == -1) {
                    throw new IncorrectSymbolException();
                }
                return true;
            }
            return false;
        }
    }

    private void skipWhiteSpaces() {
        while (hasNext() && Character.isWhitespace(data.charAt(pos))) {
            next();
        }
    }

    private int getPriority(char ch) {
        if (ch == '~' || ch == 'A') {
            return 6;
        } else if (ch == '*' || ch == '/') {
            return 5;
        } else if (ch == '+' || ch == '-') {
            return 4;
        } else if (ch == '&') {
            return 3;
        } else if (ch == '^') {
            return 2;
        } else if (ch == '|') {
            return 1;
        } else if (Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '(' || ch == ')') {
            return 0;
        }
        return -1;
    }

    public boolean hasNext() {
        return pos < data.length();
    }

    public char next() {
        return data.charAt(pos++);
    }
}
