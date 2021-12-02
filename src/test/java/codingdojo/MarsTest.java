package codingdojo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// x turning left
// x turning right
// x moving north
// x moving other directions
// x not falling off the plateau
// parsing the input
//     x plateau
//     x rover position
//     x rover instructions
//     x handling multi-line input and parsing it all
// x moving the rover!
// x formatting the output
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

    @Test
    void create_plateau_from_input() {
        assertEquals(new Plateau(new Coords(5, 5)), Mars.parsePlateau("5 5"));
        assertEquals(new Plateau(new Coords(4, 6)), Mars.parsePlateau("4 6"));
    }

    @Test
    void create_plateau_illegal_input() {
        assertThrows(IllegalArgumentException.class,
                () -> Mars.parsePlateau("bad input"),
                "expected bad input exception, but it didn't");
        assertThrows(IllegalArgumentException.class,
                () -> Mars.parsePlateau(""),
                "expected empty input exception, but it didn't");
    }

    @Test
    void create_rover() {
        var rover = Mars.parseRoverPosition("1 2 N");
        assertEquals(new Coords(1, 2), rover.getPosition());
        assertEquals(Compass.N, rover.getHeading());
    }

    @Test
    void create_second_rover() {
        var rover = Mars.parseRoverPosition("3 3 E");
        assertEquals(new Coords(3, 3), rover.getPosition());
        assertEquals(Compass.E, rover.getHeading());
    }

    @Test
    void create_rover_illegal_input() {
        assertThrows(IllegalArgumentException.class,
                () -> Mars.parseRoverPosition("bad input three"),
                "expected bad input exception, but it didn't");
        assertThrows(IllegalArgumentException.class,
                () -> Mars.parseRoverPosition(""),
                "expected empty input exception, but it didn't");
    }

    @Test
    void parse_rover_instructions() {
        assertEquals(List.of(Instruction.L, Instruction.M, Instruction.L), Mars.parseInstructions("LML"));
        assertEquals(List.of(Instruction.M, Instruction.R), Mars.parseInstructions("MR"));
    }

    @Test
    void moving_the_rover() {
       var instructions = List.of(Instruction.M, Instruction.R);
       var rover = new Rover(new Coords(0, 0), Compass.N);
       var plateau = new Plateau(new Coords(5, 5));
       rover.move(plateau, instructions);
       assertEquals(new Coords(0, 1), rover.getPosition());
       assertEquals(Compass.E, rover.getHeading());
    }

    @Test
    void moving_the_rover_without_falling_off() {
        var instructions = List.of(Instruction.M);
        var rover = new Rover(new Coords(1, 1), Compass.N);
        var plateau = new Plateau(new Coords(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> rover.move(plateau, instructions),
                "should have thrown exception when it fell off the plateau");
    }

    @Test
    void moving_and_parsing() {
        var input = """
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
""";
        var expectedOutput = """
1 3 N
5 1 E""";
        var output = Mars.moveRovers(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    void moving_and_parsing_with_three_rovers() {
        var input = """
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
2 3 W
LM
""";
        var expectedOutput = """
1 3 N
5 1 E
2 2 S""";
        var output = Mars.moveRovers(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    void format_output() {
        var rover = new Rover(new Coords(1, 3), Compass.N);
        var output = Mars.formatRoverPosition(rover);
        assertEquals("1 3 N", output);
    }

}
