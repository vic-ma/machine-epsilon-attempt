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

    public void simplify()
    {
        gcf = greatestCommonFactor(this.numerator, this.denominator);
        this.numerator /= gcf;
        this.denominator /= gcf;
    }

    public static int greatestCommonFactor(int a, int b)
    {
        return b==0 ? a : greatestCommonFactor(b, a%b);
    }

    public static Fraction sum(Fraction f1, Fraction f2)
    {
    }

}
