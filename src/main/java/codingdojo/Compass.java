package codingdojo;

import java.util.Map;

public enum Compass {
    N, S, E, W;

    public static Compass left(Compass heading) {
        var leftTurns = Map.of(
                E, N,
                N, W,
                W, S,
                S, E
        );
        return leftTurns.get(heading);
    }

    public static Compass right(Compass heading) {
        var rightTurns = Map.of(
                N, E,
                E, S,
                S, W,
                W, N
        );
        return rightTurns.get(heading);
    }
}
