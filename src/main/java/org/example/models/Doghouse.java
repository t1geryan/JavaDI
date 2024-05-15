package org.example.models;

import org.example.di.AutoInjectable;

public class Doghouse {

    @AutoInjectable
    private Dog dog;

    public void seeDog() {
        dog.bark();
    }
}
