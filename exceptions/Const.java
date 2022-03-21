package expression.exceptions;

import expression.generic.math.MathMode;

public class Const<T> implements PartOfExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const && obj.hashCode() == hashCode()) {
            return ((Const<T>) obj).value.equals(value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode() * 31;
    }

    @Override
    public T evaluate(T x, T y, T z, MathMode<T> mode) {
        return value;
    }
}
