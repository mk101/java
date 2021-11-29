package kolesov.task5.logic.expressions;

import kolesov.task5.logic.UndefinedParameterException;

public class ParameterExpression implements Expression {
    private final String name;
    private Double value;

    public ParameterExpression(String name) {
        this.name = name;
        value = null;
    }

    @Override
    public double Execute() throws UndefinedParameterException {
        return Execute(false);
    }

    @Override
    public double Execute(boolean print)throws UndefinedParameterException {
        if (value == null) {
            throw new UndefinedParameterException(name);
        }

        return value;
    }

    @Override
    public void SetParameter(String name, Integer value) {
        if (this.name.equals(name)) {
            this.value = Double.valueOf(value);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
