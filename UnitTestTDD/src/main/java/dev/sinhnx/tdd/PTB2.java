package dev.sinhnx.tdd;

public class PTB2 {
    private double a;
    private double b;
    private double c;

    public PTB2(double a, double b, double c) throws Exception {
        if (a == 0) {
            throw new Exception("Khong phai la bac 2");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(double a) throws Exception {
        if (a == 0)
            throw new Exception("Can't set a=0");
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double[] giaiPT() {
        double delta = Math.pow(b, 2) - (4 * a * c);
        if (delta == 0) {
            double[] rs = { -b / (2 * a) };
            return rs;
        } else if (delta > 0) {
            double[] rs = { (-b - Math.pow(delta, 0.5)) / (2 * a), (-b + Math.pow(delta, 0.5)) / (2 * a) };
            return rs;
        }
        return null;
    }
}