package expression.exceptions;

import expression.generic.math.MathMode;

public class Variable<T> implements PartOfExpression<T> {
    private final String sign;

    public Variable(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable && obj.hashCode() == hashCode()) {
            return ((Variable<T>) obj).sign.equals(sign);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return sign.hashCode() * 31;
    }

    @Override
    public T evaluate(T x, T y, T z, MathMode<T> mode) {
        if (sign.equals("x")) {
            return x;
        } else if (sign.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
