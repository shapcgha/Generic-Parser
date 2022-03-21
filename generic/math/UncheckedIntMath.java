package expression.generic.math;

import expression.exceptions.exception.DivisionByZeroException;

public class UncheckedIntMath implements MathMode<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer abs(Integer a) {
        return Math.abs(a);
    }

    @Override
    public Integer valueOf(Number a) {
        return Integer.valueOf(a.toString());
    }

    @Override
    public Integer valueOf(String a) {
        return Integer.valueOf(a);
    }
}
