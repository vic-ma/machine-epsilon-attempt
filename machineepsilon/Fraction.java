package machineepsilon;

public class Fraction
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator()
    {
        return this.numerator;
    }

    public int getDenominator()
    {
        return this.denominator;
    }

    public void simplify()
    {
        int gcf = greatestCommonFactor(this.numerator, this.denominator);
        this.numerator /= gcf;
        this.denominator /= gcf;
    }

    public static int greatestCommonFactor(int a, int b)
    {
        return b==0 ? a : greatestCommonFactor(b, a%b);
    }

    public static Fraction sum(Fraction f1, Fraction f2)
    {
        int numerator = f1.getNumerator() * f2.getDenominator()
                    + f2.getNumerator() * f1.getDenominator();
        int denominator = f1.getDenominator() * f2.getDenominator();
        Fraction sum = new Fraction(numerator, denominator);
        sum.simplify();
        return sum;
    }
}
