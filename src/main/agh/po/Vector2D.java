package agh.po;

import java.util.ArrayList;

public class Vector2D {
    public int x, y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y +")";
    }

    public boolean precedes(Vector2D other) {
        return (this.x <= other.x && this.y <= other.y);
    }
    public boolean follows(Vector2D other) {
        return (this.x >= other.x && this.y >= other.y);
    }
    public Vector2D upperRight(Vector2D other) {
        return new Vector2D(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }
    public Vector2D lowerLeft (Vector2D other) {
        return new Vector2D(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }
    public Vector2D add (Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public Vector2D opposite() {
        return new Vector2D(-this.x, -this.y);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash += this.x * 37;
        hash += this.y * 19;
        return  hash;
    }

    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        else if (!(other instanceof Vector2D))
            return false;
        Vector2D that = (Vector2D) other;
        return (this.x == that.y && this.y == that.y);
    }

    public ArrayList<Vector2D> nextField(){
        ArrayList<Vector2D> fields = new ArrayList<>();
        for(MapDirection direction : MapDirection.values()){
            fields.add(this.add(direction.toUnitVector()));
        }
        return fields;
    }


}
