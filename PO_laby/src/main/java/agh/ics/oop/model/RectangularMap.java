package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {


    private static final Vector2d LOWER_BORDER = new Vector2d(0,0);
    private final Vector2d upperBorder;


    public RectangularMap(int width, int height)
    {
        this.upperBorder = new Vector2d(width, height);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {

        return position.precedes(upperBorder) && position.follows(LOWER_BORDER)
                && !this.isOccupied(position);
    }


    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(LOWER_BORDER, upperBorder);
    }
}
