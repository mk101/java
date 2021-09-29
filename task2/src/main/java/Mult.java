public class Mult implements Expression {
    private final Expression e1;
    private final Expression e2;

    public Mult(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        if (e2 instanceof Sum) {
            return e1.toString() + " * (" + e2.toString() + ")";
        } else {
            return e1.toString() + " * " + e2.toString();
        }
    }

    @Override
    public double eval() {
        return e1.eval() * e2.eval();
    }
}
