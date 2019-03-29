package by.voinilo.lambdas;

public class Test {

    public static void fire(Object s){
        System.out.println("fireFunc");
    }
    public static void main(String[] args) {
        Switcher switcher = new Switcher();
        Lamp lamp = new Lamp();
        Radio radio = new Radio();

        //String message = "FIRE";


        switcher.addElectricityListener(lamp);
        switcher.addElectricityListener(radio);


       switcher.addElectricityListener( o -> System.out.println("FIRE!!!!!"));

        switcher.switchOn();
    }

}
