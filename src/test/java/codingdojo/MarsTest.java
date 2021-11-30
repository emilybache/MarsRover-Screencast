package codingdojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// x turning left
// x turning right
// x moving north
// x moving other directions
// x not falling off the plateau
// parsing the input
// formatting the output
// multiple rovers not crashing
public class MarsTest {
    @Test
    void left_turn() {
        assertEquals(Compass.W, Mars.left(Compass.N));
        assertEquals(Compass.S, Mars.left(Compass.W));
        assertEquals(Compass.E, Mars.left(Compass.S));
        assertEquals(Compass.N, Mars.left(Compass.E));
    }

    @Test
    void right_turn() {
        assertEquals(Compass.E, Mars.right(Compass.N));
        assertEquals(Compass.N, Mars.right(Compass.W));
        assertEquals(Compass.W, Mars.right(Compass.S));
        assertEquals(Compass.S, Mars.right(Compass.E));
    }

    @Test
    void moving() {
        assertEquals(new Coords(0, 1), Mars.move(new Coords(0, 0), Compass.N));
        assertEquals(new Coords(1, 0), Mars.move(new Coords(0, 0), Compass.E));
        assertEquals(new Coords(0, 1), Mars.move(new Coords(1, 1), Compass.W));
        assertEquals(new Coords(1, 0), Mars.move(new Coords(1, 1), Compass.S));
    }

    @Test
    void plateau_within_boundaries() {
        var plateau = new Plateau(new Coords(5, 5));
        assertTrue(plateau.isOk(new Coords(1, 1)));
        assertTrue(plateau.isOk(new Coords(5, 5)));
        assertTrue(plateau.isOk(new Coords(0, 0)));
        assertTrue(plateau.isOk(new Coords(5, 0)));
        assertTrue(plateau.isOk(new Coords(0, 5)));
    }

    @Test
    void plateau_outside_boundaries() {
        var plateau = new Plateau(new Coords(5, 5));
        assertFalse(plateau.isOk(new Coords(-1, -1)));
        assertFalse(plateau.isOk(new Coords(6, 6)));
        assertFalse(plateau.isOk(new Coords(0, 6)));
        assertFalse(plateau.isOk(new Coords(6, 0)));
    }

}
