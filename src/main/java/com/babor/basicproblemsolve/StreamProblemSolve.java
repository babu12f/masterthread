package com.babor.basicproblemsolve;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProblemSolve {

    public static void main( String[] args ) {
        /*
        ['Jerry', 993]
        ['Tom', 3]
        ['Neal', 443]
        ['Jerry', 112]
        ['Shannon', 259]
        ['Shannon', 533]

        ans
        ['Jerry', 993]
        ['Tom', 3]
        ['Neal', 443]
        ['Shannon', 533]

        */
        List<Person> people = new ArrayList<>();
        people.add(new Person("Jerry", 993));
        people.add(new Person("Tom", 3));
        people.add(new Person("Jerry", 112));
        people.add(new Person("Shannon", 259));
        people.add(new Person("Shannon", 533));

        List<Person> maxById = people
                .stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.maxBy(Comparator.comparingInt(Person::getId))
                ))
                .values()
                .stream()
                .map(opt -> opt.orElse(null))
                .collect(Collectors.toList());

        maxById.forEach(person -> {
            System.out.println(person.getId() + " -- "+ person.getName());
        });
    }
}

class Person {
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
