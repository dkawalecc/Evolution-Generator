package agh.po.WorldMap;

import agh.po.Interfaces.IMapElement;
import agh.po.Interfaces.IPositionChangeObserver;
import agh.po.Interfaces.IWorldMap;
import agh.po.MapElements.Animal;
import agh.po.MapElements.Grass;
import agh.po.MapVisualizer;
import agh.po.Vector2D;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final RectangleArea mapArea;
    protected int maxFreeFields;
    protected LinkedList<Vector2D> freeFields;
    private List<Animal> animals = new ArrayList<>();
    //for animals and grass
    protected HashMap<Vector2D, IMapElement> mapElements = new HashMap<>();

    abstract protected Vector2D[] calculateBorder();

    protected AbstractWorldMap(RectangleArea area) {
        this.mapArea = area;
        this.maxFreeFields = this.mapArea.area();
        this.freeFields = this.mapArea.addFreePositions();
    }
/*
    protected AbstractWorldMap(Vector2D mapLowerLeft, Vector2D mapUpperRight) {
        this.mapArea = new RectangleArea(mapLowerLeft, mapUpperRight);
        this.maxFreeFields = this.mapArea.area();
        this.freeFields = this.mapArea.addFreePositions();
    }
*/
    @Override
    public void positionChanged(IMapElement element, Vector2D oldPosition, Vector2D newPosition) {
        Animal animal = (Animal) this.mapElements.get(oldPosition);
        //this.mapElements.remove(oldPosition);
        //this.mapElements.put(newPosition, animal);
        this.elementRemoved(element ,oldPosition);
        this.elementAdded(element, newPosition);
    }

    public void elementRemoved(IMapElement element, Vector2D position) {
        this.mapElements.remove(position, element);
        if(element instanceof Animal)
            animals.remove(element);
        if(!isOccupied(element.getPosition()))
            this.freeFields.add(position);

    }
    public void elementAdded(IMapElement element, Vector2D position) {
        this.mapElements.put(position, element);
        if(!isOccupied(position))
            this.freeFields.remove(position);
    }

    public boolean place(Animal animal) {
        animals.add(animal);
        animal.addObserver(this);
        this.elementAdded(animal, animal.getPosition());
        //this.mapElements.put(animal.getPosition(), animal);
        return true;
    }

    public void run() {
        for (Animal animal : animals)
            animal.move();
    }

    public boolean isOccupied(Vector2D position) {
        return this.objectAt(position) != null;
    }

    public Object objectAt(Vector2D position) {
        return this.mapElements.get(position);
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
        if(this.freeFields.size() < this.maxFreeFields) {
            Vector2D randPos = freeFields.get(new Random().nextInt(freeFields.size()));
            Grass grass = new Grass(randPos, plantEnergy);
            grass.addObserver(this);
            this.elementAdded(grass, grass.getPosition());
        }
    }


}