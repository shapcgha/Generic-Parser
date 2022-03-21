package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedAdd<T> extends BinaryOperation<T> {

    public CheckedAdd(PartOfExpression<T> a, PartOfExpression<T> b) {
        super(a, b);
    }

    @Override
    public T operation(T a, T b, MathMode<T> mode) {
        return mode.add(a, b);
    }
}
