package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected List<MapChangeListener> listeners = new ArrayList<>();
    protected int id = new Random().nextInt(1,1000);


    @Override
    public int getId() {
        return id;
    }

    public void addListener(MapChangeListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(MapChangeListener listener)
    {
        listeners.remove(listener);
    }

    private void mapChanged(String message)
    {
        for (MapChangeListener listener : listeners)
        {
            listener.mapChanged(this, message);
        }
    }


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
    public void place(Animal animal) throws IncorrectPositionException {

        if (!Objects.equals(this.objectAt(animal.getPosition()), animal))
        {
            if (!this.canMoveTo(animal.getPosition()))
            {
                throw new IncorrectPositionException(animal.getPosition());
            }
        }
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal was placed at %s".formatted(animal.getPosition()));

    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

        Vector2d oldPosition = animal.getPosition();
        MapDirection oldDirection = animal.getMapDirection();

        // czy zwierzak na mapie
        if(Objects.equals(objectAt(animal.getPosition()), animal))
        {
            animal.move(direction, this);
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
            // jeśli zwierzak się nie może poruszyć, odłoży go na stare miejsce
        }
        mapChanged("Animal was moved from %s %s to %s %s"
                .formatted(oldDirection, oldPosition,animal.getMapDirection(), animal.getPosition()));
    }

    @Override
    public String toString()
    {
        Boundary boundary = getCurrentBounds(); // wywoła metodę z odpowiedniej klasy dziedziczącej
        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }

}
