package by.voinilo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SomeClass someClass= new SomeClass();
        //ref types
        Class clss = someClass.getClass();
        Class clss2 =  SomeClass.class;
        Class clss3 = Class.forName("by.voinilo.reflection.SomeClass");


        SomeClass someClass1 = (SomeClass) clss.newInstance();

        Constructor []  constructor = clss.getDeclaredConstructors();
        for (Constructor constructor1 : constructor) {
            System.out.println(constructor1.getName());
            Parameter [] parameters = constructor1.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
                System.out.println(parameter.getType().getName());
            }
        }
    }
}
class SomeClass{
    private static transient int i;

    String s;
    public SomeClass(){}
    SomeClass(String s){
        this.s=s;
    }
    public String someMethod(){
        System.out.println("this is "+s);
        return s;
    }
}