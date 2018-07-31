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
        ArrayList<String> terms = new ArrayList<>();
        int startIndex = 0;

        if (polynomial.charAt(0) != '-')
            polynomial = "+" + polynomial;

        for (int i = 1; i < polynomial.length(); i++)
        {
            if (polynomial.charAt(i) == '+' || polynomial.charAt(i) == '-')
            {
                terms.add(polynomial.substring(startIndex, i));
                startIndex = i;
            }
        }
        terms.add(polynomial.substring(startIndex, polynomial.length()));

        for (int i=0; i < terms.size(); i++)
            System.out.println(terms.get(i));
    }
    public void add(Term term)
    {
        terms.add(term);
    }

    public void sort()
    {
        terms.sort(null);
    }

    public void print()
    {
        for (int i=0; i < terms.size(); i++)
            System.out.println(terms.get(i));
    }

    public static void main(String args[])
    {
        Polynomial p = new Polynomial("");
    }
}
