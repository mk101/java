package kolesov.task5.logic.expressions;

import kolesov.task5.logic.UndefinedParameterException;
import kolesov.task5.logic.ZeroDivException;

public interface Expression {
    double Execute() throws ZeroDivException, UndefinedParameterException;
    double Execute(boolean print) throws ZeroDivException, UndefinedParameterException;
    void SetParameter(String name, Integer value);

}
