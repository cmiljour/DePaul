package view.gui;


import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;


public class CopyShapeCommand implements ICommand {


    private ShapeConfiguration activeShape;
    private int x, y, width, height;
    ShapeList shapeList;
    IShape shape;




    public CopyShapeCommand(IShape shape, ShapeList shapeList, ShapeConfiguration activeShapeConfiguration){

        this.x = 0;
        this.y = 0;
        this.width = shape.getWidth();
        this.height = shape.getHeight();
        this.shape = shape;
        this.shapeList = shapeList;
        this.activeShape = activeShapeConfiguration;

    }


    @Override
    public void run() {


        switch (shape.getActiveShape()) {
            case TRIANGLE:

                Triangle triangle = new Triangle(activeShape);


                int xDiff1 = shape.getXarrIndex(1) - activeShape.getActivePointsPressed().getXpoint();
                int xDiff2 = shape.getXarrIndex(2) - activeShape.getActivePointsPressed().getXpoint();

                int yDiff1 = shape.getYarrIndex(1) - activeShape.getActivePointsPressed().getYpoint();
                int yDiff2 = shape.getYarrIndex(2) - activeShape.getActivePointsPressed().getYpoint();

                triangle.setXarr(0, 0);
                triangle.setXarr(1, xDiff1);
                triangle.setXarr(2, xDiff2);
                triangle.setYarr(0, 0);
                triangle.setYarr(1, yDiff1);
                triangle.setYarr(2, yDiff2);


                triangle.draw();
                triangle.setRectangle(new Rectangle(0,0,width,height));
                shapeList.add(triangle);
                break;

            case ELLIPSE:

                Ellipse ellipse = new Ellipse(activeShape);
                ellipse.setX(10);
                ellipse.setY(10);
                ellipse.draw();
                ellipse.setRectangle(new Rectangle(10,10,width,height));
                shapeList.add(ellipse);
                break;

            case RECTANGLE:

                MyRectangle rectangle = new MyRectangle(activeShape);
                rectangle.setX(5);
                rectangle.setY(5);
                rectangle.draw();
                rectangle.setRectangle(new Rectangle(5,5,width,height));
                shapeList.add(rectangle);
                break;
        }

    }
}
