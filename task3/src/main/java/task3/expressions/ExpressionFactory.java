package task3.expressions;

import task3.Expression;

public class ExpressionFactory {
    public static Expression Create(int value) {
        return new NumExpression(value);
    }

    public static Expression Create(String name) {
        return new ParameterExpression(name);
    }

    public static Expression Create(ExpressionSign sign, Expression e1, Expression e2) {
        return switch (sign) {
            case PLUS       -> new SumExpression(e1, e2);
            case MULTIPLY   -> new MultiplyExpression(e1, e2);
            case DIVIDE     -> new DivideExpression(e1, e2);
        };
    }
}
