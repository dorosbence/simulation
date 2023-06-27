package se.task.objectmovingsimulation.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum Shape {
    RECTANGLE(1), //Rectangle
    TRIANGLE(2), // Triangle
    CIRCLE(3); //Circle

    @Getter
    private final int codeNumber;

    Shape(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    public static Optional<Shape> getShapeByCode(int code) {
        return Arrays.stream(Shape.values())
                .filter(inputCode -> inputCode.codeNumber == code)
                .findFirst();
    }
}
