package agh.ics.oop.model;


import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grasses = new HashMap<>();


    public GrassField(int counterGrass)
    {
        placeGrass(counterGrass);
    }


    private void placeGrass(int counterGrass) {
        int upperLimit = (int) Math.sqrt(counterGrass * 10);
        Random random = new Random();
        while (grasses.size() < counterGrass)
        {
            int x = random.nextInt(0, upperLimit + 1);
            int y = random.nextInt(0, upperLimit + 1);
            Vector2d grassPlacement = new Vector2d(x, y);

            if(!grasses.containsKey(grassPlacement))
            {
                grasses.put(grassPlacement, new Grass(grassPlacement));
            }
        }
    }


    @Override
    public List<WorldElement> getElements()
    {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return Collections.unmodifiableList(elements);
    } // lista animals + grasses


    @Override
    public Boundary getCurrentBounds() {

        // lista animals + grasses
        List<WorldElement> elements = this.getElements();
        if (!elements.isEmpty())
        {
            Vector2d lowerBorder = elements.getFirst().getPosition();
            Vector2d upperBorder = elements.getFirst().getPosition();

            for (WorldElement element : elements)
            {
                lowerBorder = lowerBorder.lowerLeft(element.getPosition());
                upperBorder = upperBorder.upperRight(element.getPosition());
            }

            return new Boundary(lowerBorder, upperBorder);
        }
        return new Boundary(new Vector2d(0, 0), new Vector2d(0,0));
    }

    @Override
    public WorldElement objectAt(Vector2d position) {

        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        }
        return grasses.get(position);

        //return animals.containsKey(position) ? animals.get(position) : grasses.get(position);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
        //return !(this.objectAt(position) instanceof Animal);
    }
}
