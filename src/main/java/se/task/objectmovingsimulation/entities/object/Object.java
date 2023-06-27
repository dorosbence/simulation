package se.task.objectmovingsimulation.entities.object;

import lombok.Data;
import se.task.objectmovingsimulation.enums.Action;
import se.task.objectmovingsimulation.enums.Direction;

@Data
public class Object {

    private int positionX;

    private int positionY;

    private Direction direction;

    public Object(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = Direction.North;
    }

    public void setObjectFallen() {
        this.positionX = -1;
        this.positionY = -1;
    }

    public void action(Action objectAction) {
        if (Action.FORWARD_MOVING == objectAction
                || Action.BACKWARD_MOVING == objectAction) {
            move(objectAction);
        } else if (Action.CLOCKWISE_ROTATION == objectAction
                || Action.COUNTER_CLOCKWISE_ROTATION == objectAction) {
            rotate(objectAction);
        } else if (Action.LEFT_FORWARD_DIAGONAL_MOVING == objectAction
                || Action.LEFT_BACKWARD_DIAGONAL_MOVING == objectAction
                || Action.RIGHT_FORWARD_DIAGONAL_MOVING == objectAction
                || Action.RIGHT_BACKWARD_DIAGONAL_MOVING == objectAction) {
            moveDiagonal(objectAction);
        }
    }

    private void rotate(Action rotateAction) {
        if (rotateAction == Action.CLOCKWISE_ROTATION) {
            setDirection(Direction.rotateClockwise(this.direction));
        } else if (rotateAction == Action.COUNTER_CLOCKWISE_ROTATION) {
            setDirection(Direction.rotateCounterClockwise(this.direction));
        }
    }

    private void move(Action movingAction) {
        final int change = movingAction.equals(Action.FORWARD_MOVING) ? 1 : -1;
        switch (this.direction) {
            case North -> {
                this.positionY -= change;
                break;
            }
            case South -> {
                this.positionY += change;
                break;
            }
            case East -> {
                this.positionX += change;
                break;
            }
            case West -> {
                this.positionX -= change;
                break;
            }
            default -> {
                break;
            }
        }
    }

    private void moveDiagonal(Action movingAction) {
        if (Direction.North.equals(this.direction) || Direction.South.equals(this.direction)) {
            int isNorth = Direction.North.equals(this.direction) ? 1 : -1;
            switch (movingAction) {
                case LEFT_FORWARD_DIAGONAL_MOVING -> {
                    this.positionY -= isNorth;
                    this.positionX -= isNorth;
                    break;
                }
                case RIGHT_FORWARD_DIAGONAL_MOVING -> {
                    this.positionY -= isNorth;
                    this.positionX += isNorth;
                    break;
                }
                case LEFT_BACKWARD_DIAGONAL_MOVING -> {
                    this.positionY += isNorth;
                    this.positionX -= isNorth;
                    break;
                }
                case RIGHT_BACKWARD_DIAGONAL_MOVING -> {
                    this.positionY += isNorth;
                    this.positionX += isNorth;
                    break;
                }
            }
        } else {
            int isWest = Direction.West.equals(this.direction) ? 1 : -1;
            switch (movingAction) {
                case LEFT_FORWARD_DIAGONAL_MOVING -> {
                    this.positionY += isWest;
                    this.positionX -= isWest;
                    break;
                }
                case RIGHT_FORWARD_DIAGONAL_MOVING -> {
                    this.positionY -= isWest;
                    this.positionX -= isWest;
                    break;
                }
                case LEFT_BACKWARD_DIAGONAL_MOVING -> {
                    this.positionY += isWest;
                    this.positionX += isWest;
                    break;
                }
                case RIGHT_BACKWARD_DIAGONAL_MOVING -> {
                    this.positionY -= isWest;
                    this.positionX += isWest;
                    break;
                }
            }
        }
    }
}
