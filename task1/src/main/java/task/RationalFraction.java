package task;

public class RationalFraction {
    private int numerator;
    private int denominator;

    public RationalFraction() {
        numerator = 1;
        denominator = 1;
    }

    public RationalFraction(int number) {
        numerator = number;
        denominator = 1;
    }

    public RationalFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;

        if (denominator == 0) {
            throw new RuntimeException("Denominator = 0");
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    void Sum(RationalFraction rf) {
        if (denominator == rf.denominator) {
            numerator += rf.numerator;
            return;
        }

        numerator = numerator*rf.denominator + rf.numerator;
        denominator *= rf.denominator;
    }

    void Mult(RationalFraction rf) {
        numerator *= rf.numerator;
        denominator *= rf.denominator;
    }

    void Div(RationalFraction rf) {
        numerator *= rf.denominator;
        denominator *= rf.numerator;
    }

    RationalFraction SumNew(RationalFraction rf) {
        var tmp = new RationalFraction(numerator, denominator);
        tmp.Sum(rf);
        return tmp;
    }

    RationalFraction MultNew(RationalFraction rf) {
        return new RationalFraction(numerator * rf.numerator, denominator * rf.denominator);
    }

    RationalFraction DivNew(RationalFraction rf) {
        return new RationalFraction(numerator * rf.denominator, denominator * rf.numerator);
    }

    @Override
    public String toString() {
        if (denominator != 1) {
            return numerator + "/" + denominator;
        } else {
            return Integer.toString(numerator);
        }
    }
}
