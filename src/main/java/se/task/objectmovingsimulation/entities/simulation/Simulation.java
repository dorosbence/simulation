package se.task.objectmovingsimulation.entities.simulation;

import lombok.Getter;
import se.task.objectmovingsimulation.dtos.SimulationInitDto;
import se.task.objectmovingsimulation.entities.object.Object;
import se.task.objectmovingsimulation.entities.table.CircleTable;
import se.task.objectmovingsimulation.entities.table.RectangleTable;
import se.task.objectmovingsimulation.entities.table.Table;
import se.task.objectmovingsimulation.entities.table.TriangleTable;
import se.task.objectmovingsimulation.enums.Action;
import se.task.objectmovingsimulation.enums.ActionResult;
import se.task.objectmovingsimulation.enums.Shape;
import se.task.objectmovingsimulation.exceptions.InvalidActionCodeException;
import se.task.objectmovingsimulation.exceptions.InvalidInitParamsException;
import se.task.objectmovingsimulation.exceptions.NegativeTableLengthException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Simulation {
    @Getter
    private Table table;

    @Getter
    private final Object object;

    private static final String SEPARATOR = ",";

    public Simulation(String input) throws InvalidInitParamsException, NegativeTableLengthException {
        try {
            List<Integer> initParams = Arrays.stream(input.split(SEPARATOR)).map(Integer::parseInt).toList();

            if (initParams.size() < 4) {
                throw new InvalidInitParamsException("There must be minimum 4 number parameters! Example: 4,4,2,2");
            }

            if (initParams.get(0) < 0 || initParams.get(1) < 0) {
                String message = "The table must have positive sides.";
                message += initParams.get(0) < 0 ? " Invalid width: " + initParams.get(0) : "";
                message += initParams.get(1) < 0 ? " Invalid height: " + initParams.get(1) : "";
                throw new NegativeTableLengthException(message);
            }

            try {
                Optional<Shape> shapeOptional = Shape.getShapeByCode(initParams.get(4));
                if (shapeOptional.isPresent()) {
                    Shape shape = shapeOptional.get();
                    switch (shape) {
                        case RECTANGLE -> {
                            this.table = new RectangleTable(initParams.get(0), initParams.get(1));
                        }
                        case CIRCLE -> {
                            this.table = new CircleTable(initParams.get(0), initParams.get(1));
                        }
                        case TRIANGLE -> {
                            this.table = new TriangleTable(initParams.get(0), initParams.get(1));
                        }
                    }
                } else {
                    //if no matching shape, square will be created
                    this.table = new RectangleTable(initParams.get(0), initParams.get(1));
                }
            } catch (IndexOutOfBoundsException e) {
                //if no shape code given, square will be created
                this.table = new RectangleTable(initParams.get(0), initParams.get(1));
            }

            this.object = new Object(initParams.get(2), initParams.get(3));

        } catch (NumberFormatException e) {
            throw new InvalidInitParamsException("Input parameters must be numbers, separated by: " + SEPARATOR);
        }
    }

    public Simulation(String header, String actions) throws InvalidInitParamsException, NegativeTableLengthException, InvalidActionCodeException {
        this(header);
        int[] result = takeAllActions(actions);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    public Simulation(SimulationInitDto simulationInitDto) throws InvalidInitParamsException, InvalidActionCodeException, NegativeTableLengthException {
        this(simulationInitDto.getHeader(), simulationInitDto.getActions());
    }

    public int[] takeAllActions(String inputCodes) throws InvalidActionCodeException {
        if (table.isFieldOnTable(object.getPositionX(), object.getPositionY())) {
            object.setObjectFallen();
            return new int[]{this.object.getPositionX(), this.object.getPositionY()};
        }

        for (String i : inputCodes.split(SEPARATOR)) {
            ActionResult actionResult = action(Integer.parseInt(i));
            if (ActionResult.FELL_DOWN.equals(actionResult)) {
                object.setObjectFallen();
                return new int[]{this.object.getPositionX(), this.object.getPositionY()};
            } else if (ActionResult.FINISH.equals(actionResult)) {
                break;
            }
        }
        return new int[]{this.object.getPositionX(), this.object.getPositionY()};
    }

    private ActionResult action(int inputCode) throws InvalidActionCodeException {
        Optional<Action> optionalAction = Action.getActionByCode(inputCode);
        if (optionalAction.isEmpty()) {
            throw new InvalidActionCodeException("The given code: " + inputCode
                    + " is not included in the action list: " + Action.getAllCodes());
        }

        final Action action = optionalAction.get();

        if (Action.PROGRAM_END.equals(action)) {
            return ActionResult.FINISH;
        }
        object.action(action);

        if (action.isNeedCheckPosition() &&
                table.isFieldOnTable(object.getPositionX(), object.getPositionY())) {
            return ActionResult.FELL_DOWN;
        }
        return ActionResult.CONTINUE;
    }

}
