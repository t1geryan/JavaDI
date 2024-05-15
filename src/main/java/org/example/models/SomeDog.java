package org.example.models;

public class SomeDog implements Dog {

    @Override
    public void bark() {
        System.out.println("SomeDog barks");
    }
}
