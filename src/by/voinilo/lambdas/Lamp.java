package by.voinilo.lambdas;

public class Lamp implements ElectricityConsumer{


    public void lampOn(){
        System.out.println("lamp is on");
    }

    @Override
    public void electricityOn(Object obj) {
        lampOn();
    }
}
