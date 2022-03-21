package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedMultiply<T> extends BinaryOperation<T> {

    public CheckedMultiply(PartOfExpression<T> a, PartOfExpression<T> b) {
        super(a, b);
    }

    @Override
    public T operation(T a, T b, MathMode<T> mode) {
        return mode.multiply(a, b);
    }
}
