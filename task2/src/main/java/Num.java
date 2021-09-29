public class Num implements Expression {
    private final int num;

    public Num(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return Integer.toString(num);
    }

    @Override
    public double eval() {
        return num;
    }
}
