package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;



public class Simulation {

    // Używamy ArrayList, bo często wyciągamy element o właściwym indeksie,
    // Natomiast nie dodajemy/usuwamy elementu z listy (w tym lepsza by była LinkedList)

    private List<MoveDirection> movements;
    private List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> movements)
    {
        this.movements = movements;
        for (Vector2d p : positions)
        {
            this.animals.add(new Animal(p));
        }
    }

    public void run()
    {
        int size = animals.size();
        for(int i = 0; i < movements.size(); i++)
        {
            Animal animal = animals.get(i % size);
            animal.move(movements.get(i));
            System.out.println("Zwierze %d: %s".formatted(i % size, animal));
        }
    }
}
