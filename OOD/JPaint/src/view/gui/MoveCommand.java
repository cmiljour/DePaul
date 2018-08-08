package view.gui;

import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

import static model.ShapeType.TRIANGLE;

public class MoveCommand implements ICommand {

    ShapeList shapeList;
    ArrayList<IShape> arrayList;
    SelectedShapeList selectedShapeList;
    PaintCanvas canvas;
    ShapeConfiguration activeShape;
    private int x, y, width, height;
    Point pointsPressed;
    Point pointsReleased;
    ArrayList<IShape> arraySelectedList;



    public MoveCommand(Point pointsPressed, Point pointsReleased, ShapeList shapeList, SelectedShapeList selectedShapeList, PaintCanvas canvas, ShapeConfiguration activeShape) {
        this.shapeList = shapeList;
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.pointsPressed = pointsPressed;
        this.pointsReleased = pointsReleased;
        this.arrayList = shapeList.getShapeList();
        this.selectedShapeList = selectedShapeList;
        this.arraySelectedList = selectedShapeList.getShapeList();
    }


    @Override
    public void run() {

        int xDiff;
        int yDiff;
        int xDiff0;
        int yDiff0;
        int xDiff1;
        int yDiff1;
        int xDiff2;
        int yDiff2;

        Graphics2D graphics2D = canvas.getGraphics2D();


        //selectedShapeList.printList();
        for (IShape shape : arraySelectedList)
            if (shape.getActiveShape() == TRIANGLE) {
                xDiff0 = shape.getXarrIndex(0) - pointsPressed.getXpoint();
                xDiff1 = shape.getXarrIndex(1) - pointsPressed.getXpoint();
                xDiff2 = shape.getXarrIndex(2) - pointsPressed.getXpoint();
                yDiff0 = shape.getYarrIndex(0) - pointsPressed.getYpoint();
                yDiff1 = shape.getYarrIndex(1) - pointsPressed.getYpoint();
                yDiff2 = shape.getYarrIndex(2) - pointsPressed.getYpoint();

                shape.setXarr(0, pointsReleased.getXpoint() + xDiff0);
                shape.setXarr(1, pointsReleased.getXpoint() + xDiff1);
                shape.setXarr(2, pointsReleased.getXpoint() + xDiff2);
                shape.setYarr(0, pointsReleased.getYpoint() + yDiff0);
                shape.setYarr(1, pointsReleased.getYpoint() + yDiff1);
                shape.setYarr(2, pointsReleased.getYpoint() + yDiff2);

                shape.setRectangle(new Rectangle (pointsReleased.getXpoint() + xDiff0,  pointsReleased.getYpoint() + yDiff0, shape.getWidth(), shape.getHeight() ));


            } else {

                xDiff = shape.getX() - pointsPressed.getXpoint();
                yDiff = shape.getY() - pointsPressed.getYpoint();
                shape.setX(pointsReleased.getXpoint() + xDiff);
                shape.setY(pointsReleased.getYpoint() + yDiff);
                shape.setRectangle(new Rectangle(pointsReleased.getXpoint() + xDiff, pointsReleased.getYpoint() + yDiff, shape.getWidth(), shape.getHeight() ));

            }


        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0,0,100000,100000);




        for (IShape shape : arrayList) {

            shape.draw();
           // shapeList.printList();
        }

        //selectedShapeList.printList();

    }
}
