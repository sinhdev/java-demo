public class PTB2 extends Object {
    private double a;
    private double b;
    private double c;

    public PTB2(double a, double b, double c) throws Exception {
        if (a == 0) {
            throw new Exception("Khong phai la phuong trinh bac 2");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void main(String args[]) {
        try {
            PTB2 b2 = new PTB2(0, 2, 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}