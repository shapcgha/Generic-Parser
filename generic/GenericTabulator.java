package expression.generic;

import expression.exceptions.PartOfExpression;
import expression.exceptions.exception.ParsingException;
import expression.generic.math.*;
import expression.parser.ExpressionGenericParser;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return switch (mode) {
            case "bi" -> tabulate(new BigIntegerMath(), expression, x1, x2, y1, y2, z1, z2);
            case "i" -> tabulate(new CheckedIntMath(), expression, x1, x2, y1, y2, z1, z2);
            case "l" -> tabulate(new LongMath(), expression, x1, x2, y1, y2, z1, z2);
            case "d" -> tabulate(new DoubleMath(), expression, x1, x2, y1, y2, z1, z2);
            case "u" -> tabulate(new UncheckedIntMath(), expression, x1, x2, y1, y2, z1, z2);
            case "s" -> tabulate(new ShortMath(), expression, x1, x2, y1, y2, z1, z2);
            default -> null;
        };
    }

    private <T> Object[][][] tabulate(MathMode<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        PartOfExpression<T> exp = new ExpressionGenericParser<T>().parse(expression, mode);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        result[i][j][k] = exp.evaluate(mode.valueOf(x1 + i),
                                mode.valueOf(y1 + j),
                                mode.valueOf(z1 + k), mode);
                    } catch (Exception E) {
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }

    static public void main(String[] args) throws Exception {
        GenericTabulator gen = new GenericTabulator();
        Object[][][] arr = gen.tabulate(args[0], args[1], Integer.parseInt(args[2]),
                Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]),
                Integer.parseInt(args[6]), Integer.parseInt(args[7]));
        for (int i = 0; i <= Integer.parseInt(args[3]) - Integer.parseInt(args[2]); i++) {
            for (int j = 0; j <= Integer.parseInt(args[5]) - Integer.parseInt(args[4]); j++) {
                for (int k = 0; k <= Integer.parseInt(args[7]) - Integer.parseInt(args[6]); k++) {
                    if (arr[i][j][k] == null) {
                        System.out.print("null ");
                    } else {
                        System.out.print(arr[i][j][k].toString() + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }
}
