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

    public void simplify()
    {
        this.coefficient.simplify();
        this.exponent.simplify();
    }

    public static Term sum(Term t1, Term t2)
    {
        Fraction coefficient = Fraction.add(t1.getCoefficient(), t2.getCoefficient());
        return new Term(coefficient, t1.getExponent());
    }

    public Fraction getCoefficient()
    {
        return this.coefficient;
    }

    public Fraction getExponent()
    {
        return this.exponent;
    }
}
