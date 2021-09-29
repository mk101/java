public class Div implements Expression {
    private final Expression e1;
    private final Expression e2;

    public Div(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        if (e2 instanceof Sum) {
            return e1.toString() + " / (" + e2.toString() + ")";
        } else {
            return e1.toString() + " / " + e2.toString();
        }
    }

    @Override
    public double eval() {
        double v2 = e2.eval();
        if (v2 == 0) {
            throw new RuntimeException("Div by 0");
        }

        return e1.eval() / v2;
    }
}
