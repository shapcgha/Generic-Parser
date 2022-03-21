package expression.generic.math;

import expression.exceptions.exception.DivisionByZeroException;

public class LongMath implements MathMode<Long> {
    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long divide(Long a, Long b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public Long negate(Long a) {
        return -a;
    }

    @Override
    public Long abs(Long a) {
        return Math.abs(a);
    }

    @Override
    public Long valueOf(Number a) {
        return Long.valueOf(a.toString());
    }

    @Override
    public Long valueOf(String a) {
        return Long.valueOf(a);
    }
}
