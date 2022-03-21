package expression.generic.math;

import expression.exceptions.exception.DivisionByZeroException;

public class ShortMath implements MathMode<Short> {
    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short multiply(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short divide(Short a, Short b) {
        if (b != 0) {
            return (short) (a / b);
        } else {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public Short negate(Short a) {
        return (short) (-a);
    }

    @Override
    public Short abs(Short a) {
        return (short) Math.abs(a);
    }

    @Override
    public Short valueOf(Number a) {
        return (short) Integer.parseInt(a.toString());
    }

    @Override
    public Short valueOf(String a) {
        return (short) Integer.parseInt(a);
    }
}
