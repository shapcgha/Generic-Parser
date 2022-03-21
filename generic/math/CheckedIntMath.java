package expression.generic.math;

import expression.exceptions.exception.DivisionByZeroException;
import expression.exceptions.exception.OverFlowException;

public class CheckedIntMath implements MathMode<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return Math.addExact(a, b);
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return Math.subtractExact(a, b);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return Math.multiplyExact(a, b);
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b != 0) {
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new OverFlowException();
            } else {
                return a / b;
            }
        } else {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public Integer negate(Integer a) {
        return Math.negateExact(a);
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
