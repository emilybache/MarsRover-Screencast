package codingdojo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MarsInputParser {
    public static MarsRoverFleet parseRoverFleet(String input) {
        var lines = input.split("\n");
        var plateau = parsePlateau(lines[0]);

        var all_rovers = new ArrayList<Rover>();
        int roverCount = (lines.length - 1)/2;
        for (int i = 0; i < roverCount; i++) {
            var rover = parseRoverPosition(lines[1 + i*2]);
            var instructions = parseInstructions(lines[2 + i*2]);
            rover.setInstructions(instructions);
            all_rovers.add(rover);
        }
        return new MarsRoverFleet(plateau, all_rovers);
    }

    static Plateau parsePlateau(String input) {
        var fields = input.split(" ");
        if (fields.length == 2) {
            int maxX = Integer.parseInt(fields[0]);
            int maxY = Integer.parseInt(fields[1]);
            return new Plateau(new Coords(maxX, maxY));
        } else {
            throw new IllegalArgumentException("expected two integers, instead found " + input);
        }
    }

    static Rover parseRoverPosition(String input) {
        var fields = input.split(" ");
        if (fields.length == 3) {
            int x = Integer.parseInt(fields[0]);
            int y = Integer.parseInt(fields[1]);
            var heading = Compass.valueOf(fields[2]);
            return new Rover(new Coords(x, y), heading);
        } else {
            throw new IllegalArgumentException("expected rover position, instead found " + input);
        }
    }

    static List<Instruction> parseInstructions(String input) {
        return input.chars()
                .mapToObj(c -> Instruction.valueOf(Character.toString(c)))
                .collect(Collectors.toList());
    }
}
