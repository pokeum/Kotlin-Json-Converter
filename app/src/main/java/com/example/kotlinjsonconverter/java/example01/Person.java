package com.example.kotlinjsonconverter.java.example01;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("Name")
    private String name;

    @SerializedName("Age")
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Person)) { return false; }
        Person person = (Person) obj;
        return  (this.name.equals(person.name) && this.age == person.age);
    }
}

