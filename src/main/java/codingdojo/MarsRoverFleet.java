package codingdojo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarsRoverFleet {
    private final Plateau plateau;
    private final ArrayList<Rover> all_rovers;

    public MarsRoverFleet(Plateau plateau, ArrayList<Rover> all_rovers) {

        this.plateau = plateau;
        this.all_rovers = all_rovers;
    }

    List<Rover> moveRovers() {
        return this.getAllRovers().stream()
                .map(rover -> rover.move(this.getPlateau(), this.getAllRovers()))
                .collect(Collectors.toList());
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public List<Rover> getAllRovers() {
        return all_rovers;
    }
}
