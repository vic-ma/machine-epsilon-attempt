package machineepsilon;

public class Term
{
    private Fraction coefficient;
    private Fraction exponent;

    public Term(Fraction coefficient, Fraction exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(int coefficient, int exponent)
    {
        this.coefficient = new Fraction(coefficient, 1);
        this.exponent = new Fraction(exponent, 1);
    }

    public Fraction getCoefficient()
    {
        return this.coefficient;
    }

    public Fraction getExponent()
    {
        return this.exponent;
    }

    public String toString()
    {
        return "(" + this.coefficient + ")x^(" + this.exponent + ")";
    }

    public void simplify()
    {
        this.coefficient.simplify();
        this.exponent.simplify();
    }

    public static Term add(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.add(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term subtract(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.subtract(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public static Term multiply(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.multiply(t1.getCoefficient(), t2.getCoefficient());
        Fraction exponent = Fraction.add(t1.getExponent(), t2.getExponent());
        return new Term(coefficient, exponent);
    }

    public static Term divide(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.divide(t1.getCoefficient(), t2.getCoefficient());
        Fraction exponent = Fraction.subtract(t1.getExponent(), t2.getExponent());
        return new Term(coefficient, exponent);
    }
}
