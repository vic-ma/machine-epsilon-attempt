package machineepsilon;

public class Term implements Comparable<Term>
{
    private Fraction coefficient;
    private Fraction exponent;

    public Term(Fraction coefficient, Fraction exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Term(Fraction coefficient, int exponent)
    {
        this.coefficient = coefficient;
        this.exponent = new Fraction(exponent);
    }

    public Term(int coefficient, Fraction exponent)
    {
        this.coefficient = new Fraction(coefficient);
        this.exponent = exponent;
    }

    public Term(int coefficient, int exponent)
    {
        this.coefficient = new Fraction(coefficient);
        this.exponent = new Fraction(exponent);
    }

    public Term(String term)
    {
        String coefficient = "";
        String exponent = "1";

        if (term.charAt(0) == '(')
        {
            coefficient = term.substring(1, term.indexOf(")"));
        }
        else if (term.indexOf('x') != -1)
        {
            coefficient = term.substring(0, term.indexOf("x"));
        }
        else
        {
            coefficient = term;
            exponent = "0";
        }

        if (term.indexOf('^') != -1)
        {
            if (term.charAt(term.indexOf('^')+1) == '(')
                exponent = term.substring(term.lastIndexOf('(')+1, term.length()-1);
            else
                exponent = term.substring(term.indexOf('^')+1);
        }

        if (coefficient.equals(""))
            coefficient = "1";
        else if (coefficient.equals("-"))
            coefficient = "-1";

        this.coefficient = new Fraction(coefficient);
        this.exponent = new Fraction(exponent);
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
        String coefficient = this.coefficient.toString();
        String exponent = this.exponent.toString();

        if (coefficient.equals("0"))
            return "";
        else if (exponent.equals("0"))
            return coefficient;
        else if (exponent.equals("1"))
        {
            if (coefficient.equals("1"))
                return "x";
            else if (coefficient.equals("-1"))
                return "-x";
            else if (coefficient.indexOf('/') != -1)
                return "(" + coefficient + ")x";
            else
                return coefficient + "x";
        }

        if (coefficient.indexOf('/') != -1)
            coefficient = "(" + coefficient + ")";
        if (exponent.indexOf('/') != -1 || exponent.indexOf('-') != -1)
            exponent = "(" + exponent + ")";

        if (coefficient.equals("1"))
            return "x^" + exponent;

        return coefficient + "x^" + exponent;
    }

    public void simplify()
    {
        this.coefficient.simplify();
        this.exponent.simplify();
    }

    public int compareTo(Term term)
    {
        int exponentCompare = this.exponent.compareTo(term.getExponent());
        if (exponentCompare < 0)
            return -1;
        else if (exponentCompare > 0)
            return 1;

        int coefficientCompare = this.coefficient.compareTo(term.getCoefficient());
        if (coefficientCompare < 0)
            return -1;
        else if (coefficientCompare > 0)
            return 1;

        return 0;
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

    public static void main(String args[])
    {
    }
}
