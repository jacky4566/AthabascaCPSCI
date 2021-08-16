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
*
* Classes:
*  Point.java
*
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

public class Shape {
    Point[] boundingBoxArray = new Point[4]; // Create a rectanglar bounding box, 4 corners.

    public Shape() {
        // For all default shapes start with a boudning box of 0.
        for (int i = 0; i < this.boundingBoxArray.length; i++) {
            boundingBoxArray[i] = new Point(0, 0);
        }
    }

    String area() {
        // To be overloaded
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

class Circle extends Shape {
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

class Rectangle extends Shape {
    private Point[] rectangleArray = new Point[4]; // Create a rectanglar box, 4 corners
    private double width;
    private double height;

    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        if (p1.x != p4.x || p2.x != p3.x || p1.y != p2.y || p3.y != p4.y) { //Check the corners are vertical/ horizontal. 
            System.out.println("ERROR: Given vertices are not orthogonal. Shape is not a rectangle");
            return;
        } else if (Point.isEqual(p1, p2) || Point.isEqual(p1, p3) || Point.isEqual(p1, p4) || Point.isEqual(p2, p3) || Point.isEqual(p2, p4) || Point.isEqual(p3, p4)) { //Check if any of the points are equal
            System.out.println("ERROR: Given vertices overlap. Shape is not a rectangle");
            return;
        }
        // copy the parameters into internal array
        rectangleArray[0] = p1;
        rectangleArray[1] = p2;
        rectangleArray[2] = p3;
        rectangleArray[3] = p4;
        // Check if is a square
        width = Math.abs(rectangleArray[0].x - rectangleArray[1].x);
        height = Math.abs(rectangleArray[0].y - rectangleArray[3].y);
        if (width == height) {
            System.out.println("Looks like a square to me!");
        }
        // Since both are rectanlges we can copy this straight into the bounding box.
        System.arraycopy(this.rectangleArray, 0, super.boundingBoxArray, 0, 4);
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

class Triangle extends Shape {
    private Point[] triangleArray = new Point[3]; // Create a triangle box, 3 corners

    public Triangle(Point p1, Point p2, Point p3) {
        if (Point.isEqual(p1, p2) || Point.isEqual(p1, p3) || Point.isEqual(p2, p3)) {
            System.out.println("ERROR: Given vertices overlap. Shape is not a triangle");
            return;
        }
        triangleArray[0] = p1;
        triangleArray[1] = p2;
        triangleArray[2] = p3;
        // overwrite super bounding box
        // Calculate minimum and maximum extensions
        int minX = Math.min(p1.x, Math.min(p2.x, p3.x));
        int maxX = Math.max(p1.x, Math.max(p2.x, p3.x));
        int minY = Math.min(p1.y, Math.min(p2.y, p3.y));
        int maxY = Math.max(p1.y, Math.max(p2.y, p3.y));
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
        for (Point value : this.triangleArray) { //if anything was null we can stop here
            if (value == null) {
                return null;
            }
        }
        return String.valueOf((triangleArray[0].x * (triangleArray[1].y - triangleArray[2].y)
                + triangleArray[1].x * (triangleArray[2].y - triangleArray[0].y)
                + triangleArray[2].x * (triangleArray[0].y - triangleArray[1].y)) / 2);
    }

    @Override
    public String circumfrence() {
        return null;
    }
}