package by.voinilo.factory_pattern;

public class Factory {
    public static void main(String[] args) {
        Creator [] creators = {new CreatorVar(), new CreatorVec()};
        for (Creator creator : creators) {
            Var product = creator.factoryMethod();
            System.out.printf("Created {%s}\n", product.getClass());
        }
    }
}
class Var{}
class VarScalar extends Var{}
class VarVector extends Var{}

abstract class Creator {
    public abstract Var factoryMethod();
}

class CreatorVar extends Creator{

    @Override
    public Var factoryMethod() {
        return new VarScalar();
    }
}

class CreatorVec extends Creator{

    @Override
    public Var factoryMethod() {
        return new VarVector();
    }
}
