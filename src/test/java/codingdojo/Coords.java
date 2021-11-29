package codingdojo;

public record Coords(int x, int y) {
    public Coords move(Compass heading) {
        int dx = 0;
        int dy = 0;
        if (heading == Compass.W) {
            dx = -1;
        }
        if (heading == Compass.E) {
            dx = 1;
        }
        if (heading == Compass.N) {
            dy = 1;
        }
        if (heading == Compass.S) {
            dy = -1;
        }
        return new Coords(this.x + dx, this.y + dy);
    }
}
