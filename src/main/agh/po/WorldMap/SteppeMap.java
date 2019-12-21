package agh.po.WorldMap;

import agh.po.MapElements.Animal;
import agh.po.Vector2D;

public class SteppeMap extends AbstractWorldMap {
    private JungleMap jungle;
    private final int plantEnergy;
    private final int moveEnergy;

    public SteppeMap(int width, int height, int startEnergy, int moveEnergy, int plantEnergy, double jungleRatio, int animalsCount) {
        super(new RectangleArea(new Vector2D(0, 0), new Vector2D(width - 1, height - 1)));
        this.jungle = new JungleMap(this.getMapArea().scale(jungleRatio));
        this.plantEnergy = plantEnergy;
        this.moveEnergy = moveEnergy;

        //exclude jungle fields
        this.maxFreeFields -= this.jungle.maxFreeFields;
        for (Vector2D pos1 : this.freeFields) {
            for (Vector2D pos2 : this.jungle.freeFields) {
                if (pos1.equals(pos2))
                    this.freeFields.remove(pos1);
            }
        }

        for (int i = 0; i < animalsCount; i++) {
            new Animal( this.getMapArea().placeRandPoint(), startEnergy, this );
        }
    }

    public void newDay() {
        this.run();
        this.eating();
        //this.reproduce();
    }


    public void reproduce(Animal parent1, Animal parent2) {

        //different position needed
        new Animal(parent1.getPosition().nextField(),  this, parent1, parent2 );
    }

    //increase
    //decrease
    //eatGrass


    public void eating() {

    }



    @Override
    protected Vector2D[] calculateBorder() {
        return this.getMapArea().calculateBorder();
    }




}
