package agh.po.Interfaces;
import agh.po.MapElements.AbstractMapElement;
import agh.po.Vector2D;

public interface IPositionChangeObserver {
    void positionChanged(IMapElement element, Vector2D oldPosition, Vector2D newPosition);
    //methods that allow us to update freeFields list
    void elementRemoved(IMapElement element, Vector2D position);
    void elementAdded(IMapElement element, Vector2D position);

    void deleteAnimal(IMapElement element);
}
