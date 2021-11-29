package task3.expressions;

import task3.UndefinedParameterException;
import task3.ZeroDivException;

public interface Expression {
    double Execute() throws ZeroDivException, UndefinedParameterException;
    double Execute(boolean print) throws ZeroDivException, UndefinedParameterException;
    void SetParameter(String name, Integer value);

}
