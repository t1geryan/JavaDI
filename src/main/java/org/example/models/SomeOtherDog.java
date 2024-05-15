package org.example.models;

public class SomeOtherDog implements Dog {

    @Override
    public void bark() {
        System.out.println("SomeOtherDog barks");
    }
}
