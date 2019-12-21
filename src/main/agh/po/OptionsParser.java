package agh.po;


import java.util.ArrayList;
import java.util.Arrays;

public class OptionsParser {
    public static MapDirection parse(int arg) {
        MapDirection result;
        switch (arg) {
            case 0:
                result = MapDirection.NORTH;
                break;
            case 1:
                result = MapDirection.NORTH_EAST;
                break;
            case 2:
                result = MapDirection.EAST;
                break;
            case 3:
                result = MapDirection.SOUTH_EAST;
                break;
            case 4:
                result = MapDirection.SOUTH;
                break;
            case 5:
                result = MapDirection.SOUTH_WEST;
                break;
            case 6:
                result = MapDirection.WEST;
                break;
            case 7:
                result = MapDirection.NORTH_WEST;
                break;
            default:
                throw new IllegalArgumentException(arg + " is not legal move specification");
        }
        return result;
    }

    public static ArrayList<MapDirection> getAllDirectionsVectors(){
        ArrayList<MapDirection> directions = new ArrayList<MapDirection>();
        for(int i=0;i<8;i++){
            directions.add(parse(i));
        }
        return directions;
    }

}
//redundant