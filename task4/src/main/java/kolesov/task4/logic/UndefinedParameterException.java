package kolesov.task4.logic;

public class UndefinedParameterException extends Exception{
    public UndefinedParameterException(String parameterName) {
        super("Неопределенный параметр `%s`".formatted(parameterName));
    }
}
