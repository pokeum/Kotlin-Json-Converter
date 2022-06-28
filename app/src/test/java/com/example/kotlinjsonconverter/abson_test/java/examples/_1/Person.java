package com.example.kotlinjsonconverter.abson_test.java.examples._1;

import co.ab180.abson.JsonName;

public class Person {
    @JsonName("Name") private String name;
    @JsonName("Age") private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

