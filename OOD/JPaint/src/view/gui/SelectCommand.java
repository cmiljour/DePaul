package view.gui;

import view.interfaces.ICommand;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectCommand implements ICommand {
    ShapeList shapeList;
    SelectedShapeList selectedShapeList;
    PaintCanvas canvas;
    ShapeConfiguration activeShape;
    private int x, y, width, height;
    Point pointsPressed;
    ArrayList<IShape> secondList;



    public SelectCommand(Point pointsPressed, ShapeList shapeList, SelectedShapeList selectedShapeList, PaintCanvas canvas, ShapeConfiguration activeShape) {
        this.shapeList = shapeList;
        this.canvas = canvas;
        this.activeShape = activeShape;
        this.x = Math.min(activeShape.getActivePointsPressed().getXpoint(), activeShape.getActivePointsReleased().getXpoint());
        this.y = Math.min(activeShape.getActivePointsPressed().getYpoint(), activeShape.getActivePointsReleased().getYpoint());
        this.width = Math.abs(activeShape.getActivePointsPressed().getXpoint() - activeShape.getActivePointsReleased().getXpoint());
        this.height = Math.abs(activeShape.getActivePointsPressed().getYpoint() - activeShape.getActivePointsReleased().getYpoint());
        this.pointsPressed = pointsPressed;
        this.secondList = shapeList.getShapeList();
        this.selectedShapeList = selectedShapeList;
    }


    @Override
    public void run() {
        for (IShape shape : secondList) {
            Rectangle rectangle = shape.getRectangle();

            //System.out.println(rectangle);
            if (rectangle.contains(x, y)){
                selectedShapeList.add(shape);
            }

        }

        selectedShapeList.printList();

    }
}
