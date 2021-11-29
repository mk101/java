package kolesov.task4.logic;

public class ExpressionParseException extends Exception {
    public ExpressionParseException() {
        super("Выражение содержит ошибку");
    }
}
