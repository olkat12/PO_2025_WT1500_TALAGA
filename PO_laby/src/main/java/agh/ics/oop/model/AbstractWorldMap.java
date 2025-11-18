package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    public final Map<Vector2d, Animal> animals = new HashMap<>();
    public final MapVisualizer mapVisualizer = new MapVisualizer(this);



    public List<WorldElement> getElements()
    {
        return new ArrayList<>(animals.values());
    }

    @Override
    public WorldElement objectAt(Vector2d position)
     {
         return animals.get(position);
     }

     @Override
     public boolean isOccupied(Vector2d position)
     {
         return this.objectAt(position) != null;
     }


    @Override
    public boolean place(Animal animal) {

        if (!Objects.equals(this.objectAt(animal.getPosition()), animal))
        {
            if (this.canMoveTo(animal.getPosition()))
            {
                animals.put(animal.getPosition(), animal);
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

        Vector2d oldPosition = animal.getPosition();

        // czy zwierzak na mapie
        if(Objects.equals(objectAt(animal.getPosition()), animal))
        {
            animal.move(direction, this);
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
            // jeśli zwierzak się nie może poruszyć, odłoży go na stare miejsce
        }
    }

}
