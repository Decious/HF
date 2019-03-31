package by.voinilo.decorator_pattern;

abstract class Decorator implements Unit{
    private Unit component;

    Decorator (Unit component) {
        this.component = component;
    }

    public abstract void afterDraw();

    @Override
    public void draw() {
        component.draw();
        afterDraw();
    }
}
