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


        //selectedShapeList.printList();
        for (IShape shape : arraySelectedList) {

           // if (!(shape.getActiveShape().getActiveShapeType() == TRIANGLE)){
                xDiff = shape.getX() - pointsPressed.getXpoint();
                yDiff = shape.getY() - pointsPressed.getYpoint();
                shape.setX(pointsReleased.getXpoint() + xDiff);
                shape.setY(pointsReleased.getYpoint() + yDiff);

            //}


        }

        Graphics2D graphics2D = canvas.getGraphics2D();
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0,0,100000,100000);


        for (IShape shape : arrayList) {

            shape.draw();
           // shapeList.printList();
        }

        //selectedShapeList.printList();

    }
}
