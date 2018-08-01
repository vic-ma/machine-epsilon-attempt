package machineepsilon;

import java.util.*;

public class Polynomial
{
    private ArrayList<Term> terms = new ArrayList<>();

    public Polynomial()
    {
    }

    public Polynomial(String polynomial)
    {
        polynomial = polynomial.replace(" ", "");
        int startIndex = 0;
        boolean skipNext = false;

        if (polynomial.charAt(0) != '-' && polynomial.charAt(0) != '+')
            polynomial = "+" + polynomial;

        for (int i = 1; i < polynomial.length(); i++)
        {
            if (skipNext)
            {
                skipNext = false;
                continue;
            }

            if (polynomial.charAt(i) == '+')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i;
            }
            else if (polynomial.charAt(i) == '-')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i;
            }
            else if (polynomial.charAt(i) == '(')
            {
                skipNext = true;
            }
        }
        terms.add(new Term(polynomial.substring(startIndex, polynomial.length())));
        this.sort();
    }

    public int size()
    {
        return this.terms.size();
    }

    public Term get(int i)
    {
        return this.terms.get(i);
    }

    public void addTerm(Term term)
    {
        terms.add(term);
        this.sort();
    }

    public void sort()
    {
        terms.sort(null);
    }

    public String toString()
    {
        String polynomial = terms.get(0).toString();
        String term;

        if (polynomial.equals(""))
            return "";

        if (polynomial.charAt(0) == '+')
            polynomial = polynomial.substring(1);

        for (int i=1; i < terms.size(); i++)
        {
            term = terms.get(i).toString();
            if (term.charAt(0) == '+')
                polynomial += term;
            else
                polynomial += term;
        }

        return polynomial;
    }

    public void simplify()
    {
        for (int i = 0; i < terms.size()-1; i++)
        {
            Term current = terms.get(i);
            Term next = terms.get(i+1);
            if (current.getExponent().compareTo(next.getExponent()) == 0)
            {
                Term sum = Term.add(current, next);
                this.addTerm(sum);
                terms.remove(current);
                terms.remove(next);
                this.sort();
                i--;
            }
        }
    }

    public static Polynomial add(Polynomial p1, Polynomial p2)
    {
        Polynomial sum = p1;
        for (int i = 0; i < p2.size(); i++)
            sum.addTerm(p2.get(i));
        sum.sort();
        return sum;
    } 


    /*public static Polynomial subtract(Polynomial p1, Polynomial p2)
    {
    } 

    public static Polynomial multiply(Polynomial p1, Polynomial p2)
    {
    } 

    public static Polynomial divide(Polynomial p1, Polynomial p2)
    {
    }*/

    public static void main(String args[])
    {
        Polynomial p1 = new Polynomial("");
        p1.simplify();
        System.out.println(p1);
    }
}
