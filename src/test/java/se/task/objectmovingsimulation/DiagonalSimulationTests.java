package se.task.objectmovingsimulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.task.objectmovingsimulation.entities.simulation.Simulation;
import se.task.objectmovingsimulation.exceptions.InvalidActionCodeException;
import se.task.objectmovingsimulation.exceptions.InvalidInitParamsException;
import se.task.objectmovingsimulation.exceptions.NegativeTableLengthException;

public class DiagonalSimulationTests {

    @Test
    void diagonalTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("5,3,3,8,1,7,1,6");
            Assertions.assertArrayEquals(new int[]{0, 2}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void diagonalNorthTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("5,6,7,8,0");
            Assertions.assertArrayEquals(new int[]{2, 2}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void diagonalSouthTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("5,5,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("3,3,5,6,7,8,0");
            Assertions.assertArrayEquals(new int[]{2, 2}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void diagonalEastTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("5,5,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("3,5,6,7,8,0");
            Assertions.assertArrayEquals(new int[]{2, 2}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void diagonalWestTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("4,5,6,7,8,0");
            Assertions.assertArrayEquals(new int[]{2, 2}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fallOffWithDiagonal() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("5,5,5,5,0");
            Assertions.assertArrayEquals(new int[]{-1, -1}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }
}
