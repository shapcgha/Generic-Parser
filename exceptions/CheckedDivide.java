package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedDivide<T> extends BinaryOperation<T> {

    public CheckedDivide(PartOfExpression<T> a, PartOfExpression<T> b) {
        super(a, b);
    }

    @Override
    public T operation(T a, T b, MathMode<T> mode) {
        return mode.divide(a, b);
    }
}
