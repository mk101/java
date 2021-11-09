package task3;

public class UndefinedParameterException extends Exception{
    public UndefinedParameterException(String parameterName) {
        super("Неопределенный параметр `%s`".formatted(parameterName));
    }
}
