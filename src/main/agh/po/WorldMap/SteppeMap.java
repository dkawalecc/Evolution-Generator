package agh.po.WorldMap;

import agh.po.MapElements.Animal;
import agh.po.MapElements.Grass;
import agh.po.Vector2D;
import org.jcp.xml.dsig.internal.dom.ApacheOctetStreamData;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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
            new Animal(this.getMapArea().placeRandPoint(), startEnergy, this);
        }
    }

    public void newDay() {
        this.placeRandGrass(this.plantEnergy);
        this.jungle.placeRandGrass(this.plantEnergy);
        //animals move if they have enough energy
        this.run(this.moveEnergy);
        //for each position
        //reproduce is being invoked many times for the same element
        this.animalsMap.forEach((position, animal) -> {
            this.eating(position);
            this.reproduce(position);
        });

        /*
        *   this.positionList.forEach((position) -> {
        *   this.eating(position);
        *   this.reproduce(position);
        *   });
        */

    }

    private void reproduce(Vector2D position) {
        Animal[] parents = this.strongestAnimals(position);
        if(parents != null && parents[0].canReproduce() && parents[1].canReproduce()){
            Vector2D newField = null;
            ArrayList<Vector2D> newFields = parents[0].getPosition().nextField();
            int i = 0;
            for (Vector2D field : newFields) {
                if(!isOccupied(field))
                    newField = field;
            }
            if(newField == null)
                newField = newFields.get(new Random().nextInt(8));
            new Animal(newField, this, parents[0], parents[1]);
        }
    }

    private Animal[] strongestAnimals(Vector2D position){
        ArrayList<Animal> strongest = this.strongestAtPosition(position);
        if(strongest.isEmpty()){
            return null;
        }
        else if(strongest.size() >= 2){
            return new Animal[]{strongest.get(0), strongest.get(1)};
        }
        else {
            Animal first = strongest.get(0);
            ArrayList<Animal> animals = this.animalObjectAt(position);
            if (animals.size() >= 2) {
                Animal second = animals.stream().filter(animal -> animal != first).max(Animal::compareByEnergy).get();
                return new Animal[]{first, second};
            }
        }
        return null;

    }

    private ArrayList<Animal> strongestAtPosition(Vector2D position) {
        ArrayList<Animal> animals = this.animalObjectAt(position);
        if(animals.isEmpty())
            return animals;
        else {
            int maxEnergy = animals.stream()
                    .max(Animal::compareByEnergy).get().getEnergy();

            return animals.stream()
                    .filter(an -> an.getEnergy() == maxEnergy).collect(Collectors.toCollection(ArrayList::new));
        }

    }

    private void eating(Vector2D position) {
        Grass grass = this.grassObjectAt(position);
        if(grass != null) {
            ArrayList<Animal> animals = animalObjectAt(position);
            if(!animals.isEmpty()) {
                ArrayList<Animal> strongestAnimals = this.strongestAtPosition(position);

                for (Animal animal : strongestAnimals) {
                    animal.increase(grass.eatGrass() / strongestAnimals.size());
                }
            }
        }
    }

    @Override
    protected Vector2D[] calculateBorder() {
        return this.getMapArea().calculateBorder();
    }


}
