package agh.po.MapElements;

import agh.po.Interfaces.IWorldMap;
import agh.po.Vector2D;
import agh.po.Interfaces.IMapElement;
import agh.po.Interfaces.IPositionChangeObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapElement implements IMapElement {
    protected List<IPositionChangeObserver> observers;
    protected Vector2D position;

    protected AbstractMapElement(Vector2D initPosition) {
        this.position = initPosition;
        this.observers = new ArrayList<>();

    }

    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : observers) {
            //in AbstractWorldMap
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    //protected
    public void deleteElement() {
        for (IPositionChangeObserver observer : observers) {
            removeObserver(observer);
            observer.elementRemoved(this, this.position);
        }
    }

    public Vector2D getPosition() {
        return this.position;
    }
}
