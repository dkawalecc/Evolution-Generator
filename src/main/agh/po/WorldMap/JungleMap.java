package agh.po.WorldMap;

import agh.po.Interfaces.IMapElement;
import agh.po.Vector2D;

public class JungleMap extends AbstractWorldMap{

    public JungleMap(RectangleArea area) {
        super(area);
    }

    public void placeRandGrass(int plantEnergy) {
        super.placeRandGrass(plantEnergy);
    }

    @Override
    protected Vector2D[] calculateBorder() {
        return this.getMapArea().calculateBorder();
    }

}
