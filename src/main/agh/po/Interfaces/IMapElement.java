package agh.po.Interfaces;

import agh.po.Vector2D;

public interface IMapElement {

    Vector2D getPosition();
    String toString();

    void deleteElement();
}
