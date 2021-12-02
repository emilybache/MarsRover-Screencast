package codingdojo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarsTest {
    @Test
    void left_turn() {
        assertEquals(Compass.W, Compass.left(Compass.N));
        assertEquals(Compass.S, Compass.left(Compass.W));
        assertEquals(Compass.E, Compass.left(Compass.S));
        assertEquals(Compass.N, Compass.left(Compass.E));
    }

    @Test
    void right_turn() {
        assertEquals(Compass.E, Compass.right(Compass.N));
        assertEquals(Compass.N, Compass.right(Compass.W));
        assertEquals(Compass.W, Compass.right(Compass.S));
        assertEquals(Compass.S, Compass.right(Compass.E));
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
        assertEquals(new Plateau(new Coords(5, 5)), MarsInputParser.parsePlateau("5 5"));
        assertEquals(new Plateau(new Coords(4, 6)), MarsInputParser.parsePlateau("4 6"));
    }

    @Test
    void create_plateau_illegal_input() {
        assertThrows(IllegalArgumentException.class,
                () -> MarsInputParser.parsePlateau("bad input"),
                "expected bad input exception, but it didn't");
        assertThrows(IllegalArgumentException.class,
                () -> MarsInputParser.parsePlateau(""),
                "expected empty input exception, but it didn't");
    }

    @Test
    void create_rover() {
        var rover = MarsInputParser.parseRoverPosition("1 2 N");
        assertEquals(new Coords(1, 2), rover.getPosition());
        assertEquals(Compass.N, rover.getHeading());
    }

    @Test
    void create_second_rover() {
        var rover = MarsInputParser.parseRoverPosition("3 3 E");
        assertEquals(new Coords(3, 3), rover.getPosition());
        assertEquals(Compass.E, rover.getHeading());
    }

    @Test
    void create_rover_illegal_input() {
        assertThrows(IllegalArgumentException.class,
                () -> MarsInputParser.parseRoverPosition("bad input three"),
                "expected bad input exception, but it didn't");
        assertThrows(IllegalArgumentException.class,
                () -> MarsInputParser.parseRoverPosition(""),
                "expected empty input exception, but it didn't");
    }

    @Test
    void parse_rover_instructions() {
        assertEquals(List.of(Instruction.L, Instruction.M, Instruction.L), MarsInputParser.parseInstructions("LML"));
        assertEquals(List.of(Instruction.M, Instruction.R), MarsInputParser.parseInstructions("MR"));
    }

    @Test
    void moving_the_rover() {
       var instructions = List.of(Instruction.M, Instruction.R);
       var rover = new Rover(new Coords(0, 0), Compass.N);
       var plateau = new Plateau(new Coords(5, 5));
       rover.move(plateau, List.of(), instructions);
       assertEquals(new Coords(0, 1), rover.getPosition());
       assertEquals(Compass.E, rover.getHeading());
    }

    @Test
    void moving_the_rover_without_falling_off() {
        var instructions = List.of(Instruction.M);
        var rover = new Rover(new Coords(1, 1), Compass.N);
        var plateau = new Plateau(new Coords(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> rover.move(plateau, List.of(), instructions),
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
        var output = Mars.deployRoverFleet(input);
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
        var output = Mars.deployRoverFleet(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    void format_output() {
        var rover = new Rover(new Coords(1, 3), Compass.N);
        var output = Mars.formatRoverPosition(rover);
        assertEquals("1 3 N", output);
    }

    @Test
    void rovers_stop_instead_of_crashing() {
        var rover1 = new Rover(new Coords(0, 0), Compass.N);
        // 2nd rover is directly north of first rover
        var rover2 = new Rover(new Coords(0, 1), Compass.N);

        rover1.setInstructions(List.of(Instruction.M));

        assertThrows(IllegalArgumentException.class,
                () -> rover1.move(new Plateau(new Coords(5,5)), List.of(rover2)),
                "shouldn't have been able to move - would crash into rover2");
    }

}
