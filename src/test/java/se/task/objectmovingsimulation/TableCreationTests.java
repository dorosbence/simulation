package se.task.objectmovingsimulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.task.objectmovingsimulation.entities.table.CircleTable;
import se.task.objectmovingsimulation.entities.table.RectangleTable;
import se.task.objectmovingsimulation.entities.simulation.Simulation;
import se.task.objectmovingsimulation.entities.table.TriangleTable;
import se.task.objectmovingsimulation.exceptions.InvalidInitParamsException;
import se.task.objectmovingsimulation.exceptions.NegativeTableLengthException;

public class TableCreationTests {
    @Test
    void createTriangleTableTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2,2");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(TriangleTable.class, simulation.getTable());
    }

    @Test
    void createCircleTableTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2,3");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(CircleTable.class, simulation.getTable());
    }

    @Test
    void createRectangleTableByCodeTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2,1");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(RectangleTable.class, simulation.getTable());
    }

    @Test
    void createRectangleTableByUnknownCodeTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2,5");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(RectangleTable.class, simulation.getTable());
    }

    @Test
    void createRectangleTableWithoutCodeTest() {
        Simulation simulation = null;
        try {
            simulation = new Simulation("4,4,2,2,5");
        } catch (InvalidInitParamsException | NegativeTableLengthException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(RectangleTable.class, simulation.getTable());
    }

    @Test()
    void createTableWithNegativeWidthTest() {
        Exception exception = Assertions.assertThrows(NegativeTableLengthException.class, () -> {
            Simulation simulation = new Simulation("-4,4,2,2");
        });

        String expectedMessage = "Invalid width:";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void createTableWithNegativeHeightTest() {
        Exception exception = Assertions.assertThrows(NegativeTableLengthException.class, () -> {
            Simulation simulation = new Simulation("4,-4,2,2");
        });

        String expectedMessage = "Invalid height:";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void createTableWithNegativeWidthAndHeightTest() {
        Exception exception = Assertions.assertThrows(NegativeTableLengthException.class, () -> {
            Simulation simulation = new Simulation("-4,-4,2,2");
        });

        String expectedWidthMessage = "Invalid width:";
        String expectedHeightMessage = "Invalid height:";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedWidthMessage));
        Assertions.assertTrue(actualMessage.contains(expectedHeightMessage));
    }

    @Test()
    void createTableWithoutRequiredNumberTest() {
        Exception exception = Assertions.assertThrows(InvalidInitParamsException.class, () -> {
            Simulation simulation = new Simulation("4,2,2");
        });

        String expectedMessage = "There must be minimum 4";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void createTableWithWrongSeparatorTest() {
        Exception exception = Assertions.assertThrows(InvalidInitParamsException.class, () -> {
            Simulation simulation = new Simulation("4;4;2;2");
        });

        String expectedMessage = "Input parameters must be numbers";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void createTableWithNotOnlyNumbersTest() {
        Exception exception = Assertions.assertThrows(InvalidInitParamsException.class, () -> {
            Simulation simulation = new Simulation("4,a,4,2");
        });

        String expectedMessage = "Input parameters must be numbers";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
