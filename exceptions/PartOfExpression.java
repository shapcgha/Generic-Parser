package expression.exceptions;

import expression.generic.math.MathMode;

public interface PartOfExpression<T> {
    T evaluate(T x, T y, T z, MathMode<T> mode);
}
