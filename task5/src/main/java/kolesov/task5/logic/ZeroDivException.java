package kolesov.task5.logic;

public class ZeroDivException extends Exception {
    public ZeroDivException() {
        super("Попытка деления на 0");
    }
}
