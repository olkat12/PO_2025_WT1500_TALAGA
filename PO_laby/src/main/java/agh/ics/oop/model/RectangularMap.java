package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private static final Vector2d LOWER_BORDER = new Vector2d(0,0);
    private final Vector2d upperBorder;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height)
    {
        this.upperBorder = new Vector2d(width, height);
    }

    public Map<Vector2d, Animal> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }

    @Override
    public Animal objectAt(Vector2d position) {

        return animals.get(position);
        // zwraca null jesli nie ma takiej pozycji w slowniku
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        return this.objectAt(position) != null;
        //return animals.containsKey(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return position.precedes(upperBorder) && position.follows(LOWER_BORDER)
                && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {

        if (!animals.containsValue(animal))
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
        if(animals.containsKey(oldPosition))
        {
            animal.move(direction, this);
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
            // jeśli zwierzak się nie może poruszyć, odłoży go na stare miejsce
        }
    }

    @Override
    public String toString()
    {
        return mapVisualizer.draw(LOWER_BORDER, upperBorder);
    }

}
