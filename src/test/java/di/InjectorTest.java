package di;

import org.example.di.Injector;
import org.example.models.Doghouse;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InjectorTest {

    @Test
    public void injectInjectsDependency() {
        final Properties properties = new Properties();
        properties.put("org.example.models.Dog", "org.example.models.SomeOtherDog");
        final Injector injector = new Injector(properties);
        final Doghouse doghouse = new Doghouse();

        assertThrows(NullPointerException.class, doghouse::seeDog);

        injector.inject(doghouse);

        doghouse.seeDog();
    }

    @Test
    public void injectWithNotExistedClassThrowsIllegalStateException() {
        final Properties properties = new Properties();
        properties.put("org.example.models.Dog", "this.class.is.not.exist");
        final Injector injector = new Injector(properties);

        assertThrows(IllegalStateException.class, () -> injector.inject(new Doghouse()));
    }

    @Test
    public void injectWithEmptyPropertiesThrowsIllegalStateException() {
        final Properties properties = new Properties();
        final Injector injector = new Injector(properties);

        assertThrows(IllegalStateException.class, () -> injector.inject(new Doghouse()));
    }

    @Test
    public void constructorWithNullPropertiesThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Injector(null));
    }
}
