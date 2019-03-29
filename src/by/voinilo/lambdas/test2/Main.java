package by.voinilo.lambdas.test2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("fda", 35));
        persons.add(new Person("aaaa", 42));
        persons.add(new Person("vvvvv", 51));
        persons.add(new Person("xxxxx", 16));
        persons.add(new Person("gggggg", 7));

//        for (Person p :
//                persons) {
//            System.out.println(p);
//        }

//        persons.stream().forEach(per -> System.out.println(per));

        double avg = 0;
        int sum = 0;
        int adult = 0;
        for (Person p : persons
        ) {
            if (p.getAge() >= 18) {
                sum = sum + p.getAge();
                adult++;
            }
            avg = (double) sum / adult;

    }

        System.out.println("********************");
        System.out.println(avg);
        System.out.println("*******************");


        double avgA2 = persons.stream().filter(person -> person.getAge() >= 18).
                mapToInt(p -> p.getAge()).average().getAsDouble();
        System.out.println(avgA2);


        persons.stream().
                filter(person -> person.getAge() >= 18).
                sorted(Comparator.comparing(Person::getName)).
                map(person -> person.getName()).

                forEach(System.out::println);


    }
}
