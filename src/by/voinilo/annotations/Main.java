package by.voinilo.annotations;

import java.beans.Transient;
import java.lang.annotation.*;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
            Main main = new Main();
            Person person = new Person();
            main.print(person, person.getClass());

    }

    void print (Object o, Class c) throws IllegalAccessException {
       Field [] field =  c.getDeclaredFields();
        for (Field field1 : field) {
            Annotation [] annotations = field1.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Show.class)){
                    System.out.println(field1.get(o));
                }
            }
        }
    }
}


@Retention(RetentionPolicy.RUNTIME)

@interface  Show{
    boolean value() default true;
}

class Person {
    @Show

    String name = "Max";

    int age =18;
}