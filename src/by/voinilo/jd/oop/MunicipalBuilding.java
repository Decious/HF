package by.voinilo.jd.oop;

public abstract class MunicipalBuilding implements Building {
    @Override
    public void construct() {
        System.out.println("Building IS constructed!");
    }

    @Override
    public void fire() {
        System.out.println("Building IS ON FIRE!");
    }

    @Override
    public void clean() {
        System.out.println("Building is cleaning");
    }

    @Override
    public boolean isOpen() {
        return false;

    }
}
