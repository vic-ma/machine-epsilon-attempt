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
        this.simplify();
    }

    public int size()
    {
        return terms.size();
    }

    public int degree()
    {
        return terms.get(0).getExponent().getNumerator();
    }

    public Fraction leadingCoefficient()
    {
        return terms.get(0).getCoefficient();
    }

    public Term get(int i)
    {
        return this.terms.get(i);
    }

    public void addTerm(Term term)
    {
        terms.add(term);
        this.sort();
        this.simplify();
    }

    public void subtractTerm(Term term)
    {
        terms.add(Term.multiply(term, new Term(-1, 0)));
        this.sort();
        this.simplify();
    }

    public void multiplyTerm(Term term)
    {
        for (int i = 0; i < terms.size(); i++)
        {
            terms.set(i, Term.multiply(terms.get(i), term));
        }
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
                terms.add(i, sum);
                terms.remove(current);
                terms.remove(next);
                i--;
            }
        }
        if (this.toString().equals(""))
            terms.clear();
    }

    public static Polynomial add(Polynomial p1, Polynomial p2)
    {
        Polynomial sum = p1;
        for (int i = 0; i < p2.size(); i++)
            sum.addTerm(p2.get(i));
        sum.sort();
        sum.simplify();
        return sum;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2)
    {
        Polynomial difference = p1;
        for (int i = 0; i < p2.size(); i++)
        {
            difference.subtractTerm(p2.get(i));
        }
        return difference;
    }

    public static Polynomial divide(Polynomial p1, Polynomial p2)
    {
        Polynomial q = new Polynomial();
        Polynomial r = p1;
        while (!(r.toString().equals("")) && r.degree() >= p2.degree())
        {
            Term t = Term.divide(r.get(0), p2.get(0));
            q.addTerm(t);
            Polynomial temp = p2;
            p2.multiplyTerm(t);
            r = Polynomial.subtract(r, p2);
            p2 = temp;
        }
        return q;
    }

    public static void main(String args[])
    {
        Polynomial p1 = new Polynomial("x^2");
        Polynomial p2 = new Polynomial("x+1");
        System.out.println(Polynomial.divide(p1, p2));
    }
}
