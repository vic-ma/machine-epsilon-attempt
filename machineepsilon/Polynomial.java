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
    }

    public void add(Term term)
    {
        terms.add(term);
    }

    public void sort()
    {
        terms.sort(null);
    }

    public String toString()
    {
        String polynomial = terms.get(0).toString();
        String term;

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

    public static void main(String args[])
    {
        Polynomial p = new Polynomial("");
        System.out.println(p);
    }
}
