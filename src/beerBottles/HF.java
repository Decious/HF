package beerBottles;

public class HF {
    public static void main(String[] args) {
        int beerNum = 5;
        String word = " Бутылок (Бутылки)";
        while (beerNum > 0) {
            if (beerNum == 1) {
                word = "бутылка";
            }
            if (beerNum > 0) {
            System.out.println(beerNum + word + " piva na stene");
            System.out.println(beerNum + " " + word + " piva");
            System.out.println("Voz'mu odnu");
            System.out.println("Pusti po krugu");
            beerNum = beerNum - 1;


            }
            if (beerNum ==0){
                System.out.println("net butilok piva na stene");
            }

        }
    }
}