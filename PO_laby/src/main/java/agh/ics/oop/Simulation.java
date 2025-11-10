package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Simulation {


    // final - Możemy modyfikować tą samą listę, ale nie możemy wymienić ją na inną
    private final List<MoveDirection> movements;
    private final List<Animal> animals = new ArrayList<>();
    private final WorldMap worldMap;


    public Simulation(List<Vector2d> positions, List<MoveDirection> movements, WorldMap worldMap)
    {
        this.movements = movements;
        this.worldMap = worldMap;
        for (Vector2d p : positions)
        {
            Animal animal = new Animal(p);
            if(this.worldMap.place(animal))
            {
                animals.add(animal);
            }
        }
    }

    public List<Animal> getAnimals() {

        // żeby nie zepsuć bezpieczeństwa hermetyzacji - żeby była niemodyfikowalna
        // Bo final przed tym nie chroni
        // Jedynie przed tym, żeby nie zmienić listy na inną
        return Collections.unmodifiableList(animals);
    }

    public void run()
    {
        int size = animals.size();
        for(int i = 0; i < movements.size(); i++)
        {
            Animal animal = animals.get(i % size);
            worldMap.move(animal, movements.get(i));
            System.out.println(worldMap);
        }
    }
}
