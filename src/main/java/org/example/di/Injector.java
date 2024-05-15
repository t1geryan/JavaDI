package org.example.di;

import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private final Properties config;

    public Injector(Properties config) {
        if (config == null) {
            throw new NullPointerException("Config is null");
        }
        this.config = config;
    }

    public <T> void inject(T instance) {
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (final Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String dependencyInstanceClassName = config.getProperty(fieldType.getName());
                if (dependencyInstanceClassName != null) {
                    try {
                        Class<?> dependencyInstanceClass = Class.forName(dependencyInstanceClassName);
                        Object dependencyInstance = dependencyInstanceClass.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(instance, dependencyInstance);
                    } catch (Exception e) {
                        throw new IllegalStateException("There is error while providing dependency for " + instance.getClass().getName(), e);
                    }
                } else {
                    throw new IllegalStateException("There is no declared implementation of " + fieldType.getName());
                }
            }
        }
    }
}
