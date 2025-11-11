package agh.ics.oop;

import agh.ics.oop.model.*;


import java.util.List;


public class World {

    public static void main(String[] args) {


        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<MoveDirection> directions = OptionsParser.parse(args);  // f b r l f b
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(positions, directions, rectangularMap);

        System.out.println(rectangularMap); // stan mapy zanim ruszymy
        simulation.run();

    }



    public static void run(List<MoveDirection> tab) {

        for(MoveDirection direction : tab)
        {
            String komunikat = switch(direction)
            {
                case MoveDirection.FORWARD -> "Zwierzak idzie do przodu";
                case MoveDirection.BACKWARD -> "Zwierzak idzie do tylu";
                case MoveDirection.RIGHT -> "Zwierzak skreca w prawo";
                case MoveDirection.LEFT -> "Zwierzak skreca w lewo";
            };
            System.out.println(komunikat);
        }
    }
}
