import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    void addNull() {
        String expectedMessage = "Horses cannot be null.";
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void addBlank() {
        String expectedMessage = "Horses cannot be empty.";
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void getHorses() {
        List <Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Horse" + (i + 1), i + 3);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse: horses) {
            verify(horse,only()).move();
        }
    }

    @Test
    void getWinner() {
        List <Horse> horses = new ArrayList<>();
        Horse horseWinner = new Horse("Winner", 18, 30);
        Horse horseLoser = new Horse("Loser", 5, 10);
        Horse horseLoser2 = new Horse("Loser2", 3, 9);
        horses.add(horseLoser);
        horses.add(horseLoser2);
        horses.add(horseWinner);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(winner, horseWinner);
    }
}
