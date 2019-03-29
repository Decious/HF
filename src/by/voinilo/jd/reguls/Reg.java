package by.voinilo.jd.reguls;

public class Reg  {
    public static void main(String[] args) {

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.printf("%02d*%02d=%02d ",i,j,i*j);
            }
            System.out.println(" ");
        }
    }
}
