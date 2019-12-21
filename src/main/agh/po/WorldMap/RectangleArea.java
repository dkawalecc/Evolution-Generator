package agh.po.WorldMap;

import agh.po.Vector2D;

import java.util.LinkedList;
import java.util.Random;

public class RectangleArea {
    public Vector2D mapLowerLeft;
    public Vector2D mapUpperRight;

    RectangleArea(Vector2D mapLowerLeft, Vector2D mapUpperRight) {
        this.mapLowerLeft = mapLowerLeft;
        this.mapUpperRight = mapUpperRight;
    }

    public int area() {
        return (this.mapUpperRight.x - this.mapLowerLeft.x + 1) * (this.mapUpperRight.y - this.mapLowerLeft.y + 1); //tricky
    }

    public RectangleArea scale(double ratio) {
        Vector2D lowerLeft = new Vector2D((int) (this.mapLowerLeft.x * ratio), (int) (this.mapLowerLeft.y * ratio));
        Vector2D upperRight = new Vector2D((int) (this.mapUpperRight.x * ratio), (int) (this.mapUpperRight.y * ratio));

        Vector2D diff = this.mapUpperRight.subtract(upperRight);

        upperRight = upperRight.add(new Vector2D(diff.x / 2, diff.y / 2));
        lowerLeft = lowerLeft.add(new Vector2D(diff.x / 2, diff.y / 2));

        return new RectangleArea(lowerLeft, upperRight);
    }

    public Vector2D[] calculateBorder() {
        return new Vector2D[]{this.mapLowerLeft, this.mapUpperRight};
    }

    public LinkedList<Vector2D> addFreePositions() {
        LinkedList<Vector2D> result = new LinkedList<>();
        for (int i = mapLowerLeft.x; i < mapUpperRight.x; i++) {
            for (int j = mapLowerLeft.y; j < mapUpperRight.y; j++) {
                result.add(new Vector2D(i,j));
            }
        }
        return result;
    }

    protected Vector2D placeRandPoint() {
        Random rand = new Random();
        return new Vector2D(this.mapLowerLeft.x + rand.nextInt(this.mapUpperRight.subtract(this.mapLowerLeft).x) + 1, this.mapUpperRight.subtract(this.mapLowerLeft).y + 1 );
    }

}
