package kolesov.task5.logic.expressions;

import kolesov.task5.MyApplication;
import kolesov.task5.logic.UndefinedParameterException;
import kolesov.task5.logic.ZeroDivException;

public class SubExpression implements Expression {
    private final Expression e1;
    private final Expression e2;

    public SubExpression(Expression e1, Expression e2) {
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
            MyApplication.getOutputStream().println(this.toString() + " = " + (e1Value - e2Value));
        }
        return e1Value - e2Value;
    }

    @Override
    public void SetParameter(String name, Integer value) {
        e1.SetParameter(name, value);
        e2.SetParameter(name, value);
    }

    @Override
    public String toString() {
        return e1.toString() + " - " + e2.toString();
    }
}
