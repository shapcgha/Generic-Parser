package expression.generic.math;

import expression.exceptions.exception.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerMath implements MathMode<BigInteger> {

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        if (!b.equals(new BigInteger("0"))) {
            return a.divide(b);
        } else {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger abs(BigInteger a) {
        return a.abs();
    }

    @Override
    public BigInteger valueOf(Number a) {
        return new BigInteger(a.toString());
    }

    @Override
    public BigInteger valueOf(String a) {
        return new BigInteger(a);
    }
}
