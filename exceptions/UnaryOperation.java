package expression.exceptions;

import expression.generic.math.MathMode;

public abstract class UnaryOperation<T> implements PartOfExpression<T> {
    PartOfExpression<T> a;

    public UnaryOperation(PartOfExpression<T> a) {
        this.a = a;
    }

    @Override
    public T evaluate(T x, T y, T z, MathMode<T> mode) {
        return operation(a.evaluate(x, y, z, mode), mode);
    }

    public abstract T operation(T a, MathMode<T> mode);
}
