package task3;

public class ZeroDivException extends Exception {
    public ZeroDivException() {
        super("Попытка деления на 0");
    }
}
