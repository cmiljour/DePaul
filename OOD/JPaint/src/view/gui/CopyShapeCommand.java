package view.gui;


import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.IShape;
import view.interfaces.IUndoable;

import java.awt.*;
import java.util.ArrayList;


public class CopyShapeCommand implements IUndoable,ICommand {


    private ShapeConfiguration activeShape;
    private int x, y, width, height;
    ShapeList shapeList;
    IShape shape;
    ArrayList<IShape> shapeArrayList;
    ArrayList<IShape> cloneShapeArrayList;




    public CopyShapeCommand(IShape shape, ShapeList shapeList, ShapeConfiguration activeShapeConfiguration){

        this.x = 0;
        this.y = 0;
        this.width = shape.getWidth();
        this.height = shape.getHeight();
        this.shape = shape;
        this.shapeList = shapeList;
        this.activeShape = activeShapeConfiguration;
        this.shapeArrayList = shapeList.getShapeList();
        this.cloneShapeArrayList = new ArrayList<IShape>();

    }


    @Override
    public void run() {

        for(IShape shape : shapeArrayList){
            cloneShapeArrayList.add(shape);
        }

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

    @Override
    public void undo() {
        Graphics2D graphics2D = PaintCanvas.getCanvasInstance().getGraphics2D();
        graphics2D.clearRect(0,0,10000,10000);
        shapeList.getShapeList().clear();

        for (IShape shape : cloneShapeArrayList){
            shapeList.add(shape);
            shape.draw();
        }
    }

    @Override
    public void redo() {

    }
}
