package se.task.objectmovingsimulation.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Action {
    PROGRAM_END(0, false),
    FORWARD_MOVING(1, true),
    BACKWARD_MOVING(2, true),
    CLOCKWISE_ROTATION(3, false),
    COUNTER_CLOCKWISE_ROTATION(4, false),
    LEFT_FORWARD_DIAGONAL_MOVING(5, true),
    RIGHT_FORWARD_DIAGONAL_MOVING(6, true),
    LEFT_BACKWARD_DIAGONAL_MOVING(7, true),
    RIGHT_BACKWARD_DIAGONAL_MOVING(8, true);

    @Getter
    private final int codeNumber;

    @Getter
    private final boolean needCheckPosition;

    Action(int codeNumber, boolean needCheckPosition) {
        this.codeNumber = codeNumber;
        this.needCheckPosition = needCheckPosition;
    }

    public static Optional<Action> getActionByCode(int code) {
        return Arrays.stream(Action.values())
                .filter(inputCode -> inputCode.codeNumber == code)
                .findFirst();
    }

    public static String getAllCodes() {
        return Arrays.stream(Action.values()).map(action -> String.valueOf(action.codeNumber))
                .collect(Collectors.joining(","));
    }
}
