package edu.sabana.poob.shapes;

public class Cube extends RectangleSolid {

    public Cube(){}

    public Cube(String color)
    {
        super(color);
    }

    public Cube(double side)
    {
        this.length = side;
        this.width = side;
        this.depth = side;
    }
    public Cube(String color, double side)
    {
        super(color);
        this.length = side;
        this.width = side;
        this.depth = side;
    }

}
