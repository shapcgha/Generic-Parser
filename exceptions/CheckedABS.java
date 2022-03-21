package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedABS<T> extends UnaryOperation<T> {
    public CheckedABS(PartOfExpression<T> a) {
        super(a);
    }

    @Override
    public T operation(T a, MathMode<T> mode) {
        return mode.abs(a);
    }
}
