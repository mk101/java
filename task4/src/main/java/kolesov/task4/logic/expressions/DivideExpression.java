package kolesov.task4.logic.expressions;

import kolesov.task4.MyApplication;
import kolesov.task4.logic.ZeroDivException;
import kolesov.task4.logic.UndefinedParameterException;

public class DivideExpression implements Expression {
    private final Expression e1;
    private final Expression e2;

    public DivideExpression(Expression e1, Expression e2) {
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
        if (e2Value == 0) {
            throw new ZeroDivException();
        }

        if (print) {
            //System.out.println(this.toString() + " = " + e1Value / e2Value);
            MyApplication.getOutputStream().println(this.toString() + " = " + e1Value / e2Value);
        }

        return e1Value / e2Value;
    }

    @Override
    public void SetParameter(String name, Integer value) {
        e1.SetParameter(name, value);
        e2.SetParameter(name, value);
    }

    @Override
    public String toString() {
        String e1Str, e2Str;
        if (e1 instanceof SumExpression || e1 instanceof MultiplyExpression) {
            e1Str = "(" + e1 + ")";
        } else {
            e1Str = e1.toString();
        }

        if (e2 instanceof SumExpression || e2 instanceof MultiplyExpression) {
            e2Str = "(" + e2 + ")";
        } else {
            e2Str = e2.toString();
        }

        return e1Str + " / " + e2Str;
    }
}
