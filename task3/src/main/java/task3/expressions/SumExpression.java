package task3.expressions;

import task3.Expression;
import task3.ZeroDivException;
import task3.UndefinedParameterException;

public class SumExpression implements Expression {
    private final Expression e1;
    private final Expression e2;

    public SumExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public double Execute() throws ZeroDivException, UndefinedParameterException {
        return Execute(false);
    }

    @Override
    public double Execute(boolean print) throws ZeroDivException, UndefinedParameterException {
        double e1Value = e1.Execute(print);
        double e2Value = e2.Execute(print);

        if (print) {
            System.out.println(this.toString() + " = " + (e1Value + e2Value));
        }
        return e1Value + e2Value;
    }

    @Override
    public void SetParameter(String name, Integer value) {
        e1.SetParameter(name, value);
        e2.SetParameter(name, value);
    }

    @Override
    public String toString() {
        return e1.toString() + " + " + e2.toString();
    }
}
