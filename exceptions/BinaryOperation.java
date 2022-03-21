package expression.exceptions;

import expression.generic.math.MathMode;

public abstract class BinaryOperation<T> implements PartOfExpression<T> {
    PartOfExpression<T> a;
    PartOfExpression<T> b;

    public BinaryOperation(PartOfExpression<T> a, PartOfExpression<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public T evaluate(T x, T y, T z, MathMode<T> mode) {
        return operation(a.evaluate(x, y, z, mode), b.evaluate(x, y, z, mode), mode);
    }

    public abstract T operation(T a, T b, MathMode<T> mode);
}
