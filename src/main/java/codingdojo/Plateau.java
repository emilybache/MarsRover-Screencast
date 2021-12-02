package codingdojo;

public record Plateau(Coords maxExtent) {
    public boolean isOk(Coords coords) {
        return coords.isBetween(new Coords(0, 0), maxExtent);
    }
}
