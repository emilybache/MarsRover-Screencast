package codingdojo;

import java.util.List;

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

    public void move(Plateau plateau, List<Instruction> instructions) {
        for (var instruction :
                instructions) {
            switch (instruction) {
                case L -> this.heading = Mars.left(this.heading);
                case R -> this.heading = Mars.right(this.heading);
                case M -> {
                    var newPosition = Mars.move(this.position, this.heading);
                    if (plateau.isOk(newPosition)) {
                        this.position = newPosition;
                    } else {
                        throw new IllegalArgumentException("fallen off plateau!");
                    }
                }
            }
        }
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

    public Rover move(Plateau plateau) {
        this.move(plateau, this.instructions);
        return this;
    }
}
