package agh.po.MapElements;

import agh.po.Genotype;
import agh.po.Interfaces.IWorldMap;
import agh.po.MapDirection;
import agh.po.Vector2D;

public class Animal extends AbstractMapElement {
    private int energy;
    private int startEnergy; //kinda want it final
    private MapDirection orientation;
    private IWorldMap map;
    private Genotype genotype;

    private Animal(Vector2D initPosition, IWorldMap map) {
        super(initPosition);
        this.orientation = MapDirection.randomDirection();
        this.map = map;
        this.map.place( this);

    }

    public Animal(Vector2D initPosition, int energy, IWorldMap map) {
        this(initPosition, map);

        this.energy = energy;
        this.startEnergy = energy;

        this.genotype = new Genotype();
    }

    public Animal(Vector2D initPosition, IWorldMap map, Animal parent1, Animal parent2) {
        this(initPosition, map);

        this.energy = parent1.reproduced() + parent2.reproduced();
        this.startEnergy = this.energy;

        this.genotype = new Genotype(parent1, parent2);
    }

    public void move() {
        Vector2D oldPosition = this.getPosition();

        //first take
        this.orientation = this.orientation.rotateBy(this.genotype.getRandomGen());
        Vector2D newPosition = this.position.add(this.orientation.toUnitVector());

        this.positionChanged(oldPosition, correctPosition(newPosition));
    }

    //variant checked if 2 or more animals are on the same field
    boolean canReproduce() {
        return 2 * this.energy >= this.startEnergy;
    }

    private int reproduced() {
        int result = this.energy / 4;
        this.energy -= result;
        return result;
    }

    public void increase(int difference) {
        this.energy += difference;
    }

    public boolean decrease(int difference) {
        this.energy -= difference;
        if(this.energy <= 0) {
            this.deleteElement();
            return true;
        }
        return false;
    }

    private Vector2D correctPosition(Vector2D current) {
        Vector2D result = new Vector2D(current.x, current.y);
        //mapLowerLeft should be (0,0) by default
        if (result.x < map.getMapArea().mapLowerLeft.x) {
            result.x = map.getMapArea().mapUpperRight.x;
        }
        if (result.x >= map.getMapArea().mapUpperRight.x) {
            result.x = map.getMapArea().mapLowerLeft.x;
        }
        if (result.y < map.getMapArea().mapLowerLeft.y) {
            result.y = map.getMapArea().mapUpperRight.y;
        }

        if (result.y >= map.getMapArea().mapUpperRight.y) {
            result.y = map.getMapArea().mapLowerLeft.y;
        }
        return result;
    }

    public Genotype getGenotype() {
        return this.genotype;
    }


    @Override
    public String toString() {
        return this.orientation.toString();
    }
}
