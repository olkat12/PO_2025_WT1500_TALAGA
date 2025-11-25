package agh.ics.oop;

import agh.ics.oop.model.*;


import java.util.ArrayList;
import java.util.List;

// f 123 r b
public class World {

    public static void main(String[] args) {


        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(5,5));
        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args)
        {
            try{
                String[] singleArgArray = new String[]{arg};
                List<MoveDirection> parsed = OptionsParser.parse(singleArgArray);
                directions.addAll(parsed);
            } catch (IllegalArgumentException ex) {
                System.out.println("Exception message: " + ex.getMessage());
            }
        }
        //RectangularMap rectangularMap = new RectangularMap(5, 5);
        GrassField grassField = new GrassField(7);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        grassField.addListener(consoleMapDisplay);

        Simulation simulation = new Simulation(positions, directions, grassField);
        simulation.run();
    }

}
