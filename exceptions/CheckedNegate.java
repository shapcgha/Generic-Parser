package expression.exceptions;

import expression.generic.math.MathMode;

public class CheckedNegate<T> extends UnaryOperation<T> {
    public CheckedNegate(PartOfExpression<T> a) {
        super(a);
    }

    @Override
    public T operation(T a, MathMode<T> mode) {
        return mode.negate(a);
    }
}
