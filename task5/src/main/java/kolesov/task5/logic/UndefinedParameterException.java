package kolesov.task5.logic;

public class UndefinedParameterException extends Exception{
    public UndefinedParameterException(String parameterName) {
        super("Неопределенный параметр `%s`".formatted(parameterName));
    }
}
