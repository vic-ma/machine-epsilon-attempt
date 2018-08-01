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

        for (int i = 1; i < polynomial.length(); i++)
        {
            if (polynomial.charAt(i) == '+')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i+1;
            }
            else if (polynomial.charAt(i) == '-')
            {
                terms.add(new Term(polynomial.substring(startIndex, i)));
                startIndex = i;
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

        for (int i=1; i < terms.size(); i++)
        {
            term = terms.get(i).toString();
            if (term.equals(""))
                continue;
            if (term.charAt(0) == '-')
                polynomial += " - " + term.substring(1);
            else
                polynomial += " + " + term;
        }

        return polynomial;
    }

    public static void main(String args[])
    {
        Polynomial p = new Polynomial("-x");
        System.out.println(p);
    }
}
