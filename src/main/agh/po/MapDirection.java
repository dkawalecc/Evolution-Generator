package agh.po;

import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public String toString() {
        switch (this) {
            case NORTH: return "↑"; //"N"
            case NORTH_EAST: return "↗"; //"NE"
            case EAST: return "→"; //"E"
            case SOUTH_EAST: return "↘"; //"SE"
            case SOUTH: return "↓"; //"S"
            case SOUTH_WEST: return "↙"; //"SW"
            case WEST: return "←"; //"W"
            case NORTH_WEST: return "↖"; //"NW"
            default: return null;
        }
    }

    public static MapDirection randomDirection() {
        return MapDirection.values()[(new Random()).nextInt(8)];
    }

    public MapDirection rotateBy(int rotation) {
        return MapDirection.values()[(this.ordinal() + rotation) % 8];
    }

    public MapDirection next(MapDirection direction) {
        return direction.rotateBy(1);
    }

    public MapDirection previous(MapDirection direction) {
        return direction.rotateBy(7);
    }

    public Vector2D toUnitVector() {
        switch (this) {
            case NORTH: return new Vector2D(0, 1);
            case NORTH_EAST: return new Vector2D(1, 1);
            case EAST: return new Vector2D(1, 0);
            case SOUTH_EAST: return new Vector2D(1, -1);
            case SOUTH: return new Vector2D(0, -1);
            case SOUTH_WEST: return new Vector2D(-1, -1);
            case WEST: return new Vector2D(-1, 0);
            case NORTH_WEST: return new Vector2D(-1, 1);
            default: return null;
        }
    }
}