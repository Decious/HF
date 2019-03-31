package by.voinilo.decorator_pattern;

public interface Unit {
    void draw();
}

class Soldier implements Unit {
    @Override
    public void draw() {
        System.out.println("soldier is drawn");
    }
}

class Rider implements Unit {
    @Override
    public void draw() {
        System.out.println("Rider is drawn");
    }
}

class Ork implements Unit {

    @Override
    public void draw() {
        System.out.println("Ork is drawn");
    }
}