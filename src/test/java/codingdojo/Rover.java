package codingdojo;

public class Rover {
    private Coords position;
    private Compass heading;

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
}
