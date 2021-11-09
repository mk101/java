package task3;

public interface Expression {
    double Execute() throws ZeroDivException, UndefinedParameterException;
    double Execute(boolean print) throws ZeroDivException, UndefinedParameterException;
    void SetParameter(String name, Integer value);

}
