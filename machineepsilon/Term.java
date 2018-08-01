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
        boolean positiveCoefficient = (term.charAt(0) == '+');
        String coefficient = "";
        String exponent = "1";

        if (term.charAt(1) == '(') // If coefficient is a Fraction
        {
            coefficient = term.substring(2, term.indexOf(")"));
            if (term.indexOf('x') == -1) // If Term is a Fraction constant
            {
                if (!positiveCoefficient)
                    coefficient = "-" + coefficient;
                this.coefficient = new Fraction(coefficient);
                this.exponent = new Fraction(0);
                return;
            }
        }
        else if (term.indexOf('x') != -1) // If coefficient is an integer
        {
            coefficient = term.substring(1, term.indexOf("x"));
            if (coefficient.equals(""))
                coefficient = "1";
        }
        else // If Term is an integer constant
        {
            this.coefficient = new Fraction(term);
            this.exponent = new Fraction(0);
            return;
        }

        if (term.indexOf('^') != -1) // If exponent is not 1 or 0
        {
            if (term.charAt(term.indexOf('^')+1) == '(') // If exponent is a Fraction
                exponent = term.substring(term.lastIndexOf('(')+1, term.length()-1);
            else
                exponent = term.substring(term.indexOf('^')+1);
        }

        if (!positiveCoefficient)
            coefficient = "-" + coefficient;

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
        String exponent = this.exponent.toString();
        String coefficient = this.coefficient.toString();
        boolean positiveCoefficient = (coefficient.charAt(0) != '-');

        if (coefficient.equals("0")) // If Term is 0
            return "";

        if (exponent.equals("0")) // If Term is a constant
        {
            if (positiveCoefficient)
                return "+" + coefficient;
            return coefficient;
        }

        else if (exponent.equals("1")) // If Term is linear
        {
            if (coefficient.equals("1"))
                return "+x";
            else if (coefficient.equals("-1"))
                return "-x";
            else if (coefficient.indexOf('/') != -1) // Coefficient is a Fraction
            {
                if (positiveCoefficient)
                    return "+(" + coefficient + ")x";
                return "-(" + coefficient.substring(1) + ")x";
            }
            else // Coefficient is an integer
            {
                if (positiveCoefficient)
                    return "+" + coefficient + "x";
                return "-" + coefficient.substring(1) + "x";
            }
        }

        // Term is neither constant nor linear

        if (coefficient.indexOf('/') != -1) // If coefficient is a Fraction
        {
            if (positiveCoefficient)
                coefficient = "+(" + coefficient + ")";
            else
                coefficient = "-(" + coefficient.substring(1) + ")";
        }
        else // If coefficient is an Integer
        {
            if (positiveCoefficient)
                coefficient = "+" + coefficient;
            else
                coefficient = "-" + coefficient.substring(1);
        }
        if (exponent.indexOf('/') != -1 || exponent.indexOf('-') != -1) // if exponent needs "()"
            exponent = "(" + exponent + ")";

        if (coefficient.equals("+1"))
            return "+x^" + exponent;
        else if (coefficient.equals("-1"))
            return "-x^" + exponent;

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
        Term t1 = new Term("-(1/2)");
        System.out.println(t1);
        /*
        Term t2 = new Term("-x");
        System.out.println(t2);
        Term t3 = new Term("+43x");
        System.out.println(t3);
        Term t4 = new Term("-32x");
        System.out.println(t4);
        Term t5 = new Term("+(23/3)x");
        System.out.println(t5);
        Term t6 = new Term("-(43/2)x");
        System.out.println(t6);
        */
    }
}
