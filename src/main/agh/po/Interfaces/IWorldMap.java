package agh.po.Interfaces;

import agh.po.Vector2D;
import agh.po.MapElements.Animal;
import agh.po.WorldMap.RectangleArea;

import java.util.Vector;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2D and MoveDirection classes are defined.
 *
 * @author apohllo
 */
public interface IWorldMap {
    /**
     * Place a element on the map.
     *
     *
     *            The car to place on the map.
     * @return True if the car was placed. The car cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);


    void run(int moveEnergy);

    /**

     * @param position
     *            Position to check.
     * @return True if the position is occupied by Animal.
     */
    boolean isOccupied(Vector2D position); //it was not necessary

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2D position);

    RectangleArea getMapArea();

}