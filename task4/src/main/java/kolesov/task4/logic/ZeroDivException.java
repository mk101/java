package kolesov.task4.logic;

public class ZeroDivException extends Exception {
    public ZeroDivException() {
        super("Попытка деления на 0");
    }
}
