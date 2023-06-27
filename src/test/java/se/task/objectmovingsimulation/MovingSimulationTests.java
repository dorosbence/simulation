package se.task.objectmovingsimulation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.task.objectmovingsimulation.dtos.SimulationInitDto;
import se.task.objectmovingsimulation.entities.simulation.Simulation;
import se.task.objectmovingsimulation.exceptions.InvalidActionCodeException;
import se.task.objectmovingsimulation.exceptions.InvalidInitParamsException;
import se.task.objectmovingsimulation.exceptions.NegativeTableLengthException;

class MovingSimulationTests {

    @Test
    void successSimulationTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("1,4,1,3,2,3,2,4,1,0");
            Assertions.assertArrayEquals(new int[]{0, 1}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void fallOffInstantlyTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,5,5");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("1,3,2,0");
            Assertions.assertArrayEquals(new int[]{-1, -1}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void failedSimulationTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        try {
            int[] result = simulation.takeAllActions("1,4,1,3,2,3,2,4,1,1,1,0");
            Assertions.assertArrayEquals(new int[]{-1, -1}, result);
        } catch (InvalidActionCodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void invalidActionTest() {
        Exception exception = Assertions.assertThrows(InvalidActionCodeException.class, () -> {
            Simulation simulation = new Simulation("4,4,2,2");
            int[] result = simulation.takeAllActions("1,11,2,0");
        });

        String expectedMessage = "is not included in the action list:";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void successSimulationWithJsonTest() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimulationInitDto simulationInitDto = mapper.readValue(
                    "{\"header\":\"4,4,2,2\",\"actions\":\"1,4,1,3,2,3,2,4,1,0\"}", SimulationInitDto.class);
            Simulation simulation = new Simulation(simulationInitDto.getHeader(), simulationInitDto.getActions());
            Assertions.assertArrayEquals(new int[]{0, 1}, new int[]{simulation.getObject().getPositionX(), simulation.getObject().getPositionY()});
        } catch (JsonProcessingException | InvalidInitParamsException | InvalidActionCodeException |
                 NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void failedSimulationWithJsonTest() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimulationInitDto simulationInitDto = mapper.readValue(
                    "{\"header\":\"4,4,2,2\",\"actions\":\"1,4,1,3,2,3,2,4,1,0\"}", SimulationInitDto.class);
            Simulation simulation = new Simulation(simulationInitDto.getHeader(), simulationInitDto.getActions());
            Assertions.assertArrayEquals(new int[]{0, 1}, new int[]{simulation.getObject().getPositionX(), simulation.getObject().getPositionY()});
        } catch (JsonProcessingException | InvalidInitParamsException | InvalidActionCodeException |
                 NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successSimulationWithJson2Test() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimulationInitDto simulationInitDto = mapper.readValue(
                    "{\"header\":\"2,2,4,4\",\"actions\":\"1,4,1,3,2,3,2,4,1,1,1,0\"}", SimulationInitDto.class);
            Simulation simulation = new Simulation(simulationInitDto.getHeader(), simulationInitDto.getActions());
            Assertions.assertArrayEquals(new int[]{-1, -1}, new int[]{simulation.getObject().getPositionX(), simulation.getObject().getPositionY()});
        } catch (JsonProcessingException | InvalidInitParamsException | InvalidActionCodeException |
                 NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
    }
}
