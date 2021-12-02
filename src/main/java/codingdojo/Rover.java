package codingdojo;

import java.util.List;
import java.util.Objects;

public class Rover {
    private Coords position;
    private Compass heading;
    private List<Instruction> instructions;

    public Rover(Coords position, Compass heading) {
        this.position = position;
        this.heading = heading;
    }

    public Coords getPosition() {
        return position;
    }

    public Compass getHeading() {
        return heading;
    }

    public void move(Plateau plateau, List<Rover> otherRovers, List<Instruction> instructions) {
        for (var instruction :
                instructions) {
            switch (instruction) {
                case L -> this.heading = Compass.left(this.heading);
                case R -> this.heading = Compass.right(this.heading);
                case M -> {
                    var newPosition = this.position.move(this.heading);
                    if (plateau.isOk(newPosition)) {
                        if (noCrashing(newPosition, otherRovers)) {
                            this.position = newPosition;
                        } else {
                            throw new IllegalArgumentException("crashed into other rover!");
                        }
                    } else {
                        throw new IllegalArgumentException("fallen off plateau!");
                    }
                }
            }
        }
    }

    private boolean noCrashing(Coords newPosition, List<Rover> otherRovers) {
        for (var rover:
             otherRovers) {
            if (rover == this) {
                continue;
            }
            if (Objects.equals(newPosition, rover.getPosition())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "position=" + position +
                ", heading=" + heading +
                '}';
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Rover move(Plateau plateau, List<Rover> otherRovers) {
        this.move(plateau, otherRovers, this.instructions);
        return this;
    }
}
