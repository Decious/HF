package by.voinilo.calculator;

public class Calculator implements Actions{

    @Override
    public double sum(int a, int b) {
        return a+b;
    }

    @Override
    public double mult(int a, int b) {
        return a*b;
    }

    @Override
    public double div(int a, int b) {
        return a/b;
    }

    @Override
    public double sub(int a, int b) {
        return a-b;
    }

    @Override
    public double sqrt(int a) {
        return Math.sqrt(a);
    }

    @Override
    public double exp(int a, int b) {
        return Math.pow(a,b);
    }
}
