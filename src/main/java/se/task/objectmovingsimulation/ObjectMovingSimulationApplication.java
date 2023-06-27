package se.task.objectmovingsimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.task.objectmovingsimulation.entities.simulation.Simulation;

import java.util.Scanner;

@SpringBootApplication
public class ObjectMovingSimulationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ObjectMovingSimulationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter the init params:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        Simulation simulation = new Simulation(line);

        System.out.println("Enter the action params:");
        String actions = scanner.nextLine();
        int[] result = simulation.takeAllActions(actions);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}
