package agh.po.WorldMap;

import agh.po.Interfaces.IMapElement;
import agh.po.Interfaces.IPositionChangeObserver;
import agh.po.Interfaces.IWorldMap;
import agh.po.MapElements.Animal;
import agh.po.MapElements.Grass;
import agh.po.Visualizer.MapVisualizer;
import agh.po.Vector2D;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final RectangleArea mapArea;
    protected int maxFreeFields;
    protected LinkedList<Vector2D> freeFields;
    private List<Animal> animals = new ArrayList<>();
    //for animals and grass
    protected HashMap<Vector2D, Animal> animalsMap = new HashMap<>();
    //protected HashMap<Vector2D, List<Animal>> animalsMap = new HashMap<>();
    protected HashMap<Vector2D, Grass> grassMap = new HashMap<>();

    abstract protected Vector2D[] calculateBorder();

    protected AbstractWorldMap(RectangleArea area) {
        this.mapArea = area;
        this.maxFreeFields = this.mapArea.area();
        this.freeFields = this.mapArea.addFreePositions();
    }

    @Override
    public void positionChanged(IMapElement element, Vector2D oldPosition, Vector2D newPosition) {
        this.elementRemoved(element, oldPosition);
        this.elementAdded(element, newPosition);
    }

    public void elementRemoved(IMapElement element, Vector2D position) {
        if (isAnimal(element)) {
            this.animalsMap.remove(position, element);
            //animals.remove(element);
        } else {
            this.grassMap.remove(position, element);
        }
        if (!isOccupied(element.getPosition()))
            this.freeFields.add(position);

    }

    public void elementAdded(IMapElement element, Vector2D position) {
        if (isAnimal(element)) {
            this.animalsMap.put(position, element);
        } else {
            this.grassMap.put(position,(Grass) element);
        }
        if (!isOccupied(position))
            this.freeFields.remove(position);
    }

    public void deleteAnimal(IMapElement element) {
        if (isAnimal(element)) {
            animals.remove(element);
        }
    }

    private static boolean isAnimal(IMapElement element) {
        return element instanceof Animal;
    }

    public boolean place(Animal animal) {
        animals.add(animal);
        animal.addObserver(this);
        this.elementAdded(animal, animal.getPosition());
        return true;
    }

    public void run(int moveEnergy) {
        for (Animal animal : animals) {
            if (!animal.decrease(moveEnergy))
                animal.move();
        }
    }

    public boolean isOccupied(Vector2D position) {
        return this.objectAt(position) != null;
    }

    public Object objectAt(Vector2D position) {
        IMapElement object = this.animalsMap.get(position);
        if (object == null)
            return this.grassMap.get(position);
        return object;
    }


    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Vector2D[] border = calculateBorder();
        return map.draw(border[0], border[1]);
    }

    public RectangleArea getMapArea() {
        return this.mapArea;
    }

    public void placeRandGrass(int plantEnergy) {
        //not really effective for empty map
        if (this.freeFields.size() < this.maxFreeFields) {
            Vector2D randPos = freeFields.get(new Random().nextInt(freeFields.size()));
            Grass grass = new Grass(randPos, plantEnergy);
            grass.addObserver(this);
            this.elementAdded(grass, grass.getPosition());
        }
    }

    public ArrayList<Animal> animalObjectAt(Vector2D position) {
        for(Animal animal: this.animals) {

        }
    }
    Grass grassObjectAt(Vector2D position) {
        return this.grassMap.get(position);
    }


}