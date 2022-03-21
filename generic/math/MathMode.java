package expression.generic.math;

public interface MathMode<T> {
    T add(T a, T b);

    T subtract(T a, T b);

    T multiply(T a, T b);

    T divide(T a, T b);

    T negate(T a);

    T abs(T a);

    T valueOf(Number a);

    T valueOf(String a);
}
