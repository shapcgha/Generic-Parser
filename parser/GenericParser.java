package expression.parser;

import expression.exceptions.PartOfExpression;
import expression.exceptions.exception.ParsingException;
import expression.generic.math.MathMode;

public interface GenericParser<T> {
    PartOfExpression<T> parse(String expression, MathMode<T> mode) throws ParsingException;
}
