/**
* title: Problem5.java
* description: Manipulation of shapes with the use of points.
* date: 13/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem5
*
* Purpose and Description: Purpose of this program is to demonstrate the creation of a point class and use that to create shapes and calculate data from those shapes.
*
* Compiling and running instructions
* Compile:  javac Problem3.java
* Run:      java Problem3.java
*
* Classes:
*  Point.java
*  Shape.java
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:

    Create point
    Point Location: [ 15 ][ 5 ]
    Create second point
    Point Location: [ 12 ][ 7 ]
    Add them together
    Point Location: [ 27 ][ 12 ]
    Take them away
    Point Location: [ 3 ][ -2 ]

    Create a circle shape
    Name of this class: Problem5$Circle
    Area of this Shape: 78.53981633974483
    Circumfrence of this Shape: 31.41592653589793
    Bounding Box of this Shape:
    Point Location: [ -3 ][ -2 ]
    Point Location: [ 7 ][ -2 ]
    Point Location: [ -3 ][ 8 ]
    Point Location: [ 7 ][ 8 ]

    Create a circle shape with no Radius
    ERROR: Circle was given with radius < 0
    Name of this class: Problem5$Circle
    Area of this Shape: 0.0
    Circumfrence of this Shape: 0.0
    Bounding Box of this Shape:
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]

    Create a square shape
    Looks like a square to me!
    Name of this class: Problem5$Rectangle
    Area of this Shape: 25.0
    Bounding Box of this Shape:
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 5 ][ 0 ]
    Point Location: [ 5 ][ 5 ]
    Point Location: [ 0 ][ 5 ]

    Create a rectangle shape
    Name of this class: Problem5$Rectangle
    Area of this Shape: 50.0
    Bounding Box of this Shape:
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 10 ][ 0 ]
    Point Location: [ 10 ][ 5 ]
    Point Location: [ 0 ][ 5 ]

    Create a parallelogram shape
    ERROR: Given vertices are not orthogonal. Shape is not a rectangle
    Name of this class: Problem5$Rectangle
    Area of this Shape: 0.0
    Bounding Box of this Shape:
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]

    Create a triangle shape
    Name of this class: Problem5$Triangle
    Area of this Shape: 25
    Bounding Box of this Shape:
    Point Location: [ -5 ][ 0 ]
    Point Location: [ 5 ][ 0 ]
    Point Location: [ 5 ][ 0 ]
    Point Location: [ -5 ][ 0 ]

    Create a triangle with overlapping points
    ERROR: Given vertices overlap. Shape is not a triangle
    Name of this class: Problem5$Triangle
    Bounding Box of this Shape:
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]
    Point Location: [ 0 ][ 0 ]

* ACTUAL:
*    Terminal frame displays as expected
*
* Good data cases:
*   No outside data expected
*
* Bad data cases:
*   No outside data expected
*/

public class Problem5 {

    public static void main(String[] args) {
        System.out.println("Create point");
        Point randomPoint = new Point(15, 5);
        randomPoint.show();
        System.out.println("Create second point");
        Point randomPointFriend = new Point(12, 7);
        randomPointFriend.show();
        System.out.println("Add them together");
        Point additionpoint = Point.add(randomPoint, randomPointFriend);
        additionpoint.show();
        System.out.println("Take them away");
        Point subtractpoint = Point.subtract(randomPoint, randomPointFriend);
        subtractpoint.show();
        System.out.println();

        System.out.println("Create a circle shape");
        Circle circleMan = new Circle(new Point(2, 3), 5);
        circleMan.display();

        System.out.println("Create a circle shape with no Radius");
        Circle pointCircle = new Circle(new Point(2, 3), 0);
        pointCircle.display();

        System.out.println("Create a square shape");
        Rectangle squareBob = new Rectangle(new Point(0, 0), new Point(5, 0), new Point(5, 5), new Point(0, 5));
        squareBob.display();

        System.out.println("Create a rectangle shape");
        Rectangle rectangleBob = new Rectangle(new Point(0, 0), new Point(10, 0), new Point(10, 5), new Point(0, 5));
        rectangleBob.display();

        System.out.println("Create a parallelogram shape");
        Rectangle parallelogram = new Rectangle(new Point(1, 0), new Point(11, 0), new Point(10, 5), new Point(0, 5));
        parallelogram.display();

        System.out.println("Create a triangle shape");
        Triangle threeSidedObj = new Triangle(new Point(0, 0), new Point(5, 5), new Point(-5, 5));
        threeSidedObj.display();

        System.out.println("Create a triangle with overlapping points");
        Triangle overlapTrig = new Triangle(new Point(0, 0), new Point(0, 0), new Point(-5, 5));
        overlapTrig.display();
    }
}