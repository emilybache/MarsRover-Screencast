package codingdojo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static String deployRoverFleet(String input) {
        MarsRoverFleet fleet = MarsInputParser.parseRoverFleet(input);

        List<Rover> movedRovers = fleet.moveRovers();

        return movedRovers.stream()
                .map(Mars::formatRoverPosition)
                .collect(Collectors.joining("\n"));
    }

    public static String formatRoverPosition(Rover rover) {
        var coords = rover.getPosition();
        return coords.x() + " " + coords.y() + " " + rover.getHeading();
    }
}
