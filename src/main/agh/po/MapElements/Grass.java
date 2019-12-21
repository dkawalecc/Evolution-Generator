package agh.po.MapElements;

import agh.po.Interfaces.IMapElement;
import agh.po.Interfaces.IWorldMap;
import agh.po.Vector2D;

public class Grass extends AbstractMapElement{
    private final int plantEnergy;

    public Grass(Vector2D initPosition, int energy) {
        super(initPosition);
        this.plantEnergy = energy;
    }

    public int eatGrass() {
        int result = plantEnergy;
        this.deleteElement();
        return result;

    }

    @Override
    public String toString() {
        return "*";
    }
}
