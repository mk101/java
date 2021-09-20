package task;

public class Main {
    public static void main(String[] args) {
        var rf1 = new RationalFraction(10);
        var rf2 = new RationalFraction(3, 6);

        System.out.println(rf1.SumNew(rf2));    // rf1 + rf2
        System.out.println(rf1.MultNew(rf2));   // rf1 * rf2
        System.out.println(rf1.DivNew(rf2));    // rf1 / rf2

        rf1.Div(new RationalFraction(1, 10)); // rf1 /= 1/10
        System.out.println(rf1);

        rf2.Mult(new RationalFraction(23, 42)); // rf2 *= 23/42
        System.out.println(rf2);

        rf1.Sum(rf2); // rf1 += rf2;
        System.out.println(rf1);
    }
}
