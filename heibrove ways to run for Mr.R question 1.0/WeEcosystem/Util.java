import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Util here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Util {
    public static int distanceBetween(Actor a, Actor b) { // calculate distance between 2 actors
        int distance = -1;
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance;
    }
    ////////////////////////////////////////////////
    /*
    public static List<Point> getCircleEgde(int x, int y, int radius) {
        List<Point> points = new ArrayList();
        
        return points;
    }
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean isSame(Point point) {
            if (point.x == x) {
                if (point.y == y) {
                    return true;
                }
            }
            return false;
        }
    }
    */
}
