package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Uruchomienie w PowerShell:
//$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
//.\gradlew.bat run --args="f b r l"

//ArrayList<Animal> animals = new ArrayList<>();
//animals.add(new Animal());

public class World {

    public static void main(String[] args) {


        // f b r l f f r r f f f f f f f f
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        // wydobycie zwierzęcia z listy z obiektu simulation
        System.out.println(simulation.getAnimals().get(0).toString());


        // Sprawdzenie zwierzęcia
        Animal animal1 = new Animal(new Vector2d(1,2));
        System.out.println(animal1.toString());
        System.out.println(animal1.isAt(new Vector2d(1,2)));
//
//        animal1.move(MoveDirection.LEFT); // na zachod
//        System.out.println(animal1.toString());
//        animal1.move(MoveDirection.FORWARD);  // 0,2
//        animal1.move(MoveDirection.FORWARD);  // -1, 2
//        System.out.println(animal1.toString());


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
