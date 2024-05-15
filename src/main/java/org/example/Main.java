package org.example;

import org.example.di.Injector;
import org.example.models.Doghouse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        final Properties diConfig = new Properties();
        try (InputStream is = Main.class.getResourceAsStream(PROPERTIES_RESOURCE_PATH)) {
            if (is == null) {
                System.out.println("There is no correct properties file");
                return;
            }
            diConfig.load(is);
            final Injector injector = new Injector(diConfig);
            checkInjector(injector);
        } catch (IOException e) {
            System.out.printf("Error while reading properties file: %s\n", e);
        }
    }

    private static void checkInjector(Injector injector) {
        final Doghouse doghouse = new Doghouse();
        injector.inject(doghouse);
        doghouse.seeDog();
    }

    private static final String PROPERTIES_RESOURCE_PATH = "/diConfig.properties";
}
