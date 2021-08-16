/**
* title: Point.java
* description: Holds a point.
* date: 13/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
*
* Classes:
*
* Class: Point
  Description: Holds X and Y ints for coordinates of a 2D point.

*  Constructors:
    Point(int, int)
     Creates a new 2D point with coordinates X and Y
    
*  Methods:
    show()
     Prints out the point coordinates to the terminal

    add(Point, Point)
     Adds two points and returns a new point object

    subtract(Point, Point)
     Subtracts two points and returns a new point object

    isEqual(Point)
     Compares the given point to the current point and compares if they are equal X and Y. Boolean Return.

*  Instance Variables:
    Int y
     Holds an X coordinate

    Int y
     Holds a y coordinate
*/

public class Point {
    // Holds x and y values
    int x;
    int y;

    public Point(int initX, int initY) {// constructor to create point with x, y coordinates
        this.x = initX;
        this.y = initY;
    }

    void show() { // Prints this point for display
        System.out.println("Point Location: [ " + this.x + " ][ " + this.y + " ]");
    }

    static Point add(Point p1, Point p2) { // add two points and return a new one
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    static Point subtract(Point p1, Point p2) { // subtract two points and return a new one
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    static boolean isEqual(Point compare1, Point compare2) { // compares two points for equal coordinates
        return (compare1.x == compare2.x && compare1.y == compare2.y);
    }
}