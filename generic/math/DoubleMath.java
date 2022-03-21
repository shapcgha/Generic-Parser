package expression.generic.math;

public class DoubleMath implements MathMode<Double> {

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double abs(Double a) {
        return Math.abs(a);
    }

    @Override
    public Double valueOf(Number a) {
        return Double.parseDouble(a.toString());
    }

    @Override
    public Double valueOf(String a) {
        return Double.parseDouble(a);
    }
}
