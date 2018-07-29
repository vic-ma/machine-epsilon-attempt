package machineepsilon;

import java.util.*;

public class Polynomial
{
    private ArrayList<Term> terms = new ArrayList<>();

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
        {
            System.out.println(terms.get(i));
        }
    }

    public static void main(String args[])
    {
        Polynomial p = new Polynomial();
        Term t1 = new Term(2, 3);
        Term t2 = new Term(3, 1);
        Term t3 = new Term(1, 2);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.sort();
        p.print();
    }
}
