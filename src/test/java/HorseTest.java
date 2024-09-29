import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    Double randomDouble = Horse.getRandomDouble(1,25);
    Double negativeRandomDouble = Horse.getRandomDouble(-1,-25);
    @Test
    public void nullName() {
        String expectedMessage = "Name cannot be null.";
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, randomDouble, randomDouble));
        try {
            new Horse(null, randomDouble, randomDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void blankName(String blankName) {
        String expectedMessage = "Name cannot be blank.";
        assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, randomDouble, randomDouble));
        try {
            new Horse(blankName, randomDouble, randomDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    @Test
    void negativeSpeed() {
        String expectedMessage = "Speed cannot be negative.";
        assertThrows(IllegalArgumentException.class, () -> new Horse("AnyName", negativeRandomDouble, randomDouble));
        try {
            new Horse("AnyName", negativeRandomDouble, randomDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    @Test
    void negativeDistance() {
        String expectedMessage = "Distance cannot be negative.";
        assertThrows(IllegalArgumentException.class, () -> new Horse("AnyName", randomDouble, negativeRandomDouble));
        try {
            new Horse("AnyName", randomDouble, negativeRandomDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    @Test
    void getName() {
        String horseName = "Sneakers";
        assertEquals(new Horse(horseName, randomDouble, randomDouble).getName(), horseName);
    }
    @Test
    void getSpeed() {
        Double speed = 2.1;
        assertEquals(new Horse("horseName", speed, randomDouble).getSpeed(), speed);
    }
    @Test
    void getDistance() {
        Double distance = 3.5;
        assertEquals(new Horse("horseName", randomDouble, distance).getDistance(), distance);
    }
    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class);) {
            Horse testHorse = new Horse("Name", randomDouble, randomDouble);
            double expectedRandomDouble = 0.5;
            double expectedDistanceAfterMove = testHorse.getDistance() + testHorse.getSpeed() * expectedRandomDouble;
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectedRandomDouble);
            testHorse.move();
            assertEquals(expectedDistanceAfterMove, testHorse.getDistance());
        }
    }
}
