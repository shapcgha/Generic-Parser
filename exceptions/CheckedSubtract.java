package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedSubtract<T> extends BinaryOperation<T> {

    public CheckedSubtract(PartOfExpression a, PartOfExpression b) {
        super(a, b);
    }

    @Override
    public T operation(T a, T b, MathMode<T> mode) {
        return mode.subtract(a, b);
    }
}
