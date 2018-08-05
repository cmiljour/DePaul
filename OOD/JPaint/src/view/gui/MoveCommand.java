package view.gui;

import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand {

    ShapeList shapeList;
    ArrayList<IShape> arrayList;
    SelectedShapeList selectedShapeList;
    PaintCanvas canvas;
    ShapeConfiguration activeShape;
    private int x, y, width, height;
    Point pointsPressed;
    ArrayList<IShape> arraySelectedList;



    public MoveCommand(Point pointsPressed, ShapeList shapeList, SelectedShapeList selectedShapeList, PaintCanvas canvas, ShapeConfiguration activeShape) {
        this.shapeList = shapeList;
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.pointsPressed = pointsPressed;
        this.arrayList = shapeList.getShapeList();
        this.selectedShapeList = selectedShapeList;
        this.arraySelectedList = selectedShapeList.getShapeList();
    }


    @Override
    public void run() {

        //selectedShapeList.printList();
        for (IShape shape : arraySelectedList) {

            shape.setX(x);
            shape.setY(y);


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
