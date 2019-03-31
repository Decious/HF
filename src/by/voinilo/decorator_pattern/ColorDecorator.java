package by.voinilo.decorator_pattern;

public class ColorDecorator extends Decorator{
    public ColorDecorator(Unit component) {
        super(component);
    }

    @Override
    public void afterDraw() {
        System.out.println(".........added color");
    }
}
