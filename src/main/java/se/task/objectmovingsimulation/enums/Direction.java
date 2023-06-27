package se.task.objectmovingsimulation.enums;

public enum Direction {
    North,
    East,
    West,
    South;

    public static Direction rotateClockwise(Direction direction) {
        switch (direction) {
            case North -> {
                return East;
            }
            case East -> {
                return South;
            }
            case South -> {
                return West;
            }
            case West -> {
                return North;
            }
            default -> {
                return direction;
            }
        }
    }

    public static Direction rotateCounterClockwise(Direction direction) {
        switch (direction) {
            case North -> {
                return West;
            }
            case West -> {
                return South;
            }
            case South -> {
                return East;
            }
            case East -> {
                return North;
            }
            default -> {
                return direction;
            }
        }
    }
}
