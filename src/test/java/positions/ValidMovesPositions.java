package positions;

import board.coords.Direction;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ValidMovesPositions {

    public static Stream<Object[]> providesDirectionsWithOppositeColorOnStart() {
        return Stream.of(
                new Object[]{"c3", 1, 1},
                new Object[]{"d3", 1, 0},
                new Object[]{"c4", 0, 1},
                new Object[]{"e6", -1, 0},
                new Object[]{"f5", 0, -1},
                new Object[]{"f6", -1, -1}
        );
    }

    public static Stream<Object[]> provideDirectionsWithOppositeColorAfterD3() {
        return Stream.of(
                new Object[]{"c2", new ArrayList<Direction>() {{
                    add(new Direction(1, 1));
                }}},
                new Object[]{"d2", new ArrayList<Direction>() {{
                    add(new Direction(1, 0));
                }}},
                new Object[]{"e2", new ArrayList<Direction>() {{
                    add(new Direction(1, -1));
                }}},
                new Object[]{"e3", new ArrayList<Direction>() {{
                    add(new Direction(0, -1));
                    add(new Direction(1, -1));
                    add(new Direction(1, 0));
                }}},
                new Object[]{"f3", new ArrayList<Direction>() {{
                    add(new Direction(1, -1));
                }}},
                new Object[]{"f4", new ArrayList<Direction>() {{
                    add(new Direction(0, -1));
                }}},
                new Object[]{"f5", new ArrayList<Direction>() {{
                    add(new Direction(-1, -1));
                }}},
                new Object[]{"e6", new ArrayList<Direction>() {{
                    add(new Direction(-1, -1));
                }}},
                new Object[]{"d6", new ArrayList<Direction>() {{
                    add(new Direction(-1, 0));
                }}},
                new Object[]{"c6", new ArrayList<Direction>() {{
                    add(new Direction(-1, 1));

                }}},
                new Object[]{"c5", new ArrayList<Direction>() {{
                    add(new Direction(-1, 1));
                    add(new Direction(0, 1));
                }}},
                new Object[]{"c4", new ArrayList<Direction>() {{
                    add(new Direction(-1, 1));
                    add(new Direction(0, 1));
                    add(new Direction(1, 1));
                }}},
                new Object[]{"c3", new ArrayList<Direction>() {{
                    add(new Direction(0, 1));
                    add(new Direction(1, 1));
                }}}
        );
    }

    public static Stream<String[]> provideCoordinatesWhichDoesNotHaveOppositeColor() {
        return Stream.of(
                new String[]{"a1"},
                new String[]{"h8"},
                new String[]{"c6"},
                new String[]{"g2"}
        );
    }

    public static Stream<Object[]> provideCoordinatesForValidMovesOnStart() {
        return Stream.of(
                new Object[]{"D3", 0,  new ArrayList<Direction>() {{
                    add(new Direction(1, 0));
                }}},
                new Object[]{"C4", 1, new ArrayList<Direction>() {{
                    add(new Direction(0, 1));
                }}},
                new Object[]{"F5", 2, new ArrayList<Direction>() {{
                    add(new Direction(0, - 1));
                }}},
                new Object[]{"E6", 3, new ArrayList<Direction>() {{
                    add(new Direction(-1, 0));
                }}}
        );
    }

    public static Stream<Object[]> provideCoordinatesForValidMovesAfterD3(){
        return Stream.of(
                new Object[]{"C3", 0, new ArrayList<Direction>() {{
                    add(new Direction(1, 1));
                }}},
                new Object[]{"E3", 1, new ArrayList<Direction>() {{
                    add(new Direction(1, 0));
                }}},
                new Object[]{"C5", 2, new ArrayList<Direction>() {{
                    add(new Direction(0, 1));
                }}}
        );
    }
}

