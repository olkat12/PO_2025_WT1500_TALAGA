package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {


    private static final Vector2d LOWER_BORDER = new Vector2d(0,0);
    private final Vector2d upperBorder;


    public RectangularMap(int width, int height)
    {
        this.upperBorder = new Vector2d(width, height);
    }

//    public Map<Vector2d, Animal> getAnimals() {
//        return Collections.unmodifiableMap(animals);
//    }


    @Override
    public boolean canMoveTo(Vector2d position) {

        return position.precedes(upperBorder) && position.follows(LOWER_BORDER)
                && !this.isOccupied(position);
    }

    @Override
    public String toString()
    {
        return mapVisualizer.draw(LOWER_BORDER, upperBorder);
    }

}
