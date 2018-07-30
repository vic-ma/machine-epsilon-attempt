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
        int coefficientStartIndex = -1;
        int coefficientEndIndex = -1;
        int exponentStartIndex = -1;
        int exponentEndIndex = -1;

        if (term.equals("x"))
        {
            this.coefficient = new Fraction(0);
            this.exponent = new Fraction(0);
            return;
        }
        if (term.indexOf('^') == -1)
        {
            this.exponent = new Fraction(1);
            exponentStartIndex = -2;
            exponentEndIndex = -2;
        }
        if (term.indexOf('x') == -1)
        {
            this.coefficient = new Fraction(term);
            this.exponent = new Fraction(0);
            return;
        }

        for (int i = 0; i < term.length(); i++)
        {
            if (coefficientStartIndex == -1)
            {
                if (term.charAt(i) == '-')
                    coefficientStartIndex = i;
                else if (Character.isDigit(term.charAt(i)))
                    coefficientStartIndex = i;
            }
            else if (coefficientEndIndex == -1)
            {
                if (term.charAt(i) == ')' || term.charAt(i) == 'x')
                    coefficientEndIndex = i;
            } 
            else if (exponentStartIndex == -1)
            {
                if (term.charAt(i) == '-')
                    exponentStartIndex = i;
                else if (Character.isDigit(term.charAt(i)))
                    exponentStartIndex = i;
            }
            else if (exponentEndIndex == -1)
            {
                if (term.charAt(i) == ')')
                    exponentEndIndex = i;
            }
        }

        if (exponentEndIndex == -1)
            exponentEndIndex = term.length();

        if (coefficientStartIndex != -2)
            this.coefficient = new Fraction(term.substring(coefficientStartIndex,
                        coefficientEndIndex));
        if (exponentStartIndex != -2)
            this.exponent = new Fraction(term.substring(exponentStartIndex, exponentEndIndex));
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
