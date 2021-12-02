package codingdojo;

import java.util.List;
import java.util.stream.Collectors;

public class Mars {

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
