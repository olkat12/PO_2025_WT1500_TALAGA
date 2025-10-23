package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

// Uruchomienie w PowerShell:
//$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
//.\gradlew.bat run --args="f b r l"

public class World {

    public static void main(String[] args) {

          //System.out.println("System wystartowal");
          //World.run(OptionsParser.parse(args));
          //System.out.println("System zakonczyl dzialanie");

        // Sprawdzenie wektorów
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        // Sprawdzenie equals i hashCode
        Vector2d position3 = new Vector2d(1,2);
        System.out.println(position1.equals(position3));
        System.out.println(position1.hashCode() == position3.hashCode());

        // Sprawdzenie MapDirection
        MapDirection polnoc = MapDirection.NORTH;
        MapDirection zachod = MapDirection.WEST;

        System.out.println(polnoc.next());
        System.out.println(polnoc.previous());
        System.out.println(polnoc.toUnitVector());
        System.out.println(zachod.toUnitVector());




    }



    public static void run(MoveDirection[] tab) {

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


        // Sposób 1
        //System.out.print(String.join(", ", args));

        // Sposób 2
//        for (int i = 0; i < args.length - 2; i++) {
//            System.out.print(args[i] + ", ");
//        }
//        System.out.println(args[args.length - 1]);

    }
}
