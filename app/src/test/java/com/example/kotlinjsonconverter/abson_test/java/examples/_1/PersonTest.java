package com.example.kotlinjsonconverter.abson_test.java.examples._1;

import static org.junit.Assert.assertEquals;

import static co.ab180.abson.serialization.SerializerKt.serialize;

import org.junit.Test;

public class PersonTest {

    @Test
    public void test() {
        Person person = new Person("Alice", 29);
        String json = "{\"Name\": \"Alice\", \"Age\": 29}";

        assertEquals(json, serialize(person));
    }
}