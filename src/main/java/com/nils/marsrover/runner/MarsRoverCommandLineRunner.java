package com.nils.marsrover.runner;

import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.service.MarsRoverControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Command line interaction and solver method is handled here
 * @author  Nil Seri
 */
@Component
@RequiredArgsConstructor
public class MarsRoverCommandLineRunner implements CommandLineRunner {

    private final MarsRoverControllerService marsRoverControllerService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        RoverRobot roverRobot;
        while (true) {
            int roverX = scanner.nextInt();
            int roverY = scanner.nextInt();
            char roverOrientation = scanner.next().charAt(0);
            String instructions = scanner.next();

            roverRobot = marsRoverControllerService.solve(x, y, roverX, roverY, roverOrientation, instructions);

            StringBuilder sb = new StringBuilder();
            sb.append(roverRobot.getCoordinate().toString());
            sb.append(' ');
            sb.append(roverRobot.getOrientation());
            System.out.println(sb);
        }
    }
}
