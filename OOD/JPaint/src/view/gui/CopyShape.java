package view.gui;

import view.interfaces.IShape;

import java.awt.*;

public class CopyShape {
    private CopyShape(){}

    public static IShape copyShape(IShape shape, ShapeConfiguration activeShape){
        int width = shape.getWidth();
        int height = shape.getHeight();

        if(shape.getActiveShape().toString().equalsIgnoreCase("TRIANGLE")){
            int xDiff1 = shape.getXarrIndex(1) - activeShape.getActivePointsPressed().getXpoint();
            int xDiff2 = shape.getXarrIndex(2) - activeShape.getActivePointsPressed().getXpoint();
            int yDiff1 = shape.getYarrIndex(1) - activeShape.getActivePointsPressed().getYpoint();
            int yDiff2 = shape.getYarrIndex(2) - activeShape.getActivePointsPressed().getYpoint();

            Triangle triangle = new Triangle(activeShape);
            triangle.setXarr(0, 0);
            triangle.setXarr(1, xDiff1);
            triangle.setXarr(2, xDiff2);
            triangle.setYarr(0, 0);
            triangle.setYarr(1, yDiff1);
            triangle.setYarr(2, yDiff2);
            triangle.draw();
            triangle.setRectangle(new Rectangle(0,0,width,height));
            return triangle;

        } else if (shape.getActiveShape().toString().equalsIgnoreCase("ELLIPSE")){
            Ellipse ellipse = new Ellipse(activeShape);
            ellipse.setX(10);
            ellipse.setY(10);
            ellipse.draw();
            ellipse.setRectangle(new Rectangle(10,10,width,height));
            return ellipse;

        } else if(shape.getActiveShape().toString().equalsIgnoreCase("RECTANGLE")){
            MyRectangle rectangle = new MyRectangle(activeShape);
            rectangle.setX(5);
            rectangle.setY(5);
            rectangle.draw();
            rectangle.setRectangle(new Rectangle(5,5,width,height));
            return rectangle;
        }

        return null;
    }
}
