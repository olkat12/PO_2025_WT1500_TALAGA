package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.SimulationEngine;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class World {

    public static void main(String[] args) {


        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(5,5),
                                                new Vector2d(4, 7));
        List<MoveDirection> directions = new ArrayList<>();

        // parsowanie
        for (String arg : args)
        {
            try {
                String[] singleArgArray = new String[]{arg};
                List<MoveDirection> parsed = OptionsParser.parse(singleArgArray);
                directions.addAll(parsed);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }

        // mapy
        RectangularMap rectangularMap = new RectangularMap(7, 7);
        GrassField grassField = new GrassField(7);

        // dodanie listenera do map
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        rectangularMap.addListener(consoleMapDisplay);
        grassField.addListener(consoleMapDisplay);

        // symulacje
        //Simulation simulation_grass = new Simulation(positions, directions, grassField);
        //Simulation simulation_rect = new Simulation(positions, directions, rectangularMap);
        //List<Simulation> simulations = List.of(simulation_grass, simulation_rect);

        List<Simulation> simulations = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 1000; i++)
        {
            int x = random.nextInt(1,10);
            AbstractWorldMap map = x % 2 == 0 ? new GrassField(7) : new RectangularMap(7,7);
            map.addListener(consoleMapDisplay);
            simulations.add(new Simulation(positions, directions, map));
        }


        // RUN
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        //simulationEngine.runSync();
        //simulationEngine.runAsync();
        //simulationEngine.awaitSymulationsEnd();
        simulationEngine.runAsyncInThreadPool();

        System.out.println("System zakonczyl dzialanie dla %d symulacji".formatted(simulations.size()));

        
    }

}
