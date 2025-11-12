package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private int counterGrass;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();


    public GrassField(int counterGrass)
    {
        this.counterGrass = counterGrass;

        int upperLimit = (int) Math.sqrt(counterGrass * 10);
        Random random = new Random(12);
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
    public WorldElement objectAt(Vector2d position) {

        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        }
        return grasses.get(position);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }


    @Override
    public String toString()
    {
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

            return mapVisualizer.draw(lowerBorder, upperBorder);
        }
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(0,0));
        
    }

}
