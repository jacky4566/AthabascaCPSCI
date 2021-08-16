/**
* title: Problem5.java
* description: ***
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
* Purpose and Description:
*
* Compiling and running instructions
* Compile:  javac Problem3.java
* Run:      java Problem3.java
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

* Class: Shape
  Description: Framework for more descriptive shape classes
  
*  Constructors:
    Shape()
     Initlaizes the bounding box
    
*  Methods:
    area()
     Prints out the area of this shape, if relevent. 

    circumfrence()
     Prints out the circumfrence of this shape, if relevent. 

    display()
     Prints out all relavent information for this shape

*  Instance Variables:
    boundingBoxArray[4]
     Holds 4 points representing the bounding box of the child shape.

* Class: Circle
  Description: Child class that inherits shape for decription and calculation of a circle.
  
*  Constructors:
    Circle(Point, Point)
     accepts 2 points for the creation of a circle. does error checking and generates the bounding box.
    
*  Methods:
    area()
     Calculates area of a circle. 

    circumfrence()
     Calculates circumfrence of a circle. 

*  Instance Variables:
    Point circleCentre
     Holds a point class representing the centre of the circle.

    int radius
     Hold the value of the radii for this circule.

* Class: Rectangle
  Description: Child class that inherits shape for decription and calculation of a Rectangle.
  
*  Constructors:
    Rectangle(Point, Point, Point, Point)
     accepts 4 points for the creation of a Rectangle. does error checking and generates the bounding box.
    
*  Methods:
    area()
     Calculates area of a circle. 

    circumfrence()
     Returns null as this does not apply 

*  Instance Variables:
    rectangleBoxArray
     Holds 4 point classes representing the corners of the rectangle

    double width
     Hold the value representing the width of the rectangle

    double height
     Hold the value representing the height of the rectangle

* Class: Triangle
  Description: Child class that inherits shape for decription and calculation of a Triangle.
  
*  Constructors:
    Triangle(Point, Point, Point)
     accepts 3 points for the creation of a Triangle. does error checking and generates the bounding box.
    
*  Methods:
    area()
     Calculates area of the Triangle. 

    circumfrence()
     Returns null as this does not apply 

*  Instance Variables:
    triangleBoxArray
     Holds 3 point classes representing the corners of the Triangle
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

    static class Point {
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

        Point add(Point p1, Point p2) { // add two points and return a new one
            return new Point(p1.x + p2.x, p1.y + p2.y);
        }

        Point subtract(Point p1, Point p2) { // subtract two points and return a new one
            return new Point(p1.x - p2.x, p1.y - p2.y);
        }

        boolean isEqual(Point compare) { // compares two points for equal coordinates
            return (this.x == compare.x && this.y == compare.y);
        }
    }

    static class Shape {
        private Point[] boundingBoxArray = new Point[4]; // Create a rectanglar bounding box, 4 corners.

        public Shape() {
            // For all default shapes start with a boudning box of 0.
            for (int i = 0; i < this.boundingBoxArray.length; i++) {
                boundingBoxArray[i] = new Point(0, 0);
            }
        }

        String area() {
            // Overload this with sub types
            return "This function prints the area of a shape";
        }

        String circumfrence() {
            // Overload this with sub types
            return "This function prints the circumfrence of a shape";
        }

        void display() {
            System.out.println("Name of this class: " + this.getClass().getName()); // Display the name of the class
            if (this.area() != null) {
                System.out.println("Area of this Shape: " + this.area()); // Print the area of this shape
            }
            if (this.circumfrence() != null) {
                System.out.println("Circumfrence of this Shape: " + this.circumfrence()); // Print the circumfrence of this shape
            }
            System.out.println("Bounding Box of this Shape: ");
            for (Point value : this.boundingBoxArray) {
                value.show();
            }
            System.out.println("");
        }
    }

    static class Circle extends Shape {
        private Point circleCentre;
        private int radius;

        public Circle(Point origin, int radius) { // Create a circle with given x, y, and radius
            // Do some error checking
            if (radius <= 0) {
                System.out.println("ERROR: Circle was given with radius < 0");
                return;
            }
            // store values for new shape
            this.circleCentre = origin;
            this.radius = radius;
            // overwrite super bounding box
            // Top Left
            super.boundingBoxArray[0] = new Point(this.circleCentre.x - radius, this.circleCentre.y - radius);
            // Top Right
            super.boundingBoxArray[1] = new Point(this.circleCentre.x + radius, this.circleCentre.y - radius);
            // Bottom Right
            super.boundingBoxArray[2] = new Point(this.circleCentre.x - radius, this.circleCentre.y + radius);
            // Bottom Left
            super.boundingBoxArray[3] = new Point(this.circleCentre.x + radius, this.circleCentre.y + radius);
        }

        @Override
        public String area() {
            return String.valueOf(Math.PI * this.radius * this.radius);
        }

        @Override
        public String circumfrence() {
            return String.valueOf(2 * Math.PI * this.radius);
        }
    }

    static class Rectangle extends Shape {
        private Point[] rectangleBoxArray = new Point[4]; // Create a rectanglar box, 4 corners
        private double width;
        private double height;

        public Rectangle(Point p1, Point p2, Point p3, Point p4) {
            if (p1.x != p4.x || p2.x != p3.x || p1.y != p2.y || p3.y != p4.y) { //Check the corners are vertical/ horizontal. 
                System.out.println("ERROR: Given vertices are not orthogonal. Shape is not a rectangle");
                return;
            } else if (p1.isEqual(p2) || p1.isEqual(p3) || p1.isEqual(p4) || p2.isEqual(p3) || p2.isEqual(p4) || p3.isEqual(p4)) { //Check if any of the points are equal
                System.out.println("ERROR: Given vertices overlap. Shape is not a rectangle");
                return;
            }
            // copy the parameters into internal array
            rectangleBoxArray[0] = p1;
            rectangleBoxArray[1] = p2;
            rectangleBoxArray[2] = p3;
            rectangleBoxArray[3] = p4;
            // Check if is a square
            width = Math.abs(rectangleBoxArray[0].x - rectangleBoxArray[1].x);
            height = Math.abs(rectangleBoxArray[0].y - rectangleBoxArray[3].y);
            if (width == height) {
                System.out.println("Looks like a square to me!");
            }
            // Since both are rectanlges we can copy this straight into the bounding box.
            System.arraycopy(this.rectangleBoxArray, 0, super.boundingBoxArray, 0, 4);
        }

        @Override
        public String area() {
            return String.valueOf(width * height);
        }

        @Override
        public String circumfrence() {
            return null;
        }
    }

    static class Triangle extends Shape {
        private Point[] triangleBoxArray = new Point[3]; // Create a triangle box, 3 corners

        public Triangle(Point p1, Point p2, Point p3) {
            if (p1.isEqual(p2) || p1.isEqual(p3) || p2.isEqual(p3)) {
                System.out.println("ERROR: Given vertices overlap. Shape is not a triangle");
                return;
            }
            triangleBoxArray[0] = p1;
            triangleBoxArray[1] = p2;
            triangleBoxArray[2] = p3;
            // overwrite super bounding box
            // Calculate minimum and maximum extensions
            int minX = Math.min(p1.x, Math.min(p2.x, p3.x));
            int maxX = Math.max(p1.x, Math.max(p2.x, p3.x));
            int minY = Math.min(p1.y, Math.min(p2.y, p3.y));
            int maxY = Math.min(p1.y, Math.min(p2.y, p3.y));
            // Top Left
            super.boundingBoxArray[0] = new Point(minX, minY);
            // Top Right
            super.boundingBoxArray[1] = new Point(maxX, minY);
            // Bottom Right
            super.boundingBoxArray[2] = new Point(maxX, maxY);
            // Bottom Left
            super.boundingBoxArray[3] = new Point(minX, maxY);
        }

        @Override
        public String area() {
            for (Point value : this.triangleBoxArray) { //if anything was null we can stop here
                if (value == null) {
                    return null;
                }
            }
            return String.valueOf((triangleBoxArray[0].x * (triangleBoxArray[1].y - triangleBoxArray[2].y)
                    + triangleBoxArray[1].x * (triangleBoxArray[2].y - triangleBoxArray[0].y)
                    + triangleBoxArray[2].x * (triangleBoxArray[0].y - triangleBoxArray[1].y)) / 2);
        }

        @Override
        public String circumfrence() {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Create point");
        Point randomPoint = new Point(15, 5);
        randomPoint.show();
        System.out.println("Create second point");
        Point randomPointFriend = new Point(12, 7);
        randomPointFriend.show();
        System.out.println("Add them together");
        Point additionpoint = randomPoint.add(randomPoint, randomPointFriend);
        additionpoint.show();
        System.out.println("Take them away");
        Point subtractpoint = randomPoint.subtract(randomPoint, randomPointFriend);
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