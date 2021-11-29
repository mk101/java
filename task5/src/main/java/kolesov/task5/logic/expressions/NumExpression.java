package kolesov.task5.logic.expressions;

import kolesov.task5.logic.UndefinedParameterException;
import kolesov.task5.logic.ZeroDivException;

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
