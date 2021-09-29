public class Main {
    public static void main(String[] args) {
        Expression e = new Mult(new Num(42), new Sum(new Div(new Num(46), new Num(7)), new Num(84)));
        System.out.println(e);
        System.out.println(e.eval());
    }
}
