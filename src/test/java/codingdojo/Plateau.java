package codingdojo;

public class Plateau {
    private final Coords maxExtent;
    private final Coords minExtent = new Coords(0, 0);

    public Plateau(Coords maxExtent) {
        this.maxExtent = maxExtent;
    }

    public boolean isOk(Coords coords) {
        return coords.isBetween(minExtent, maxExtent);
    }
}
