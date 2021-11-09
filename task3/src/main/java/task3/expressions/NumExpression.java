package task3.expressions;

import task3.Expression;
import task3.UndefinedParameterException;
import task3.ZeroDivException;

public class NumExpression implements Expression {
    private final double val;

    public NumExpression(double val) {
        this.val = val;
    }

    @Override
    public double Execute() {
        return val;
    }

    @Override
    public double Execute(boolean print) throws ZeroDivException, UndefinedParameterException {
        return val;
    }

    @Override
    public void SetParameter(String name, Integer value) {}

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
