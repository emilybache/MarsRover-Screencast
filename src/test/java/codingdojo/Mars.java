package codingdojo;

import java.util.Map;

public class Mars {
    public static Compass left(Compass heading) {
        var leftTurns = Map.of(
                Compass.E, Compass.N,
                Compass.N, Compass.W,
                Compass.W, Compass.S,
                Compass.S, Compass.E
        );
        return leftTurns.get(heading);
    }

    public static Compass right(Compass heading) {
        var rightTurns = Map.of(
                Compass.N, Compass.E,
                Compass.E, Compass.S,
                Compass.S, Compass.W,
                Compass.W, Compass.N
        );
        return rightTurns.get(heading);
    }

    public static Coords move(Coords currentPosition, Compass heading) {
        return currentPosition.move(heading);
    }

    public static Plateau parsePlateau(String input) {
        var fields = input.split(" ");
        if (fields.length == 2) {
            int maxX = Integer.parseInt(fields[0]);
            int maxY = Integer.parseInt(fields[1]);
            return new Plateau(new Coords(maxX, maxY));
        } else {
            throw new IllegalArgumentException("expected two integers, instead found " + input);
        }
    }

    public static Rover parseRoverPosition(String input) {
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
}
