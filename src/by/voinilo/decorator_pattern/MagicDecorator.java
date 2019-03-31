package by.voinilo.decorator_pattern;

public class MagicDecorator extends Decorator {
    public MagicDecorator(Unit component) {
        super(component);
    }

    @Override
    public void afterDraw() {
        System.out.println("...........added magic");
    }
}
